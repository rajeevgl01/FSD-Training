class Factorial{
	public static void main(String[] args) {
		int num = Integer.parseInt(args[0]);
		int x = 1;
		 while(num >= 1){
		 	x = x * num;
		 	num--;
		 }
		System.out.println("factorial of number is:" + x);
	}
}