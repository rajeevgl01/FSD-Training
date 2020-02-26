package com.ibm.training.ui;

import java.util.Scanner;

import com.ibm.training.bean.Bean;
import com.ibm.training.service.ServiceClass;

public class TransactionUI {
	Scanner scan;
	public TransactionUI(Bean b, ServiceClass serve) {
		scan = new Scanner(System.in);
		
		System.out.println("Enter Phone Number");
		b.setPhoneNumber(scan.nextLong());
		
		for(Bean val : serve.TransactServe(b)) {
			System.out.println(val);
		}
	}
}
