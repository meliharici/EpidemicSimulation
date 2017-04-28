package model;

public class Infected extends Health {

	private static Infected instance = new Infected();

	private Infected() {
		this.isVisiblySick = false;
		this.isInfectious = true;
	}

	public static Health getInstance() {
		return instance;
	}

	public void changeHealth(int dayPassed, int today, Person person) {
		if (dayPassed == 6) {
			person.setHealth(today, Sick.getInstance());
		}
	}

	public void move(Country country, int i, int j, Person person) {
		country.addPeople(person);
		Reaper.getInstance().addPersonLocations(person, i, j);
	}

}
