class Fibonacci{
	public static void main(String[] args) {
		int num1 = 0;
		int num2 = 1;
		int x = 0;
		while(x <= 89)
		{
			System.out.println(num1);
			x = num1 + num2;
			num1 = num2;
			num2 = x;
		}
		System.out.println(num1);
	}
}