package fileimport;

import gui.WrongFileException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArcgridData  {
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

	public ArcgridData(BufferedReader br) throws IOException, WrongFileException {
		this.reader = br;
<<<<<<< HEAD
		String[] temp;
=======
		
		
		for(int i=0; i<6; i++){
>>>>>>> branch 'jamesDev' of https://github.com/alexandereriksson90/DEM.git

<<<<<<< HEAD
		for (int i = 0; i < 6; i++) {

			temp = reader.readLine().split(" ");
			if (i == 0) {
				this.ncols = Integer.parseInt(temp[1]);
=======
			if(i==0){
				this.ncols =Integer.parseInt(getLine(reader.readLine()).get(1));
>>>>>>> branch 'jamesDev' of https://github.com/alexandereriksson90/DEM.git
			}
<<<<<<< HEAD
			else if (i == 1) {
				this.nrows = Integer.parseInt(temp[1]);
=======
			if(i==1){
				this.nrows =Integer.parseInt(getLine(reader.readLine()).get(1));
>>>>>>> branch 'jamesDev' of https://github.com/alexandereriksson90/DEM.git
			}
<<<<<<< HEAD
			else if (i == 2) {
				this.xllcenter = Double.parseDouble((temp[1]));
=======
			if(i==2){
				this.xllcenter =Double.parseDouble(getLine(reader.readLine()).get(1));
>>>>>>> branch 'jamesDev' of https://github.com/alexandereriksson90/DEM.git
			}
<<<<<<< HEAD
			else if (i == 3) {
				this.yllcenter = Double.parseDouble((temp[1]));
=======
			if(i==3){
				this.yllcenter =Double.parseDouble(getLine(reader.readLine()).get(1));
>>>>>>> branch 'jamesDev' of https://github.com/alexandereriksson90/DEM.git
			}
<<<<<<< HEAD
			else if (i == 4) {
				this.cellsize = Double.parseDouble((temp[2]));
=======
			if(i==4){
				this.cellsize =Double.parseDouble(getLine(reader.readLine()).get(1));
>>>>>>> branch 'jamesDev' of https://github.com/alexandereriksson90/DEM.git
			}
<<<<<<< HEAD
			else if (i == 5) {
				this.nodata_value = Double.parseDouble((temp[1]));
=======
			if(i==5){
				this.nodata_value =Double.parseDouble(getLine(reader.readLine()).get(1));
>>>>>>> branch 'jamesDev' of https://github.com/alexandereriksson90/DEM.git
			}
			
		}
		this.height = getHeight();
	}

	private Double[][] getHeight() throws IOException {
		Double[][] height = new Double[getNrows()][getNcols()];
<<<<<<< HEAD
		for (int i = 0; i < getNrows(); i++) {
			String temp[] = reader.readLine().split("   ");

			for (int k = 0; k <= getNcols() - 1; k++) {

				height[i][k] = Double.parseDouble(temp[k + 1]);
=======
		for (int i=0; i<getNrows(); i++){
			List<String> list = getLine(reader.readLine());
			
			for(int k =0; k<getNcols(); k++){
				
				height[i][k]= Double.parseDouble(list.get(k));
>>>>>>> branch 'jamesDev' of https://github.com/alexandereriksson90/DEM.git
				isMin(height[i][k]);
				isMax(height[i][k]);
			}
		}
		return height;
	}
<<<<<<< HEAD

	private void isMin(double min) {
		if (min < minh) {
			minh = min;
=======
	private void isMin(double min){
		if(min<minh && min!=nodata_value){
			minh =	min;
>>>>>>> branch 'jamesDev' of https://github.com/alexandereriksson90/DEM.git
		}
	}
<<<<<<< HEAD

	private void isMax(double max) {
		if (max > maxh) {
=======
	private void isMax(double max){
		if(max>maxh && max!=nodata_value){
>>>>>>> branch 'jamesDev' of https://github.com/alexandereriksson90/DEM.git
			maxh = max;
		}
	}

	public Double[][] getHeightValues() {
		return height;
	}

	public int getNrows() {
		return nrows;
	}

	public double getMinHeight() {
		return minh;
	}

	public double getMaxHeight() {
		return maxh;
	}

	public int getNcols() {
		return ncols;
	}

	public double getXllcenter() {
		return xllcenter;
	}

	public double getYllcenter() {
		return yllcenter;
	}

	public double getCellsize() {
		return cellsize;
	}

	public double getNodatavalue() {
		return nodata_value;
	}
<<<<<<< HEAD
=======
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

>>>>>>> branch 'jamesDev' of https://github.com/alexandereriksson90/DEM.git

}
