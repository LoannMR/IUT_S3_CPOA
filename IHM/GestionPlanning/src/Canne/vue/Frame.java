package Canne.vue;

import java.awt.Toolkit;
import java.sql.Connection;

import javax.sql.DataSource;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;


@SuppressWarnings("serial")
public class Frame extends JFrame {

	DataSource dataSourceDAO;
	Connection connexionBD;
	
	private JMenu jMenuAcceuil;
    private JMenu jMenuPlanning;
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
		
		jMenuAcceuil = new JMenu();
		jMenuAcceuil.setText("Accueil");
		jMenuBar.add(jMenuAcceuil);

		jMenuPlanning = new JMenu();
		jMenuPlanning.setText("Planning");
        jMenuBar.add(jMenuPlanning);

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
		repaint();
		getContentPane().add(new FrameAccueil(this));
	}
	
	public void setPlanning(int idPlanning) {
		getContentPane().removeAll();
		repaint();
		System.out.println(idPlanning);
	}
	
}
