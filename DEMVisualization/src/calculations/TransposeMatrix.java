package calculations;

import fileimport.ArcgridData;
/**
 * Transponerar en ArcgridData fils matris
 * @author ndi13jed
 * @author nbt12aen
 *
 */
public class TransposeMatrix {

	ArcgridData arcgrid;

	public TransposeMatrix(ArcgridData arcgrid) {
		this.arcgrid = arcgrid;

	}
/**
 * Transponerar en 2x2 matris från ArcgridData filen
 * @return Den transponerade matrisen
 */
	public Double[][] getTransposedMatrix() {
		Double[][] transposedMatrix = new Double[arcgrid.getNcols()][arcgrid.getNrows()];
		for (int i = 0; i < arcgrid.getNcols(); i++) {
			for (int k = 0; k < arcgrid.getNrows(); k++) {
				transposedMatrix[i][k] = arcgrid.getHeightValues()[k][i];
			}
		}

		return transposedMatrix;
	}
	/**
	 * Anger antalet rader för den transponerade matrisen
	 * @return antal rader
	 */
	public int getRows(){
		return arcgrid.getNcols();
		
	}
	/**
	 * Anger antalet kolumner för den transponerade matrisen
	 * @return antal kolumner
	 */
	public int getColumns(){
		return arcgrid.getNrows();
	}
}
