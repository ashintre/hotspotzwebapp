package com.buzzters.hotspotz.ui;

import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class HotspotzWebAppServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {		
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, Buzzters");
	}
}
