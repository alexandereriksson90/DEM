package gui;
import java.awt.Color;
import java.awt.Graphics;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;


import javax.swing.JPanel;

import calculations.ColorScaler;
import calculations.TransposeMatrix;
import fileimport.ArcgridData;
/**
 * Skapar Jpanel med en DEM bild
 * @author ndi13jed	
 * @author nbt12aen
 *
 */
public class ArcgridImage extends JPanel {


	private static final long serialVersionUID = 1L;
	ArcgridData arcgrid;
	int wa;
	int ha;
	BufferedImage rasterImage;
	/**
	 * Skapar en ny ArcgridImage
	 * @param arcgrid - arcgrid data
	 */
	public ArcgridImage(ArcgridData arcgrid){
		this.arcgrid = arcgrid;

		
	}
	/** 
	 * Ritar ut bilden i Jpanel
	 */
	protected void paintComponent(Graphics g){

		BufferedImage image = createPicture();

	    g.drawImage(image, 0, 0, null);

	    

	    
	}
	/**
	 * Skapar DEM bilden med en BufferedImage 
	 * @return	DEM Bilden
	 */
	public BufferedImage createPicture(){
		TransposeMatrix matrix = new TransposeMatrix(arcgrid);
		final BufferedImage image;
		
		 int[] iArray = { 0, 0, 0, 255 };
		 
				 
		image = (BufferedImage) createImage(matrix.getRows()*2,matrix.getColumns()*2);
		WritableRaster raster = image.getRaster();
		Double[][] transposedMatrix = matrix.getTransposedMatrix();
		ColorScaler cs = new ColorScaler(arcgrid.getMinHeight(), arcgrid.getMaxHeight());
		for (int row = 0; row < matrix.getRows(); row++) {
	        for (int col = 0 ; col <matrix.getColumns(); col++) {
	        	int rgb = Color.HSBtoRGB(0, 0, (float)cs.getColorPercentage(transposedMatrix[row][col]));
		           
	        	iArray[0] = rgb;
		        iArray[1] =rgb;
		        iArray[2] =rgb;
		           
		        raster.setPixel(row,col, iArray);
		        	
	        	
	        }
	        
	        }
		return image;
	}

	

}
