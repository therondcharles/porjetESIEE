package com.SI;

import java.io.IOException;
import javax.servlet.http.*;

import com.google.appengine.api.datastore.Entity;

@SuppressWarnings("serial")
public class PorjetESIEEServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, you are my new friend");
	}
	
	
}
