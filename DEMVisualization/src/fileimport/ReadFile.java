package fileimport;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/**
 * Läser av en textfil
 * @author ndi13jed
 *	@author nbt12aen
 */
public class ReadFile {
	private String path;

	public ReadFile(String file) {
		path = file;
	}
	/**
	 * Hämtar ett BufferedReader objekt som innehåller en textfil
	 * @return	BufferedReader objekt
	 * @throws IOException
	 */
	public BufferedReader getBufferedReader() throws IOException {
		FileReader fr;
		BufferedReader textReader = null;

		fr = new FileReader(path);
		textReader = new BufferedReader(fr);

		return textReader;
	}

}
