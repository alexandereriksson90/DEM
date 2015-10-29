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
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import fileimport.ArcgridData;
import fileimport.ReadFile;

public class ApplicationWindow extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArcgridData gridData;
	
	public ApplicationWindow(){
		setSize(800, 600);
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
							} catch (IOException e) {
								
								e.printStackTrace();
							}
				           }
						
					}
					
				}
				
				);
		JMenuItem transparent = new JMenuItem("Choose image");
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
	private void createImage(String path) throws IOException{
		getContentPane().removeAll();
		ReadFile rf = new ReadFile(path);
		ArcgridData arc = new ArcgridData(rf.openFile());
		ArcgridImage image = new ArcgridImage(arc);
		gridData = arc;
		add(image);

		revalidate();
	}
	private void openTransparentImage(String path){
		getContentPane().removeAll();
		TransparentImage ti = new TransparentImage(path,gridData);
		
		add(ti);
		revalidate();
	}

	
}
