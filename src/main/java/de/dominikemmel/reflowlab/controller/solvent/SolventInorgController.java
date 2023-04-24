package de.dominikemmel.reflowlab.controller.solvent;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import de.dominikemmel.reflowlab.Database;
import de.dominikemmel.reflowlab.DecimalColumnFactory;

public class SolventInorgController implements javafx.fxml.Initializable {

	@FXML private TableView<ObjInorganicSolvent> tblInorgSolvent;

	@FXML private TableColumn<ObjInorganicSolvent, Integer> ID;
	@FXML private TableColumn<ObjInorganicSolvent, String> StructuralFormula;
	@FXML private TableColumn<ObjInorganicSolvent, Double> c;
	@FXML private TableColumn<ObjInorganicSolvent, Double> density;
	@FXML private TableColumn<ObjInorganicSolvent, Double> dynViscosity;
	@FXML private TableColumn<ObjInorganicSolvent, Double> kinViscosity;
	@FXML private TableColumn<ObjInorganicSolvent, String> editDate;
	@FXML private TableColumn<ObjInorganicSolvent, Integer> RefIDdensity;
	@FXML private TableColumn<ObjInorganicSolvent, Integer> RefIDdynViscosity;
	@FXML private TableColumn<ObjInorganicSolvent, Integer> RefIDkinViscosity;

	@FXML private TextFlow ID_TextFlow;
	@FXML private TextFlow StructuralFormula_TextFlow;
	@FXML private TextFlow c_TextFlow;
	@FXML private TextFlow density_TextFlow;
	@FXML private TextFlow dynViscosity_TextFlow;
	@FXML private TextFlow kinViscosity_TextFlow;
	@FXML private TextFlow editDate_TextFlow;

	private ObservableList<ObjInorganicSolvent> selection;

	private final ReadOnlyObjectWrapper<ObjInorganicSolvent> currentSelection = new ReadOnlyObjectWrapper<>();


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		SolventInorgController.this.reloadDataSolventInorg();
		SolventInorgController.this.addColumnNames();

