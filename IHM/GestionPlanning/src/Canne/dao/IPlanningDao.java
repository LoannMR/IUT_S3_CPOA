package Canne.dao;

import java.sql.Connection;
import java.util.List;

import javax.sql.DataSource;

import Canne.dao.modele.Planning;

public interface IPlanningDao {

	public void setDataSource(DataSource ds);
	
	public void setConnection(Connection c);
	
	public List<Planning> listeDesFilm();
	
}
