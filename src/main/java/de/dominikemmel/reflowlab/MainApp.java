package de.dominikemmel.reflowlab;

import java.awt.Color;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;



public class MainApp extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {

		Parent root = FXMLLoader.load(getClass().getResource("/de/dominikemmel/reflowlab/controller/maincontrol/fxml/main.fxml"));
		
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/de/dominikemmel/reflowlab/style/reflowlabStyle1.css").toExternalForm());
		primaryStage.initStyle(StageStyle.DECORATED);
		
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setTitle("ReFlowLab");
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/de/dominikemmel/reflowlab/img/logo_simple/1x/logo_simple1x.png")));
		
//		MainController.dbTest();

//		Database.createConnection("activeMaterial");
//		Database.createConnection("solvent");
//		Database.createConnection("electrolyte");
//		Database.createConnection("costAnalysis");
//		Database.createConnection("reference");


	}

	public static void main(String[] args) throws Exception {

		launch(args);
	}
}
