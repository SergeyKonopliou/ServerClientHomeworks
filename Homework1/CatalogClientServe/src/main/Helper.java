package main;

import java.util.ArrayList;
import java.util.List;

public class Helper {
	List<Object> keyWords = new ArrayList<>();
	
	public void createMap() {
		keyWords.add(new KeyWord("abstract","абстрактный метод, абстрактный класс"));
		keyWords.add(new KeyWord("boolean","булевый тип"));
		keyWords.add(new KeyWord("try","оператор обработки исключений"));
		keyWords.add(new KeyWord("super","объект или конструктор суперкласса"));
		keyWords.add(new KeyWord("public","модификатор доступа"));
		
	}
	
	public Object checkWord(String word) {
		for (int i = 0; i < keyWords.size(); i++) {
		    KeyWord element = (KeyWord) keyWords.get(i);
		    if(element.name.equalsIgnoreCase(word)) {
				return element;
			}   
		}
		 return null;	
	}
}
