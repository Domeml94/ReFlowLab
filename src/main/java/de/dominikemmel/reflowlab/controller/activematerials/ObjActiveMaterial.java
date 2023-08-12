package de.dominikemmel.reflowlab.controller.activematerials;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ObjActiveMaterial {

	public IntegerProperty ID = new SimpleIntegerProperty();
	public StringProperty ABBREVIATION = new SimpleStringProperty();
	public StringProperty NAME = new SimpleStringProperty();
	public StringProperty STRUCTURALFORMULA = new SimpleStringProperty();
	public DoubleProperty M = new SimpleDoubleProperty();
	public IntegerProperty N = new SimpleIntegerProperty();
	public IntegerProperty NumberH = new SimpleIntegerProperty();
	public DoubleProperty CAM = new SimpleDoubleProperty();
	public StringProperty SOLVENT = new SimpleStringProperty();
	public StringProperty Salt = new SimpleStringProperty();
	public DoubleProperty SaltC = new SimpleDoubleProperty();
	public DoubleProperty PH = new SimpleDoubleProperty();
	public DoubleProperty E = new SimpleDoubleProperty();
	public StringProperty Category = new SimpleStringProperty();
	public StringProperty EDITDATE = new SimpleStringProperty();
	public IntegerProperty RefIDn = new SimpleIntegerProperty();
	public IntegerProperty RefIDNumberH = new SimpleIntegerProperty();
	public IntegerProperty RefIDCAM = new SimpleIntegerProperty();
	public IntegerProperty RefIDE = new SimpleIntegerProperty();

	public IntegerProperty IDProperty() {
		return ID;
	}
	public StringProperty ABBREVIATIONProperty() {
		return ABBREVIATION;
	}
	public StringProperty NAMEProperty() {
		return NAME;
	}
	public StringProperty STRUCTURALFORMULAProperty() {
		return STRUCTURALFORMULA;
	}
	public DoubleProperty MProperty() {
		return M;
	}
	public IntegerProperty NProperty() {
		return N;
	}
	public IntegerProperty NumberHProperty() {
		return NumberH;
	}
	public DoubleProperty CAMProperty() {
		return CAM;
	}
	public StringProperty SOLVENTProperty() {
		return SOLVENT;
	}
	public StringProperty SaltProperty() {
		return Salt;
	}
	public DoubleProperty SaltCProperty() {
		return SaltC;
	}
	public DoubleProperty PHProperty() {
		return PH;
	}
	public DoubleProperty EProperty() {
		return E;
	}
	public StringProperty CategoryProperty() {
		return Category;
	}
	public StringProperty EDITDATEProperty() {
		return EDITDATE;
	}
	public IntegerProperty RefIDnProperty() {
		return RefIDn;
	}
	public IntegerProperty RefIDNumberHProperty() {
		return RefIDNumberH;
	}
	public IntegerProperty RefIDCAMProperty() {
		return RefIDCAM;
	}
	public IntegerProperty RefIDEProperty() {
		return RefIDE;
	}

	// Using constructor to set values of properties.
	public ObjActiveMaterial(int IDValue, String ABBREVIATIONValue, String NAMEValue, String STRUCTURALFORMULAValue, double MValue, int NValue, int NumberHValue, double CAMValue, String SOLVENTValue, String SaltValue, double SaltCValue, double PHValue, double EValue, String CategoryValue, String EDITDATEValue, int RefIDnValue, int RefIDNumberHValue, int RefIDCAMValue, int RefIDEValue) {
		ID.set(IDValue);
		ABBREVIATION.set(ABBREVIATIONValue);
		NAME.set(NAMEValue);
		STRUCTURALFORMULA.set(STRUCTURALFORMULAValue);
		M.set(MValue);
		N.set(NValue);
		NumberH.set(NumberHValue);
		CAM.set(CAMValue);
		SOLVENT.set(SOLVENTValue);
		Salt.set(SaltValue);
		SaltC.set(SaltCValue);
		PH.set(PHValue);
		E.set(EValue);
		Category.set(CategoryValue);
		EDITDATE.set(EDITDATEValue);
		RefIDn.set(RefIDnValue);
		RefIDNumberH.set(RefIDNumberHValue);
		RefIDCAM.set(RefIDCAMValue);
		RefIDE.set(RefIDEValue);

	}

	// Default constructor.
	public ObjActiveMaterial(){}

}
