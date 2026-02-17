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


public class SolventOrgController implements javafx.fxml.Initializable {

	@FXML private TableView<ObjOrganicSolvent> tblOrgSolvent;

	@FXML private TableColumn<ObjOrganicSolvent, Integer> ID;
	@FXML private TableColumn<ObjOrganicSolvent, String> Name;
	@FXML private TableColumn<ObjOrganicSolvent, String> StructuralFormula;
	@FXML private TableColumn<ObjOrganicSolvent, Double> density;
	@FXML private TableColumn<ObjOrganicSolvent, Double> dynViscosity;
	@FXML private TableColumn<ObjOrganicSolvent, Double> kinViscosity;
	@FXML private TableColumn<ObjOrganicSolvent, Double> ElimitCat;
	@FXML private TableColumn<ObjOrganicSolvent, Double> ElimitAn;
	@FXML private TableColumn<ObjOrganicSolvent, String> editDate;
	@FXML private TableColumn<ObjOrganicSolvent, Integer> RefIDdensity;
	@FXML private TableColumn<ObjOrganicSolvent, Integer> RefIDdynViscosity;
	@FXML private TableColumn<ObjOrganicSolvent, Integer> RefIDkinViscosity;
	@FXML private TableColumn<ObjOrganicSolvent, Integer> RefIDElimitCat;
	@FXML private TableColumn<ObjOrganicSolvent, Integer> RefIDElimitAn;

	@FXML private TextFlow ID_TextFlow;
	@FXML private TextFlow Name_TextFlow;
	@FXML private TextFlow StructuralFormula_TextFlow;
	@FXML private TextFlow density_TextFlow;
	@FXML private TextFlow dynViscosity_TextFlow;
	@FXML private TextFlow kinViscosity_TextFlow;
	@FXML private TextFlow ElimitCat_TextFlow;
	@FXML private TextFlow ElimitAn_TextFlow;
	@FXML private TextFlow editDate_TextFlow;

	private ObservableList<ObjOrganicSolvent> selection;

	private final ReadOnlyObjectWrapper<ObjOrganicSolvent> currentSelection = new ReadOnlyObjectWrapper<>();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		SolventOrgController.this.reloadDataSolventOrg();
		SolventOrgController.this.addColumnNames();
        // set up click on table:

