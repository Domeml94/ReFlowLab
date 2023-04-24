package de.dominikemmel.reflowlab.controller.references;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ObjReference {


	public IntegerProperty ID = new SimpleIntegerProperty();
	public StringProperty DOI = new SimpleStringProperty();
	public StringProperty Reference = new SimpleStringProperty();
	public StringProperty Bound = new SimpleStringProperty();
	public StringProperty Comment = new SimpleStringProperty();
	public StringProperty editDate = new SimpleStringProperty();
	public BooleanProperty refExist = new SimpleBooleanProperty();

	public IntegerProperty IDProperty() {
		return ID;
	}
	public StringProperty DOIProperty() {
		return DOI;
	}
	public StringProperty ReferenceProperty() {
		return Reference;
	}
	public StringProperty BoundProperty() {
		return Bound;
	}
	public StringProperty CommentProperty() {
		return Comment;
	}
	public StringProperty editDateProperty() {
		return editDate;
	}
	public BooleanProperty refExistProperty() {
		return refExist;
	}

	// Using constructor to set values of properties.
	public ObjReference(int IDValue, String DOIValue, String ReferenceValue, String BoundValue, String CommentValue, String editDateValue, boolean refExistValue) {
		ID.set(IDValue);
		DOI.set(DOIValue);
		Reference.set(ReferenceValue);
		Bound.set(BoundValue);
		Comment.set(CommentValue);
		editDate.set(editDateValue);
		refExist.set(refExistValue);

	}

	// Default constructor.
	public ObjReference(){}



}
