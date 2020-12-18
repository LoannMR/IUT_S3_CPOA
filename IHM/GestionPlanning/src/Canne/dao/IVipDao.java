package Canne.dao;

import java.sql.Connection;
import java.util.List;

import javax.sql.DataSource;

import Canne.dao.modele.Vip;


public interface IVipDao {

	public void setDataSource(DataSource ds);
	
	public void setConnection(Connection c);
	
	public List<Vip> listeDesVip();
	
}
