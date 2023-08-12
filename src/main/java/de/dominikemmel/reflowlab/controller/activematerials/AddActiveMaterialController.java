package de.dominikemmel.reflowlab.controller.activematerials;

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

public class AddActiveMaterialController implements javafx.fxml.Initializable {

	@FXML private AnchorPane addPane;


	@FXML private TextField inputABBREVIATION;
	@FXML private TextField inputName;
	@FXML private TextField inputSTRUCTURALFORMULA;
	@FXML private TextField inputM;
	@FXML private TextField inputCategory;
	@FXML private TextField inputN;
	@FXML private TextField inputNumberH;
	@FXML private TextField inputCAM;
	@FXML private TextField inputSOLVENT;
	@FXML private TextField inputSalt;
	@FXML private TextField inputSaltc;
	@FXML private TextField inputPH;
	@FXML private TextField inputE;
	
	@FXML private TextField inputRefDOIN;
	@FXML private TextField inputRefN;
	@FXML private TextField inputRefDOINumberH;
	@FXML private TextField inputRefNumberH;
	@FXML private TextField inputRefDOICAM;
	@FXML private TextField inputRefCAM;
	@FXML private TextField inputRefDOIE;
	@FXML private TextField inputRefE;

	@FXML private Button btnAddActiveMaterial;
	@FXML private Button btnCancelActiveMaterial;
	
	@FXML private Button btnInputRefN;
	@FXML private Button btnInputRefNumberH;
	@FXML private Button btnInputRefCAM;
	
	String table = "activeMaterial";

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Platform.runLater(new Runnable() {

            @Override
            public void run() {
            	inputABBREVIATION.requestFocus();
            }
        });
		
		Image img = new Image(getClass().getResourceAsStream("/de/dominikemmel/reflowlab/img/ArrowDown/1x/arrowDownmdpi.png"));
		ImageView imgViewBtnInputRefN = new ImageView(img);
		imgViewBtnInputRefN.setFitHeight(10);
		imgViewBtnInputRefN.setFitWidth(12);
		
		ImageView imgViewBtnInputRefNumberH = new ImageView(img);
		imgViewBtnInputRefNumberH.setFitHeight(10);
		imgViewBtnInputRefNumberH.setFitWidth(12);
		
		ImageView imgViewBtnInputRefCAM = new ImageView(img);
		imgViewBtnInputRefCAM.setFitHeight(10);
		imgViewBtnInputRefCAM.setFitWidth(12);
		
		btnInputRefN.setGraphic(imgViewBtnInputRefN);
		btnInputRefNumberH.setGraphic(imgViewBtnInputRefNumberH);
		btnInputRefCAM.setGraphic(imgViewBtnInputRefCAM);
		
