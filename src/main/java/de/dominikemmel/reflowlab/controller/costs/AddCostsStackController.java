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
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import de.dominikemmel.reflowlab.Database;
import de.dominikemmel.reflowlab.VariousMethods;
import de.dominikemmel.reflowlab.controller.references.ObjReference;
import de.dominikemmel.reflowlab.controller.references.ReferencesController;

public class AddCostsStackController implements javafx.fxml.Initializable {
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
	
	
	String table = "costStack";

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
	public void cancelAddASR(ActionEvent event) {
		//close Add-Pane:
		Stage stage = (Stage) addPane.getScene().getWindow();
		stage.close();
	}
	// Event Listener on Button.onAction
	@FXML
	public void addAddASR(ActionEvent event) throws ClassNotFoundException, SQLException {

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
			Connection con = Database.getConnection(table);
			Statement state = con.createStatement();
			state.executeUpdate("INSERT INTO "+table+"(ID, ComponentName, ComponentType, ComponentCost, CostUnit, editDate)"
					+" VALUES(DEFAULT, '"+objCostsStack.ComponentName.getValue()+"', '"
					+objCostsStack.ComponentType.getValue()+"', "+objCostsStack.ComponentCost.getValue()+", '"
					+objCostsStack.CostUnit.getValue()+"', "
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
		AddCostsStackController.this.cancelAddASR(event);


	}
}
