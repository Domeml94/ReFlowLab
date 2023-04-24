package de.dominikemmel.reflowlab.controller.costs;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ObjSeparatorParameter {

	public IntegerProperty ID = new SimpleIntegerProperty();
	public StringProperty SeparatorName = new SimpleStringProperty();
	public DoubleProperty Rasr = new SimpleDoubleProperty();
	public IntegerProperty RefIDRasr = new SimpleIntegerProperty();
	public StringProperty editDate = new SimpleStringProperty();

	public IntegerProperty IDProperty() {
		return ID;
	}
	public StringProperty SeparatorNameProperty() {
		return SeparatorName;
	}
	public DoubleProperty RasrProperty() {
		return Rasr;
	}
	public IntegerProperty RefIDRasrProperty() {
		return RefIDRasr;
	}
	public StringProperty editDateProperty() {
		return editDate;
	}

	// Using constructor to set values of properties.
	public ObjSeparatorParameter(int IDValue, String SeparatorNameValue, double RasrValue, int RefIDRasrValue, String editDateValue) {
		ID.set(IDValue);
		SeparatorName.set(SeparatorNameValue);
		Rasr.set(RasrValue);
		RefIDRasr.set(RefIDRasrValue);
		editDate.set(editDateValue);
	}

	// Default constructor.
	public ObjSeparatorParameter(){}


}
