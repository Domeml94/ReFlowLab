package de.dominikemmel.reflowlab.controller.costs;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ObjCostsStack {

	public IntegerProperty ID = new SimpleIntegerProperty();
	public StringProperty ComponentName = new SimpleStringProperty();
	public StringProperty ComponentType = new SimpleStringProperty();
	public DoubleProperty ComponentCost = new SimpleDoubleProperty();
	public IntegerProperty RefIDComponentCost = new SimpleIntegerProperty();
	public StringProperty CostUnit = new SimpleStringProperty();
	public StringProperty editDate = new SimpleStringProperty();

	public IntegerProperty IDProperty() {
		return ID;
	}
	public StringProperty ComponentNameProperty() {
		return ComponentName;
	}
	public StringProperty ComponentTypeProperty() {
		return ComponentType;
	}
	public DoubleProperty ComponentCostProperty() {
		return ComponentCost;
	}
	public IntegerProperty RefIDComponentCostProperty() {
		return RefIDComponentCost;
	}
	public StringProperty CostUnitProperty() {
		return CostUnit;
	}
	public StringProperty editDateProperty() {
		return editDate;
	}

	// Using constructor to set values of properties.
	public ObjCostsStack(int IDValue, String ComponentNameValue, String ComponentTypeValue, double ComponentCostValue, int RefIDComponentCostValue, String CostUnitValue, String editDateValue) {
		ID.set(IDValue);
		ComponentName.set(ComponentNameValue);
		ComponentType.set(ComponentTypeValue);
		ComponentCost.set(ComponentCostValue);
		RefIDComponentCost.set(RefIDComponentCostValue);
		CostUnit.set(CostUnitValue);
		editDate.set(editDateValue);

	}

	// Default constructor.
	public ObjCostsStack(){}


}
