package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.MenuController;

public class MenuPanel extends JPanel {
	private final String[] PARAMETER_TEXTS = { "N", "P", "X", "V", "S", "A", "D" };
	private final String[] BUTTON_TEXTS = { "kill some people", "didn't get it", "reset" };
	private final Color LABEL_COLOR = Color.white;
	private final Font FONT = new Font("Comic Sans MS", Font.BOLD,14);
	private JTextField[] textFiedlds;
	private BufferedImage image;
	private MenuController listener;

	public MenuPanel() {
		this.textFiedlds = new JTextField[PARAMETER_TEXTS.length];
		this.listener = new MenuController(this);
		try {
			image = ImageIO.read(new File("res/menu.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		init();
		textFiedlds[0].setText("3");
		textFiedlds[1].setText("810");
		textFiedlds[2].setText("30");
		textFiedlds[3].setText("3");
		textFiedlds[4].setText("10");
		textFiedlds[5].setText("40");
		textFiedlds[6].setText("5");

	}

	public void init() {
		setLayout(null);
		setSize(new Dimension(image.getWidth(),image.getHeight()));
		
		JLabel name = new JLabel("Epidemic Simulation", SwingConstants.CENTER);
		name.setBounds((getWidth()-300)/2,20, 300, 50);
		name.setFont(new Font("Comic Sans MS", Font.BOLD,30));
		name.setForeground(LABEL_COLOR.darker());
		add(name);
		
		for (int i = 0; i < PARAMETER_TEXTS.length; i++) {
			addField(PARAMETER_TEXTS[i], i);
		}

		for (int i = 0; i < BUTTON_TEXTS.length; i++) {
			addButtons(BUTTON_TEXTS[i], i);
		}
	}

	private void addField(String parameterName, int index) {
		int yCoordinate = 150 + (index * 35);
		JLabel parameterLabel = new JLabel(parameterName + ":", SwingConstants.CENTER);
		parameterLabel.setFont(FONT);
		parameterLabel.setBounds(10, yCoordinate, 20, 30);
		parameterLabel.setForeground(LABEL_COLOR);
		add(parameterLabel);
		JTextField parameterTextField = new JTextField(SwingConstants.CENTER);
		parameterTextField.setFont(FONT);
		parameterTextField.setBounds(45, yCoordinate, 150, 30);
		parameterTextField.setOpaque(false);
		parameterTextField.setForeground(LABEL_COLOR);
		parameterTextField.setCaretColor(LABEL_COLOR);
		textFiedlds[index] = parameterTextField;
		add(parameterTextField);
	}

	private void addButtons(String parameterName, int index) {
		JButton button = new JButton(parameterName);
		button.setFont(FONT);
		button.setBounds(300, 260 + (index * 50), 150, 30);
		button.setBackground(Color.darkGray);
		button.setForeground(LABEL_COLOR);
		button.addActionListener(listener);
		add(button);

	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
	}

	public JTextField[] getTextFiedlds() {
		return textFiedlds;
	}

}
