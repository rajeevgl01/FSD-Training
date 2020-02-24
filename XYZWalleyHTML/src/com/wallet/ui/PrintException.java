package com.wallet.ui;

import com.wallet.bean.Bean;

public class PrintException {

 	public static void problemConnection(Bean b) {
		System.out.println("problem while connecting " + b.getException());
	}
	
	public static void problemInserting(Bean b) {
		System.out.println("problem while inserting " + b.getException());
	}
	
	public static void problemUpdatingName(Bean b) {
		System.out.println("problem while updating name " + b.getException());
	}
	
	public static void problemUpdatingNumber(Bean b) {
		System.out.println("problem while updating number " + b.getException());
	}
	
	public static void problemGettingBalance(Bean b) {
		System.out.println("problem while printing balance " + b.getException());
	}
	
	public static void problemCheckingBalance(Bean b) {
		System.out.println("problem while checking balance " + b.getException());
	}
	
	public static void problemDepositing(Bean b) {
		System.out.println("problem while depositing funds " + b.getException());
	}
	
	public static void problemWithdrawing(Bean b) {
		System.out.println("problem while withdrawing funds " + b.getException());
	}
	
	public static void problemPrintingTransaction(Bean b) {
		System.out.println("problem while printing transactions " + b.getException());
	}
	
//	public static void problemPrintingData(Bean b) {
//		System.out.println("problem while printing data " + b.getException());
//	}
	
	public static void printNoFunds(Bean b) {
		System.out.println("not enough funds");
	}
	
	public static void problemClass(Bean b) {
		System.out.println("probelm in clas.forName " + b.getException());
	}
}
