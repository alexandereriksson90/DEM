package fileimport;

import java.io.BufferedReader;
import java.io.IOException;

public class heightValues {
BufferedReader reader;
int nrows;
int ncols;
double cellsize;
double xllcenter;
double yllcenter;
double nodata_value;
String[][] height;
	public heightValues(BufferedReader br) throws IOException{
		this.reader = br;
		String[] temp;
		String[]d;
		for(int i=0; i<6; i++){

			 temp =reader.readLine().split(" ");
			if(i==0){
				this.ncols =Integer.parseInt(temp[1]);
			}
			if(i==1){
				this.nrows =Integer.parseInt(temp[1]);
			}
			if(i==2){
				this.xllcenter =Double.parseDouble((temp[1]));
			}
			if(i==3){
				this.yllcenter =Double.parseDouble((temp[1]));
			}
			if(i==4){
				this.cellsize =Double.parseDouble((temp[2]));
			}
			if(i==5){
				this.nodata_value =Double.parseDouble((temp[1]));
			}
		}
		this.height = getHeight();
	}
	
	private String[][] getHeight() throws IOException{
		String[][] height = new String[getNrows()][getNcols()];
		for (int i=0; i<getNrows(); i++){
			String temp[] = reader.readLine().split("   ");
			
			for(int k =0; k<=getNcols()-1; k++){
				
				height[i][k]= temp[k+1];
				
			}
		}
		return height;
	}
	public String[][] getHeightValues(){
		return height;
	}
	public int getNrows(){
		return nrows;
	}
	public int getNcols(){
		return ncols;
	}
	public double getXllcenter(){
		return xllcenter;
	}
	public double getYllcenter(){
		return yllcenter;
	}
	public double getCellsize(){
		return cellsize;
	}
	public double getNodatavalue(){
		return nodata_value;
	}

	public static void main(String[] args) throws IOException {
		ReadFile rf = new ReadFile("C:\\Users\\james\\Desktop\\DEM.txt");
		heightValues hv = new heightValues(rf.openFile());
	
		String[][] test = hv.getHeightValues();
		System.out.println(test[1][0]);
		System.out.println(test[1][1]);
		System.out.println(test[1][314]);
		System.out.println(test[1][315]);
	}

}
