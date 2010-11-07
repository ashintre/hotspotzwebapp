package com.buzzters.hotspotz.ui;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.buzzters.hotspotz.dao.PMF;
import com.buzzters.hotspotz.model.MeetingPlace;

@SuppressWarnings("serial")
public class GetTagLocations extends HttpServlet
{
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String tag = URLDecoder.decode(req.getParameter("tag"), "UTF-8");
		StringBuilder responseBuilder = new StringBuilder("");
		
		String getTagLocationsQuery = "select from " + MeetingPlace.class.getName() +
										" where tag == '" + tag + "'";
		@SuppressWarnings("unchecked")
		List<MeetingPlace> tagLocations = (List<MeetingPlace>)pm.newQuery(getTagLocationsQuery).execute();
		if(tagLocations != null)
		{
			for(MeetingPlace meetingPlace : tagLocations)
			{
				responseBuilder.append(meetingPlace.toSerializedString()).append(";");
			}
		}
		resp.setContentType("text/plain");
		resp.getWriter().println(responseBuilder.toString());
	}
}
