package Canne.dao.Maria;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import org.mariadb.jdbc.MariaDbDataSource;

public class MonMariaDbDataSource extends MariaDbDataSource {
	private static MonMariaDbDataSource mds;

	private MonMariaDbDataSource() {}

	
	public static MonMariaDbDataSource getMdbDataSource() {

		if (mds == null) {
			Properties prop = new Properties();
			FileInputStream fichier = null;
			try {
				fichier = new FileInputStream(".\\src\\Canne\\dao\\Maria\\connexion.properties");
			} 
			catch (FileNotFoundException ex1) {
				System.out.println("Fichier de proprietes non trouvé");
			}
			try {prop.load(fichier);} catch (IOException ex) {
				System.out.println("Erreur lors du chargement du fichier de proprietes mySQL");
			}
			finally {
				try {
					fichier.close();
				} 
				catch (IOException ex) {
					System.out.print("Problème d'entree/sortie" + ex.getMessage());
				}
			}
			mds = new MonMariaDbDataSource();
			try {
				mds.setPortNumber(Integer.parseInt(prop.getProperty("port")));
				mds.setServerName(prop.getProperty("serveur"));
				mds.setDatabaseName(prop.getProperty("base"));
				mds.setUser(prop.getProperty("user"));
				mds.setPassword(prop.getProperty("pwd"));
			} catch (NumberFormatException | SQLException e) {
				e.printStackTrace();
			}
			
		} 
		else System.out.println("---(lasource de data existe deja)");
		return mds;
	} 
}