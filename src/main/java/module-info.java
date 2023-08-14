module reflowlab {
	exports de.dominikemmel.reflowlab.controller.activematerials;
	exports de.dominikemmel.reflowlab.controller.home;
	exports de.dominikemmel.reflowlab;
	exports de.dominikemmel.reflowlab.controller.solvent;
	exports de.dominikemmel.reflowlab.controller.electrolytes;
	exports de.dominikemmel.reflowlab.controller.costs;
	exports de.dominikemmel.reflowlab.controller.ragonetool;
	exports de.dominikemmel.reflowlab.controller.references;
	exports de.dominikemmel.reflowlab.controller.costanalysistool;
	exports de.dominikemmel.reflowlab.controller.maincontrol;

	requires java.desktop;
	requires java.sql;
	requires transitive javafx.base;
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires net.harawata.appdirs;
	requires org.jfree.fxgraphics2d;
	requires org.jfree.jfreechart;
	requires javafx.media;
	requires jlatexmath;
	requires javafx.web;
	requires javafx.swing;
//	requires org.testfx.framework.junit5;
	
	
	opens de.dominikemmel.reflowlab.controller.activematerials to javafx.graphics, javafx.fxml;
	opens de.dominikemmel.reflowlab.controller.home to javafx.graphics, javafx.fxml;
	opens de.dominikemmel.reflowlab to javafx.graphics, javafx.fxml;
	opens de.dominikemmel.reflowlab.controller.solvent to javafx.graphics, javafx.fxml;
	opens de.dominikemmel.reflowlab.controller.electrolytes to javafx.graphics, javafx.fxml;
	opens de.dominikemmel.reflowlab.controller.costs to javafx.graphics, javafx.fxml;
	opens de.dominikemmel.reflowlab.controller.ragonetool to javafx.graphics, javafx.fxml;
	opens de.dominikemmel.reflowlab.controller.references to javafx.graphics, javafx.fxml;
	opens de.dominikemmel.reflowlab.controller.costanalysistool to javafx.graphics, javafx.fxml;
	opens de.dominikemmel.reflowlab.controller.maincontrol to javafx.graphics, javafx.fxml;
}
