package com.ibm.training.ui;

import java.util.Scanner;

import com.ibm.training.bean.Bean;
import com.ibm.training.service.ServiceClass;

public class FundTransferUI {
	Scanner scan;
	
	public FundTransferUI(Bean b, ServiceClass serve) {
		scan = new Scanner(System.in);
		
		System.out.println("Enter Phone Number of receiver");
		long phone = scan.nextLong();
		b.setPhoneNumber(phone);
		
		if(serve.checkNumberServe(b) == true) {
			System.out.println("Enter Your Phone Number");
			b.setPhoneNumber(scan.nextLong());
			
			System.out.println("Enter amount to be withdrawn");
			long amount = scan.nextLong();
			
			b.setTransactionAmount(amount);
			
			b.setTransactionTime(serve.CurrentTime(b));
			
			if(serve.WithdrawServe(b) == 2) {
				b.setAmount(amount);
				b.setPhoneNumber(phone);
				
				if(serve.DepositServe(b) == true) {
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
		else 
			System.out.println("Number not found");
	}
}
