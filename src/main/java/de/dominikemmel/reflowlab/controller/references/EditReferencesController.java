package de.dominikemmel.reflowlab.controller.references;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import de.dominikemmel.reflowlab.Database;
import de.dominikemmel.reflowlab.VariousMethods;

public class EditReferencesController implements javafx.fxml.Initializable {

	//passing selected data from ReferenceController:
	private ObservableList<ObjReference> selection;

	@FXML
	private AnchorPane editPane;
	@FXML
	private TextArea inputDOI;
	@FXML
	private TextArea inputReference;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Platform.runLater(new Runnable() {

            @Override
            public void run() {
            	inputReference.requestFocus();
            }
        });
	}

	public void setSelectionData(ObservableList<ObjReference> selection) {
		this.selection = selection;

		inputDOI.setText(selection.get(0).DOI.getValue());
		inputReference.setText(selection.get(0).Reference.getValue());

	}

	// Event Listener on Button.onAction
	@FXML
	public void cancelEditReferences(ActionEvent event) {
		Stage stage = (Stage) editPane.getScene().getWindow();
		stage.close();
	}
	// Event Listener on Button.onAction
	@FXML
	public void editEditReferences(ActionEvent event) {
		ObjReference objReference = new ObjReference();

		String inputDOI_Value = VariousMethods.getTextAreaInput(inputDOI);
		String inputReference_Value = VariousMethods.getTextAreaInput(inputReference);

		objReference.DOI.set(inputDOI_Value);
		objReference.Reference.set(inputReference_Value);


		try {
			Connection con = Database.getConnection("reference");
			Statement state = con.createStatement();
			state.executeUpdate("UPDATE reference "
					+ "SET ID = "+selection.get(0).ID.getValue()+", DOI = '"+objReference.DOI.getValue()+
					"', Reference = '"+objReference.Reference.getValue()+"', editDate = CURRENT_TIMESTAMP"
							+ " WHERE ID = "+selection.get(0).ID.getValue()+"");

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		//close Edit-Pane:
		EditReferencesController.this.cancelEditReferences(event);
	}
	// Event Listener on Button.onAction
	@FXML
	public void deleteEditReferences(ActionEvent event) {
		
		if (selection.get(0).Comment.getValue() != "") {
			
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/de/dominikemmel/reflowlab/controller/references/fxml/refDel.fxml"));
				Parent rootRefDel = loader.load();
				loader.setControllerFactory((Class<?> controllerType) -> {
					if (controllerType == RefDelPaneController.class) {
						RefDelPaneController controller = new RefDelPaneController();
						controller.setSelectionData(selection);
						return controller;
					} else {
						try {
							return controllerType.newInstance();
						} catch (Exception e) {
							throw new RuntimeException(e);
						}
					}
				});

				RefDelPaneController controller = loader.<RefDelPaneController>getController();
				controller.setSelectionData(selection);

				Stage stage = new Stage();
				stage.setScene(new Scene(rootRefDel));
				stage.show();

				stage.setTitle("ReFlowLab - Delete reference?");
				
				stage.getScene().getStylesheets().add(getClass().getResource("/de/dominikemmel/reflowlab/style/reflowlabStyle1.css").toExternalForm());
				
				stage.setOnCloseRequest(event2 -> {
					//close Edit-Pane:
					EditReferencesController.this.cancelEditReferences(event);
				});
				
				stage.setOnHidden(event3 -> {
					//close Edit-Pane:
					EditReferencesController.this.cancelEditReferences(event);
				});
				
			} catch (IOException e) {
					e.printStackTrace();
				}
			
		} else {
			try {
				Connection con = Database.getConnection("reference");
				Statement state = con.createStatement();
				state.executeUpdate("DELETE FROM reference WHERE ID = "+selection.get(0).ID.getValue()+"");

			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			
			//close Edit-Pane:
			EditReferencesController.this.cancelEditReferences(event);
		}



	}

}
