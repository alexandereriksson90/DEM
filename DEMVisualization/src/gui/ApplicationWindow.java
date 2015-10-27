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

public class ApplicationWindow extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ApplicationWindow(){
		setSize(800, 800);
		setJMenuBar(menuBar());
		setTitle("Arcgrid raster");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	public void setVisible(){
		setVisible(true);
	}
	private JMenuBar menuBar(){
		JMenuBar menu = new JMenuBar();
		JMenu open = new JMenu("Open");

		JMenuItem menuItem = new JMenuItem("Choose file...");
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
								JOptionPane.showMessageDialog(null, e.getMessage(), "Arcgrid raster", JOptionPane.ERROR_MESSAGE);
							}
				           }
						
					}
					
				}
				
				);
		open.add(menuItem);
		menu.add(open);
		return menu;
	}
	private void createImage(String path) throws IOException, WrongFileException{
		ReadFile rf = new ReadFile(path);
		ArcgridData arc = null;
		try {
			arc = new ArcgridData(rf.openFile());
		} catch (IOException | ArrayIndexOutOfBoundsException e) {
			throw new WrongFileException("Du valde inte en arcgrid fil, Försök igen!");
		}
		ArcgridImage image = new ArcgridImage(arc);
		add(image);
		
		revalidate();
	}

	
}
