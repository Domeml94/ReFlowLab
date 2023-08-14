package de.dominikemmel.reflowlab.controller.costs;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import de.dominikemmel.reflowlab.Database;
import de.dominikemmel.reflowlab.DecimalColumnFactory;

public class CostsController implements javafx.fxml.Initializable {
	@FXML
	private TableView<ObjCostsStack> tblStackCosts;

	@FXML
	private TableColumn<ObjCostsStack, Integer> ID1;
	@FXML
	private TextFlow ID1_TextFlow;
	@FXML
	private TableColumn<ObjCostsStack, String> ComponentName;
	@FXML
	private TextFlow ComponentName_TextFlow;
	@FXML
	private TableColumn<ObjCostsStack, String> ComponentType;
	@FXML
	private TextFlow ComponentType_TextFlow;
	@FXML
	private TableColumn<ObjCostsStack, Double> ComponentCost;
	@FXML
	private TextFlow ComponentCost_TextFlow;
	@FXML
	private TableColumn<ObjCostsStack, String> CostUnit;
	@FXML
	private TextFlow CostUnit_TextFlow;
	@FXML
	private TableColumn<ObjCostsStack, Integer> RefIDComponentCost;
	@FXML
	private TableColumn<ObjCostsStack, String> editDate1;
	@FXML
	private TextFlow editDate1_TextFlow;

	@FXML
	private TableView<ObjCostsSalt> tblCostsSalt;

	@FXML
	private TableColumn<ObjCostsSalt, Integer> ID2;
	@FXML
	private TextFlow ID2_TextFlow;
	@FXML
	private TableColumn<ObjCostsSalt, String> Salt;
	@FXML
	private TextFlow Salt_TextFlow;
	@FXML
	private TableColumn<ObjCostsSalt, Double> MSalt;
	@FXML
	private TextFlow MSalt_TextFlow;
	@FXML
	private TableColumn<ObjCostsSalt, Double> CSalt;
	@FXML
	private TextFlow CSalt_TextFlow;
	@FXML
	private TableColumn<ObjCostsSalt, Integer> RefIDMSalt;
	@FXML
	private TableColumn<ObjCostsSalt, Integer> RefIDCSalt;
	@FXML
	private TableColumn<ObjCostsSalt, String> editDate2;
	@FXML
	private TextFlow editDate2_TextFlow;

	@FXML
	private TableView<ObjCostsSolvent> tblCostsSolvent;

	@FXML
	private TableColumn<ObjCostsSolvent, Integer> ID3;
	@FXML
	private TextFlow ID3_TextFlow;
	@FXML
	private TableColumn<ObjCostsSolvent, String> Solvent;
	@FXML
	private TextFlow Solvent_TextFlow;
	@FXML
	private TableColumn<ObjCostsSolvent, Double> MSolvent;
	@FXML
	private TextFlow MSolvent_TextFlow;
	@FXML
	private TableColumn<ObjCostsSolvent, Double> CSolvent;
	@FXML
	private TextFlow CSolvent_TextFlow;
	@FXML
	private TableColumn<ObjCostsSolvent, Integer> RefIDMSolvent;
	@FXML
	private TableColumn<ObjCostsSolvent, Integer> RefIDCSolvent;
	@FXML
	private TableColumn<ObjCostsSolvent, String> editDate3;
	@FXML
	private TextFlow editDate3_TextFlow;
	
	@FXML
	private TableView<ObjSeparatorParameter> tblSeparatorParameter;

	@FXML
	private TableColumn<ObjSeparatorParameter, Integer> ID4;
	@FXML
	private TextFlow ID4_TextFlow;
	@FXML
	private TableColumn<ObjSeparatorParameter, String> SeparatorName;
	@FXML
	private TextFlow SeparatorName_TextFlow;
	@FXML
	private TableColumn<ObjSeparatorParameter, Double> Rasr;
	@FXML
	private TextFlow Rasr_TextFlow;
	@FXML
	private TableColumn<ObjSeparatorParameter, Integer> RefIDRasr;
	@FXML
	private TableColumn<ObjSeparatorParameter, String> editDate4;
	@FXML
	private TextFlow editDate4_TextFlow;
	
	
	@FXML
	private TableView<ObjCostsPeripherals> tblPeripheralCosts;

	@FXML
	private TableColumn<ObjCostsPeripherals, Integer> ID5;
	@FXML
	private TextFlow ID5_TextFlow;
	@FXML
	private TableColumn<ObjCostsPeripherals, String> ComponentNamePeripherals;
	@FXML
	private TextFlow ComponentNamePeripherals_TextFlow;
	@FXML
	private TableColumn<ObjCostsPeripherals, String> ComponentTypePeripherals;
	@FXML
	private TextFlow ComponentTypePeripherals_TextFlow;
	@FXML
	private TableColumn<ObjCostsPeripherals, Double> ComponentCostPeripherals;
	@FXML
	private TextFlow ComponentCostPeripherals_TextFlow;
	@FXML
	private TableColumn<ObjCostsPeripherals, String> CostUnitPeripherals;
	@FXML
	private TextFlow CostUnitPeripherals_TextFlow;
	@FXML
	private TableColumn<ObjCostsPeripherals, Integer> RefIDComponentCostPeripherals;
	@FXML
	private TableColumn<ObjCostsPeripherals, String> editDatePeripherals;
	@FXML
	private TextFlow editDatePeripherals_TextFlow;

	ResultSet res1 = null;
	ResultSet res2 = null;
	ResultSet res3 = null;
	ResultSet res4 = null;
	ResultSet res5 = null;

	private Integer selectionTest = 1;
	private Integer selectionTestTable = null;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		CostsController.this.addColumnNames();

		CostsController.this.getConnectionDatabase();

		CostsController.this.reloadDataCostsStack();
		CostsController.this.reloadDataCostsSalt();
		CostsController.this.reloadDataCostsSolvent();
		CostsController.this.reloadDataSeparatorParameter();
		CostsController.this.reloadDataCostsPeripherals();

