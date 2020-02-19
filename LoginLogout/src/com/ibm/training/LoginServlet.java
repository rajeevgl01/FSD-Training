package com.ibm.training;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ibm.dao.DaoClass;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	DaoClass dao;
	Connection dbCon;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		dao = new DaoClass();
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("link.html").include(request, response);
		
		String user = request.getParameter("name");
		String password = request.getParameter("password");
		
		if(dao.getUserId(user)) {
			if(dao.getPassword(user, password)){
				out.print("Welcome, "+ user);
				HttpSession session = request.getSession();
				session.setAttribute("name", user);
//				response.setHeader("Cache-Control", "no-cache, no-store"); // HTTP 1.1.
//				response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
//				response.setDateHeader("Expires", -1);
			}
			else{
				out.print("Sorry, username or password incorrect!");
				request.getRequestDispatcher("login.html").include(request, response);
			}
		}
		out.close();
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
