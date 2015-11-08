package fileimport;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * L�ser av en str�ng med DEM Data och tar vara p� alla viktiga variabler
 * 
 * @author ndi13jed
 * @author nbt12aen
 *
 */
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
	/**
	 * Tar emot en bufferedReader som inneh�ller DEM data
	 * @param br
	 * @throws IOException
	 */
	public ArcgridData(BufferedReader br) throws IOException {
		this.reader = br;

		for (int i = 0; i < 6; i++) {

			if (i == 0) {
				this.ncols = Integer
						.parseInt(getLine(reader.readLine()).get(1));
			}
			if (i == 1) {
				this.nrows = Integer
						.parseInt(getLine(reader.readLine()).get(1));
			}
			if (i == 2) {
				this.xllcenter = Double.parseDouble(getLine(reader.readLine())
						.get(1));
			}
			if (i == 3) {
				this.yllcenter = Double.parseDouble(getLine(reader.readLine())
						.get(1));
			}
			if (i == 4) {
				this.cellsize = Double.parseDouble(getLine(reader.readLine())
						.get(1));
			}
			if (i == 5) {
				this.nodata_value = Double.parseDouble(getLine(
						reader.readLine()).get(1));
			}
		}
		this.height = getHeight();
	}
	/**
	 * H�mtar H�jd v�rdena fr�n datat och placerar de i en matris med storleken av Nrows och Ncols
	 * @return En matris med storleken nrows*ncols med varje h�jd v�rde
	 * @throws IOException
	 */
	private Double[][] getHeight() throws IOException {
		Double[][] height = new Double[getNrows()][getNcols()];
		for (int i = 0; i < getNrows(); i++) {
			List<String> list = getLine(reader.readLine());

			for (int k = 0; k < getNcols(); k++) {

				height[i][k] = Double.parseDouble(list.get(k));
				isMin(height[i][k]);
				isMax(height[i][k]);
			}
		}
		return height;
	}
	/**
	 * Kollar om v�rdet �r det minsta!
	 * @param min v�rdet som ska kollas
	 */
	private void isMin(double min) {
		if (min < minh && min != nodata_value) {
			minh = min;
		}
	}
	/**
	 * Kollar om v�rdet �r det st�rsta!
	 * @param max v�rdet som ska kollas
	 */
	private void isMax(double max) {
		if (max > maxh && max != nodata_value) {
			maxh = max;
		}
	}
	/**
	 * H�mtar en 2x2 matris med h�jd v�rdena
	 * @return h�jd v�rdena
	 */
	public Double[][] getHeightValues() {
		return height;
	}
	/**
	 * Retunerar antalet rader som finns i Arcgrid filen
	 * @return antalet rader
	 */
	public int getNrows() {
		return nrows;
	}
	/**
	 * H�mtar det minsta h�jd v�rdet
	 * @return det minsta h�jd v�rdet
	 */
	public double getMinHeight() {
		return minh;
	}
	/**
	 * H�mtar det st�rsta h�jd v�rdet
	 * @return	det st�rsta h�jd v�rdet
	 */
	public double getMaxHeight() {
		return maxh;
	}
	/** 
	 * Retunerar antalet kolumner som finns i Arcgrid filen
	 * @return	antalet kolumner
	 */
	public int getNcols() {
		return ncols;
	}
	/**
	 * retunerar koordinaten f�r xllcenter
	 * @return	xllcenter
	 */
	public double getXllcenter() {
		return xllcenter;
	}
	/**
	 * retunerar koordinaten f�r yllcenter
	 * @return	yllcenter
	 */
	public double getYllcenter() {
		return yllcenter;
	}
	/**
	 * Retunerar cellsize fr�n arcgrid filen
	 * @return	cellsize
	 */
	public double getCellsize() {
		return cellsize;
	}
	/**
	 * H�mtar noData_value fr�n arcgrid filen
	 * @return nodata_value 
	 */
	public double getNodatavalue() {
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
