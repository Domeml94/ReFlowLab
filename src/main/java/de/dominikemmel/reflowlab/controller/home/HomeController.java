package de.dominikemmel.reflowlab.controller.home;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import de.dominikemmel.reflowlab.controller.electrolytes.ElectrolytesController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class HomeController implements javafx.fxml.Initializable {
	
	@FXML
	private Button btnRagone;
	@FXML
	private Button btnCostAnalysis;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	@FXML
	private void btnRagone(ActionEvent event) {

		try {
			Parent rootRagoneTool;
			rootRagoneTool = FXMLLoader.load(getClass().getResource("/de/dominikemmel/reflowlab/controller/ragonetool/fxml/ragoneTool.fxml"));
			Scene scene = new Scene(rootRagoneTool);
			scene.getStylesheets().add(getClass().getResource("/de/dominikemmel/reflowlab/style/reflowlabStyle1.css").toExternalForm());
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
			stage.setTitle("ReFlowLab - RagoneTool");
			stage.getIcons().add(new Image(getClass().getResourceAsStream("/de/dominikemmel/reflowlab/img/logo_simple/1x/logo_simple1x.png")));
			
			btnRagone.setDisable(true);
			
			stage.setOnCloseRequest(event2 -> {
				btnRagone.setDisable(false);
			});
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void btnCostAnalysis(ActionEvent event) {

		try {
			Parent rootCostAnalysisTool;
			rootCostAnalysisTool = FXMLLoader.load(getClass().getResource("/de/dominikemmel/reflowlab/controller/costanalysistool/fxml/costAnalysisTool.fxml"));
			Scene scene = new Scene(rootCostAnalysisTool);
//			scene.getStylesheets().add(getClass().getResource("/de/dominikemmel/reflowlab/controller/costanalysistool/style/costAnalysisTool.css").toExternalForm());
			scene.getStylesheets().add(getClass().getResource("/de/dominikemmel/reflowlab/style/reflowlabStyle1.css").toExternalForm());
			Stage stage = new Stage();
			stage.setScene(scene);

			stage.show();
			
			stage.setTitle("ReFlowLab - CostAnalysisTool");
			stage.getIcons().add(new Image(getClass().getResourceAsStream("/de/dominikemmel/reflowlab/img/logo_simple/1x/logo_simple1x.png")));
			
			btnCostAnalysis.setDisable(true);
			
			stage.setOnCloseRequest(event2 -> {
				btnCostAnalysis.setDisable(false);
			});
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}




}
