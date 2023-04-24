package de.dominikemmel.reflowlab.controller.costanalysistool;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ObjExportSelection {
	public StringProperty dataAbbreviation = new SimpleStringProperty();
	public StringProperty dataSolvent = new SimpleStringProperty();
	public StringProperty dataSaltSQL = new SimpleStringProperty();

	public StringProperty dataAbbreviationProperty() {
		return dataAbbreviation;
	}
	public StringProperty dataSolventProperty() {
		return dataSolvent;
	}
	public StringProperty dataSaltSQLProperty() {
		return dataSaltSQL;
	}

	public ObjExportSelection(String dataAbbreviationValue, String dataSolventValue, String dataSaltSQLValue) {
		dataAbbreviation.set(dataAbbreviationValue);
		dataSolvent.set(dataSolventValue);
		dataSaltSQL.set(dataSaltSQLValue);
	}

	public ObjExportSelection() {}
}
