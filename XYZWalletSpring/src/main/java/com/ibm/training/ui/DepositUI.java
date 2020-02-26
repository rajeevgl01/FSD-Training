package com.ibm.training.ui;

import java.util.Scanner;

import com.ibm.training.bean.Bean;
import com.ibm.training.service.ServiceClass;

public class DepositUI {
	Scanner scan;
	public DepositUI(Bean b, ServiceClass serve) {
		scan = new Scanner(System.in);
		
		System.out.println("Enter Phone Number");
		b.setPhoneNumber(scan.nextLong());
		
		System.out.println("Enter amount to be deposited");
		b.setTransactionAmount(scan.nextLong());
		
		b.setTransactionTime(serve.CurrentTime(b));
		
		if(serve.DepositServe(b) == true)
			System.out.println("Balance updated");
		else
			System.out.println("Balance not updated");
	
	}
}
