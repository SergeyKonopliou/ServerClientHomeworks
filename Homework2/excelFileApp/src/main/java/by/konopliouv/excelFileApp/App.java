package by.konopliouv.excelFileApp;

import javax.swing.SwingUtilities;

/**
 * Main class
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				ExcelWorker worker = new ExcelWorker();
				new View(worker).startApp();
			}
		});
    }
}
