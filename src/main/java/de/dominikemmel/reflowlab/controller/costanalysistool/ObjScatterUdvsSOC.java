package de.dominikemmel.reflowlab.controller.costanalysistool;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ObjScatterUdvsSOC {
	public StringProperty title = new SimpleStringProperty();
	public DoubleProperty Ud = new SimpleDoubleProperty();
	public DoubleProperty soc = new SimpleDoubleProperty();

	public StringProperty titleProperty() {
		return title;
	}
	public DoubleProperty UdProperty() {
		return Ud;
	}
	public DoubleProperty socProperty() {
		return soc;
	}

	public ObjScatterUdvsSOC(String titleValue, double UdValue, double socValue) {
		title.set(titleValue);
		Ud.set(UdValue);
		soc.set(socValue);
	}

	public ObjScatterUdvsSOC() {}
}
