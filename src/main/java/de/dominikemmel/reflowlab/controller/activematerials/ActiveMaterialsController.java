package de.dominikemmel.reflowlab.controller.activematerials;

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
import javafx.scene.control.Button;
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


public class ActiveMaterialsController implements javafx.fxml.Initializable {

	@FXML
	private TableView<ObjActiveMaterial> tblActiveMaterial;

	@FXML private TableColumn<ObjActiveMaterial, Integer> ID;
	@FXML private TableColumn<ObjActiveMaterial, String> ABBREVIATION;
	@FXML private TableColumn<ObjActiveMaterial, String> NAME;
	@FXML private TableColumn<ObjActiveMaterial, String> STRUCTURALFORMULA;
	@FXML private TableColumn<ObjActiveMaterial, Double> M;
	@FXML private TableColumn<ObjActiveMaterial, Integer> N;
	@FXML private TableColumn<ObjActiveMaterial, Integer> NumberH;
	@FXML private TableColumn<ObjActiveMaterial, Double> CAM;
	@FXML private TableColumn<ObjActiveMaterial, String> SOLVENT;
	@FXML private TableColumn<ObjActiveMaterial, String> Salt;
	@FXML private TableColumn<ObjActiveMaterial, Double> SaltC;
	@FXML private TableColumn<ObjActiveMaterial, Double> PH;
	@FXML private TableColumn<ObjActiveMaterial, Double> E;
	@FXML private TableColumn<ObjActiveMaterial, String> EDITDATE;
	
	@FXML private TableColumn<ObjActiveMaterial, Integer> RefIDn;
	@FXML private TableColumn<ObjActiveMaterial, Integer> RefIDNumberH;
	@FXML private TableColumn<ObjActiveMaterial, Integer> RefIDCAM;
	@FXML private TableColumn<ObjActiveMaterial, Integer> RefIDE;

	@FXML private TextFlow ID_TextFlow;
	@FXML private TextFlow ABBREVIATION_TextFlow;
	@FXML private TextFlow NAME_TextFlow;
	@FXML private TextFlow STRUCTURALFORMULA_TextFlow;
	@FXML private TextFlow M_TextFlow;
	@FXML private TextFlow N_TextFlow;
	@FXML private TextFlow NumberH_TextFlow;
	@FXML private TextFlow CAM_TextFlow;
	@FXML private TextFlow SOLVENT_TextFlow;
	@FXML private TextFlow Salt_TextFlow;
	@FXML private TextFlow SaltC_TextFlow;
	@FXML private TextFlow PH_TextFlow;
	@FXML private TextFlow E_TextFlow;
	@FXML private TextFlow EDITDATE_TextFlow;

