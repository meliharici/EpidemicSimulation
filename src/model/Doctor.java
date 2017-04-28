package model;

import java.util.ArrayList;

public class Doctor extends Person {
	private int maximumNumberOfPeopleToVaccinate;

	public Doctor(int A,int maximumNumberOfPeopleToVaccinate) {
		super(A);
		this.maximumNumberOfPeopleToVaccinate = maximumNumberOfPeopleToVaccinate;
	}

	public void vaccinate(Country country) {
		ArrayList<Person> people = country.getPeople();
		for (int i = 0; i < maximumNumberOfPeopleToVaccinate; i++) {

			Person randomPerson = people.get(random.nextInt(people.size()));
			if (!(randomPerson.getHealth().isInfectious)) {
				randomPerson.setHealthBehaviour(new SuperBehaviour(randomPerson));
			}
		}
	}
}
