package com.wallet.ui;

import java.util.Scanner;

public class MainUI {	
	static Scanner scan;
	public static void main(String[] args) {
		boolean flag = true;
		scan = new Scanner(System.in);
		while(flag) {
			System.out.println("Enter the choice");
			System.out.println("Press 1 : New Account; 2 : Deposit Money; 3 : Withdraw Money; 4 : Update Name; 5 : Update Phone Number; 6 : Fund Transfer; 7 : Get balance; 8 : Get transactions; 9 : Terminate");
			int choice = scan.nextInt();
			
			switch(choice) {
				case 1:
					new InsertUI();
					break;
		
				case 2:
					new DepositUI();
					break;
					
				case 3:
					new WithdrawUI();
					break;
					
				case 4:
					new UpdateNameUI();
					break;
					
				case 5:
					new UpdatePhoneUI();
					break;
					
				case 6:
					new FundTransferUI();
					break;
				case 7:
					new BalanceUI();
					break;
					
				case 8:
					new TransactionUI();
					break;
				
				case 9:
					flag = false;
					break;
					
				default:
					break;
			}
		
		}
		
	}

}
