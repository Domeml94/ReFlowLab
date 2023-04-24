package de.dominikemmel.reflowlab.controller.references;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import de.dominikemmel.reflowlab.Database;
import de.dominikemmel.reflowlab.VariousMethods;

public class AddReferencesController implements javafx.fxml.Initializable {
	@FXML
	private AnchorPane addPane;
	@FXML
	private TextArea inputDOI;
	@FXML
	private TextArea inputReference;

	int inputnumber = 1;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Platform.runLater(new Runnable() {

            @Override
            public void run() {
            	inputReference.requestFocus();
            }
        });
		
		addPane.autosize();
	}

	// Event Listener on Button.onAction
	@FXML
	public void cancelAddReferences(ActionEvent event) {
		//close Add-Pane:
		Stage stage = (Stage) addPane.getScene().getWindow();
		stage.close();
	}
	// Event Listener on Button.onAction
	@FXML
	public void addAddReferences(ActionEvent event) {
		ObjReference objReference = new ObjReference();

		String inputDOI_Value = VariousMethods.getTextAreaInput(inputDOI);
		String inputReference_Value = VariousMethods.getTextAreaInput(inputReference);


		objReference.DOI.set(inputDOI_Value);
		objReference.Reference.set(inputReference_Value);


		try {
			Connection con = Database.getConnection("reference");
			Statement state = con.createStatement();
			state.executeUpdate("INSERT INTO reference(ID, DOI, Reference, editDate)"
					+" VALUES(DEFAULT, '"+objReference.DOI.getValue()+"', '"+objReference.Reference.getValue()+"', CURRENT_TIMESTAMP)");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		//close Add-Pane:
		AddReferencesController.this.cancelAddReferences(event);
	}

}
