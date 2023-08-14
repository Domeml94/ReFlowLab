package de.dominikemmel.reflowlab.controller.maincontrol;

import java.net.URL;
import java.util.ResourceBundle;

import de.dominikemmel.reflowlab.controller.costanalysistool.CostAnalysisToolController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;

public class ConsoleOverviewController implements Initializable {
	
    @FXML
    private PrintStream ps ;
    
	@FXML
	private TextArea textAreaUI;
	
	@FXML
	private Button btnClose;
	
	@FXML
	private AnchorPane consoleAnchorPane;
	
	public static TextArea staticTxtArea;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
//		ps = new PrintStream(new Console(console)) ;
		
		staticTxtArea = textAreaUI;
		
	}
//	
//    public class Console extends OutputStream {
//        private TextArea console;
//
//        public Console(TextArea console) {
//            this.console = console;
//        }
//
//        public void appendText(String valueOf) {
//            Platform.runLater(() -> console.appendText(valueOf));
//        }
//
//        public void write(int b) throws IOException {
//            appendText(String.valueOf((char)b));
//        }
//    }
	
	
	
	@FXML
	public void btnCloseConsoleEvent(ActionEvent event) {
		
		ConsoleOverviewController.this.closeEvent();
		
	}
	
	
	public void closeEvent() {
		Stage consoleStage = (Stage) consoleAnchorPane.getScene().getWindow();
		consoleStage.hide();
	}



}
