package de.dominikemmel.reflowlab.controller.electrolytes;

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

public class EditElectrolytesController implements javafx.fxml.Initializable {

	//passing selected data from ElectrolyteController:
	private ObservableList<ObjElectrolytes> selection;

	@FXML
	private AnchorPane editPane;

	@FXML
	private TextField inputActiveMaterial;
	@FXML
	private TextField inputSolvent;
	@FXML
	private TextField inputSalt;
	@FXML
	private TextField inputcSalt;
	@FXML
	private TextField inputpH;
	@FXML
	private TextField inputmaxSolubility;
	@FXML
	private TextField inputDOx;
	@FXML
	private TextField inputDRed;
	@FXML
	private TextField inputkOx;
	@FXML
	private TextField inputAlphaOx;
	@FXML
	private TextField inputkRed;
	@FXML
	private TextField inputAlphaRed;
	@FXML
	private TextField inputdegRate;
	@FXML
	private TextField inputf;
	
	@FXML
	private TextField inputRefDOIMaxSolubility;
	@FXML
	private TextField inputRefMaxSolubility;
	@FXML
	private TextField inputRefDOIDOx;
	@FXML
	private TextField inputRefDOx;
	@FXML
	private TextField inputRefDOIDRed;
	@FXML
	private TextField inputRefDRed;
	@FXML
	private TextField inputRefDOIkOx;
	@FXML
	private TextField inputRefkOx;
	@FXML
	private TextField inputRefDOIAlphaOx;
	@FXML
	private TextField inputRefAlphaOx;
	@FXML
	private TextField inputRefDOIkRed;
	@FXML
	private TextField inputRefkRed;
	@FXML
	private TextField inputRefDOIAlphaRed;
	@FXML
	private TextField inputRefAlphaRed;
	@FXML
	private TextField inputRefDOIdegRate;
	@FXML
	private TextField inputRefdegRate;
	@FXML
	private TextField inputRefDOIF;
	@FXML
	private TextField inputRefF;
	
	@FXML
	private Button btnInputRefMaxSolubility; 
	@FXML
	private Button btnInputRefDOx; 
	@FXML
	private Button btnInputRefDRed; 
	@FXML
	private Button btnInputRefkOx; 
	@FXML
	private Button btnInputRefAlphaOx; 
	@FXML
	private Button btnInputRefkRed; 
	@FXML
	private Button btnInputRefAlphaRed; 
	@FXML
	private Button btnInputRefdegRate; 
	@FXML
	private Button btnInputRefF; 
	
	String table = "electrolyte";

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Platform.runLater(new Runnable() {

            @Override
            public void run() {
            	inputActiveMaterial.requestFocus();
            }
        });
		
		
		Image img = new Image(getClass().getResourceAsStream("/de/dominikemmel/reflowlab/img/ArrowDown/1x/arrowDownmdpi.png"));
		ImageView imgViewBtnInputRefMaxSolubility = new ImageView(img);
		imgViewBtnInputRefMaxSolubility.setFitHeight(10);
		imgViewBtnInputRefMaxSolubility.setFitWidth(12);
		
		ImageView imgViewBtnInputRefDOx = new ImageView(img);
		imgViewBtnInputRefDOx.setFitHeight(10);
		imgViewBtnInputRefDOx.setFitWidth(12);
		
		ImageView imgViewBtnInputRefDRed = new ImageView(img);
		imgViewBtnInputRefDRed.setFitHeight(10);
		imgViewBtnInputRefDRed.setFitWidth(12);
		
		ImageView imgViewBtnInputRefkOx = new ImageView(img);
		imgViewBtnInputRefkOx.setFitHeight(10);
		imgViewBtnInputRefkOx.setFitWidth(12);
		
		ImageView imgViewBtnInputRefAlphaOx = new ImageView(img);
		imgViewBtnInputRefAlphaOx.setFitHeight(10);
		imgViewBtnInputRefAlphaOx.setFitWidth(12);
		
		ImageView imgViewBtnInputRefkRed = new ImageView(img);
		imgViewBtnInputRefkRed.setFitHeight(10);
		imgViewBtnInputRefkRed.setFitWidth(12);
		
