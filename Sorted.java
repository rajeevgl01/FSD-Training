import java.util.*;

class Sorted{
	static int reverseNumber(int num){
		StringBuilder str = new StringBuilder(Integer.toString(num));
		str.reverse();
		return(Integer.parseInt(str.toString()));
	}

	 static void getSorted(int[] array ){
	 	Arrays.sort(array);
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		System.out.println("Enter number of elements in the array");
		int size = scan.nextInt();


		int []arr = new int[size];

		System.out.println("Enter the elements in the array to be reversed");

		for (int i = 0; i < size; i++) {
			arr[i] = scan.nextInt();
		}

		for (int j = 0; j < size; j++){
			arr[j] = reverseNumber(arr[j]);
		}

		getSorted(arr);

		System.out.println("New sorted array is:");
		for (int k : arr) {
			System.out.println(k);
		}



	}
}