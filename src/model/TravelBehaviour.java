package model;

import java.util.Random;

public abstract class TravelBehaviour {
	protected Random random;

	public TravelBehaviour() {
		this.random = new Random();
	}

	public abstract Country getCountry(Country[][] countryToMove, int i, int j);
}