		inputRefDOIN.textProperty().addListener((observable, oldValue, newValue) -> {
		    if(newValue == "" || newValue == null) {
		    	disableBtn(btnInputRefN);
		    } else {
		    	enableBtn(btnInputRefN);
		    	
				ObjReference objReference;
				try {
					objReference = ReferencesController.checkRef(newValue);
					
					if (objReference.Reference.get() != null) {
						inputRefN.setText(objReference.Reference.get());
					}
					
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
		    }
		});
		
		inputRefDOINumberH.textProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue == "" || newValue == null) {
				disableBtn(btnInputRefNumberH);
			} else {
				enableBtn(btnInputRefNumberH);
				
				ObjReference objReference;
				try {
					objReference = ReferencesController.checkRef(newValue);
					
					if (objReference.Reference.get() != null) {
						inputRefNumberH.setText(objReference.Reference.get());
					}
					
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}
		});
		
		inputRefDOICAM.textProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue == "" || newValue == null) {
				disableBtn(btnInputRefCAM);
			} else {
				enableBtn(btnInputRefCAM);
				
				ObjReference objReference;
				try {
					objReference = ReferencesController.checkRef(newValue);
					
					if (objReference.Reference.get() != null) {
						inputRefCAM.setText(objReference.Reference.get());
					}
					
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}
		});
		
		inputRefDOIE.textProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue == "" || newValue == null) {

			} else {
				
				ObjReference objReference;
				try {
					objReference = ReferencesController.checkRef(newValue);
					
					if (objReference.Reference.get() != null) {
						inputRefE.setText(objReference.Reference.get());
					}
					
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void enableBtn(Button btn) {
		btn.setDisable(false);
		btn.setVisible(true);
	}
	public void disableBtn(Button btn) {
		btn.setDisable(true);
		btn.setVisible(false);
	}
	
	@FXML
	public void btnInputRefNEvent(ActionEvent event) {
		inputRefDOINumberH.setText(inputRefDOIN.getText());
		inputRefNumberH.setText(inputRefN.getText());
		inputRefDOICAM.setText(inputRefDOIN.getText());
		inputRefCAM.setText(inputRefN.getText());
		inputRefDOIE.setText(inputRefDOIN.getText());
		inputRefE.setText(inputRefN.getText());
	}
	
	@FXML
	public void btnInputRefNumberHEvent(ActionEvent event) {
		inputRefDOICAM.setText(inputRefDOINumberH.getText());
		inputRefCAM.setText(inputRefNumberH.getText());
		inputRefDOIE.setText(inputRefDOINumberH.getText());
		inputRefE.setText(inputRefNumberH.getText());
	}
	
	@FXML
	public void btnInputRefCAMEvent(ActionEvent event) {
		inputRefDOIE.setText(inputRefDOICAM.getText());
		inputRefE.setText(inputRefCAM.getText());
	}

public void btnAddActiveMaterial(ActionEvent event) throws ClassNotFoundException, SQLException {
	//Maybe also with some for-loop possible, combined with some if-statements for testing of object argument type?

	ObjActiveMaterial objActiveMaterial = new ObjActiveMaterial();

	String inputABBREVIATION_Value = VariousMethods.getTextFieldInput(inputABBREVIATION, "stringInput");
	String inputName_Value = VariousMethods.getTextFieldInput(inputName, "stringInput");
	String inputSTRUCTURALFORMULA_Value = VariousMethods.getTextFieldInput(inputSTRUCTURALFORMULA, "stringInput");
	Double inputM_Value = VariousMethods.getTextFieldInput(inputM, "doubleInput");
	String inputCategory_Value = VariousMethods.getTextFieldInput(inputCategory, "stringInput");
	Integer inputN_Value = VariousMethods.getTextFieldInput(inputN, "integerInput");
	Integer inputNumberH_Value = VariousMethods.getTextFieldInput(inputNumberH, "integerInput");
	Double inputCAM_Value = VariousMethods.getTextFieldInput(inputCAM, "doubleInput");
	String inputSOLVENT_Value = VariousMethods.getTextFieldInput(inputSOLVENT, "stringInput");
	String inputSalt_Value = VariousMethods.getTextFieldInput(inputSalt, "stringInput");
	Double inputSaltc_Value = VariousMethods.getTextFieldInput(inputSaltc, "doubleInput");
	Double inputPH_Value = VariousMethods.getTextFieldInput(inputPH, "doubleInput");
	Double inputE_Value = VariousMethods.getTextFieldInput(inputE, "doubleInput");

//	Instant dateTime = Instant.now();
//	DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-mm-dd HH:mm:ss.SS").withZone(ZoneId.of("Europe/Berlin" ));
//	String dateTimeValue = pattern.format(dateTime).toString();

	objActiveMaterial.ABBREVIATION.set(inputABBREVIATION_Value);
	objActiveMaterial.NAME.set(inputName_Value);
	objActiveMaterial.STRUCTURALFORMULA.set(inputSTRUCTURALFORMULA_Value);
	objActiveMaterial.M.set(inputM_Value);
	objActiveMaterial.Category.set(inputCategory_Value);
	objActiveMaterial.N.set(inputN_Value);
	objActiveMaterial.NumberH.set(inputNumberH_Value);
	objActiveMaterial.CAM.set(inputCAM_Value);
	objActiveMaterial.SOLVENT.set(inputSOLVENT_Value);
	objActiveMaterial.Salt.set(inputSalt_Value);
	objActiveMaterial.SaltC.set(inputSaltc_Value);
	objActiveMaterial.PH.set(inputPH_Value);
	objActiveMaterial.E.set(inputE_Value);

	try {
		Connection con = Database.getConnection("activeMaterial");
		Statement state = con.createStatement();
		state.executeUpdate("INSERT INTO activeMaterial(ID, Abbreviation, Name, StructuralFormula, M, Category, n, NumberH, CAM, Solvent, Salt, Saltc, pH, E, editDate)"
				+" VALUES(DEFAULT, '"+objActiveMaterial.ABBREVIATION.getValue()+"', '"
				+objActiveMaterial.NAME.getValue()+"', '"+objActiveMaterial.STRUCTURALFORMULA.getValue()+"', "
				+objActiveMaterial.M.getValue()+", '"+objActiveMaterial.Category.getValue()+"', "+objActiveMaterial.N.getValue()+", "+objActiveMaterial.NumberH.getValue()+", "
				+objActiveMaterial.CAM.getValue()+", '"+objActiveMaterial.SOLVENT.getValue()+"', '"
				+objActiveMaterial.Salt.getValue()+"', "
				+objActiveMaterial.SaltC.getValue()+", "+objActiveMaterial.PH.getValue()+", "
				+objActiveMaterial.E.getValue()+", CURRENT_TIMESTAMP)");

	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	//Ref Input
	String inputRefDOIN_Value = VariousMethods.getTextFieldInput(inputRefDOIN, "stringInput");
	String inputRefN_Value = VariousMethods.getTextFieldInput(inputRefN, "stringInput");
	
	String inputRefDOINumberH_Value = VariousMethods.getTextFieldInput(inputRefDOINumberH, "stringInput");
	String inputRefNumberH_Value = VariousMethods.getTextFieldInput(inputRefNumberH, "stringInput");
	
	String inputRefDOICAM_Value = VariousMethods.getTextFieldInput(inputRefDOICAM, "stringInput");
	String inputRefCAM_Value = VariousMethods.getTextFieldInput(inputRefCAM, "stringInput");
	
	String inputRefDOIE_Value = VariousMethods.getTextFieldInput(inputRefDOIE, "stringInput");
	String inputRefE_Value = VariousMethods.getTextFieldInput(inputRefE, "stringInput");
	
	ObjReference objReferenceN = new ObjReference();
	objReferenceN.DOI.set(inputRefDOIN_Value);
	objReferenceN.Reference.set(inputRefN_Value);
	
	ObjReference objReferenceNumberH = new ObjReference();
	objReferenceNumberH.DOI.set(inputRefDOINumberH_Value);
	objReferenceNumberH.Reference.set(inputRefNumberH_Value);
	
	ObjReference objReferenceCAM = new ObjReference();
	objReferenceCAM.DOI.set(inputRefDOICAM_Value);
	objReferenceCAM.Reference.set(inputRefCAM_Value);
	
	ObjReference objReferenceE = new ObjReference();
	objReferenceE.DOI.set(inputRefDOIE_Value);
	objReferenceE.Reference.set(inputRefE_Value);
	
	objReferenceN = ReferencesController.updateRef("update", table, "Abbreviation",inputABBREVIATION_Value, null, "RefIDn", objReferenceN);
	
	objReferenceNumberH = ReferencesController.updateRef("update", table, "Abbreviation",inputABBREVIATION_Value, null, "RefIDNumberH", objReferenceNumberH);
	
	objReferenceCAM = ReferencesController.updateRef("update", table, "Abbreviation",inputABBREVIATION_Value, null, "RefIDCAM", objReferenceCAM);
	
	objReferenceE = ReferencesController.updateRef("update", table, "Abbreviation",inputABBREVIATION_Value, null, "RefIDE", objReferenceE);
	

	//close Add-Pane:
	AddActiveMaterialController.this.btnCancelActiveMaterial(event);

}

public void btnCancelActiveMaterial(ActionEvent event) {
	//close Add-Pane:
	Stage stage = (Stage) addPane.getScene().getWindow();
	stage.close();
}

}
