package controller;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import model.Country;
import model.SimulationBase;
import view.CountryPanel;
import view.SimulationFrame;
import view.SimulationPanel;

public class CountyPanelAdapter extends MouseAdapter {

	private SimulationBase base;
	public ArrayList<String> infos;

	public CountyPanelAdapter(SimulationBase base, SimulationPanel panel) {
		this.base = base;

		for (int i = 0; i < panel.getCountries().length; i++) {
			panel.getCountries()[i].addMouseListener(this);
		}

	}

	public void giveCountryInfo() {
		Country[][] countries = base.getCountries();
		infos = new ArrayList<String>();

		for (int i = 0; i < countries.length; i++) {
			for (int j = 0; j < countries.length; j++) {
				infos.add(countries[i][j].giveInfo());
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		giveCountryInfo();
		ImageIcon icon = new ImageIcon("res/skull_icon.png");
		CountryPanel currentCountry = (CountryPanel) e.getSource(); 
		int current = currentCountry.getID();
		JOptionPane.showOptionDialog((Component) e.getSource(), infos.get(current), "Country: " + current,
				JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, icon, new Object[] {}, null);
	}

}
