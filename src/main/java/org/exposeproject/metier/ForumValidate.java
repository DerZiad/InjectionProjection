package org.exposeproject.metier;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

public class ForumValidate implements Serializable {

	private HttpServletRequest req;
	private HashMap<String, String> errors;
	private HttpServletResponse resp;
	
	
	

	public ForumValidate(HttpServletRequest request) {
		this.req = request;
		errors = new HashMap<String, String>();
	}

	public void readForm() throws IOException, ServletException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		Collection<Part> parts = req.getParts();
		for(Part file:parts) {
			//file.get
		}
	}

	

}
