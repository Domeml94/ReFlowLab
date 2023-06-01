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

public class EditSolventOrgController implements javafx.fxml.Initializable {
	@FXML
	private AnchorPane editPane;

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
	
	private ObjOrganicSolvent selection = new ObjOrganicSolvent();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Platform.runLater(new Runnable() {

            @Override
            public void run() {
            	inputSolvent.requestFocus();
            }
        });
	
		Image img = new Image(getClass().getResourceAsStream("/de/dominikemmel/reflowlab/img/ArrowDown/1x/arrowDownmdpi.png"));
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
					
					inputRefDensity.setText(objReference.Reference.get());
					
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
					
					inputRefdynViscosity.setText(objReference.Reference.get());
					
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
					
					inputRefkinViscosity.setText(objReference.Reference.get());
					
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
					
					inputRefElimitCat.setText(objReference.Reference.get());
					
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
					
					inputRefElimitAn.setText(objReference.Reference.get());
					
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

	public void setSelectionData(ObjOrganicSolvent selection) throws ClassNotFoundException, SQLException {
		//passing selected data from SolventInorgController:
		this.selection = selection;

		inputSolvent.setText(selection.SolventName.getValue().toString());
		inputStructuralFormula.setText(selection.SolventStructuralFormula.getValue().toString());
		inputDensity.setText(selection.density.getValue().toString());
		inputdynViscosity.setText(selection.dynViscosity.getValue().toString());
		inputkinViscosity.setText(selection.kinViscosity.getValue().toString());
		inputElimitCat.setText(selection.ElimitCat.getValue().toString());
		inputElimitAn.setText(selection.ElimitAn.getValue().toString());
		
		ObjReference objReferenceDensity = ReferencesController.checkRefID(selection.RefIDdensity.getValue());
		
		inputRefDOIDensity.setText(objReferenceDensity.DOI.get());
		inputRefDensity.setText(objReferenceDensity.Reference.get());
		
		ObjReference objReferenceDynViscosity = ReferencesController.checkRefID(selection.RefIDdynViscosity.getValue());
		
		inputRefDOIdynViscosity.setText(objReferenceDynViscosity.DOI.get());
		inputRefdynViscosity.setText(objReferenceDynViscosity.Reference.get());
		
		ObjReference objReferenceKinViscosity = ReferencesController.checkRefID(selection.RefIDkinViscosity.getValue());
		
		inputRefDOIkinViscosity.setText(objReferenceKinViscosity.DOI.get());
		inputRefkinViscosity.setText(objReferenceKinViscosity.Reference.get());
		
		ObjReference objReferenceElimitCat = ReferencesController.checkRefID(selection.RefIDElimitCat.getValue());
		
		inputRefDOIElimitCat.setText(objReferenceElimitCat.DOI.get());
		inputRefElimitCat.setText(objReferenceElimitCat.Reference.get());
		
		ObjReference objReferenceElimitAn = ReferencesController.checkRefID(selection.RefIDElimitAn.getValue());
		
		inputRefDOIElimitAn.setText(objReferenceElimitAn.DOI.get());
		inputRefElimitAn.setText(objReferenceElimitAn.Reference.get());
	}



	@FXML
	public void cancelEditSolventOrg(ActionEvent event) {
		//close Add-Pane:
		Stage stage = (Stage) editPane.getScene().getWindow();
		stage.close();
	}




