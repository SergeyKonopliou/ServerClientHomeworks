package by.konopliouv.excelFileApp;

public interface FileWorker {

	public abstract void createFile();
	
	public abstract void readFile();
	
	public abstract void writeFile(Student student);
}
