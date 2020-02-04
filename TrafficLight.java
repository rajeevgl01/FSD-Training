import java.util.*;

class TrafficLight{
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		int num = scan.nextInt();
		switch(num){
			case 1:
			System.out.println("stop");
			break;

			case 2:
			System.out.println("ready");
			break;

			case 3:
			System.out.println("Go");
			break;

			default:
			try{
				throw new IllegalStateException();
			}catch(IllegalStateException e){
				e.printStackTrace();
			}
		}


	}
}