	@FXML
	public void editEditSolventOrg(ActionEvent event) throws ClassNotFoundException, SQLException {
		ObjOrganicSolvent selectionEdit = new ObjOrganicSolvent();

		selectionEdit.SolventName.set(VariousMethods.getTextFieldInput(inputSolvent, "stringInput"));
		selectionEdit.SolventStructuralFormula.set(VariousMethods.getTextFieldInput(inputStructuralFormula, "stringInput"));
		selectionEdit.density.set(VariousMethods.getTextFieldInput(inputDensity, "doubleInput"));
		selectionEdit.dynViscosity.set(VariousMethods.getTextFieldInput(inputdynViscosity, "doubleInput"));
		selectionEdit.kinViscosity.set(VariousMethods.getTextFieldInput(inputkinViscosity, "doubleInput"));
		selectionEdit.ElimitCat.set(VariousMethods.getTextFieldInput(inputElimitCat, "doubleInput"));
		selectionEdit.ElimitAn.set(VariousMethods.getTextFieldInput(inputElimitAn, "doubleInput"));

		try {
			Connection con = Database.getConnection("solventOrganic");
			Statement state = con.createStatement();
			state.executeUpdate("UPDATE solventOrganic "
					+ "SET ID = "+selection.ID.getValue()+", SolventName = '"+selectionEdit.SolventName.getValue()+
					"', SolventStructuralFormula = '"+selectionEdit.SolventStructuralFormula.getValue()+
					"', density = '"+selectionEdit.density.getValue()+"', dynViscosity = "+selectionEdit.dynViscosity.getValue()+
					", kinViscosity = "+selectionEdit.kinViscosity.getValue()+
					", ElimitCat = "+selectionEdit.ElimitCat.getValue()+", ElimitAn = "+selectionEdit.ElimitAn.getValue()+
					", editDate = CURRENT_TIMESTAMP"
							+ " WHERE ID = "+selection.ID.getValue()+"");

		} catch (ClassNotFoundException | SQLException e) {
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
				
				objReferenceDensity = ReferencesController.updateRef("update", table, "SolventName",selectionEdit.SolventName.getValue(), selection.SolventName.getValue().toString(), "RefIDdensity", objReferenceDensity);
				
				objReferenceDynViscosity = ReferencesController.updateRef("update", table, "SolventName",selectionEdit.SolventName.getValue(), selection.SolventName.getValue().toString(), "RefIDdynViscosity", objReferenceDynViscosity);
				
				objReferenceKinViscosity = ReferencesController.updateRef("update", table, "SolventName",selectionEdit.SolventName.getValue(), selection.SolventName.getValue().toString(), "RefIDkinViscosity", objReferenceKinViscosity);
				
				objReferenceElimitCat = ReferencesController.updateRef("update", table, "SolventName",selectionEdit.SolventName.getValue(), selection.SolventName.getValue().toString(), "RefIDElimitCat", objReferenceElimitCat);
				
				objReferenceElimitAn = ReferencesController.updateRef("update", table, "SolventName",selectionEdit.SolventName.getValue(), selection.SolventName.getValue().toString(), "RefIDElimitAn", objReferenceElimitAn);
				
		
		//close Add-Pane:
		EditSolventOrgController.this.cancelEditSolventOrg(event);
	}





	@FXML
	public void deleteEditSolventOrg(ActionEvent event) throws ClassNotFoundException, SQLException {
		
		ObjOrganicSolvent selectionEdit = new ObjOrganicSolvent();

		selectionEdit.SolventName.set(VariousMethods.getTextFieldInput(inputSolvent, "stringInput"));
		
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
		
		objReferenceDensity = ReferencesController.updateRef("delete", table, "SolventName",selectionEdit.SolventName.getValue(), selection.SolventName.getValue().toString(), "RefIDdensity", objReferenceDensity);
		
		objReferenceDynViscosity = ReferencesController.updateRef("delete", table, "SolventName",selectionEdit.SolventName.getValue(), selection.SolventName.getValue().toString(), "RefIDdynViscosity", objReferenceDynViscosity);
		
		objReferenceKinViscosity = ReferencesController.updateRef("delete", table, "SolventName",selectionEdit.SolventName.getValue(), selection.SolventName.getValue().toString(), "RefIDkinViscosity", objReferenceKinViscosity);
		
		objReferenceElimitCat = ReferencesController.updateRef("delete", table, "SolventName",selectionEdit.SolventName.getValue(), selection.SolventName.getValue().toString(), "RefIDElimitCat", objReferenceElimitCat);
		
		objReferenceElimitAn = ReferencesController.updateRef("delete", table, "SolventName",selectionEdit.SolventName.getValue(), selection.SolventName.getValue().toString(), "RefIDElimitAn", objReferenceElimitAn);
		
		
		try {
			Connection con = Database.getConnection("solventOrganic");
			Statement state = con.createStatement();
			state.executeUpdate("DELETE FROM solventOrganic WHERE ID = "+selection.ID.getValue()+"");

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		//close Add-Pane:
		EditSolventOrgController.this.cancelEditSolventOrg(event);

	}


}
