package de.dominikemmel.reflowlab.controller.solvent;

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

public class AddSolventOrgController implements javafx.fxml.Initializable {
	@FXML
	private AnchorPane addPane;
	@FXML
	private TextField inputSolvent;
	@FXML
	private TextField inputStructuralFormula;
	@FXML
	private TextField inputDensity;
	@FXML
	private TextField inputdynViscosity;
	@FXML
	private TextField inputkinViscosity;
	@FXML
	private TextField inputElimitCat;
	@FXML
	private TextField inputElimitAn;
	
	@FXML private TextField inputRefDOIDensity;
	@FXML private TextField inputRefDensity;
	@FXML private TextField inputRefDOIdynViscosity;
	@FXML private TextField inputRefdynViscosity;
	@FXML private TextField inputRefDOIkinViscosity;
	@FXML private TextField inputRefkinViscosity;
	@FXML private TextField inputRefDOIElimitCat;
	@FXML private TextField inputRefElimitCat;
	@FXML private TextField inputRefDOIElimitAn;
	@FXML private TextField inputRefElimitAn;
	
	@FXML private Button btnInputRefDensity;
	@FXML private Button btnInputRefdynViscosity;
	@FXML private Button btnInputRefElimitCat;
	
	String table = "solventOrganic";


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Platform.runLater(new Runnable() {

            @Override
            public void run() {
            	inputSolvent.requestFocus();
            }
        });
		
		Image img = new Image(getClass().getResourceAsStream("/de/dominikemmel/reflowlab/controller/maincontrol/img/arrowDown.png"));
		ImageView imgViewBtnInputRefDensity = new ImageView(img);
		imgViewBtnInputRefDensity.setFitHeight(10);
		imgViewBtnInputRefDensity.setFitWidth(12);
		
		ImageView imgViewBtnInputRefdynViscosity = new ImageView(img);
		imgViewBtnInputRefdynViscosity.setFitHeight(10);
		imgViewBtnInputRefdynViscosity.setFitWidth(12);
		
		ImageView imgViewBtnInputRefElimitCat = new ImageView(img);
		imgViewBtnInputRefElimitCat.setFitHeight(10);
		imgViewBtnInputRefElimitCat.setFitWidth(12);
		
		btnInputRefDensity.setGraphic(imgViewBtnInputRefDensity);
		btnInputRefdynViscosity.setGraphic(imgViewBtnInputRefdynViscosity);
		btnInputRefElimitCat.setGraphic(imgViewBtnInputRefElimitCat);
		
