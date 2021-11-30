package Task1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

public class StartApp {

	public static void main(String[] args) {
		PersonFactory<Person> factory = Person::new;
		Person[] persons = { factory.create("Иван", 27), factory.create("Петр", 24), factory.create("Артем", 31),
				factory.create("Петр", 21), factory.create("Юлий", 23) };

		// добавление условия сортировки по имени
		Comparator<Person> comparatorName = Comparator.comparing(personName -> personName.getName());
		// добавление условия сортировки по возрасту
		Comparator<Person> comparatorAge = Comparator.comparing(Person::getAge);

		Stream<Person> stream = Arrays.stream(persons);

		System.out.println("Сортировка по имени: ");
		stream.sorted(comparatorName)//изначальный массив при этом останется в прежнем состоянии(не отсортированным)
		.forEach(System.out::println);
		
		stream = Arrays.stream(persons);
		System.out.println("Сортировка по возрасту: ");
		stream.sorted(comparatorAge)//изначальный массив при этом останется в прежнем состоянии(не отсортированным)
		.forEach(System.out::println);
		
		stream = Arrays.stream(persons);
		System.out.println("Изначальный массив: ");
		stream.forEach(System.out::println);

		//Вариант записи 2
		System.out.println("------------------------------------------------------------");
		Arrays.stream(persons).sorted(Comparator.comparing(Person::getName).thenComparing(Person::getAge))
		.forEach(person -> System.out.println(person.getName() + " " + person.getAge()));
		
	}

}
