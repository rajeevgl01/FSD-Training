class Decimal{
	public static void main(String[] args) {
		int num = Integer.parseInt(args[0]);

		int result = 0;
		while(num > 0){
			result = num % 2;
			num = num / 2;
			System.out.println(result);
		}
	}
}