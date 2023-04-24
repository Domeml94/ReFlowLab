package de.dominikemmel.reflowlab.controller.solvent;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SolventController implements javafx.fxml.Initializable {
	@FXML
	private Button btnInorganicSolvents;
	@FXML
	private Button btnOrganicSolvents;
	@FXML
	private Pane tblSolventPane;

	ObjInorganicSolvent selectionInorg = new ObjInorganicSolvent();
	ObjOrganicSolvent selectionOrg = new ObjOrganicSolvent();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		SolventController.this.showInorganicSolvents();
	}




	// test which pane is active:
	private boolean inorganicActive = true;

	public boolean isInorganicActive() {
		return inorganicActive;
	}

	public void setInorganicActive(boolean inorganicActive) {
		this.inorganicActive = inorganicActive;
	}




	public void showInorganicSolvents() {
		//Load inorganic pane + load controller
		FXMLLoader tblLoader = new FXMLLoader(getClass().getResource("/de/dominikemmel/reflowlab/controller/solvent/fxml/solventInorg.fxml"));
		try {
			Pane view = tblLoader.load();
			tblSolventPane.getChildren().add(view);
		} catch (IOException e) {
			e.printStackTrace();
		}
		SolventInorgController tblController = tblLoader.getController();

		//Retrieve selection property from inorganic pane
		tblController.currentSelectionProperty().addListener((observable, oldSelection, newSelection) -> {
			selectionInorg.ID.set(newSelection.ID.get());
			selectionInorg.SolventStructuralFormula.set(newSelection.SolventStructuralFormula.get());
			selectionInorg.c.set(newSelection.c.get());
			selectionInorg.density.set(newSelection.density.get());
			selectionInorg.dynViscosity.set(newSelection.dynViscosity.get());
			selectionInorg.kinViscosity.set(newSelection.kinViscosity.get());
			selectionInorg.editDate.set(newSelection.editDate.get());
			selectionInorg.RefIDdensity.set(newSelection.RefIDdensity.get());
			selectionInorg.RefIDdynViscosity.set(newSelection.RefIDdynViscosity.get());
			selectionInorg.RefIDkinViscosity.set(newSelection.RefIDkinViscosity.get());
		});

		setInorganicActive(true);
	}




	public void showOrganicSolvents() {
		//Load organic pane + load controller
		FXMLLoader tblLoader = new FXMLLoader(getClass().getResource("/de/dominikemmel/reflowlab/controller/solvent/fxml/solventOrg.fxml"));
		try {
			Pane view = tblLoader.load();
			tblSolventPane.getChildren().add(view);
		} catch (IOException e) {
			e.printStackTrace();
		}
		SolventOrgController tblController = tblLoader.getController();

		//Retrieve selection property from organic pane
		tblController.currentSelectionProperty().addListener((observable, oldSelection, newSelection) -> {
			selectionOrg.ID.set(newSelection.ID.get());
			selectionOrg.SolventName.set(newSelection.SolventName.get());
			selectionOrg.SolventStructuralFormula.set(newSelection.SolventStructuralFormula.get());
			selectionOrg.density.set(newSelection.density.get());
			selectionOrg.dynViscosity.set(newSelection.dynViscosity.get());
			selectionOrg.kinViscosity.set(newSelection.kinViscosity.get());
			selectionOrg.ElimitCat.set(newSelection.ElimitCat.get());
			selectionOrg.ElimitAn.set(newSelection.ElimitAn.get());
			selectionOrg.editDate.set(newSelection.editDate.get());
			selectionOrg.RefIDdensity.set(newSelection.RefIDdensity.get());
			selectionOrg.RefIDdynViscosity.set(newSelection.RefIDdynViscosity.get());
			selectionOrg.RefIDkinViscosity.set(newSelection.RefIDkinViscosity.get());
			selectionOrg.RefIDElimitCat.set(newSelection.RefIDElimitCat.get());
			selectionOrg.RefIDElimitAn.set(newSelection.RefIDElimitAn.get());
		});

		setInorganicActive(false);
	}




	public void addSolvent() {
		if (isInorganicActive()) {

			try {
				Parent rootAddSolvent;
				rootAddSolvent = FXMLLoader.load(getClass().getResource("/de/dominikemmel/reflowlab/controller/solvent/fxml/addSolventInorg.fxml"));
				Stage stage = new Stage();
				stage.setScene(new Scene(rootAddSolvent));
				stage.show();
				stage.setTitle("ReFlowLab - Add Solvent inorganic");
				
				stage.getScene().getStylesheets().add(getClass().getResource("/de/dominikemmel/reflowlab/style/reflowlabStyle1.css").toExternalForm());
				


				stage.setOnCloseRequest(event2 -> {
					SolventController.this.showInorganicSolvents();
				});

				stage.setOnHidden(event3 -> {
					SolventController.this.showInorganicSolvents();
				});

			} catch (IOException e) {
				e.printStackTrace();
			}


		} else {

			try {
				Parent rootAddSolvent;
				rootAddSolvent = FXMLLoader.load(getClass().getResource("/de/dominikemmel/reflowlab/controller/solvent/fxml/addSolventOrg.fxml"));
				Stage stage = new Stage();
				stage.setScene(new Scene(rootAddSolvent));
				stage.show();
				
				stage.setTitle("ReFlowLab - Add Solvent organic");
				
				stage.getScene().getStylesheets().add(getClass().getResource("/de/dominikemmel/reflowlab/style/reflowlabStyle1.css").toExternalForm());
				


				stage.setOnCloseRequest(event2 -> {
					SolventController.this.showOrganicSolvents();
				});

				stage.setOnHidden(event3 -> {
					SolventController.this.showOrganicSolvents();
				});

			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}





	public void editSolvent() throws ClassNotFoundException, SQLException {
		if (isInorganicActive()) {

			try {

				FXMLLoader loader = new FXMLLoader(getClass().getResource("/de/dominikemmel/reflowlab/controller/solvent/fxml/editSolventInorg.fxml"));
				Parent rootEditActiveMaterial = loader.load();
				loader.setControllerFactory((Class<?> controllerType) -> {
					if (controllerType == EditSolventInorgController.class) {
						EditSolventInorgController controller = new EditSolventInorgController();
						try {
							controller.setSelectionData(selectionInorg);
						} catch (ClassNotFoundException | SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						return controller;
					} else {
						try {
							return controllerType.newInstance();
						} catch (Exception e) {
							throw new RuntimeException(e);
						}
					}
				});

				EditSolventInorgController controller = loader.<EditSolventInorgController>getController();
				controller.setSelectionData(selectionInorg);

				Stage stage = new Stage();
				stage.setScene(new Scene(rootEditActiveMaterial));
				stage.show();
				
				stage.setTitle("ReFlowLab - Edit Solvent inorganic");
				
				stage.getScene().getStylesheets().add(getClass().getResource("/de/dominikemmel/reflowlab/style/reflowlabStyle1.css").toExternalForm());

				stage.setOnCloseRequest(event2 -> {
					SolventController.this.showInorganicSolvents();
				});

				stage.setOnHidden(event3 -> {
					SolventController.this.showInorganicSolvents();
				});

			} catch (IOException e) {
				e.printStackTrace();
			}


		} else {

			try {

				FXMLLoader loader = new FXMLLoader(getClass().getResource("/de/dominikemmel/reflowlab/controller/solvent/fxml/editSolventOrg.fxml"));
				Parent rootEditActiveMaterial = loader.load();
				loader.setControllerFactory((Class<?> controllerType) -> {
					if (controllerType == EditSolventOrgController.class) {
						EditSolventOrgController controller = new EditSolventOrgController();
						try {
							controller.setSelectionData(selectionOrg);
						} catch (ClassNotFoundException | SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						return controller;
					} else {
						try {
							return controllerType.newInstance();
						} catch (Exception e) {
							throw new RuntimeException(e);
						}
					}
				});

				EditSolventOrgController controller = loader.<EditSolventOrgController>getController();
				controller.setSelectionData(selectionOrg);

				Stage stage = new Stage();
				stage.setScene(new Scene(rootEditActiveMaterial));
				stage.show();
				
				stage.setTitle("ReFlowLab - Edit Solvent organic");
				
				stage.getScene().getStylesheets().add(getClass().getResource("/de/dominikemmel/reflowlab/style/reflowlabStyle1.css").toExternalForm());


				stage.setOnCloseRequest(event2 -> {
					SolventController.this.showOrganicSolvents();
				});

				stage.setOnHidden(event3 -> {
					SolventController.this.showOrganicSolvents();
				});

			} catch (IOException e) {
				e.printStackTrace();
			}


		}
	}

	@FXML
	private void btnInorganicSolvents(ActionEvent event) {
		SolventController.this.showInorganicSolvents();
	}

	@FXML
	private void btnOrganicSolvents(ActionEvent event) {
		SolventController.this.showOrganicSolvents();
	}

	@FXML
	private void btnAddSolvent(ActionEvent event) {
		SolventController.this.addSolvent();
	}

	@FXML
	private void btnEditSolvent(ActionEvent event) throws ClassNotFoundException, SQLException {
		SolventController.this.editSolvent();
	}



}
