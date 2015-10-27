package fileimport;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArcgridData {
BufferedReader reader;
int nrows;
int ncols;
double cellsize;
double xllcenter;
double yllcenter;
double nodata_value;
double minh = Double.MAX_VALUE;
double maxh = Double.MIN_VALUE;;
Double[][] height;
	public ArcgridData(BufferedReader br) throws IOException{
		this.reader = br;
		
		
		for(int i=0; i<6; i++){

			if(i==0){
				this.ncols =Integer.parseInt(getLine(reader.readLine()).get(1));
			}
			if(i==1){
				this.nrows =Integer.parseInt(getLine(reader.readLine()).get(1));
			}
			if(i==2){
				this.xllcenter =Double.parseDouble(getLine(reader.readLine()).get(1));
			}
			if(i==3){
				this.yllcenter =Double.parseDouble(getLine(reader.readLine()).get(1));
			}
			if(i==4){
				this.cellsize =Double.parseDouble(getLine(reader.readLine()).get(1));
			}
			if(i==5){
				this.nodata_value =Double.parseDouble(getLine(reader.readLine()).get(1));
			}
		}
		this.height = getHeight();
	}
	
	private Double[][] getHeight() throws IOException{
		Double[][] height = new Double[getNrows()][getNcols()];
		for (int i=0; i<getNrows(); i++){
			List<String> list = getLine(reader.readLine());
			
			for(int k =0; k<getNcols(); k++){
				
				height[i][k]= Double.parseDouble(list.get(k));
				isMin(height[i][k]);
				isMax(height[i][k]);
			}
		}
		return height;
	}
	private void isMin(double min){
		if(min<minh && min!=nodata_value){
			minh =	min;
		}
	}
	private void isMax(double max){
		if(max>maxh && max!=nodata_value){
			maxh = max;
		}
	}
	public Double[][] getHeightValues(){
		return height;
	}
	public int getNrows(){
		return nrows;
	}
	public double getMinHeight(){
		return minh;
	}
	public double getMaxHeight(){
		return maxh;
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
	private List<String> getLine(String line) {
		String st = line;
		String temp = "";
		int nr;
		List<String> listr = new ArrayList<String>();
		for (int i = 0; i < st.length(); i++) {
			nr = i;
			temp = "";

			while (st.charAt(nr) != ' ') {
				temp = temp + st.charAt(nr);

				nr++;
				if (nr == st.length()) {
					break;
				}

			}
			if (temp != "") {
				listr.add(temp);
			}

			i = nr;
		}

		return listr;
	}


}
