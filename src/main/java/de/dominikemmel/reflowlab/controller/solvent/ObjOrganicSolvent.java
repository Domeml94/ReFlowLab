package de.dominikemmel.reflowlab.controller.solvent;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ObjOrganicSolvent {

	public IntegerProperty ID = new SimpleIntegerProperty();
	public StringProperty SolventName = new SimpleStringProperty();
	public StringProperty SolventStructuralFormula = new SimpleStringProperty();
	public DoubleProperty density = new SimpleDoubleProperty();
	public DoubleProperty dynViscosity = new SimpleDoubleProperty();
	public DoubleProperty kinViscosity = new SimpleDoubleProperty();
	public DoubleProperty ElimitCat = new SimpleDoubleProperty();
	public DoubleProperty ElimitAn = new SimpleDoubleProperty();
	public StringProperty editDate = new SimpleStringProperty();
	public IntegerProperty RefIDdensity = new SimpleIntegerProperty();
	public IntegerProperty RefIDdynViscosity = new SimpleIntegerProperty();
	public IntegerProperty RefIDkinViscosity = new SimpleIntegerProperty();
	public IntegerProperty RefIDElimitCat = new SimpleIntegerProperty();
	public IntegerProperty RefIDElimitAn = new SimpleIntegerProperty();

	public IntegerProperty IDProperty() {
		return ID;
	}

	public StringProperty SolventNameProperty() {
		return SolventName;
	}

	public StringProperty SolventStructuralFormulaProperty() {
		return SolventStructuralFormula;
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
	public DoubleProperty ElimitCatProperty() {
		return ElimitCat;
	}
	public DoubleProperty ElimitAnProperty() {
		return ElimitAn;
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
	
	public IntegerProperty RefIDElimitCatProperty() {
		return RefIDElimitCat;
	}
	
	public IntegerProperty RefIDElimitAnProperty() {
		return RefIDElimitAn;
	}


	// Using constructor to set values of properties.
	public ObjOrganicSolvent(int IDValue, String SolventNameValue, String SolventStructuralFormulaValue, double densityValue, double dynViscosityValue, double kinViscosityValue, double ElimitCatValue, double ElimitAnValue, String editDateValue, int RefIDdensityValue, int RefIDdynViscosityValue, int RefIDkinViscosityValue, int RefIDElimitCatValue, int RefIDElimitAnValue) {
		ID.set(IDValue);
		SolventName.set(SolventNameValue);
		SolventStructuralFormula.set(SolventStructuralFormulaValue);
		density.set(densityValue);
		dynViscosity.set(dynViscosityValue);
		kinViscosity.set(kinViscosityValue);
		ElimitCat.set(ElimitCatValue);
		ElimitAn.set(ElimitAnValue);
		editDate.set(editDateValue);
		RefIDdensity.set(RefIDdensityValue);
		RefIDdynViscosity.set(RefIDdynViscosityValue);
		RefIDkinViscosity.set(RefIDkinViscosityValue);
		RefIDElimitCat.set(RefIDElimitCatValue);
		RefIDElimitAn.set(RefIDElimitAnValue);
	}

	// Default constructor.
	public ObjOrganicSolvent(){}


}
