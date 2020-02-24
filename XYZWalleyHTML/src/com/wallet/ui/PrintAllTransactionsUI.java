package com.wallet.ui;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wallet.bean.Bean;
import com.wallet.service.ServiceClass;


@WebServlet("/printAlltransactionsui")
public class PrintAllTransactionsUI extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		ServiceClass service = new ServiceClass();
		RequestDispatcher dispatcher = null;
		Bean b = new Bean();
				
		b.setPhoneNumber(Long.parseLong(request.getParameter("number")));
		
		if(request.getSession() == null) {
			out.println("session ended");
			dispatcher = request.getRequestDispatcher("Index.html");
			dispatcher.include(request, response);		
		}
		else {
			List<Bean> bilers = service.TransactServe(b);
			for(Bean val : bilers) {
				out.print(val.printData() + "<br>" );
			}
			dispatcher = request.getRequestDispatcher("Logout.html");
			dispatcher.include(request, response);
		}
}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
