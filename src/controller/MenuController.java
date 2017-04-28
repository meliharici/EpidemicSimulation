package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;

import model.SimulationBase;
import view.MenuPanel;
import view.SimulationFrame;
import view.SimulationPanel;

public class MenuController implements ActionListener {
	private MenuPanel panel;

	public MenuController(MenuPanel panel) {
		this.panel = panel;

	}

	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		JTextField[] fields = panel.getTextFiedlds();
		switch (button.getText()) {
		case "kill some people":
			killPeople(fields);
			break;
		case "didn't get it":
			// help
			break;
		case "reset":
			reset(fields);
			break;
		}
	}

	private void killPeople(JTextField[] fields) {
		SimulationFrame frame = (SimulationFrame) panel.getTopLevelAncestor();
		frame.initForSimulation();

		int[] parameters = new int[fields.length];
		for (int i = 0; i < parameters.length; i++) {

			parameters[i] = Integer.valueOf(fields[i].getText());
		}
		SimulationBase base = new SimulationBase(parameters[0], parameters[1], parameters[2], parameters[3],
				parameters[4], parameters[5], parameters[6]);

		SimulationPanel simulationPanel = new SimulationPanel(Integer.valueOf(fields[0].getText()), base);

		frame.setContentPane(simulationPanel);

		NextButtonController nc = new NextButtonController(base, simulationPanel);
		CountyPanelAdapter cpa = new CountyPanelAdapter(base, simulationPanel);

		frame.repaint();
	}

	private void reset(JTextField[] fields) {
		for (int i = 0; i < fields.length; i++) {
			fields[i].setText("");
			fields[i].repaint();
		}
	}
}
