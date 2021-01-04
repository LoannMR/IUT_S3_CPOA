package Canne.dao.modele;

import java.util.ArrayList;

import Canne.dao.Maria.Categorie;

public class Salle {

    
    private String nomSalle;
    private int nbPlaces;
    private int idSalle;
    private ArrayList<Categorie> listCategorie = new ArrayList<>();
    
    public Salle(int idSalle, int nbPlaces, String nomSalle, String categorie) {
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
    
	public ArrayList<Categorie> getListCategorie(){
		return listCategorie;
	}
	
	public void setListCategorie(String categorie) {
		String[] ls = categorie.split(" ");
		listCategorie.clear();
		for(String c : ls) {
			switch(c) {
	    	case "LM":
	    		listCategorie.add(Categorie.LM);
	    		break;
	    	case "UCR":
	    		listCategorie.add(Categorie.UCR);
	    		break;
	    	case "CM":
	    		listCategorie.add(Categorie.CM);
	    		break;
	    	case "HC":
	    		listCategorie.add(Categorie.HC);
	    		break;
	    	case "PP":
	    		listCategorie.add(Categorie.PP);
	    		break;
	    	default:
	    		listCategorie.add(Categorie.HC);
	    		break;
	    	}
		}
	}

	@Override
	public String toString() {
		return "Salle [nomSalle=" + nomSalle + ", nbPlaces=" + nbPlaces + ", idSalle=" + idSalle + ", listCategorie="
				+ listCategorie + "]";
	}
    
	
}
