package Canne.dao.Maria;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import Canne.dao.IPlanningDao;
import Canne.dao.modele.Planning;

public class MariaPlanningDao implements IPlanningDao {

    DataSource ds;
    Connection c;
    List<Planning> listePlanning = new ArrayList<>();
	
    @Override
	public void setDataSource(DataSource ds) {
		this.ds = ds;
		
	}

	@Override
	public void setConnection(Connection c) {
		this.c = c;
		
	}

	@Override
	public List<Planning> listeDesPlannings() {
		ResultSet rset=null;
		Statement stmt=null;
		List<Planning> listePlanning = null;
		try {
			stmt= c.createStatement();
			listePlanning = new ArrayList<>();
			rset = stmt.executeQuery("SELECT * from Planning");
			while (rset.next()) {
				int id = rset.getInt("id");
				String nom = rset.getString("nom");
				
				Planning planning = new Planning(id, nom);
				listePlanning.add(planning);
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
		return listePlanning;
	}
	
	public boolean insertPlanning(Planning p) {
		int value = 0;
		Statement stmt = null;
		
		try {
			stmt= c.createStatement();
			String sql = "INSERT INTO Planning VALUES (" + p.getId() + ",'" + p.getNom() + "')";
			value = stmt.executeUpdate(sql);
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
    
	public boolean deletePlanning(Planning p) {
		int value = 0;
		Statement stmt = null;
		
		try {
			stmt= c.createStatement();
			value = stmt.executeUpdate("DELETE INTO Planning where idPlanning=" + p.getId());
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
