package com.buzzters.hotspotz.ui;

import java.io.IOException;
import java.net.URLDecoder;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.buzzters.hotspotz.dao.PMF;
import com.buzzters.hotspotz.model.MeetingPlace;
import com.google.appengine.api.datastore.GeoPt;

@SuppressWarnings("serial")
public class AddMeetingPlace extends HttpServlet
{	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		String tag = URLDecoder.decode(req.getParameter("tag"), "UTF-8");
		String nameOfPlace = URLDecoder.decode(req.getParameter("nameOfPlace"), "UTF-8");
		GeoPt geoPt = new GeoPt(Float.valueOf(req.getParameter("latitude")), 
				Float.valueOf(req.getParameter("longitude")));
		
		MeetingPlace newMeetingPlace = new MeetingPlace(tag, nameOfPlace, geoPt);
		try
		{
			pm.makePersistent(newMeetingPlace);
		}
		finally
		{
			pm.close();
		}
	}
}
