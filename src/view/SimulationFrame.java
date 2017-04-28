package view;

import java.awt.FlowLayout;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class SimulationFrame extends JFrame {

	public SimulationFrame() {
		init();
		setVisible(true);
	}

	private void init() {

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Epidemic Simulation");
		try {
			this.setIconImage(ImageIO.read(new File("res/mainFrame_icon.png")));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		MenuPanel mp =new MenuPanel();
		setContentPane(mp);
		setSize(mp.getWidth(), mp.getHeight());
		setLocationRelativeTo(null);
		setResizable(false);
	}

	public void initForSimulation() {
		remove(getContentPane());
		repaint();
		setBounds(50, 20, 1000, 650);
		setLocationRelativeTo(null);
	}

}
