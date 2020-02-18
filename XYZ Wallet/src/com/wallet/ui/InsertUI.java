package com.wallet.ui;

import java.util.Random;
import java.util.Scanner;

import com.wallet.bean.Bean;
import com.wallet.service.ServiceClass;

public class InsertUI {
	Scanner scan;
	public static int generateRandomDigits() {
	    int m = (int) Math.pow(10, 9);
	    return m + new Random().nextInt(9 * m);
	}

	public InsertUI() {
		Bean b = new Bean();
		scan = new Scanner(System.in);
		
		System.out.println("Enter name of the Account Holder");
		b.setName(scan.nextLine());
		
		System.out.println("Enter Phone Number");
		b.setPhoneNumber(scan.nextLong());
		
		System.out.println("Enter the amount you want to start the account with");
		b.setBalance(scan.nextLong());
		
		b.setAccountNumber(generateRandomDigits());
		
		ServiceClass serve = new ServiceClass();
		if(serve.InsertServe(b) > 0)
			System.out.println("Account Created");
		else
			System.out.println("Account Not Created");
		
	}
}
