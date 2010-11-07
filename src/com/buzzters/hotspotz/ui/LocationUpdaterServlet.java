package com.buzzters.hotspotz.ui;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.buzzters.hotspotz.dao.PMF;
import com.buzzters.hotspotz.model.UserLocation;
import com.google.appengine.api.datastore.Email;
import com.google.appengine.api.datastore.GeoPt;

@SuppressWarnings("serial")
public class LocationUpdaterServlet extends HttpServlet {
	
	private static final Logger logger = Logger.getLogger(LocationUpdaterServlet.class.getName()); 
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {				
	}	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		logger.info("Called the POST Request in LocationUpdaterServlet");
		GeoPt geoPt = new GeoPt(Float.valueOf(req.getParameter("latitude")), 
								Float.valueOf(req.getParameter("longitude")));
		Email email = new Email(URLDecoder.decode(req.getParameter("userEmail"), "UTF-8"));
		String userGroup = req.getParameter("userGroup");
		
		UserLocation userLocation = new UserLocation(userGroup, email, geoPt, new Date());
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String getDataQuery = "select from " + UserLocation.class.getName()
								+ " where userEmail == '" + email.getEmail() + "'";
		@SuppressWarnings("unchecked")
		List<UserLocation> existingLocations = (List<UserLocation>)pm.newQuery(getDataQuery).execute();
		try
		{
			if(existingLocations != null && !existingLocations.isEmpty())
			{
				existingLocations.get(0).setGeoPt(geoPt);
				existingLocations.get(0).setDate(new Date());
				pm.makePersistent(existingLocations.get(0));
			}
			else
			{			
				pm.makePersistent(userLocation);
				logger.info("Successfully persisted entries into datastore");		
			}
		}
		finally
		{
			pm.close();
		}				
	}
}
