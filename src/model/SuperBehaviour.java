package model;

public class SuperBehaviour extends HealthBehaviour {

	public SuperBehaviour(Person person) {
		super(person);
		this.health = Immune.getInstance();
	}

	public void changeHealth(int today) {

	}

}
