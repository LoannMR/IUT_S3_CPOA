package Canne.vue;

import java.awt.Toolkit;
import java.util.List;

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
		tabbedPane = new JTabbedPane();
		for(int i=0; i<listSalle.size(); i++) {
			GrillePlanning gp = new GrillePlanning(f);
			JScrollPane scroll = new JScrollPane();
			scroll.setViewportView(gp);
			scroll.setBounds(0, 0, (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 100, (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()-400);
			tabbedPane.addTab(listSalle.get(i).getNomSalle(), scroll);
		}
		tabbedPane.setBounds(0, 0, (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 100, (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()-400);
		add(tabbedPane);
		
		
	}
	
}
