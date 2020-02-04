import java.util.*;

class SecondSmallest{

	static int getSecondSmallest(int[] array){
		Arrays.sort(array);
		return(array[1]);
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		System.out.println("Enter the number of the digits in array");
		int size = scan.nextInt();

		int []arr = new int[size];
		System.out.println("Enter elements");
		for (int i = 0; i < size; i++) {
			arr[i] = scan.nextInt();
		}

		System.out.println("Second smallest number in the array is " + getSecondSmallest(arr));

	}
}