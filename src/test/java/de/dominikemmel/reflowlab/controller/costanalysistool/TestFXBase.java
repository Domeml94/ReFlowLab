package de.dominikemmel.reflowlab.controller.costanalysistool;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;

import de.dominikemmel.reflowlab.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

public abstract class TestFXBase extends ApplicationTest {

	  @Override
	  public void start (Stage stage) throws Exception {
	    Parent mainNode = FXMLLoader.load(Main.class.getResource("sample.fxml"));
	    stage.setScene(new Scene(mainNode));
	    stage.show();
	    stage.toFront();
	  }

	  @BeforeAll
	  public void setUp () throws Exception {
	  }

	  @AfterAll
	  public void tearDown () throws Exception {
	    FxToolkit.hideStage();
	    release(new KeyCode[]{});
	    release(new MouseButton[]{});
	  }

//	  @Test
//	  public void testEnglishInput () {
//	    clickOn("#inputField");
//	    write("This is a test!");
//	  }
}
