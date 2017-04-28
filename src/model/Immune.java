package model;

public class Immune extends Health {

	private static Immune instance = new Immune();

	private Immune() {
		this.isVisiblySick = false;
		this.isInfectious = true;
	}

	public static Immune getInstance() {
		return instance;
	}

	public void changeHealth(int dayPassed, int today, Person person) {
		if (dayPassed == 2) {
			person.setHealth(today, Healthy.getInstance());
		}
	}

	public void move(Country country, int i, int j,Person person) {
		country.addPeople(person);
		Reaper.getInstance().addPersonLocations(person, i, j);
	}

}
