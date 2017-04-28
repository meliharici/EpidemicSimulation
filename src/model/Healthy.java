package model;

import java.util.Random;

public class Healthy extends Health {

	private Random random = new Random();
	private static Healthy instance = new Healthy();

	private Healthy() {
		this.isVisiblySick = false;
		this.isInfectious = false;
	}

	public static Healthy getInstance() {
		return instance;
	}

	public void move(Country country, int i, int j, Person person) {
		country.addPeople(person);
		Reaper.getInstance().addPersonLocations(person, i, j);

		if (country.hasInfectiousPerson()) {
			int probability = random.nextInt(100) + 1; 
														
			if (probability < 41) {
				person.setHealth(person.getDaysToMove(), Infected.getInstance());
			}
		}
	}

	public void changeHealth(int dayPassed, int today, Person person) {

	}

}
