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


@WebServlet("/fundstransferui")
public class FundsTransferUI extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		ServiceClass service = new ServiceClass();
		RequestDispatcher dispatcher = null;
		Bean b = new Bean();
		
		if(request.getSession() == null) {
			out.println("session ended");
			dispatcher = request.getRequestDispatcher("Index.html");
			dispatcher.include(request, response);		
		}
		else {
			//b.setTransactionTime(new DaoClass().getCurrentTime());
			
			b.setPhoneNumber(Long.parseLong(request.getParameter("senderContactNumber")));
			b.setAmount(Long.parseLong(request.getParameter("transactionAmount")));
			
			if(service.WithdrawServe(b) == 2) {
				b.setPhoneNumber(Long.parseLong(request.getParameter("receiverContactNumber")));
				if(service.DepositServe(b) == 2) {
					out.println("Amount Deposited");
				}
				else {
					out.println("Amount not deposited");
				}
				out.println("Amount Withrawn");
				dispatcher = request.getRequestDispatcher("Logout.html");
				dispatcher.include(request, response);
			}
			else {
				out.println("Amount not withdrawn <br> try again");
				dispatcher = request.getRequestDispatcher("Login.html");
				dispatcher.include(request, response);
			}
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
