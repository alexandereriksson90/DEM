package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

import javax.swing.JPanel;

import calculations.ColorScaler;
import calculations.Transpose;
import fileimport.ArcgridData;

public class ArcgridImage extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArcgridData arcgrid;
	Transpose matrix;
	public ArcgridImage(ArcgridData arcgrid){
		this.arcgrid = arcgrid;
		this.matrix = new Transpose(arcgrid);
		
	}
	public void paintComponent(Graphics g){
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
		
		int w = image.getWidth();
		int h = image.getHeight();
		BufferedImage after = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		AffineTransform at = new AffineTransform();
		at.scale(2.0, 2.0);
		AffineTransformOp scaleOp = 
		   new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
		after = scaleOp.filter(image, after);
		
		
		
	    g.drawImage(after, 0, 0, null);

	    
	}
	
}
