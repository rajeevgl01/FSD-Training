package com.ibm.training.ui;

import java.util.Scanner;

import com.ibm.training.bean.Bean;
import com.ibm.training.service.ServiceClass;


public class WithdrawUI {
	Scanner scan;
	public WithdrawUI(Bean b, ServiceClass serve) {
		scan = new Scanner(System.in);
		
		System.out.println("Enter Phone Number");
		b.setPhoneNumber(scan.nextLong());
		
		System.out.println("Enter amount to be withdrawn");
		b.setTransactionAmount(scan.nextLong());
		
		b.setTransactionTime(serve.CurrentTime(b));
		if(serve.WithdrawServe(b) == 2)
			System.out.println("Balance updated");
		else if(serve.WithdrawServe(b) == 1)
			System.out.println("Transaction updated but issue with balance");
		else if(serve.WithdrawServe(b) == 0)
			System.out.println("issue with transactions");
		else
			System.out.println("Balance inssufficient");
	}

}
