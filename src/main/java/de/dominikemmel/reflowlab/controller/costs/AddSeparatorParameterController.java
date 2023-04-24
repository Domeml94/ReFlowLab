package de.dominikemmel.reflowlab.controller.costs;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import de.dominikemmel.reflowlab.Database;
import de.dominikemmel.reflowlab.VariousMethods;
import de.dominikemmel.reflowlab.controller.references.ObjReference;
import de.dominikemmel.reflowlab.controller.references.ReferencesController;

public class AddSeparatorParameterController implements javafx.fxml.Initializable {
	@FXML
	private AnchorPane addPane;
	@FXML
	private TextField inputSeparator;
	@FXML
	private TextField inputASR;

	@FXML
	private TextField inputRefDOIASR;
	@FXML
	private TextField inputRefASR;
	
	String table = "separatorParameter";
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Platform.runLater(new Runnable() {

            @Override
            public void run() {
            	inputSeparator.requestFocus();
            }
        });
		
		inputRefDOIASR.textProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue == "" || newValue == null) {

			} else {
				
				ObjReference objReference;
				try {
					objReference = ReferencesController.checkRef(newValue);
					
					if (objReference.Reference.get() != null) {
						inputRefASR.setText(objReference.Reference.get());
					}
					
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Event Listener on Button.onAction
	@FXML
	public void cancelAddSeparatorParameter(ActionEvent event) {
		//close Add-Pane:
		Stage stage = (Stage) addPane.getScene().getWindow();
		stage.close();
	}
	// Event Listener on Button.onAction
	@FXML
	public void addAddSeparatorParameter(ActionEvent event) throws ClassNotFoundException, SQLException {

		ObjSeparatorParameter objSeparatorParameter = new ObjSeparatorParameter();

		String inputSeparator_Value = VariousMethods.getTextFieldInput(inputSeparator, "stringInput");
		Double inputASR_Value = VariousMethods.getTextFieldInput(inputASR, "doubleInput");


		objSeparatorParameter.SeparatorName.set(inputSeparator_Value);
		objSeparatorParameter.Rasr.set(inputASR_Value);

		try {
			Connection con = Database.getConnection("separatorParameter");
			Statement state = con.createStatement();
			state.executeUpdate("INSERT INTO separatorParameter(ID, SeparatorName, Rasr, editDate)"
					+" VALUES(DEFAULT, '"+objSeparatorParameter.SeparatorName.getValue()+"', "
							+objSeparatorParameter.Rasr.getValue()+", CURRENT_TIMESTAMP)");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//Ref Input
		String inputRefDOIASR_Value = VariousMethods.getTextFieldInput(inputRefDOIASR, "stringInput");
		String inputRefASR_Value = VariousMethods.getTextFieldInput(inputRefASR, "stringInput");
		
		ObjReference objReference = new ObjReference();
		objReference.DOI.set(inputRefDOIASR_Value);
		objReference.Reference.set(inputRefASR_Value);
		
		objReference = ReferencesController.updateRef("update", table, "SeparatorName",inputSeparator_Value, null, "RefIDRasr", objReference);
		
		
		//close Add-Pane:
		AddSeparatorParameterController.this.cancelAddSeparatorParameter(event);
	}
}
