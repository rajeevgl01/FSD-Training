class RuntimeAdd{
	public static void main(String[] args) {
		int num1 = Integer.parseInt(args[0]);
		int num2 = Integer.parseInt(args[1]);
		int num3 = Integer.parseInt(args[2]);

		int flag1 = 0;
		int sum = num1 + num2 + num3;

		if(num1 >= 40)
			flag1++;
		if(num2 >= 40)
			flag1++;
		if(num3 >= 40)
			flag1++;
		if(sum >= 125)
			flag1++;

		if(flag1 == 4)
			System.out.println("PASSING");
		if (flag1 < 4) {
			System.out.println("FAILING");
		}
	}
}