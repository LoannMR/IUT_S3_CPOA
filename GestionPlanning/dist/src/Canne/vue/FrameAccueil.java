package Canne.vue;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Canne.dao.Maria.MariaPlanningDao;
import Canne.dao.modele.Planning;

@SuppressWarnings("serial")
public class FrameAccueil extends JPanel{

	private Frame f;
	private MariaPlanningDao planningDao;
	
	public FrameAccueil(Frame f) {
		this.f = f;
		setBounds(0, 0, (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 100, (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()-100);
		setLayout(null);
		try {
			initComponentAcceuil();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private JButton jButtonOuvrirPlanning;
    private JButton jButtonNouveauPlanning;
        
    private JLabel image;
	
	public void initComponentAcceuil() throws IOException {
		getPlanning();
		
		String imagePath = ".\\src\\CAnne\\vue\\Image\\Logo_Canne.png";
		
		image = new JLabel(new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(500, 300, Image.SCALE_DEFAULT)));
		image.setBounds(100, 50, (int) getSize().getWidth() - 200, (int) getSize().getHeight() - 450);
		add(image);
		
		jButtonOuvrirPlanning = new JButton("Ouvrir un Planning");
		jButtonOuvrirPlanning.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				jButtonOuvrirActionPerformed(e);
				
			}
		});
		jButtonOuvrirPlanning.setBounds((int) (getSize().getWidth()/2)-250, (int) getSize().getHeight()-250, 150, 50);
		add(jButtonOuvrirPlanning);
		
		jButtonNouveauPlanning = new JButton("Nouveau Planning");
		jButtonNouveauPlanning.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				jButtonNouveauActionPerformed(e);
				
			}
		});
		jButtonNouveauPlanning.setBounds((int) (getSize().getWidth()/2)+100, (int) getSize().getHeight()-250, 150, 50);
		add(jButtonNouveauPlanning);
		

		
	}


	private void jButtonOuvrirActionPerformed(ActionEvent evt) {
		JComboBox<String> planning = new JComboBox<String>(); 
		for(Planning p : listPlanning) {
			planning.addItem(p.getNom());
		}
		int retour = JOptionPane.showConfirmDialog(null, planning, "Choix du planning", JOptionPane.DEFAULT_OPTION);
		if(retour == 0) {
			Planning p = null;
			int i = 0;
			while(p==null && i<listPlanning.size()) {
				if(listPlanning.get(i).getNom().equalsIgnoreCase((String) planning.getSelectedItem())) {
					p = listPlanning.get(i);
				}
				else {
					i++;
				}
			}
			f.setPlanning(p.getId());
		}
	}
	
	private void jButtonNouveauActionPerformed(ActionEvent evt) {
		JTextField nom = new JTextField();
		int retour = JOptionPane.showConfirmDialog(null, nom, "Saisir le nom du planning", JOptionPane.DEFAULT_OPTION);
		if(retour == 0) {
			int nb = -1, index = 0;
			while(nb<0) {
				int i=0;
				boolean present = false;
				while(i<listPlanning.size() && !present) {
					if(listPlanning.get(i).getId() == index) {
						present = true;
						index++;
					}
					else {
						i++;
					}
				}
				if(!present) nb = index;
		
			}
			Planning p = new Planning(nb, nom.getText());
			planningDao.insertPlanning(p);
			f.setPlanning(nb);
		}

	}
	
	
	private List<Planning> listPlanning;
	private void getPlanning() {
		planningDao = new MariaPlanningDao();
		planningDao.setDataSource(f.getDataSourceDAO());
		planningDao.setConnection(f.getConnexionBD());
		listPlanning = planningDao.listeDesPlannings();
	}
	
}
