package Canne.vue;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Canne.dao.Maria.MariaFilmDao;
import Canne.dao.Maria.MariaVipDao;
import Canne.dao.modele.Film;
import Canne.dao.modele.Seance;
import Canne.dao.modele.Vip;

@SuppressWarnings("serial")
public class GrillePlanning extends JPanel {

	ArrayList<Seance> listSeance = new ArrayList<>();
	ArrayList<JLabel> labelHeure = new ArrayList<>();
	
	private MariaFilmDao filmDao;
	private MariaVipDao vipDao;
	
	@SuppressWarnings("unused")
	private Frame f;
	
	public GrillePlanning(Frame f) {
		
		this.f = f;
		
		setSize(700, 31*100+50);
		setLayout(null);
		
		repaint();
		
		filmDao = new MariaFilmDao();
		filmDao.setConnection(f.getConnexionBD());
		filmDao.setDataSource(f.getDataSourceDAO());
		
		vipDao = new MariaVipDao();
		vipDao.setConnection(f.getConnexionBD());
		vipDao.setDataSource(f.getDataSourceDAO());
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.clearRect(0, 0, 700, 31*100+50);
		for(int i=0;i<31;i++) {
			g.drawLine(150, 50+i*100, 650, 50+i*100);
		}
		if(labelHeure.size() == 0) {
			setLabelHoraire();
		}
		
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
		
		String horaire = s.getHoraire();
		int horaireDebut = Integer.parseInt(horaire.substring(0, 2))*60 + Integer.parseInt(horaire.substring(3));
		horaireDebut -= 510;
		
		Vip vip = vipDao.getVip(f.getIdRealisateur());
		
		JButton seance = new JButton();
		seance.setText("<html>Nom : " + f.getNomFilm() + "<br>Réalisateur : " + vip.getPrenom() + " " + vip.getNom() + "<br>Durée : " + duree + " min");
		seance.setBounds(200, 50+(horaireDebut/30)*100+((horaireDebut%30)*10/3), 400, (duree/30)*100+((duree%30)*10/3));
		add(seance);
	}

	
}
