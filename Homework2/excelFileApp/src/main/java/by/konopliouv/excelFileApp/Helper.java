package by.konopliouv.excelFileApp;

import java.util.ArrayList;
import java.util.List;

public class Helper {

	 public static List<Student> listStudents() {
	        List<Student> list = new ArrayList<Student>();
	        
	        Student e1 = new Student("Виктор", "Белый", 19, "АСОИ-213", 23432);

	        list.add(e1);
	        return list;
	    }
}
