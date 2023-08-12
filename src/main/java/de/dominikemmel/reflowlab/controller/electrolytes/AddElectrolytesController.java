package de.dominikemmel.reflowlab.controller.electrolytes;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import de.dominikemmel.reflowlab.Database;
import de.dominikemmel.reflowlab.MyConstants;
import de.dominikemmel.reflowlab.VariousMethods;
import de.dominikemmel.reflowlab.controller.costanalysistool.CostAnalysisToolController;
import de.dominikemmel.reflowlab.controller.references.ObjReference;
import de.dominikemmel.reflowlab.controller.references.ReferencesController;

public class AddElectrolytesController implements javafx.fxml.Initializable {
	@FXML private AnchorPane addPane;

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
	private TextField inputTheoMaxCap;
	@FXML
	private TextField inputNote;
	@FXML
	private TextField inputfEloVol;
	@FXML
	private TextField inputfConc;
	@FXML
	private CheckBox inputfSymCellCycl;
	
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
	
	@FXML
	private Button btnCalcQmax; 
	
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
					
					if (objReference.Reference.get() != null) {
						inputRefMaxSolubility.setText(objReference.Reference.get());
					}
					
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
					
					if (objReference.Reference.get() != null) {
						inputRefDOx.setText(objReference.Reference.get());
					}
					
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
					
					if (objReference.Reference.get() != null) {
						inputRefDRed.setText(objReference.Reference.get());
					}
					
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
					
					if (objReference.Reference.get() != null) {
						inputRefkOx.setText(objReference.Reference.get());
					}
					
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
					
					if (objReference.Reference.get() != null) {
						inputRefAlphaOx.setText(objReference.Reference.get());
					}
					
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
					
					if (objReference.Reference.get() != null) {
						inputRefkRed.setText(objReference.Reference.get());
					}
					
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
					
					if (objReference.Reference.get() != null) {
						inputRefAlphaRed.setText(objReference.Reference.get());
					}
					
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
					
					if (objReference.Reference.get() != null) {
						inputRefdegRate.setText(objReference.Reference.get());
					}
					
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
					
