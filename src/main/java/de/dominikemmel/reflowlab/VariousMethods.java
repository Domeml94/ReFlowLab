package de.dominikemmel.reflowlab;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class VariousMethods {

	public static void Browser (String url) {

    if(Desktop.isDesktopSupported()){
        Desktop desktop = Desktop.getDesktop();
        try {
            desktop.browse(new URI(url));
        } catch (IOException | URISyntaxException e) {

            e.printStackTrace();
        }
    }else{
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec("xdg-open " + url);
        } catch (IOException e) {

            e.printStackTrace();
            }
        }
    }

	public static <Any> Any getTextFieldInput(TextField field, String input) {

		Any output = null;
		String txt = field.getText();

		switch (input) {
		case "stringInput":
			if (txt == "") {
				output = (Any)(String)"";
				break;
			} else if (null == txt) {
				output = (Any)(String)"";
				break;
			} else {
				output = (Any)field.getText();
			}
			break;
		case "doubleInput":
			if (txt == "") {
				output = (Any)(Double)0.;
				break;
			} else if (null == txt) {
				output = (Any)(Double)0.;
				break;
			} else {
				output = (Any)Double.valueOf(field.getText());
			}
			break;
		case "integerInput":
			if (txt == "") {
				output = (Any)(Integer)0;
				break;
			} else if (null == txt) {
				output = (Any)(Integer)0;
				break;
			} else {
				output = (Any)Integer.valueOf(field.getText());
			}
			break;
		}
		return output;

	}
	
	public static <Any> Any getTextAreaInput(TextArea field) {

		Any output = null;
		String txt = field.getText();
		
		if (txt == "") {
			output = null;
		} else if (null == txt) {
			output = null;
		} else {
			output = (Any)field.getText();
		}
		
		return output;

	}

}
