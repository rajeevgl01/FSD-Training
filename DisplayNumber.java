import java.io.*;
import java.util.Scanner;

class DisplayNumber{
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);

		System.out.println("Enter the file name with extension .txt");

		String filename = scan.nextLine();
		String line = null;
		int wordCount = 0, characterCount = 0;

		try{
			BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));

			int linenum = 0;
			while((line = bufferedReader.readLine()) != null){
				linenum++;
				if(!(line.equals(""))){
					characterCount += line.length();

					String[] wordList = line.split(" ");
					wordCount += wordList.length;
				}
			}

			System.out.println("Number of lines in file are: " + linenum);
			System.out.println("Number of words in the file are: " + wordCount);
			System.out.println("Number of Characters in the file are: " + characterCount);

			bufferedReader.close();

		}catch(IOException e){
			System.out.println(filename + " not found");
		}
	}
}