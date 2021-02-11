package Canne.dao.modele;

import Canne.dao.Maria.Categorie;

public class Film {

	private int id;
    private String nomFilm;
    private int idRealisateur;
    private int duree; // En minutes
    private Categorie categorie;

    public Film(int id, String nomFilm, int idRealisateur, int duree, String categorie) {
        this.id = id;
        this.nomFilm = nomFilm;
        this.idRealisateur = idRealisateur;
        this.duree = duree;
        setCategorie(categorie);
    }

    //Getters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomFilm() {
        return nomFilm;
    }

    public void setNomFilm(String nomFilm) {
        this.nomFilm = nomFilm;
    }

    public int getIdRealisateur() {
        return idRealisateur;
    }

    //Setters
    public void setIdRealisateur(int idRealisateur) {
        this.idRealisateur = idRealisateur;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public Categorie getCategorie() {
        return categorie;
    }
    
    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }
    
    public void setCategorie(String categorie) {
    	switch(categorie) {
    	case "LM":
    		this.categorie = Categorie.LM;
    		break;
    	case "UCR":
    		this.categorie = Categorie.UCR;
    		break;
    	case "CM":
    		this.categorie = Categorie.CM;
    		break;
    	case "HC":
    		this.categorie = Categorie.HC;
    		break;
    	case "PP":
    		this.categorie = Categorie.PP;
    		break;
    	default:
    		this.categorie = Categorie.HC;
    		break;
    	}
    		
        
    }

	@Override
	public String toString() {
		return "Film [id=" + id + ", nomFilm=" + nomFilm + ", idRealisateur=" + idRealisateur + ", duree=" + duree
				+ ", categorie=" + categorie + "]";
	}
    
    
	
}
