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

public class AddSolventInorgController implements javafx.fxml.Initializable {

	@FXML private AnchorPane addPane;

	@FXML private TextField inputStructuralFormula;
	@FXML private TextField inputC;
	@FXML private TextField inputDensity;
	@FXML private TextField inputdynViscosity;
	@FXML private TextField inputkinViscosity;
	
	@FXML private TextField inputRefDOIDensity;
	@FXML private TextField inputRefDensity;
	@FXML private TextField inputRefDOIdynViscosity;
	@FXML private TextField inputRefdynViscosity;
	@FXML private TextField inputRefDOIkinViscosity;
	@FXML private TextField inputRefkinViscosity;
	
	@FXML private Button btnInputRefDensity;
	@FXML private Button btnInputRefdynViscosity;
	
	String table = "solventInorganic";


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
	
	public void cancelAddSolventInorg (ActionEvent event) {
		//close Add-Pane:
		Stage stage = (Stage) addPane.getScene().getWindow();
		stage.close();
	}

	public void addAddSolventInorg (ActionEvent event) throws ClassNotFoundException, SQLException {

		ObjInorganicSolvent objInorganicSolvent = new ObjInorganicSolvent();

		String inputStructuralFormula_Value = VariousMethods.getTextFieldInput(inputStructuralFormula, "stringInput");
		Double inputC_Value = VariousMethods.getTextFieldInput(inputC, "doubleInput");
		Double inputDensity_Value = VariousMethods.getTextFieldInput(inputDensity, "doubleInput");
		Double inputdynViscosity_Value = VariousMethods.getTextFieldInput(inputdynViscosity, "doubleInput");
		Double inputkinViscosity_Value = VariousMethods.getTextFieldInput(inputkinViscosity, "doubleInput");


		objInorganicSolvent.SolventStructuralFormula.set(inputStructuralFormula_Value);
		objInorganicSolvent.c.set(inputC_Value);
		objInorganicSolvent.density.set(inputDensity_Value);
		objInorganicSolvent.dynViscosity.set(inputdynViscosity_Value);
		objInorganicSolvent.kinViscosity.set(inputkinViscosity_Value);

		try {
			Connection con = Database.getConnection("solventInorganic");
			Statement state = con.createStatement();
			state.executeUpdate("INSERT INTO solventInorganic(ID, SolventStructuralFormula, c, density, dynViscosity, kinViscosity, editDate)"
					+" VALUES(DEFAULT, '"+objInorganicSolvent.SolventStructuralFormula.getValue()+"', '"
					+objInorganicSolvent.c.getValue()+"', '"+objInorganicSolvent.density.getValue()+"', "
					+objInorganicSolvent.dynViscosity.getValue()+", "+objInorganicSolvent.kinViscosity.getValue()
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
		
		ObjReference objReferenceDensity = new ObjReference();
		objReferenceDensity.DOI.set(inputRefDOIDensity_Value);
		objReferenceDensity.Reference.set(inputRefDensity_Value);
		
		ObjReference objReferenceDynViscosity = new ObjReference();
		objReferenceDynViscosity.DOI.set(inputRefDOIdynViscosity_Value);
		objReferenceDynViscosity.Reference.set(inputRefdynViscosity_Value);
		
		ObjReference objReferenceKinViscosity = new ObjReference();
		objReferenceKinViscosity.DOI.set(inputRefDOIkinViscosity_Value);
		objReferenceKinViscosity.Reference.set(inputRefkinViscosity_Value);
		
		objReferenceDensity = ReferencesController.updateRef("update", table, "SolventStructuralFormula",inputStructuralFormula_Value, null, "RefIDdensity", objReferenceDensity);
		
		objReferenceDynViscosity = ReferencesController.updateRef("update", table, "SolventStructuralFormula",inputStructuralFormula_Value, null, "RefIDdynViscosity", objReferenceDynViscosity);
		
		objReferenceKinViscosity = ReferencesController.updateRef("update", table, "SolventStructuralFormula",inputStructuralFormula_Value, null, "RefIDkinViscosity", objReferenceKinViscosity);
		
		
		//close Add-Pane:
		AddSolventInorgController.this.cancelAddSolventInorg(event);
	}

}
