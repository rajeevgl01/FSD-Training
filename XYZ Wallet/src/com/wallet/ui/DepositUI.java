package com.wallet.ui;

import java.util.Scanner;

import com.wallet.bean.Bean;
import com.wallet.service.ServiceClass;

public class DepositUI {
	Scanner scan;
	public DepositUI() {

		Bean b = new Bean();
		scan = new Scanner(System.in);
		ServiceClass serve = new ServiceClass();
		
		System.out.println("Enter Phone Number");
		b.setPhoneNumber(scan.nextLong());
		
		System.out.println("Enter amount to be deposited");
		b.setAmount(scan.nextLong());
		
		if(serve.DepositServe(b) == 2)
			System.out.println("Balance updated");
		else
			System.out.println("Balance not updated");
	
	}

}
