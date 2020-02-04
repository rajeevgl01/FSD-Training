import java.util.*;

class PositiveString{
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String str = new String(scan.nextLine());
		
		char []newstr = str.toCharArray();
		int []ascii = new int[newstr.length];

		int i = 0;
		for (char ch : newstr) {
			ascii[i] = (byte)ch;
			i++;
		}

		boolean flag = false;

		for (int j = 0; j < ascii.length - 1; j++) {
			if(ascii[j] < ascii[j + 1])
				flag = true;
			else{
				flag = false;
				break;
			}
		}

		if(flag == true)
			System.out.println("Positive String");
		else
			System.out.println("Negative String");
		

	}
}