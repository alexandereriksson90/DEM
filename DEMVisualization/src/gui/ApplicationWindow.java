package gui;

import javax.swing.JFrame;

public class ApplicationWindow extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ApplicationWindow(ArcgridImage image){
		add(image);
		pack();
		
	}
	public void setVisible(){
		setVisible(true);
	}
	
}
