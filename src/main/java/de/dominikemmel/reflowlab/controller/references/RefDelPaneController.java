package de.dominikemmel.reflowlab.controller.references;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.ResourceBundle;

import de.dominikemmel.reflowlab.Database;
import de.dominikemmel.reflowlab.controller.maincontrol.MainController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class RefDelPaneController extends MainController implements javafx.fxml.Initializable {

	@FXML
	Button btnYesDel;
	@FXML
	Button btnNoCancel;
	@FXML
	TextFlow refTextFlow;
	@FXML
	TextFlow refTextFlowQuestion;
	@FXML
	AnchorPane refDelPane;
	
	//passing selected data from ReferenceController:
	private ObservableList<ObjReference> selection;
	
	private String DOI = null;
	private String Bound = null;
	private String Comment = null;
	private Integer ID = null;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	public void setSelectionData(ObservableList<ObjReference> selection) {
		this.selection = selection;
		
		DOI = selection.get(0).DOI.getValue();
	    Bound = selection.get(0).Bound.getValue();
		Comment = selection.get(0).Comment.getValue();
		ID = selection.get(0).ID.getValue();
		
		RefDelPaneController.this.addTextToTextFlow();
	}
	
	public void addTextToTextFlow() {

		Text text1 = new Text("The selected reference is already linked to the following input values: \n \n");
		text1.setStyle("-fx-font-weight: bold");

		Text text2 = new Text(this.Comment);
		text2.setStyle("-fx-font-weight: bold");
		text2.setStyle("-fx-font-style: italic");

		Text text3 = new Text("Are you sure you want to delete it?");
		text3.setStyle("-fx-font-weight: bold");
		text3.setStyle("-fx-font-size: 16");

		refTextFlow.getChildren().addAll(text1,text2);

		refTextFlowQuestion.getChildren().addAll(text3);
		
		refTextFlow.autosize();
		refDelPane.autosize();
	}


	@FXML
	public void btnYesDelEvent(ActionEvent event) {
		try {
			Connection con = Database.getConnection("reference");
			Statement state = con.createStatement();
			state.executeUpdate("DELETE FROM reference WHERE ID = "+ID);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		this.closeSelectionPane(event);
	}

	@FXML
	public void btnNoCancelEvent(ActionEvent event) {

		this.closeSelectionPane(event);
	}

	@FXML
	private void closeSelectionPane(ActionEvent event) {
	    Stage stage = (Stage) refDelPane.getScene().getWindow();
	    stage.close();
	}
}
