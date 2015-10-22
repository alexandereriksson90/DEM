package fileimport;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {
	private String path;

	public ReadFile(String file) {
		path = file;
	}

	public BufferedReader openFile() throws IOException {
		FileReader fr;
		BufferedReader textReader = null;

		fr = new FileReader(path);
		textReader = new BufferedReader(fr);

		return textReader;
	}

}
