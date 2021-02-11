package Canne.vue;

import javax.swing.JButton;

import Canne.dao.modele.Film;
import Canne.dao.modele.Seance;
import Canne.dao.modele.Vip;

@SuppressWarnings("serial")
public class SeanceButton extends JButton {

	private Seance seance;
	private Film film;
	private Vip vip;
	
	public SeanceButton(Seance seance, Film film, Vip vip) {
		this.setSeance(seance);
		this.setFilm(film);
		this.setVip(vip);
	}

	public Seance getSeance() {
		return seance;
	}

	public void setSeance(Seance seance) {
		this.seance = seance;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public Vip getVip() {
		return vip;
	}

	public void setVip(Vip vip) {
		this.vip = vip;
	}
	
}
