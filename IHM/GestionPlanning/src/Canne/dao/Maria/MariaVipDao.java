package Canne.dao.Maria;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import Canne.dao.IVipDao;
import Canne.dao.modele.Vip;

public class MariaVipDao implements IVipDao {
    
    DataSource ds;
    Connection c;
    List<Vip> listeVip = new ArrayList<>();

    @Override
	public void setDataSource(DataSource ds) {
		this.ds = ds;
		
	}

	@Override
	public void setConnection(Connection c) {
		this.c = c;
		
	}

	@Override
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
    
    
    

	
    
    
    
    
    
    
    
    
}
