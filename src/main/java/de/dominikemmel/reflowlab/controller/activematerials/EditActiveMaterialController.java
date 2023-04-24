package de.dominikemmel.reflowlab.controller.activematerials;

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

public class EditActiveMaterialController implements javafx.fxml.Initializable  {

	@FXML private AnchorPane editPane;

	@FXML private TextField inputABBREVIATION;
	@FXML private TextField inputName;
	@FXML private TextField inputSTRUCTURALFORMULA;
	@FXML private TextField inputM;
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
	@FXML private Button btnEditActiveMaterial;
	
	@FXML private Button btnInputRefN;
	@FXML private Button btnInputRefNumberH;
	@FXML private Button btnInputRefCAM;

	String table = "activeMaterial";
	
	//passing selected data from ActiveMaterialsController:
	private ObservableList<ObjActiveMaterial> selection;

	public void setSelectionData(ObservableList<ObjActiveMaterial> selection) throws ClassNotFoundException, SQLException {
		this.selection = selection;

		inputABBREVIATION.setText(selection.get(0).ABBREVIATION.getValue());
		inputName.setText(selection.get(0).NAME.getValue());
		inputSTRUCTURALFORMULA.setText(selection.get(0).STRUCTURALFORMULA.getValue());
		inputM.setText(selection.get(0).M.getValue().toString());
		inputN.setText(selection.get(0).N.getValue().toString());
		inputNumberH.setText(selection.get(0).NumberH.getValue().toString());
		inputCAM.setText(selection.get(0).CAM.getValue().toString());
		inputSOLVENT.setText(selection.get(0).SOLVENT.getValue());
		inputSalt.setText(selection.get(0).Salt.getValue());
		inputSaltc.setText(selection.get(0).SaltC.getValue().toString());
		inputPH.setText(selection.get(0).PH.getValue().toString());
		inputE.setText(selection.get(0).E.getValue().toString());
		
		
		ObjReference objReferenceN = ReferencesController.checkRefID(selection.get(0).RefIDn.getValue());
		
		inputRefDOIN.setText(objReferenceN.DOI.get());
		inputRefN.setText(objReferenceN.Reference.get());
		
		ObjReference objReferenceNumberH = ReferencesController.checkRefID(selection.get(0).RefIDNumberH.getValue());
		
		inputRefDOINumberH.setText(objReferenceNumberH.DOI.get());
		inputRefNumberH.setText(objReferenceNumberH.Reference.get());
		
		ObjReference objReferenceCAM = ReferencesController.checkRefID(selection.get(0).RefIDCAM.getValue());
		
		inputRefDOICAM.setText(objReferenceCAM.DOI.get());
		inputRefCAM.setText(objReferenceCAM.Reference.get());
		
		ObjReference objReferenceE = ReferencesController.checkRefID(selection.get(0).RefIDE.getValue());
		
		inputRefDOIE.setText(objReferenceE.DOI.get());
		inputRefE.setText(objReferenceE.Reference.get());
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Platform.runLater(new Runnable() {

            @Override
            public void run() {
            	inputABBREVIATION.requestFocus();
            }
        });
		
		Image img = new Image(getClass().getResourceAsStream("/de/dominikemmel/reflowlab/controller/maincontrol/img/arrowDown.png"));
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

					inputRefN.setText(objReference.Reference.get());
					
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

					inputRefNumberH.setText(objReference.Reference.get());
					
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
					
					inputRefCAM.setText(objReference.Reference.get());
					
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
					
					inputRefE.setText(objReference.Reference.get());
					
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
	
	public void btnEditActiveMaterial(ActionEvent event) throws ClassNotFoundException, SQLException {

		ObjActiveMaterial objActiveMaterial = new ObjActiveMaterial();

		String inputABBREVIATION_Value = VariousMethods.getTextFieldInput(inputABBREVIATION, "stringInput");
		String inputName_Value = VariousMethods.getTextFieldInput(inputName, "stringInput");
		String inputSTRUCTURALFORMULA_Value = VariousMethods.getTextFieldInput(inputSTRUCTURALFORMULA, "stringInput");
		Double inputM_Value = VariousMethods.getTextFieldInput(inputM, "doubleInput");
		Integer inputN_Value = VariousMethods.getTextFieldInput(inputN, "integerInput");
		Integer inputNumberH_Value = VariousMethods.getTextFieldInput(inputNumberH, "integerInput");
		Double inputCAM_Value = VariousMethods.getTextFieldInput(inputCAM, "doubleInput");
		String inputSOLVENT_Value = VariousMethods.getTextFieldInput(inputSOLVENT, "stringInput");
		String inputSalt_Value = VariousMethods.getTextFieldInput(inputSalt, "stringInput");
		Double inputSaltc_Value = VariousMethods.getTextFieldInput(inputSaltc, "doubleInput");
		Double inputPH_Value = VariousMethods.getTextFieldInput(inputPH, "doubleInput");
		Double inputE_Value = VariousMethods.getTextFieldInput(inputE, "doubleInput");

		objActiveMaterial.ABBREVIATION.set(inputABBREVIATION_Value);
		objActiveMaterial.NAME.set(inputName_Value);
		objActiveMaterial.STRUCTURALFORMULA.set(inputSTRUCTURALFORMULA_Value);
		objActiveMaterial.M.set(inputM_Value);
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
			state.executeUpdate("UPDATE activeMaterial "
					+ "SET ID = "+selection.get(0).ID.getValue()+
					", Abbreviation = '"+objActiveMaterial.ABBREVIATION.getValue()+"', Name = '"+objActiveMaterial.NAME.getValue()+
					"', StructuralFormula = '"+objActiveMaterial.STRUCTURALFORMULA.getValue()+"', M = "+objActiveMaterial.M.getValue()+
					", n = "+objActiveMaterial.N.getValue()+", NumberH = "+objActiveMaterial.NumberH.getValue()+
					", CAM = "+objActiveMaterial.CAM.getValue()+", Solvent = '"+objActiveMaterial.SOLVENT.getValue()+
					"', Salt = '"+objActiveMaterial.Salt.getValue()+
					"', Saltc = "+objActiveMaterial.SaltC.getValue()+", pH = "
					+objActiveMaterial.PH.getValue()+", E = "+objActiveMaterial.E.getValue()+", editDate = CURRENT_TIMESTAMP"
							+ " WHERE ID = "+selection.get(0).ID.getValue()+"");

		} catch (ClassNotFoundException | SQLException e) {
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
		
		objReferenceN = ReferencesController.updateRef("update", table, "Abbreviation",inputABBREVIATION_Value, selection.get(0).ABBREVIATION.getValue(), "RefIDn", objReferenceN);
		
		objReferenceNumberH = ReferencesController.updateRef("update", table, "Abbreviation",inputABBREVIATION_Value, selection.get(0).ABBREVIATION.getValue(), "RefIDNumberH", objReferenceNumberH);
		
		objReferenceCAM = ReferencesController.updateRef("update", table, "Abbreviation",inputABBREVIATION_Value, selection.get(0).ABBREVIATION.getValue(), "RefIDCAM", objReferenceCAM);
		
		objReferenceE = ReferencesController.updateRef("update", table, "Abbreviation",inputABBREVIATION_Value, selection.get(0).ABBREVIATION.getValue(), "RefIDE", objReferenceE);
		
		
		//close Edit-Pane:
		EditActiveMaterialController.this.btnCancelActiveMaterial(event);
	}

	public void btnCancelActiveMaterial(ActionEvent event) {
		Stage stage = (Stage) editPane.getScene().getWindow();
		stage.close();
	}

	public void btnDeleteActiveMaterial(ActionEvent event) throws ClassNotFoundException, SQLException {
		
		String inputABBREVIATION_Value = VariousMethods.getTextFieldInput(inputABBREVIATION, "stringInput");
		
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
		
		objReferenceN = ReferencesController.updateRef("delete", table, "Abbreviation",inputABBREVIATION_Value, selection.get(0).ABBREVIATION.getValue(), "RefIDn", objReferenceN);
		
		objReferenceNumberH = ReferencesController.updateRef("delete", table, "Abbreviation",inputABBREVIATION_Value, selection.get(0).ABBREVIATION.getValue(), "RefIDNumberH", objReferenceNumberH);
		
		objReferenceCAM = ReferencesController.updateRef("delete", table, "Abbreviation",inputABBREVIATION_Value, selection.get(0).ABBREVIATION.getValue(), "RefIDCAM", objReferenceCAM);
		
		objReferenceE = ReferencesController.updateRef("delete", table, "Abbreviation",inputABBREVIATION_Value, selection.get(0).ABBREVIATION.getValue(), "RefIDE", objReferenceE);
		

		try {
			Connection con = Database.getConnection("activeMaterial");
			Statement state = con.createStatement();
			state.executeUpdate("DELETE FROM activeMaterial WHERE ID = "+selection.get(0).ID.getValue()+"");

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		//close Edit-Pane:
		EditActiveMaterialController.this.btnCancelActiveMaterial(event);


	}

}
