package de.dominikemmel.reflowlab.controller.costanalysistool;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ObjSpiderWebData {

	public StringProperty title = new SimpleStringProperty();
	public DoubleProperty cTotal = new SimpleDoubleProperty();
	public DoubleProperty cPower = new SimpleDoubleProperty();
	public DoubleProperty cElectrolyte = new SimpleDoubleProperty();
	public DoubleProperty cNPV = new SimpleDoubleProperty();

	public StringProperty titleProperty() {
		return title;
	}
	public DoubleProperty cTotalProperty() {
		return cTotal;
	}
	public DoubleProperty cPowerProperty() {
		return cPower;
	}
	public DoubleProperty cElectrolyteProperty() {
		return cElectrolyte;
	}
	public DoubleProperty cNPVProperty() {
		return cNPV;
	}

	public ObjSpiderWebData(String titleValue, Double cTotalValue, Double cPowerValue, Double cElectrolyteValue, Double cNPVValue) {
		title.set(titleValue);
		cTotal.set(cTotalValue);
		cPower.set(cPowerValue);
		cElectrolyte.set(cElectrolyteValue);
		cNPV.set(cNPVValue);
	}

	public ObjSpiderWebData() {}
}
