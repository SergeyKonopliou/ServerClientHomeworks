package Task3;

public class Main {

	public static void main(String[] args) {
		ThreeFunction<Integer> example = (one,two,three) -> one + two + three;
		System.out.println(example.makeSomething(Integer.valueOf("10"),Integer.valueOf("10"),Integer.valueOf("10")));
		
		ThreeFunction<Integer> example2 = (one,two,three) -> one * two / three;
		System.out.println(example2.makeSomething(Integer.valueOf("10"),Integer.valueOf("5"),Integer.valueOf("2")));
	}

}
