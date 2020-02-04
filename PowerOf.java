import java.util.*;

class PowerOf{
	static boolean checkNumber(int num){
		double x = Math.log10(num)/Math.log10(2);
		if(x - (int)x == 0){
			return true;
		}
		else
			return false;
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the number to be checked");

		int num = scan.nextInt();
		boolean bool = checkNumber(num);
		if(bool){
			System.out.println( num + " is a power of 2");
		}
		else
			System.out.println( num +" is not a power of 2");
	}
}