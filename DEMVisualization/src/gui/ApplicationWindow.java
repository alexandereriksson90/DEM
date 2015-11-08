package gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import fileimport.ArcgridData;
import fileimport.ReadFile;
/**
 *	Jframe som anv�nds f�r att rita upp bilderna
 * @author ndi13jed
 * @author nbt12aen
 */
public class ApplicationWindow extends JFrame{

	
	private static final long serialVersionUID = 1L;
	ArcgridData gridData;
	/** 
	 * Skapar ett nytt ApplicationWindow 
	 */
	public ApplicationWindow(){
		setSize(800, 600);
		setJMenuBar(menuBar());
		setTitle("Arcgrid raster");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	/**
	 * G�r f�nstret synligt
	 */
	public void setVisible(){
		setVisible(true);
	}
	/**
	 * Skapar en JMenuBar med m�jlighet att �ppna txt filer(DEM FILER) och bilder(Kartor)
	 * @return En JMenuBar som kan �ppna bilder och txt filer
	 */
	private JMenuBar menuBar(){
		JMenuBar menu = new JMenuBar();
		JMenu open = new JMenu("Open");

		JMenuItem menuItem = new JMenuItem("DEM File");
		menuItem.addActionListener(
				new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent arg0) {
						JFileChooser chooser = new JFileChooser();
						FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
						chooser.setFileFilter(filter);
						File f;
						 Component frame = null;
						chooser.setCurrentDirectory(new java.io.File("."));
						   chooser.setSelectedFile(new File(""));
				           chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				           if (chooser.showOpenDialog(frame) == JFileChooser.OPEN_DIALOG) {
				               f = chooser.getSelectedFile();
				              try {
								createImage(f.getAbsolutePath());
							} catch (IOException | WrongFileException e) {
								
								JOptionPane.showMessageDialog(null, e.getMessage(),"Arcgrid raster",JOptionPane.ERROR_MESSAGE);
							}
				           }
						
					}
					
				}
				
				);
		JMenuItem transparent = new JMenuItem("Image");
		transparent.addActionListener(
				new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent arg0) {
						JFileChooser chooser = new JFileChooser();
						FileNameExtensionFilter filter = new FileNameExtensionFilter("GIF FILES", "GIF", "gif");
						chooser.setFileFilter(filter);
						File f;
						 Component frame = null;
						chooser.setCurrentDirectory(new java.io.File("."));
						   chooser.setSelectedFile(new File(""));
				           chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				           if (chooser.showOpenDialog(frame) == JFileChooser.OPEN_DIALOG) {
				               f = chooser.getSelectedFile();
				              openTransparentImage(f.getAbsolutePath());
				           }
						
						
					}
					
				}
				
				);
	
		open.add(menuItem);
		open.add(transparent);
		menu.add(open);
		return menu;
	}
	/**
	 * L�gger till en DEM bild i f�nstret om txt filen som valdes �r av DEM formatet
	 * @param path Filens s�kv�g 
	 * @throws IOException
	 * @throws WrongFileException
	 */
	private void createImage(String path) throws IOException, WrongFileException{
		getContentPane().removeAll();
		ReadFile rf = new ReadFile(path);
		ArcgridData arc = null;
		try{
			arc = new ArcgridData(rf.getBufferedReader());
		}
		catch(IOException | IndexOutOfBoundsException | NumberFormatException e){
			throw new WrongFileException("Du valde inte n�gon arcgrid fil, f�rs�k igen!");
		}
		ArcgridImage image = new ArcgridImage(arc);
		gridData = arc;
		add(image);

		revalidate();
	}
	/**
	 * L�gger till en bild med en gif-bild och Dem bilden d�r gif-bilden blir genomskinlig s� man kan se kartan �ver dem bilden
	 * @param path s�kv�gen till bilden
	 */
	private void openTransparentImage(String path){
		getContentPane().removeAll();
		TransparentImage ti = new TransparentImage(path,gridData);
		
		add(ti);
		revalidate();
	}

	
}
