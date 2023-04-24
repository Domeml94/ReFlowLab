package de.dominikemmel.reflowlab.controller.costanalysistool;

import javafx.stage.Stage;


public class ReturnStage extends Stage {
	public ObjStackSelection showAndReturn(StackSelectionPaneController controll) {    
		
		super.showAndWait();
		return controll.getReturn();
	   }
}
