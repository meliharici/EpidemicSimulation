package model;

import java.util.ArrayList;

public class DirectTravel extends TravelBehaviour {

	private static final DirectTravel instance = new DirectTravel();

	private DirectTravel() {
		super();
	}

	public static DirectTravel getInstance() {
		return instance;
	}

	@SuppressWarnings("unchecked")
	public Country getCountry(Country[][] countries, int i, int j) {

		Country currentCountrie = countries[i][j];
		ArrayList<Country> neighbours = currentCountrie.getNeighbours();
		ArrayList<Country> tempNeighbours = new ArrayList<>(4);
		tempNeighbours = (ArrayList<Country>) neighbours.clone();//eclipse cries here i don't know why
		for (int k = 0; k < tempNeighbours.size(); k++) {
			ArrayList<Person> people = tempNeighbours.get(k).getPeople();
			for (Person person : people) {
				if (person.isVisiblySick()) {
					tempNeighbours.remove(k);
					break;
				}
			}
		}
		return (0 < tempNeighbours.size()) ? tempNeighbours.get(random.nextInt(tempNeighbours.size())) : null;
	}

}
