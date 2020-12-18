package Canne.dao.modele;

public class Salle {

    
    private String nomSalle;
    private int nbPlaces;
    private int idSalle;
    
    public Salle(int idSalle, int nbPlaces, String nomSalle) {
		this.idSalle = idSalle;
		this.nbPlaces = nbPlaces;
		this.nomSalle = nomSalle;
	}

	public String getNomSalle() {
		return nomSalle;
	}

	public void setNomSalle(String nomSalle) {
		this.nomSalle = nomSalle;
	}

	public int getNbPlaces() {
		return nbPlaces;
	}

	public void setNbPlaces(int nbPlaces) {
		this.nbPlaces = nbPlaces;
	}

	public int getIdSalle() {
		return idSalle;
	}

	public void setIdSalle(int idSalle) {
		this.idSalle = idSalle;
	}
    
    
	
}
