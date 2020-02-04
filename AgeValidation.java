import java.util.*;

class UserDefinedException extends Exception{
	
		String str1;
		UserDefinedException(String str2){
			str1 = str2;
		}
		public String toString(){
			return str1;
		}
		
	}


class AgeValidation{
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		System.out.println("Enter age");
		int age = scan.nextInt();

		if(age < 15){
			try{
				throw new UserDefinedException("Age must be above 15...");
			}catch(UserDefinedException e){
				e.printStackTrace();
			}
		}
		else{
			System.out.println("Age Validated");
		}

	}
}