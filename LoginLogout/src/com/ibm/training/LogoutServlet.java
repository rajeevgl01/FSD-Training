package com.ibm.training;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		if(request.getSession(false) != null) {
			response.setContentType("text/html");
			HttpSession session = request.getSession();
			request.getRequestDispatcher("index2.html").include(request, response);	
			out.print("<br>" + "You are successfully logged out!" + "<br>");
			out.println("Session has ended.");
//			session.invalidate();
			response.setHeader("Cache-Control", "no-cache");

			response.setHeader("Cache-Control", "no-store");

			response.setDateHeader("Expires", -1);

			response.setHeader("Pragma", "no-cache");
			session.invalidate();
			
		}
		else {
			request.getRequestDispatcher("login.html").include(request, response);;
			out.println("Session has ended.  Please login.");
		}
		out.close();
	}
	

}

