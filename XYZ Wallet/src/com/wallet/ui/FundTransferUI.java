package com.wallet.ui;

import java.util.Scanner;

import com.wallet.bean.Bean;
import com.wallet.service.ServiceClass;

public class FundTransferUI {
	Scanner scan;
	public FundTransferUI() {

		Bean b = new Bean();
		ServiceClass serve = new ServiceClass();
		scan = new Scanner(System.in);
		
		System.out.println("Enter Your Phone Number");
		b.setPhoneNumber(scan.nextLong());
		
		System.out.println("Enter amount to be withdrawn");
		long amount = scan.nextLong();
		
		b.setAmount(amount);
		
		if(serve.WithdrawServe(b) == 2) {
			System.out.println("Enter Phone Number of receiver");
			b.setPhoneNumber(scan.nextLong());
		
			System.out.println("Enter amount to be deposited");
			b.setAmount(amount);
			
			if(serve.DepositServe(b) == 2) {
				System.out.println("Receiver Balance updated");
				System.out.println("Your Balance updated");
			}
			else
				System.out.println("Receiver Balance not updated");
		}			
		else if(serve.WithdrawServe(b) == 1)
			System.out.println("Transaction updated but issue with balance");
		else if(serve.WithdrawServe(b) == 0)
			System.out.println("issue with transactions");
		else
			System.out.println("Balance inssufficient");
		
		
		
		
	
	}

}
