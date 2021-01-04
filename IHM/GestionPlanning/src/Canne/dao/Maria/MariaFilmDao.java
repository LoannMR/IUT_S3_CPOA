package Canne.dao.Maria;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import Canne.dao.IFilmDao;
import Canne.dao.modele.Film;

public class MariaFilmDao implements IFilmDao{

	DataSource ds;
    Connection c;
    List<Film> listeFilm = new ArrayList<>();

    @Override
	public void setDataSource(DataSource ds) {
		this.ds = ds;
		
	}

	@Override
	public void setConnection(Connection c) {
		this.c = c;
		
	}

	@Override
	public List<Film> listeDesFilms() {
		ResultSet rset=null;
		Statement stmt=null;
		List<Film> listeFilm = null;
		try {
			stmt= c.createStatement();
			listeFilm = new ArrayList<>();
			rset = stmt.executeQuery("SELECT * from Film");
			while (rset.next()) {
				int id = rset.getInt("idFilm");
				int idRealisateur = rset.getInt("idRealisateur");
				String nomfilm = rset.getString("nomFilm");
				int duree = rset.getInt("duree");
				String categorie = rset.getString("categorie");
				
				Film film = new Film(id, nomfilm, idRealisateur, duree, categorie);
				listeFilm.add(film);
			}
		}
		catch (SQLException exc) {
		exc.printStackTrace();
		} finally {
			try {
			
			if (stmt != null) stmt.close();
			if (rset != null) rset.close();
			} catch (SQLException ex) {
				
			}
		}
		return listeFilm;
	}
    
    
    
    

    
    
    
    
    
}
