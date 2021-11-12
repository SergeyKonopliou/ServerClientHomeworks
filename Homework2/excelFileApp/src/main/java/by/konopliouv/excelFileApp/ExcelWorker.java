package by.konopliouv.excelFileApp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Formatter;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

public class ExcelWorker implements FileWorker {

	private static final String FILE_NAME = "students.xls";
	private File file;

	public ExcelWorker() {
		file = new File(FILE_NAME);
		if (!file.exists()) {
			createFile();
		}

	}

	@Override
	public void createFile() {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Students sheet");

		// задание стиля для текста,добавляемого в ячейки
		HSSFCellStyle style = workbook.createCellStyle();
		style.setFont(createCellFont(workbook));

		Cell cell;
		Row row = sheet.createRow(0);

		cell = row.createCell(0, CellType.STRING);
		cell.setCellValue("№");
		cell.setCellStyle(style);

		cell = row.createCell(1, CellType.STRING);
		cell.setCellValue("Name");
		cell.setCellStyle(style);

		cell = row.createCell(2, CellType.STRING);
		cell.setCellValue("Second name");
		cell.setCellStyle(style);

		cell = row.createCell(3, CellType.NUMERIC);
		cell.setCellValue("Age");
		cell.setCellStyle(style);

		cell = row.createCell(4, CellType.STRING);
		cell.setCellValue("Group");
		cell.setCellStyle(style);

		cell = row.createCell(5, CellType.NUMERIC);
		cell.setCellValue("ID");
		cell.setCellStyle(style);

		// создание файла
		try {
			file.createNewFile();
		} catch (IOException e1) {
			System.out.println("Возникли проблемы при создании файла");
		}

		try {
			workbook.write(new FileOutputStream(file));
		} catch (FileNotFoundException e) {
			System.out.println("Файл не нейден");
		} catch (IOException e) {
			System.out.println("Ошибка записи файла");
		}
		try {
			workbook.close();
		} catch (IOException e) {
			System.out.println("Проблемы с закрытием потока записи");
		}

	}

	@Override
	public StringBuilder readFile() {

		StringBuilder builder = new StringBuilder();
		FileInputStream inputStream;
		try {
			inputStream = new FileInputStream(new File(FILE_NAME));
			HSSFWorkbook workbook;
			try {
				workbook = new HSSFWorkbook(inputStream);
				HSSFSheet sheet = workbook.getSheetAt(0);

				if (sheet.getLastRowNum() == 0) {
					builder.append("Файл пуст");
					System.out.println("Файл пуст");
				} else {
					// итератор строк
					Iterator<Row> rowIterator = sheet.iterator();
					
					while (rowIterator.hasNext()) {
						Row row = rowIterator.next();
						// итератор ячеек строки
						Iterator<Cell> cellIterator = row.cellIterator();

						while (cellIterator.hasNext()) {
							Cell cell = cellIterator.next();
							CellType cellType = cell.getCellTypeEnum();
					 
							
							switch (cellType) {
							case _NONE:
								System.out.printf("%-20s"," ");
								Formatter f = new Formatter();
								f.format("%-30s"," ");
								builder.append(f);
								break;
							case NUMERIC:
								System.out.printf("%-20d",(int)cell.getNumericCellValue());
								Formatter f2 = new Formatter();
								f2.format("%-30d",(int)cell.getNumericCellValue());
								builder.append(f2);
								break;
							case STRING:
								System.out.printf("%-20s",cell.getStringCellValue());
								Formatter f3 = new Formatter();
								f3.format("%-30s",cell.getStringCellValue());
								builder.append(f3);
								break;
							default:
								break;
							}
						}
						System.out.println();
						builder.append("\n");
					}
				}
			} catch (IOException e) {
				System.out.println("Проблемы с чтением файла");
			}

		} catch (FileNotFoundException e1) {
			System.out.println("Файл не найден");
		}
		
		return builder;

	}

	@Override
	public void writeFile(Student student) {

		FileInputStream inputStream;
		try {
			inputStream = new FileInputStream(new File(FILE_NAME));
			HSSFWorkbook workbook;
			try {
				workbook = new HSSFWorkbook(inputStream);
				HSSFSheet sheet = workbook.getSheetAt(0);

				// получаем количество строк в документе
				int rowNum = sheet.getLastRowNum();

				Row row = sheet.createRow(++rowNum);
				Cell cell;

				cell = row.createCell(0, CellType.STRING);
				cell.setCellValue(rowNum);

				cell = row.createCell(1, CellType.STRING);
				cell.setCellValue(student.getFirstName());
				//автоподстройка длинны ячейки
				sheet.autoSizeColumn(1);

				cell = row.createCell(2, CellType.STRING);
				cell.setCellValue(student.getSecondName());
				sheet.autoSizeColumn(2);

				cell = row.createCell(3, CellType.NUMERIC);
				cell.setCellValue(student.getAge());

				cell = row.createCell(4, CellType.STRING);
				cell.setCellValue(student.getGroupName());
				sheet.autoSizeColumn(4);

				cell = row.createCell(5, CellType.NUMERIC);
				cell.setCellValue(student.getStudentID());
				sheet.autoSizeColumn(5);

				FileOutputStream outFile = new FileOutputStream(file);
				workbook.write(outFile);
				
				

			} catch (IOException e) {
				System.out.println("Проблемы с чтением файла");
			}

		} catch (FileNotFoundException e) {
			System.out.println("Файл не найден");
		}

	}

	/*
	 * method create style for text
	 */
	private HSSFFont createCellFont(HSSFWorkbook book) {
		HSSFFont font = book.createFont();
		font.setFontHeightInPoints((short) 12);
		font.setFontName("Times New Roman");

		return (font);
	}

}