		ImageView imgViewBtnInputRefAlphaRed = new ImageView(img);
		imgViewBtnInputRefAlphaRed.setFitHeight(10);
		imgViewBtnInputRefAlphaRed.setFitWidth(12);
		
		ImageView imgViewBtnInputRefdegRate = new ImageView(img);
		imgViewBtnInputRefdegRate.setFitHeight(10);
		imgViewBtnInputRefdegRate.setFitWidth(12);
		
		ImageView imgViewBtnInputRefF = new ImageView(img);
		imgViewBtnInputRefF.setFitHeight(10);
		imgViewBtnInputRefF.setFitWidth(12);
		
		btnInputRefMaxSolubility.setGraphic(imgViewBtnInputRefMaxSolubility);
		btnInputRefDOx.setGraphic(imgViewBtnInputRefDOx);
		btnInputRefDRed.setGraphic(imgViewBtnInputRefDRed);
		btnInputRefkOx.setGraphic(imgViewBtnInputRefkOx);
		btnInputRefAlphaOx.setGraphic(imgViewBtnInputRefAlphaOx);
		btnInputRefkRed.setGraphic(imgViewBtnInputRefkRed);
		btnInputRefAlphaRed.setGraphic(imgViewBtnInputRefAlphaRed);
		btnInputRefdegRate.setGraphic(imgViewBtnInputRefdegRate);
		btnInputRefF.setGraphic(imgViewBtnInputRefF);
		
		
		inputRefDOIMaxSolubility.textProperty().addListener((observable, oldValue, newValue) -> {
		    if(newValue == "" || newValue == null) {
		    	disableBtn(btnInputRefMaxSolubility);
		    } else {
		    	enableBtn(btnInputRefMaxSolubility);
		    	
				ObjReference objReference;
				try {
					objReference = ReferencesController.checkRef(newValue);
					
					inputRefMaxSolubility.setText(objReference.Reference.get());
					
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
		    }
		});
		inputRefDOIDOx.textProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue == "" || newValue == null) {
				disableBtn(btnInputRefDOx);
			} else {
				enableBtn(btnInputRefDOx);
				
				ObjReference objReference;
				try {
					objReference = ReferencesController.checkRef(newValue);
					
					inputRefDOx.setText(objReference.Reference.get());
					
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}
		});
		inputRefDOIDRed.textProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue == "" || newValue == null) {
				disableBtn(btnInputRefDRed);
			} else {
				enableBtn(btnInputRefDRed);
				
				ObjReference objReference;
				try {
					objReference = ReferencesController.checkRef(newValue);
					
					inputRefDRed.setText(objReference.Reference.get());
					
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}
		});
		inputRefDOIkOx.textProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue == "" || newValue == null) {
				disableBtn(btnInputRefkOx);
			} else {
				enableBtn(btnInputRefkOx);
				
				ObjReference objReference;
				try {
					objReference = ReferencesController.checkRef(newValue);
					
					inputRefkOx.setText(objReference.Reference.get());
					
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}
		});
		
		inputRefDOIAlphaOx.textProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue == "" || newValue == null) {

			} else {
				
				ObjReference objReference;
				try {
					objReference = ReferencesController.checkRef(newValue);
					
					inputRefAlphaOx.setText(objReference.Reference.get());
					
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}
		});
		
		inputRefDOIkRed.textProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue == "" || newValue == null) {
				disableBtn(btnInputRefkRed);
			} else {
				enableBtn(btnInputRefkRed);
				
				ObjReference objReference;
				try {
					objReference = ReferencesController.checkRef(newValue);
					
					inputRefkRed.setText(objReference.Reference.get());
					
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}
		});
		
		inputRefDOIAlphaRed.textProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue == "" || newValue == null) {

			} else {
				
				ObjReference objReference;
				try {
					objReference = ReferencesController.checkRef(newValue);
					
					inputRefAlphaRed.setText(objReference.Reference.get());
					
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}
		});
		
		inputRefDOIdegRate.textProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue == "" || newValue == null) {
				disableBtn(btnInputRefdegRate);
			} else {
				enableBtn(btnInputRefdegRate);
				
				ObjReference objReference;
				try {
					objReference = ReferencesController.checkRef(newValue);
					
					inputRefdegRate.setText(objReference.Reference.get());
					
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}
		});
		
		inputRefDOIF.textProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue == "" || newValue == null) {

			} else {
				
				ObjReference objReference;
				try {
					objReference = ReferencesController.checkRef(newValue);
					
					inputRefF.setText(objReference.Reference.get());
					
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
	public void btnInputRefMaxSolubilityEvent(ActionEvent event) {
		inputRefDOIDOx.setText(inputRefDOIMaxSolubility.getText());
		inputRefDOx.setText(inputRefMaxSolubility.getText());
		inputRefDOIDRed.setText(inputRefDOIMaxSolubility.getText());
		inputRefDRed.setText(inputRefMaxSolubility.getText());
		inputRefDOIkOx.setText(inputRefDOIMaxSolubility.getText());
		inputRefkOx.setText(inputRefMaxSolubility.getText());
		inputRefDOIAlphaOx.setText(inputRefDOIMaxSolubility.getText());
		inputRefAlphaOx.setText(inputRefMaxSolubility.getText());
		inputRefDOIkRed.setText(inputRefDOIMaxSolubility.getText());
		inputRefkRed.setText(inputRefMaxSolubility.getText());
		inputRefDOIAlphaRed.setText(inputRefDOIMaxSolubility.getText());
		inputRefAlphaRed.setText(inputRefMaxSolubility.getText());
		inputRefDOIdegRate.setText(inputRefDOIMaxSolubility.getText());
		inputRefdegRate.setText(inputRefMaxSolubility.getText());
		inputRefDOIF.setText(inputRefDOIMaxSolubility.getText());
		inputRefF.setText(inputRefMaxSolubility.getText());
	}
	
	@FXML
	public void btnInputRefDOxEvent(ActionEvent event) {
		inputRefDOIkOx.setText(inputRefDOIDOx.getText());
		inputRefkOx.setText(inputRefDOx.getText());
		inputRefDOIAlphaOx.setText(inputRefDOIDOx.getText());
		inputRefAlphaOx.setText(inputRefDOx.getText());
		inputRefDOIdegRate.setText(inputRefDOIDOx.getText());
		inputRefdegRate.setText(inputRefDOx.getText());
		inputRefDOIF.setText(inputRefDOIDOx.getText());
		inputRefF.setText(inputRefDOx.getText());
	}
	
	@FXML
	public void btnInputRefDRedEvent(ActionEvent event) {
		inputRefDOIkRed.setText(inputRefDOIDRed.getText());
		inputRefkRed.setText(inputRefDRed.getText());
		inputRefDOIAlphaRed.setText(inputRefDOIDRed.getText());
		inputRefAlphaRed.setText(inputRefDRed.getText());
		inputRefDOIdegRate.setText(inputRefDOIDRed.getText());
		inputRefdegRate.setText(inputRefDRed.getText());
		inputRefDOIF.setText(inputRefDOIDRed.getText());
		inputRefF.setText(inputRefDRed.getText());
	}
	
	@FXML
	public void btnInputRefkOxEvent(ActionEvent event) {
		inputRefDOIAlphaOx.setText(inputRefDOIkOx.getText());
		inputRefAlphaOx.setText(inputRefkOx.getText());
	}
	
	@FXML
	public void btnInputRefkRedEvent(ActionEvent event) {
		inputRefDOIAlphaRed.setText(inputRefDOIkRed.getText());
		inputRefAlphaRed.setText(inputRefkRed.getText());
	}
	
	@FXML
	public void btnInputRefdegRateEvent(ActionEvent event) {
		inputRefDOIF.setText(inputRefDOIdegRate.getText());
		inputRefF.setText(inputRefdegRate.getText());
	}
	


	public void setSelectionData(ObservableList<ObjElectrolytes> selection) throws ClassNotFoundException, SQLException {
		this.selection = selection;

		inputActiveMaterial.setText(selection.get(0).ActiveMaterial.getValue());
		inputSolvent.setText(selection.get(0).Solvent.getValue());
		inputSalt.setText(selection.get(0).Salt.getValue());
		inputcSalt.setText(selection.get(0).cSalt.getValue().toString());
		inputpH.setText(selection.get(0).pH.getValue().toString());
		inputmaxSolubility.setText(selection.get(0).maxSolubility.getValue().toString());
		inputDOx.setText(selection.get(0).DOx.getValue().toString());
		inputDRed.setText(selection.get(0).DRed.getValue().toString());
		inputkOx.setText(selection.get(0).kOx.getValue().toString());
		inputAlphaOx.setText(selection.get(0).AlphaOx.getValue().toString());
		inputkRed.setText(selection.get(0).kRed.getValue().toString());
		inputAlphaRed.setText(selection.get(0).AlphaRed.getValue().toString());
		inputdegRate.setText(selection.get(0).degRate.getValue().toString());
		inputf.setText(selection.get(0).f.getValue().toString());
		
		ObjReference objReferenceMaxSolubility = ReferencesController.checkRefID(selection.get(0).RefIDmaxSolubility.getValue());
		
		inputRefDOIMaxSolubility.setText(objReferenceMaxSolubility.DOI.get());
		inputRefMaxSolubility.setText(objReferenceMaxSolubility.Reference.get());
		
		
		ObjReference objReferenceDOx = ReferencesController.checkRefID(selection.get(0).RefIDDOx.getValue());
		
		inputRefDOIDOx.setText(objReferenceDOx.DOI.get());
		inputRefDOx.setText(objReferenceDOx.Reference.get());
		
		
		ObjReference objReferenceDRed = ReferencesController.checkRefID(selection.get(0).RefIDDRed.getValue());
		
		inputRefDOIDRed.setText(objReferenceDRed.DOI.get());
		inputRefDRed.setText(objReferenceDRed.Reference.get());
		
		
		ObjReference objReferencekOx = ReferencesController.checkRefID(selection.get(0).RefIDkOx.getValue());
		
		inputRefDOIkOx.setText(objReferencekOx.DOI.get());
		inputRefkOx.setText(objReferencekOx.Reference.get());
		
		
		ObjReference objReferenceAlphaOx = ReferencesController.checkRefID(selection.get(0).RefIDAlphaOx.getValue());
		
		inputRefDOIAlphaOx.setText(objReferenceAlphaOx.DOI.get());
		inputRefAlphaOx.setText(objReferenceAlphaOx.Reference.get());
		
		
		ObjReference objReferencekRed = ReferencesController.checkRefID(selection.get(0).RefIDkRed.getValue());
		
		inputRefDOIkRed.setText(objReferencekRed.DOI.get());
		inputRefkRed.setText(objReferencekRed.Reference.get());
		
		
		ObjReference objReferenceAlphaRed = ReferencesController.checkRefID(selection.get(0).RefIDAlphaRed.getValue());
		
		inputRefDOIAlphaRed.setText(objReferenceAlphaRed.DOI.get());
		inputRefAlphaRed.setText(objReferenceAlphaRed.Reference.get());
		
		
		ObjReference objReferenceDegRate = ReferencesController.checkRefID(selection.get(0).RefIDdegRate.getValue());
		
		inputRefDOIdegRate.setText(objReferenceDegRate.DOI.get());
		inputRefdegRate.setText(objReferenceDegRate.Reference.get());
		
		
		ObjReference objReferenceF = ReferencesController.checkRefID(selection.get(0).RefIDf.getValue());
		
		inputRefDOIF.setText(objReferenceF.DOI.get());
		inputRefF.setText(objReferenceF.Reference.get());

	}




	// Event Listener on Button.onAction
	@FXML
	public void cancelEditElectrolytes(ActionEvent event) {
		Stage stage = (Stage) editPane.getScene().getWindow();
		stage.close();
	}
	// Event Listener on Button.onAction
	@FXML
	public void editEditElectrolytes(ActionEvent event) throws ClassNotFoundException, SQLException {
		ObjElectrolytes objElectrolytes = new ObjElectrolytes();

		String inputActiveMaterial_Value = VariousMethods.getTextFieldInput(inputActiveMaterial, "stringInput");
		String inputSolvent_Value = VariousMethods.getTextFieldInput(inputSolvent, "stringInput");
		String inputSalt_Value = VariousMethods.getTextFieldInput(inputSalt, "stringInput");
		Double inputcSalt_Value = VariousMethods.getTextFieldInput(inputcSalt, "doubleInput");
		Double inputpH_Value = VariousMethods.getTextFieldInput(inputpH, "doubleInput");
		Double inputmaxSolubility_Value = VariousMethods.getTextFieldInput(inputmaxSolubility, "doubleInput");
		Double inputDOx_Value = VariousMethods.getTextFieldInput(inputDOx, "doubleInput");
		Double inputDRed_Value = VariousMethods.getTextFieldInput(inputDRed, "doubleInput");
		Double inputkOx_Value = VariousMethods.getTextFieldInput(inputkOx, "doubleInput");
		Double inputAlphaOx_Value = VariousMethods.getTextFieldInput(inputAlphaOx, "doubleInput");
		Double inputkRed_Value = VariousMethods.getTextFieldInput(inputkRed, "doubleInput");
		Double inputAlphaRed_Value = VariousMethods.getTextFieldInput(inputAlphaRed, "doubleInput");
		Double inputdegRate_Value = VariousMethods.getTextFieldInput(inputdegRate, "doubleInput");
		Double inputf_Value = VariousMethods.getTextFieldInput(inputf, "doubleInput");

		objElectrolytes.ActiveMaterial.set(inputActiveMaterial_Value);
		objElectrolytes.Solvent.set(inputSolvent_Value);
		objElectrolytes.Salt.set(inputSalt_Value);
		objElectrolytes.cSalt.set(inputcSalt_Value);
		objElectrolytes.pH.set(inputpH_Value);
		objElectrolytes.maxSolubility.set(inputmaxSolubility_Value);
		objElectrolytes.DOx.set(inputDOx_Value);
		objElectrolytes.DRed.set(inputDRed_Value);
		objElectrolytes.kOx.set(inputkOx_Value);
		objElectrolytes.AlphaOx.set(inputAlphaOx_Value);
		objElectrolytes.kRed.set(inputkRed_Value);
		objElectrolytes.AlphaRed.set(inputAlphaRed_Value);
		objElectrolytes.degRate.set(inputdegRate_Value);
		objElectrolytes.f.set(inputf_Value);

		try {
			Connection con = Database.getConnection("electrolyte");
			Statement state = con.createStatement();
			state.executeUpdate("UPDATE electrolyte "
					+ "SET ID = "+selection.get(0).ID.getValue()+
					", ActiveMaterial = '"+objElectrolytes.ActiveMaterial.getValue()+"', Solvent = '"
					+objElectrolytes.Solvent.getValue()+
					"', Salt = '"+objElectrolytes.Salt.getValue()+
					"', cSalt = "+objElectrolytes.cSalt.getValue()+", pH = "+objElectrolytes.pH.getValue()+
					", maxSolubility = "+objElectrolytes.maxSolubility.getValue()
					+", DOx = "+objElectrolytes.DOx.getValue()
					+", DRed = "+objElectrolytes.DRed.getValue()
					+", kOx = "+objElectrolytes.kOx.getValue()
					+", AlphaOx = "+objElectrolytes.AlphaOx.getValue()
					+", kRed = "+objElectrolytes.kRed.getValue()
					+", AlphaRed = "+objElectrolytes.AlphaRed.getValue()
					+", f = "+objElectrolytes.f.getValue()
					+", degRate = "+objElectrolytes.degRate.getValue()+", editDate = CURRENT_TIMESTAMP"
							+ " WHERE ID = "+selection.get(0).ID.getValue()+"");

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		//Ref Input
		String inputRefDOIMaxSolubility_Value = VariousMethods.getTextFieldInput(inputRefDOIMaxSolubility, "stringInput");
		String inputRefMaxSolubility_Value = VariousMethods.getTextFieldInput(inputRefMaxSolubility, "stringInput");
		
		String inputRefDOIDOx_Value = VariousMethods.getTextFieldInput(inputRefDOIDOx, "stringInput");
		String inputRefDOx_Value = VariousMethods.getTextFieldInput(inputRefDOx, "stringInput");
		
		String inputRefDOIDRed_Value = VariousMethods.getTextFieldInput(inputRefDOIDRed, "stringInput");
		String inputRefDRed_Value = VariousMethods.getTextFieldInput(inputRefDRed, "stringInput");
		
		String inputRefDOIkOx_Value = VariousMethods.getTextFieldInput(inputRefDOIkOx, "stringInput");
		String inputRefkOx_Value = VariousMethods.getTextFieldInput(inputRefkOx, "stringInput");
		
		String inputRefDOIAlphaOx_Value = VariousMethods.getTextFieldInput(inputRefDOIAlphaOx, "stringInput");
		String inputRefAlphaOx_Value = VariousMethods.getTextFieldInput(inputRefAlphaOx, "stringInput");
		
		String inputRefDOIkRed_Value = VariousMethods.getTextFieldInput(inputRefDOIkRed, "stringInput");
		String inputRefkRed_Value = VariousMethods.getTextFieldInput(inputRefkRed, "stringInput");
		
		String inputRefDOIAlphaRed_Value = VariousMethods.getTextFieldInput(inputRefDOIAlphaRed, "stringInput");
		String inputRefAlphaRed_Value = VariousMethods.getTextFieldInput(inputRefAlphaRed, "stringInput");
		
		String inputRefDOIdegRate_Value = VariousMethods.getTextFieldInput(inputRefDOIdegRate, "stringInput");
		String inputRefdegRate_Value = VariousMethods.getTextFieldInput(inputRefdegRate, "stringInput");
		
		String inputRefDOIF_Value = VariousMethods.getTextFieldInput(inputRefDOIF, "stringInput");
		String inputRefF_Value = VariousMethods.getTextFieldInput(inputRefF, "stringInput");
		
		ObjReference objReferenceMaxSolubility = new ObjReference();
		objReferenceMaxSolubility.DOI.set(inputRefDOIMaxSolubility_Value);
		objReferenceMaxSolubility.Reference.set(inputRefMaxSolubility_Value);
		
		ObjReference objReferenceDOx = new ObjReference();
		objReferenceDOx.DOI.set(inputRefDOIDOx_Value);
		objReferenceDOx.Reference.set(inputRefDOx_Value);
		
		ObjReference objReferenceDRed = new ObjReference();
		objReferenceDRed.DOI.set(inputRefDOIDRed_Value);
		objReferenceDRed.Reference.set(inputRefDRed_Value);
		
		ObjReference objReferencekOx = new ObjReference();
		objReferencekOx.DOI.set(inputRefDOIkOx_Value);
		objReferencekOx.Reference.set(inputRefkOx_Value);
		
		ObjReference objReferenceAlphaOx = new ObjReference();
		objReferenceAlphaOx.DOI.set(inputRefDOIAlphaOx_Value);
		objReferenceAlphaOx.Reference.set(inputRefAlphaOx_Value);
		
		ObjReference objReferencekRed = new ObjReference();
		objReferencekRed.DOI.set(inputRefDOIkRed_Value);
		objReferencekRed.Reference.set(inputRefkRed_Value);
		
		ObjReference objReferenceAlphaRed = new ObjReference();
		objReferenceAlphaRed.DOI.set(inputRefDOIAlphaRed_Value);
		objReferenceAlphaRed.Reference.set(inputRefAlphaRed_Value);
		
		ObjReference objReferenceDegRate = new ObjReference();
		objReferenceDegRate.DOI.set(inputRefDOIdegRate_Value);
		objReferenceDegRate.Reference.set(inputRefdegRate_Value);
		
		ObjReference objReferenceF = new ObjReference();
		objReferenceF.DOI.set(inputRefDOIF_Value);
		objReferenceF.Reference.set(inputRefF_Value);
		
		objReferenceMaxSolubility = ReferencesController.updateRef("update", table, "ActiveMaterial",inputActiveMaterial_Value, selection.get(0).ActiveMaterial.getValue(), "RefIDmaxSolubility", objReferenceMaxSolubility);
		
		objReferenceMaxSolubility = ReferencesController.updateRef("update", table, "ActiveMaterial",inputActiveMaterial_Value, selection.get(0).ActiveMaterial.getValue(), "RefIDDOx", objReferenceDOx);
		
		objReferenceMaxSolubility = ReferencesController.updateRef("update", table, "ActiveMaterial",inputActiveMaterial_Value, selection.get(0).ActiveMaterial.getValue(), "RefIDDRed", objReferenceDRed);
		
		objReferenceMaxSolubility = ReferencesController.updateRef("update", table, "ActiveMaterial",inputActiveMaterial_Value, selection.get(0).ActiveMaterial.getValue(), "RefIDkOx", objReferencekOx);
		
		objReferenceMaxSolubility = ReferencesController.updateRef("update", table, "ActiveMaterial",inputActiveMaterial_Value, selection.get(0).ActiveMaterial.getValue(), "RefIDAlphaOx", objReferenceAlphaOx);
		
		objReferenceMaxSolubility = ReferencesController.updateRef("update", table, "ActiveMaterial",inputActiveMaterial_Value, selection.get(0).ActiveMaterial.getValue(), "RefIDkRed", objReferencekRed);
		
		objReferenceMaxSolubility = ReferencesController.updateRef("update", table, "ActiveMaterial",inputActiveMaterial_Value, selection.get(0).ActiveMaterial.getValue(), "RefIDAlphaRed", objReferenceAlphaRed);
		
		objReferenceMaxSolubility = ReferencesController.updateRef("update", table, "ActiveMaterial",inputActiveMaterial_Value, selection.get(0).ActiveMaterial.getValue(), "RefIDdegRate", objReferenceDegRate);
		
		objReferenceMaxSolubility = ReferencesController.updateRef("update", table, "ActiveMaterial",inputActiveMaterial_Value, selection.get(0).ActiveMaterial.getValue(), "RefIDf", objReferenceF);

		
		//close Edit-Pane:
		EditElectrolytesController.this.cancelEditElectrolytes(event);
	}
	@FXML
	public void deleteEditElectrolytes(ActionEvent event) throws ClassNotFoundException, SQLException {
		String inputActiveMaterial_Value = VariousMethods.getTextFieldInput(inputActiveMaterial, "stringInput");
		
		//Ref Input
		String inputRefDOIMaxSolubility_Value = VariousMethods.getTextFieldInput(inputRefDOIMaxSolubility, "stringInput");
		String inputRefMaxSolubility_Value = VariousMethods.getTextFieldInput(inputRefMaxSolubility, "stringInput");
		
		String inputRefDOIDOx_Value = VariousMethods.getTextFieldInput(inputRefDOIDOx, "stringInput");
		String inputRefDOx_Value = VariousMethods.getTextFieldInput(inputRefDOx, "stringInput");
		
		String inputRefDOIDRed_Value = VariousMethods.getTextFieldInput(inputRefDOIDRed, "stringInput");
		String inputRefDRed_Value = VariousMethods.getTextFieldInput(inputRefDRed, "stringInput");
		
		String inputRefDOIkOx_Value = VariousMethods.getTextFieldInput(inputRefDOIkOx, "stringInput");
		String inputRefkOx_Value = VariousMethods.getTextFieldInput(inputRefkOx, "stringInput");
		
		String inputRefDOIAlphaOx_Value = VariousMethods.getTextFieldInput(inputRefDOIAlphaOx, "stringInput");
		String inputRefAlphaOx_Value = VariousMethods.getTextFieldInput(inputRefAlphaOx, "stringInput");
		
		String inputRefDOIkRed_Value = VariousMethods.getTextFieldInput(inputRefDOIkRed, "stringInput");
		String inputRefkRed_Value = VariousMethods.getTextFieldInput(inputRefkRed, "stringInput");
		
		String inputRefDOIAlphaRed_Value = VariousMethods.getTextFieldInput(inputRefDOIAlphaRed, "stringInput");
		String inputRefAlphaRed_Value = VariousMethods.getTextFieldInput(inputRefAlphaRed, "stringInput");
		
		String inputRefDOIdegRate_Value = VariousMethods.getTextFieldInput(inputRefDOIdegRate, "stringInput");
		String inputRefdegRate_Value = VariousMethods.getTextFieldInput(inputRefdegRate, "stringInput");
		
		String inputRefDOIF_Value = VariousMethods.getTextFieldInput(inputRefDOIF, "stringInput");
		String inputRefF_Value = VariousMethods.getTextFieldInput(inputRefF, "stringInput");
		
		ObjReference objReferenceMaxSolubility = new ObjReference();
		objReferenceMaxSolubility.DOI.set(inputRefDOIMaxSolubility_Value);
		objReferenceMaxSolubility.Reference.set(inputRefMaxSolubility_Value);
		
		ObjReference objReferenceDOx = new ObjReference();
		objReferenceDOx.DOI.set(inputRefDOIDOx_Value);
		objReferenceDOx.Reference.set(inputRefDOx_Value);
		
		ObjReference objReferenceDRed = new ObjReference();
		objReferenceDRed.DOI.set(inputRefDOIDRed_Value);
		objReferenceDRed.Reference.set(inputRefDRed_Value);
		
		ObjReference objReferencekOx = new ObjReference();
		objReferencekOx.DOI.set(inputRefDOIkOx_Value);
		objReferencekOx.Reference.set(inputRefkOx_Value);
		
		ObjReference objReferenceAlphaOx = new ObjReference();
		objReferenceAlphaOx.DOI.set(inputRefDOIAlphaOx_Value);
		objReferenceAlphaOx.Reference.set(inputRefAlphaOx_Value);
		
		ObjReference objReferencekRed = new ObjReference();
		objReferencekRed.DOI.set(inputRefDOIkRed_Value);
		objReferencekRed.Reference.set(inputRefkRed_Value);
		
		ObjReference objReferenceAlphaRed = new ObjReference();
		objReferenceAlphaRed.DOI.set(inputRefDOIAlphaRed_Value);
		objReferenceAlphaRed.Reference.set(inputRefAlphaRed_Value);
		
		ObjReference objReferenceDegRate = new ObjReference();
		objReferenceDegRate.DOI.set(inputRefDOIdegRate_Value);
		objReferenceDegRate.Reference.set(inputRefdegRate_Value);
		
		ObjReference objReferenceF = new ObjReference();
		objReferenceF.DOI.set(inputRefDOIF_Value);
		objReferenceF.Reference.set(inputRefF_Value);
		
		objReferenceMaxSolubility = ReferencesController.updateRef("delete", table, "ActiveMaterial",inputActiveMaterial_Value, selection.get(0).ActiveMaterial.getValue(), "RefIDmaxSolubility", objReferenceMaxSolubility);
		
		objReferenceMaxSolubility = ReferencesController.updateRef("delete", table, "ActiveMaterial",inputActiveMaterial_Value, selection.get(0).ActiveMaterial.getValue(), "RefIDDOx", objReferenceDOx);
		
		objReferenceMaxSolubility = ReferencesController.updateRef("delete", table, "ActiveMaterial",inputActiveMaterial_Value, selection.get(0).ActiveMaterial.getValue(), "RefIDDRed", objReferenceDRed);
		
		objReferenceMaxSolubility = ReferencesController.updateRef("delete", table, "ActiveMaterial",inputActiveMaterial_Value, selection.get(0).ActiveMaterial.getValue(), "RefIDkOx", objReferencekOx);
		
		objReferenceMaxSolubility = ReferencesController.updateRef("delete", table, "ActiveMaterial",inputActiveMaterial_Value, selection.get(0).ActiveMaterial.getValue(), "RefIDAlphaOx", objReferenceAlphaOx);
		
		objReferenceMaxSolubility = ReferencesController.updateRef("delete", table, "ActiveMaterial",inputActiveMaterial_Value, selection.get(0).ActiveMaterial.getValue(), "RefIDkRed", objReferencekRed);
		
		objReferenceMaxSolubility = ReferencesController.updateRef("delete", table, "ActiveMaterial",inputActiveMaterial_Value, selection.get(0).ActiveMaterial.getValue(), "RefIDAlphaRed", objReferenceAlphaRed);
		
		objReferenceMaxSolubility = ReferencesController.updateRef("delete", table, "ActiveMaterial",inputActiveMaterial_Value, selection.get(0).ActiveMaterial.getValue(), "RefIDdegRate", objReferenceDegRate);
		
		objReferenceMaxSolubility = ReferencesController.updateRef("delete", table, "ActiveMaterial",inputActiveMaterial_Value, selection.get(0).ActiveMaterial.getValue(), "RefIDf", objReferenceF);


		try {
			Connection con = Database.getConnection("electrolyte");
			Statement state = con.createStatement();
			state.executeUpdate("DELETE FROM electrolyte WHERE ID = "+selection.get(0).ID.getValue()+"");

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		//close Edit-Pane:
		EditElectrolytesController.this.cancelEditElectrolytes(event);
	}

}
