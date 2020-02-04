import java.util.*;

class Prime{
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the number");
		int num = scan.nextInt();

		boolean[] arr = new boolean[num + 1];
		Arrays.fill(arr, true);
		arr[0] = arr[1] = false;

		for (int i = 2; i <= (int)(Math.sqrt(num)); i++ ) {
			if(arr[i] == true){
				for (int j = i * i; j < num + 1; j = j + i ) {
					arr[j] = false;
				}
			}
		}
		for(int i = 0; i < arr.length; i++){
			if(arr[i] == true)
				System.out.println(i);
		}
	}
}
