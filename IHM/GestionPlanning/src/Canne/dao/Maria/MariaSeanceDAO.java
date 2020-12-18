package Canne.dao.Maria;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import Canne.dao.ISeanceDao;
import Canne.dao.modele.Seance;

public class MariaSeanceDAO implements ISeanceDao{

	DataSource ds;
    Connection c;
    List<Seance> listeSceance = new ArrayList<>();

    @Override
	public void setDataSource(DataSource ds) {
		this.ds = ds;
		
	}

	@Override
	public void setConnection(Connection c) {
		this.c = c;
		
	}

	@Override
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
		return listeSceance;
	}

}
