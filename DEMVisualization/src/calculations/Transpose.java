package calculations;

import fileimport.ArcgridData;

public class Transpose {

	ArcgridData arcgrid;
	public Transpose(ArcgridData arcgrid) {
		this.arcgrid = arcgrid;

	}

	public Double[][] getTransposedMatrix() {
		Double[][] transposedMatrix = new Double[arcgrid.getNcols()][arcgrid.getNrows()];
		for (int i = 0; i < arcgrid.getNcols(); i++) {
			for (int k = 0; k < arcgrid.getNrows(); k++) {
				transposedMatrix[i][k] = arcgrid.getHeightValues()[k][i];
			}
		}

		return transposedMatrix;
	}
	public int getRows(){
		return arcgrid.getNcols();
		
	}
	public int getColumns(){
		return arcgrid.getNrows();
	}
}
