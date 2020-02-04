import java.util.*;

class CheckNumber{
	static boolean checkNumber(int[] array){
		boolean flag = false;

		for (int j = 0; j < array.length - 1; j++) {
			if(array[j] <= array[j + 1])
				flag = true;
			else{
				flag = false;
				break;
			}
		}
		return flag;
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the number to be checked");
		String str = new String(scan.nextLine());
		
		char []newstr = str.toCharArray();
		int []ascii = new int[newstr.length];

		int i = 0;
		for (char ch : newstr) {
			ascii[i] = (byte)ch;
			i++;
		}
		
		boolean bool = checkNumber(ascii);
		if(bool == true)
			System.out.println("Increasing Number");
		else
			System.out.println("Not Increasing Number");

	}
	}
