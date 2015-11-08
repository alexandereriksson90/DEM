package fileimport;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Läser av en sträng med DEM Data och tar vara på alla viktiga variabler
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
	 * Tar emot en bufferedReader som innehåller DEM data
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
	 * Hämtar Höjd värdena från datat och placerar de i en matris med storleken av Nrows och Ncols
	 * @return En matris med storleken nrows*ncols med varje höjd värde
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
	 * Kollar om värdet är det minsta!
	 * @param min värdet som ska kollas
	 */
	private void isMin(double min) {
		if (min < minh && min != nodata_value) {
			minh = min;
		}
	}
	/**
	 * Kollar om värdet är det största!
	 * @param max värdet som ska kollas
	 */
	private void isMax(double max) {
		if (max > maxh && max != nodata_value) {
			maxh = max;
		}
	}
	/**
	 * Hämtar en 2x2 matris med höjd värdena
	 * @return höjd värdena
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
	 * Hämtar det minsta höjd värdet
	 * @return det minsta höjd värdet
	 */
	public double getMinHeight() {
		return minh;
	}
	/**
	 * Hämtar det största höjd värdet
	 * @return	det största höjd värdet
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
	 * retunerar koordinaten för xllcenter
	 * @return	xllcenter
	 */
	public double getXllcenter() {
		return xllcenter;
	}
	/**
	 * retunerar koordinaten för yllcenter
	 * @return	yllcenter
	 */
	public double getYllcenter() {
		return yllcenter;
	}
	/**
	 * Retunerar cellsize från arcgrid filen
	 * @return	cellsize
	 */
	public double getCellsize() {
		return cellsize;
	}
	/**
	 * Hämtar noData_value från arcgrid filen
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
