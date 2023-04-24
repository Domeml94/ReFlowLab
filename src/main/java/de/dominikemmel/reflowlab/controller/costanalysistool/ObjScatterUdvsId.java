package de.dominikemmel.reflowlab.controller.costanalysistool;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ObjScatterUdvsId {
	public StringProperty title = new SimpleStringProperty();
	public DoubleProperty Ud = new SimpleDoubleProperty();
	public DoubleProperty Uct = new SimpleDoubleProperty();
	public DoubleProperty Uohm = new SimpleDoubleProperty();
	public DoubleProperty UctAnolyte = new SimpleDoubleProperty();
	public DoubleProperty UctCatholyte = new SimpleDoubleProperty();
	public DoubleProperty UConcAnolyte = new SimpleDoubleProperty();
	public DoubleProperty UConcCatholyte = new SimpleDoubleProperty();
	public DoubleProperty UConc = new SimpleDoubleProperty();
	public DoubleProperty id = new SimpleDoubleProperty();

	public StringProperty titleProperty() {
		return title;
	}
	public DoubleProperty UdProperty() {
		return Ud;
	}
	public DoubleProperty UctProperty() {
		return Uct;
	}
	public DoubleProperty UohmProperty() {
		return Uohm;
	}
	public DoubleProperty UctAnolyteProperty() {
		return UctAnolyte;
	}
	public DoubleProperty UctCatholyteProperty() {
		return UctCatholyte;
	}
	public DoubleProperty UConcAnolyteProperty() {
		return UConcAnolyte;
	}
	public DoubleProperty UConcCatholyteProperty() {
		return UConcCatholyte;
	}
	public DoubleProperty UConcProperty() {
		return UConc;
	}
	public DoubleProperty idProperty() {
		return id;
	}

	public ObjScatterUdvsId(String titleValue, double UdValue, double UctValue,
			double UohmValue, double UctAnolyteValue, double UctCatholyteValue, double UConcAnolyteValue, double UConcCatholyteValue, double UConcValue, double idValue) {
		title.set(titleValue);
		Ud.set(UdValue);
		Uct.set(UctValue);
		Uohm.set(UohmValue);
		UctAnolyte.set(UctAnolyteValue);
		UctCatholyte.set(UctCatholyteValue);
		UConcAnolyte.set(UConcAnolyteValue);
		UConcCatholyte.set(UConcCatholyteValue);
		UConc.set(UConcValue);
		id.set(idValue);
	}

	public ObjScatterUdvsId() {}


}
