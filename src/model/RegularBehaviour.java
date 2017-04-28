package model;

public class RegularBehaviour extends HealthBehaviour {

	public RegularBehaviour(Person person) {
		super(person);
		this.health = Healthy.getInstance();
	}

	public void changeHealth(int today) {
		health.changeHealth(today - dayHealthChanged, today, person);
	}


}
