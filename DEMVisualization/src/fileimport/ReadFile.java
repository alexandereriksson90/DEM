package fileimport;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {
	private String path;
	
	
	
	public ReadFile(String file){
		path = file;
	}
	@SuppressWarnings("resource")
	public BufferedReader openFile () {
		FileReader fr;
		String test = "";
		BufferedReader textReader = null;
		try {
			fr = new FileReader(path);
			 textReader = new BufferedReader(fr);
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}

		return textReader;
	}
	public static void main(String[] args) throws IOException{
		ReadFile rf = new ReadFile("C:\\Users\\james\\Desktop\\DEM.txt");
		BufferedReader d = rf.openFile();

		System.out.println(d.readLine());
		System.out.println(d.readLine());
		System.out.println(d.readLine());
		System.out.println(d.readLine());
		System.out.println(d.readLine());
				
	}
}
