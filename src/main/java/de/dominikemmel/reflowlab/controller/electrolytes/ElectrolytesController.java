package de.dominikemmel.reflowlab.controller.electrolytes;

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
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import de.dominikemmel.reflowlab.Database;
import de.dominikemmel.reflowlab.DecimalColumnFactory;

public class ElectrolytesController implements javafx.fxml.Initializable {

	@FXML
	private TableView<ObjElectrolytes> tblElectrolytes;

	@FXML private TableColumn<ObjElectrolytes, Integer> ID;
	@FXML private TableColumn<ObjElectrolytes, String> ActiveMaterial;
	@FXML private TableColumn<ObjElectrolytes, String> Solvent;
	@FXML private TableColumn<ObjElectrolytes, String> Salt;
	@FXML private TableColumn<ObjElectrolytes, Double> cSalt;
	@FXML private TableColumn<ObjElectrolytes, Double> pH;
	@FXML private TableColumn<ObjElectrolytes, Double> maxSolubility;
	@FXML private TableColumn<ObjElectrolytes, Double> DOx;
	@FXML private TableColumn<ObjElectrolytes, Double> DRed;
	@FXML private TableColumn<ObjElectrolytes, Double> kOx;
	@FXML private TableColumn<ObjElectrolytes, Double> AlphaOx;
	@FXML private TableColumn<ObjElectrolytes, Double> kRed;
	@FXML private TableColumn<ObjElectrolytes, Double> AlphaRed;
	@FXML private TableColumn<ObjElectrolytes, Double> degRate;
	@FXML private TableColumn<ObjElectrolytes, Double> f;
	@FXML private TableColumn<ObjElectrolytes, Double> fEloVol;
	@FXML private TableColumn<ObjElectrolytes, Double> fConc;
	@FXML private TableColumn<ObjElectrolytes, String> note;
	@FXML private TableColumn<ObjElectrolytes, Boolean> fSymCellCycl;
	@FXML private TableColumn<ObjElectrolytes, Double> theoMaxCap;
	@FXML private TableColumn<ObjElectrolytes, String> editDate;
	
	@FXML private TableColumn<ObjElectrolytes, Integer> RefIDmaxSolubility;
	@FXML private TableColumn<ObjElectrolytes, Integer> RefIDDOx;
	@FXML private TableColumn<ObjElectrolytes, Integer> RefIDDRed;
	@FXML private TableColumn<ObjElectrolytes, Integer> RefIDkOx;
	@FXML private TableColumn<ObjElectrolytes, Integer> RefIDAlphaOx;
	@FXML private TableColumn<ObjElectrolytes, Integer> RefIDkRed;
	@FXML private TableColumn<ObjElectrolytes, Integer> RefIDAlphaRed;
	@FXML private TableColumn<ObjElectrolytes, Integer> RefIDdegRate;
	@FXML private TableColumn<ObjElectrolytes, Integer> RefIDf;

