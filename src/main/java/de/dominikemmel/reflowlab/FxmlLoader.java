package de.dominikemmel.reflowlab;

import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class FxmlLoader {
	private Pane view;

	public Pane getPage(String fileName) {

		try {
//			"de/dominikemmel/reflowlab/controller/" + fileName + ".fxml"
			URL fileUrl = Main.class.getResource(fileName);
			if (fileUrl == null) {
				throw new java.io.FileNotFoundException("FXML file can't be found");
			}

			FXMLLoader FXML = new FXMLLoader();
			view = FXMLLoader.load(fileUrl);

		}

		catch (Exception e) {
			System.out.println("No page " + fileName + " please check FileLoader.");

		}
		return view;
	}



	public Pane getPageByResource (String PageURL) {

		try {
			URL fileUrl = Main.class.getResource(PageURL);
			if (fileUrl == null) {
				throw new java.io.FileNotFoundException("FXML file can't be found");
			}

			FXMLLoader FXML = new FXMLLoader();
			view = FXMLLoader.load(fileUrl);
		}
		catch (Exception e) {
			System.out.println("No page " + PageURL + " please check FileLoader.");
		}

		return view;
	}

}
