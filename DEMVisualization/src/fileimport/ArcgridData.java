package fileimport;

import gui.WrongFileException;

import java.io.BufferedReader;
import java.io.IOException;

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
		String[] temp;

		for (int i = 0; i < 6; i++) {

			temp = reader.readLine().split(" ");
			if (i == 0) {
				this.ncols = Integer.parseInt(temp[1]);
			}
			else if (i == 1) {
				this.nrows = Integer.parseInt(temp[1]);
			}
			else if (i == 2) {
				this.xllcenter = Double.parseDouble((temp[1]));
			}
			else if (i == 3) {
				this.yllcenter = Double.parseDouble((temp[1]));
			}
			else if (i == 4) {
				this.cellsize = Double.parseDouble((temp[2]));
			}
			else if (i == 5) {
				this.nodata_value = Double.parseDouble((temp[1]));
			}
			
		}
		this.height = getHeight();
	}

	private Double[][] getHeight() throws IOException {
		Double[][] height = new Double[getNrows()][getNcols()];
		for (int i = 0; i < getNrows(); i++) {
			String temp[] = reader.readLine().split("   ");

			for (int k = 0; k <= getNcols() - 1; k++) {

				height[i][k] = Double.parseDouble(temp[k + 1]);
				isMin(height[i][k]);
				isMax(height[i][k]);
			}
		}
		return height;
	}

	private void isMin(double min) {
		if (min < minh) {
			minh = min;
		}
	}

	private void isMax(double max) {
		if (max > maxh) {
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

}
