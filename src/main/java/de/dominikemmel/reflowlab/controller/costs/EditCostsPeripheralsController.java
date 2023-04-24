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

public class EditCostsPeripheralsController implements javafx.fxml.Initializable {

	//passing selected data from CostPeripheralsController:
	private ObservableList<ObjCostsPeripherals> selection;

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
		
		inputComponentType.getItems().add("Pump");
		inputComponentType.getItems().add("Heat exchanger");
		inputComponentType.getItems().add("Power inverters ");
		inputComponentType.getItems().add("Pipelines and fittings ");
		inputComponentType.getItems().add("Cabling ");
		inputComponentType.getItems().add("Process control system");
		
		inputComponentType.getSelectionModel().selectedIndexProperty().addListener((ChangeListener) (ov, old, newval) -> {
			
			Number idx = (Number) newval;
			
			switch (idx.intValue()) {
				case 0:
					inputCostUnit.setText("$ per item");
					break;
				case 1:
					inputCostUnit.setText("$/kW");
					break;
				case 2:
					inputCostUnit.setText("$/kW");
					break;
				case 3:
					inputCostUnit.setText("$/kW");
					break;
				case 4:
					inputCostUnit.setText("$/kW");
					break;
				case 5:
					inputCostUnit.setText("$ per battery system");
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

	public void setSelectionData(ObservableList<ObjCostsPeripherals> selection) throws ClassNotFoundException, SQLException {
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
	public void cancelEditPeripherals(ActionEvent event) {
		Stage stage = (Stage) editPane.getScene().getWindow();
		stage.close();
	}
	// Event Listener on Button.onAction
	@FXML
	public void editEditPeripherals(ActionEvent event) throws ClassNotFoundException, SQLException {

		ObjCostsPeripherals objCostsPeripherals = new ObjCostsPeripherals();

		String inputComponentName_Value = VariousMethods.getTextFieldInput(inputComponentName, "stringInput");
		String inputComponentType_Value = inputComponentType.getValue().toString();
		Double inputComponentCost_Value = VariousMethods.getTextFieldInput(inputComponentCost, "doubleInput");
		String inputCostUnit_Value = VariousMethods.getTextFieldInput(inputCostUnit, "stringInput");

		objCostsPeripherals.ComponentName.set(inputComponentName_Value);
		objCostsPeripherals.ComponentType.set(inputComponentType_Value);
		objCostsPeripherals.ComponentCost.set(inputComponentCost_Value);
		objCostsPeripherals.CostUnit.set(inputCostUnit_Value);

		try {
			Connection con = Database.getConnection("costPeripherals");
			Statement state = con.createStatement();
			state.executeUpdate("UPDATE costPeripherals "
					+ "SET ID = "+selection.get(0).ID.getValue()+
					", ComponentName = '"+objCostsPeripherals.ComponentName.getValue()+"', ComponentType = '"+objCostsPeripherals.ComponentType.getValue()+
					"', ComponentCost = "+objCostsPeripherals.ComponentCost.getValue()+", CostUnit = '"+objCostsPeripherals.CostUnit.getValue()+
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
		
		objReference = ReferencesController.updateRef("update", "costPeripherals", "ComponentName",inputComponentName_Value, selection.get(0).ComponentName.getValue(), "RefIDComponentCost", objReference);

		//close Edit-Pane:
		EditCostsPeripheralsController.this.cancelEditPeripherals(event);
	}
	// Event Listener on Button.onAction
	@FXML
	public void deleteEditPeripherals(ActionEvent event) throws ClassNotFoundException, SQLException {
		String inputComponentName_Value = VariousMethods.getTextFieldInput(inputComponentName, "stringInput");
		
		//Ref Input
		String inputRefDOIComponentCost_Value = VariousMethods.getTextFieldInput(inputRefDOIComponentCost, "stringInput");
		String inputRefComponentCost_Value = VariousMethods.getTextFieldInput(inputRefComponentCost, "stringInput");
		
		ObjReference objReference = new ObjReference();
		objReference.DOI.set(inputRefDOIComponentCost_Value);
		objReference.Reference.set(inputRefComponentCost_Value);
		
		objReference = ReferencesController.updateRef("delete", "costPeripherals", "ComponentName",inputComponentName_Value, selection.get(0).ComponentName.getValue(), "RefIDComponentCost", objReference);

		try {
			Connection con = Database.getConnection("costPeripherals");
			Statement state = con.createStatement();
			state.executeUpdate("DELETE FROM costPeripherals WHERE ID = "+selection.get(0).ID.getValue()+"");

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		//close Edit-Pane:
		EditCostsPeripheralsController.this.cancelEditPeripherals(event);
	}

}
