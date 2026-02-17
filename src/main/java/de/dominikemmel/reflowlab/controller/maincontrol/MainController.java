package de.dominikemmel.reflowlab.controller.maincontrol;



import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import de.dominikemmel.reflowlab.Database;
import de.dominikemmel.reflowlab.FxmlLoader;
import de.dominikemmel.reflowlab.VariousMethods;
import net.harawata.appdirs.AppDirs;
import net.harawata.appdirs.AppDirsFactory;

public class MainController implements Initializable {


	@FXML
	private BorderPane mainPane;

	@FXML
	private Button btnHome;
	@FXML
	private Button btnActiveMaterial;
	@FXML
	private Button btnSolvent;
	@FXML
	private Button btnElectrolyte;
	@FXML
	private Button btnCosts;
	@FXML
	private Button btnReference;
	@FXML
	private Button btnDB;
	
	Stage stageConsole = new Stage();


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		FxmlLoader object = new FxmlLoader();
		Pane view = object.getPageByResource("controller/home/fxml/home.fxml");
		mainPane.setCenter(view);

		btnHome.getStyleClass().clear();
		btnActiveMaterial.getStyleClass().clear();
		btnSolvent.getStyleClass().clear();
		btnElectrolyte.getStyleClass().clear();
		btnCosts.getStyleClass().clear();
		btnReference.getStyleClass().clear();

		btnHome.getStyleClass().add("buttonSelected");
		btnActiveMaterial.getStyleClass().add("button1");
		btnSolvent.getStyleClass().add("button1");
		btnElectrolyte.getStyleClass().add("button1");
		btnCosts.getStyleClass().add("button1");
		btnReference.getStyleClass().add("button1");

		MainController.defaultInitDB();
		MainController.defaultInitSettings();
		MainController.this.dbTest();
		MainController.this.testBtnDB();
		MainController.this.startConsole();

	}

