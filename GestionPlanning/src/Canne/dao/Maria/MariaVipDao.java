package Canne.dao.Maria;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import Canne.dao.modele.Vip;

public class MariaVipDao {
    
    DataSource ds;
    Connection c;
    List<Vip> listeVip = new ArrayList<>();

	public void setDataSource(DataSource ds) {
		this.ds = ds;
		
	}

	public void setConnection(Connection c) {
		this.c = c;
		
	}

	public List<Vip> listeDesVip() {
		ResultSet rset=null;
		Statement stmt=null;
		List<Vip> listeVip = null;
		try {
			stmt= c.createStatement();
			listeVip = new ArrayList<>();
			rset = stmt.executeQuery("SELECT * from VIP");
			while (rset.next()) {
				int id = rset.getInt("IDVIP");
				String nom = rset.getString("nom");
				String prenom = rset.getString("prenom");
				String nationalite = rset.getString("nationnalite");
				String titre = rset.getString("titre");
				String importance = rset.getString("importance");
				String photo = rset.getString("photo");
				String infosupp = rset.getString("infosupp");
				
				Vip vip = new Vip(id, nom, prenom, nationalite, titre, importance, photo, infosupp);
				listeVip.add(vip);
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
		return listeVip;
	}
    
    public Vip getVip(int id) {
    	ResultSet rset=null;
		Statement stmt=null;
		Vip vip = null;
		try {
			stmt= c.createStatement();
			listeVip = new ArrayList<>();
			rset = stmt.executeQuery("SELECT * from VIP where IDVIP="+id);
			while (rset.next()) {
				int idvip = rset.getInt("IDVIP");
				String nom = rset.getString("nom");
				String prenom = rset.getString("prenom");
				String nationalite = rset.getString("nationnalite");
				String titre = rset.getString("titre");
				String importance = rset.getString("importance");
				String photo = rset.getString("photo");
				String infosupp = rset.getString("infosupp");
				
				vip = new Vip(idvip, nom, prenom, nationalite, titre, importance, photo, infosupp);
				listeVip.add(vip);
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
		return vip;
    }
    

	
    
    
    
    
    
    
    
    
}
