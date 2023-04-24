package de.dominikemmel.reflowlab.controller.solvent;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ObjInorganicSolvent {

	public IntegerProperty ID = new SimpleIntegerProperty();
	public StringProperty SolventStructuralFormula = new SimpleStringProperty();
	public DoubleProperty c = new SimpleDoubleProperty();
	public DoubleProperty density = new SimpleDoubleProperty();
	public DoubleProperty dynViscosity = new SimpleDoubleProperty();
	public DoubleProperty kinViscosity = new SimpleDoubleProperty();
	public StringProperty editDate = new SimpleStringProperty();
	public IntegerProperty RefIDdensity = new SimpleIntegerProperty();
	public IntegerProperty RefIDdynViscosity = new SimpleIntegerProperty();
	public IntegerProperty RefIDkinViscosity = new SimpleIntegerProperty();

	public IntegerProperty IDProperty() {
		return ID;
	}

	public StringProperty SolventStructuralFormulaProperty() {
		return SolventStructuralFormula;
	}

	public DoubleProperty cProperty() {
		return c;
	}

	public DoubleProperty densityProperty() {
		return density;
	}

	public DoubleProperty dynViscosityProperty() {
		return dynViscosity;
	}

	public DoubleProperty kinViscosityProperty() {
		return kinViscosity;
	}

	public StringProperty editDateProperty() {
		return editDate;
	}
	
	public IntegerProperty RefIDdensityProperty() {
		return RefIDdensity;
	}
	
	public IntegerProperty RefIDdynViscosityProperty() {
		return RefIDdynViscosity;
	}
	
	public IntegerProperty RefIDkinViscosityProperty() {
		return RefIDkinViscosity;
	}


	// Using constructor to set values of properties.
	public ObjInorganicSolvent(int IDValue, String SolventStructuralFormulaValue, double cValue, double densityValue, double dynViscosityValue, double kinViscosityValue, String editDateValue, int RefIDdensityValue, int RefIDdynViscosityValue, int RefIDkinViscosityValue) {
		ID.set(IDValue);
		SolventStructuralFormula.set(SolventStructuralFormulaValue);
		c.set(cValue);
		density.set(densityValue);
		dynViscosity.set(dynViscosityValue);
		kinViscosity.set(kinViscosityValue);
		editDate.set(editDateValue);
		
		RefIDdensity.set(RefIDdensityValue);
		RefIDdynViscosity.set(RefIDdynViscosityValue);
		RefIDkinViscosity.set(RefIDkinViscosityValue);
	}

	// Default constructor.
	public ObjInorganicSolvent(){}

}

