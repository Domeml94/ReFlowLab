package de.dominikemmel.reflowlab.controller.costs;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.ObservableList;
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

public class EditSeparatorParameterController implements javafx.fxml.Initializable {


	//passing selected data from SeperatorParameterController:
	private ObservableList<ObjSeparatorParameter> selection;

	@FXML
	private AnchorPane editPane;
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
					
					inputRefASR.setText(objReference.Reference.get());
					
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	public void setSelectionData(ObservableList<ObjSeparatorParameter> selection) throws ClassNotFoundException, SQLException {
		this.selection = selection;

		inputSeparator.setText(selection.get(0).SeparatorName.getValue());
		inputASR.setText(selection.get(0).Rasr.getValue().toString());
		
		ObjReference objReference = ReferencesController.checkRefID(selection.get(0).RefIDRasr.getValue());
		
		inputRefDOIASR.setText(objReference.DOI.get());
		inputRefASR.setText(objReference.Reference.get());
	}

	// Event Listener on Button.onAction
	@FXML
	public void cancelEditSeparatorParameter(ActionEvent event) {
		Stage stage = (Stage) editPane.getScene().getWindow();
		stage.close();
	}
	// Event Listener on Button.onAction
	@FXML
	public void editEditSeparatorParameter(ActionEvent event) throws ClassNotFoundException, SQLException {

		ObjSeparatorParameter objSeparatorParameter = new ObjSeparatorParameter();

		String inputSeparator_Value = VariousMethods.getTextFieldInput(inputSeparator, "stringInput");
		Double inputASR_Value = VariousMethods.getTextFieldInput(inputASR, "doubleInput");

		objSeparatorParameter.SeparatorName.set(inputSeparator_Value);
		objSeparatorParameter.Rasr.set(inputASR_Value);

		try {
			Connection con = Database.getConnection("separatorParameter");
			Statement state = con.createStatement();
			state.executeUpdate("UPDATE separatorParameter "
					+ "SET ID = "+selection.get(0).ID.getValue()+
					", SeparatorName = '"+objSeparatorParameter.SeparatorName.getValue()+"', Rasr = "+objSeparatorParameter.Rasr.getValue()+
					", editDate = CURRENT_TIMESTAMP"
							+ " WHERE ID = "+selection.get(0).ID.getValue()+"");

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		
		//Ref Input
		String inputRefDOIASR_Value = VariousMethods.getTextFieldInput(inputRefDOIASR, "stringInput");
		String inputRefASR_Value = VariousMethods.getTextFieldInput(inputRefASR, "stringInput");
		
		ObjReference objReference = new ObjReference();
		objReference.DOI.set(inputRefDOIASR_Value);
		objReference.Reference.set(inputRefASR_Value);
		
		objReference = ReferencesController.updateRef("update", table, "SeparatorName",inputSeparator_Value, selection.get(0).SeparatorName.getValue(), "RefIDRasr", objReference);
		

		//close Edit-Pane:
		EditSeparatorParameterController.this.cancelEditSeparatorParameter(event);
	}
	// Event Listener on Button.onAction

	@FXML
	public void deleteEditSeparatorParameter(ActionEvent event) throws ClassNotFoundException, SQLException {
		String inputSeparator_Value = VariousMethods.getTextFieldInput(inputSeparator, "stringInput");
		
		//Ref Input
		String inputRefDOIASR_Value = VariousMethods.getTextFieldInput(inputRefDOIASR, "stringInput");
		String inputRefASR_Value = VariousMethods.getTextFieldInput(inputRefASR, "stringInput");
		
		ObjReference objReference = new ObjReference();
		objReference.DOI.set(inputRefDOIASR_Value);
		objReference.Reference.set(inputRefASR_Value);
		
		objReference = ReferencesController.updateRef("delete", table, "SeparatorName",inputSeparator_Value, selection.get(0).SeparatorName.getValue(), "RefIDRasr", objReference);
		
		try {
			Connection con = Database.getConnection("separatorParameter");
			Statement state = con.createStatement();
			state.executeUpdate("DELETE FROM separatorParameter WHERE ID = "+selection.get(0).ID.getValue()+"");

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		//close Edit-Pane:
		EditSeparatorParameterController.this.cancelEditSeparatorParameter(event);

	}

}
