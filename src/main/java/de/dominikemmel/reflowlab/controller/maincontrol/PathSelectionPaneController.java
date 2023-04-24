package de.dominikemmel.reflowlab.controller.maincontrol;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class PathSelectionPaneController extends MainController implements javafx.fxml.Initializable {

	@FXML
	Button btnSelectDB;
	@FXML
	Button btnCreateDB;
	@FXML
	TextFlow pathSelectionTextFlow;
	@FXML
	AnchorPane selectionPane;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		PathSelectionPaneController.this.addTextToTextFlow();
	}

	public void addTextToTextFlow() {

		Text text1 = new Text("No database found in directory: ");
		text1.setStyle("-fx-font-weight: bold");

        Properties prop = new Properties();
        String path = MainController.defaultInitSettings();
        File tempFile = new File(path);
        String pathDB = "(no path selected)";

        if (tempFile.exists()) {
            try (InputStream input = new FileInputStream(path)) {

                // load a properties file
                prop.load(input);

                pathDB = prop.getProperty("db.path");

            } catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

        }

		Text text2 = new Text(pathDB);
		text2.setStyle("-fx-font-weight: bold");
		text2.setStyle("-fx-font-style: italic");

		Text text3 = new Text(". Please, select an already existing database or select a path for creating a new one.");
		text3.setStyle("-fx-font-weight: bold");

		pathSelectionTextFlow.getChildren().addAll(text1,text2,text3);
	}


	@FXML
	public void btnSelectDB(ActionEvent event) {

	    Stage stage = (Stage) selectionPane.getScene().getWindow();
	    stage.setAlwaysOnTop(false);

		this.populatePropertyFileDbFile();
		this.closeSelectionPane(event);
	}

	@FXML
	public void btnCreateDB(ActionEvent event) {

	    Stage stage = (Stage) selectionPane.getScene().getWindow();
	    stage.setAlwaysOnTop(false);

		this.populatePropertyFileDbPath();
		this.closeSelectionPane(event);

	}

	@FXML
	private void closeSelectionPane(ActionEvent event) {
	    // get a handle to the stage
	    Stage stage = (Stage) selectionPane.getScene().getWindow();
	    // do what you have to do
	    stage.close();
	}
}
