package model;

public abstract class Health {
	protected boolean isVisiblySick;
	protected boolean isInfectious;

	public abstract void changeHealth(int dayPassed, int today, Person person);

	public abstract void move(Country country, int i, int j, Person person);

	public boolean isVisiblySick() {
		return isVisiblySick;
	}
	
	public boolean isInfectious() {
		return isInfectious;
	}
}
