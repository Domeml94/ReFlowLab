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

public class AddCostsPeripheralsController implements javafx.fxml.Initializable {
	@FXML
	private AnchorPane addPane;

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
	
	String table = "costPeripherals";

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

					if (objReference.Reference.get() != null) {
						inputRefComponentCost.setText(objReference.Reference.get());
					}
					
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
		    }
		});
	}
	
	@FXML
	public void inputComponentTypeEvent(ActionEvent event) {

	}
	

	// Event Listener on Button.onAction
	@FXML
	public void cancelAddPeripherals(ActionEvent event) {
		//close Add-Pane:
		Stage stage = (Stage) addPane.getScene().getWindow();
		stage.close();
	}
	// Event Listener on Button.onAction
	@FXML
	public void addAddPeripherals(ActionEvent event) throws ClassNotFoundException, SQLException {

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
			Connection con = Database.getConnection(table);
			Statement state = con.createStatement();
			state.executeUpdate("INSERT INTO "+table+"(ID, ComponentName, ComponentType, ComponentCost, CostUnit, editDate)"
					+" VALUES(DEFAULT, '"+objCostsPeripherals.ComponentName.getValue()+"', '"
					+objCostsPeripherals.ComponentType.getValue()+"', "+objCostsPeripherals.ComponentCost.getValue()+", '"
					+objCostsPeripherals.CostUnit.getValue()+"', "
					+"CURRENT_TIMESTAMP)");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//Ref Input
		String inputRefDOIComponentCost_Value = VariousMethods.getTextFieldInput(inputRefDOIComponentCost, "stringInput");
		String inputRefComponentCost_Value = VariousMethods.getTextFieldInput(inputRefComponentCost, "stringInput");
		
		ObjReference objReference = new ObjReference();
		objReference.DOI.set(inputRefDOIComponentCost_Value);
		objReference.Reference.set(inputRefComponentCost_Value);
		
		objReference = ReferencesController.updateRef("update", table, "ComponentName",inputComponentName_Value, null, "RefIDComponentCost", objReference);
		
		//close Add-Pane:
		AddCostsPeripheralsController.this.cancelAddPeripherals(event);


	}
}
