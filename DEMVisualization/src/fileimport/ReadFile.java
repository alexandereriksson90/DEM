package fileimport;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class ReadFile {
	private String path;
	
	
	
	public ReadFile(String file){
		path = file;
	}

	public BufferedReader openFile () throws IOException {
		FileReader fr;
		BufferedReader textReader = null;

			fr = new FileReader(path);
			 textReader = new BufferedReader(fr);


		return textReader;
	}
	public static void main(String[] args) throws IOException{
		ReadFile rf = new ReadFile("Files\\DEM.txt");
		BufferedReader d = rf.openFile();

		String ds="";
		for(int i =0; i<7; i++){
			d.readLine();
			if(i==6){
				ds =d.readLine();
			}
		}
		ds = ds.replaceAll("   ", " ");
		System.out.println(ds);
//		String[] st = ds.split("   ");
//		int k = st.length;
//		for (int i=0; i<k; i++){
//			System.out.println(i);
//		System.out.println(st[i]);
//		}
		
		
				
	}
}
