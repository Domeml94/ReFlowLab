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

public class EditSolventInorgController implements javafx.fxml.Initializable {
	@FXML
	private AnchorPane editPane;

	@FXML
	private TextField inputStructuralFormula;
	@FXML
	private TextField inputC;
	@FXML
	private TextField inputDensity;
	@FXML
	private TextField inputdynViscosity;
	@FXML
	private TextField inputkinViscosity;
	
	@FXML private TextField inputRefDOIDensity;
	@FXML private TextField inputRefDensity;
	@FXML private TextField inputRefDOIdynViscosity;
	@FXML private TextField inputRefdynViscosity;
	@FXML private TextField inputRefDOIkinViscosity;
	@FXML private TextField inputRefkinViscosity;
	
	@FXML private Button btnInputRefDensity;
	@FXML private Button btnInputRefdynViscosity;
	
	String table = "solventInorganic";

	private ObjInorganicSolvent selection = new ObjInorganicSolvent();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Platform.runLater(new Runnable() {

            @Override
            public void run() {
            	inputStructuralFormula.requestFocus();
            }
        });
		
		Image img = new Image(getClass().getResourceAsStream("/de/dominikemmel/reflowlab/img/ArrowDown/1x/arrowDownmdpi.png"));
		ImageView imgViewBtnInputRefDensity = new ImageView(img);
		imgViewBtnInputRefDensity.setFitHeight(10);
		imgViewBtnInputRefDensity.setFitWidth(12);
		
		ImageView imgViewBtnInputRefdynViscosity = new ImageView(img);
		imgViewBtnInputRefdynViscosity.setFitHeight(10);
		imgViewBtnInputRefdynViscosity.setFitWidth(12);
		
		btnInputRefDensity.setGraphic(imgViewBtnInputRefDensity);
		btnInputRefdynViscosity.setGraphic(imgViewBtnInputRefdynViscosity);
		
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
	

	public void setSelectionData(ObjInorganicSolvent selection) throws ClassNotFoundException, SQLException {
		//passing selected data from SolventInorgController:
		this.selection = selection;

		inputStructuralFormula.setText(selection.SolventStructuralFormula.getValue().toString());
		inputC.setText(selection.c.getValue().toString());
		inputDensity.setText(selection.density.getValue().toString());
		inputdynViscosity.setText(selection.dynViscosity.getValue().toString());
		inputkinViscosity.setText(selection.kinViscosity.getValue().toString());
		
		ObjReference objReferenceDensity = ReferencesController.checkRefID(selection.RefIDdensity.getValue());
		
		inputRefDOIDensity.setText(objReferenceDensity.DOI.get());
		inputRefDensity.setText(objReferenceDensity.Reference.get());
		
		ObjReference objReferenceDynViscosity = ReferencesController.checkRefID(selection.RefIDdynViscosity.getValue());
		
		inputRefDOIdynViscosity.setText(objReferenceDynViscosity.DOI.get());
		inputRefdynViscosity.setText(objReferenceDynViscosity.Reference.get());
		
		ObjReference objReferenceKinViscosity = ReferencesController.checkRefID(selection.RefIDkinViscosity.getValue());
		
		inputRefDOIkinViscosity.setText(objReferenceKinViscosity.DOI.get());
		inputRefkinViscosity.setText(objReferenceKinViscosity.Reference.get());
		
	}




	@FXML
	public void cancelEditSolventInorg(ActionEvent event) {
		//close Add-Pane:
		Stage stage = (Stage) editPane.getScene().getWindow();
		stage.close();
	}




