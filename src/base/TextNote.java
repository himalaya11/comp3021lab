package base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.BufferedWriter;
import java.io.FileWriter;


public class TextNote extends Note{
	private String content;

	public TextNote(String title){
		super(title);
	}

	public TextNote(String title, String content){
		super(title);
		this.content = content;
	}

	public TextNote(File f) {
		super(f.getName());
		this.content = getTextFromFile(f.getAbsolutePath());
	}

	private String getTextFromFile(String absolutePath) {
		String result = "";
		try
        {
            result = new String (Files.readAllBytes(Paths.get(absolutePath)));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
		return result;
	}

	public void exportTextToFile(String pathFolder) {
        if(pathFolder == ""){
        	pathFolder = ".";
        }
		File file = new File(pathFolder + File.separator + this.getTitle().replaceAll(" ", "_") + ".txt");
		try {
			  FileWriter myWriter = new FileWriter(file);
			  myWriter.write(this.content);
			  myWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}



	public String getContent() {
		return content;
	}

}