	@FXML private Button addActiveMaterial;
	@FXML private Button editActiveMaterial;


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		ActiveMaterialsController.this.addColumnNames();
		ActiveMaterialsController.this.reloadDataActiveMaterials();

	}

	public void reloadDataActiveMaterials() {

		try {
			tblActiveMaterial.refresh();
			
			Database.createConnection("activeMaterial");

			ResultSet res = Database.selectData("activeMaterial");

		    //extracting data from ResulSet to ArrayList
	    	ArrayList<ObjActiveMaterial> dataActiveMaterial = new ArrayList<>();
	    	while (res.next()) {
		    		ObjActiveMaterial objActiveMaterial = new ObjActiveMaterial();

		    		objActiveMaterial.ID.set(res.getInt("ID"));
		    		objActiveMaterial.ABBREVIATION.set(res.getString("ABBREVIATION"));
		    		objActiveMaterial.NAME.set(res.getString("NAME"));
		    		objActiveMaterial.STRUCTURALFORMULA.set(res.getString("STRUCTURALFORMULA"));
		    		objActiveMaterial.M.set(res.getDouble("M"));
		    		objActiveMaterial.N.set(res.getInt("N"));
		    		objActiveMaterial.NumberH.set(res.getInt("NumberH"));
		    		objActiveMaterial.CAM.set(res.getDouble("CAM"));
		    		objActiveMaterial.SOLVENT.set(res.getString("SOLVENT"));
		    		objActiveMaterial.Salt.set(res.getString("Salt"));
		    		objActiveMaterial.SaltC.set(res.getDouble("SaltC"));
		    		objActiveMaterial.PH.set(res.getDouble("PH"));
		    		objActiveMaterial.E.set(res.getDouble("E"));
		    		objActiveMaterial.RefIDn.set(res.getInt("RefIDn"));
		    		objActiveMaterial.RefIDNumberH.set(res.getInt("RefIDNumberH"));
		    		objActiveMaterial.RefIDCAM.set(res.getInt("RefIDCAM"));
		    		objActiveMaterial.RefIDE.set(res.getInt("RefIDE"));

		    		Timestamp timestamp = res.getTimestamp("EDITDATE");
		    		String timestampString = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(timestamp);
		    		objActiveMaterial.EDITDATE.set(timestampString);

		    		dataActiveMaterial.add(objActiveMaterial);
		    	}

	    	ObservableList<ObjActiveMaterial> dbData = FXCollections.observableArrayList(dataActiveMaterial);

	    	ID.setCellValueFactory(new PropertyValueFactory<ObjActiveMaterial, Integer>("ID"));
	    	ABBREVIATION.setCellValueFactory(new PropertyValueFactory<ObjActiveMaterial, String>("ABBREVIATION"));
	    	NAME.setCellValueFactory(new PropertyValueFactory<ObjActiveMaterial, String>("NAME"));
	    	STRUCTURALFORMULA.setCellValueFactory(new PropertyValueFactory<ObjActiveMaterial, String>("STRUCTURALFORMULA"));
	    	M.setCellValueFactory(new PropertyValueFactory<ObjActiveMaterial, Double>("M"));
	    	N.setCellValueFactory(new PropertyValueFactory<ObjActiveMaterial, Integer>("N"));
	    	NumberH.setCellValueFactory(new PropertyValueFactory<ObjActiveMaterial, Integer>("NumberH"));
	    	CAM.setCellValueFactory(new PropertyValueFactory<ObjActiveMaterial, Double>("CAM"));
	    	SOLVENT.setCellValueFactory(new PropertyValueFactory<ObjActiveMaterial, String>("SOLVENT"));
	    	Salt.setCellValueFactory(new PropertyValueFactory<ObjActiveMaterial, String>("Salt"));
	    	SaltC.setCellValueFactory(new PropertyValueFactory<ObjActiveMaterial, Double>("SaltC"));
	    	PH.setCellValueFactory(new PropertyValueFactory<ObjActiveMaterial, Double>("PH"));
	    	E.setCellValueFactory(new PropertyValueFactory<ObjActiveMaterial, Double>("E"));
	    	EDITDATE.setCellValueFactory(new PropertyValueFactory<ObjActiveMaterial, String>("EDITDATE"));
	    	RefIDn.setCellValueFactory(new PropertyValueFactory<ObjActiveMaterial, Integer>("RefIDn"));
	    	RefIDNumberH.setCellValueFactory(new PropertyValueFactory<ObjActiveMaterial, Integer>("RefIDNumberH"));
	    	RefIDCAM.setCellValueFactory(new PropertyValueFactory<ObjActiveMaterial, Integer>("RefIDCAM"));
	    	RefIDE.setCellValueFactory(new PropertyValueFactory<ObjActiveMaterial, Integer>("RefIDE"));

	    	M.setCellFactory(new DecimalColumnFactory<>(new DecimalFormat("0.00")));
	    	N.setCellFactory(new DecimalColumnFactory<>(new DecimalFormat("0.00")));
	    	NumberH.setCellFactory(new DecimalColumnFactory<>(new DecimalFormat("0.00")));
	    	CAM.setCellFactory(new DecimalColumnFactory<>(new DecimalFormat("0.00")));
	    	SaltC.setCellFactory(new DecimalColumnFactory<>(new DecimalFormat("0.00")));
	    	PH.setCellFactory(new DecimalColumnFactory<>(new DecimalFormat("0.00")));
	    	E.setCellFactory(new DecimalColumnFactory<>(new DecimalFormat("0.00")));

	    	//sorting data, condition: Abbreviation.
	    	SortedList<ObjActiveMaterial> dbDataSorted = new SortedList<>(dbData);
	    	// this ensures the dbDataSorted is sorted according to the sort columns in the table:
	    	dbDataSorted.comparatorProperty().bind(tblActiveMaterial.comparatorProperty());
	        //Filling up TableView with data.
	    	tblActiveMaterial.setItems(dbDataSorted);
	    	// programmatically set a sort column:
	    	tblActiveMaterial.getSortOrder().addAll(ABBREVIATION);


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

    	//Abbreviation:
    	Text Abbreviation1 = new Text("Abbreviation");
    	Abbreviation1.setStyle("-fx-font-weight: bold");

    	ABBREVIATION_TextFlow.getChildren().addAll(Abbreviation1);

    	//Name:
    	Text Name1 = new Text("Name");
    	Name1.setStyle("-fx-font-weight: bold");

    	NAME_TextFlow.getChildren().addAll(Name1);

    	//STRUCTURALFORMULA:
		Text STRUCTURALFORMULA1 = new Text("Structural Formula");
		STRUCTURALFORMULA1.setStyle("-fx-font-weight: bold");

		STRUCTURALFORMULA_TextFlow.getChildren().addAll(STRUCTURALFORMULA1);

		//M:
		Text M1 = new Text("M");
		M1.setStyle("-fx-font-weight: bold");
		M1.setStyle("-fx-font-style: italic");
		Text M2 = new Text(" / g mol");
		M2.setStyle("-fx-font-weight: bold");
		Text M3 = new Text("-1");
		M3.setStyle("-fx-font-weight: bold");
		M3.setTranslateY(M1.getFont().getSize() * -0.3);
		M3.setFont(Font.font(M2.getFont().getStyle(),M2.getFont().getSize()*0.75));

		M_TextFlow.getChildren().addAll(M1,M2,M3);

		//N:
		Text N1 = new Text("n");
		N1.setStyle("-fx-font-weight: bold");
		N1.setStyle("-fx-font-style: italic");
		Text N2 = new Text("e-");
		N2.setStyle("-fx-font-weight: bold");
		N2.setTranslateY(N1.getFont().getSize() * 0.3);
		N2.setFont(Font.font(N1.getFont().getStyle(),N1.getFont().getSize()*0.75));

		N_TextFlow.getChildren().addAll(N1,N2);

		//NumberH:
		Text NumberH1 = new Text("n");
		NumberH1.setStyle("-fx-font-weight: bold");
		NumberH1.setStyle("-fx-font-style: italic");
		Text NumberH2 = new Text("H+");
		NumberH2.setStyle("-fx-font-weight: bold");
		NumberH2.setTranslateY(NumberH1.getFont().getSize() * 0.3);
		NumberH2.setFont(Font.font(NumberH1.getFont().getStyle(),NumberH1.getFont().getSize()*0.75));

		NumberH_TextFlow.getChildren().addAll(NumberH1,NumberH2);

		//C_AM:
		Text C_AM1 = new Text("C");
		C_AM1.setStyle("-fx-font-weight: bold");
		C_AM1.setStyle("-fx-font-style: italic");
		Text C_AM2 = new Text("AM");
		C_AM2.setStyle("-fx-font-weight: bold");
		C_AM2.setTranslateY(C_AM1.getFont().getSize() * 0.3);
		C_AM2.setFont(Font.font(C_AM1.getFont().getStyle(),C_AM1.getFont().getSize()*0.75));
		Text C_AM3 = new Text(" / $ kg");
		C_AM3.setStyle("-fx-font-weight: bold");
		Text C_AM4 = new Text("-1");
		C_AM4.setStyle("-fx-font-weight: bold");
		C_AM4.setTranslateY(C_AM1.getFont().getSize() * -0.3);
		C_AM4.setFont(Font.font(C_AM1.getFont().getStyle(),C_AM1.getFont().getSize()*0.75));

		CAM_TextFlow.getChildren().addAll(C_AM1,C_AM2,C_AM3,C_AM4);

		//Solvent:
		Text SOLVENT1 = new Text("Solvent");
		SOLVENT1.setStyle("-fx-font-weight: bold");

		SOLVENT_TextFlow.getChildren().addAll(SOLVENT1);

		//Salt:
		Text Salt1 = new Text("Salt");
		Salt1.setStyle("-fx-font-weight: bold");

		Salt_TextFlow.getChildren().addAll(Salt1);

		//Saltc:
		Text SaltC1 = new Text("c");
		SaltC1.setStyle("-fx-font-weight: bold");
		SaltC1.setStyle("-fx-font-style: italic");
		Text SaltC2 = new Text("salt");
		SaltC2.setStyle("-fx-font-weight: bold");
		SaltC2.setTranslateY(SaltC1.getFont().getSize() * 0.3);
		SaltC2.setFont(Font.font(SaltC1.getFont().getStyle(),SaltC1.getFont().getSize()*0.75));
		Text SaltC3 = new Text(" / mol L");
		SaltC3.setStyle("-fx-font-weight: bold");
		Text SaltC4 = new Text("-1");
		SaltC4.setStyle("-fx-font-weight: bold");
		SaltC4.setTranslateY(SaltC1.getFont().getSize() * -0.3);
		SaltC4.setFont(Font.font(SaltC1.getFont().getStyle(),SaltC1.getFont().getSize()*0.75));

		SaltC_TextFlow.getChildren().addAll(SaltC1,SaltC2,SaltC3,SaltC4);

		//pH:
		Text pH1 = new Text("pH");
		pH1.setStyle("-fx-font-weight: bold");

		PH_TextFlow.getChildren().addAll(pH1);

		//E:
		Text E1 = new Text("E");
		E1.setStyle("-fx-font-weight: bold");
		E1.setStyle("-fx-font-style: italic");
		Text E2 = new Text("� vs NHE / V");
		E2.setStyle("-fx-font-weight: bold");

		E_TextFlow.getChildren().addAll(E1,E2);

		//EDITDATE:
		Text EDITDATE1 = new Text("Date");
		EDITDATE1.setStyle("-fx-font-weight: bold");
		Text EDITDATE2 = new Text("edit");
		EDITDATE2.setStyle("-fx-font-weight: bold");
		EDITDATE2.setTranslateY(EDITDATE1.getFont().getSize() * 0.3);
		EDITDATE2.setFont(Font.font(EDITDATE1.getFont().getStyle(),EDITDATE1.getFont().getSize()*0.75));

		EDITDATE_TextFlow.getChildren().addAll(EDITDATE1,EDITDATE2);
	}

	@FXML
	public void addActiveMaterial(ActionEvent event) {
		try {
			Parent rootAddActiveMaterial;
			rootAddActiveMaterial = FXMLLoader.load(getClass().getResource("/de/dominikemmel/reflowlab/controller/activematerials/fxml/addActiveMaterial.fxml"));
			Stage stage = new Stage();
			stage.setScene(new Scene(rootAddActiveMaterial));
			stage.show();
			
			stage.setTitle("ReFlowLab - Add Active materials");
			stage.getIcons().add(new Image(getClass().getResourceAsStream("/de/dominikemmel/reflowlab/img/logo_simple/1x/logo_simple1x.png")));
			
			stage.getScene().getStylesheets().add(getClass().getResource("/de/dominikemmel/reflowlab/style/reflowlabStyle1.css").toExternalForm());
			


			stage.setOnCloseRequest(event2 -> {
				ActiveMaterialsController.this.reloadDataActiveMaterials();
			});

			stage.setOnHidden(event3 -> {
				ActiveMaterialsController.this.reloadDataActiveMaterials();
			});

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void editActiveMaterial(ActionEvent event) throws ClassNotFoundException, SQLException {

		ObservableList<ObjActiveMaterial> selection = tblActiveMaterial.getSelectionModel().getSelectedItems();

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/de/dominikemmel/reflowlab/controller/activematerials/fxml/editActiveMaterial.fxml"));
			Parent rootEditActiveMaterial = loader.load();
			loader.setControllerFactory((Class<?> controllerType) -> {
				if (controllerType == EditActiveMaterialController.class) {
					EditActiveMaterialController controller = new EditActiveMaterialController();
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

			EditActiveMaterialController controller = loader.<EditActiveMaterialController>getController();
			controller.setSelectionData(selection);

			Stage stage = new Stage();
			stage.setScene(new Scene(rootEditActiveMaterial));
			stage.show();
			
			stage.setTitle("ReFlowLab - Edit Active materials");
			stage.getIcons().add(new Image(getClass().getResourceAsStream("/de/dominikemmel/reflowlab/img/logo_simple/1x/logo_simple1x.png")));
			
			stage.getScene().getStylesheets().add(getClass().getResource("/de/dominikemmel/reflowlab/style/reflowlabStyle1.css").toExternalForm());


			stage.setOnCloseRequest(event2 -> {
				ActiveMaterialsController.this.reloadDataActiveMaterials();
			});

			stage.setOnHidden(event3 -> {
				ActiveMaterialsController.this.reloadDataActiveMaterials();
			});

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
