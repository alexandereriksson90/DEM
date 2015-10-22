package main;

import java.io.IOException;

import fileimport.ArcgridData;
import fileimport.ReadFile;
import gui.ApplicationWindow;
import gui.ArcgridImage;

public class Main {

	public static void main(String[] args) throws IOException {
	ReadFile rf = new ReadFile("Files\\DEM.txt");
	ArcgridData arc = new ArcgridData(rf.openFile());
	ArcgridImage image = new ArcgridImage(arc);
	ApplicationWindow window = new ApplicationWindow(image);
	window.setVisible();
	}

}