		inputRefDOIDensity.textProperty().addListener((observable, oldValue, newValue) -> {
		    if(newValue == "" || newValue == null) {
		    	disableBtn(btnInputRefDensity);
		    } else {
		    	enableBtn(btnInputRefDensity);
		    	
				ObjReference objReference;
				try {
					objReference = ReferencesController.checkRef(newValue);
					
					if (objReference.Reference.get() != null) {
						inputRefDensity.setText(objReference.Reference.get());
					}
					
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
		    }
		});
		inputRefDOIdynViscosity.textProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue == "" || newValue == null) {
				disableBtn(btnInputRefdynViscosity);
			} else {
				enableBtn(btnInputRefdynViscosity);
				
				ObjReference objReference;
				try {
					objReference = ReferencesController.checkRef(newValue);
					
					if (objReference.Reference.get() != null) {
						inputRefdynViscosity.setText(objReference.Reference.get());
					}
					
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}
		});
		inputRefDOIkinViscosity.textProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue == "" || newValue == null) {

			} else {
				
				ObjReference objReference;
				try {
					objReference = ReferencesController.checkRef(newValue);
					
					if (objReference.Reference.get() != null) {
						inputRefkinViscosity.setText(objReference.Reference.get());
					}
					
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}
		});
		inputRefDOIElimitCat.textProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue == "" || newValue == null) {
				disableBtn(btnInputRefElimitCat);
			} else {
				enableBtn(btnInputRefElimitCat);
				
				ObjReference objReference;
				try {
					objReference = ReferencesController.checkRef(newValue);
					
					if (objReference.Reference.get() != null) {
						inputRefElimitCat.setText(objReference.Reference.get());
					}
					
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}
		});
		inputRefDOIElimitAn.textProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue == "" || newValue == null) {

			} else {
				
				ObjReference objReference;
				try {
					objReference = ReferencesController.checkRef(newValue);
					
					if (objReference.Reference.get() != null) {
						inputRefElimitAn.setText(objReference.Reference.get());
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
	public void btnInputRefDensityEvent(ActionEvent event) {
		inputRefDOIdynViscosity.setText(inputRefDOIDensity.getText());
		inputRefdynViscosity.setText(inputRefDensity.getText());
		inputRefDOIkinViscosity.setText(inputRefDOIDensity.getText());
		inputRefkinViscosity.setText(inputRefDensity.getText());
	}
	
	@FXML
	public void btnInputRefdynViscosityEvent(ActionEvent event) {
		inputRefDOIkinViscosity.setText(inputRefDOIdynViscosity.getText());
		inputRefkinViscosity.setText(inputRefdynViscosity.getText());
	}
	
	@FXML
	public void btnInputRefElimitCatEvent(ActionEvent event) {
		inputRefDOIElimitAn.setText(inputRefDOIElimitCat.getText());
		inputRefElimitAn.setText(inputRefElimitCat.getText());
	}

	@FXML
	public void cancelAddSolventOrg(ActionEvent event) {
		//close Add-Pane:
		Stage stage = (Stage) addPane.getScene().getWindow();
		stage.close();
	}

	@FXML
	public void addAddSolventOrg(ActionEvent event) throws ClassNotFoundException, SQLException {

		ObjOrganicSolvent objOrganicSolvent = new ObjOrganicSolvent();

		String inputSolvent_Value = VariousMethods.getTextFieldInput(inputSolvent, "stringInput");
		String inputStructuralFormula_Value = VariousMethods.getTextFieldInput(inputStructuralFormula, "stringInput");
		Double inputDensity_Value = VariousMethods.getTextFieldInput(inputDensity, "doubleInput");
		Double inputdynViscosity_Value = VariousMethods.getTextFieldInput(inputdynViscosity, "doubleInput");
		Double inputkinViscosity_Value = VariousMethods.getTextFieldInput(inputkinViscosity, "doubleInput");
		Double inputElimitCat_Value = VariousMethods.getTextFieldInput(inputElimitCat, "doubleInput");
		Double inputElimitAn_Value = VariousMethods.getTextFieldInput(inputElimitAn, "doubleInput");

		objOrganicSolvent.SolventName.set(inputSolvent_Value);
		objOrganicSolvent.SolventStructuralFormula.set(inputStructuralFormula_Value);
		objOrganicSolvent.density.set(inputDensity_Value);
		objOrganicSolvent.dynViscosity.set(inputdynViscosity_Value);
		objOrganicSolvent.kinViscosity.set(inputkinViscosity_Value);
		objOrganicSolvent.ElimitCat.set(inputElimitCat_Value);
		objOrganicSolvent.ElimitAn.set(inputElimitAn_Value);

		try {
			Connection con = Database.getConnection("solventOrganic");
			Statement state = con.createStatement();
			state.executeUpdate("INSERT INTO solventOrganic(ID, SolventName, SolventStructuralFormula, density, dynViscosity, kinViscosity, ElimitCat, ElimitAn, editDate)"
					+" VALUES(DEFAULT, '"+objOrganicSolvent.SolventName.getValue()+"', '"+objOrganicSolvent.SolventStructuralFormula.getValue()+"', '"
					+objOrganicSolvent.density.getValue()+"', "
					+objOrganicSolvent.dynViscosity.getValue()+", "+objOrganicSolvent.kinViscosity.getValue()+
					", "+objOrganicSolvent.ElimitCat.getValue()+", "+objOrganicSolvent.ElimitAn.getValue()
					+", CURRENT_TIMESTAMP)");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//Ref Input
		String inputRefDOIDensity_Value = VariousMethods.getTextFieldInput(inputRefDOIDensity, "stringInput");
		String inputRefDensity_Value = VariousMethods.getTextFieldInput(inputRefDensity, "stringInput");
		
		String inputRefDOIdynViscosity_Value = VariousMethods.getTextFieldInput(inputRefDOIdynViscosity, "stringInput");
		String inputRefdynViscosity_Value = VariousMethods.getTextFieldInput(inputRefdynViscosity, "stringInput");
		
		String inputRefDOIkinViscosity_Value = VariousMethods.getTextFieldInput(inputRefDOIkinViscosity, "stringInput");
		String inputRefkinViscosity_Value = VariousMethods.getTextFieldInput(inputRefkinViscosity, "stringInput");
		
		String inputRefDOIElimitCat_Value = VariousMethods.getTextFieldInput(inputRefDOIElimitCat, "stringInput");
		String inputRefElimitCat_Value = VariousMethods.getTextFieldInput(inputRefElimitCat, "stringInput");
		
		String inputRefDOIElimitAn_Value = VariousMethods.getTextFieldInput(inputRefDOIElimitAn, "stringInput");
		String inputRefElimitAn_Value = VariousMethods.getTextFieldInput(inputRefElimitAn, "stringInput");
		
		ObjReference objReferenceDensity = new ObjReference();
		objReferenceDensity.DOI.set(inputRefDOIDensity_Value);
		objReferenceDensity.Reference.set(inputRefDensity_Value);
		
		ObjReference objReferenceDynViscosity = new ObjReference();
		objReferenceDynViscosity.DOI.set(inputRefDOIdynViscosity_Value);
		objReferenceDynViscosity.Reference.set(inputRefdynViscosity_Value);
		
		ObjReference objReferenceKinViscosity = new ObjReference();
		objReferenceKinViscosity.DOI.set(inputRefDOIkinViscosity_Value);
		objReferenceKinViscosity.Reference.set(inputRefkinViscosity_Value);
		
		ObjReference objReferenceElimitCat = new ObjReference();
		objReferenceElimitCat.DOI.set(inputRefDOIElimitCat_Value);
		objReferenceElimitCat.Reference.set(inputRefElimitCat_Value);
		
		ObjReference objReferenceElimitAn = new ObjReference();
		objReferenceElimitAn.DOI.set(inputRefDOIElimitAn_Value);
		objReferenceElimitAn.Reference.set(inputRefElimitAn_Value);
		
		objReferenceDensity = ReferencesController.updateRef("update", table, "SolventName",inputSolvent_Value, null, "RefIDdensity", objReferenceDensity);
		
		objReferenceDynViscosity = ReferencesController.updateRef("update", table, "SolventName",inputSolvent_Value, null, "RefIDdynViscosity", objReferenceDynViscosity);
		
		objReferenceKinViscosity = ReferencesController.updateRef("update", table, "SolventName",inputSolvent_Value, null, "RefIDkinViscosity", objReferenceKinViscosity);
		
		objReferenceElimitCat = ReferencesController.updateRef("update", table, "SolventName",inputSolvent_Value, null, "RefIDElimitCat", objReferenceElimitCat);
		
		objReferenceElimitAn = ReferencesController.updateRef("update", table, "SolventName",inputSolvent_Value, null, "RefIDElimitAn", objReferenceElimitAn);
		

		//close Add-Pane:
		AddSolventOrgController.this.cancelAddSolventOrg(event);
	}

}
