package Canne.dao.Maria;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import Canne.dao.modele.Seance;

public class MariaSeanceDao {

	DataSource ds;
    Connection c;

	public void setDataSource(DataSource ds) {
		this.ds = ds;
		
	}

	public void setConnection(Connection c) {
		this.c = c;
		
	}

	public List<Seance> listeDesSeances() {
		ResultSet rset=null;
		Statement stmt=null;
		List<Seance> listeSeance = null;
		try {
			stmt= c.createStatement();
			listeSeance = new ArrayList<>();
			rset = stmt.executeQuery("SELECT * from Seance");
			while (rset.next()) {
				int idSeance = rset.getInt("idSeance");
				int idPlanning = rset.getInt("idPlanning");
				int idFilm = rset.getInt("idFilm");
				int idSalle = rset.getInt("idSalle");
				String jour = rset.getString("jour");
				String horaire = rset.getString("horaire");
				
				
				Seance seance = new Seance(idSeance, idPlanning, idFilm, idSalle, jour, horaire);
				listeSeance.add(seance);
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
		return listeSeance;
	}
	
	public List<Seance> listSeancePlanning(int id){
		ResultSet rset=null;
		Statement stmt=null;
		List<Seance> listeSeance = null;
		try {
			stmt= c.createStatement();
			listeSeance = new ArrayList<>();
			rset = stmt.executeQuery("SELECT * from Seance where idPlanning="+id);
			while (rset.next()) {
				int idSeance = rset.getInt("idSeance");
				int idPlanning = rset.getInt("idPlanning");
				int idFilm = rset.getInt("idFilm");
				int idSalle = rset.getInt("idSalle");
				String jour = rset.getString("jour");
				String horaire = rset.getString("horaire");
				
				
				Seance seance = new Seance(idSeance, idPlanning, idFilm, idSalle, jour, horaire);
				listeSeance.add(seance);
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
		return listeSeance;
	}
	
	public List<Seance> listSeancePlanning(int id, String journee){
		ResultSet rset=null;
		Statement stmt=null;
		List<Seance> listeSeance = null;
		try {
			stmt= c.createStatement();
			listeSeance = new ArrayList<>();
			rset = stmt.executeQuery("SELECT * from Seance where idPlanning="+id + " AND jour='"+journee+"'");
			while (rset.next()) {
				int idSeance = rset.getInt("idSeance");
				int idPlanning = rset.getInt("idPlanning");
				int idFilm = rset.getInt("idFilm");
				int idSalle = rset.getInt("idSalle");
				String jour = rset.getString("jour");
				String horaire = rset.getString("horaire");
				
				
				Seance seance = new Seance(idSeance, idPlanning, idFilm, idSalle, jour, horaire);
				listeSeance.add(seance);
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
		return listeSeance;
	}
	
	public boolean insertSeance(Seance s) {
		int value = 0;
		Statement stmt = null;
		
		try {
			stmt= c.createStatement();
			value = stmt.executeUpdate("INSERT INTO Seance VALUES (" + s.getIdSeance() + "," + s.getIdPlanning() + "," + s.getIdFilm() + "," + s.getIdSalle() + ",'" + s.getJour() + "', '" + s.getHoraire() + "')");
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
	
	public int maxId(){
		ResultSet rset=null;
		Statement stmt=null;
		int idSeance = 0;
		try {
			stmt= c.createStatement();
			rset = stmt.executeQuery("SELECT max(idSeance) maxId from Seance");
			while (rset.next()) {
				idSeance = rset.getInt("maxId");
			}
		}
		catch (SQLException exc) {
		exc.printStackTrace();
		} finally {
			try {
			
				if (stmt != null) stmt.close();
				if (rset != null) rset.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return idSeance;
	}
	
	public void insertSeance(List<Seance> list) {
		for(Seance s : list) {
			if(!insertSeance(s)) {
				System.out.println("la seance " + s + "n'a pas été inseré");
			}
		}
	}
	
	public boolean deleteSeance(Seance s) {
		int value = 0;
		Statement stmt = null;
		
		try {
			stmt= c.createStatement();
			value = stmt.executeUpdate("DELETE FROM Seance where idSeance=" + s.getIdSeance());
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

}
