package de.dominikemmel.reflowlab.controller.costs;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ObjCostsSalt {

	public IntegerProperty ID = new SimpleIntegerProperty();
	public StringProperty Salt = new SimpleStringProperty();
	public DoubleProperty MSalt = new SimpleDoubleProperty();
	public DoubleProperty CSalt = new SimpleDoubleProperty();
	public IntegerProperty RefIDMSalt = new SimpleIntegerProperty();
	public IntegerProperty RefIDCSalt = new SimpleIntegerProperty();
	public StringProperty editDate = new SimpleStringProperty();

	public IntegerProperty IDProperty() {
		return ID;
	}
	public StringProperty SaltProperty() {
		return Salt;
	}
	public DoubleProperty MSaltProperty() {
		return MSalt;
	}
	public DoubleProperty CSaltProperty() {
		return CSalt;
	}
	public IntegerProperty RefIDMSaltProperty() {
		return RefIDMSalt;
	}
	public IntegerProperty RefIDCSaltProperty() {
		return RefIDCSalt;
	}
	public StringProperty editDateProperty() {
		return editDate;
	}

	// Using constructor to set values of properties.
	public ObjCostsSalt(int IDValue, String SaltValue, double MSaltValue, double CSaltValue, int RefIDMSaltValue, int RefIDCSaltValue, String editDateValue) {
		ID.set(IDValue);
		Salt.set(SaltValue);
		MSalt.set(MSaltValue);
		CSalt.set(CSaltValue);
		RefIDMSalt.set(RefIDMSaltValue);
		RefIDCSalt.set(RefIDCSaltValue);
		editDate.set(editDateValue);

	}

	// Default constructor.
	public ObjCostsSalt(){}

}
