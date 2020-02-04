import java.util.*;

class UsernameRegister{
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		while(true){
			System.out.println("Enter the username");

			String str = new String(scan.nextLine());
			char[] ch = str.toCharArray();
			int n = ch.length;

			if(str.length() >= 12){
				if(ch[n-1] != 'b' && ch[n-2] != 'o' && ch[n-3] != 'j' && ch[n-4] != '_'){
					System.out.println("Username must contain 8 characters along with '_job'");
				}
			
				else{
					System.out.println("New Username is " + str);
					break;
				}
		}
							System.out.println("Enter Username again!!!");
	}
	}
}