	@FXML private TextFlow ID_TextFlow;
	@FXML private TextFlow ActiveMaterial_TextFlow;
	@FXML private TextFlow Solvent_TextFlow;
	@FXML private TextFlow Salt_TextFlow;
	@FXML private TextFlow cSalt_TextFlow;
	@FXML private TextFlow pH_TextFlow;
	@FXML private TextFlow maxSolubility_TextFlow;
	@FXML private TextFlow DOx_TextFlow;
	@FXML private TextFlow DRed_TextFlow;
	@FXML private TextFlow kOx_TextFlow;
	@FXML private TextFlow AlphaOx_TextFlow;
	@FXML private TextFlow kRed_TextFlow;
	@FXML private TextFlow AlphaRed_TextFlow;
	@FXML private TextFlow degRate_TextFlow;
	@FXML private TextFlow f_TextFlow;
	@FXML private TextFlow fEloVol_TextFlow;
	@FXML private TextFlow fConc_TextFlow;
	@FXML private TextFlow note_TextFlow;
	@FXML private TextFlow fSymCellCycl_TextFlow;
	@FXML private TextFlow theoMaxCap_TextFlow;
	@FXML private TextFlow editDate_TextFlow;


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ElectrolytesController.this.addColumnNames();
		ElectrolytesController.this.reloadDataElectrolytes();
	}



	public void reloadDataElectrolytes() {

		try {
			tblElectrolytes.refresh();
			
			Database.createConnection("electrolyte");

			ResultSet res = Database.selectData("electrolyte");

		    //extracting data from ResulSet to ArrayList
	    	ArrayList<ObjElectrolytes> dataElectrolytes = new ArrayList<>();
	    	while (res.next()) {
	    		ObjElectrolytes objElectrolytes = new ObjElectrolytes();

	    		objElectrolytes.ID.set(res.getInt("ID"));
	    		objElectrolytes.ActiveMaterial.set(res.getString("ActiveMaterial"));
	    		objElectrolytes.Solvent.set(res.getString("Solvent"));
	    		objElectrolytes.Salt.set(res.getString("Salt"));
	    		objElectrolytes.cSalt.set(res.getDouble("cSalt"));
	    		objElectrolytes.pH.set(res.getDouble("pH"));
	    		objElectrolytes.maxSolubility.set(res.getDouble("maxSolubility"));
	    		objElectrolytes.DOx.set(res.getDouble("DOx"));
	    		objElectrolytes.DRed.set(res.getDouble("DRed"));
	    		objElectrolytes.kOx.set(res.getDouble("kOx"));
	    		objElectrolytes.AlphaOx.set(res.getDouble("AlphaOx"));
	    		objElectrolytes.kRed.set(res.getDouble("kRed"));
	    		objElectrolytes.AlphaRed.set(res.getDouble("AlphaRed"));
	    		objElectrolytes.degRate.set(res.getDouble("degRate"));
	    		objElectrolytes.f.set(res.getDouble("f"));
	    		objElectrolytes.fEloVol.set(res.getDouble("fEloVol"));
	    		objElectrolytes.fConc.set(res.getDouble("fConc"));
	    		objElectrolytes.note.set(res.getString("note"));
	    		if (res.getInt("fSymCellCycl") == 1) {
	    			objElectrolytes.fSymCellCycl.set(true);
	    		} else {
	    			objElectrolytes.fSymCellCycl.set(false);
	    		}
	    		    		
	    		objElectrolytes.theoMaxCap.set(res.getDouble("theoMaxCap"));
	    		
	    		objElectrolytes.RefIDmaxSolubility.set(res.getInt("RefIDmaxSolubility"));
	    		objElectrolytes.RefIDDOx.set(res.getInt("RefIDDOx"));
	    		objElectrolytes.RefIDDRed.set(res.getInt("RefIDDRed"));
	    		objElectrolytes.RefIDkOx.set(res.getInt("RefIDkOx"));
	    		objElectrolytes.RefIDAlphaOx.set(res.getInt("RefIDAlphaOx"));
	    		objElectrolytes.RefIDkRed.set(res.getInt("RefIDkRed"));
	    		objElectrolytes.RefIDAlphaRed.set(res.getInt("RefIDAlphaRed"));
	    		objElectrolytes.RefIDdegRate.set(res.getInt("RefIDdegRate"));
	    		objElectrolytes.RefIDf.set(res.getInt("RefIDf"));

		    		Timestamp timestamp = res.getTimestamp("editDate");
		    		String timestampString = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(timestamp);
		    		objElectrolytes.editDate.set(timestampString);

		    		dataElectrolytes.add(objElectrolytes);
		    	}

	    	ObservableList<ObjElectrolytes> dbData = FXCollections.observableArrayList(dataElectrolytes);

	    	ID.setCellValueFactory(new PropertyValueFactory<ObjElectrolytes, Integer>("ID"));
	    	ActiveMaterial.setCellValueFactory(new PropertyValueFactory<ObjElectrolytes, String>("ActiveMaterial"));
	    	Solvent.setCellValueFactory(new PropertyValueFactory<ObjElectrolytes, String>("Solvent"));
	    	Salt.setCellValueFactory(new PropertyValueFactory<ObjElectrolytes, String>("Salt"));
	    	cSalt.setCellValueFactory(new PropertyValueFactory<ObjElectrolytes, Double>("cSalt"));
	    	pH.setCellValueFactory(new PropertyValueFactory<ObjElectrolytes, Double>("pH"));
	    	maxSolubility.setCellValueFactory(new PropertyValueFactory<ObjElectrolytes, Double>("maxSolubility"));
	    	DOx.setCellValueFactory(new PropertyValueFactory<ObjElectrolytes, Double>("DOx"));
	    	DRed.setCellValueFactory(new PropertyValueFactory<ObjElectrolytes, Double>("DRed"));
	    	kOx.setCellValueFactory(new PropertyValueFactory<ObjElectrolytes, Double>("kOx"));
	    	AlphaOx.setCellValueFactory(new PropertyValueFactory<ObjElectrolytes, Double>("AlphaOx"));
	    	kRed.setCellValueFactory(new PropertyValueFactory<ObjElectrolytes, Double>("kRed"));
	    	AlphaRed.setCellValueFactory(new PropertyValueFactory<ObjElectrolytes, Double>("AlphaRed"));
	    	degRate.setCellValueFactory(new PropertyValueFactory<ObjElectrolytes, Double>("degRate"));
	    	f.setCellValueFactory(new PropertyValueFactory<ObjElectrolytes, Double>("f"));
	    	fEloVol.setCellValueFactory(new PropertyValueFactory<ObjElectrolytes, Double>("fEloVol"));
	    	fConc.setCellValueFactory(new PropertyValueFactory<ObjElectrolytes, Double>("fConc"));
	    	note.setCellValueFactory(new PropertyValueFactory<ObjElectrolytes, String>("note"));
	    	fSymCellCycl.setCellValueFactory(new PropertyValueFactory<ObjElectrolytes, Boolean>("fSymCellCycl"));
	    	theoMaxCap.setCellValueFactory(new PropertyValueFactory<ObjElectrolytes, Double>("theoMaxCap"));
	    	editDate.setCellValueFactory(new PropertyValueFactory<ObjElectrolytes, String>("editDate"));
	    	
	    	RefIDmaxSolubility.setCellValueFactory(new PropertyValueFactory<ObjElectrolytes, Integer>("RefIDmaxSolubility"));
	    	RefIDDOx.setCellValueFactory(new PropertyValueFactory<ObjElectrolytes, Integer>("RefIDDOx"));
	    	RefIDDRed.setCellValueFactory(new PropertyValueFactory<ObjElectrolytes, Integer>("RefIDDRed"));
	    	RefIDkOx.setCellValueFactory(new PropertyValueFactory<ObjElectrolytes, Integer>("RefIDkOx"));
	    	RefIDAlphaOx.setCellValueFactory(new PropertyValueFactory<ObjElectrolytes, Integer>("RefIDAlphaOx"));
	    	RefIDkRed.setCellValueFactory(new PropertyValueFactory<ObjElectrolytes, Integer>("RefIDkRed"));
	    	RefIDAlphaRed.setCellValueFactory(new PropertyValueFactory<ObjElectrolytes, Integer>("RefIDAlphaRed"));
	    	RefIDdegRate.setCellValueFactory(new PropertyValueFactory<ObjElectrolytes, Integer>("RefIDdegRate"));
	    	RefIDf.setCellValueFactory(new PropertyValueFactory<ObjElectrolytes, Integer>("RefIDf"));

	    	cSalt.setCellFactory(new DecimalColumnFactory<>(new DecimalFormat("0.00")));
	    	pH.setCellFactory(new DecimalColumnFactory<>(new DecimalFormat("0.00")));
	    	maxSolubility.setCellFactory(new DecimalColumnFactory<>(new DecimalFormat("0.00")));
	    	DOx.setCellFactory(new DecimalColumnFactory<>(new DecimalFormat("0.######E0")));
	    	DRed.setCellFactory(new DecimalColumnFactory<>(new DecimalFormat("0.######E0")));
	    	kOx.setCellFactory(new DecimalColumnFactory<>(new DecimalFormat("0.######E0")));
	    	AlphaOx.setCellFactory(new DecimalColumnFactory<>(new DecimalFormat("0.00")));
	    	kRed.setCellFactory(new DecimalColumnFactory<>(new DecimalFormat("0.######E0")));
	    	AlphaRed.setCellFactory(new DecimalColumnFactory<>(new DecimalFormat("0.00")));
	    	degRate.setCellFactory(new DecimalColumnFactory<>(new DecimalFormat("0.00")));
	    	f.setCellFactory(new DecimalColumnFactory<>(new DecimalFormat("0.00")));

	    	//sorting data, condition: ActiveMaterial.
	    	SortedList<ObjElectrolytes> dbDataSorted = new SortedList<>(dbData);
	    	// this ensures the dbDataSorted is sorted according to the sort columns in the table:
	    	dbDataSorted.comparatorProperty().bind(tblElectrolytes.comparatorProperty());
	        //Filling up TableView with data.
	    	tblElectrolytes.setItems(dbDataSorted);
	    	// programmatically set a sort column:
	    	tblElectrolytes.getSortOrder().addAll(ActiveMaterial);


		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}




	@FXML
	private void addColumnNames() {
    	//column names:

    	//ID:
    	Text ID1 = new Text("Id");
    	ID1.setStyle("-fx-font-weight: bold");

    	ID_TextFlow.getChildren().addAll(ID1);

    	//ActiveMaterial:
    	Text ActiveMaterial1 = new Text("Active material");
    	ActiveMaterial1.setStyle("-fx-font-weight: bold");

    	ActiveMaterial_TextFlow.getChildren().addAll(ActiveMaterial1);

    	//Solvent:
    	Text Solvent1 = new Text("Solvent");
    	Solvent1.setStyle("-fx-font-weight: bold");

    	Solvent_TextFlow.getChildren().addAll(Solvent1);

    	//Salt:
    	Text Salt1 = new Text("Salt");
    	Solvent1.setStyle("-fx-font-weight: bold");

    	Salt_TextFlow.getChildren().addAll(Salt1);

    	//cSalt:
    	Text cSalt1 = new Text("c");
    	cSalt1.setStyle("-fx-font-weight: bold");
    	cSalt1.setStyle("-fx-font-style: italic");
		Text cSalt2 = new Text("salt");
		cSalt2.setStyle("-fx-font-weight: bold");
		cSalt2.setTranslateY(cSalt1.getFont().getSize() * 0.3);
		cSalt2.setFont(Font.font(cSalt1.getFont().getStyle(),cSalt1.getFont().getSize()*0.75));
		Text cSalt3 = new Text(" / mol L");
		cSalt3.setStyle("-fx-font-weight: bold");
		Text cSalt4 = new Text("⁻¹");
		cSalt4.setStyle("-fx-font-weight: bold");

    	cSalt_TextFlow.getChildren().addAll(cSalt1,cSalt2,cSalt3,cSalt4);

    	//pH:
    	Text pH1 = new Text("pH");
    	pH1.setStyle("-fx-font-weight: bold");

    	pH_TextFlow.getChildren().addAll(pH1);

    	//maxSolubility:
    	Text maxSolubility1 = new Text("solubility");
    	maxSolubility1.setStyle("-fx-font-weight: bold");
		Text maxSolubility2 = new Text(" / mol L");
		maxSolubility2.setStyle("-fx-font-weight: bold");
		Text maxSolubility3 = new Text("⁻¹");
		maxSolubility3.setStyle("-fx-font-weight: bold");

		maxSolubility_TextFlow.getChildren().addAll(maxSolubility1,maxSolubility2,maxSolubility3);

    	//D ox:
    	Text Dox1 = new Text("D ox");
    	Dox1.setStyle("-fx-font-weight: bold");
    	Dox1.setStyle("-fx-font-style: italic");
		Text Dox2 = new Text(" / cm");
		Dox2.setStyle("-fx-font-weight: bold");
		Text Dox3 = new Text("²");
		Dox3.setStyle("-fx-font-weight: bold");
    	Text Dox4 = new Text(" s");
    	Dox4.setStyle("-fx-font-weight: bold");
		Text Dox5 = new Text("⁻¹");
		Dox5.setStyle("-fx-font-weight: bold");

    	DOx_TextFlow.getChildren().addAll(Dox1,Dox2,Dox3,Dox4,Dox5);

    	//D red:
    	Text Dred1 = new Text("D red");
    	Dred1.setStyle("-fx-font-weight: bold");
    	Dred1.setStyle("-fx-font-style: italic");
		Text Dred2 = new Text(" / cm");
		Dred2.setStyle("-fx-font-weight: bold");
		Text Dred3 = new Text("²");
		Dred3.setStyle("-fx-font-weight: bold");
    	Text Dred4 = new Text(" s");
    	Dred4.setStyle("-fx-font-weight: bold");
		Text Dred5 = new Text("⁻¹");
		Dred5.setStyle("-fx-font-weight: bold");

    	DRed_TextFlow.getChildren().addAll(Dred1,Dred2,Dred3,Dred4,Dred5);

    	//k ox:
    	Text kox1 = new Text("k ox");
    	kox1.setStyle("-fx-font-weight: bold");
    	kox1.setStyle("-fx-font-style: italic");
		Text kox2 = new Text(" / cm");
		kox2.setStyle("-fx-font-weight: bold");
    	Text kox4 = new Text(" s");
    	kox4.setStyle("-fx-font-weight: bold");
		Text kox5 = new Text("⁻¹");
		kox5.setStyle("-fx-font-weight: bold");

    	kOx_TextFlow.getChildren().addAll(kox1,kox2,kox4,kox5);

    	//Alpha ox:
    	Text alphaOx1 = new Text("a ox");
    	alphaOx1.setStyle("-fx-font-weight: bold");
    	alphaOx1.setStyle("-fx-font-style: italic");

    	AlphaOx_TextFlow.getChildren().addAll(alphaOx1);

    	//Alpha red:
    	Text alphaRed1 = new Text("a red");
    	alphaRed1.setStyle("-fx-font-weight: bold");
    	alphaRed1.setStyle("-fx-font-style: italic");

    	AlphaRed_TextFlow.getChildren().addAll(alphaRed1);

    	//k red:
    	Text kred1 = new Text("k red");
    	kred1.setStyle("-fx-font-weight: bold");
    	kred1.setStyle("-fx-font-style: italic");
		Text kred2 = new Text(" / cm");
		kred2.setStyle("-fx-font-weight: bold");
    	Text kred4 = new Text(" s");
    	kred4.setStyle("-fx-font-weight: bold");
		Text kred5 = new Text("⁻¹");
		kred5.setStyle("-fx-font-weight: bold");

    	kRed_TextFlow.getChildren().addAll(kred1,kred2,kred4,kred5);

    	//degRate:
    	Text degRate1 = new Text("fade rate / % d");
    	degRate1.setStyle("-fx-font-weight: bold");
    	Text degRate2 = new Text("⁻¹");
    	degRate2.setStyle("-fx-font-weight: bold");

    	degRate_TextFlow.getChildren().addAll(degRate1, degRate2);

    	//f:
    	Text f1 = new Text("f ");
    	f1.setStyle("-fx-font-weight: bold");
    	f1.setStyle("-fx-font-style: italic");
    	Text f2 = new Text("/ Y");
    	f2.setStyle("-fx-font-weight: bold");
    	Text f3 = new Text("⁻¹");
    	f3.setStyle("-fx-font-weight: bold");

    	f_TextFlow.getChildren().addAll(f1, f2, f3);
    	
    	//fEloVol:
    	Text fEloVol1 = new Text("V");
    	fEloVol1.setStyle("-fx-font-weight: bold");
    	fEloVol1.setStyle("-fx-font-style: italic");
    	Text fEloVol2 = new Text("theo., max., cap.");
    	fEloVol2.setStyle("-fx-font-weight: bold");
    	fEloVol2.setTranslateY(fEloVol1.getFont().getSize() * 0.3);
    	fEloVol2.setFont(Font.font(fEloVol1.getFont().getStyle(),fEloVol1.getFont().getSize()*0.75));
    	Text fEloVol3 = new Text(" / mL");
    	fEloVol3.setStyle("-fx-font-weight: bold");
    	
    	fEloVol_TextFlow.getChildren().addAll(fEloVol1, fEloVol2, fEloVol3);
    	
    	//fConc:
    	Text fConc1 = new Text("c");
    	fConc1.setStyle("-fx-font-weight: bold");
    	fConc1.setStyle("-fx-font-style: italic");
    	Text fConc2 = new Text("theo., max., cap.");
    	fConc2.setStyle("-fx-font-weight: bold");
    	fConc2.setTranslateY(fConc1.getFont().getSize() * 0.3);
    	fConc2.setFont(Font.font(fConc1.getFont().getStyle(),fConc1.getFont().getSize()*0.75));
    	Text fConc3 = new Text(" / mol L");
    	fConc3.setStyle("-fx-font-weight: bold");
    	Text fConc4 = new Text("⁻¹");
    	fConc4.setStyle("-fx-font-weight: bold");
    	
    	fConc_TextFlow.getChildren().addAll(fConc1, fConc2, fConc3, fConc4);
    	
    	//note:
    	Text note1 = new Text("note");
    	note1.setStyle("-fx-font-weight: bold");
    	
    	note_TextFlow.getChildren().addAll(note1);
    	
    	//fSymCellCycl:
    	Text fSymCellCycl1 = new Text("sym. cyclization?");
    	fSymCellCycl1.setStyle("-fx-font-weight: bold");
    	
    	fSymCellCycl_TextFlow.getChildren().addAll(fSymCellCycl1);
    	
    	//theoMaxCap:
    	Text theoMaxCap1 = new Text("q");
    	theoMaxCap1.setStyle("-fx-font-weight: bold");
    	theoMaxCap1.setStyle("-fx-font-style: italic");
    	Text theoMaxCap2 = new Text("max., theo.");
    	theoMaxCap2.setStyle("-fx-font-weight: bold");
    	theoMaxCap2.setTranslateY(theoMaxCap1.getFont().getSize() * 0.3);
    	Text theoMaxCap3 = new Text("/ mAh L");
    	theoMaxCap3.setStyle("-fx-font-weight: bold");
    	Text theoMaxCap4 = new Text("⁻¹");
    	theoMaxCap4.setStyle("-fx-font-weight: bold");
    	
    	theoMaxCap_TextFlow.getChildren().addAll(theoMaxCap1, theoMaxCap2, theoMaxCap3, theoMaxCap4);

    	//editDate:
		Text editDate1 = new Text("Date");
		editDate1.setStyle("-fx-font-weight: bold");
		Text editDate2 = new Text("edit");
		editDate2.setStyle("-fx-font-weight: bold");
		editDate2.setTranslateY(editDate1.getFont().getSize() * 0.3);
		editDate2.setFont(Font.font(editDate1.getFont().getStyle(),editDate1.getFont().getSize()*0.75));

		editDate_TextFlow.getChildren().addAll(editDate1,editDate2);



	}

	@FXML
	private void addElectrolytes (ActionEvent event) {
		try {
			Parent rootAddElectrolytes;
			rootAddElectrolytes = FXMLLoader.load(getClass().getResource("/de/dominikemmel/reflowlab/controller/electrolytes/fxml/addElectrolytes.fxml"));
			Stage stage = new Stage();
			stage.setScene(new Scene(rootAddElectrolytes));
			stage.show();
			stage.setTitle("ReFlowLab - Add electrolyte");
			stage.getIcons().add(new Image(getClass().getResourceAsStream("/de/dominikemmel/reflowlab/img/logo_simple/1x/logo_simple1x.png")));
			
			stage.getScene().getStylesheets().add(getClass().getResource("/de/dominikemmel/reflowlab/style/reflowlabStyle1.css").toExternalForm());


			stage.setOnCloseRequest(event2 -> {
				ElectrolytesController.this.reloadDataElectrolytes();
			});

			stage.setOnHidden(event3 -> {
				ElectrolytesController.this.reloadDataElectrolytes();
			});

		} catch (IOException e) {
			e.printStackTrace();
		}
	}




	@FXML
	private void editElectrolytes (ActionEvent event) throws ClassNotFoundException, SQLException {
		ObservableList<ObjElectrolytes> selection = tblElectrolytes.getSelectionModel().getSelectedItems();

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/de/dominikemmel/reflowlab/controller/electrolytes/fxml/editElectrolytes.fxml"));
			Parent rootEditElectrolytes = loader.load();
			loader.setControllerFactory((Class<?> controllerType) -> {
				if (controllerType == EditElectrolytesController.class) {
					EditElectrolytesController controller = new EditElectrolytesController();
					try {
						controller.setSelectionData(selection);
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return controller;
				} else {
					try {
						return controllerType.newInstance();
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				}
			});

			EditElectrolytesController controller = loader.<EditElectrolytesController>getController();
			controller.setSelectionData(selection);

			Stage stage = new Stage();
			stage.setScene(new Scene(rootEditElectrolytes));
			stage.show();
			stage.setTitle("ReFlowLab - Edit electrolyte");
			stage.getIcons().add(new Image(getClass().getResourceAsStream("/de/dominikemmel/reflowlab/img/logo_simple/1x/logo_simple1x.png")));
			
			stage.getScene().getStylesheets().add(getClass().getResource("/de/dominikemmel/reflowlab/style/reflowlabStyle1.css").toExternalForm());
			


			stage.setOnCloseRequest(event2 -> {
				ElectrolytesController.this.reloadDataElectrolytes();
			});

			stage.setOnHidden(event3 -> {
				ElectrolytesController.this.reloadDataElectrolytes();
			});

		} catch (IOException e) {
			e.printStackTrace();
		}



	}

}
