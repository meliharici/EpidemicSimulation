package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.SimulationBase;

public class SimulationPanel extends JPanel {
	private final String[] DATA = { "Day", "Healthy", "Infected", "Sick", "Immune", "Dead" };
	private int N;
	private JButton next;
	private JPanel infoPanel;
	private JPanel countryPanel;
	private ArrayList<String> countryInfos;
	private CountryPanel[] countries;
	private JLabel[] infoLabels;
	private SimulationBase simBase;

	public SimulationPanel(int N, SimulationBase simBase) {
		this.N = N;
		this.infoLabels = new JLabel[DATA.length];
		this.simBase = simBase;
		init();
		addComponenets();

	}

	private void init() {
		setLayout(null);
		setBackground(Color.black);
	}

	private void addComponenets() {

		countryInfos = new ArrayList<String>();
		for (int k = 0; k < N; k++) {
			countryInfos.add("this is country: " + k);
		}

		countryPanel = new JPanel();
		countryPanel.setBackground(Color.DARK_GRAY);
		countryPanel.setBounds(25, 25, 450, 450);
		countryPanel.setLayout(new GridLayout(N, N, 5, 5));

		countries = new CountryPanel[N * N];

		for (int i = 0; i < countries.length; i++) {
			countries[i] = new CountryPanel(i);
			countryPanel.add(countries[i]);
		}

		infoPanel = new JPanel();
		infoPanel.setBackground(Color.DARK_GRAY);
		infoPanel.setBounds(500, 25, 450, 550);
		infoPanel.setLayout(new GridLayout(0, 2, 0, 5));

		JLabel daylabel = new JLabel(DATA[0] + ":", SwingConstants.CENTER);
		daylabel.setForeground(Color.white);
		infoPanel.add(daylabel);
		infoLabels[0] = new JLabel(simBase.getPresentDay() + "",SwingConstants.CENTER);
		infoLabels[0].setForeground(Color.white);
		infoPanel.add(infoLabels[0]);

		for (int i = 1; i < DATA.length; i++) {
			JLabel label = new JLabel(DATA[i] + ":", SwingConstants.CENTER);
			label.setForeground(Color.white);
			infoLabels[i] = new JLabel(simBase.giveInfoTotal()[i - 1] + "",SwingConstants.CENTER);
			infoLabels[i].setForeground(Color.white);
			infoPanel.add(label);
			infoPanel.add(infoLabels[i]);
		}

		next = new JButton("next");
		next.setBounds(25, 500, 100, 50);
		next.setBackground(Color.black);
		next.setForeground(Color.white);

		add(next);
		add(countryPanel);
		add(infoPanel);
	}

	public CountryPanel[] getCountries() {
		return countries;
	}

	public JPanel getInfoPanel() {
		return infoPanel;
	}

	public JLabel[] getInfoLabels() {
		return infoLabels;
	}

	public JButton getNextButton() {
		return next;
	}

}
