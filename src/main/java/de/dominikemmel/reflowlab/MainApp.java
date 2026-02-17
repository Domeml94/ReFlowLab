package de.dominikemmel.reflowlab;

import java.awt.Color;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;

import de.dominikemmel.reflowlab.controller.maincontrol.ConsoleOverviewController;
import de.dominikemmel.reflowlab.controller.maincontrol.ReaderThread;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;



public class MainApp extends Application {
	
	private final PipedInputStream pipeIn = new PipedInputStream();
    private final PipedInputStream pipeIn2 = new PipedInputStream();
    Thread errorThrower;
    private Thread reader;
    private Thread reader2;
    boolean quit; 
    private TextArea txtArea;

	@Override
	public void start(Stage primaryStage) throws IOException {
		
		Updates.updateDB();

		Parent root = FXMLLoader.load(getClass().getResource("/de/dominikemmel/reflowlab/controller/maincontrol/fxml/main.fxml"));
		
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/de/dominikemmel/reflowlab/style/reflowlabStyle1.css").toExternalForm());
		primaryStage.initStyle(StageStyle.DECORATED);
		
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setTitle("ReFlowLab");
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/de/dominikemmel/reflowlab/img/logo_simple/1x/logo_simple1x.png")));
		
//		MainController.dbTest();

//		Database.createConnection("activeMaterial");
//		Database.createConnection("solvent");
//		Database.createConnection("electrolyte");
//		Database.createConnection("costAnalysis");
//		Database.createConnection("reference");
		
        
        txtArea = ConsoleOverviewController.staticTxtArea;
        
        //Thread execution for reading output stream
        executeReaderThreads();
        
        //Thread closing on stag close event
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
        @Override
        public void handle(WindowEvent e) {
            
        closeThread();            
        Platform.exit();
        System.exit(0);
        }
        });


	}
	
	//method to handle thread closing on stage closing
    synchronized void closeThread() {
       System.out.println("Message: Stage is closed.");  
       this.quit = true;
       notifyAll();
       try { this.reader.join(1000L); this.pipeIn.close(); } catch (Exception e) {
       }try { this.reader2.join(1000L); this.pipeIn2.close(); } catch (Exception e) {
       }System.exit(0);
    }

    /**
     * @param args the command line arguments
     */

	public static void main(String[] args) throws Exception {

		launch(args);
	}
	
	public void executeReaderThreads() {
    	try {
    		PipedOutputStream pout = new PipedOutputStream(this.pipeIn);
    		System.setOut(new PrintStream(pout, true));
    		}
    	catch (IOException io) {
    		
    	}
    	catch (SecurityException se) {
    		
    	}

	    try {
	      PipedOutputStream pout2 = new PipedOutputStream(this.pipeIn2);
	      System.setErr(new PrintStream(pout2, true));
	    }
	    catch (IOException io) {
	    	
	    }
	    catch (SecurityException se) {
	    	
	    }
	    
	    ReaderThread obj = new ReaderThread(pipeIn, pipeIn2, errorThrower, reader, reader2, quit, txtArea);

	          
    }
}