//	@FXML
//	public void imageMoose (ActionEvent event) {
//
//		try {
//
//			VariousMethods.Browser("https://www.uni-giessen.de/fbz/fb08/Inst/physchem/schroeder");
//
//		} catch (Exception e) {
//
//			e.printStackTrace();
//		}
//	}
	
	//initialize default settings + DB
	public static String defaultInitDB () {
		
        AppDirs appDirs = AppDirsFactory.getInstance();
        String pathString = appDirs.getUserDataDir("reflowlab", "db", "dominikemmel");
        String dbName = "reflowlabDefaultDB.mv.db";
        String pathStringDB = pathString + File.separator + dbName;
        
        File file = new File(pathStringDB);
		File directory = new File(pathString);
        
        if (file.exists()) {
        	return pathStringDB;
        } else {

			if (!directory.exists()) {
				directory.mkdirs();
			}
        	
        	Path pathDB = Paths.get(pathStringDB);

			InputStream inputStreamDB = MainController.class.getResourceAsStream("/de/dominikemmel/reflowlab/default/reflowlabDefaultDB.mv.db");

			try {
				Files.copy(inputStreamDB, pathDB);
			} catch (IOException e) {
				e.printStackTrace();
			}
				
	        System.out.println("Path created and default DB copied: "+pathStringDB);

        	return pathStringDB;
        	
        }
	}
	
	
	
	public static String defaultInitSettings () {
		
		AppDirs appDirs = AppDirsFactory.getInstance();
		String pathString = appDirs.getUserDataDir("reflowlab", "settings", "dominikemmel");
		String setName = "defaultSettings.properties";
		String pathStringSet = pathString + File.separator + setName;
		
		File file = new File(pathStringSet);
		File directory = new File(pathString);
		
		if (file.exists()) {
			return pathStringSet;
		} else {
			if (!directory.exists()) {
				directory.mkdirs();
			}
			
			Path pathSet = Paths.get(pathStringSet);
			
			InputStream inputStreamSet = MainController.class.getResourceAsStream("/de/dominikemmel/reflowlab/default/defaultSettings.properties");

			try {
				Files.copy(inputStreamSet, pathSet);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
        	System.out.println("Path created and default settings copied: "+pathStringSet);
        	
        	Database.populatePropertyFile(defaultInitDB(), "style1");
        	
			return pathStringSet;
		}
	}
	

	@FXML
	public void imageInES (ActionEvent event) {

		try {
			VariousMethods.Browser("https://www.tu-braunschweig.de/ines");

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@FXML
	public void imageTUBS (ActionEvent event) {

		try {
			VariousMethods.Browser("https://www.tu-braunschweig.de/");

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@FXML
	public void emailToMe (ActionEvent event) throws IOException {

		URI msg;
		try {
			msg = new URI("mailto","d.emmel@tu-braunschweig.de?subject=[ReFlowLab]", (String) null);
			Desktop.getDesktop().mail(msg);
		} catch (URISyntaxException e) {

			e.printStackTrace();
		}

	}

	@FXML
	public void loadHome (ActionEvent event) {

		FxmlLoader object = new FxmlLoader();
		Pane view = object.getPageByResource("controller/home/fxml/home.fxml");
		mainPane.setCenter(view);

		btnHome.getStyleClass().clear();
		btnActiveMaterial.getStyleClass().clear();
		btnSolvent.getStyleClass().clear();
		btnElectrolyte.getStyleClass().clear();
		btnCosts.getStyleClass().clear();
		btnReference.getStyleClass().clear();

		btnHome.getStyleClass().add("buttonSelected");
		btnActiveMaterial.getStyleClass().add("button1");
		btnSolvent.getStyleClass().add("button1");
		btnElectrolyte.getStyleClass().add("button1");
		btnCosts.getStyleClass().add("button1");
		btnReference.getStyleClass().add("button1");
	}

	@FXML
	public void loadActiveMaterial (ActionEvent event) {

		FxmlLoader object = new FxmlLoader();
		Pane view = object.getPageByResource("controller/activematerials/fxml/activeMaterials.fxml");
		mainPane.setCenter(view);

		btnHome.getStyleClass().clear();
		btnActiveMaterial.getStyleClass().clear();
		btnSolvent.getStyleClass().clear();
		btnElectrolyte.getStyleClass().clear();
		btnCosts.getStyleClass().clear();
		btnReference.getStyleClass().clear();

		btnHome.getStyleClass().add("button1");
		btnActiveMaterial.getStyleClass().add("buttonSelected");
		btnSolvent.getStyleClass().add("button1");
		btnElectrolyte.getStyleClass().add("button1");
		btnCosts.getStyleClass().add("button1");
		btnReference.getStyleClass().add("button1");
	}


	@FXML
	public void loadSolvent (ActionEvent event) {

		FxmlLoader object = new FxmlLoader();
		Pane view = object.getPageByResource("controller/solvent/fxml/solvent.fxml");
		mainPane.setCenter(view);

		btnHome.getStyleClass().clear();
		btnActiveMaterial.getStyleClass().clear();
		btnSolvent.getStyleClass().clear();
		btnElectrolyte.getStyleClass().clear();
		btnCosts.getStyleClass().clear();
		btnReference.getStyleClass().clear();

		btnHome.getStyleClass().add("button1");
		btnActiveMaterial.getStyleClass().add("button1");
		btnSolvent.getStyleClass().add("buttonSelected");
		btnElectrolyte.getStyleClass().add("button1");
		btnCosts.getStyleClass().add("button1");
		btnReference.getStyleClass().add("button1");
	}

	@FXML
	public void loadElectrolytes (ActionEvent event) {

		FxmlLoader object = new FxmlLoader();
		Pane view = object.getPageByResource("controller/electrolytes/fxml/electrolytes.fxml");
		mainPane.setCenter(view);

		btnHome.getStyleClass().clear();
		btnActiveMaterial.getStyleClass().clear();
		btnSolvent.getStyleClass().clear();
		btnElectrolyte.getStyleClass().clear();
		btnCosts.getStyleClass().clear();
		btnReference.getStyleClass().clear();

		btnHome.getStyleClass().add("button1");
		btnActiveMaterial.getStyleClass().add("button1");
		btnSolvent.getStyleClass().add("button1");
		btnElectrolyte.getStyleClass().add("buttonSelected");
		btnCosts.getStyleClass().add("button1");
		btnReference.getStyleClass().add("button1");
	}

	@FXML
	public void loadCosts (ActionEvent event) {

		FxmlLoader object = new FxmlLoader();
		Pane view = object.getPageByResource("controller/costs/fxml/costs.fxml");
		mainPane.setCenter(view);

		btnHome.getStyleClass().clear();
		btnActiveMaterial.getStyleClass().clear();
		btnSolvent.getStyleClass().clear();
		btnElectrolyte.getStyleClass().clear();
		btnCosts.getStyleClass().clear();
		btnReference.getStyleClass().clear();

		btnHome.getStyleClass().add("button1");
		btnActiveMaterial.getStyleClass().add("button1");
		btnSolvent.getStyleClass().add("button1");
		btnElectrolyte.getStyleClass().add("button1");
		btnCosts.getStyleClass().add("buttonSelected");
		btnReference.getStyleClass().add("button1");
	}

	@FXML
	public void loadReferences (ActionEvent event) {

		FxmlLoader object = new FxmlLoader();
		Pane view = object.getPageByResource("controller/references/fxml/references.fxml");
		mainPane.setCenter(view);

		btnHome.getStyleClass().clear();
		btnActiveMaterial.getStyleClass().clear();
		btnSolvent.getStyleClass().clear();
		btnElectrolyte.getStyleClass().clear();
		btnCosts.getStyleClass().clear();
		btnReference.getStyleClass().clear();

		btnHome.getStyleClass().add("button1");
		btnActiveMaterial.getStyleClass().add("button1");
		btnSolvent.getStyleClass().add("button1");
		btnElectrolyte.getStyleClass().add("button1");
		btnCosts.getStyleClass().add("button1");
		btnReference.getStyleClass().add("buttonSelected");
	}



	@FXML
	public void btnDB (ActionEvent event) {
		selectPathPaneBtn();
		testBtnDB();
	}

	public void btnDBGreen() {
		String btnStyle = String.format("-fx-background-color: #90EE90");
		btnDB.setStyle(btnStyle);
	}


	public void btnDBRed() {
		String btnStyle = String.format("-fx-background-color: #D0312D");
		btnDB.setStyle(btnStyle);
	}


	public void testBtnDB() {
		Properties prop = new Properties();
		prop = Database.readDatabasePropertyFile();
        String pathDB = prop.getProperty("db.path");
        File tempFileDB = new File(pathDB);

        if (tempFileDB.exists()) {
        	btnDBGreen();
        } else {
        	btnDBRed();
//        	Database.selectPathPane();
        }
	}




	//DB file:
	public void populatePropertyFileDbFile() {

		//Create a file chooser
		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("Database file chooser");

		//Open Dialog:
		fc.showOpenDialog(null);


	    File f = new File(fc.getSelectedFile().getAbsoluteFile().toString());
	    System.out.println(f);

	    // test if file is existing:
	    if (f.exists() && f.isFile()) {
	    	System.out.println(f  + " exists");
	    }

		String path = f.getAbsolutePath();
//		String[] pathDB = path.split(".mv.db");

//		Database.populatePropertyFile(pathDB[0],"");
		Database.populatePropertyFile(path, "");
	}




	//DB path:
	public void populatePropertyFileDbPath() {
		JFrame parentFrame = new JFrame();
		//Create a file chooser
		JFileChooser fc2 = new JFileChooser();
		fc2.setDialogTitle("Database path chooser");
		fc2.setDialogType(JFileChooser.SAVE_DIALOG);
		fc2.setSelectedFile(new File("reflowlabDefaultDB.mv.db"));
		fc2.setFileFilter(new FileNameExtensionFilter("mv.db file","mv.db"));
		fc2.showSaveDialog(parentFrame);

	    File f = new File(fc2.getSelectedFile().getAbsoluteFile().toString());
	    System.out.println(f);

	    // test if file is existing:
	    if (f.exists() && f.isFile()) {
	    	System.out.println(f  + " exists");
	    }

		String path = f.getAbsolutePath();
//		String[] pathDB = path.split(".mv.db");

		Database.populatePropertyFile(path,"");

		try {
			Database.createConnection("activeMaterial");
			Database.createConnection("solvent");
			Database.createConnection("electrolyte");
			Database.createConnection("costAnalysis");
			Database.createConnection("reference");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	public void dbTest() {

		Properties prop = new Properties();
		prop = Database.readDatabasePropertyFile();
        String pathDB = prop.getProperty("db.path");
        File tempFileDB = new File(pathDB);

        if (tempFileDB.exists()) {
//        	System.out.println(pathDB + " exists!");

        } else {
        	MainController.this.selectPathPane();
        }
	}



	//Path selection pane:
	public void selectPathPane() {

		try {
			Stage stage1 = new Stage();
			Parent rootPathSelectionPane;
			rootPathSelectionPane = FXMLLoader.load(PathSelectionPaneController.class.getResource("/de/dominikemmel/reflowlab/controller/maincontrol/fxml/pathSelectionPane.fxml"));

			stage1.setScene(new Scene(rootPathSelectionPane));
			stage1.show();
			stage1.setAlwaysOnTop(true);

			stage1.setOnCloseRequest(event1 -> {
				MainController.this.dbTest();
				MainController.this.testBtnDB();
			});

			stage1.setOnHidden(event2 -> {
				MainController.this.dbTest();
				MainController.this.testBtnDB();
			});

		} catch (IOException e) {
			e.printStackTrace();
		}
	}



	//Path selection pane:
	public void selectPathPaneBtn() {

		try {
			Stage stage2 = new Stage();
			Parent rootPathSelectionPane;
			rootPathSelectionPane = FXMLLoader.load(PathSelection.class.getResource("/de/dominikemmel/reflowlab/controller/maincontrol/fxml/pathSelection.fxml"));

			stage2.setScene(new Scene(rootPathSelectionPane));
			stage2.show();
			stage2.setAlwaysOnTop(true);

			stage2.setOnCloseRequest(event1 -> {
				MainController.this.dbTest();
				MainController.this.testBtnDB();
			});

			stage2.setOnHidden(event2 -> {
				MainController.this.dbTest();
				MainController.this.testBtnDB();
			});

		} catch (IOException e) {
			e.printStackTrace();
		}
	}



	@FXML
	public void openConsole(MouseEvent event) {

		stageConsole.show();

	}
	
	public void startConsole() {
		try {
			Parent rootRagoneTool;
			rootRagoneTool = FXMLLoader.load(getClass().getResource("/de/dominikemmel/reflowlab/controller/maincontrol/fxml/consoleOverview.fxml"));
			
			stageConsole.setScene(new Scene(rootRagoneTool));
			stageConsole.initStyle(StageStyle.DECORATED);
//			stage.show();
			stageConsole.setTitle("Console");
			stageConsole.getIcons().add(new Image(getClass().getResourceAsStream("/de/dominikemmel/reflowlab/img/logo_simple/1x/logo_simple1x.png")));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}





