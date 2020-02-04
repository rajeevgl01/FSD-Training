import java.util.StringTokenizer;
import java.util.Scanner;

class StringToken{
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		StringTokenizer st = new StringTokenizer(scan.nextLine());

		int sum = 0;
		String token;

		while(st.hasMoreTokens()){
			token = st.nextToken();
			System.out.println(token);
			sum += Integer.parseInt(token);
		}

		System.out.println("Sum of Numbers is " + sum);
	}
}