					if (objReference.Reference.get() != null) {
						inputRefF.setText(objReference.Reference.get());
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
	
	@FXML
	public void btnCalcQmaxEvent(ActionEvent event) {
		
		double vol = Double.valueOf(inputfEloVol.getText()) * Math.pow(10, -3); 
		double conc = Double.valueOf(inputfConc.getText()); 
		
		int numberEl = 0;
		
		try {
			
			ResultSet res = Database.selectData("activeMaterial");

			ObservableList<Integer> dataActiveMaterial = FXCollections.observableArrayList();

			while (res.next()) {
				String sqlAbbrev = res.getString("ABBREVIATION");
				
				if (sqlAbbrev.equals(inputActiveMaterial.getText())) {
					int dataNumberEl = res.getInt("N");
					dataActiveMaterial.add(dataNumberEl);
				}
			}

			if (!dataActiveMaterial.isEmpty()) {
				numberEl = dataActiveMaterial.get(0).intValue();
			}
 

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		// theo. max. capacity / mAh
		double maxCap = 0;
		if (numberEl > 0) {
			maxCap = MyConstants.F * vol * conc * numberEl * Math.pow(10, 3) / (60 * 60);
		}
		
		inputTheoMaxCap.setText(String.valueOf(maxCap));
	}
	
	
	// Event Listener on Button.onAction
	@FXML
	public void cancelAddElectrolytes(ActionEvent event) {
		//close Add-Pane:
		Stage stage = (Stage) addPane.getScene().getWindow();
		stage.close();
	}
	// Event Listener on Button.onAction
	@FXML
	public void addAddElectrolytes(ActionEvent event) throws ClassNotFoundException, SQLException {
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
		Double inputTheoMaxCap_Value = VariousMethods.getTextFieldInput(inputTheoMaxCap, "doubleInput");
		String inputNote_Value = VariousMethods.getTextFieldInput(inputNote, "stringInput");
		Double inputfEloVol_Value = VariousMethods.getTextFieldInput(inputfEloVol, "doubleInput");
		Double inputfConc_Value = VariousMethods.getTextFieldInput(inputfConc, "doubleInput");
		Boolean inputfSymCellCycl_Value = inputfSymCellCycl.isSelected();

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
		objElectrolytes.theoMaxCap.set(inputTheoMaxCap_Value);
		objElectrolytes.note.set(inputNote_Value);
		objElectrolytes.fEloVol.set(inputfEloVol_Value);
		objElectrolytes.fConc.set(inputfConc_Value);
		objElectrolytes.fSymCellCycl.set(inputfSymCellCycl_Value);
		
		double fSymCellCycl_sql = 0;
		if (inputfSymCellCycl_Value) {
			fSymCellCycl_sql = 1;
		} else {
			fSymCellCycl_sql = 0;
		}

		try {
			Connection con = Database.getConnection("electrolyte");
			Statement state = con.createStatement();
			state.executeUpdate("INSERT INTO electrolyte(ID, ActiveMaterial, Solvent, Salt, cSalt, pH, maxSolubility, DOx, DRed, kOx, AlphaOx, kRed, AlphaRed, degRate, f, theoMaxCap, fEloVol, fConc, note, fSymCellCycl, editDate)"
					+" VALUES(DEFAULT, '"+objElectrolytes.ActiveMaterial.getValue()+"', '"
					+objElectrolytes.Solvent.getValue()+"', '"+objElectrolytes.Salt.getValue()+"', "
					+objElectrolytes.cSalt.getValue()+", "
					+objElectrolytes.pH.getValue()+", "+objElectrolytes.maxSolubility.getValue()+", "
					+objElectrolytes.DOx.getValue()+", "+objElectrolytes.DRed.getValue()+", "+objElectrolytes.kOx.getValue()+", "
					+objElectrolytes.AlphaOx.getValue()+", "+objElectrolytes.kRed.getValue()+", "+objElectrolytes.AlphaRed.getValue()+", "
					+objElectrolytes.degRate.getValue()+", "+objElectrolytes.f.getValue()+", "+objElectrolytes.theoMaxCap.getValue()+", "+objElectrolytes.fEloVol.getValue()+", "+objElectrolytes.fConc.getValue()+", '"+objElectrolytes.note.getValue()+"', "+fSymCellCycl_sql+", CURRENT_TIMESTAMP)");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
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
		
		objReferenceMaxSolubility = ReferencesController.updateRef("update", table, "ActiveMaterial",inputActiveMaterial_Value, null, "RefIDmaxSolubility", objReferenceMaxSolubility);
		
		objReferenceMaxSolubility = ReferencesController.updateRef("update", table, "ActiveMaterial",inputActiveMaterial_Value, null, "RefIDDOx", objReferenceDOx);
		
		objReferenceMaxSolubility = ReferencesController.updateRef("update", table, "ActiveMaterial",inputActiveMaterial_Value, null, "RefIDDRed", objReferenceDRed);
		
		objReferenceMaxSolubility = ReferencesController.updateRef("update", table, "ActiveMaterial",inputActiveMaterial_Value, null, "RefIDkOx", objReferencekOx);
		
		objReferenceMaxSolubility = ReferencesController.updateRef("update", table, "ActiveMaterial",inputActiveMaterial_Value, null, "RefIDAlphaOx", objReferenceAlphaOx);
		
		objReferenceMaxSolubility = ReferencesController.updateRef("update", table, "ActiveMaterial",inputActiveMaterial_Value, null, "RefIDkRed", objReferencekRed);
		
		objReferenceMaxSolubility = ReferencesController.updateRef("update", table, "ActiveMaterial",inputActiveMaterial_Value, null, "RefIDAlphaRed", objReferenceAlphaRed);
		
		objReferenceMaxSolubility = ReferencesController.updateRef("update", table, "ActiveMaterial",inputActiveMaterial_Value, null, "RefIDdegRate", objReferenceDegRate);
		
		objReferenceMaxSolubility = ReferencesController.updateRef("update", table, "ActiveMaterial",inputActiveMaterial_Value, null, "RefIDf", objReferenceF);

		//close Add-Pane:
		AddElectrolytesController.this.cancelAddElectrolytes(event);


	}

}
