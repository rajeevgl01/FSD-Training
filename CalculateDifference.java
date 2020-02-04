import java.util.*;

class CalculateDifference{
	static int calculateDifference(int n){
		int sum = 0;
		int sum1 = 0;
		for (int i = 1; i <= n; i++) {
			sum += Math.pow(i, 2);
			sum1 += i;
		}
		sum1 = (int)Math.pow(sum1, 2);

		return (sum - sum1);
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		System.out.println("Enter the number of natural numbers");
		int n = scan.nextInt();
		
		System.out.println("Differnece is " + calculateDifference(n));
		
	}
}