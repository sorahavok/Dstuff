package sora.havok;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import sora.havok.Human.BloodType;
import sora.havok.Human.Gender;
import sora.havok.Soda.Color;

public class Java8Stuff implements Runnable {
	
	private static final Random rand = new Random();
	private static final String[] NAMES = {"Scott", "Finkel", "Halley", "Sora", "Havok", "Rena", "Ryuuguu"};
	private static final String[] SODAS = {"Coke", "Sprite", "Sunkist", "Dr. Pepper", "Pepsi", "Mr. Pib"};
	private static final long NUM_HUMS = 10;
	private static final long NUM_SODAS = 20;

	public static void main(String[] args) {
		new FizzBuzz(new ArrayList<Buzzer>(){{
			add(new Buzzer("Razz", 3));
			add(new Buzzer("Tezz", 4));
			add(new Buzzer("Fizz", 5));
			add(new Buzzer("Lozz", 6));
			add(new Buzzer("Buzz", 7));
		}}).play(30);
		
		
//		new Java8Stuff().run();
	}
	
	@Override
	public void run() {
		generateClasses();
		
		generateHumans(NUM_HUMS).stream()
		  .filter( h -> h.age() > 18 && h.age() < 50 && h.bloodType() != BloodType.Unknown && h.gender() == Gender.Female )
		  .sorted( (h1,h2) -> h1.firstName().compareTo(h2.firstName()) )
		  .forEach( System.out::println );
		
		generateSoda(NUM_SODAS).stream()
		  .filter(soda -> soda.calories() > 100)
		  .filter(Soda::diet)
		  .sorted((s1,s2) -> Integer.compare(s1.calories(), s2.calories()))
		  .forEach( System.out::println );
	}
	
	private List<Human> generateHumans(final long hums) {
		return LongStream.range(0, hums)
		    .mapToObj(x -> new Human(getRand(NAMES), getRand(NAMES), rand.nextInt(100), getRand(BloodType.values()), getRand(Gender.values())))
		    .collect(Collectors.toList());
	}
	
	private List<Soda> generateSoda(final long sodas) {
		return LongStream.range(0, sodas)
		    .mapToObj(x -> new Soda(getRand(SODAS), rand.nextInt(300), getRand(new Boolean[]{true, false}), getRand(Color.values())))
		    .collect(Collectors.toList());
	}

	private <T> T getRand(T[] array) {
		return array[rand.nextInt(array.length)];		
	}
	
	private void generateClasses() {
		final PojoGen gen = new PojoGen();	
		
		final Map<String, String> sodaFields = new LinkedHashMap<>();
		sodaFields.put("name", "String");
		sodaFields.put("calories", "int");
		sodaFields.put("diet", "boolean");
		sodaFields.put("color", "Color");
		gen.genClass("Soda", "sora.havok", sodaFields, new Pair<>("Color","Brown,Clear,Orange,RedBrown,Yellow"));

		final Map<String, String> humanFields = new LinkedHashMap<>();
		humanFields.put("firstName", "String");
		humanFields.put("lastName", "String");
		humanFields.put("age", "int");
		humanFields.put("bloodType", "BloodType");
		humanFields.put("gender", "Gender");
		gen.genClass("Human", "sora.havok", humanFields, new Pair<>("BloodType", "O,A,B,AB,Unknown"), new Pair<>("Gender","Male,Female,Other"));
	}
}
