package com.wallet.ui;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wallet.bean.Bean;
import com.wallet.dao.DaoClass;

@WebServlet("/walletwebUI")
public class WalletWebUI extends HttpServlet {
	PreparedStatement pstmt;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		Bean b = new Bean();
		PrintWriter out = response.getWriter();
		
		//ServiceClass service = new ServiceClass();
		HttpSession session = request.getSession();
		b.setPhoneNumber(Long.parseLong(request.getParameter("holderContactNumber")));
		b.setPassword(request.getParameter("passWord"));
		
		if(new DaoClass().getPassword(b)) {
			out.println("logged in");
			RequestDispatcher dispatcher = request.getRequestDispatcher("Login.html");
			dispatcher.include(request, response);
		}
		else {
			out.println("wrong password!!");
			RequestDispatcher dispatcher = request.getRequestDispatcher("Index.html");
			dispatcher.include(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
