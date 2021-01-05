package Canne.dao.Maria;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import Canne.dao.ISalleDao;
import Canne.dao.modele.Salle;

public class MariaSalleDao implements ISalleDao{

	DataSource ds;
    Connection c;
    List<Salle> listeSalle = new ArrayList<>();
	
    @Override
	public void setDataSource(DataSource ds) {
		this.ds = ds;
		
	}

	@Override
	public void setConnection(Connection c) {
		this.c = c;
		
	}

	@Override
	public List<Salle> listeDesSalles() {
		ResultSet rset=null;
		Statement stmt=null;
		List<Salle> listeSalle = null;
		try {
			stmt= c.createStatement();
			listeSalle = new ArrayList<>();
			rset = stmt.executeQuery("SELECT * from Salle");
			while (rset.next()) {
				int id = rset.getInt("idSalle");
				String nomSalle = rset.getString("nomSalle");
				int nbPlaces = rset.getInt("nbPlace");
				String categorie = rset.getString("categorie");
				
				Salle salle = new Salle(id, nbPlaces, nomSalle, categorie);
				listeSalle.add(salle);
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
		return listeSalle;
	}

	
	public Salle getMaxPlace(String nomCategorie) {
		ResultSet rset=null;
		Statement stmt=null;
		Salle salle = null;
		try {
			stmt= c.createStatement();
			rset = stmt.executeQuery("SELECT * from Salle where nbPlace=(select MAX(nbPlace) from Salle where categorie='"+nomCategorie+"')");
			while (rset.next()) {
				int id = rset.getInt("idSalle");
				String nomSalle = rset.getString("nomSalle");
				int nbPlaces = rset.getInt("nbPlace");
				String categorie = rset.getString("categorie");
				
				salle = new Salle(id, nbPlaces, nomSalle, categorie);
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
		return salle;
	}
	
	
}
