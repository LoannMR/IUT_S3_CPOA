package Canne.vue;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import Canne.dao.Maria.MariaSalleDao;
import Canne.dao.modele.Salle;

@SuppressWarnings("serial")
public class FramePlanning extends JPanel {

	private Frame f;
	private MariaSalleDao salleDao;
	private List<Salle> listSalle;
	private String[] jour = {"jour 1", "jour 2", "jour 3", "jour 4", "jour 5", "jour 6","jour 7",
			"jour 8","jour 9", "jour 10","jour 11", "jour 12", "jour 13", "jour 14", "jour 15"};
	private JComboBox<String> selectJour;
	private JButton previous, next, createPlanning, addSeance;
	
	
	public FramePlanning(Frame f, int idPlanning) {
		this.f = f;
		setBounds(0, 0, (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 100, (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()-100);
		setLayout(null);
		
		salleDao = new MariaSalleDao();
		salleDao.setConnection(f.getConnexionBD());
		salleDao.setDataSource(f.getDataSourceDAO());
		listSalle = salleDao.listeDesSalles();
		initComponentPlanning();
	}
	
	JTabbedPane tabbedPane;
	
	private void initComponentPlanning() {
		
		selectJour = new JComboBox<>(jour);
		selectJour.setBounds(((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 100) / 2 - 75, 50, 150, 30);
		add(selectJour);
		
		previous = new JButton("Précédent");
		previous.setBounds(((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 100) / 2 - 400, 50, 150, 30);
		add(previous);
		
		next = new JButton("Suivant");
		next.setBounds(((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 100) / 2 + 250, 50, 150, 30);
		add(next);
		
		tabbedPane = new JTabbedPane();
		for(int i=0; i<listSalle.size(); i++) {
			GrillePlanning gp = new GrillePlanning(f);
			gp.setPreferredSize(new Dimension(500, 3150));
			JScrollPane scroll = new JScrollPane(gp, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			scroll.setPreferredSize(new Dimension(500, 600));
			tabbedPane.addTab(listSalle.get(i).getNomSalle(), scroll);
		}
		
		tabbedPane.setBounds(0, 100, (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth()-115, (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()-400);
		add(tabbedPane);
		repaint();
		
		createPlanning = new JButton("Générer le planning");
		createPlanning.setBounds(250, (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()-250, 150, 50);
		add(createPlanning);
		
		addSeance = new JButton("Ajouter une Séance");
		addSeance.setBounds((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 450, (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()-250, 150, 50);
		add(addSeance);
	}
	
}
