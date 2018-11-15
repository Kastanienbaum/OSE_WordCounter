package at.tw.ose;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TxtFileReader {
	
	private BufferedReader reader; 
	private String text = "";

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	/**
	 * Creates a TxtFileReader with a FileReader wrapping the file object.
	 * 
	 * @param File file to be written
	 * @throws IOException only propagated
	 */
	public TxtFileReader(File file) throws IOException {
		
		reader = new BufferedReader(new FileReader(file));
		
		String lineOfText = null; 
		do {
			try {
				// read in every line of the file separately 
				if ((lineOfText = reader.readLine()) != null) {
					text = text.concat(lineOfText); 
					// depending on the OS, a \n or \r\n is added 
					text = text.concat(System.getProperty("line.separator")); 
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} while (lineOfText != null);
		try {
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
}
