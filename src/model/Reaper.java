package model;
import java.util.ArrayList;

public class Reaper {
	private static Reaper instance = new Reaper();
	private ArrayList<PersonLocation> personLocations;

	private Reaper() {
		this.personLocations = new ArrayList<PersonLocation>();
	}
	
	public static Reaper getInstance(){
		return instance;
	}

	public void addPersonLocations(Person person, int i, int j) {
		this.personLocations.add(new PersonLocation(person, i, j));
	}

	public void removeFromLocation(Country[][] countries) {
		for (PersonLocation currentPersonLocation : personLocations) {
			countries[currentPersonLocation.getI()][currentPersonLocation.getJ()].getPeople()
					.remove(currentPersonLocation.getPerson());
		}
		removePersonLocations();
	}

	private void removePersonLocations() {
		personLocations.clear();
	}
}
