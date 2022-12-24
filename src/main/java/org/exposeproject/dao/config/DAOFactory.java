package org.exposeproject.dao.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.exposeproject.exception.ShutdownException;

public class DAOFactory {

	private static DAOFactory instance = null;

	private static final String FICHIER_PROPERTIES = "/org/exposeproject/dao/config/dao.properties";

	private static final String PROPERTY_URL = "url";
	private static final String PROPERTY_DRIVER = "driver";
	private static final String PROPERTY_NOM_UTILISATEUR = "nomutilisateur";
	private static final String PROPERTY_MOT_DE_PASSE = "motdepasse";
	private static final String CONFIGURED_ATTRIBUT = "configured";

	private static final String DATABASE_FILE = "/org/exposeproject/dao/config/Database.sql";
	private Connection connection = null;

	private String url;
	private String username;
	private String password;

	private DAOFactory(String url, String username, String password, Connection connection) {
		this.url = url;
		this.username = username;
		this.password = password;
		this.connection = connection;
	}

	public static DAOFactory getInstance() throws ShutdownException {

		if (instance == null) {
			Properties properties = new Properties();
			String url;
			String driver;
			String nomUtilisateur;
			String motDePasse;
			boolean configured = false;

			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			InputStream fichierProperties = classLoader.getResourceAsStream(FICHIER_PROPERTIES);

			if (fichierProperties == null) {
				throw new ShutdownException("Le fichier properties " + FICHIER_PROPERTIES + " est introuvable.");
			}

			// Loading Properties
			try {
				properties.load(fichierProperties);
				url = properties.getProperty(PROPERTY_URL);
				driver = properties.getProperty(PROPERTY_DRIVER);
				nomUtilisateur = properties.getProperty(PROPERTY_NOM_UTILISATEUR);
				motDePasse = properties.getProperty(PROPERTY_MOT_DE_PASSE);
				configured = Integer.parseInt(properties.getProperty(CONFIGURED_ATTRIBUT)) == 0 ? false : true;
			} catch (IOException e) {
				throw new ShutdownException("Impossible de charger le fichier properties " + FICHIER_PROPERTIES);
			}

			// Loading Drive
			try {
				Class.forName(driver);

			} catch (ClassNotFoundException e) {
				throw new ShutdownException("Le driver est introuvable dans le classpath.");
			}

			// Connecting to the database
			Connection connection = null;
			try {
				connection = DriverManager.getConnection(url, nomUtilisateur, motDePasse);
			} catch (SQLException e) {
				throw new ShutdownException("[ - ] - Database is disconnected");
			}

			// Installing database
			if (!configured) {
				ScriptRunner sr = new ScriptRunner(connection);
				Reader reader = new BufferedReader(
						new InputStreamReader(classLoader.getResourceAsStream(DATABASE_FILE)));
				sr.runScript(reader);
			}

			instance = new DAOFactory(url, nomUtilisateur, motDePasse, connection);
		}
		return instance;
	}

	public Connection getConnection() throws ShutdownException {
		try {
			if (connection.isClosed())
				this.connection = renewConnection();
		} catch (SQLException e) {
			this.connection = renewConnection();
		}
		return connection;
	}

	public Connection renewConnection() throws ShutdownException {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			throw new ShutdownException("[ - ] - Database is disconnected");
		}
		return connection;
	}
	
	public void close() {
		try {
			this.connection.close();
			System.out.println("[ + ] - Connection with the database is closed");
		} catch (SQLException e) {
			System.out.println("[ + ] - Connection with the database is closed");
		}
	}

}