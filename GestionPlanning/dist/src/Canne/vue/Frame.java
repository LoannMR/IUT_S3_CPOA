package Canne.vue;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;

import javax.sql.DataSource;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Canne.dao.Maria.MariaPlanningDao;
import Canne.dao.modele.Planning;


@SuppressWarnings("serial")
public class Frame extends JFrame {

	DataSource dataSourceDAO;
	Connection connexionBD;
	
	private JMenu jMenuAccueil;
	private JMenuItem jAccueiItem;
    private JMenu jMenuPlanning;
    private JMenuItem jPlanningItem;
    private JMenuItem jPlanningItem2;
    private JMenu jMenuEdit;
    private JMenu jMenuView;
    private JMenu jMenuWindows;
    private JMenu jMenuHelp;
    private JMenuBar jMenuBar;
    
    
	
	public Frame(DataSource ds, Connection c) {
		dataSourceDAO = ds;
		connexionBD = c;
		setTitle("Gestion Projection des films du festival de Canne");
		setSize((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 100, (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()-100);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		initComponent();
		setAccueil();
	}
	
	private void initComponent() {
		jMenuBar = new JMenuBar();
		
		jMenuAccueil = new JMenu();
		jMenuAccueil.setText("Accueil");
		jMenuBar.add(jMenuAccueil);
		
		jAccueiItem = new JMenuItem("Accueil");
		jAccueiItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setAccueil();
				
			}
		});
		jMenuAccueil.add(jAccueiItem);

		jMenuPlanning = new JMenu();
		jMenuPlanning.setText("Planning");
        jMenuBar.add(jMenuPlanning);
        
        jPlanningItem = new JMenuItem("Ouvrir");
        jPlanningItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				getPlanning();
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
					setPlanning(p.getId());
				}
				
			}
		});
		jMenuPlanning.add(jPlanningItem);
		
		jPlanningItem2 = new JMenuItem("Nouveau");
        jPlanningItem2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				getPlanning();
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
					setPlanning(nb);
				}
				
			}
		});
		jMenuPlanning.add(jPlanningItem2);

        jMenuEdit = new JMenu();
        jMenuEdit.setText("Edit");
        jMenuBar.add(jMenuEdit);

        jMenuView = new JMenu();
        jMenuView.setText("View");
        jMenuBar.add(jMenuView);

        jMenuWindows = new JMenu();
        jMenuWindows.setText("Window");
        jMenuBar.add(jMenuWindows);

        jMenuHelp = new JMenu();
        jMenuHelp.setText("Help");
        jMenuBar.add(jMenuHelp);

        setJMenuBar(jMenuBar);
	}



	public DataSource getDataSourceDAO() {
		return dataSourceDAO;
	}

	public Connection getConnexionBD() {
		return connexionBD;
	}
	
	public void setAccueil() {
		getContentPane().removeAll();
		getContentPane().add(new FrameAccueil(this));
		repaint();
	}
	
	public void setPlanning(int idPlanning) {
		getContentPane().removeAll();
		getContentPane().add(new FramePlanning(this, idPlanning));
		repaint();
	}
	
	private MariaPlanningDao planningDao;
	private List<Planning> listPlanning;
	private void getPlanning() {
		planningDao = new MariaPlanningDao();
		planningDao.setDataSource(getDataSourceDAO());
		planningDao.setConnection(getConnexionBD());
		listPlanning = planningDao.listeDesPlannings();
	}
	
}
