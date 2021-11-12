package by.konopliouv.excelFileApp;

public interface FileWorker {

	public abstract void createFile();
	
	public StringBuilder readFile();
	
	public abstract void writeFile(Student student);
}
