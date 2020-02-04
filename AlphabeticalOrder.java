import java.util.*;

class AlphabeticalOrder{
	static String[] check(String[] str){
		for(int i = 0; i < str.length; i++){
			for(int j = (i + 1) ; j < str.length; j++){
				int k = str[i].compareTo(str[j]);
				if(k > 0){
					String temp = str[i];
					str[i] = str[j];
					str[j] = temp;
				}
			} 
		}
		return str;
	}

	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);

		System.out.println("Enter the no of words");
		int size = scan.nextInt();

		String[] str = new String[(size + 1)];
		System.out.println("Enter the words by pressing enter after each word ");

		for(int i = 0 ; i < (size + 1) ; i++){
			str[i]= scan.nextLine();
		}	

		String[] s = check(str);
		String[] d = new String[(size + 1)];

		for(int i = 0; i < (size + 1) ; i++){
			d[i] = s[i];
		}

		for(int i = 0; i < d.length; i++){
			int n = d[i].length()/2;
			d[i] = d[i].substring(0, n).toUpperCase();
		}

		for(int i = 0 ; i < (size + 1) ; i++){	
			int n = s[i].length()/2;
			s[i] = s[i].substring(n, s[i].length());
			s[i] = d[i] + s[i];
			System.out.println(s[i]);
		}	
	}
}