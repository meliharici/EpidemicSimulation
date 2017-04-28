package model;

import java.util.ArrayList;

public class Country {

	private ArrayList<Person> people;
	private ArrayList<Country> neighbours;
	private int[] numberOfEach;
	private int i, j;

	public Country(int i, int j) {
		this.i = i;
		this.j = j;
	}

	public String toString() {
		return "i: " + i + " ,j: " + j;
	}

	public ArrayList<Person> getPeople() {
		return people;
	}

	public void setPeople(ArrayList<Person> people) {
		this.people = people;
	}

	public void addPeople(Person person) {
		people.add(person);
	}

	public ArrayList<Country> getNeighbours() {
		return neighbours;
	}

	public void setNeighbours(ArrayList<Country> neighbours) {
		this.neighbours = neighbours;
	}

	public int[] getNumberOfEach() {
		return numberOfEach;
	}

	public boolean hasInfectiousPerson() {
		int infectious = numberOfEach[1] + numberOfEach[2] + numberOfEach[3] + numberOfEach[4];
		return (infectious > 0);
	}

	public String giveInfo() {
		countPeople();
		String newLine = "\n";
		String info = "number of healty people: " + numberOfEach[0] + newLine + "number of infected people: "
				+ numberOfEach[1] + newLine + "number of sick people: " + numberOfEach[2] + newLine
				+ "number of immune people: " + numberOfEach[3] + newLine + "number of dead people: " + numberOfEach[4];
		return info;
	}

	public void countPeople() {
		int[] ppl = new int[5];
		for (int i = 0; i < people.size(); i++) {
			Health health = people.get(i).getHealth();
			if (health instanceof Healthy)
				ppl[0]++;
			else if (health instanceof Infected)
				ppl[1]++;
			else if (health instanceof Sick)
				ppl[2]++;
			else if (health instanceof Immune)
				ppl[3]++;
			else if (health instanceof Dead)
				ppl[4]++;
		}
		numberOfEach = ppl;
	}

}
