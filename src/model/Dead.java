package model;

public class Dead extends Health {

	private static Dead instance = new Dead();

	private Dead() {
		this.isVisiblySick = true;
		this.isInfectious = true;
	}
	
	public static Dead getInstance() {
		return instance;
	}

	public void move(Country country, int i, int j,Person person) {

	}

	public void changeHealth(int dayPassed, int today, Person person) {

	}

}
