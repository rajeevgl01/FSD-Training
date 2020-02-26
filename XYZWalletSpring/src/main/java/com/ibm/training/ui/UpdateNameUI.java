package com.ibm.training.ui;

import java.util.Scanner;

import com.ibm.training.bean.Bean;
import com.ibm.training.service.ServiceClass;

public class UpdateNameUI {
	Scanner scan;
	public UpdateNameUI(Bean b, ServiceClass serve) {
		scan = new Scanner(System.in);
		
		System.out.println("Enter account Number");
		b.setAccountNumber(scan.nextInt());
		
		scan.nextLine();
		
		System.out.println("Enter name");
		b.setName(scan.nextLine());
		
	
		if(serve.UpdateNameServe(b) == true)
			System.out.println("Name Updated");
		else
			System.out.println("Name not updated");
	}

}
