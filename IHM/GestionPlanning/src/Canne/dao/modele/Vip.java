package Canne.dao.modele;

public class Vip {

	//ID
    private int id;
    //Informations de la classe
    private String nom;
    private String prenom;
    private String nationalite;
    private String titre;
    private String importance;
    private String photo;
    private String infosupp;
	
	public Vip(int id,String nom, String prenom, String nationalite, String titre, String importance, String photo, String infosupp) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.nationalite = nationalite;
        this.titre = titre;
        this.importance = importance;
        this.photo = photo;
        this.infosupp = infosupp;
        
    }
    
    //Getters
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNationalite() {
        return nationalite;
    }
    
    //Setters
    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getImportance() {
        return importance;
    }

    public void setImportance(String importance) {
        this.importance = importance;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getInfosupp() {
        return infosupp;
    }

    public void setInfosupp(String infosupp) {
        this.infosupp = infosupp;
    }
    
    //ToString
    @Override
    public String toString() {
        return "OracleVipDao{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", nationalite=" + nationalite + ", titre=" + titre + ", importance=" + importance + ", photo=" + photo + ", infosupp=" + infosupp + '}';
    }
	
}
