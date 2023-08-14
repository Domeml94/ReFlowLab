package de.dominikemmel.reflowlab.controller.electrolytes;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ObjElectrolytes {

	public IntegerProperty ID = new SimpleIntegerProperty();
	public StringProperty ActiveMaterial = new SimpleStringProperty();
	public StringProperty Solvent = new SimpleStringProperty();
	public StringProperty Salt = new SimpleStringProperty();
	public DoubleProperty cSalt = new SimpleDoubleProperty();
	public DoubleProperty pH = new SimpleDoubleProperty();
	public DoubleProperty maxSolubility = new SimpleDoubleProperty();
	public DoubleProperty DOx = new SimpleDoubleProperty();
	public DoubleProperty DRed = new SimpleDoubleProperty();
	public DoubleProperty kOx = new SimpleDoubleProperty();
	public DoubleProperty AlphaOx = new SimpleDoubleProperty();
	public DoubleProperty kRed = new SimpleDoubleProperty();
	public DoubleProperty AlphaRed = new SimpleDoubleProperty();
	public DoubleProperty degRate = new SimpleDoubleProperty();
	public DoubleProperty f = new SimpleDoubleProperty();
	public DoubleProperty fEloVol = new SimpleDoubleProperty();
	public DoubleProperty fConc = new SimpleDoubleProperty();
	public StringProperty note = new SimpleStringProperty();
	public BooleanProperty fSymCellCycl = new SimpleBooleanProperty();
	public DoubleProperty theoMaxCap = new SimpleDoubleProperty();
	public StringProperty editDate = new SimpleStringProperty();
	
	public IntegerProperty RefIDmaxSolubility = new SimpleIntegerProperty();
	public IntegerProperty RefIDDOx = new SimpleIntegerProperty();
	public IntegerProperty RefIDDRed = new SimpleIntegerProperty();
	public IntegerProperty RefIDkOx = new SimpleIntegerProperty();
	public IntegerProperty RefIDAlphaOx = new SimpleIntegerProperty();
	public IntegerProperty RefIDkRed = new SimpleIntegerProperty();
	public IntegerProperty RefIDAlphaRed = new SimpleIntegerProperty();
	public IntegerProperty RefIDdegRate = new SimpleIntegerProperty();
	public IntegerProperty RefIDf = new SimpleIntegerProperty();

	public IntegerProperty IDProperty() {
		return ID;
	}
	public StringProperty ActiveMaterialProperty() {
		return ActiveMaterial;
	}
	public StringProperty SolventProperty() {
		return Solvent;
	}
	public StringProperty SaltProperty() {
		return Salt;
	}
	public DoubleProperty cSaltProperty() {
		return cSalt;
	}
	public DoubleProperty pHProperty() {
		return pH;
	}
	public DoubleProperty maxSolubilityProperty() {
		return maxSolubility;
	}
	public DoubleProperty DOxProperty() {
		return DOx;
	}
	public DoubleProperty DRedProperty() {
		return DRed;
	}
	public DoubleProperty kOxProperty() {
		return kOx;
	}
	public DoubleProperty AlphaOxProperty() {
		return AlphaOx;
	}
	public DoubleProperty kRedProperty() {
		return kRed;
	}
	public DoubleProperty AlphaRedProperty() {
		return AlphaRed;
	}
	public DoubleProperty degRateProperty() {
		return degRate;
	}
	public DoubleProperty fProperty() {
		return f;
	}
	public DoubleProperty fEloVolProperty() {
		return fEloVol;
	}
	public DoubleProperty fConcProperty() {
		return fConc;
	}
	public StringProperty noteProperty() {
		return note;
	}
	public BooleanProperty fSymCellCyclProperty() {
		return fSymCellCycl;
	}
	public DoubleProperty theoMaxCapProperty() {
		return theoMaxCap;
	}
	public StringProperty editDateProperty() {
		return editDate;
	}
	
	public IntegerProperty RefIDmaxSolubilityProperty() {
		return RefIDmaxSolubility;
	}
	public IntegerProperty RefIDDOxProperty() {
		return RefIDDOx;
	}
	public IntegerProperty RefIDDRedProperty() {
		return RefIDDRed;
	}
	public IntegerProperty RefIDkOxProperty() {
		return RefIDkOx;
	}
	public IntegerProperty RefIDAlphaOxProperty() {
		return RefIDAlphaOx;
	}
	public IntegerProperty RefIDkRedProperty() {
		return RefIDkRed;
	}
	public IntegerProperty RefIDAlphaRedProperty() {
		return RefIDAlphaRed;
	}
	public IntegerProperty RefIDdegRateProperty() {
		return RefIDdegRate;
	}
	public IntegerProperty RefIDfProperty() {
		return RefIDf;
	}

	// Using constructor to set values of properties.
	public ObjElectrolytes(int IDValue, String ActiveMaterialValue, String SolventValue, String SaltValue, double cSaltValue, double pHValue, double maxSolubilityValue, double DOxValue, double DRedValue, double kOxValue, double AlphaOxValue, double kRedValue, double AlphaRedValue, double degRateValue, double fValue, double fEloVolValue, double fConcValue, String noteValue, boolean fSymCellCyclValue, double theoMaxCapValue, String editDateValue, int RefIDmaxSolubilityValue, int RefIDDOxValue, int RefIDDRedValue, int RefIDkOxValue, int RefIDAlphaOxValue, int RefIDkRedValue, int RefIDAlphaRedValue, int RefIDdegRateValue, int RefIDfValue) {
		ID.set(IDValue);
		ActiveMaterial.set(ActiveMaterialValue);
		Solvent.set(SolventValue);
		Salt.set(SaltValue);
		cSalt.set(cSaltValue);
		pH.set(pHValue);
		maxSolubility.set(maxSolubilityValue);
		DOx.set(DOxValue);
		DRed.set(DRedValue);
		kOx.set(kOxValue);
		AlphaOx.set(AlphaOxValue);
		kRed.set(kRedValue);
		AlphaRed.set(AlphaRedValue);
		degRate.set(degRateValue);
		f.set(fValue);
		fEloVol.set(fEloVolValue);
		fConc.set(fConcValue);
		note.set(noteValue);
		fSymCellCycl.set(fSymCellCyclValue);
		theoMaxCap.set(theoMaxCapValue);
		editDate.set(editDateValue);
		
		RefIDmaxSolubility.set(RefIDmaxSolubilityValue);
		RefIDDOx.set(RefIDDOxValue);
		RefIDDRed.set(RefIDDRedValue);
		RefIDkOx.set(RefIDkOxValue);
		RefIDAlphaOx.set(RefIDAlphaOxValue);
		RefIDkRed.set(RefIDkRedValue);
		RefIDAlphaRed.set(RefIDAlphaRedValue);
		RefIDdegRate.set(RefIDdegRateValue);
		RefIDf.set(RefIDfValue);

	}

	// Default constructor.
	public ObjElectrolytes(){}

}
