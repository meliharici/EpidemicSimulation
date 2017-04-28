package model;

import java.util.ArrayList;
import java.util.Random;

public class SimulationBase {
	private Random random = new Random();
	private int N;
	private int P;
	private int X;
	private int V;
	private int S;
	private int A;
	private int D;
	private Country[][] countries;
	private int presentDay;

	public SimulationBase(int N, int P, int X, int V, int S, int A, int D) {
		this.N = N;
		this.P = P;
		this.X = X;
		this.V = V;
		this.S = S;
		this.A = A;
		this.D = D;
		this.countries = new Country[N][N];
		this.presentDay = 0;
		run();
	}

	private void run() {
		fillWithCountries();
		fillWithPeople();
		addDoctors();
		addSuper();
		makeInfected();
		giveNeighbours();
		allCountEach();
	}

	private void allCountEach() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				countries[i][j].countPeople();
			}
		}
	}

	private void giveNeighbours() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				countries[i][j].setNeighbours(getNeighbours(i, j));
			}
		}
	}

	private void fillWithCountries() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				countries[i][j] = new Country(i, j);
			}
		}
	}

	private void fillWithPeople() {
		ArrayList<Integer> numOfPeople = assignRandomPeople(P, (N * N));
		int current = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				ArrayList<Person> p = new ArrayList<Person>();
				for (int k = 0; k < numOfPeople.get(current); k++) {
					p.add(new RegularPerson(A));
				}
				((Country) countries[i][j]).setPeople(p);
				current++;
			}
		}
	}

	private void addDoctors() {
		int countDocs = 0;
		int numberOfDoctors = (int) Math.round(P * (D / 100.0));
		while (countDocs != numberOfDoctors) {
			Country country = countries[random.nextInt(countries.length)][random.nextInt(countries[0].length)];
			ArrayList<Person> people = country.getPeople();
			int randomIndex = 0;
			try {
				randomIndex = random.nextInt(people.size());
			} catch (Exception e) {

			}
			if (randomIndex > 0 && !(people.get(randomIndex) instanceof Doctor)) {
				people.set(randomIndex, new Doctor(A, V));
				countDocs++;
			}
		}
	}

	private void addSuper() {
		int count = 0;
		int numberOfSuperPeople = (int) Math.round(P * (S / 100.0));
		while (count != numberOfSuperPeople) {
			Country country = countries[random.nextInt(countries.length)][random.nextInt(countries[0].length)];
			ArrayList<Person> people = country.getPeople();
			int randomIndex = 0;
			try {
				randomIndex = random.nextInt(people.size());
			} catch (Exception e) {

			}
			if (randomIndex > 0) {
				Person person = people.get(randomIndex);
				if (!(person.getBehaviour() instanceof SuperBehaviour)) {
					person.setHealthBehaviour(new SuperBehaviour(person));
					count++;
				}
			}
		}
	}

	private void makeInfected() {
		int countInf = 0;
		int numberOfInfectedPeople = (int) Math.round(P * (X / 100.0));
		while (countInf != numberOfInfectedPeople) {
			Country country = countries[random.nextInt(countries.length)][random.nextInt(countries[0].length)];
			ArrayList<Person> people = country.getPeople();
			int randomIndex = 0;
			try {
				randomIndex = random.nextInt(people.size());
			} catch (Exception e) {

			}
			if (randomIndex > 0) {
				Person person = people.get(randomIndex);
				if (!(person.getHealth().isInfectious())) {
					person.setHealth(0, Infected.getInstance());
					countInf++;
				}
			}
		}
	}

	public void next() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {

				vaccinate(i, j);
				ArrayList<Person> population = countries[i][j].getPeople();
				changeHealth(population);
				move(presentDay, i, j, population);
				Reaper.getInstance().removeFromLocation(countries);
			}
		}
		presentDay++;
		allCountEach();
	}

	private void move(int y, int i, int j, ArrayList<Person> population) {
		for (int k = 0; k < population.size(); k++) {

			population.get(k).movebev(y, countries, i, j);

		}
	}

	private void changeHealth(ArrayList<Person> population) {
		for (int k = 0; k < population.size(); k++) {
			population.get(k).changeHealth(k);
		}
	}

	private void vaccinate(int i, int j) {
		for (int k = 0; k < countries[i][j].getPeople().size(); k++) {
			countries[i][j].getPeople().get(k).vaccinate(countries[i][j]);
		}
	}

	public ArrayList<Country> getNeighbours(int i, int j) {
		ArrayList<Country> neighbourCountries = new ArrayList<Country>();
		int lastIndex = N - 1;
		if (i == 0) {
			neighbourCountries.add(countries[lastIndex][j]);
		} else if (i > 0) {
			neighbourCountries.add(countries[i - 1][j]);
		}
		if (j == 0) {
			neighbourCountries.add(countries[i][lastIndex]);
		} else if (j > 0) {
			neighbourCountries.add(countries[i][j - 1]);
		}
		if (i < lastIndex) {
			neighbourCountries.add(countries[i + 1][j]);
		} else if (i == lastIndex) {
			neighbourCountries.add(countries[0][j]);
		}
		if (j < lastIndex) {
			neighbourCountries.add(countries[i][j + 1]);
		} else if (j == lastIndex) {
			neighbourCountries.add(countries[i][0]);
		}
		return neighbourCountries;
	}

	public Country[][] getCountries() {
		return countries;
	}

	public int getPresentDay() {
		return presentDay;
	}

	public int[] giveInfoTotal() {
		int[] ppl = new int[5];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < ppl.length; k++) {
					ppl[k] += countries[i][j].getNumberOfEach()[k];
				}
			}
		}
		return ppl;
	}

	private ArrayList<Integer> assignRandomPeople(int targetSum, int numberOfDraws) {
		Random r = new Random();
		ArrayList<Integer> load = new ArrayList<>();

		int sum = 0;
		for (int i = 0; i < numberOfDraws; i++) {
			int next = r.nextInt(targetSum) + 1;
			load.add(next);
			sum += next;
		}

		double scale = 1d * targetSum / sum;
		sum = 0;
		for (int i = 0; i < numberOfDraws; i++) {
			load.set(i, (int) (load.get(i) * scale));
			sum += load.get(i);
		}

		while (sum++ < targetSum) {
			int i = r.nextInt(numberOfDraws);
			load.set(i, load.get(i) + 1);
		}
		return load;
	}
}
