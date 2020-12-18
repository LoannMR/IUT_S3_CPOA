package Canne.dao.modele;

public class Seance {

	

	private int idSeance;
	private int idPlanning;
	private int idFilm;
	private int idSalle;
	private String jour;
	private String horaire;
	
	public Seance(int idSenace, int idPlanning, int idFilm, int idSalle, String jour, String horaire) {
		this.idSeance = idSenace;
		this.idPlanning = idPlanning;
		this.idFilm = idFilm;
		this.idSalle = idSalle;
		this.jour = jour;
		this.horaire = horaire;
	}

	public int getIdSeance() {
		return idSeance;
	}

	public void setIdSeance(int idSeance) {
		this.idSeance = idSeance;
	}

	public int getIdPlanning() {
		return idPlanning;
	}

	public void setIdPlanning(int idPlanning) {
		this.idPlanning = idPlanning;
	}

	public int getIdFilm() {
		return idFilm;
	}

	public void setIdFilm(int idFilm) {
		this.idFilm = idFilm;
	}

	public int getIdSalle() {
		return idSalle;
	}

	public void setIdSalle(int idSalle) {
		this.idSalle = idSalle;
	}

	public String getJour() {
		return jour;
	}

	public void setJour(String jour) {
		this.jour = jour;
	}

	public String getHoraire() {
		return horaire;
	}

	public void setHoraire(String horaire) {
		this.horaire = horaire;
	}
	
	@Override
	public String toString() {
		return "Seance [idSeance=" + idSeance + ", idPlanning=" + idPlanning + ", idFilm=" + idFilm + ", idSalle="
				+ idSalle + ", jour=" + jour + ", horaire=" + horaire + "]";
	}
	
	
}
