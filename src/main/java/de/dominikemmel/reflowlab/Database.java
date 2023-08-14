package de.dominikemmel.reflowlab;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import de.dominikemmel.reflowlab.controller.maincontrol.MainController;

public class Database {

	private static Connection con;
	private static boolean hasData = false;

	//read property file:
	public static Properties readDatabasePropertyFile() {
		String propPath = MainController.defaultInitSettings();

        Properties prop = new Properties();
        
        File tempFile = new File(propPath);

        if (tempFile.exists()) {
            try (InputStream input = new FileInputStream(propPath)) {

                // load a properties file
                prop.load(input);

                // get the property value and print it out
//                System.out.println(prop.getProperty("db.path"));
//                System.out.println(prop.getProperty("db.user"));
//                System.out.println(prop.getProperty("db.password"));

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
        	System.out.println("no Database property file exists!");
        }
		return prop;
	}



	//create property file or change input values
	public static void populatePropertyFile(String pathValue, String style) {
		String propPath = MainController.defaultInitSettings();
				
		try (OutputStream output = new FileOutputStream(propPath)) {

            Properties prop = new Properties();

//          TODO Add style sheets! 
            // set the properties value
            prop.setProperty("db.path", pathValue);
            prop.setProperty("style", "coming soon");

            // save properties to project root folder
            prop.store(output, null);

            System.out.println("DB path set to: " + pathValue);

        } catch (IOException io) {
            io.printStackTrace();
        }
	}





    // Extracting Data from SQL database.
	public static ResultSet selectData(String table) throws ClassNotFoundException, SQLException {
		ResultSet res = null;

		if(con == null) {
			createConnection(table);
		}
		Statement state = con.createStatement();

		switch (table) {
		case "activeMaterial":
			res = state.executeQuery("SELECT * FROM ACTIVEMATERIAL");
			break;
		case "solventInorganic":
			res = state.executeQuery("SELECT * FROM solventInorganic");
			break;
		case "solventOrganic":
			res = state.executeQuery("SELECT * FROM solventOrganic");
			break;
		case "electrolyte":
			res = state.executeQuery("SELECT * FROM electrolyte");
			break;
		case "costStack":
			res = state.executeQuery("SELECT * FROM costStack");
			break;
		case "costSalt":
			res = state.executeQuery("SELECT * FROM costSalt");
			break;
		case "costSolvent":
			res = state.executeQuery("SELECT * FROM costSolvent");
			break;
		case "separatorParameter":
			res = state.executeQuery("SELECT * FROM separatorParameter");
			break;
		case "costPeripherals":
			res = state.executeQuery("SELECT * FROM costPeripherals");
			break;
		case "reference":
			res = state.executeQuery("SELECT * FROM reference");
			break;
		case "stackSystemSelection":
			res = state.executeQuery("SELECT * FROM stackSystemSelection");
			break;
		case "lockedIDs":
			res = state.executeQuery("SELECT * FROM lockedIDs");
			break;
		default:
			System.out.println("no table selected.");
			break;
		}
		return res;
	}


	// Connecting with database + (creating database if not existing (H2 database)).
	public static void createConnection(String table) throws ClassNotFoundException, SQLException {
		Properties prop = new Properties();
		prop = Database.readDatabasePropertyFile();
		String pathProp = prop.getProperty("db.path");
		
		String[] pathDB = pathProp.split(".mv.db");


		File f = new File(pathDB[0]);

		Class.forName("org.h2.Driver");
		con = DriverManager.getConnection("jdbc:h2:" + pathDB[0], "root", "IcI0HQbjNC*zABHR#sk$nSZV$miPhS");

		Database.initialise(table);

		if (con.isValid(0)) {
			System.out.println("connection to: " + pathDB[0] + " - table: " + table);
		}
		if (con.isClosed()) {
			System.out.println("connection to: " + pathDB[0] + " - table: " + table + " is closed");
		}
//		dbTest();
	}



	// get Connection to H2 database.
	public static Connection getConnection(String table) throws ClassNotFoundException, SQLException {

		Properties prop = new Properties();
		prop = Database.readDatabasePropertyFile();
		String pathProp = prop.getProperty("db.path");
		
		String[] pathDB = pathProp.split(".mv.db");

		File f = new File(pathDB[0]);

	    Class.forName("org.h2.Driver");
		con = DriverManager.getConnection("jdbc:h2:" + pathDB[0], "root", "IcI0HQbjNC*zABHR#sk$nSZV$miPhS");

		Database.initialise(table);

		if (con.isValid(0)) {
			System.out.println("connection to: " + pathDB[0] + " - table: " + table);
		}
		if (con.isClosed()) {
			System.out.println("connection to: " + pathDB[0] + " - table: " + table + " is closed");
		}
//		dbTest();
		return con;
	}



	// Building tables if not existing.
	private static void initialise(String table) throws SQLException {
//		if (!hasData) {
//			hasData = true;

			Statement state = con.createStatement();
			String update = null;
			ResultSet res = null;

			switch (table) {
			case "activeMaterial":
				update = "CREATE TABLE IF NOT EXISTS activeMaterial(ID INT NOT NULL AUTO_INCREMENT,"
						+ "Abbreviation VARCHAR(255),"
						+ "Name VARCHAR(255),"
						+ "StructuralFormula VARCHAR(255),"
						+ "Category VARCHAR(255),"
						+ "M FLOAT,"
						+ "n INT,"
						+ "RefIDn INT,"
						+ "NumberH INT,"
						+ "RefIDNumberH INT,"
						+ "CAM FLOAT,"
						+ "RefIDCAM INT,"
						+ "Solvent VARCHAR(255),"
						+ "Salt VARCHAR(255),"
						+ "SaltC FLOAT,"
						+ "pH FLOAT,"
						+ "E FLOAT,"
						+ "RefIDE INT,"
						+ "editDate TIMESTAMP,"
						+ "primary key(ID));";
				break;
				
			case "solventInorganic":
				update = "CREATE TABLE IF NOT EXISTS solventInorganic(ID INT NOT NULL AUTO_INCREMENT,"
						+ "SolventStructuralFormula VARCHAR(255),"
						+ "c FLOAT,"
						+ "density FLOAT,"
						+ "RefIDdensity INT,"
						+ "dynViscosity FLOAT,"
						+ "RefIDdynViscosity INT,"
						+ "kinViscosity FLOAT,"
						+ "RefIDkinViscosity INT,"
						+ "editDate TIMESTAMP,"
						+ "primary key(ID));";
				break;
			case "solventOrganic":
				update = "CREATE TABLE IF NOT EXISTS solventOrganic(ID INT NOT NULL AUTO_INCREMENT,"
						+ "SolventName VARCHAR(255),"
						+ "SolventStructuralFormula VARCHAR(255),"
						+ "density FLOAT,"
						+ "RefIDdensity INT,"
						+ "dynViscosity FLOAT,"
						+ "RefIDdynViscosity INT,"
						+ "kinViscosity FLOAT,"
						+ "RefIDkinViscosity INT,"
						+ "ElimitCat FLOAT,"
						+ "RefIDElimitCat INT,"
						+ "ElimitAn FLOAT,"
						+ "RefIDElimitAn INT,"
						+ "editDate TIMESTAMP,"
						+ "primary key(ID));";
				break;
				
			case "electrolyte":
				update = "CREATE TABLE IF NOT EXISTS electrolyte(ID INT NOT NULL AUTO_INCREMENT,"
						+ "ActiveMaterial VARCHAR(255),"
						+ "Solvent VARCHAR(255),"
						+ "Salt VARCHAR(255),"
						+ "cSalt FLOAT,"
						+ "pH FLOAT,"
						+ "maxSolubility FLOAT,"
						+ "RefIDmaxSolubility INT,"
						+ "DOx FLOAT,"
						+ "RefIDDOx INT,"
						+ "DRed FLOAT,"
						+ "RefIDDRed INT,"
						+ "kOx FLOAT,"
						+ "RefIDkOx INT,"
						+ "AlphaOx FLOAT,"
						+ "RefIDAlphaOx INT,"
						+ "kRed FLOAT,"
						+ "RefIDkRed INT,"
						+ "AlphaRed FLOAT,"
						+ "RefIDAlphaRed INT,"
						+ "degRate FLOAT,"
						+ "RefIDdegRate INT,"
						+ "f FLOAT,"
						+ "fEloVol FLOAT,"
						+ "fConc FLOAT,"
						+ "note VARCHAR(1000),"
						+ "fSymCellCycl INT,"
						+ "theoMaxCap FLOAT,"
						+ "RefIDf INT,"
						+ "editDate TIMESTAMP,"
						+ "primary key(ID));";
				break;

			case "costStack":
				update = "CREATE TABLE IF NOT EXISTS costStack(ID INT NOT NULL AUTO_INCREMENT,"
						+ "ComponentName VARCHAR(255),"
						+ "ComponentType VARCHAR(255),"
						+ "ComponentCost FLOAT,"
						+ "RefIDComponentCost INT,"
						+ "CostUnit VARCHAR(255),"
						+ "editDate TIMESTAMP,"
						+ "primary key(ID));";
				break;
			case "costSalt":
				update = "CREATE TABLE IF NOT EXISTS costSalt(ID INT NOT NULL AUTO_INCREMENT,"
						+ "Salt VARCHAR(255),"
						+ "MSalt FLOAT,"
						+ "RefIDMSalt INT,"
						+ "CSalt FLOAT,"
						+ "RefIDCSalt INT,"
						+ "editDate TIMESTAMP,"
						+ "primary key(ID));";
				break;
			case "costSolvent":
				update =  "CREATE TABLE IF NOT EXISTS costSolvent(ID INT NOT NULL AUTO_INCREMENT,"
						+ "Solvent VARCHAR(255),"
						+ "MSolvent FLOAT,"
						+ "RefIDMSolvent INT,"
						+ "CSolvent FLOAT,"
						+ "RefIDCSolvent INT,"
						+ "editDate TIMESTAMP,"
						+ "primary key(ID));";
				break;
			case "costPeripherals":
				update =  "CREATE TABLE IF NOT EXISTS costPeripherals(ID INT NOT NULL AUTO_INCREMENT,"
						+ "ComponentName VARCHAR(255),"
						+ "ComponentType VARCHAR(255),"
						+ "ComponentCost FLOAT,"
						+ "RefIDComponentCost INT,"
						+ "CostUnit VARCHAR(255),"
						+ "editDate TIMESTAMP,"
						+ "primary key(ID));";
				break;
			case "separatorParameter":
				update = "CREATE TABLE IF NOT EXISTS separatorParameter(ID INT NOT NULL AUTO_INCREMENT,"
						+ "SeparatorName VARCHAR(255),"
						+ "Rasr FLOAT,"
						+ "RefIDRasr INT,"
						+ "editDate TIMESTAMP,"
						+ "primary key(ID));";
				break;
				
			case "reference":
				update = "CREATE TABLE IF NOT EXISTS reference(ID INT NOT NULL AUTO_INCREMENT,"
						+ "DOI VARCHAR(255),"
						+ "Bound VARCHAR(1000),"
						+ "Reference VARCHAR(255),"
						+ "Comment VARCHAR(2000),"
						+ "editDate TIMESTAMP,"
						+ "primary key(ID));";
				break;
				
			case "stackSystemSelection":
				update = "CREATE TABLE IF NOT EXISTS stackSystemSelection(ID INT NOT NULL AUTO_INCREMENT,"
						+ "StackSystem VARCHAR(255),"
						+ "SeparatorID INT,"
						+ "ElectrodeID INT,"
						+ "BipolarPlateID INT,"
						+ "CellFramesSealsID INT,"
						+ "CurrentCollectorID INT,"
						+ "StackFrameID INT,"
						+ "AssemblyID INT,"
						+ "editDate TIMESTAMP,"
						+ "primary key(ID));";
				break;
			case "lockedIDs":
				update = "CREATE TABLE IF NOT EXISTS lockedIDs(ID INT NOT NULL AUTO_INCREMENT,"
						+ "Bound VARCHAR(1000),"
						+ "editDate TIMESTAMP,"
						+ "primary key(ID));";
				break;
			}
			state.executeUpdate(update);
	}
}





