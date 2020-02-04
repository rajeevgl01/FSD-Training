import java.util.*;

class FibonacciRecursive{
	static int fibonacci(int n){
		if(n <= 1)
			return n;
		return fibonacci(n-1) + fibonacci(n - 2);
	}

	static int fibonaccinormal(int n){
		int fib = 0, first = 1, second = 1;
		for (int i = 2; i < n ; i++) {
			fib = first + second;
			first = second;
			second = fib;
		}

		return fib;
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		int num = scan.nextInt();

		System.out.println(fibonacci(num));
		System.out.println(fibonaccinormal(num));
	}
}