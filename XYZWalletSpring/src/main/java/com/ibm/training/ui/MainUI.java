package com.ibm.training.ui;

import java.util.Scanner;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ibm.training.bean.Bean;
import com.ibm.training.service.ServiceClass;

public class MainUI {	
	static Scanner scan;
	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("appContext.xml");
		ServiceClass serve = context.getBean("sc", ServiceClass.class);
		Bean b = context.getBean("be", Bean.class);
		
		boolean flag = true;
		scan = new Scanner(System.in);
		while(flag) {
			System.out.println("Enter the choice");
			System.out.println("Press 1 : New Account; 2 : Deposit Money; 3 : Withdraw Money; 4 : Update Name; 5 : Update Phone Number; 6 : Fund Transfer; 7 : Get balance; 8 : Get transactions; 9 : Terminate");
			int choice = scan.nextInt();
			
			switch(choice) {
				case 1:
					new InsertUI(b, serve);
					break;
		
				case 2:
					new DepositUI(b, serve);
					break;
					
				case 3:
					new WithdrawUI(b, serve);
					break;
					
				case 4:
					new UpdateNameUI(b, serve);
					break;
					
				case 5:
					new UpdatePhoneUI(b, serve);
					break;
					
				case 6:
					new FundTransferUI(b, serve);
					break;
					
				case 7:
					new BalanceUI(b, serve);
					break;
					
				case 8:
					new TransactionUI(b, serve);
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
