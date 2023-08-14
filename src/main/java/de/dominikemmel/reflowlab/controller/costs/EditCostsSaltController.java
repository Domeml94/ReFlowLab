package de.dominikemmel.reflowlab.controller.costs;

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
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import de.dominikemmel.reflowlab.Database;
import de.dominikemmel.reflowlab.VariousMethods;
import de.dominikemmel.reflowlab.controller.references.ObjReference;
import de.dominikemmel.reflowlab.controller.references.ReferencesController;

public class EditCostsSaltController implements javafx.fxml.Initializable {

	//passing selected data from CostSaltController:
	private ObservableList<ObjCostsSalt> selection;

	@FXML
	private AnchorPane editPane;
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
	private TextFlow MSalt_TextFlow;
	@FXML
	private TextFlow CSalt_TextFlow;
	
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
		
    	//MSalt:
    	Text MSalt1 = new Text("M");
    	MSalt1.setStyle("-fx-font-weight: bold; -fx-font-style: italic");
    	Text MSalt2 = new Text(" / g mol");
    	MSalt2.setStyle("-fx-font-weight: bold");
    	Text MSalt3 = new Text("⁻¹");
    	MSalt3.setStyle("-fx-font-weight: bold");
		Text MSalt4 = new Text(":");
		MSalt4.setStyle("-fx-font-weight: bold");

    	MSalt_TextFlow.getChildren().addAll(MSalt1,MSalt2,MSalt3,MSalt4);

    	//CSalt:
    	Text CSalt1 = new Text("C");
    	CSalt1.setStyle("-fx-font-weight: bold; -fx-font-style: italic");
		Text CSalt2 = new Text(" / $ kg");
		CSalt2.setStyle("-fx-font-weight: bold");
		Text CSalt3 = new Text("⁻¹");
		CSalt3.setStyle("-fx-font-weight: bold");
		Text CSalt4 = new Text(":");
		CSalt4.setStyle("-fx-font-weight: bold");

		CSalt_TextFlow.getChildren().addAll(CSalt1,CSalt2,CSalt3,CSalt4);
		
		
		Image img = new Image(getClass().getResourceAsStream("/de/dominikemmel/reflowlab/img/ArrowDown/0.5x/Asset 2@0.5x.png"));
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

					inputRefMSalt.setText(objReference.Reference.get());
					
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
					
					inputRefCSalt.setText(objReference.Reference.get());
					
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

	public void setSelectionData(ObservableList<ObjCostsSalt> selection) throws ClassNotFoundException, SQLException {
		this.selection = selection;

		inputSalt.setText(selection.get(0).Salt.getValue());
		inputMSalt.setText(selection.get(0).MSalt.getValue().toString());
		inputCSalt.setText(selection.get(0).CSalt.getValue().toString());
		
		ObjReference objReferenceMSalt = ReferencesController.checkRefID(selection.get(0).RefIDMSalt.getValue());
		
		inputRefDOIMSalt.setText(objReferenceMSalt.DOI.get());
		inputRefMSalt.setText(objReferenceMSalt.Reference.get());
		
		ObjReference objReferenceCSalt = ReferencesController.checkRefID(selection.get(0).RefIDCSalt.getValue());
		
		inputRefDOICSalt.setText(objReferenceCSalt.DOI.get());
		inputRefCSalt.setText(objReferenceCSalt.Reference.get());

	}

	// Event Listener on Button.onAction
	@FXML
	public void cancelEditSalt(ActionEvent event) {
		Stage stage = (Stage) editPane.getScene().getWindow();
		stage.close();
	}
	// Event Listener on Button.onAction
	@FXML
	public void editEditSalt(ActionEvent event) throws ClassNotFoundException, SQLException {
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
			state.executeUpdate("UPDATE costSalt "
					+ "SET ID = "+selection.get(0).ID.getValue()+
					", Salt = '"+objCostsSalt.Salt.getValue()+"', MSalt = "+objCostsSalt.MSalt.getValue()+
					", CSalt = "+objCostsSalt.CSalt.getValue()+
					", editDate = CURRENT_TIMESTAMP"
							+ " WHERE ID = "+selection.get(0).ID.getValue()+"");

		} catch (ClassNotFoundException | SQLException e) {
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
		
		objReferenceMSalt = ReferencesController.updateRef("update", table, "Salt",inputSalt_Value, selection.get(0).Salt.getValue(), "RefIDMSalt", objReferenceMSalt);

		objReferenceCSalt = ReferencesController.updateRef("update", table, "Salt",inputSalt_Value, selection.get(0).Salt.getValue(), "RefIDCSalt", objReferenceCSalt);


		//close Edit-Pane:
		EditCostsSaltController.this.cancelEditSalt(event);
	}
	// Event Listener on Button.onAction
	@FXML
	public void deleteEditSalt(ActionEvent event) throws ClassNotFoundException, SQLException {
		String inputSalt_Value = VariousMethods.getTextFieldInput(inputSalt, "stringInput");
		
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
		
		objReferenceMSalt = ReferencesController.updateRef("delete", table, "Salt",inputSalt_Value, selection.get(0).Salt.getValue(), "RefIDMSalt", objReferenceMSalt);
		
		objReferenceCSalt = ReferencesController.updateRef("delete", table, "Salt",inputSalt_Value, selection.get(0).Salt.getValue(), "RefIDCSalt", objReferenceCSalt);
		
		try {
			Connection con = Database.getConnection("costSalt");
			Statement state = con.createStatement();
			state.executeUpdate("DELETE FROM costSalt WHERE ID = "+selection.get(0).ID.getValue()+"");

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		//close Edit-Pane:
		EditCostsSaltController.this.cancelEditSalt(event);
	}
}
