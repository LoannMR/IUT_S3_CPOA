/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Canne.dao.oracle;

/**
 *
 * @author loann
 */
public class OracleFilmDao {
    
    private int id;
    private String nomFilm;
    private int idRealisateur;
    private int duree; // En minutes
    private OracleCategorieDao categorie;

    public OracleFilmDao(int id, String nomFilm, int idRealisateur, int duree, OracleCategorieDao categorie) {
        this.id = id;
        this.nomFilm = nomFilm;
        this.idRealisateur = idRealisateur;
        this.duree = duree;
        this.categorie = categorie;
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

    public OracleCategorieDao getCategorie() {
        return categorie;
    }
    
    public void setCategorie(OracleCategorieDao categorie) {
        this.categorie = categorie;
    }
    
    //ToString
    @Override
    public String toString() {
        return "OracleFilmDao{" + "id=" + id + ", nomFilm=" + nomFilm + ", idRealisateur=" + idRealisateur + ", duree=" + duree + ", categorie=" + categorie + '}';
    }
    
    

    
    
    
    
    
}
