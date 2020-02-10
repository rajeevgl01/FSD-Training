import java.util.*;
import java.io.*;

class FileType{
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		System.out.println("Enter file name with appropriate extension..");

		String filename = scan.nextLine();
		String[] str = filename.split("\\.");

		File file = new File(filename);
		if(file.exists()){
			System.out.println("File exists!!");
		}

		if(file.canRead()){
			System.out.println("File is readable!!");
		}

		if(file.canWrite()){
			System.out.println("File is Writable!!");
		}

		System.out.println("Length of file: " + file.length() + " bytes");

		System.out.println("Type of file: " + str[1]);

		
		
		
	}
}