	@FXML
	public void editEditSolventInorg(ActionEvent event) throws ClassNotFoundException, SQLException {
		ObjInorganicSolvent selectionEdit = new ObjInorganicSolvent();

		selectionEdit.SolventStructuralFormula.set(VariousMethods.getTextFieldInput(inputStructuralFormula, "stringInput"));
		selectionEdit.c.set(VariousMethods.getTextFieldInput(inputC, "doubleInput"));
		selectionEdit.density.set(VariousMethods.getTextFieldInput(inputDensity, "doubleInput"));
		selectionEdit.dynViscosity.set(VariousMethods.getTextFieldInput(inputdynViscosity, "doubleInput"));
		selectionEdit.kinViscosity.set(VariousMethods.getTextFieldInput(inputkinViscosity, "doubleInput"));

		try {
			Connection con = Database.getConnection("solventInorganic");
			Statement state = con.createStatement();
			state.executeUpdate("UPDATE solventInorganic "
					+ "SET ID = "+selection.ID.getValue()+
					", SolventStructuralFormula = '"+selectionEdit.SolventStructuralFormula.getValue()+"', c = '"+selectionEdit.c.getValue()+
					"', density = '"+selectionEdit.density.getValue()+"', dynViscosity = "+selectionEdit.dynViscosity.getValue()+
					", kinViscosity = "+selectionEdit.kinViscosity.getValue()+", editDate = CURRENT_TIMESTAMP"
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
		
		ObjReference objReferenceDensity = new ObjReference();
		objReferenceDensity.DOI.set(inputRefDOIDensity_Value);
		objReferenceDensity.Reference.set(inputRefDensity_Value);
		
		ObjReference objReferenceDynViscosity = new ObjReference();
		objReferenceDynViscosity.DOI.set(inputRefDOIdynViscosity_Value);
		objReferenceDynViscosity.Reference.set(inputRefdynViscosity_Value);
		
		ObjReference objReferenceKinViscosity = new ObjReference();
		objReferenceKinViscosity.DOI.set(inputRefDOIkinViscosity_Value);
		objReferenceKinViscosity.Reference.set(inputRefkinViscosity_Value);
		
		objReferenceDensity = ReferencesController.updateRef("update", table, "SolventStructuralFormula",selectionEdit.SolventStructuralFormula.getValue(), selection.SolventStructuralFormula.getValue().toString(), "RefIDdensity", objReferenceDensity);
		
		objReferenceDynViscosity = ReferencesController.updateRef("update", table, "SolventStructuralFormula",selectionEdit.SolventStructuralFormula.getValue(), selection.SolventStructuralFormula.getValue().toString(), "RefIDdynViscosity", objReferenceDynViscosity);
		
		objReferenceKinViscosity = ReferencesController.updateRef("update", table, "SolventStructuralFormula",selectionEdit.SolventStructuralFormula.getValue(), selection.SolventStructuralFormula.getValue().toString(), "RefIDkinViscosity", objReferenceKinViscosity);
		
		
		//close Add-Pane:
		EditSolventInorgController.this.cancelEditSolventInorg(event);
	}




	@FXML
	public void deleteEditSolventInorg(ActionEvent event) throws ClassNotFoundException, SQLException {
		
		ObjInorganicSolvent selectionEdit = new ObjInorganicSolvent();

		selectionEdit.SolventStructuralFormula.set(VariousMethods.getTextFieldInput(inputStructuralFormula, "stringInput"));
		
		//Ref Input
		String inputRefDOIDensity_Value = VariousMethods.getTextFieldInput(inputRefDOIDensity, "stringInput");
		String inputRefDensity_Value = VariousMethods.getTextFieldInput(inputRefDensity, "stringInput");
		
		String inputRefDOIdynViscosity_Value = VariousMethods.getTextFieldInput(inputRefDOIdynViscosity, "stringInput");
		String inputRefdynViscosity_Value = VariousMethods.getTextFieldInput(inputRefdynViscosity, "stringInput");
		
		String inputRefDOIkinViscosity_Value = VariousMethods.getTextFieldInput(inputRefDOIkinViscosity, "stringInput");
		String inputRefkinViscosity_Value = VariousMethods.getTextFieldInput(inputRefkinViscosity, "stringInput");
		
		ObjReference objReferenceDensity = new ObjReference();
		objReferenceDensity.DOI.set(inputRefDOIDensity_Value);
		objReferenceDensity.Reference.set(inputRefDensity_Value);
		
		ObjReference objReferenceDynViscosity = new ObjReference();
		objReferenceDynViscosity.DOI.set(inputRefDOIdynViscosity_Value);
		objReferenceDynViscosity.Reference.set(inputRefdynViscosity_Value);
		
		ObjReference objReferenceKinViscosity = new ObjReference();
		objReferenceKinViscosity.DOI.set(inputRefDOIkinViscosity_Value);
		objReferenceKinViscosity.Reference.set(inputRefkinViscosity_Value);
		
		objReferenceDensity = ReferencesController.updateRef("delete", table, "SolventStructuralFormula",selectionEdit.SolventStructuralFormula.getValue(), selection.SolventStructuralFormula.getValue().toString(), "RefIDdensity", objReferenceDensity);
		
		objReferenceDynViscosity = ReferencesController.updateRef("delete", table, "SolventStructuralFormula",selectionEdit.SolventStructuralFormula.getValue(), selection.SolventStructuralFormula.getValue().toString(), "RefIDdynViscosity", objReferenceDynViscosity);
		
		objReferenceKinViscosity = ReferencesController.updateRef("delete", table, "SolventStructuralFormula",selectionEdit.SolventStructuralFormula.getValue(), selection.SolventStructuralFormula.getValue().toString(), "RefIDkinViscosity", objReferenceKinViscosity);
		
		
		try {
			Connection con = Database.getConnection("solventInorganic");
			Statement state = con.createStatement();
			state.executeUpdate("DELETE FROM solventInorganic WHERE ID = "+selection.ID.getValue()+"");

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		//close Add-Pane:
		EditSolventInorgController.this.cancelEditSolventInorg(event);

	}

}