        // set up click on table:
		tblStackCosts.setRowFactory( tv -> {
		    TableRow<ObjCostsStack> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (event.getClickCount() == 1 && (! row.isEmpty()) ) {
		        	selectionTest = 1;
		        }
		    });
		    return row ;
		});
		tblCostsSalt.setRowFactory( tv -> {
			TableRow<ObjCostsSalt> row2 = new TableRow<>();
			row2.setOnMouseClicked(event -> {
				if (event.getClickCount() == 1 && (! row2.isEmpty()) ) {
					selectionTest = 2;
				}
			});
			return row2 ;
		});
		tblCostsSolvent.setRowFactory( tv -> {
			TableRow<ObjCostsSolvent> row3 = new TableRow<>();
			row3.setOnMouseClicked(event -> {
				if (event.getClickCount() == 1 && (! row3.isEmpty()) ) {
					selectionTest = 3;
				}
			});
			return row3 ;
		});
		tblSeparatorParameter.setRowFactory( tv -> {
			TableRow<ObjSeparatorParameter> row4 = new TableRow<>();
			row4.setOnMouseClicked(event -> {
				if (event.getClickCount() == 1 && (! row4.isEmpty()) ) {
					selectionTest = 4;
				}
			});
			return row4 ;
		});
		tblPeripheralCosts.setRowFactory( tv -> {
			TableRow<ObjCostsPeripherals> row5 = new TableRow<>();
			row5.setOnMouseClicked(event -> {
				if (event.getClickCount() == 1 && (! row5.isEmpty()) ) {
					selectionTest = 5;
				}
			});
			return row5 ;
		});
	}

	public void getConnectionDatabase() {
		try {
			Database.createConnection("costStack");
			Database.createConnection("costSalt");
			Database.createConnection("costSolvent");
			Database.createConnection("separatorParameter");
			Database.createConnection("costPeripherals");

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	public void reloadDataCostsStack() {

		try {
			tblStackCosts.refresh();

			ResultSet res1 = Database.selectData("costStack");

		    //extracting data from ResulSet to ArrayList
	    	ArrayList<ObjCostsStack> dataCostsStack = new ArrayList<>();
	    	while (res1.next()) {
	    		ObjCostsStack objCostsStack = new ObjCostsStack();

	    		objCostsStack.ID.set(res1.getInt("ID"));
	    		objCostsStack.ComponentName.set(res1.getString("ComponentName"));
	    		objCostsStack.ComponentType.set(res1.getString("ComponentType"));
	    		objCostsStack.ComponentCost.set(res1.getDouble("ComponentCost"));
	    		objCostsStack.CostUnit.set(res1.getString("CostUnit"));
	    		objCostsStack.RefIDComponentCost.set(res1.getInt("RefIDComponentCost"));

		    		Timestamp timestamp = res1.getTimestamp("editDate");
		    		String timestampString = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(timestamp);
		    		objCostsStack.editDate.set(timestampString);

		    		dataCostsStack.add(objCostsStack);
		    	}

	    	ObservableList<ObjCostsStack> dbDataStackCost = FXCollections.observableArrayList(dataCostsStack);

	    	ID1.setCellValueFactory(new PropertyValueFactory<ObjCostsStack, Integer>("ID"));
	    	ComponentName.setCellValueFactory(new PropertyValueFactory<ObjCostsStack, String>("ComponentName"));
	    	ComponentType.setCellValueFactory(new PropertyValueFactory<ObjCostsStack, String>("ComponentType"));
	    	ComponentCost.setCellValueFactory(new PropertyValueFactory<ObjCostsStack, Double>("ComponentCost"));
	    	CostUnit.setCellValueFactory(new PropertyValueFactory<ObjCostsStack, String>("CostUnit"));
	    	RefIDComponentCost.setCellValueFactory(new PropertyValueFactory<ObjCostsStack, Integer>("RefIDComponentCost"));
	    	editDate1.setCellValueFactory(new PropertyValueFactory<ObjCostsStack, String>("editDate"));

	    	ComponentCost.setCellFactory(new DecimalColumnFactory<>(new DecimalFormat("0.00")));

	    	//sorting data, condition: System.
	    	SortedList<ObjCostsStack> dbDataSortedStackCost = new SortedList<>(dbDataStackCost);
	    	// this ensures the dbDataSorted is sorted according to the sort columns in the table:
	    	dbDataSortedStackCost.comparatorProperty().bind(tblStackCosts.comparatorProperty());
	        //Filling up TableView with data.
	    	tblStackCosts.setItems(dbDataSortedStackCost);
	    	// programmatically set a sort column:
	    	tblStackCosts.getSortOrder().addAll(ComponentName);


		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	public void reloadDataCostsSalt() {

		try {
			tblCostsSalt.refresh();

			ResultSet res2 = Database.selectData("costSalt");

		    //extracting data from ResulSet to ArrayList
	    	ArrayList<ObjCostsSalt> dataCostsSalt = new ArrayList<>();
	    	while (res2.next()) {
	    		ObjCostsSalt objCostsSalt = new ObjCostsSalt();

	    		objCostsSalt.ID.set(res2.getInt("ID"));
	    		objCostsSalt.Salt.set(res2.getString("Salt"));
	    		objCostsSalt.MSalt.set(res2.getDouble("MSalt"));
	    		objCostsSalt.CSalt.set(res2.getDouble("CSalt"));
	    		objCostsSalt.RefIDMSalt.set(res2.getInt("RefIDMSalt"));
	    		objCostsSalt.RefIDCSalt.set(res2.getInt("RefIDCSalt"));

		    		Timestamp timestamp = res2.getTimestamp("editDate");
		    		String timestampString = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(timestamp);
		    		objCostsSalt.editDate.set(timestampString);

		    		dataCostsSalt.add(objCostsSalt);
		    	}

	    	ObservableList<ObjCostsSalt> dbDataSalt = FXCollections.observableArrayList(dataCostsSalt);

	    	ID2.setCellValueFactory(new PropertyValueFactory<ObjCostsSalt, Integer>("ID"));
	    	Salt.setCellValueFactory(new PropertyValueFactory<ObjCostsSalt, String>("Salt"));
	    	MSalt.setCellValueFactory(new PropertyValueFactory<ObjCostsSalt, Double>("MSalt"));
	    	CSalt.setCellValueFactory(new PropertyValueFactory<ObjCostsSalt, Double>("CSalt"));
	    	RefIDMSalt.setCellValueFactory(new PropertyValueFactory<ObjCostsSalt, Integer>("RefIDMSalt"));
	    	RefIDCSalt.setCellValueFactory(new PropertyValueFactory<ObjCostsSalt, Integer>("RefIDCSalt"));
	    	editDate2.setCellValueFactory(new PropertyValueFactory<ObjCostsSalt, String>("editDate"));

	    	MSalt.setCellFactory(new DecimalColumnFactory<>(new DecimalFormat("0.00")));
	    	CSalt.setCellFactory(new DecimalColumnFactory<>(new DecimalFormat("0.00")));

	    	//sorting data, condition: Salt.
	    	SortedList<ObjCostsSalt> dbDataSortedSalt = new SortedList<>(dbDataSalt);
	    	// this ensures the dbDataSorted is sorted according to the sort columns in the table:
	    	dbDataSortedSalt.comparatorProperty().bind(tblCostsSalt.comparatorProperty());
	        //Filling up TableView with data.
	    	tblCostsSalt.setItems(dbDataSortedSalt);
	    	// programmatically set a sort column:
	    	tblCostsSalt.getSortOrder().addAll(Salt);


		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}




	public void reloadDataCostsSolvent() {

		try {
			tblCostsSolvent.refresh();

			ResultSet res3 = Database.selectData("costSolvent");

		    //extracting data from ResulSet to ArrayList
	    	ArrayList<ObjCostsSolvent> dataCostsSolvent = new ArrayList<>();
	    	while (res3.next()) {
	    		ObjCostsSolvent objCostsSolvent = new ObjCostsSolvent();

	    		objCostsSolvent.ID.set(res3.getInt("ID"));
	    		objCostsSolvent.Solvent.set(res3.getString("Solvent"));
	    		objCostsSolvent.MSolvent.set(res3.getDouble("MSolvent"));
	    		objCostsSolvent.CSolvent.set(res3.getDouble("CSolvent"));
	    		objCostsSolvent.RefIDMSolvent.set(res3.getInt("RefIDMSolvent"));
	    		objCostsSolvent.RefIDCSolvent.set(res3.getInt("RefIDCSolvent"));

		    		Timestamp timestamp = res3.getTimestamp("editDate");
		    		String timestampString = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(timestamp);
		    		objCostsSolvent.editDate.set(timestampString);

		    		dataCostsSolvent.add(objCostsSolvent);
		    	}

	    	ObservableList<ObjCostsSolvent> dbDataSolvent = FXCollections.observableArrayList(dataCostsSolvent);

	    	ID3.setCellValueFactory(new PropertyValueFactory<ObjCostsSolvent, Integer>("ID"));
	    	Solvent.setCellValueFactory(new PropertyValueFactory<ObjCostsSolvent, String>("Solvent"));
	    	MSolvent.setCellValueFactory(new PropertyValueFactory<ObjCostsSolvent, Double>("MSolvent"));
	    	CSolvent.setCellValueFactory(new PropertyValueFactory<ObjCostsSolvent, Double>("CSolvent"));
	    	RefIDMSolvent.setCellValueFactory(new PropertyValueFactory<ObjCostsSolvent, Integer>("RefIDMSolvent"));
	    	RefIDCSolvent.setCellValueFactory(new PropertyValueFactory<ObjCostsSolvent, Integer>("RefIDCSolvent"));
	    	editDate3.setCellValueFactory(new PropertyValueFactory<ObjCostsSolvent, String>("editDate"));

	    	MSolvent.setCellFactory(new DecimalColumnFactory<>(new DecimalFormat("0.00")));
	    	CSolvent.setCellFactory(new DecimalColumnFactory<>(new DecimalFormat("0.00")));

	    	//sorting data, condition: Solvent.
	    	SortedList<ObjCostsSolvent> dbDataSortedSolvent = new SortedList<>(dbDataSolvent);
	    	// this ensures the dbDataSorted is sorted according to the sort columns in the table:
	    	dbDataSortedSolvent.comparatorProperty().bind(tblCostsSolvent.comparatorProperty());
	        //Filling up TableView with data.
	    	tblCostsSolvent.setItems(dbDataSortedSolvent);
	    	// programmatically set a sort column:
	    	tblCostsSolvent.getSortOrder().addAll(Solvent);


		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	public void reloadDataSeparatorParameter() {

		try {
			tblSeparatorParameter.refresh();

			ResultSet res4 = Database.selectData("separatorParameter");

		    //extracting data from ResulSet to ArrayList
	    	ArrayList<ObjSeparatorParameter> dataSeparatorParameter = new ArrayList<>();
	    	while (res4.next()) {
	    		ObjSeparatorParameter objSeparatorParameter = new ObjSeparatorParameter();

	    		objSeparatorParameter.ID.set(res4.getInt("ID"));
	    		objSeparatorParameter.SeparatorName.set(res4.getString("SeparatorName"));
	    		objSeparatorParameter.Rasr.set(res4.getDouble("Rasr"));
	    		objSeparatorParameter.RefIDRasr.set(res4.getInt("RefIDRasr"));

		    		Timestamp timestamp = res4.getTimestamp("editDate");
		    		String timestampString = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(timestamp);
		    		objSeparatorParameter.editDate.set(timestampString);

		    		dataSeparatorParameter.add(objSeparatorParameter);
		    	}

	    	ObservableList<ObjSeparatorParameter> dbDataSeparatorParameter = FXCollections.observableArrayList(dataSeparatorParameter);

	    	ID4.setCellValueFactory(new PropertyValueFactory<ObjSeparatorParameter, Integer>("ID"));
	    	SeparatorName.setCellValueFactory(new PropertyValueFactory<ObjSeparatorParameter, String>("SeparatorName"));
	    	Rasr.setCellValueFactory(new PropertyValueFactory<ObjSeparatorParameter, Double>("Rasr"));
	    	RefIDRasr.setCellValueFactory(new PropertyValueFactory<ObjSeparatorParameter, Integer>("RefIDRasr"));
	    	editDate4.setCellValueFactory(new PropertyValueFactory<ObjSeparatorParameter, String>("editDate"));

	    	Rasr.setCellFactory(new DecimalColumnFactory<>(new DecimalFormat("0.00")));

	    	//sorting data, condition: Seperator.
	    	SortedList<ObjSeparatorParameter> dbDataSortedSeparatorParameter = new SortedList<>(dbDataSeparatorParameter);
	    	// this ensures the dbDataSorted is sorted according to the sort columns in the table:
	    	dbDataSortedSeparatorParameter.comparatorProperty().bind(tblSeparatorParameter.comparatorProperty());
	        //Filling up TableView with data.
	    	tblSeparatorParameter.setItems(dbDataSortedSeparatorParameter);
	    	// programmatically set a sort column:
	    	tblSeparatorParameter.getSortOrder().addAll(SeparatorName);


		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public void reloadDataCostsPeripherals() {

		try {
			tblPeripheralCosts.refresh();

			ResultSet res5 = Database.selectData("costPeripherals");

		    //extracting data from ResulSet to ArrayList
	    	ArrayList<ObjCostsPeripherals> dataCostsPeripherals = new ArrayList<>();
	    	while (res5.next()) {
	    		ObjCostsPeripherals objCostsPeripherals = new ObjCostsPeripherals();

	    		objCostsPeripherals.ID.set(res5.getInt("ID"));
	    		objCostsPeripherals.ComponentName.set(res5.getString("ComponentName"));
	    		objCostsPeripherals.ComponentType.set(res5.getString("ComponentType"));
	    		objCostsPeripherals.ComponentCost.set(res5.getDouble("ComponentCost"));
	    		objCostsPeripherals.CostUnit.set(res5.getString("CostUnit"));
	    		objCostsPeripherals.RefIDComponentCost.set(res5.getInt("RefIDComponentCost"));

		    		Timestamp timestamp = res5.getTimestamp("editDate");
		    		String timestampString = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(timestamp);
		    		objCostsPeripherals.editDate.set(timestampString);

		    		dataCostsPeripherals.add(objCostsPeripherals);
		    	}

	    	ObservableList<ObjCostsPeripherals> dbDataPeripheralsCost = FXCollections.observableArrayList(dataCostsPeripherals);

	    	ID5.setCellValueFactory(new PropertyValueFactory<ObjCostsPeripherals, Integer>("ID"));
	    	ComponentNamePeripherals.setCellValueFactory(new PropertyValueFactory<ObjCostsPeripherals, String>("ComponentName"));
	    	ComponentTypePeripherals.setCellValueFactory(new PropertyValueFactory<ObjCostsPeripherals, String>("ComponentType"));
	    	ComponentCostPeripherals.setCellValueFactory(new PropertyValueFactory<ObjCostsPeripherals, Double>("ComponentCost"));
	    	CostUnitPeripherals.setCellValueFactory(new PropertyValueFactory<ObjCostsPeripherals, String>("CostUnit"));
	    	RefIDComponentCostPeripherals.setCellValueFactory(new PropertyValueFactory<ObjCostsPeripherals, Integer>("RefIDComponentCost"));
	    	editDatePeripherals.setCellValueFactory(new PropertyValueFactory<ObjCostsPeripherals, String>("editDate"));

	    	ComponentCostPeripherals.setCellFactory(new DecimalColumnFactory<>(new DecimalFormat("0.00")));

	    	//sorting data, condition: System.
	    	SortedList<ObjCostsPeripherals> dbDataSortedPeripheralsCost = new SortedList<>(dbDataPeripheralsCost);
	    	// this ensures the dbDataSorted is sorted according to the sort columns in the table:
	    	dbDataSortedPeripheralsCost.comparatorProperty().bind(tblPeripheralCosts.comparatorProperty());
	        //Filling up TableView with data.
	    	tblPeripheralCosts.setItems(dbDataSortedPeripheralsCost);
	    	// programmatically set a sort column:
	    	tblPeripheralCosts.getSortOrder().addAll(ComponentNamePeripherals);


		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}





	@FXML
	private void addColumnNames() {
    	//column names:

    	//ID1:
    	Text ID1 = new Text("Id");
    	ID1.setStyle("-fx-font-weight: bold");

    	ID1_TextFlow.getChildren().addAll(ID1);


    	//ComponentName:
    	Text ComponentName1 = new Text("Comp. Name");
    	ComponentName1.setStyle("-fx-font-weight: bold");

    	ComponentName_TextFlow.getChildren().addAll(ComponentName1);
    	

    	//ComponentType:
    	Text ComponentType1 = new Text("Comp. Type");
    	ComponentType1.setStyle("-fx-font-weight: bold");

    	ComponentType_TextFlow.getChildren().addAll(ComponentType1);
    	
    	
    	//ComponentCost:
    	Text ComponentCost1 = new Text("Comp. Cost");
    	ComponentCost1.setStyle("-fx-font-weight: bold");
		Text ComponentCost2 = new Text("");
		ComponentCost2.setStyle("-fx-font-weight: bold");

		ComponentCost_TextFlow.getChildren().addAll(ComponentCost1,ComponentCost2);
		

    	//CostUnit:
    	Text CostUnit1 = new Text("Cost Unit");
    	CostUnit1.setStyle("-fx-font-weight: bold");

    	CostUnit_TextFlow.getChildren().addAll(CostUnit1);


    	//editDate1:
		Text editDate11 = new Text("Date");
		editDate11.setStyle("-fx-font-weight: bold");
		Text editDate12 = new Text("edit");
		editDate12.setStyle("-fx-font-weight: bold");
		editDate12.setTranslateY(editDate11.getFont().getSize() * 0.3);
		editDate12.setFont(Font.font(editDate11.getFont().getStyle(),editDate11.getFont().getSize()*0.75));

		editDate1_TextFlow.getChildren().addAll(editDate11,editDate12);



    	//ID2:
    	Text ID2 = new Text("Id");
    	ID2.setStyle("-fx-font-weight: bold");

    	ID2_TextFlow.getChildren().addAll(ID2);

    	//Salt:
    	Text Salt1 = new Text("Salt");
    	Salt1.setStyle("-fx-font-weight: bold");

    	Salt_TextFlow.getChildren().addAll(Salt1);

    	//MSalt:
    	Text MSalt1 = new Text("M");
    	MSalt1.setStyle("-fx-font-weight: bold");
    	MSalt1.setStyle("-fx-font-style: italic");
    	Text MSalt2 = new Text(" / g mol");
    	MSalt2.setStyle("-fx-font-weight: bold");
    	Text MSalt3 = new Text("⁻¹");
    	MSalt3.setStyle("-fx-font-weight: bold");

    	MSalt_TextFlow.getChildren().addAll(MSalt1,MSalt2,MSalt3);

    	//CSalt:
    	Text CSalt1 = new Text("C");
    	CSalt1.setStyle("-fx-font-weight: bold");
    	CSalt1.setStyle("-fx-font-style: italic");
		Text CSalt2 = new Text(" / $ kg");
		CSalt2.setStyle("-fx-font-weight: bold");
		Text CSalt3 = new Text("⁻¹");
		CSalt3.setStyle("-fx-font-weight: bold");

		CSalt_TextFlow.getChildren().addAll(CSalt1,CSalt2,CSalt3);

    	//editDate2:
		Text editDate21 = new Text("Date");
		editDate21.setStyle("-fx-font-weight: bold");
		Text editDate22 = new Text("edit");
		editDate22.setStyle("-fx-font-weight: bold");
		editDate22.setTranslateY(editDate21.getFont().getSize() * 0.3);
		editDate22.setFont(Font.font(editDate21.getFont().getStyle(),editDate21.getFont().getSize()*0.75));

		editDate2_TextFlow.getChildren().addAll(editDate21,editDate22);



    	//ID3:
    	Text ID3 = new Text("Id");
    	ID3.setStyle("-fx-font-weight: bold");

    	ID3_TextFlow.getChildren().addAll(ID3);

    	//Solvent:
    	Text Solvent1 = new Text("Solvent");
    	Solvent1.setStyle("-fx-font-weight: bold");

    	Solvent_TextFlow.getChildren().addAll(Solvent1);

    	//CSolvent:
    	Text CSolvent1 = new Text("C");
    	CSolvent1.setStyle("-fx-font-weight: bold");
    	CSolvent1.setStyle("-fx-font-style: italic");
    	Text CSolvent2 = new Text(" / $ kg");
    	CSolvent2.setStyle("-fx-font-weight: bold");
    	Text CSolvent3 = new Text("⁻¹");
    	CSolvent3.setStyle("-fx-font-weight: bold");

    	CSolvent_TextFlow.getChildren().addAll(CSolvent1,CSolvent2,CSolvent3);

    	//MSolvent:
    	Text MSolvent1 = new Text("M");
    	MSolvent1.setStyle("-fx-font-weight: bold");
    	MSolvent1.setStyle("-fx-font-style: italic");
		Text MSolvent2 = new Text(" / g mol");
		MSolvent2.setStyle("-fx-font-weight: bold");
		Text MSolvent3 = new Text("⁻¹");
		MSolvent3.setStyle("-fx-font-weight: bold");

		MSolvent_TextFlow.getChildren().addAll(MSolvent1,MSolvent2,MSolvent3);

    	//editDate3:
		Text editDate31 = new Text("Date");
		editDate31.setStyle("-fx-font-weight: bold");
		Text editDate32 = new Text("edit");
		editDate32.setStyle("-fx-font-weight: bold");
		editDate32.setTranslateY(editDate31.getFont().getSize() * 0.3);
		editDate32.setFont(Font.font(editDate31.getFont().getStyle(),editDate31.getFont().getSize()*0.75));

		editDate3_TextFlow.getChildren().addAll(editDate31,editDate32);
		
		
		
		
    	//ID4:
    	Text ID4 = new Text("Id");
    	ID4.setStyle("-fx-font-weight: bold");

    	ID4_TextFlow.getChildren().addAll(ID4);

    	//SeparatorName:
    	Text SeparatorName1 = new Text("Separator Name");
    	SeparatorName1.setStyle("-fx-font-weight: bold");

    	SeparatorName_TextFlow.getChildren().addAll(SeparatorName1);

    	//Rasr:
    	Text Rasr1 = new Text("R");
    	Rasr1.setStyle("-fx-font-weight: bold");
    	Rasr1.setStyle("-fx-font-style: italic");
    	Text Rasr2 = new Text("asr");
    	Rasr2.setStyle("-fx-font-weight: bold");
    	Rasr2.setTranslateY(Rasr1.getFont().getSize() * 0.3);
    	Rasr2.setFont(Font.font(Rasr1.getFont().getStyle(),Rasr1.getFont().getSize()*0.75));
    	Text Rasr3 = new Text(" / Ohm cm");
    	Rasr3.setStyle("-fx-font-weight: bold");
    	Text Rasr4 = new Text("⁻²");
    	Rasr4.setStyle("-fx-font-weight: bold");

    	Rasr_TextFlow.getChildren().addAll(Rasr1, Rasr2, Rasr3, Rasr4);

    	//editDate4:
		Text editDate41 = new Text("Date");
		editDate41.setStyle("-fx-font-weight: bold");
		Text editDate42 = new Text("edit");
		editDate42.setStyle("-fx-font-weight: bold");
		editDate42.setTranslateY(editDate41.getFont().getSize() * 0.3);
		editDate42.setFont(Font.font(editDate41.getFont().getStyle(),editDate41.getFont().getSize()*0.75));

		editDate4_TextFlow.getChildren().addAll(editDate41,editDate42);
		
		
		
		//ID5:
    	Text ID5 = new Text("Id");
    	ID5.setStyle("-fx-font-weight: bold");

    	ID5_TextFlow.getChildren().addAll(ID5);


    	//ComponentName:
    	Text ComponentNamePeripherals1 = new Text("Comp. Name");
    	ComponentNamePeripherals1.setStyle("-fx-font-weight: bold");

    	ComponentNamePeripherals_TextFlow.getChildren().addAll(ComponentNamePeripherals1);
    	

    	//ComponentType:
    	Text ComponentTypePeripherals1 = new Text("Comp. Type");
    	ComponentTypePeripherals1.setStyle("-fx-font-weight: bold");

    	ComponentTypePeripherals_TextFlow.getChildren().addAll(ComponentTypePeripherals1);
    	
    	
    	//ComponentCost:
    	Text ComponentCostPeripherals1 = new Text("Comp. Cost");
    	ComponentCostPeripherals1.setStyle("-fx-font-weight: bold");
		Text ComponentCostPeripherals2 = new Text("");
		ComponentCostPeripherals2.setStyle("-fx-font-weight: bold");

		ComponentCostPeripherals_TextFlow.getChildren().addAll(ComponentCostPeripherals1,ComponentCostPeripherals2);
		

    	//CostUnit:
    	Text CostUnitPeripherals1 = new Text("Cost Unit");
    	CostUnitPeripherals1.setStyle("-fx-font-weight: bold");

    	CostUnitPeripherals_TextFlow.getChildren().addAll(CostUnitPeripherals1);


    	//editDate1:
		Text editDatePeripherals1 = new Text("Date");
		editDatePeripherals1.setStyle("-fx-font-weight: bold");
		Text editDatePeripherals2 = new Text("edit");
		editDatePeripherals2.setStyle("-fx-font-weight: bold");
		editDatePeripherals2.setTranslateY(editDatePeripherals1.getFont().getSize() * 0.3);
		editDatePeripherals2.setFont(Font.font(editDatePeripherals1.getFont().getStyle(),editDatePeripherals1.getFont().getSize()*0.75));

		editDatePeripherals_TextFlow.getChildren().addAll(editDatePeripherals1,editDatePeripherals2);
	}

	@FXML
	private void tblStackCostsSelected(MouseEvent event) {
		selectionTestTable = 1;
	}

	@FXML
	private void tblCostsSaltSelected(MouseEvent event) {
		selectionTestTable = 2;
	}

	@FXML
	private void tblCostsSolventSelected(MouseEvent event) {
		selectionTestTable = 3;
	}
	
	@FXML
	private void tblSeparatorParameterSelected(MouseEvent event) {
		selectionTestTable = 4;
	}
	
	@FXML
	private void tblPeripheralCostsSelected(MouseEvent event) {
		selectionTestTable = 5;
	}

	@FXML
	private void addCosts (ActionEvent event) {

		switch(selectionTestTable) {
		case 1:
				try {
					Parent rootAddStack;
					rootAddStack = FXMLLoader.load(getClass().getResource("/de/dominikemmel/reflowlab/controller/costs/fxml/addCostsStack.fxml"));
					Stage stage = new Stage();
					stage.setScene(new Scene(rootAddStack));
					stage.show();
					stage.setTitle("ReFlowLab - Add stack costs");
					stage.getIcons().add(new Image(getClass().getResourceAsStream("/de/dominikemmel/reflowlab/img/logo_simple/1x/logo_simple1x.png")));
					
					stage.getScene().getStylesheets().add(getClass().getResource("/de/dominikemmel/reflowlab/style/reflowlabStyle1.css").toExternalForm());
					
					

					stage.setOnCloseRequest(event2 -> {
						CostsController.this.reloadDataCostsStack();
					});

					stage.setOnHidden(event3 -> {
						CostsController.this.reloadDataCostsStack();
					});

				} catch (IOException e) {
					e.printStackTrace();
				}
			break;

		case 2:
				try {
					Parent rootAddSalt;
					rootAddSalt = FXMLLoader.load(getClass().getResource("/de/dominikemmel/reflowlab/controller/costs/fxml/addCostsSalt.fxml"));
					Stage stage = new Stage();
					stage.setScene(new Scene(rootAddSalt));
					stage.show();
					stage.setTitle("ReFlowLab - Add salt costs");
					stage.getIcons().add(new Image(getClass().getResourceAsStream("/de/dominikemmel/reflowlab/img/logo_simple/1x/logo_simple1x.png")));
					
					stage.getScene().getStylesheets().add(getClass().getResource("/de/dominikemmel/reflowlab/style/reflowlabStyle1.css").toExternalForm());


					stage.setOnCloseRequest(event2 -> {
						CostsController.this.reloadDataCostsSalt();
					});

					stage.setOnHidden(event3 -> {
						CostsController.this.reloadDataCostsSalt();
					});

				} catch (IOException e) {
					e.printStackTrace();
				}
			break;

		case 3:
			try {
				Parent rootAddSolvent;
				rootAddSolvent = FXMLLoader.load(getClass().getResource("/de/dominikemmel/reflowlab/controller/costs/fxml/addCostsSolvent.fxml"));
				Stage stage = new Stage();
				stage.setScene(new Scene(rootAddSolvent));
				stage.show();
				stage.setTitle("ReFlowLab - Add solvent costs");
				stage.getIcons().add(new Image(getClass().getResourceAsStream("/de/dominikemmel/reflowlab/img/logo_simple/1x/logo_simple1x.png")));
				
				stage.getScene().getStylesheets().add(getClass().getResource("/de/dominikemmel/reflowlab/style/reflowlabStyle1.css").toExternalForm());


				stage.setOnCloseRequest(event2 -> {
					CostsController.this.reloadDataCostsSolvent();
				});

				stage.setOnHidden(event3 -> {
					CostsController.this.reloadDataCostsSolvent();
				});

			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
			
		case 4:
			try {
				Parent rootAddSeparator;
				rootAddSeparator = FXMLLoader.load(getClass().getResource("/de/dominikemmel/reflowlab/controller/costs/fxml/addSeparatorParameter.fxml"));
				Stage stage = new Stage();
				stage.setScene(new Scene(rootAddSeparator));
				stage.show();
				stage.setTitle("ReFlowLab - Add separator parameters");
				stage.getIcons().add(new Image(getClass().getResourceAsStream("/de/dominikemmel/reflowlab/img/logo_simple/1x/logo_simple1x.png")));
				
				stage.getScene().getStylesheets().add(getClass().getResource("/de/dominikemmel/reflowlab/style/reflowlabStyle1.css").toExternalForm());


				stage.setOnCloseRequest(event2 -> {
					CostsController.this.reloadDataSeparatorParameter();
				});

				stage.setOnHidden(event3 -> {
					CostsController.this.reloadDataSeparatorParameter();
				});

			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
			
		case 5:
			try {
				Parent rootAddPeripherals;
				rootAddPeripherals = FXMLLoader.load(getClass().getResource("/de/dominikemmel/reflowlab/controller/costs/fxml/addCostsPeripherals.fxml"));
				Stage stage = new Stage();
				stage.setScene(new Scene(rootAddPeripherals));
				stage.show();
				stage.setTitle("ReFlowLab - Add peripherals costs");
				stage.getIcons().add(new Image(getClass().getResourceAsStream("/de/dominikemmel/reflowlab/img/logo_simple/1x/logo_simple1x.png")));
				
				stage.getScene().getStylesheets().add(getClass().getResource("/de/dominikemmel/reflowlab/style/reflowlabStyle1.css").toExternalForm());
				
				

				stage.setOnCloseRequest(event2 -> {
					CostsController.this.reloadDataCostsPeripherals();
				});

				stage.setOnHidden(event3 -> {
					CostsController.this.reloadDataCostsPeripherals();
				});

			} catch (IOException e) {
				e.printStackTrace();
			}
		break;

		}


	}

	@FXML
	private void editCosts (ActionEvent event) throws ClassNotFoundException, SQLException {
		switch(selectionTest) {
		case 1:
			ObservableList<ObjCostsStack> selectionStack = tblStackCosts.getSelectionModel().getSelectedItems();

			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/de/dominikemmel/reflowlab/controller/costs/fxml/editCostsStack.fxml"));
				Parent rootEditStack = loader.load();
				loader.setControllerFactory((Class<?> controllerType) -> {
					if (controllerType == EditCostsStackController.class) {
						EditCostsStackController controllerStack = new EditCostsStackController();
						try {
							controllerStack.setSelectionData(selectionStack);
						} catch (ClassNotFoundException | SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						return controllerStack;
					} else {
						try {
							return controllerType.newInstance();
						} catch (Exception e) {
							throw new RuntimeException(e);
						}
					}
				});

				EditCostsStackController controllerStack = loader.<EditCostsStackController>getController();
				controllerStack.setSelectionData(selectionStack);

				Stage stage = new Stage();
				stage.setScene(new Scene(rootEditStack));
				stage.show();
				stage.setTitle("ReFlowLab - Edit stack costs");
				stage.getIcons().add(new Image(getClass().getResourceAsStream("/de/dominikemmel/reflowlab/img/logo_simple/1x/logo_simple1x.png")));
				
				stage.getScene().getStylesheets().add(getClass().getResource("/de/dominikemmel/reflowlab/style/reflowlabStyle1.css").toExternalForm());



				stage.setOnCloseRequest(event2 -> {
					CostsController.this.reloadDataCostsStack();
				});

				stage.setOnHidden(event3 -> {
					CostsController.this.reloadDataCostsStack();
				});

			} catch (IOException e) {
				e.printStackTrace();
			}
			selectionTest = null;
			break;

		case 2:
			ObservableList<ObjCostsSalt> selectionSalt = tblCostsSalt.getSelectionModel().getSelectedItems();

			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/de/dominikemmel/reflowlab/controller/costs/fxml/editCostsSalt.fxml"));
				Parent rootEditSalt = loader.load();
				loader.setControllerFactory((Class<?> controllerType) -> {
					if (controllerType == EditCostsSaltController.class) {
						EditCostsSaltController controllerSalt = new EditCostsSaltController();
						try {
							controllerSalt.setSelectionData(selectionSalt);
						} catch (ClassNotFoundException | SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						return controllerSalt;
					} else {
						try {
							return controllerType.newInstance();
						} catch (Exception e) {
							throw new RuntimeException(e);
						}
					}
				});

				EditCostsSaltController controllerSalt = loader.<EditCostsSaltController>getController();
				controllerSalt.setSelectionData(selectionSalt);

				Stage stage = new Stage();
				stage.setScene(new Scene(rootEditSalt));
				stage.show();
				stage.setTitle("ReFlowLab - Edit salt costs");
				stage.getIcons().add(new Image(getClass().getResourceAsStream("/de/dominikemmel/reflowlab/img/logo_simple/1x/logo_simple1x.png")));
				
				stage.getScene().getStylesheets().add(getClass().getResource("/de/dominikemmel/reflowlab/style/reflowlabStyle1.css").toExternalForm());



				stage.setOnCloseRequest(event2 -> {
					CostsController.this.reloadDataCostsSalt();
				});

				stage.setOnHidden(event3 -> {
					CostsController.this.reloadDataCostsSalt();
				});

			} catch (IOException e) {
				e.printStackTrace();
			}
			selectionTest = null;
			break;

		case 3:
			ObservableList<ObjCostsSolvent> selectionSolvent = tblCostsSolvent.getSelectionModel().getSelectedItems();

			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/de/dominikemmel/reflowlab/controller/costs/fxml/editCostsSolvent.fxml"));
				Parent rootEditSolvent = loader.load();
				loader.setControllerFactory((Class<?> controllerType) -> {
					if (controllerType == EditCostsSolventController.class) {
						EditCostsSolventController controllerSolvent = new EditCostsSolventController();
						try {
							controllerSolvent.setSelectionData(selectionSolvent);
						} catch (ClassNotFoundException | SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						return controllerSolvent;
					} else {
						try {
							return controllerType.newInstance();
						} catch (Exception e) {
							throw new RuntimeException(e);
						}
					}
				});

				EditCostsSolventController controllerSolvent = loader.<EditCostsSolventController>getController();
				controllerSolvent.setSelectionData(selectionSolvent);

				Stage stage = new Stage();
				stage.setScene(new Scene(rootEditSolvent));
				stage.show();
				stage.setTitle("ReFlowLab - Edit solvent costs");
				stage.getIcons().add(new Image(getClass().getResourceAsStream("/de/dominikemmel/reflowlab/img/logo_simple/1x/logo_simple1x.png")));
				
				stage.getScene().getStylesheets().add(getClass().getResource("/de/dominikemmel/reflowlab/style/reflowlabStyle1.css").toExternalForm());



				stage.setOnCloseRequest(event2 -> {
					CostsController.this.reloadDataCostsSolvent();
				});

				stage.setOnHidden(event3 -> {
					CostsController.this.reloadDataCostsSolvent();
				});

			} catch (IOException e) {
				e.printStackTrace();
			}
			selectionTest = null;
			break;
			
		case 4:
			ObservableList<ObjSeparatorParameter> selectionSeparator = tblSeparatorParameter.getSelectionModel().getSelectedItems();

			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/de/dominikemmel/reflowlab/controller/costs/fxml/editSeparatorParameter.fxml"));
				Parent rootEditSeparator = loader.load();
				loader.setControllerFactory((Class<?> controllerType) -> {
					if (controllerType == EditSeparatorParameterController.class) {
						EditSeparatorParameterController controllerSeparator = new EditSeparatorParameterController();
						try {
							controllerSeparator.setSelectionData(selectionSeparator);
						} catch (ClassNotFoundException | SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						return controllerSeparator;
					} else {
						try {
							return controllerType.newInstance();
						} catch (Exception e) {
							throw new RuntimeException(e);
						}
					}
				});

				EditSeparatorParameterController controllerSeparator = loader.<EditSeparatorParameterController>getController();
				controllerSeparator.setSelectionData(selectionSeparator);

				Stage stage = new Stage();
				stage.setScene(new Scene(rootEditSeparator));
				stage.show();
				stage.setTitle("ReFlowLab - Edit separator parameters");
				stage.getIcons().add(new Image(getClass().getResourceAsStream("/de/dominikemmel/reflowlab/img/logo_simple/1x/logo_simple1x.png")));
				
				stage.getScene().getStylesheets().add(getClass().getResource("/de/dominikemmel/reflowlab/style/reflowlabStyle1.css").toExternalForm());



				stage.setOnCloseRequest(event2 -> {
					CostsController.this.reloadDataSeparatorParameter();
				});

				stage.setOnHidden(event3 -> {
					CostsController.this.reloadDataSeparatorParameter();
				});

			} catch (IOException e) {
				e.printStackTrace();
			}
			selectionTest = null;
			break;
			
		case 5:
			ObservableList<ObjCostsPeripherals> selectionPeripherals = tblPeripheralCosts.getSelectionModel().getSelectedItems();

			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/de/dominikemmel/reflowlab/controller/costs/fxml/editCostsPeripherals.fxml"));
				Parent rootEditPeripherals = loader.load();
				loader.setControllerFactory((Class<?> controllerType) -> {
					if (controllerType == EditCostsPeripheralsController.class) {
						EditCostsPeripheralsController controllerPeripherals = new EditCostsPeripheralsController();
						try {
							controllerPeripherals.setSelectionData(selectionPeripherals);
						} catch (ClassNotFoundException | SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						return controllerPeripherals;
					} else {
						try {
							return controllerType.newInstance();
						} catch (Exception e) {
							throw new RuntimeException(e);
						}
					}
				});

				EditCostsPeripheralsController controllerPeripherals = loader.<EditCostsPeripheralsController>getController();
				controllerPeripherals.setSelectionData(selectionPeripherals);

				Stage stage = new Stage();
				stage.setScene(new Scene(rootEditPeripherals));
				stage.show();
				stage.setTitle("ReFlowLab - Edit peripherals costs");
				stage.getIcons().add(new Image(getClass().getResourceAsStream("/de/dominikemmel/reflowlab/img/logo_simple/1x/logo_simple1x.png")));
				
				stage.getScene().getStylesheets().add(getClass().getResource("/de/dominikemmel/reflowlab/style/reflowlabStyle1.css").toExternalForm());



				stage.setOnCloseRequest(event2 -> {
					CostsController.this.reloadDataCostsPeripherals();
				});

				stage.setOnHidden(event3 -> {
					CostsController.this.reloadDataCostsPeripherals();
				});

			} catch (IOException e) {
				e.printStackTrace();
			}
			selectionTest = null;
			break;
			

		}
	}

}
