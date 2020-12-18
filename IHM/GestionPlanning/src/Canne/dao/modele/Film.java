package Canne.dao.modele;

import Canne.dao.Maria.MariaCategorieDao;

public class Film {

	private int id;
    private String nomFilm;
    private int idRealisateur;
    private int duree; // En minutes
    private MariaCategorieDao categorie;

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

    public MariaCategorieDao getCategorie() {
        return categorie;
    }
    
    public void setCategorie(MariaCategorieDao categorie) {
        this.categorie = categorie;
    }
    
    public void setCategorie(String categorie) {
    	switch(categorie) {
    	case "LM":
    		this.categorie = MariaCategorieDao.LM;
    		break;
    	case "UCR":
    		this.categorie = MariaCategorieDao.UCR;
    		break;
    	case "CM":
    		this.categorie = MariaCategorieDao.CM;
    		break;
    	case "HC":
    		this.categorie = MariaCategorieDao.HC;
    		break;
    	case "PP":
    		this.categorie = MariaCategorieDao.PP;
    		break;
    	default:
    		this.categorie = MariaCategorieDao.HC;
    		break;
    	}
    		
        
    }

	@Override
	public String toString() {
		return "Film [id=" + id + ", nomFilm=" + nomFilm + ", idRealisateur=" + idRealisateur + ", duree=" + duree
				+ ", categorie=" + categorie + "]";
	}
    
    
	
}
