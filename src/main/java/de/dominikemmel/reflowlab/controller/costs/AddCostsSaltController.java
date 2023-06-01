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

public class AddCostsSaltController implements javafx.fxml.Initializable {
	@FXML
	private AnchorPane addPane;
	@FXML
	private TextField inputSalt;
	@FXML
	private TextField inputMSalt;
	@FXML
	private TextField inputCSalt;
	
	@FXML
	private TextField inputRefDOIMSalt;
	@FXML
	private TextField inputRefMSalt;
	@FXML
	private TextField inputRefDOICSalt;
	@FXML
	private TextField inputRefCSalt;
	
	@FXML
	private Button btnInputRefMSalt; 

	String table = "costSalt";
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Platform.runLater(new Runnable() {

            @Override
            public void run() {
            	inputSalt.requestFocus();
            }
        });

		Image img = new Image(getClass().getResourceAsStream("/de/dominikemmel/reflowlab/img/ArrowDown/1x/arrowDownmdpi.png"));
		ImageView imgView = new ImageView(img);
		imgView.setFitHeight(10);
		imgView.setFitWidth(12);
		btnInputRefMSalt.setGraphic(imgView);
		
		
		inputRefDOIMSalt.textProperty().addListener((observable, oldValue, newValue) -> {
		    if(newValue == "" || newValue == null) {
		    	disableBtnInputRefMSalt();
		    } else {
		    	enableBtnInputRefMSalt();
		    	
				ObjReference objReference;
				try {
					objReference = ReferencesController.checkRef(newValue);

					if (objReference.Reference.get() != null) {
						inputRefMSalt.setText(objReference.Reference.get());
					}
					
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
		    }
		});
		
		inputRefDOICSalt.textProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue == "" || newValue == null) {

			} else {
				
				ObjReference objReference;
				try {
					objReference = ReferencesController.checkRef(newValue);
					
					if (objReference.Reference.get() != null) {
						inputRefCSalt.setText(objReference.Reference.get());
					}
					
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
	
	public void enableBtnInputRefMSalt() {
		btnInputRefMSalt.setDisable(false);
		btnInputRefMSalt.setVisible(true);
	}
	public void disableBtnInputRefMSalt() {
		btnInputRefMSalt.setDisable(true);
		btnInputRefMSalt.setVisible(false);
	}
	
	@FXML
	public void btnInputRefMSaltEvent(ActionEvent event ) {
		inputRefDOICSalt.setText(inputRefDOIMSalt.getText());
		inputRefCSalt.setText(inputRefMSalt.getText());
	}
	
	
	// Event Listener on Button.onAction
	@FXML
	public void cancelAddSalt(ActionEvent event) {
		//close Add-Pane:
		Stage stage = (Stage) addPane.getScene().getWindow();
		stage.close();
	}
	// Event Listener on Button.onAction
	@FXML
	public void addAddSalt(ActionEvent event) throws ClassNotFoundException, SQLException {
		ObjCostsSalt objCostsSalt = new ObjCostsSalt();

		String inputSalt_Value = VariousMethods.getTextFieldInput(inputSalt, "stringInput");
		Double inputMSalt_Value = VariousMethods.getTextFieldInput(inputMSalt, "doubleInput");
		Double inputCSalt_Value = VariousMethods.getTextFieldInput(inputCSalt, "doubleInput");


		objCostsSalt.Salt.set(inputSalt_Value);
		objCostsSalt.MSalt.set(inputMSalt_Value);
		objCostsSalt.CSalt.set(inputCSalt_Value);

		try {
			Connection con = Database.getConnection("costSalt");
			Statement state = con.createStatement();
			state.executeUpdate("INSERT INTO costSalt(ID, Salt, MSalt, CSalt, editDate)"
					+" VALUES(DEFAULT, '"+objCostsSalt.Salt.getValue()+"', "
					+objCostsSalt.MSalt.getValue()+", "
					+objCostsSalt.CSalt.getValue()+", CURRENT_TIMESTAMP)");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		//Ref Input
		String inputRefDOIMSalt_Value = VariousMethods.getTextFieldInput(inputRefDOIMSalt, "stringInput");
		String inputRefMSalt_Value = VariousMethods.getTextFieldInput(inputRefMSalt, "stringInput");
		
		String inputRefDOICSalt_Value = VariousMethods.getTextFieldInput(inputRefDOICSalt, "stringInput");
		String inputRefCSalt_Value = VariousMethods.getTextFieldInput(inputRefCSalt, "stringInput");
		
		ObjReference objReferenceMSalt = new ObjReference();
		objReferenceMSalt.DOI.set(inputRefDOIMSalt_Value);
		objReferenceMSalt.Reference.set(inputRefMSalt_Value);
		
		ObjReference objReferenceCSalt = new ObjReference();
		objReferenceCSalt.DOI.set(inputRefDOICSalt_Value);
		objReferenceCSalt.Reference.set(inputRefCSalt_Value);
		
		objReferenceMSalt = ReferencesController.updateRef("update", table, "Salt",inputSalt_Value, null, "RefIDMSalt", objReferenceMSalt);
		
		if (inputRefDOIMSalt_Value != inputRefDOICSalt_Value) {
			objReferenceCSalt = ReferencesController.updateRef("update", table, "Salt",inputSalt_Value, null, "RefIDCSalt", objReferenceCSalt);
		}
		
		//close Add-Pane:
		AddCostsSaltController.this.cancelAddSalt(event);
	}
}
