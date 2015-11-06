package gui;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import calculations.ColorScaler;
import calculations.Transpose;
import fileimport.ArcgridData;

public class TransparentImage extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ImageIcon image;
	Image im;
	ArcgridData arcgrid;
	int imageWidth;
	int imageHeight;

	public TransparentImage(String path, ArcgridData arcgrid) {
		this.image = new ImageIcon(path);
		this.imageHeight = image.getIconHeight();
		this.imageWidth = image.getIconWidth();
		this.im = image.getImage();
		this.arcgrid = arcgrid;
	}

	protected void paintComponent(Graphics g) {
		BufferedImage arcgridImage = createPicture();
		Image resizeB = resize(arcgridImage, arcgridImage.getWidth() * 2,
				arcgridImage.getHeight() * 2);
		g.drawImage(resizeB, 0, 0, null);
		Graphics2D g2d = (Graphics2D) g;
		Dimension scaled = getScaled(
				new Dimension(imageWidth, imageHeight),
				new Dimension(arcgridImage.getWidth() * 2, arcgridImage
						.getHeight() * 2));
		Image resized = resize(im, scaled.width, scaled.height);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
				0.2f));
		g2d.drawImage(resized, 0, 0, null);

	}

	private Dimension getScaled(Dimension imgSize, Dimension boundary) {
		int original_width = imgSize.width;
		int original_height = imgSize.height;
		int bound_width = boundary.width;
		int bound_height = boundary.height;
		int new_width = original_width;
		int new_height = original_height;

		if (original_width > bound_width) {
			new_width = bound_width;
			new_height = (new_width * original_height) / original_width;
		}

		if (new_height > bound_height) {
			new_height = bound_height;
			new_width = (new_height * original_width) / original_height;
		}

		return new Dimension(new_width, new_height);
	}

	private Image resize(Image originalImage, int width, int height) {
		int type = BufferedImage.TYPE_INT_ARGB;

		BufferedImage resizedImage = new BufferedImage(width, height, type);
		Graphics2D g = resizedImage.createGraphics();

		g.setComposite(AlphaComposite.Src);
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.setRenderingHint(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.drawImage(originalImage, 0, 0, width, height, this);
		g.dispose();

		return resizedImage;
	}

	public BufferedImage createPicture() {
		Transpose matrix = new Transpose(arcgrid);
		final BufferedImage image;

		int[] iArray = { 0, 0, 0, 255 };

		image = (BufferedImage) createImage(matrix.getRows(),
				matrix.getColumns());
		WritableRaster raster = image.getRaster();
		Double[][] transposedMatrix = matrix.getTransposedMatrix();
		ColorScaler cs = new ColorScaler(arcgrid.getMinHeight(),
				arcgrid.getMaxHeight());
		for (int row = 0; row < matrix.getRows(); row++) {
			for (int col = 0; col < matrix.getColumns(); col++) {
				int rgb = Color.HSBtoRGB(0, 0, (float) cs
						.getColorPercentage(transposedMatrix[row][col]));

				iArray[0] = rgb;
				iArray[1] = rgb;
				iArray[2] = rgb;

				raster.setPixel(row, col, iArray);

			}

		}
		return image;
	}

}
