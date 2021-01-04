package Canne.vue;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.DataSource;

import Canne.dao.Maria.MariaCategorieDao;
import Canne.dao.Maria.MariaFilmDao;
import Canne.dao.Maria.MariaVipDao;
import Canne.dao.Maria.MonMariaDbDataSource;
import Canne.dao.modele.Film;
import Canne.dao.modele.Vip;

public class Frame {

	static DataSource dataSourceDAO;
	static Connection connexionBD;
	static MariaVipDao vipDao;
	static MariaFilmDao filmDao;
	
	public static void main(String[] args) {
		
		try {
			dataSourceDAO = MonMariaDbDataSource.getMdbDataSource();
			vipDao = new MariaVipDao();
			vipDao.setDataSource(dataSourceDAO);
			
			
			connexionBD = dataSourceDAO.getConnection();
			vipDao.setConnection(connexionBD);
			
			List<Vip> list = vipDao.listeDesVip();
			for(Vip v : list) {
				System.out.println(v);
			}
			
			filmDao = new MariaFilmDao();
			filmDao.setDataSource(dataSourceDAO);
			filmDao.setConnection(connexionBD);
			
			List<Film> listFilm = filmDao.listeDesFilms();
			for(Film f : listFilm) {
				System.out.println(f);
			}
			
		} catch (SQLException e) {
			Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, e);
		}
		
	}
	
}
