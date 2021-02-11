package Canne.dao.Maria;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import Canne.dao.modele.Film;

public class MariaFilmDao {

	DataSource ds;
    Connection c;
    List<Film> listeFilm = new ArrayList<>();

	public void setDataSource(DataSource ds) {
		this.ds = ds;
		
	}

	public void setConnection(Connection c) {
		this.c = c;
		
	}

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
				int intCat = rset.getInt("categorie");
				ResultSet result = stmt.executeQuery("SELECT * from Categorie where idCat = " + intCat);
				result.next();
				String categorie = result.getString("nomCat");
				
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
	
	public Film getFilm(int idFilm) {
		ResultSet rset=null;
		Statement stmt=null;
		Film f = null;
		try {
			stmt= c.createStatement();
			listeFilm = new ArrayList<>();
			rset = stmt.executeQuery("SELECT * from Film where idFilm="+idFilm);
			
			while (rset.next()) {
				int id = rset.getInt("idFilm");
				int idRealisateur = rset.getInt("idRealisateur");
				String nomfilm = rset.getString("nomFilm");
				int duree = rset.getInt("duree");
				int intCat = rset.getInt("categorie");
				ResultSet result = stmt.executeQuery("SELECT * from Categorie where idCat = " + intCat);
				result.next();
				String categorie = result.getString("nomCat");
				
				f = new Film(id, nomfilm, idRealisateur, duree, categorie);
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
		return f;
	}
	
	public boolean insertFilm(Film f) {
		int value = 0;
		Statement stmt = null;
		
		try {
			stmt= c.createStatement();
			value = stmt.executeUpdate("INSERT INTO Film VALUES (" + f.getId() + ",'" + f.getNomFilm() + "'," + f.getIdRealisateur() + "," + f.getDuree() + ",'" + categorieToString(f.getCategorie()) + "')");
		}
		catch (SQLException exc) {
			exc.printStackTrace();
		}
		finally {
			try {
				if (stmt != null) stmt.close();
			}
			catch (SQLException ex) {
				ex.printStackTrace();	
			}
		}
		if(value > 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void insertFilm(List<Film> listF) {
		for(Film f : listF) {
			if(!insertFilm(f)) {
				System.out.println("le film " + f + "n'a pas été inseré");
			}
		}
	
	}
    
    private int categorieToString(Categorie cat) {
    	switch(cat) {
    	case LM:
    		return 1;
    	case HC:
    		return 2;
    	case UCR:
    		return 3;
    	case CM:
    		return 4;
    	case PP:
    		return 5;
    	default : 
    		return 1;
    	}
    }
    

    
    
    
    
    
}
