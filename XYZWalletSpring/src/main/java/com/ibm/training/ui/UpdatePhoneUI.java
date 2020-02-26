package com.ibm.training.ui;

import java.util.Scanner;

import com.ibm.training.bean.Bean;
import com.ibm.training.service.ServiceClass;

public class UpdatePhoneUI {
	Scanner scan;
	
	public UpdatePhoneUI(Bean b, ServiceClass serve) {
		scan = new Scanner(System.in);
		System.out.println("Enter account Number");
		b.setAccountNumber(scan.nextInt());
		
		scan.nextLine();
		
		System.out.println("Enter new phone number");
		b.setPhoneNumber(scan.nextLong());
		
		
		if(serve.UpdatePhoneServe(b) == true)
			System.out.println("Phone Number Updated");
		else
			System.out.println("Phone number not updated");
		
	}

}
