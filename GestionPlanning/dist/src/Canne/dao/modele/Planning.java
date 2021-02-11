package Canne.dao.modele;


public class Planning {

	private int id;
	private String nom;
	
	public Planning(int id, String nom) {
		this.id = id;
		this.nom = nom;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	public String toString() {
		return "Planning [id=" + id + ", nom=" + nom + "]";
	}
	
	
	
}
