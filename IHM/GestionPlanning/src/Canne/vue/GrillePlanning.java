package Canne.vue;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.DataSource;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Canne.dao.Maria.MariaFilmDao;
import Canne.dao.Maria.MariaVipDao;
import Canne.dao.Maria.MonMariaDbDataSource;
import Canne.dao.modele.Film;
import Canne.dao.modele.Seance;
import Canne.dao.modele.Vip;

public class GrillePlanning extends JPanel {

	ArrayList<Seance> listSeance = new ArrayList<>();
	
	public GrillePlanning() {
		setSize(700, 31*100+50);
		setLayout(null);
		
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setSize(700, 800);
		f.getContentPane().add(this);
		f.repaint();
		repaint();
		f.setVisible(true);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.clearRect(0, 0, 700, 31*100+50);
		removeAll();
		int duree = 60*8+30, min = 0 , heure = 0;
		String text = "00h00";
		for(int i=0;i<31;i++) {
			text = "00h00";
			g.drawLine(150, 50+i*100, 650, 50+i*100);
			JLabel label = new JLabel();
			min = duree%60;
			heure = (duree-min)/60;
			if(heure < 10) text = text.replace("00h", "0" + heure + "h");
			else text = text.replace("00h", heure + "h");
			if(min < 10) text = text.replace("h00", "h0" + min);
			else text = text.replace("h00", "h" + min);
			label.setText(text);
			label.setBounds(25, 25+i*100, 100, 50);
			add(label);
			duree += 30;
		}
		for(Seance s : listSeance) {
			addSeance(s);
		}
		
	}
	
	int i = 0;
	
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
	
	public void AjoutSeance(Seance s) {
		listSeance.add(s);
		repaint();
	}
	
	static DataSource dataSourceDAO;
	static Connection connexionBD;
	static MariaFilmDao filmDao;
	static MariaVipDao vipDao;
	
	public static void main(String[] args) {
		
		try {
			dataSourceDAO = MonMariaDbDataSource.getMdbDataSource();
			connexionBD = dataSourceDAO.getConnection();
			filmDao = new MariaFilmDao();
			filmDao.setConnection(connexionBD);
			filmDao.setDataSource(dataSourceDAO);
			
			vipDao = new MariaVipDao();
			vipDao.setConnection(connexionBD);
			vipDao.setDataSource(dataSourceDAO);
			
			EventQueue.invokeLater(new Runnable() {
	            public void run() {
	                GrillePlanning p = new GrillePlanning();
	                Seance s = new Seance(0, 0, 1, 1, "jour 1", "09h00");
	                s = new Seance(1, 0, 2, 1, "jour 1", "11h30");
	                p.AjoutSeance(s);
	            }
	        });
			
		} catch (SQLException e) {
			Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, e);
		}
		
	}
	
}
