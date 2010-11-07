package com.buzzters.hotspotz.ui;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.buzzters.hotspotz.dao.PMF;
import com.buzzters.hotspotz.model.UserLocation;


@SuppressWarnings("serial")
public class GetContactLocations extends HttpServlet
{
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		PersistenceManager pm = PMF.get().getPersistenceManager();
		resp.setContentType("text/plain");
		// Get the emailIds from the request.
		
		String emailIds = req.getParameter("emailIds");
		StringBuilder responseBuilder = new StringBuilder("");
		// Get the locations from the database and return them
		try
		{		
			for(String emailId : emailIds.split(","))
			{
				String getDataQuery = "select from " + UserLocation.class.getName()
										+ " where userEmail == '" + emailId + "'";
				@SuppressWarnings("unchecked")
				List<UserLocation> userLocation = (List<UserLocation>)pm.newQuery(getDataQuery).execute();
				if(userLocation != null && !userLocation.isEmpty())
				{
					responseBuilder.append(userLocation.get(0).toSerializedString()).append(";");
				}
			}
		}
		finally
		{
			pm.close();
		}
		resp.getWriter().println(responseBuilder.toString());		
	}
}
