package com.wallet.ui;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wallet.bean.Bean;
import com.wallet.dao.DaoClass;
import com.wallet.service.ServiceClass;

@WebServlet("/insertui")
public class InsertUI extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		ServiceClass service = new ServiceClass();
		RequestDispatcher dispatcher = null;
		
		Bean b = new Bean();
		Random rand = new Random();
		
		long phone = Long.parseLong(request.getParameter("holderContactNumber"));

		
		b.setPhoneNumber(phone);
		
		if(new DaoClass().getPhone(b) == false) {
			String pass = request.getParameter("password");
			//System.out.println(pass);
			String name = request.getParameter("holderName");
			long bal = Long.parseLong(request.getParameter("accountBalance"));
			
			b.setPhoneNumber(phone);
			b.setPassword(pass);
			//System.out.println(b.getPassword());
			b.setName(name);
			b.setBalance(bal);
			
			int accountNumber = rand.nextInt(Integer.MAX_VALUE)+1;
			b.setAccountNumber(accountNumber);
			
			if(service.InsertServe(b) >= 2) {
				out.println("account created");
				dispatcher = request.getRequestDispatcher("Login.html");
				dispatcher.include(request, response);
			}
			else {
				out.println("account not created <br> try again");
				dispatcher = request.getRequestDispatcher("Index.html");
				dispatcher.include(request, response);
			}		
		}
		else {
			out.print("User already exists..");
			out.println("session ended");
			dispatcher = request.getRequestDispatcher("Index.html");
			dispatcher.include(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
