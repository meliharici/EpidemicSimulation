package view;

import java.awt.Color;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CountryPanel extends JPanel {
	private static final Random random = new Random();
	private int ID;
	
	public CountryPanel(int ID){
		super();
		this.ID = ID;
		init();
	}

	public void init(){
		JLabel lb = new JLabel();
		String str = "C: " + ID;
		lb.setText(str);
		lb.setForeground(Color.WHITE);
		add(lb);
		setBackground(new Color(random.nextFloat(),random.nextFloat(),random.nextFloat()));
	}
	
	public int getID() {
		return ID;
	}
}
