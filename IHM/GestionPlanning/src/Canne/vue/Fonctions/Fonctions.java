/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Canne.vue.Fonctions;

import Canne.dao.Maria.MariaFilmDao;
import Canne.dao.Maria.MariaVipDao;
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

    static DataSource dataSourceDAO;
    static Connection connexionBD;
    static MariaFilmDao filmDao = new MariaFilmDao();;
    static List<Film> listFilms;
    
    public static void main(String[] args) {
        
    }
    
    
    public void genererPlaning() throws SQLException
    {
        //connexion a la BD
        connexionBD = dataSourceDAO.getConnection();
        
        filmDao.setDataSource(dataSourceDAO);
        filmDao.setConnection(connexionBD);
        
        //Récupération de tous les films dans la BD
        listFilms = filmDao.listeDesFilms();
        
        //Long metrage
        
        
        //Un certain regard
        
        
        
    }
    
    public void courtMetrage()
    {
       
       /*for(Vip v : list) {
				System.out.println(v);
			}*/
        
    }
    
    
    
}
