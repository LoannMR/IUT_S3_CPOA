package Canne.vue;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.DataSource;

import Canne.dao.Maria.MonMariaDbDataSource;

public class Main {

	private static DataSource dataSourceDAO;
	private static Connection connexionBD;
	
	public static void main(String[] args) {
		
		try {
			dataSourceDAO = MonMariaDbDataSource.getMdbDataSource();
			connexionBD = dataSourceDAO.getConnection();
			
			EventQueue.invokeLater(new Runnable() {
	            public void run() {
	                new Frame(dataSourceDAO, connexionBD).setVisible(true);
	            }
	        });
			
		} catch (SQLException e) {
			Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, e);
		}
		
	}

}
