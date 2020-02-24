package com.wallet.ui;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wallet.bean.Bean;
import com.wallet.service.ServiceClass;


@WebServlet("/printbalanceui")
public class PrintBalanceUI extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		ServiceClass service= new ServiceClass();
		RequestDispatcher dispatcher = null;
		PrintWriter out = response.getWriter();
		Bean b = new Bean();
		if(request.getSession(false)==null) {
			out.println("session ended");
			dispatcher = request.getRequestDispatcher("Index.html");
			dispatcher.include(request, response);		
		}
		else {
			b.setPhoneNumber(Long.parseLong(request.getParameter("holderContactNumber")));
			
			Bean rs = service.BalanceServe(b);
			out.println("the account balance is : " + rs.getBalance());
			dispatcher = request.getRequestDispatcher("Logout.html");
			dispatcher.include(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
