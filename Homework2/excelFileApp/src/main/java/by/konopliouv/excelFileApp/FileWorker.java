package by.konopliouv.excelFileApp;

import java.io.File;

public interface FileWorker {

	public abstract File createFile();
	
	public abstract File readFile();
	
	public abstract File showFile();
	
	public abstract File writeFile();
}
