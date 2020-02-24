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
import com.wallet.dao.DaoClass;
import com.wallet.service.ServiceClass;


@WebServlet("/depositfundsui")
public class DeposiFundsUI extends HttpServlet {
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		ServiceClass service = new ServiceClass();
		Bean b = new Bean();
		PrintWriter out = response.getWriter();
		RequestDispatcher dispatcher = null;
		if(request.getSession(false)==null) {
			out.println("session ended");
			dispatcher = request.getRequestDispatcher("Index.html");
			dispatcher.include(request, response);		
		}
		else {
			
			b.setPhoneNumber(Long.parseLong(request.getParameter("holderContactNumber")));
			b.setAmount(Integer.parseInt(request.getParameter("transactionAmount")));
			b.setTransactionTime(new DaoClass().getCurrentTime());
			
			if(service.DepositServe(b) == 2) {
				out.println("Amount Deposited");
				dispatcher = request.getRequestDispatcher("Logout.html");
				dispatcher.include(request, response);
			}
			else {
				out.println("Amount not deposited <br> try again");
				dispatcher = request.getRequestDispatcher("Login.html");
				dispatcher.include(request, response);
			}
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
