package com.wallet.ui;

import java.util.Scanner;

import com.wallet.bean.Bean;
import com.wallet.service.ServiceClass;

public class UpdateNameUI {
	Scanner scan;
	public UpdateNameUI() {
		
		Bean b = new Bean();
		scan = new Scanner(System.in);
		
		System.out.println("Enter account Number");
		b.setAccountNumber(scan.nextInt());
		
		scan.nextLine();
		
		System.out.println("Enter name");
		b.setName(scan.nextLine());
		
		ServiceClass serve = new ServiceClass();
		if(serve.UpdateNameServe(b) > 0)
			System.out.println("Name Updated");
		else
			System.out.println("Name not updated");
	}

}
