package model;

import java.util.Random;

public abstract class Person {

	protected Random random;
	protected HealthBehaviour healthBehaviour;
	protected TravelBehaviour travelBehaviour;
	protected int daysToMove;
	protected int A;

	public Person(int A) {
		this.random = new Random();
		this.healthBehaviour = new RegularBehaviour(this);
		assignNewDaysToMove();
		this.A = A;
	}

	public void movebev(int today, Country[][] countries, int i, int j) {
		if (daysToMove == today) {
			detectTravelBehaviour();
			Country countryToMove = travelBehaviour.getCountry(countries, i, j);
			if (countryToMove != null) {
				healthBehaviour.move(countryToMove, i, j);
			}
			this.daysToMove = today + assignNewDaysToMove();
		}
	}

	private void detectTravelBehaviour() {
		if (random.nextInt(100) + 1 < A) {
			travelBehaviour = AirTravel.getInsatance();
		} else {
			travelBehaviour = DirectTravel.getInstance();
		}
	}

	public void changeHealth(int today) {
		healthBehaviour.changeHealth(today);
	}

	public void setHealth(int dayHealthChanged, Health health) {
		healthBehaviour.setHealth(dayHealthChanged, health);
	}

	public Health getHealth() {
		return healthBehaviour.getHealth();
	}

	private int assignNewDaysToMove() {
		return random.nextInt(5) + 1;
	}

	public boolean isVisiblySick() {
		return healthBehaviour.isVisiblySick();
	}

	public void setHealthBehaviour(HealthBehaviour healthBehaviour) {
		this.healthBehaviour = healthBehaviour;
	}

	public int getDaysToMove() {
		return daysToMove;
	}

	public abstract void vaccinate(Country country);

	public HealthBehaviour getBehaviour() {
		return healthBehaviour;
	}
}
