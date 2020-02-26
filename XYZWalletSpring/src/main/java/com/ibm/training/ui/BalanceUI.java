package com.ibm.training.ui;

import java.util.Scanner;

import com.ibm.training.bean.Bean;
import com.ibm.training.service.ServiceClass;

public class BalanceUI {
	Scanner scan;
	public BalanceUI(Bean b, ServiceClass serve) {
		scan = new Scanner(System.in);
		
		System.out.println("Enter Phone Number");
		b.setPhoneNumber(scan.nextLong());
		
		System.out.println(serve.BalanceServe(b));		
	}
}
