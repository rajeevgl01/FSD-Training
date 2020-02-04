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


class NameValidation{
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		System.out.println("Enter first name");
		String firstName = new String(scan.nextLine());
		System.out.println("Enter last name");
		String lastName = new String(scan.nextLine());

		if(firstName.length() == 0 || lastName.length() == 0 ){
			try{
				throw new UserDefinedException("Name must not be entered blank");
			}catch(UserDefinedException e){
				e.printStackTrace();
			}
		}
		else{
			System.out.println("Name Validated");
		}

	}
}