		tblOrgSolvent.setRowFactory( tv -> {
		    TableRow<ObjOrganicSolvent> row = new TableRow<>();
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


    	//Name:
		Text Name1 = new Text("Solvent");
		Name1.setStyle("-fx-font-weight: bold");


		Name_TextFlow.getChildren().addAll(Name1);


    	//StructuralFormula:
    	Text StructuralFormula1 = new Text("Structural formula");
    	StructuralFormula1.setStyle("-fx-font-weight: bold");

    	StructuralFormula_TextFlow.getChildren().addAll(StructuralFormula1);


		//density:
		Text density1 = new Text("density");
		density1.setStyle("-fx-font-weight: bold");
		Text density2 = new Text(" / g cm");
		density2.setStyle("-fx-font-weight: bold");
		Text density3 = new Text("⁻³");
		density3.setStyle("-fx-font-weight: bold");

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
		Text kinViscosity3 = new Text("²");
		kinViscosity3.setStyle("-fx-font-weight: bold");
		Text kinViscosity4 = new Text(" s");
		kinViscosity4.setStyle("-fx-font-weight: bold");
		Text kinViscosity5 = new Text("⁻¹");
		kinViscosity5.setStyle("-fx-font-weight: bold");

		kinViscosity_TextFlow.getChildren().addAll(kinViscosity1,kinViscosity2,kinViscosity3,kinViscosity4,kinViscosity5);



		//ElimitCat
		Text ElimitCat1 = new Text("E");
		ElimitCat1.setStyle("-fx-font-weight: bold");
		ElimitCat1.setStyle("-fx-font-style: italic");
		Text ElimitCat2 = new Text("limit");
		ElimitCat2.setStyle("-fx-font-weight: bold");
		ElimitCat2.setTranslateY(ElimitCat1.getFont().getSize() * 0.3);
		ElimitCat2.setFont(Font.font(ElimitCat1.getFont().getStyle(),ElimitCat1.getFont().getSize()*0.75));
		Text ElimitCat3 = new Text(" (cathodic) / V");
		ElimitCat3.setStyle("-fx-font-weight: bold");

		ElimitCat_TextFlow.getChildren().addAll(ElimitCat1,ElimitCat2,ElimitCat3);



		//ElimitAn
		Text ElimitAn1 = new Text("E");
		ElimitAn1.setStyle("-fx-font-weight: bold");
		ElimitAn1.setStyle("-fx-font-style: italic");
		Text ElimitAn2 = new Text("limit");
		ElimitAn2.setStyle("-fx-font-weight: bold");
		ElimitAn2.setTranslateY(ElimitAn1.getFont().getSize() * 0.3);
		ElimitAn2.setFont(Font.font(ElimitAn1.getFont().getStyle(),ElimitAn1.getFont().getSize()*0.75));
		Text ElimitAn3 = new Text(" (anodic) / V");
		ElimitAn3.setStyle("-fx-font-weight: bold");

		ElimitAn_TextFlow.getChildren().addAll(ElimitAn1,ElimitAn2,ElimitAn3);


		//editDate
		Text editDate1 = new Text("Date");
		editDate1.setStyle("-fx-font-weight: bold");
		Text editDate2 = new Text("edit");
		editDate2.setStyle("-fx-font-weight: bold");
		editDate2.setTranslateY(editDate1.getFont().getSize() * 0.3);
		editDate2.setFont(Font.font(editDate1.getFont().getStyle(),editDate1.getFont().getSize()*0.75));

		editDate_TextFlow.getChildren().addAll(editDate1,editDate2);

	}


	public void reloadDataSolventOrg() {
		this.tblOrgSolvent = tblOrgSolvent;

		try {
			tblOrgSolvent.refresh();
			
			Database.createConnection("solventOrganic");

			ResultSet res = Database.selectData("solventOrganic");

		    //extracting data from ResulSet to ArrayList
	    	ArrayList<ObjOrganicSolvent> dataOrganicSolvent = new ArrayList<>();
	    	while (res.next()) {
	    		ObjOrganicSolvent objOrganicSolvent = new ObjOrganicSolvent();

	    		objOrganicSolvent.ID.set(res.getInt("ID"));
	    		objOrganicSolvent.SolventName.set(res.getString("SolventName"));
	    		objOrganicSolvent.SolventStructuralFormula.set(res.getString("SolventStructuralFormula"));
	    		objOrganicSolvent.density.set(res.getDouble("density"));
	    		objOrganicSolvent.dynViscosity.set(res.getDouble("dynViscosity"));
	    		objOrganicSolvent.kinViscosity.set(res.getDouble("kinViscosity"));
	    		objOrganicSolvent.ElimitCat.set(res.getDouble("ElimitCat"));
	    		objOrganicSolvent.ElimitAn.set(res.getDouble("ElimitAn"));
	    		objOrganicSolvent.RefIDdensity.set(res.getInt("RefIDdensity"));
	    		objOrganicSolvent.RefIDdynViscosity.set(res.getInt("RefIDdynViscosity"));
	    		objOrganicSolvent.RefIDkinViscosity.set(res.getInt("RefIDkinViscosity"));
	    		objOrganicSolvent.RefIDElimitCat.set(res.getInt("RefIDElimitCat"));
	    		objOrganicSolvent.RefIDElimitAn.set(res.getInt("RefIDElimitAn"));

		    		Timestamp timestamp = res.getTimestamp("editDate");
		    		String timestampString = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(timestamp);
		    		objOrganicSolvent.editDate.set(timestampString);

		    		dataOrganicSolvent.add(objOrganicSolvent);
		    	}

	    	ObservableList<ObjOrganicSolvent> dbData = FXCollections.observableArrayList(dataOrganicSolvent);

	    	ID.setCellValueFactory(new PropertyValueFactory<ObjOrganicSolvent, Integer>("ID"));
	    	Name.setCellValueFactory(new PropertyValueFactory<ObjOrganicSolvent, String>("SolventName"));
	    	StructuralFormula.setCellValueFactory(new PropertyValueFactory<ObjOrganicSolvent, String>("SolventStructuralFormula"));
	    	density.setCellValueFactory(new PropertyValueFactory<ObjOrganicSolvent, Double>("density"));
	    	dynViscosity.setCellValueFactory(new PropertyValueFactory<ObjOrganicSolvent, Double>("dynViscosity"));
	    	kinViscosity.setCellValueFactory(new PropertyValueFactory<ObjOrganicSolvent, Double>("kinViscosity"));
	    	ElimitCat.setCellValueFactory(new PropertyValueFactory<ObjOrganicSolvent, Double>("ElimitCat"));
	    	ElimitAn.setCellValueFactory(new PropertyValueFactory<ObjOrganicSolvent, Double>("ElimitAn"));
	    	editDate.setCellValueFactory(new PropertyValueFactory<ObjOrganicSolvent, String>("editDate"));
	    	RefIDdensity.setCellValueFactory(new PropertyValueFactory<ObjOrganicSolvent, Integer>("RefIDdensity"));
	    	RefIDdynViscosity.setCellValueFactory(new PropertyValueFactory<ObjOrganicSolvent, Integer>("RefIDdynViscosity"));
	    	RefIDkinViscosity.setCellValueFactory(new PropertyValueFactory<ObjOrganicSolvent, Integer>("RefIDkinViscosity"));
	    	RefIDElimitCat.setCellValueFactory(new PropertyValueFactory<ObjOrganicSolvent, Integer>("RefIDElimitCat"));
	    	RefIDElimitAn.setCellValueFactory(new PropertyValueFactory<ObjOrganicSolvent, Integer>("RefIDElimitAn"));

	    	density.setCellFactory(new DecimalColumnFactory<>(new DecimalFormat("0.00")));
	    	dynViscosity.setCellFactory(new DecimalColumnFactory<>(new DecimalFormat("0.00")));
	    	kinViscosity.setCellFactory(new DecimalColumnFactory<>(new DecimalFormat("0.00")));
	    	ElimitCat.setCellFactory(new DecimalColumnFactory<>(new DecimalFormat("0.00")));
	    	ElimitAn.setCellFactory(new DecimalColumnFactory<>(new DecimalFormat("0.00")));

	    	//sorting data, condition: Name.
	    	SortedList<ObjOrganicSolvent> dbDataSorted = new SortedList<>(dbData);
	    	// this ensures the dbDataSorted is sorted according to the sort columns in the table:
	    	dbDataSorted.comparatorProperty().bind(tblOrgSolvent.comparatorProperty());
	        //Filling up TableView with data.
	    	tblOrgSolvent.setItems(dbDataSorted);
	    	// programmatically set a sort column:
	    	tblOrgSolvent.getSortOrder().addAll(Name);

		} catch (Exception e) {
			e.printStackTrace();
		}


	}

	public ReadOnlyObjectProperty<ObjOrganicSolvent> currentSelectionProperty() {
		return currentSelection.getReadOnlyProperty();
	}

	public ObjOrganicSolvent getCurrentSelection() {
		return currentSelection.get();
	}




}
