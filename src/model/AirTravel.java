package model;

public class AirTravel extends TravelBehaviour {

	private static final AirTravel instance = new AirTravel();

	private AirTravel() {
		super();
	}

	public static AirTravel getInsatance() {
		return instance;
	}

	public Country getCountry(Country[][] countries, int i, int j) {
		int x;
		int y;
		do {
			x = random.nextInt(countries.length);
			y = random.nextInt(countries[0].length);
		} while (x == i && y == j);
		Country countryToMove = countries[x][y];
		return countryToMove;
	}

}
