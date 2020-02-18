package com.wallet.ui;

import java.sql.SQLException;
import java.util.Scanner;

import com.wallet.bean.Bean;
import com.wallet.service.ServiceClass;

public class BalanceUI {
	Scanner scan;
	public BalanceUI() {
		
		Bean b = new Bean();
		ServiceClass serve = new ServiceClass();
		scan = new Scanner(System.in);
		
		System.out.println("Enter Phone Number");
		b.setPhoneNumber(scan.nextLong());
		
		try {
			(serve.BalanceServe(b)).next();
			System.out.println("Balance : " + (serve.BalanceServe(b)).getInt("Balance"));
		} catch (SQLException e) {
			System.out.println("issue with balance retrieval " + e.getMessage());
		}
		
		
	}

}
