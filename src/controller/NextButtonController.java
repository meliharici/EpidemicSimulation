package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import model.SimulationBase;
import view.SimulationPanel;

public class NextButtonController implements ActionListener {

	private SimulationBase base;
	private SimulationPanel panel;

	public NextButtonController(SimulationBase base, SimulationPanel panel) {
		this.base = base;
		this.panel = panel;
		panel.getNextButton().addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		base.next();
		JLabel[] infoLabels = panel.getInfoLabels();
		infoLabels[0].setText(base.getPresentDay() + "");
		for (int i = 1; i < infoLabels.length; i++) {
			infoLabels[i].setText(base.giveInfoTotal()[i - 1] + "");
			infoLabels[i].repaint();
		}
		panel.getInfoPanel().repaint();
	}

}
