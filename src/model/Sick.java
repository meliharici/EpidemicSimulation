package model;

import java.util.Random;

public class Sick extends Health {

	private final Random random = new Random();
	private static Sick instance = new Sick();

	private Sick() {
		this.isVisiblySick = true;
		this.isInfectious = true;
	}

	public static Sick getInstance() {
		return instance;
	}

	public void changeHealth(int dayPassed, int today, Person person) {
		if (dayPassed == 8) {
			if (random.nextInt(4) == 0) {
				person.setHealth(today, Dead.getInstance());
			}
		} else if (dayPassed == 10) {
			person.setHealth(today, Immune.getInstance());
		}
	}

	public void move(Country country, int i, int j, Person person) {
		country.addPeople(person);
		Reaper.getInstance().addPersonLocations(person, i, j);
	}

}
