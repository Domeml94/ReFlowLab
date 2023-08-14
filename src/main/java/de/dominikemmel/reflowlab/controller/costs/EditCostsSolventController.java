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

public class EditCostsSolventController implements javafx.fxml.Initializable {


	//passing selected data from CostSolventController:
	private ObservableList<ObjCostsSolvent> selection;

	@FXML
	private AnchorPane editPane;
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
	
	@FXML
	private TextFlow MSolvent_TextFlow;
	@FXML
	private TextFlow CSolvent_TextFlow;
	
	String table = "costSolvent";

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Platform.runLater(new Runnable() {

            @Override
            public void run() {
            	inputSolvent.requestFocus();
            }
        });
		
    	//CSolvent:
    	Text CSolvent1 = new Text("C");
    	CSolvent1.setStyle("-fx-font-weight: bold; -fx-font-style: italic");
    	Text CSolvent2 = new Text(" / $ kg");
    	CSolvent2.setStyle("-fx-font-weight: bold");
    	Text CSolvent3 = new Text("⁻¹");
    	CSolvent3.setStyle("-fx-font-weight: bold");
		Text CSolvent4 = new Text(":");
		CSolvent4.setStyle("-fx-font-weight: bold");

    	CSolvent_TextFlow.getChildren().addAll(CSolvent1,CSolvent2,CSolvent3,CSolvent4);

    	//MSolvent:
    	Text MSolvent1 = new Text("M");
    	MSolvent1.setStyle("-fx-font-weight: bold; -fx-font-style: italic");
		Text MSolvent2 = new Text(" / g mol");
		MSolvent2.setStyle("-fx-font-weight: bold");
		Text MSolvent3 = new Text("⁻¹");
		MSolvent3.setStyle("-fx-font-weight: bold");
		Text MSolvent4 = new Text(":");
		MSolvent4.setStyle("-fx-font-weight: bold");

		MSolvent_TextFlow.getChildren().addAll(MSolvent1,MSolvent2,MSolvent3,MSolvent4);
		
		
		Image img = new Image(getClass().getResourceAsStream("/de/dominikemmel/reflowlab/img/ArrowDown/0.5x/Asset 2@0.5x.png"));
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

					inputRefMSolvent.setText(objReference.Reference.get());
					
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
					
					inputRefCSolvent.setText(objReference.Reference.get());
					
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


	public void setSelectionData(ObservableList<ObjCostsSolvent> selection) throws ClassNotFoundException, SQLException {
		this.selection = selection;

		inputSolvent.setText(selection.get(0).Solvent.getValue());
		inputMSolvent.setText(selection.get(0).MSolvent.getValue().toString());
		inputCSolvent.setText(selection.get(0).CSolvent.getValue().toString());
		
		ObjReference objReferenceMSolvent = ReferencesController.checkRefID(selection.get(0).RefIDMSolvent.getValue());
		
		inputRefDOIMSolvent.setText(objReferenceMSolvent.DOI.get());
		inputRefMSolvent.setText(objReferenceMSolvent.Reference.get());
		
		ObjReference objReferenceCSolvent = ReferencesController.checkRefID(selection.get(0).RefIDCSolvent.getValue());
		
		inputRefDOICSolvent.setText(objReferenceCSolvent.DOI.get());
		inputRefCSolvent.setText(objReferenceCSolvent.Reference.get());

	}

	// Event Listener on Button.onAction
	@FXML
	public void cancelEditSolvent(ActionEvent event) {
		Stage stage = (Stage) editPane.getScene().getWindow();
		stage.close();
	}
	// Event Listener on Button.onAction
	@FXML
	public void editEditSolvent(ActionEvent event) throws ClassNotFoundException, SQLException {

		ObjCostsSolvent objCostsSolvent = new ObjCostsSolvent();

		String inputSolvent_Value = VariousMethods.getTextFieldInput(inputSolvent, "stringInput");
		Double inputMSolvent_Value = VariousMethods.getTextFieldInput(inputMSolvent, "doubleInput");
		Double inputCSolvent_Value = VariousMethods.getTextFieldInput(inputCSolvent, "doubleInput");

		objCostsSolvent.Solvent.set(inputSolvent_Value);
		objCostsSolvent.MSolvent.set(inputMSolvent_Value);
		objCostsSolvent.CSolvent.set(inputCSolvent_Value);

		try {
			Connection con = Database.getConnection("costSolvent");
			Statement state = con.createStatement();
			state.executeUpdate("UPDATE costSolvent "
					+ "SET ID = "+selection.get(0).ID.getValue()+
					", Solvent = '"+objCostsSolvent.Solvent.getValue()+"', MSolvent = "+objCostsSolvent.MSolvent.getValue()+
					", CSolvent = "+objCostsSolvent.CSolvent.getValue()+
					", editDate = CURRENT_TIMESTAMP"
							+ " WHERE ID = "+selection.get(0).ID.getValue()+"");

		} catch (ClassNotFoundException | SQLException e) {
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
		
		objReferenceMSolvent = ReferencesController.updateRef("update", table, "Solvent", inputSolvent_Value, selection.get(0).Solvent.getValue(), "RefIDMSolvent", objReferenceMSolvent);
		
		objReferenceCSolvent = ReferencesController.updateRef("update", table, "Solvent", inputSolvent_Value, selection.get(0).Solvent.getValue(), "RefIDCSolvent", objReferenceCSolvent);
		
		//close Edit-Pane:
		EditCostsSolventController.this.cancelEditSolvent(event);
	}
	// Event Listener on Button.onAction

	@FXML
	public void deleteEditSolvent(ActionEvent event) throws ClassNotFoundException, SQLException {
		String inputSolvent_Value = VariousMethods.getTextFieldInput(inputSolvent, "stringInput");
		
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
		
		objReferenceMSolvent = ReferencesController.updateRef("delete", table, "Solvent", inputSolvent_Value, selection.get(0).Solvent.getValue(), "RefIDMSolvent", objReferenceMSolvent);
		
		objReferenceCSolvent = ReferencesController.updateRef("delete", table, "Solvent", inputSolvent_Value, selection.get(0).Solvent.getValue(), "RefIDCSolvent", objReferenceCSolvent);

		try {
			Connection con = Database.getConnection("costSolvent");
			Statement state = con.createStatement();
			state.executeUpdate("DELETE FROM costSolvent WHERE ID = "+selection.get(0).ID.getValue()+"");

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		//close Edit-Pane:
		EditCostsSolventController.this.cancelEditSolvent(event);

	}

}
