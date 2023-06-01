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

public class AddCostsSolventController implements javafx.fxml.Initializable {
	@FXML
	private AnchorPane addPane;
	@FXML
	private TextField inputSolvent;
	@FXML
	private TextField inputMSolvent;
	@FXML
	private TextField inputCSolvent;

	@FXML
	private TextField inputRefDOIMSolvent;
	@FXML
	private TextField inputRefMSolvent;
	@FXML
	private TextField inputRefDOICSolvent;
	@FXML
	private TextField inputRefCSolvent;
	
	@FXML
	private Button btnInputRefMSolvent; 
	
	String table = "costSolvent";
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Platform.runLater(new Runnable() {

            @Override
            public void run() {
            	inputSolvent.requestFocus();
            }
        });
		
		Image img = new Image(getClass().getResourceAsStream("/de/dominikemmel/reflowlab/img/ArrowDown/1x/arrowDownmdpi.png"));
		ImageView imgView = new ImageView(img);
		imgView.setFitHeight(10);
		imgView.setFitWidth(12);
		btnInputRefMSolvent.setGraphic(imgView);
		
		
		inputRefDOIMSolvent.textProperty().addListener((observable, oldValue, newValue) -> {
		    if(newValue == "" || newValue == null) {
		    	disableBtnInputRefMSolvent();
		    } else {
		    	enableBtnInputRefMSolvent();
		    	
				ObjReference objReference;
				try {
					objReference = ReferencesController.checkRef(newValue);

					if (objReference.Reference.get() != null) {
						inputRefMSolvent.setText(objReference.Reference.get());
					}
					
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
		    }
		});
		
		inputRefDOICSolvent.textProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue == "" || newValue == null) {

			} else {
				
				ObjReference objReference;
				try {
					objReference = ReferencesController.checkRef(newValue);	
					
					if (objReference.Reference.get() != null) {
						inputRefCSolvent.setText(objReference.Reference.get());
					}
					
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void enableBtnInputRefMSolvent() {
		btnInputRefMSolvent.setDisable(false);
		btnInputRefMSolvent.setVisible(true);
	}
	public void disableBtnInputRefMSolvent() {
		btnInputRefMSolvent.setDisable(true);
		btnInputRefMSolvent.setVisible(false);
	}
	
	@FXML
	public void btnInputRefMSolventEvent(ActionEvent event ) {
		inputRefDOICSolvent.setText(inputRefDOIMSolvent.getText());
		inputRefCSolvent.setText(inputRefMSolvent.getText());
	}

	// Event Listener on Button.onAction
	@FXML
	public void cancelAddSolvent(ActionEvent event) {
		//close Add-Pane:
		Stage stage = (Stage) addPane.getScene().getWindow();
		stage.close();
	}
	// Event Listener on Button.onAction
	@FXML
	public void addAddSolvent(ActionEvent event) throws ClassNotFoundException, SQLException {

		ObjCostsSolvent objCostAnalysisSolvent = new ObjCostsSolvent();

		String inputSolvent_Value = VariousMethods.getTextFieldInput(inputSolvent, "stringInput");
		Double inputMSolvent_Value = VariousMethods.getTextFieldInput(inputMSolvent, "doubleInput");
		Double inputCSolvent_Value = VariousMethods.getTextFieldInput(inputCSolvent, "doubleInput");


		objCostAnalysisSolvent.Solvent.set(inputSolvent_Value);
		objCostAnalysisSolvent.MSolvent.set(inputMSolvent_Value);
		objCostAnalysisSolvent.CSolvent.set(inputCSolvent_Value);

		try {
			Connection con = Database.getConnection("costSolvent");
			Statement state = con.createStatement();
			state.executeUpdate("INSERT INTO costSolvent(ID, Solvent, MSolvent, CSolvent, editDate)"
					+" VALUES(DEFAULT, '"+objCostAnalysisSolvent.Solvent.getValue()+"', "
							+objCostAnalysisSolvent.MSolvent.getValue()+", "
					+objCostAnalysisSolvent.CSolvent.getValue()+", CURRENT_TIMESTAMP)");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//Ref Input
		String inputRefDOIMSolvent_Value = VariousMethods.getTextFieldInput(inputRefDOIMSolvent, "stringInput");
		String inputRefMSolvent_Value = VariousMethods.getTextFieldInput(inputRefMSolvent, "stringInput");
		
		String inputRefDOICSolvent_Value = VariousMethods.getTextFieldInput(inputRefDOICSolvent, "stringInput");
		String inputRefCSolvent_Value = VariousMethods.getTextFieldInput(inputRefCSolvent, "stringInput");
		
		ObjReference objReferenceMSolvent = new ObjReference();
		objReferenceMSolvent.DOI.set(inputRefDOIMSolvent_Value);
		objReferenceMSolvent.Reference.set(inputRefMSolvent_Value);
		
		ObjReference objReferenceCSolvent = new ObjReference();
		objReferenceCSolvent.DOI.set(inputRefDOICSolvent_Value);
		objReferenceCSolvent.Reference.set(inputRefCSolvent_Value);
		
		objReferenceMSolvent = ReferencesController.updateRef("update", table, "Solvent",inputSolvent_Value, null, "RefIDMSolvent", objReferenceMSolvent);
		
		objReferenceCSolvent = ReferencesController.updateRef("update", table, "Solvent",inputSolvent_Value, null, "RefIDCSolvent", objReferenceCSolvent);
		
		
		//close Add-Pane:
		AddCostsSolventController.this.cancelAddSolvent(event);
	}
}
