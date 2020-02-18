package com.wallet.ui;

import java.util.Scanner;

import com.wallet.bean.Bean;
import com.wallet.service.ServiceClass;

public class UpdatePhoneUI {
	Scanner scan;
	
	public UpdatePhoneUI() {
		Bean b = new Bean();
		scan = new Scanner(System.in);
		
		System.out.println("Enter account Number");
		b.setAccountNumber(scan.nextInt());
		
		scan.nextLine();
		
		System.out.println("Enter new phone number");
		b.setPhoneNumber(scan.nextLong());
		
		ServiceClass serve = new ServiceClass();
		if(serve.UpdatePhoneServe(b) > 0)
			System.out.println("Phone Number Updated");
		else
			System.out.println("Phone number not updated");
		
	}

}
