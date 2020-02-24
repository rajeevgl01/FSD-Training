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



@WebServlet("/updatenameui")
public class UpdateName extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		RequestDispatcher dispatcher =  null;
		Bean b = new Bean();
		ServiceClass service = new ServiceClass();
		if(request.getSession(false)==null) {
			out.println("session ended");
			dispatcher = request.getRequestDispatcher("Index.html");
			dispatcher.include(request, response);		
		}
		else {
			b.setAccountNumber(Integer.parseInt(request.getParameter("accountNumber")));
			b.setName(request.getParameter("newName"));
			if(service.UpdateNameServe(b)>0) {
				out.println("name updated");
				dispatcher = request.getRequestDispatcher("Logout.html");
				dispatcher.include(request, response);
			}
			else {
				out.println("name not updated <br> try again");
				dispatcher = request.getRequestDispatcher("Login.html");
				dispatcher.include(request, response);
			}
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
