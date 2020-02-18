package com.wallet.ui;

import java.util.List;
import java.util.Scanner;

import com.wallet.bean.Bean;
import com.wallet.service.ServiceClass;

public class TransactionUI {
	Scanner scan;
	public TransactionUI() {

		Bean b = new Bean();
		ServiceClass serve = new ServiceClass();
		scan = new Scanner(System.in);
		
		System.out.println("Enter Phone Number");
		b.setPhoneNumber(scan.nextLong());
		
		List<Bean> bilers = serve.TransactServe(b);
		for(Bean val : bilers) {
			System.out.print("Transaction Number : " + val.getTransactionNumber() );
			System.out.print(" Account Number : " + val.getAccountNumber());
			System.out.print(" Amount : " + val.getTransactionAmount());
			System.out.println(" Time : " + val.getTransactionTime());
		}
		
		
	}

}
