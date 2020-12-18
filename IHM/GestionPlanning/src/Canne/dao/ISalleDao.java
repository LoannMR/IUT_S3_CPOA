package Canne.dao;

import java.sql.Connection;
import java.util.List;

import javax.sql.DataSource;

import Canne.dao.modele.Salle;


public interface ISalleDao {

	public void setDataSource(DataSource ds);
	
	public void setConnection(Connection c);
	
	public List<Salle> listeDesSalles();
	
}
