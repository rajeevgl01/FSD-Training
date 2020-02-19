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

@WebServlet("/register")
public class NewUser extends HttpServlet {
	
	DaoClass dao;	
	Connection dbCon;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		dao = new DaoClass();
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("link.html").include(request, response);
		
		String user = request.getParameter("name");
		String password = request.getParameter("password");
		
		if(!dao.getUserId(user)) {
			if(!password.equals(null) && !user.equals(null)){
				if(dao.addNewUser(user, password) > 0) {		
					out.print("Welcome, "+ user);
					out.print("Your new account has been created...");
					HttpSession session = request.getSession();
					session.setAttribute("name", user);
					response.setHeader("Cache-Control", "no-cache, no-store"); // HTTP 1.1.
					response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
					response.setDateHeader("Expires", -1);
				}
				else{
					out.print("Sorry, username or password cannot be empty!");
					request.getRequestDispatcher("login.html").include(request, response);
				}
			}
		}
		else {
			out.print("User already exists.. enter a new username");
			request.getRequestDispatcher("login.html").include(request, response);
		}
		out.close();
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
