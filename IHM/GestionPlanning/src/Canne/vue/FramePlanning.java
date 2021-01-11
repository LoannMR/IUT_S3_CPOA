package Canne.vue;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.LayoutStyle;

import Canne.dao.Maria.MariaFilmDao;
import Canne.dao.Maria.MariaSalleDao;
import Canne.dao.Maria.MariaSeanceDao;
import Canne.dao.modele.Film;
import Canne.dao.modele.Salle;
import Canne.dao.modele.Seance;

@SuppressWarnings("serial")
public class FramePlanning extends JPanel{

	private Frame f;
	private MariaSalleDao salleDao;
	private MariaSeanceDao seanceDao;
	private MariaFilmDao filmDao;
	private List<Salle> listSalle;
	private List<Seance> listSeance;
	private List<Film> listFilm;
	private String[] jour = {"jour 1", "jour 2", "jour 3", "jour 4", "jour 5", "jour 6","jour 7",
			"jour 8","jour 9", "jour 10","jour 11", "jour 12", "jour 13", "jour 14", "jour 15"};
	private JComboBox<String> selectJour;
	private JButton previous, next, createPlanning, addSeance;
	private List<GrillePlanning> listGp = new ArrayList<>();
	private int id;
	
	public FramePlanning(Frame f, int idPlanning) {
		this.f = f;
		id = idPlanning;
		setBounds(0, 0, (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 100, (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()-100);
		setLayout(null);
		
		salleDao = new MariaSalleDao();
		salleDao.setConnection(f.getConnexionBD());
		salleDao.setDataSource(f.getDataSourceDAO());
		listSalle = salleDao.listeDesSalles();
		
		seanceDao = new MariaSeanceDao();
		seanceDao.setConnection(f.getConnexionBD());
		seanceDao.setDataSource(f.getDataSourceDAO());
		
		filmDao = new MariaFilmDao();
		filmDao.setConnection(f.getConnexionBD());
		filmDao.setDataSource(f.getDataSourceDAO());
		listFilm = filmDao.listeDesFilms();
		
		Seance.setId(seanceDao.maxId());
		
		initComponentPlanning();
	}
	
	JTabbedPane tabbedPane;
	
	private void initComponentPlanning() {
		
		selectJour = new JComboBox<>(jour);
		selectJour.setBounds(((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 100) / 2 - 75, 50, 150, 30);
		add(selectJour);
		selectJour.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				listSeance = seanceDao.listSeancePlanning(id);
				for(GrillePlanning gp : listGp) {
					gp.clearSeance();
					for(Seance s : listSeance) {
						if(s.getIdSalle() == gp.getIdSalle() && s.getJour().equalsIgnoreCase((String) selectJour.getSelectedItem())) {
							gp.addSeance(s);
						}
					}
					gp.repaint();
				}
				
			}
		});
		
		previous = new JButton("Précédent");
		previous.setBounds(((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 100) / 2 - 400, 50, 150, 30);
		previous.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = selectJour.getSelectedIndex() - 1;
				if(index>=0) {
					selectJour.setSelectedIndex(index);
				}
			}
		});
		add(previous);
		
		next = new JButton("Suivant");
		next.setBounds(((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 100) / 2 + 250, 50, 150, 30);
		next.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = selectJour.getSelectedIndex() + 1;
				if(index<selectJour.getItemCount()) {
					selectJour.setSelectedIndex(index);
				}
			}
		});
		add(next);
		
		tabbedPane = new JTabbedPane();
		for(int i=0; i<listSalle.size(); i++) {
			GrillePlanning gp = new GrillePlanning(f, listSalle.get(i).getIdSalle(), listSalle.get(i).getNomSalle());
			gp.setPreferredSize(new Dimension(500, 3150));
			JScrollPane scroll = new JScrollPane(gp, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			scroll.setPreferredSize(new Dimension(500, 600));
			tabbedPane.addTab(listSalle.get(i).getNomSalle(), scroll);
			listGp.add(gp);
		}
		
		tabbedPane.setBounds(0, 100, (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth()-115, (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()-400);
		add(tabbedPane);
		repaint();
		
		createPlanning = new JButton("Générer le planning");
		createPlanning.setBounds(250, (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()-250, 150, 50);
		createPlanning.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		add(createPlanning);
		
		addSeance = new JButton("Ajouter une Séance");
		addSeance.setBounds((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 450, (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()-250, 150, 50);
		addSeance.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ajoutSeance();
				
			}
		});
		add(addSeance);
		
		listSeance = seanceDao.listSeancePlanning(id);
		for(GrillePlanning gp : listGp) {
			gp.clearSeance();
			for(Seance s : listSeance) {
				if(s.getIdSalle() == gp.getIdSalle() && s.getJour().equalsIgnoreCase((String) selectJour.getSelectedItem())) {
					gp.addSeance(s);
				}
			}
			gp.repaint();
		}
	}
	
	JComboBox<String> boxFilm, boxSalle, boxHeure;
	
	private void ajoutSeance() {
		
		boxFilm = new JComboBox<>();
        boxSalle = new JComboBox<>();
        boxHeure = new JComboBox<>();
        
		JDialog dialog = new JDialog();
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		JButton buttonValider = new JButton("Valider");
        JLabel labelFilm = new JLabel(),
        labelSalle = new JLabel(),
        labelHeure = new JLabel();
        
        for(Salle s : listSalle) {
        	boxSalle.addItem(s.getNomSalle());
        }
        
        int duree = 60*8+30, min = 0 , heure = 0;
		String text = "00h00";
		for(int i=0;i<31;i++) {
			text = "00h00";
			min = duree%60;
			heure = (duree-min)/60;
			if(heure < 10) text = text.replace("00h", "0" + heure + "h");
			else text = text.replace("00h", heure + "h");
			if(min < 10) text = text.replace("h00", "h0" + min);
			else text = text.replace("h00", "h" + min);
			boxHeure.addItem(text);
			duree += 30;
		}
		
		for(Film f : listFilm) {
			boxFilm.addItem(f.getNomFilm());
		}
		
		labelFilm.setText("Film : ");

        labelSalle.setText("Salle : ");

        labelHeure.setText("Heure début : ");

        GroupLayout dialogLayout = new GroupLayout(dialog.getContentPane());
        dialog.getContentPane().setLayout(dialogLayout);
        dialogLayout.setHorizontalGroup(
            dialogLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(dialogLayout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(dialogLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(GroupLayout.Alignment.TRAILING, dialogLayout.createSequentialGroup()
                        .addGroup(dialogLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addGroup(dialogLayout.createSequentialGroup()
                                .addComponent(labelHeure)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 158, Short.MAX_VALUE)
                                .addComponent(boxHeure, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGroup(dialogLayout.createSequentialGroup()
                                .addComponent(labelSalle)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(boxSalle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGroup(dialogLayout.createSequentialGroup()
                                .addComponent(labelFilm)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(boxFilm, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                        .addGap(70, 70, 70))
                    .addGroup(GroupLayout.Alignment.TRAILING, dialogLayout.createSequentialGroup()
                        .addComponent(buttonValider)
                        .addGap(204, 204, 204))))
        );
        dialogLayout.setVerticalGroup(
            dialogLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(dialogLayout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(dialogLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(boxFilm, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelFilm))
                .addGap(45, 45, 45)
                .addGroup(dialogLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(boxSalle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSalle))
                .addGap(39, 39, 39)
                .addGroup(dialogLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(boxHeure, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelHeure))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addComponent(buttonValider)
                .addGap(37, 37, 37))
        );
        
        buttonValider.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String nomFilm = (String) boxFilm.getSelectedItem();
				int idFilm = -1, index = 0;
				while(idFilm == -1 && index < listFilm.size()) {
					if(listFilm.get(index).getNomFilm().equalsIgnoreCase(nomFilm)) {
						idFilm = listFilm.get(index).getId();
					}
					else {
						index++;
					}
				}
				
				String nomSalle = (String) boxSalle.getSelectedItem();
				int idSalle = -1;
				index = 0;
				while(idSalle == -1 && index < listSalle.size()) {
					if(listSalle.get(index).getNomSalle().equalsIgnoreCase(nomSalle)) {
						idSalle = listSalle.get(index).getIdSalle();
					}
					else {
						index++;
					}
				}
				
				String heure = (String) boxHeure.getSelectedItem();
				
				Seance s = new Seance(-1, id, idFilm, idSalle, (String) selectJour.getSelectedItem(), heure);
				seanceDao.insertSeance(s);
				
				index = 0;
				boolean end = false;
				while(!end && index<listGp.size()) {
					if(listGp.get(index).getIdSalle() == idSalle) {
						listGp.get(index).addSeance(s);
						end = true;
					}
					index++;
				}
				dialog.dispose();
			}
		});
        
        dialog.pack();
        dialog.setVisible(true);
	}


	
	
}
