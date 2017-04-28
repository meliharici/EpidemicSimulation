package model;

public abstract class HealthBehaviour {

	protected int dayHealthChanged;
	protected Health health;
	protected Person person;

	public HealthBehaviour(Person person) {
		this.dayHealthChanged = 0;
		this.person = person;
	}

	public void setHealth(int dayHealthChanged, Health health) {
		this.dayHealthChanged = dayHealthChanged;
		this.health = health;
	}

	public abstract void changeHealth(int today);

	public void move(Country country, int i, int j) {
		health.move(country, i, j, person);
	}

	public int getDayHealthChanged() {
		return dayHealthChanged;
	}

	public Health getHealth() {
		return health;
	}

	public boolean isVisiblySick() {
		return health.isVisiblySick();
	}

}
