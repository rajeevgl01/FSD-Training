class LimitTable{
	public static void main(String[] args) {
		int num = Integer.parseInt(args[0]);
		int limit = Integer.parseInt(args[1]);

		for (int mul = 1; mul <= limit / num; mul++ ) {
			System.out.println(num + "*" + mul + "=" + mul * num);			
		}
	}
}