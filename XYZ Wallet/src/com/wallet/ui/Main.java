package com.wallet.ui;
import com.wallet.service.ServiceClass;

public class Main {	
	
	public static void main(String[] args) {
		ServiceClass sc = new ServiceClass();
		System.out.println("Welcome to XYZ Wallet");
		sc.serviceMethod();
	}
}
