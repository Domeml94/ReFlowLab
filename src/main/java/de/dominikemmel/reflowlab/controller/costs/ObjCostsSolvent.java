package de.dominikemmel.reflowlab.controller.costs;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ObjCostsSolvent {

	public IntegerProperty ID = new SimpleIntegerProperty();
	public StringProperty Solvent = new SimpleStringProperty();
	public DoubleProperty CSolvent = new SimpleDoubleProperty();
	public DoubleProperty MSolvent = new SimpleDoubleProperty();
	public IntegerProperty RefIDMSolvent = new SimpleIntegerProperty();
	public IntegerProperty RefIDCSolvent = new SimpleIntegerProperty();
	public StringProperty editDate = new SimpleStringProperty();

	public IntegerProperty IDProperty() {
		return ID;
	}
	public StringProperty SolventProperty() {
		return Solvent;
	}
	public DoubleProperty MSolventProperty() {
		return MSolvent;
	}
	public DoubleProperty CSolventProperty() {
		return CSolvent;
	}
	public IntegerProperty RefIDMSolventProperty() {
		return RefIDMSolvent;
	}
	public IntegerProperty RefIDCSolventProperty() {
		return RefIDCSolvent;
	}
	public StringProperty editDateProperty() {
		return editDate;
	}

	// Using constructor to set values of properties.
	public ObjCostsSolvent(int IDValue, String SolventValue, double MSolventValue, double CSolventValue, int RefIDMSolventValue, int RefIDCSolventValue, String editDateValue) {
		ID.set(IDValue);
		Solvent.set(SolventValue);
		MSolvent.set(MSolventValue);
		CSolvent.set(CSolventValue);
		RefIDMSolvent.set(RefIDMSolventValue);
		RefIDCSolvent.set(RefIDCSolventValue);
		editDate.set(editDateValue);
	}

	// Default constructor.
	public ObjCostsSolvent(){}


}
