package Canne.vue;

import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class FramePlanning extends JPanel {

	private Frame f;
	
	public FramePlanning(Frame f) {
		this.f = f;
		setBounds(0, 0, (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 100, (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()-100);
		setLayout(null);
		initComponentPlanning();
		
	}
	
	private void initComponentPlanning() {
		JPanel panel = new JPanel();
	}
	
}
