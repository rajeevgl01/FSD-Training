import java.io.*;
import java.util.Scanner;

class FileReadAndDisplay{
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);

		System.out.println("Enter the file name with extension .txt");

		String filename = scan.nextLine();
		String line = null;

		try{
			BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));

			int linenum = 1;
			while((line = bufferedReader.readLine()) != null){
				System.out.println(linenum + ". " + line);
				linenum++;
			}

			bufferedReader.close();

		}catch(IOException e){
			System.out.println(filename + " not found in directory");
		}
	}
}