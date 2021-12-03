package Task2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class StartApp {

	public static void main(String[] args) {

		Book firstBook = new Book("Летняя ночь", 123);
		Book secondBook = new Book("Зимний день", 321);
		Book thirdBook = new Book("Поэт", 34);
		Book fourBook = new Book("Balance", 89);
		Book fiveBook = new Book("Железные цветы Семипалатинска", 1);

		Book[] books = { firstBook, secondBook, thirdBook, fourBook, fiveBook };

		Author firstAuthor = new Author("Леримантин", 45, new ArrayList<Book>(Arrays.asList(books[1], books[2])));
		Author secondAuthor = new Author("Мандаринов", 32, new ArrayList<Book>(Arrays.asList(books[1], books[4])));
		Author thirdAuthor = new Author("Лепшов", 67, new ArrayList<Book>(Arrays.asList(books[0], books[2], books[3])));

		Author[] authors = { firstAuthor, secondAuthor, thirdAuthor };


		/**
		 *  Создайте поток (stream) массива books
		 */
		Stream<Book> stream = Arrays.stream(books);
		
		/**
		 *  I. Проверьте,есть ли в массиве одна или более книг с количеством страниц
		 *  более 200
		 */
		 
		boolean bigBooks = stream.anyMatch((s) -> s.getNumberOfPages() > 200);
		System.out.println(
				"\nПроверьте,есть ли в массиве одна или более книг с количеством страниц более 200: " + bigBooks);
		

		/**
		 *  II.Найдите книги с максимальным и минимальным количеством страниц
		 */
		stream = Arrays.stream(books);
		//Класс Optional — при написании кода разработчик часто не может знать — будет ли существовать нужный объект на момент исполнения программы или нет,
		//и в таких случаях приходится делать проверки на null. Если такими проверками пренебречь, то рано или поздно (обычно рано) Ваша программа рухнет с NullPointerException.
		Optional<Book> maxPage = stream.
                collect(Collectors.maxBy(Comparator.comparing(Book::getNumberOfPages)));
        System.out.println("\nMax pages: " + maxPage.get().getTitle() + " " + maxPage.get().getNumberOfPages());
		
        stream = Arrays.stream(books);
		Optional<Book> minPage = stream.
                collect(Collectors.minBy(Comparator.comparing(Book::getNumberOfPages)));
        System.out.println("\nMin pages: " + minPage.get().getTitle() + " " + minPage.get().getNumberOfPages());
        
        
		/**
		 *  III. Найдите книги, у которых только один автор
		 */
        stream = Arrays.stream(books);
        System.out.println("\nКниги с одним автором: ");
        stream.filter(s -> s.getAuthors().size() == 1).forEach(System.out::println);
        
		
		/**
		 *  IV. Отсортируйте книги по количеству страниц
		 */
        stream = Arrays.stream(books);
        System.out.println("\nСортировка по количеству страниц: ");
        Comparator<Book> comp = Comparator.comparing(countPage -> countPage.getNumberOfPages());
        stream.sorted(comp).forEach(System.out::println);
        
        
		
		/**
		 *  V. Получите список (list) всех названий (title) книг
		 */
        stream = Arrays.stream(books);
        List<String> bookName = stream.map(title -> title.getTitle()).collect(Collectors.toList());
        

		/**
		 *  VI. Выведите полученный список с названиями с помощью forEach
		 */
        System.out.println("\nСписок названий всех книг:");
        bookName.stream().forEach(System.out::println);
		
        
		/**
		 *  VII. Получите список повторяющихся авторов книг
		 */
        stream = Arrays.stream(books);
        //получаем список списков авторов для каждой книги
        List<List<String>> bookAuthor = stream.map(name -> name.getAuthors().stream().map(n -> n.getName()).collect(Collectors.toList()))
                .collect(Collectors.toList());
//        bookAuthor.stream().forEach(System.out::println);
        
        //теперь получаем один список имен авторов для каждой книги
        List<String> names = bookAuthor.stream().flatMap(x -> x.stream())
                .collect(Collectors.toList());
//        names.stream().forEach(System.out::println);
        
      //теперь получаем список повторяющихся имен авторов
        List<String> duplicates = names.stream()
        	    //группируем в map (пользователь -> количество вхождений)
        	    .collect(Collectors.groupingBy(Function.identity()))
        	    //проходим по группам
        	    .entrySet()
        	    .stream()
        	    //отбираем пользователей, встречающихся более одного раза
        	    .filter(e -> e.getValue().size() > 1)
        	    //вытаскиваем ключи
        	    .map(Map.Entry::getKey)
        	    //собираем в список
        	    .collect(Collectors.toList());
        System.out.println("\nСписок повторяющихся авторов книг:" );
        duplicates.stream().forEach(System.out::println);
		

	}
}