        // set up click on table:
		tblInorgSolvent.setRowFactory( tv -> {
		    TableRow<ObjInorganicSolvent> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (event.getClickCount() == 1 && (! row.isEmpty()) ) {
		        	currentSelection.set(row.getItem());
		        }
		    });
		    return row ;
		});

	}


	@FXML
	private void addColumnNames() {
    	//column names:

    	//ID:
    	Text ID1 = new Text("Id");
    	ID1.setStyle("-fx-font-weight: bold");

    	ID_TextFlow.getChildren().addAll(ID1);


    	//StrucuralFormula:
    	Text StructuralFormula1 = new Text("Solvent");
    	StructuralFormula1.setStyle("-fx-font-weight: bold");

    	StructuralFormula_TextFlow.getChildren().addAll(StructuralFormula1);


    	//c:
		Text c1 = new Text("c");
		c1.setStyle("-fx-font-weight: bold");
		c1.setStyle("-fx-font-style: italic");
		Text c2 = new Text(" / mol L");
		c2.setStyle("-fx-font-weight: bold");
		Text c3 = new Text("-1");
		c3.setStyle("-fx-font-weight: bold");
		c3.setTranslateY(c1.getFont().getSize() * -0.3);
		c3.setFont(Font.font(c2.getFont().getStyle(),c2.getFont().getSize()*0.75));

		c_TextFlow.getChildren().addAll(c1,c2,c3);


		//density:
		Text density1 = new Text("density");
		density1.setStyle("-fx-font-weight: bold");
		Text density2 = new Text(" / g cm");
		density2.setStyle("-fx-font-weight: bold");
		Text density3 = new Text("-3");
		density3.setStyle("-fx-font-weight: bold");
		density3.setTranslateY(density1.getFont().getSize() * -0.3);
		density3.setFont(Font.font(density2.getFont().getStyle(),density2.getFont().getSize()*0.75));

		density_TextFlow.getChildren().addAll(density1,density2,density3);


		//dynViscosity
		Text dynViscosity1 = new Text("dyn. viscosity");
		dynViscosity1.setStyle("-fx-font-weight: bold");
		Text dynViscosity2 = new Text(" / mPa s");
		dynViscosity2.setStyle("-fx-font-weight: bold");

		dynViscosity_TextFlow.getChildren().addAll(dynViscosity1,dynViscosity2);


		//kinViscosity
		Text kinViscosity1 = new Text("kin. viscosity");
		kinViscosity1.setStyle("-fx-font-weight: bold");
		Text kinViscosity2 = new Text(" / cm");
		kinViscosity2.setStyle("-fx-font-weight: bold");
		Text kinViscosity3 = new Text("2");
		kinViscosity3.setStyle("-fx-font-weight: bold");
		kinViscosity3.setTranslateY(kinViscosity1.getFont().getSize() * -0.3);
		kinViscosity3.setFont(Font.font(kinViscosity2.getFont().getStyle(),kinViscosity2.getFont().getSize()*0.75));
		Text kinViscosity4 = new Text(" s");
		kinViscosity4.setStyle("-fx-font-weight: bold");
		Text kinViscosity5 = new Text("-1");
		kinViscosity5.setStyle("-fx-font-weight: bold");
		kinViscosity5.setTranslateY(kinViscosity1.getFont().getSize() * -0.3);
		kinViscosity5.setFont(Font.font(kinViscosity2.getFont().getStyle(),kinViscosity2.getFont().getSize()*0.75));

		kinViscosity_TextFlow.getChildren().addAll(kinViscosity1,kinViscosity2,kinViscosity3,kinViscosity4,kinViscosity5);


		//editDate
		Text editDate1 = new Text("Date");
		editDate1.setStyle("-fx-font-weight: bold");
		Text editDate2 = new Text("edit");
		editDate2.setStyle("-fx-font-weight: bold");
		editDate2.setTranslateY(editDate1.getFont().getSize() * 0.3);
		editDate2.setFont(Font.font(editDate1.getFont().getStyle(),editDate1.getFont().getSize()*0.75));

		editDate_TextFlow.getChildren().addAll(editDate1,editDate2);

	}

	public void reloadDataSolventInorg() {
		this.tblInorgSolvent = tblInorgSolvent;

		try {
			tblInorgSolvent.refresh();

			Database.createConnection("solventInorganic");

			ResultSet res = Database.selectData("solventInorganic");

		    //extracting data from ResulSet to ArrayList
	    	ArrayList<ObjInorganicSolvent> dataInorganicSolvent = new ArrayList<>();
	    	while (res.next()) {
	    		ObjInorganicSolvent objInorganicSolvent = new ObjInorganicSolvent();

	    			objInorganicSolvent.ID.set(res.getInt("ID"));
	    			objInorganicSolvent.SolventStructuralFormula.set(res.getString("SolventStructuralFormula"));
	    			objInorganicSolvent.c.set(res.getDouble("c"));
	    			objInorganicSolvent.density.set(res.getDouble("density"));
	    			objInorganicSolvent.dynViscosity.set(res.getDouble("dynViscosity"));
	    			objInorganicSolvent.kinViscosity.set(res.getDouble("kinViscosity"));
	    			objInorganicSolvent.RefIDdensity.set(res.getInt("RefIDdensity"));
	    			objInorganicSolvent.RefIDdynViscosity.set(res.getInt("RefIDdynViscosity"));
	    			objInorganicSolvent.RefIDkinViscosity.set(res.getInt("RefIDkinViscosity"));

		    		Timestamp timestamp = res.getTimestamp("editDate");
		    		String timestampString = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(timestamp);
		    		objInorganicSolvent.editDate.set(timestampString);

		    		dataInorganicSolvent.add(objInorganicSolvent);
		    	}

	    	ObservableList<ObjInorganicSolvent> dbData = FXCollections.observableArrayList(dataInorganicSolvent);

	    	ID.setCellValueFactory(new PropertyValueFactory<ObjInorganicSolvent, Integer>("ID"));
	    	StructuralFormula.setCellValueFactory(new PropertyValueFactory<ObjInorganicSolvent, String>("SolventStructuralFormula"));
	    	c.setCellValueFactory(new PropertyValueFactory<ObjInorganicSolvent, Double>("c"));
	    	density.setCellValueFactory(new PropertyValueFactory<ObjInorganicSolvent, Double>("density"));
	    	dynViscosity.setCellValueFactory(new PropertyValueFactory<ObjInorganicSolvent, Double>("dynViscosity"));
	    	kinViscosity.setCellValueFactory(new PropertyValueFactory<ObjInorganicSolvent, Double>("kinViscosity"));
	    	editDate.setCellValueFactory(new PropertyValueFactory<ObjInorganicSolvent, String>("editDate"));
	    	RefIDdensity.setCellValueFactory(new PropertyValueFactory<ObjInorganicSolvent, Integer>("RefIDdensity"));
	    	RefIDdynViscosity.setCellValueFactory(new PropertyValueFactory<ObjInorganicSolvent, Integer>("RefIDdynViscosity"));
	    	RefIDkinViscosity.setCellValueFactory(new PropertyValueFactory<ObjInorganicSolvent, Integer>("RefIDkinViscosity"));

	    	c.setCellFactory(new DecimalColumnFactory<>(new DecimalFormat("0.00")));
	    	density.setCellFactory(new DecimalColumnFactory<>(new DecimalFormat("0.00")));
	    	dynViscosity.setCellFactory(new DecimalColumnFactory<>(new DecimalFormat("0.00")));
	    	kinViscosity.setCellFactory(new DecimalColumnFactory<>(new DecimalFormat("0.00")));

	    	//sorting data, condition: StructuralFormula.
	    	SortedList<ObjInorganicSolvent> dbDataSorted = new SortedList<>(dbData);
	    	// this ensures the dbDataSorted is sorted according to the sort columns in the table:
	    	dbDataSorted.comparatorProperty().bind(tblInorgSolvent.comparatorProperty());
	        //Filling up TableView with data.
	    	tblInorgSolvent.setItems(dbDataSorted);
	    	// programmatically set a sort column:
	    	tblInorgSolvent.getSortOrder().addAll(StructuralFormula);

		} catch (Exception e) {
			e.printStackTrace();
		}


	}

	public ReadOnlyObjectProperty<ObjInorganicSolvent> currentSelectionProperty() {
		return currentSelection.getReadOnlyProperty();
	}

	public ObjInorganicSolvent getCurrentSelection() {
		return currentSelection.get();
	}

}
