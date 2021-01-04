/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Canne.vue.Fonctions;

import Canne.dao.Maria.MariaCategorieDao;
import Canne.dao.Maria.MariaFilmDao;
import Canne.dao.Maria.MariaSalleDao;
import Canne.dao.Maria.MariaVipDao;
import Canne.dao.Maria.MonMariaDbDataSource;
import Canne.dao.modele.Film;
import Canne.dao.modele.Vip;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author loann
 */
public class Fonctions {

    static DataSource dataSourceDAO = MonMariaDbDataSource.getMdbDataSource();
    static Connection connexionBD;
    static MariaFilmDao filmDao = new MariaFilmDao();;
    static List<Film> listFilms;
    
    public static void main(String[] args) throws SQLException {
        genererPlaning();
    }
    
    
    public static void genererPlaning() throws SQLException
    {
        //connexion a la BD
        connexionBD = dataSourceDAO.getConnection();
        
        filmDao.setDataSource(dataSourceDAO);
        filmDao.setConnection(connexionBD);
        
        //Récupération de tous les films dans la BD
        listFilms = filmDao.listeDesFilms();
        
        //Long metrage
        
        //Seance = un film + une salle...
        //Un certain regard
        for(Film f : listFilms) 
            if(f.getCategorie() == MariaCategorieDao.CM)
                System.out.println(f);
                //CM --> salle DEBussy ou Buñuel
                //Debussy : 1,2,3,4,5,6,7,8
                //Bunuel : 5,6,7,8,1,2,3,4
			     
    }
    
    public void courtMetrage()
    {
       
       
        
    }
    
    
    
}
