package model;

public class PersonLocation {
	private Person person;
	private int i, j;

	public PersonLocation(Person person, int i, int j) {
		this.person = person;
		this.i = i;
		this.j = j;
	}

	public Person getPerson() {
		return person;
	}

	public int getI() {
		return i;
	}

	public int getJ() {
		return j;
	}

}
