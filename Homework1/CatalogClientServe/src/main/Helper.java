package main;

import java.util.ArrayList;
import java.util.List;

public class Helper {
	List<KeyWord> keyWords = new ArrayList<>();
	
	public void createMap() {
		keyWords.add(new KeyWord("abstract","абстрактный метод, абстрактный класс"));
		keyWords.add(new KeyWord("boolean","булевый тип"));
		keyWords.add(new KeyWord("try","оператор обработки исключений"));
		keyWords.add(new KeyWord("super","объект или конструктор суперкласса"));
		keyWords.add(new KeyWord("public","модификатор доступа"));
		
	}
	
	public KeyWord checkWord(String word) {
		return keyWords.stream().filter((s) -> s.getName().equals(word)).findAny().orElse(null);
	}
}
