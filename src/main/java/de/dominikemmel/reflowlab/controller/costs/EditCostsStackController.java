package de.dominikemmel.reflowlab.controller.costs;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import de.dominikemmel.reflowlab.Database;
import de.dominikemmel.reflowlab.VariousMethods;
import de.dominikemmel.reflowlab.controller.references.ObjReference;
import de.dominikemmel.reflowlab.controller.references.ReferencesController;

public class EditCostsStackController implements javafx.fxml.Initializable {

	//passing selected data from StackController:
	private ObservableList<ObjCostsStack> selection;

	@FXML
	private AnchorPane editPane;

	@FXML
	private TextField inputComponentName;
	@FXML
	private ChoiceBox inputComponentType;
	@FXML
	private TextField inputComponentCost;
	@FXML
	private TextField inputCostUnit;
	
	@FXML
	private TextField inputRefDOIComponentCost;
	@FXML
	private TextField inputRefComponentCost;

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Platform.runLater(new Runnable() {

            @Override
            public void run() {
            	inputComponentName.requestFocus();
            }
        });
		
		inputComponentType.getItems().add("Separator");
		inputComponentType.getItems().add("Electrode");
		inputComponentType.getItems().add("Bipolar plate");
		inputComponentType.getItems().add("Cell frames and seals");
		inputComponentType.getItems().add("Current collectors");
		inputComponentType.getItems().add("Stack frame");
		inputComponentType.getItems().add("Assembly");
		
		inputComponentType.getSelectionModel().selectedIndexProperty().addListener((ChangeListener) (ov, old, newval) -> {
			
			Number idx = (Number) newval;
			
			switch (idx.intValue()) {
				case 0:
					inputCostUnit.setText("$/m2");
					break;
				case 1:
					inputCostUnit.setText("$/m2");
					break;
				case 2:
					inputCostUnit.setText("$/m2");
					break;
				case 3:
					inputCostUnit.setText("$ per piece");
					break;
				case 4:
					inputCostUnit.setText("$ per piece");
					break;
				case 5:
					inputCostUnit.setText("$ per piece");
					break;
				case 6:
					inputCostUnit.setText("$ per piece");
					break;
			}
		});
		
		inputRefDOIComponentCost.textProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue == "" || newValue == null) {

			} else {
				
				ObjReference objReference;
				try {
					objReference = ReferencesController.checkRef(newValue);
					
					inputRefComponentCost.setText(objReference.Reference.get());
					
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void setSelectionData(ObservableList<ObjCostsStack> selection) throws ClassNotFoundException, SQLException {
		this.selection = selection;

		inputComponentName.setText(selection.get(0).ComponentName.getValue());
		inputComponentType.setValue(selection.get(0).ComponentType.getValue());
		inputComponentCost.setText(selection.get(0).ComponentCost.getValue().toString());
		inputCostUnit.setText(selection.get(0).CostUnit.getValue());
		
		ObjReference objReference = ReferencesController.checkRefID(selection.get(0).RefIDComponentCost.getValue());
		
		inputRefDOIComponentCost.setText(objReference.DOI.get());
		inputRefComponentCost.setText(objReference.Reference.get());
	}

	// Event Listener on Button.onAction
	@FXML
	public void cancelEditStack(ActionEvent event) {
		Stage stage = (Stage) editPane.getScene().getWindow();
		stage.close();
	}
	// Event Listener on Button.onAction
	@FXML
	public void editEditStack(ActionEvent event) throws ClassNotFoundException, SQLException {

		ObjCostsStack objCostsStack = new ObjCostsStack();

		String inputComponentName_Value = VariousMethods.getTextFieldInput(inputComponentName, "stringInput");
		String inputComponentType_Value = inputComponentType.getValue().toString();
		Double inputComponentCost_Value = VariousMethods.getTextFieldInput(inputComponentCost, "doubleInput");
		String inputCostUnit_Value = VariousMethods.getTextFieldInput(inputCostUnit, "stringInput");

		objCostsStack.ComponentName.set(inputComponentName_Value);
		objCostsStack.ComponentType.set(inputComponentType_Value);
		objCostsStack.ComponentCost.set(inputComponentCost_Value);
		objCostsStack.CostUnit.set(inputCostUnit_Value);

		try {
			Connection con = Database.getConnection("costStack");
			Statement state = con.createStatement();
			state.executeUpdate("UPDATE costStack "
					+ "SET ID = "+selection.get(0).ID.getValue()+
					", ComponentName = '"+objCostsStack.ComponentName.getValue()+"', ComponentType = '"+objCostsStack.ComponentType.getValue()+
					"', ComponentCost = "+objCostsStack.ComponentCost.getValue()+", CostUnit = '"+objCostsStack.CostUnit.getValue()+
					"', editDate = CURRENT_TIMESTAMP"
							+ " WHERE ID = "+selection.get(0).ID.getValue()+"");

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		//Ref Input
		String inputRefDOIComponentCost_Value = VariousMethods.getTextFieldInput(inputRefDOIComponentCost, "stringInput");
		String inputRefComponentCost_Value = VariousMethods.getTextFieldInput(inputRefComponentCost, "stringInput");
		
		ObjReference objReference = new ObjReference();
		objReference.DOI.set(inputRefDOIComponentCost_Value);
		objReference.Reference.set(inputRefComponentCost_Value);
		
		objReference = ReferencesController.updateRef("update", "costStack", "ComponentName",inputComponentName_Value, selection.get(0).ComponentName.getValue(), "RefIDComponentCost", objReference);

		//close Edit-Pane:
		EditCostsStackController.this.cancelEditStack(event);
	}
	// Event Listener on Button.onAction
	@FXML
	public void deleteEditStack(ActionEvent event) throws ClassNotFoundException, SQLException {
		String inputComponentName_Value = VariousMethods.getTextFieldInput(inputComponentName, "stringInput");
		
		//Ref Input
		String inputRefDOIComponentCost_Value = VariousMethods.getTextFieldInput(inputRefDOIComponentCost, "stringInput");
		String inputRefComponentCost_Value = VariousMethods.getTextFieldInput(inputRefComponentCost, "stringInput");
		
		ObjReference objReference = new ObjReference();
		objReference.DOI.set(inputRefDOIComponentCost_Value);
		objReference.Reference.set(inputRefComponentCost_Value);
		
		objReference = ReferencesController.updateRef("delete", "costStack", "ComponentName",inputComponentName_Value, selection.get(0).ComponentName.getValue(), "RefIDComponentCost", objReference);

		try {
			Connection con = Database.getConnection("costStack");
			Statement state = con.createStatement();
			state.executeUpdate("DELETE FROM costStack WHERE ID = "+selection.get(0).ID.getValue()+"");

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		//close Edit-Pane:
		EditCostsStackController.this.cancelEditStack(event);
	}

}
