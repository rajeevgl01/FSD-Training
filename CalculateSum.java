import java.util.*;

class CalculateSum{
	static int calculateSum(int n){
		int sum = 0;

		for (int i = 1; i <= n; i++ ) {
			if(i % 3 == 0 || i % 5 == 0){
				sum += i;
			}
		}

		return sum;
		
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		System.out.println("Enter the number of naturals numbers");
		int n = scan.nextInt();

		System.out.println("Sum of " + n + " natural numbers " + calculateSum(n));
		
	}
}