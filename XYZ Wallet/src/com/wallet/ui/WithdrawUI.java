package com.wallet.ui;

import java.util.Scanner;

import com.wallet.bean.Bean;
import com.wallet.service.ServiceClass;

public class WithdrawUI {
	Scanner scan;
	public WithdrawUI() {

		Bean b = new Bean();
		scan = new Scanner(System.in);
		ServiceClass serve = new ServiceClass();
		
		System.out.println("Enter Phone Number");
		b.setPhoneNumber(scan.nextLong());
		
		System.out.println("Enter amount to be withdrawn");
		b.setAmount(scan.nextLong());
		
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
