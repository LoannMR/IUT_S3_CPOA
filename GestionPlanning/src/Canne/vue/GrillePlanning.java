package Canne.vue;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

import Canne.dao.Maria.MariaFilmDao;
import Canne.dao.Maria.MariaSeanceDao;
import Canne.dao.Maria.MariaVipDao;
import Canne.dao.modele.Film;
import Canne.dao.modele.Seance;
import Canne.dao.modele.Vip;

@SuppressWarnings("serial")
public class GrillePlanning extends JPanel implements ActionListener {

	ArrayList<Seance> listSeance = new ArrayList<>();
	ArrayList<JLabel> labelHeure = new ArrayList<>();
	
	private MariaFilmDao filmDao;
	private MariaVipDao vipDao;
	private MariaSeanceDao seanceDao;
	
	@SuppressWarnings("unused")
	private Frame f;
	private int idSalle;
	private String nomSalle;
	
	public GrillePlanning(Frame f, int idSalle, String nomSalle) {
		
		this.f = f;
		this.idSalle = idSalle;
		this.nomSalle = nomSalle;
		
		setSize((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 100, 3150);
		setLayout(null);
		
		repaint();
		
		filmDao = new MariaFilmDao();
		filmDao.setConnection(f.getConnexionBD());
		filmDao.setDataSource(f.getDataSourceDAO());
		
		vipDao = new MariaVipDao();
		vipDao.setConnection(f.getConnexionBD());
		vipDao.setDataSource(f.getDataSourceDAO());
		
		seanceDao = new MariaSeanceDao();
		seanceDao.setConnection(f.getConnexionBD());
		seanceDao.setDataSource(f.getDataSourceDAO());
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.clearRect(0, 0, (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 100, 31*100+50);
		for(int i=0;i<31;i++) {
			g.drawLine(150, 50+i*100, (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 150, 50+i*100);
		}
		
		for(JLabel l : labelHeure) remove(l);
		labelHeure.clear();
		setLabelHoraire();
		
	}
	

	public void setLabelHoraire() {
		int duree = 60*8+30, min = 0 , heure = 0;
		String text = "00h00";
		for(int i=0;i<31;i++) {
			text = "00h00";
			JLabel label = new JLabel();
			min = duree%60;
			heure = (duree-min)/60;
			if(heure < 10) text = text.replace("00h", "0" + heure + "h");
			else text = text.replace("00h", heure + "h");
			if(min < 10) text = text.replace("h00", "h0" + min);
			else text = text.replace("h00", "h" + min);
			label.setText(text);
			label.setBounds(25, 25+i*100, 100, 50);
			labelHeure.add(label);
			add(label);
			duree += 30;
		}
			
	}
	
	public void addSeance(Seance s) {
		
		Film f = filmDao.getFilm(s.getIdFilm());
		int duree = f.getDuree();
		listSeance.add(s);
		
		String horaire = s.getHoraire();
		int horaireDebut = Integer.parseInt(horaire.substring(0, 2))*60 + Integer.parseInt(horaire.substring(3));
		horaireDebut -= 510;
		
		Vip vip = vipDao.getVip(f.getIdRealisateur());
		
		SeanceButton seance = new SeanceButton(s, f, vip);
		seance.setText("<html>Nom : " + f.getNomFilm() + "<br>Réalisateur : " + vip.getPrenom() + " " + vip.getNom() + "<br>Durée : " + duree + " min");
		seance.setBounds(200, 50+(horaireDebut/30)*100+((horaireDebut%30)*10/3), 400, (duree/30)*100+((duree%30)*10/3));
		seance.addActionListener(this);
		add(seance);
	}
	
	public void removeSeance(Seance s) {
		listSeance.remove(s);
	}
	
	public void clearSeance() {
		listSeance.clear();
		removeAll();
		setLabelHoraire();
	}

	public ArrayList<Seance> getListSeance() {
		return listSeance;
	}

	
	public int getIdSalle() {
		return idSalle;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		SeanceButton sb = (SeanceButton) e.getSource();
		
		JDialog dialog = new JDialog();
        JLabel labelFilm = new JLabel(),
        labelReal = new JLabel(),
        labelSalle = new JLabel(),
        labelHeure = new JLabel(),
        labelDuree = new JLabel();
        JButton buttonSup = new JButton(),
        buttonClose = new JButton();
        JTextField textFilm = new JTextField(),
        textReal = new JTextField(),
        textSalle = new JTextField(),
        textHoraire = new JTextField(),
        textDuree = new JTextField();
		
		dialog.setTitle("Info/editer séance");
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        labelFilm.setText("Film : ");

        labelReal.setText("Réalisateur : ");

        labelSalle.setText("Salle : ");

        labelHeure.setText("Heure début : ");

        labelDuree.setText("Durée : ");

        buttonSup.setText("Supprimer");
        buttonSup.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				seanceDao.deleteSeance(sb.getSeance());
				dialog.dispose();
				removeSeance(sb.getSeance());
				remove(sb);
				repaint();
			}
		});

        buttonClose.setText("Fermer");
        buttonClose.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.dispose();
			}
		});
        

        textFilm.setText(sb.getFilm().getNomFilm());
        textFilm.setEditable(false);
        textFilm.setBorder(BorderFactory.createEmptyBorder());

        textReal.setText(sb.getVip().getPrenom() + " " + sb.getVip().getNom());
        textReal.setEditable(false);
        textReal.setBorder(BorderFactory.createEmptyBorder());

        textSalle.setText(nomSalle);
        textSalle.setEditable(false);
        textSalle.setBorder(BorderFactory.createEmptyBorder());

        textHoraire.setText(sb.getSeance().getHoraire());
        textHoraire.setEditable(false);
        textHoraire.setBorder(BorderFactory.createEmptyBorder());

        textDuree.setText(sb.getFilm().getDuree() + "");
        textDuree.setEditable(false);
        textDuree.setBorder(BorderFactory.createEmptyBorder());

        GroupLayout dialogLayout = new GroupLayout(dialog.getContentPane());
        dialog.getContentPane().setLayout(dialogLayout);
        dialogLayout.setHorizontalGroup(
            dialogLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(dialogLayout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addGroup(dialogLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addGroup(dialogLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(labelDuree)
                        .addComponent(labelHeure)
                        .addComponent(labelSalle)
                        .addComponent(labelReal)
                        .addComponent(labelFilm))
                    .addGroup(dialogLayout.createSequentialGroup()
                        .addComponent(buttonSup)
                        .addGap(61, 61, 61)))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 154, Short.MAX_VALUE)
                .addGroup(dialogLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(GroupLayout.Alignment.TRAILING, dialogLayout.createSequentialGroup()
                        .addComponent(buttonClose)
                        .addGap(161, 161, 161))
                    .addGroup(GroupLayout.Alignment.TRAILING, dialogLayout.createSequentialGroup()
                        .addGroup(dialogLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(textReal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(textFilm, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(textSalle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(textHoraire, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(textDuree, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(171, 171, 171))))
        );
        dialogLayout.setVerticalGroup(
            dialogLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(dialogLayout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addGroup(dialogLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(labelFilm)
                    .addComponent(textFilm, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(dialogLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(labelReal)
                    .addComponent(textReal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(dialogLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(labelSalle)
                    .addComponent(textSalle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(dialogLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(labelHeure)
                    .addComponent(textHoraire, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(dialogLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(labelDuree)
                    .addComponent(textDuree, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                .addGroup(dialogLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonSup)
                    .addComponent(buttonClose))
                .addGap(46, 46, 46))
        );
		
        dialog.pack();
        dialog.setVisible(true);
	}



	
}
