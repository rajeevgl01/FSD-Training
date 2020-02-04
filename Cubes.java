import java.util.StringTokenizer;
import java.util.Scanner;

class Cubes{
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String st = new String(scan.nextLine());

		char []digits = st.toCharArray();

		int sum = 0;
		for (char ch : digits) {
			sum += Math.pow(Integer.parseInt(String.valueOf(ch)), 3);
		}

		System.out.println("Sum of cubes of digits of " + st + " is " + sum);

	}
}