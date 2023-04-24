package de.dominikemmel.reflowlab.controller.ragonetool;

import static de.dominikemmel.reflowlab.MyConstants.F;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import org.jfree.chart.JFreeChart;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import de.dominikemmel.reflowlab.Database;
import de.dominikemmel.reflowlab.controller.activematerials.ObjActiveMaterial;
import de.dominikemmel.reflowlab.controller.costanalysistool.ChartCanvasJFreeChart;
import de.dominikemmel.reflowlab.controller.electrolytes.ObjElectrolytes;
import de.dominikemmel.reflowlab.controller.solvent.ObjInorganicSolvent;
import de.dominikemmel.reflowlab.controller.solvent.ObjOrganicSolvent;

public class RagoneAnalysisController implements javafx.fxml.Initializable {
	@FXML
	private TextField cellVoltage_Output;
	@FXML
	private TextFlow cellVoltage_TextFlow;
	@FXML
	private TextField currentCapacity_Output;
	@FXML
	private TextFlow currentCapacity_TextFlow;
	@FXML
	private TextField power_Output;
	@FXML
	private TextFlow power_TextFlow;
	@FXML
	private TextField energyCapacity_Output;
	@FXML
	private TextFlow energyCapacity_TextFlow;
	@FXML
	private TextField specPower_Output;
	@FXML
	private TextFlow specPower_TextFlow;
	@FXML
	private TextField specEnergyCapacity_Output;
	@FXML
	private TextFlow specEnergyCapacity_TextFlow;
	@FXML
	private TextField specPowerVol_Output;
	@FXML
	private TextFlow specPowerVol_TextFlow;
	@FXML
	private TextField specEnergyCapacityVol_Output;
	@FXML
	private TextFlow specEnergyCapacityVol_TextFlow;
	@FXML
	private TextField cellVoltageDet_Output;
	@FXML
	private TextFlow cellVoltageDet_TextFlow;
	@FXML
	private TextField currentCapacityDet_Output;
	@FXML
	private TextFlow currentCapacityDet_TextFlow;
	@FXML
	private TextField powerDet_Output;
	@FXML
	private TextFlow powerDet_TextFlow;
	@FXML
	private TextField energyCapacityDet_Output;
	@FXML
	private TextFlow energyCapacityDet_TextFlow;
	@FXML
	private TextField volumeElectrolyte_Output;
	@FXML
	private TextFlow volumeElectrolyte_TextFlow;
	@FXML
	private TextField massElectrolyte_Output;
	@FXML
	private TextFlow massElectrolyte_TextFlow;
	@FXML
	private TextField currentGrav_Output;
	@FXML
	private TextFlow currentGrav_TextFlow;
	@FXML
	private TextField currentVol_Output;
	@FXML
	private TextFlow currentVol_TextFlow;
	@FXML
	private TextField volumeElectrolyteLimitSide_Output;
	@FXML
	private TextFlow volumeElectrolyteLimitSide_TextFlow;
	@FXML
	private TextField massElectrolyteLimitSide_Output;
	@FXML
	private TextFlow massElectrolyteLimitSide_TextFlow;
	@FXML
	private TextField currentGravLimitSide_Output;
	@FXML
	private TextFlow currentGravLimitSide_TextFlow;
	@FXML
	private TextField currentVolLimitSide_Output;
	@FXML
	private TextFlow currentVolLimitSide_TextFlow;
	@FXML
	private TextField specPowerDet_Output;
	@FXML
	private TextFlow specPowerDet_TextFlow;
	@FXML
	private TextField specEnergyCapacityDet_Output;
	@FXML
	private TextFlow specEnergyCapacityDet_TextFlow;
	@FXML
	private TextField specPowerVolDet_Output;
	@FXML
	private TextFlow specPowerVolDet_TextFlow;
	@FXML
	private TextField specEnergyCapacityVolDet_Output;
	@FXML
	private TextFlow specEnergyCapacityVolDet_TextFlow;

	@FXML
	private TextFlow electrodeArea_TextFlow;

	@FXML
	private TextField specPowerDetLimitSide_Output;

	@FXML
	private TextField specEnergyCapacityDetLimitSide_Output;

	@FXML
	private TextField specPowerVolDetLimitSide_Output;

	@FXML
	private TextField specEnergyCapacityVolDetLimitSide_Output;



	@FXML
	private Button btnCalculate;
	@FXML
	private TextFlow CInput_TextFlow;
	@FXML
	private TextField surfaceArea_Input;
	@FXML
	private TextField AnolyteVolume_Input;
	@FXML
	private TextField CatholyteVolume_Input;
	@FXML
	private ComboBox AnolyteActiveMaterial_Input;
	@FXML
	private ComboBox AnolyteSolvent_Input;
	@FXML
	private ComboBox AnolyteSalt_Input;
	@FXML
	private ComboBox CatholyteActiveMaterial_Input;
	@FXML
	private ComboBox CatholyteSolvent_Input;
	@FXML
	private ComboBox CatholyteSalt_Input;

//	@FXML
//	private ScatterChart<Double, Double> ragonePlot;
//	@FXML
//	private NumberAxis yAxis;
//	@FXML
//	private NumberAxis xAxis;

	@FXML
	private TextField AnolyteC_Input;
	@FXML
	private TextField CatholyteC_Input;
	@FXML
	private Pane centerChart;



	String anolyteActiveMaterialSelection;
	String catholyteActiveMaterialSelection;
	String anolyteSaltSelection;
	Double anolyteSaltcSelection;
	String catholyteSaltSelection;
	Double catholyteSaltcSelection;
	String anolyteSolventSelection;
	String catholyteSolventSelection;

	Integer inorganicSolventAnolyte = 0;
	Integer organicSolventAnolyte = 0;
	Integer inorganicSolventCatholyte = 0;
	Integer organicSolventCatholyte = 0;

	Connection con = null;
	Connection con1 = null;
	Connection con2 = null;
	Connection con3 = null;

	ObjActiveMaterial objActiveMaterialAnolyte = new ObjActiveMaterial();
	ObjActiveMaterial objActiveMaterialCatholyte = new ObjActiveMaterial();
	ObjInorganicSolvent objSolventInorganicAnolyte = new ObjInorganicSolvent();
	ObjInorganicSolvent objSolventInorganicCatholyte = new ObjInorganicSolvent();
	ObjOrganicSolvent objSolventOrganicAnolyte = new ObjOrganicSolvent();
	ObjOrganicSolvent objSolventOrganicCatholyte = new ObjOrganicSolvent();
	ObjElectrolytes objElectrolytesAnolyte = new ObjElectrolytes();
	ObjElectrolytes objElectrolytesCatholyte = new ObjElectrolytes();

	JFreeChart newChart;

	double xValue = 0;
	double yValue = 0;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		RagoneAnalysisController.this.populateAnolyteCombobox();
		RagoneAnalysisController.this.populateCatholyteCombobox();
		RagoneAnalysisController.this.addNames();

//        ChartViewer cv = new ChartViewer(newChart);
//        cv.setPrefWidth(422);
//        cv.setPrefHeight(294);

        ScatterPlotRagone Chart = new ScatterPlotRagone();
		newChart = Chart.ScatterPlotRagone("", xValue, yValue);
        centerChart.getChildren().removeAll();

        ChartCanvasJFreeChart chartCanvas = new ChartCanvasJFreeChart(newChart);

        chartCanvas.widthProperty().bind(centerChart.widthProperty());
        chartCanvas.heightProperty().bind(centerChart.heightProperty());

        centerChart.getChildren().add(chartCanvas);

	}

	private void populateAnolyteCombobox() {
		AnolyteActiveMaterial_Input.setEditable(true);
		try {
			ResultSet res = Database.selectData("activeMaterial");

			ObservableList<String> dataABBREVIATION = FXCollections.observableArrayList();
	    	while (res.next()) {
	    		dataABBREVIATION.add(res.getString("ABBREVIATION"));
	    	}
			AnolyteActiveMaterial_Input.setItems(dataABBREVIATION);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}


	private void populateCatholyteCombobox() {
		CatholyteActiveMaterial_Input.setEditable(true);
		try {
			ResultSet res = Database.selectData("activeMaterial");

			ObservableList<String> dataABBREVIATION = FXCollections.observableArrayList();
			while (res.next()) {
				dataABBREVIATION.add(res.getString("ABBREVIATION"));
			}
			CatholyteActiveMaterial_Input.setItems(dataABBREVIATION);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}


	@FXML
	private void populateAnolyteSolventCombobox(ActionEvent event) {

		try {
			AnolyteSolvent_Input.getSelectionModel().clearSelection();

			ResultSet res = Database.selectData("activeMaterial");

			ObservableList<String> dataSolvent = FXCollections.observableArrayList();

			String AnolyteSelection = AnolyteActiveMaterial_Input.getSelectionModel().getSelectedItem().toString();

			while (res.next()) {
				if (res.getString("ABBREVIATION") == AnolyteSelection) {
					String dataSolventCombination =  res.getString("Solvent");
					dataSolvent.add(dataSolventCombination);
				}
			}

			AnolyteSolvent_Input.setItems(dataSolvent);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}


	@FXML
	private void populateCatholyteSolventCombobox(ActionEvent event) {

		try {
			CatholyteSolvent_Input.getSelectionModel().clearSelection();

			ResultSet res = Database.selectData("activeMaterial");

			ObservableList<String> dataSolvent = FXCollections.observableArrayList();

			String CatholyteSelection = CatholyteActiveMaterial_Input.getSelectionModel().getSelectedItem().toString();

			while (res.next()) {
				if (res.getString("ABBREVIATION") == CatholyteSelection) {
					String dataSolventCombination =  res.getString("Solvent");
					dataSolvent.add(dataSolventCombination);
				}
			}

			CatholyteSolvent_Input.setItems(dataSolvent);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void populateAnolyteSaltCombobox(ActionEvent event) {
		try {
			AnolyteSalt_Input.getSelectionModel().clearSelection();

			ResultSet res = Database.selectData("activeMaterial");

			ObservableList<String> dataSalt = FXCollections.observableArrayList();

			String AnolyteSelection = AnolyteActiveMaterial_Input.getSelectionModel().getSelectedItem().toString();
			String AnolyteSolventSelection = AnolyteSolvent_Input.getSelectionModel().getSelectedItem().toString();

			while (res.next()) {
				if (res.getString("ABBREVIATION") == AnolyteSelection &&  res.getString("Solvent") == AnolyteSolventSelection) {
					String dataSolventCombination =  res.getString("Salt") +", "+ res.getString("Saltc").toString() +"M";
					dataSalt.add(dataSolventCombination);
				}
			}

			AnolyteSalt_Input.setItems(dataSalt);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}




	@FXML
	private void populateCatholyteSaltCombobox(ActionEvent event) {
		try {
			CatholyteSalt_Input.getSelectionModel().clearSelection();

			ResultSet res = Database.selectData("activeMaterial");

			ObservableList<String> dataSalt = FXCollections.observableArrayList();

			String CatholyteSelection = CatholyteActiveMaterial_Input.getSelectionModel().getSelectedItem().toString();
			String CatholyteSolventSelection = CatholyteSolvent_Input.getSelectionModel().getSelectedItem().toString();

			while (res.next()) {
				if (res.getString("ABBREVIATION") == CatholyteSelection &&  res.getString("Solvent") == CatholyteSolventSelection) {
					String dataSolventCombination =  res.getString("Salt") +", "+ res.getString("Saltc").toString() +"M";
					dataSalt.add(dataSolventCombination);
				}
			}

			CatholyteSalt_Input.setItems(dataSalt);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}


	@FXML
	private void getMaxSolAnolyte(ActionEvent event) throws ClassNotFoundException, SQLException {
		//get selection active material:
		anolyteActiveMaterialSelection = AnolyteActiveMaterial_Input.getSelectionModel().getSelectedItem().toString();

		// get selected Solvent + Salt + concentration:
		anolyteSolventSelection = AnolyteSolvent_Input.getSelectionModel().getSelectedItem().toString();

		String anolyteSaltCombinationSelection = AnolyteSalt_Input.getSelectionModel().getSelectedItem().toString();
		String[] anolyteSaltCombinationSelectionSplit = anolyteSaltCombinationSelection.split(", ");
		anolyteSaltSelection = anolyteSaltCombinationSelectionSplit[0];
		String anolyteSaltcSelectionSplit[] = anolyteSaltCombinationSelectionSplit[1].split("M");
		anolyteSaltcSelection = Double.valueOf(anolyteSaltcSelectionSplit[0]);

		ObjElectrolytes objElectrolytesAnolyte1 = new ObjElectrolytes();

		con3 = Database.getConnection("electrolyte");
		Statement stateElectrolytes = con3.createStatement();
		//anolyte:
		ResultSet resElectrolytesAnolyte = stateElectrolytes.executeQuery("SELECT * FROM electrolyte "
				+ "WHERE ActiveMaterial = '" + anolyteActiveMaterialSelection +
				"' AND Solvent = '" + anolyteSolventSelection
				+ "' AND Salt = '" + anolyteSaltSelection
				+ "' ORDER BY ABS(" + anolyteSaltcSelection + "- cSalt) LIMIT 1");

		while (resElectrolytesAnolyte.next()) {
			objElectrolytesAnolyte1.ID.set(resElectrolytesAnolyte.getInt("ID"));
			objElectrolytesAnolyte1.ActiveMaterial.set(resElectrolytesAnolyte.getString("ActiveMaterial"));
			objElectrolytesAnolyte1.Solvent.set(resElectrolytesAnolyte.getString("Solvent"));
			objElectrolytesAnolyte1.Salt.set(resElectrolytesAnolyte.getString("Salt"));
			objElectrolytesAnolyte1.cSalt.set(resElectrolytesAnolyte.getDouble("cSalt"));
			objElectrolytesAnolyte1.pH.set(resElectrolytesAnolyte.getDouble("pH"));
			objElectrolytesAnolyte1.maxSolubility.set(resElectrolytesAnolyte.getDouble("maxSolubility"));
			objElectrolytesAnolyte1.DOx.set(resElectrolytesAnolyte.getDouble("DOx"));
			objElectrolytesAnolyte1.DRed.set(resElectrolytesAnolyte.getDouble("DRed"));
			objElectrolytesAnolyte1.kOx.set(resElectrolytesAnolyte.getDouble("kOx"));
			objElectrolytesAnolyte1.AlphaOx.set(resElectrolytesAnolyte.getDouble("AlphaOx"));
			objElectrolytesAnolyte1.kRed.set(resElectrolytesAnolyte.getDouble("kRed"));
			objElectrolytesAnolyte1.AlphaRed.set(resElectrolytesAnolyte.getDouble("AlphaRed"));
			objElectrolytesAnolyte1.f.set(resElectrolytesAnolyte.getDouble("f"));
		}

		AnolyteC_Input.setText(String.valueOf(objElectrolytesAnolyte1.maxSolubility.doubleValue()));
	}


	@FXML
	private void getMaxSolCatholyte(ActionEvent event) throws ClassNotFoundException, SQLException {
		//get selection active material:
		catholyteActiveMaterialSelection = CatholyteActiveMaterial_Input.getSelectionModel().getSelectedItem().toString();

		// get selected Solvent + Salt + concentration:
		catholyteSolventSelection = CatholyteSolvent_Input.getSelectionModel().getSelectedItem().toString();


		String catholyteSaltCombinationSelection = CatholyteSalt_Input.getSelectionModel().getSelectedItem().toString();
		String[] catholyteSaltCombinationSelectionSplit = catholyteSaltCombinationSelection.split(", ");
		catholyteSaltSelection = catholyteSaltCombinationSelectionSplit[0];
		String catholyteSaltcSelectionSplit[] = catholyteSaltCombinationSelectionSplit[1].split("M");
		catholyteSaltcSelection = Double.valueOf(catholyteSaltcSelectionSplit[0]);

		ObjElectrolytes objElectrolytesCatholyte1 = new ObjElectrolytes();

		con3 = Database.getConnection("electrolyte");
		Statement stateElectrolytes = con3.createStatement();
		//catholyte:
		ResultSet resElectrolytesCatholyte = stateElectrolytes.executeQuery("SELECT * FROM electrolyte "
				+ "WHERE ActiveMaterial = '" + catholyteActiveMaterialSelection +
				"' AND Solvent = '" + catholyteSolventSelection
				+ "' AND Salt = '" + catholyteSaltSelection
				+ "' ORDER BY ABS(" + catholyteSaltcSelection + "- cSalt) LIMIT 1");
		while (resElectrolytesCatholyte.next()) {
			objElectrolytesCatholyte1.ID.set(resElectrolytesCatholyte.getInt("ID"));
			objElectrolytesCatholyte1.ActiveMaterial.set(resElectrolytesCatholyte.getString("ActiveMaterial"));
			objElectrolytesCatholyte1.Solvent.set(resElectrolytesCatholyte.getString("Solvent"));
			objElectrolytesCatholyte1.Salt.set(resElectrolytesCatholyte.getString("Salt"));
			objElectrolytesCatholyte1.cSalt.set(resElectrolytesCatholyte.getDouble("cSalt"));
			objElectrolytesCatholyte1.pH.set(resElectrolytesCatholyte.getDouble("pH"));
			objElectrolytesCatholyte1.maxSolubility.set(resElectrolytesCatholyte.getDouble("maxSolubility"));
			objElectrolytesCatholyte1.DOx.set(resElectrolytesCatholyte.getDouble("DOx"));
			objElectrolytesCatholyte1.DRed.set(resElectrolytesCatholyte.getDouble("DRed"));
			objElectrolytesCatholyte1.kOx.set(resElectrolytesCatholyte.getDouble("kOx"));
			objElectrolytesCatholyte1.AlphaOx.set(resElectrolytesCatholyte.getDouble("AlphaOx"));
			objElectrolytesCatholyte1.kRed.set(resElectrolytesCatholyte.getDouble("kRed"));
			objElectrolytesCatholyte1.AlphaRed.set(resElectrolytesCatholyte.getDouble("AlphaRed"));
			objElectrolytesCatholyte1.f.set(resElectrolytesCatholyte.getDouble("f"));
		}

		CatholyteC_Input.setText(String.valueOf(objElectrolytesCatholyte1.maxSolubility.doubleValue()));
	}




	private void getSelectedItems() {
		//get selection active material:
		anolyteActiveMaterialSelection = AnolyteActiveMaterial_Input.getSelectionModel().getSelectedItem().toString();
		catholyteActiveMaterialSelection = CatholyteActiveMaterial_Input.getSelectionModel().getSelectedItem().toString();

		// get selected Solvent + Salt + concentration:
		String anolyteSaltCombinationSelection = AnolyteSalt_Input.getSelectionModel().getSelectedItem().toString();
		String[] anolyteSaltCombinationSelectionSplit = anolyteSaltCombinationSelection.split(", ");
		anolyteSaltSelection = anolyteSaltCombinationSelectionSplit[0];
		String anolyteSaltcSelectionSplit[] = anolyteSaltCombinationSelectionSplit[1].split("M");
		anolyteSaltcSelection = Double.valueOf(anolyteSaltcSelectionSplit[0]);


		String catholyteSaltCombinationSelection = CatholyteSalt_Input.getSelectionModel().getSelectedItem().toString();
		String[] catholyteSaltCombinationSelectionSplit = catholyteSaltCombinationSelection.split(", ");
		catholyteSaltSelection = catholyteSaltCombinationSelectionSplit[0];
		String catholyteSaltcSelectionSplit[] = catholyteSaltCombinationSelectionSplit[1].split("M");
		catholyteSaltcSelection = Double.valueOf(catholyteSaltcSelectionSplit[0]);

		anolyteSolventSelection = AnolyteSolvent_Input.getSelectionModel().getSelectedItem().toString();
		catholyteSolventSelection = CatholyteSolvent_Input.getSelectionModel().getSelectedItem().toString();
	}





	private void solventTest() throws ClassNotFoundException, SQLException {
		//Test Solvent inorganic/organic:
		con = Database.getConnection("solvent");
		//anolyte:
		Statement stateAn = con.createStatement();
		ResultSet resAnolyte = stateAn.executeQuery("SELECT *"
				+ " FROM solventInorganic"
				+ " WHERE SolventStructuralFormula = '" + anolyteSaltSelection + "'");
		if (resAnolyte.next()) {
			inorganicSolventAnolyte = 1;
		} else {
			inorganicSolventAnolyte = 0;
		}

		Statement stateAn1 = con.createStatement();
		ResultSet resAnolyte1 = stateAn1.executeQuery("SELECT *"
				+ " FROM solventOrganic"
				+ " WHERE SolventName = '" + anolyteSolventSelection + "'");
		if (resAnolyte1.next()) {
			organicSolventAnolyte = 1;
		} else {
			organicSolventAnolyte = 0;
		}

		//catholyte:
		Statement stateCath = con.createStatement();
		ResultSet resCatholyte = stateCath.executeQuery("SELECT *"
				+ " FROM solventInorganic"
				+ " WHERE SolventStructuralFormula = '" + catholyteSaltSelection + "'");
		if (resCatholyte.next()) {
			inorganicSolventCatholyte = 1;
		} else {
			inorganicSolventCatholyte = 0;
		}

		Statement stateCath1 = con.createStatement();
		ResultSet resCatholyte1 = stateCath1.executeQuery("SELECT *"
				+ " FROM solventOrganic"
				+ " WHERE SolventName = '" + catholyteSolventSelection + "'");
		if (resCatholyte1.next()) {
			organicSolventCatholyte = 1;
		} else {
			organicSolventCatholyte = 0;
		}
	}



	private void createObjActiveMaterial() throws ClassNotFoundException, SQLException {
		//objActiveMaterial:

		con1 = Database.getConnection("activeMaterial");

		//anolyte:
		Statement stateAn2 = con1.createStatement();
		ResultSet resAnolyte2 = stateAn2.executeQuery("SELECT *"
				+ " FROM activeMaterial"
				+ " WHERE Abbreviation = '" + anolyteActiveMaterialSelection
				+ "' AND Solvent = '" + anolyteSolventSelection
				+ "' AND Salt = '" + anolyteSaltSelection
				+ "' AND Saltc = " + anolyteSaltcSelection);

		//SELECT * FROM activeMaterial WHERE Abbreviation = 'Test1' AND Solvent = 'H2SO4' AND Solventc = 1

		while (resAnolyte2.next()) {
			objActiveMaterialAnolyte.ID.set(resAnolyte2.getInt("ID"));
			objActiveMaterialAnolyte.ABBREVIATION.set(resAnolyte2.getString("ABBREVIATION"));
			objActiveMaterialAnolyte.NAME.set(resAnolyte2.getString("NAME"));
			objActiveMaterialAnolyte.STRUCTURALFORMULA.set(resAnolyte2.getString("STRUCTURALFORMULA"));
			objActiveMaterialAnolyte.M.set(resAnolyte2.getDouble("M"));
			objActiveMaterialAnolyte.N.set(resAnolyte2.getInt("N"));
			objActiveMaterialAnolyte.SOLVENT.set(resAnolyte2.getString("SOLVENT"));
			objActiveMaterialAnolyte.Salt.set(resAnolyte2.getString("Salt"));
			objActiveMaterialAnolyte.SaltC.set(resAnolyte2.getDouble("Saltc"));
			objActiveMaterialAnolyte.PH.set(resAnolyte2.getDouble("PH"));
			objActiveMaterialAnolyte.E.set(resAnolyte2.getDouble("E"));
		}

		//catholyte:
		Statement stateCath2 = con1.createStatement();
		ResultSet resCatholyte2 = stateCath2.executeQuery("SELECT *"
				+ " FROM activeMaterial"
				+ " WHERE Abbreviation = '" +catholyteActiveMaterialSelection
				+ "' AND Solvent = '" + catholyteSolventSelection
				+ "' AND Salt = '" + catholyteSaltSelection
				+ "' AND Saltc = " + catholyteSaltcSelection);
		while (resCatholyte2.next()) {
			objActiveMaterialCatholyte.ID.set(resCatholyte2.getInt("ID"));
			objActiveMaterialCatholyte.ABBREVIATION.set(resCatholyte2.getString("ABBREVIATION"));
			objActiveMaterialCatholyte.NAME.set(resCatholyte2.getString("NAME"));
			objActiveMaterialCatholyte.STRUCTURALFORMULA.set(resCatholyte2.getString("STRUCTURALFORMULA"));
			objActiveMaterialCatholyte.M.set(resCatholyte2.getDouble("M"));
			objActiveMaterialCatholyte.N.set(resCatholyte2.getInt("N"));
			objActiveMaterialCatholyte.SOLVENT.set(resCatholyte2.getString("SOLVENT"));
			objActiveMaterialCatholyte.Salt.set(resCatholyte2.getString("Salt"));
			objActiveMaterialCatholyte.SaltC.set(resCatholyte2.getDouble("Saltc"));
			objActiveMaterialCatholyte.PH.set(resCatholyte2.getDouble("PH"));
			objActiveMaterialCatholyte.E.set(resCatholyte2.getDouble("E"));
		}
	}



	private void createObjSolvent() throws ClassNotFoundException, SQLException {
		con2 = Database.getConnection("solvent");
		Statement stateSolvent = con2.createStatement();
		//anolyte:
		if (inorganicSolventAnolyte == 1) {
			ResultSet resSolventAnolyte = stateSolvent.executeQuery("SELECT * FROM solventInorganic "
					+ "WHERE SolventStructuralFormula = '" + anolyteSaltSelection +
					"' ORDER BY ABS(" + anolyteSaltcSelection + "- c) LIMIT 1");
			while (resSolventAnolyte.next()) {
				objSolventInorganicAnolyte.ID.set(resSolventAnolyte.getInt("ID"));
				objSolventInorganicAnolyte.SolventStructuralFormula.set(resSolventAnolyte.getString("SolventStructuralFormula"));
				objSolventInorganicAnolyte.c.set(resSolventAnolyte.getDouble("c"));
				objSolventInorganicAnolyte.density.set(resSolventAnolyte.getDouble("density"));
				objSolventInorganicAnolyte.dynViscosity.set(resSolventAnolyte.getDouble("dynViscosity"));
				objSolventInorganicAnolyte.kinViscosity.set(resSolventAnolyte.getDouble("kinViscosity"));
			}
		} else {
			objSolventInorganicAnolyte = null;
		}

		if (organicSolventAnolyte == 1) {
			ResultSet resSolventAnolyte = stateSolvent.executeQuery("SELECT * FROM solventOrganic "
					+ "WHERE SolventName = '" + anolyteSolventSelection +
					"' LIMIT 1");
			while (resSolventAnolyte.next()) {
				objSolventOrganicAnolyte.ID.set(resSolventAnolyte.getInt("ID"));
				objSolventOrganicAnolyte.SolventName.set(resSolventAnolyte.getString("SolventName"));
				objSolventOrganicAnolyte.SolventStructuralFormula.set(resSolventAnolyte.getString("SolventStructuralFormula"));
				objSolventOrganicAnolyte.density.set(resSolventAnolyte.getDouble("density"));
				objSolventOrganicAnolyte.dynViscosity.set(resSolventAnolyte.getDouble("dynViscosity"));
				objSolventOrganicAnolyte.kinViscosity.set(resSolventAnolyte.getDouble("kinViscosity"));
			}
		} else {
			objSolventOrganicAnolyte = null;
		}

		//catholyte:
		if (inorganicSolventCatholyte == 1) {
			ResultSet resSolventCatholyte = stateSolvent.executeQuery("SELECT * FROM solventInorganic "
					+ "WHERE SolventStructuralFormula = '" + catholyteSaltSelection +
					"' ORDER BY ABS(" + catholyteSaltcSelection + "- c) LIMIT 1");
			while (resSolventCatholyte.next()) {
				objSolventInorganicCatholyte.ID.set(resSolventCatholyte.getInt("ID"));
				objSolventInorganicCatholyte.SolventStructuralFormula.set(resSolventCatholyte.getString("SolventStructuralFormula"));
				objSolventInorganicCatholyte.c.set(resSolventCatholyte.getDouble("c"));
				objSolventInorganicCatholyte.density.set(resSolventCatholyte.getDouble("density"));
				objSolventInorganicCatholyte.dynViscosity.set(resSolventCatholyte.getDouble("dynViscosity"));
				objSolventInorganicCatholyte.kinViscosity.set(resSolventCatholyte.getDouble("kinViscosity"));
			}
		} else {
			objSolventInorganicCatholyte = null;

		}

		if (organicSolventCatholyte == 1) {
			ResultSet resSolventCatholyte = stateSolvent.executeQuery("SELECT * FROM solventOrganic "
					+ "WHERE SolventName = '" + catholyteSolventSelection +
					"' LIMIT 1");
			while (resSolventCatholyte.next()) {
				objSolventOrganicCatholyte.ID.set(resSolventCatholyte.getInt("ID"));
				objSolventOrganicCatholyte.SolventName.set(resSolventCatholyte.getString("SolventName"));
				objSolventOrganicCatholyte.SolventStructuralFormula.set(resSolventCatholyte.getString("SolventStructuralFormula"));
				objSolventOrganicCatholyte.density.set(resSolventCatholyte.getDouble("density"));
				objSolventOrganicCatholyte.dynViscosity.set(resSolventCatholyte.getDouble("dynViscosity"));
				objSolventOrganicCatholyte.kinViscosity.set(resSolventCatholyte.getDouble("kinViscosity"));
			}
		} else {
			objSolventOrganicCatholyte = null;
		}
	}


	private void createObjElectrolyte() throws ClassNotFoundException, SQLException {
		con3 = Database.getConnection("electrolyte");
		Statement stateElectrolytes = con3.createStatement();
		//anolyte:
		ResultSet resElectrolytesAnolyte = stateElectrolytes.executeQuery("SELECT * FROM electrolyte "
				+ "WHERE ActiveMaterial = '" + anolyteActiveMaterialSelection +
				"' AND Solvent = '" + anolyteSolventSelection
				+ "' AND Salt = '" + anolyteSaltSelection
				+ "' ORDER BY ABS(" + anolyteSaltcSelection + "- cSalt) LIMIT 1");
		while (resElectrolytesAnolyte.next()) {
			objElectrolytesAnolyte.ID.set(resElectrolytesAnolyte.getInt("ID"));
			objElectrolytesAnolyte.ActiveMaterial.set(resElectrolytesAnolyte.getString("ActiveMaterial"));
			objElectrolytesAnolyte.Solvent.set(resElectrolytesAnolyte.getString("Solvent"));
			objElectrolytesAnolyte.Salt.set(resElectrolytesAnolyte.getString("Salt"));
			objElectrolytesAnolyte.cSalt.set(resElectrolytesAnolyte.getDouble("cSalt"));
			objElectrolytesAnolyte.pH.set(resElectrolytesAnolyte.getDouble("pH"));
			objElectrolytesAnolyte.maxSolubility.set(resElectrolytesAnolyte.getDouble("maxSolubility"));
			objElectrolytesAnolyte.DOx.set(resElectrolytesAnolyte.getDouble("DOx"));
			objElectrolytesAnolyte.DRed.set(resElectrolytesAnolyte.getDouble("DRed"));
			objElectrolytesAnolyte.kOx.set(resElectrolytesAnolyte.getDouble("kOx"));
			objElectrolytesAnolyte.AlphaOx.set(resElectrolytesAnolyte.getDouble("AlphaOx"));
			objElectrolytesAnolyte.kRed.set(resElectrolytesAnolyte.getDouble("kRed"));
			objElectrolytesAnolyte.AlphaRed.set(resElectrolytesAnolyte.getDouble("AlphaRed"));
			objElectrolytesAnolyte.f.set(resElectrolytesAnolyte.getDouble("f"));
		}

		//catholyte:
		ResultSet resElectrolytesCatholyte = stateElectrolytes.executeQuery("SELECT * FROM electrolyte "
				+ "WHERE ActiveMaterial = '" + catholyteActiveMaterialSelection +
				"' AND Solvent = '" + catholyteSolventSelection
				+ "' AND Salt = '" + catholyteSaltSelection
				+ "' ORDER BY ABS(" + catholyteSaltcSelection + "- cSalt) LIMIT 1");
		while (resElectrolytesCatholyte.next()) {
			objElectrolytesCatholyte.ID.set(resElectrolytesCatholyte.getInt("ID"));
			objElectrolytesCatholyte.ActiveMaterial.set(resElectrolytesCatholyte.getString("ActiveMaterial"));
			objElectrolytesCatholyte.Solvent.set(resElectrolytesCatholyte.getString("Solvent"));
			objElectrolytesCatholyte.Salt.set(resElectrolytesCatholyte.getString("Salt"));
			objElectrolytesCatholyte.cSalt.set(resElectrolytesCatholyte.getDouble("cSalt"));
			objElectrolytesCatholyte.pH.set(resElectrolytesCatholyte.getDouble("pH"));
			objElectrolytesCatholyte.maxSolubility.set(resElectrolytesCatholyte.getDouble("maxSolubility"));
			objElectrolytesCatholyte.DOx.set(resElectrolytesCatholyte.getDouble("DOx"));
			objElectrolytesCatholyte.DRed.set(resElectrolytesCatholyte.getDouble("DRed"));
			objElectrolytesCatholyte.kOx.set(resElectrolytesCatholyte.getDouble("kOx"));
			objElectrolytesCatholyte.AlphaOx.set(resElectrolytesCatholyte.getDouble("AlphaOx"));
			objElectrolytesCatholyte.kRed.set(resElectrolytesCatholyte.getDouble("kRed"));
			objElectrolytesCatholyte.AlphaRed.set(resElectrolytesCatholyte.getDouble("AlphaRed"));
			objElectrolytesCatholyte.f.set(resElectrolytesCatholyte.getDouble("f"));
		}
	}

	private void drawNewChart(JFreeChart newChart, double xValue, double yValue) {
		ScatterPlotRagone Chart = new ScatterPlotRagone();
		newChart = Chart.ScatterPlotRagone("", xValue, yValue);
        centerChart.getChildren().removeAll();

        ChartCanvasJFreeChart chartCanvas = new ChartCanvasJFreeChart(newChart);

        chartCanvas.widthProperty().bind(centerChart.widthProperty());
        chartCanvas.heightProperty().bind(centerChart.heightProperty());

        centerChart.getChildren().add(chartCanvas);
	}


	@FXML
	private void Calculation(ActionEvent event) {

		try {
			RagoneAnalysisController.this.getSelectedItems();
			RagoneAnalysisController.this.solventTest();
			RagoneAnalysisController.this.createObjActiveMaterial();
			RagoneAnalysisController.this.createObjSolvent();
			RagoneAnalysisController.this.createObjElectrolyte();


		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		/////////////////////////////
		//anolyte:
		String anolyteActiveMaterial = anolyteActiveMaterialSelection;
		Integer anolyteN = objActiveMaterialAnolyte.N.intValue();
		double anolyteM = objActiveMaterialAnolyte.M.doubleValue();
		String anolyteSolvent = objActiveMaterialAnolyte.SOLVENT.getValue();
		String anolyteSalt = objActiveMaterialAnolyte.Salt.getValue();
		double anolyteSaltc = objActiveMaterialAnolyte.SaltC.doubleValue();
		double anolyteCmax = Double.valueOf(AnolyteC_Input.getText());
		double anolyteD = 0;

		if (objElectrolytesAnolyte.DRed.getValue() != 0) {
			anolyteD = objElectrolytesAnolyte.DRed.getValue();
		} else if (objElectrolytesAnolyte.DOx.getValue() != 0) {
			anolyteD = objElectrolytesAnolyte.DOx.getValue();
		} else {
			System.out.println("no diffusion coefficient defined on anolyte side!");
		}

		double anolyteDensity = 0;
		if (inorganicSolventAnolyte == 1) {
			anolyteDensity = objSolventInorganicAnolyte.density.doubleValue();
		}
		if (organicSolventAnolyte == 1) {
			anolyteDensity = objSolventOrganicAnolyte.density.doubleValue();
		}
		double anolyteE = objActiveMaterialAnolyte.E.doubleValue();
		double anolyteV = Double.valueOf(AnolyteVolume_Input.getText());

		//catholyte:
		String catholyteActiveMaterial = catholyteActiveMaterialSelection;
		Integer catholyteN = objActiveMaterialCatholyte.N.intValue();
		double catholyteM = objActiveMaterialCatholyte.M.doubleValue();
		String catholyteSolvent = objActiveMaterialCatholyte.SOLVENT.getValue();
		String catholyteSalt = objActiveMaterialCatholyte.Salt.getValue();
		double catholyteSaltc = objActiveMaterialCatholyte.SaltC.doubleValue();
		double catholyteCmax = Double.valueOf(CatholyteC_Input.getText());
		double catholyteD = 0;

		if (objElectrolytesCatholyte.DOx.getValue() != 0) {
			catholyteD = objElectrolytesAnolyte.DOx.getValue();
		} else if (objElectrolytesCatholyte.DRed.getValue() != 0) {
			catholyteD = objElectrolytesCatholyte.DRed.getValue();
		} else {
			System.out.println("no diffusion coefficient defined on catholyte side!");
		}

		double catholyteDensity = 0;
		if (inorganicSolventCatholyte == 1) {
			catholyteDensity = objSolventInorganicCatholyte.density.doubleValue();
		}
		if (organicSolventCatholyte == 1) {
			catholyteDensity = objSolventOrganicCatholyte.density.doubleValue();
		}
		double catholyteE = objActiveMaterialCatholyte.E.doubleValue();
		double catholyteV = Double.valueOf(CatholyteVolume_Input.getText());

		//Electrode area / cm^2:
		double electrodeArea = Double.valueOf(surfaceArea_Input.getText());

		//Nernst layer / cm:
		double dNernst = 4 * Math.pow(10, -3);

		//Calculation:

		//anolyte:
		//m / g:
		double mAnolyte = (anolyteDensity + anolyteM * anolyteCmax*Math.pow(10, -3)) * anolyteV;
		//Q / C:
		double QAnolyte = anolyteCmax * anolyteV*Math.pow(10, -3) * anolyteN * F;
		//ilimit / A cm^-2:
		double iLimitAnolyte = (anolyteN * F * anolyteD * anolyteCmax*Math.pow(10, -3))/dNernst;

		//catholyte:
		//m / g:
		double mCatholyte = (catholyteDensity + catholyteM * catholyteCmax*Math.pow(10, -3)) * catholyteV;
		//Q / C:
		double QCatholyte = catholyteCmax * catholyteV*Math.pow(10, -3) * catholyteN * F;
		//ilimit / A cm^-2:
		double iLimitCatholyte = (catholyteN * F * catholyteD * catholyteCmax*Math.pow(10, -3))/dNernst;


		/////////////////////////////
		//Battery:
		//Ecell / V:
		double ECell = catholyteE - anolyteE;
		//Qmax / C:
		double Qmax = Math.min(QAnolyte, QCatholyte);
		//mGes / g:
		double mGes = mAnolyte + mCatholyte;
		//VGes / mL:
		double VGes = anolyteV + catholyteV;
		//Qmax (grav.) / C g^-1:
		double QmaxGrav = Qmax/mGes;
		//Qmax (vol.) / C L^-1:
		double QmaxVol = Qmax/(VGes*Math.pow(10, -3));
		//Pmax / W cm^-2:
		double Pmax = Math.min(iLimitAnolyte, iLimitCatholyte)*ECell;
		//EnergyCapacity / J:
		double energyCapacity = ECell * Qmax;

		//specPmax / W g^-1:
		double specPmax = Pmax / mGes * electrodeArea;
		//specEnergyCapacity / J g^-1:
		double specEnergyCapacity = energyCapacity / mGes;
		//specVolPmax / W L^-1:
		double specPmaxVol = Pmax /(VGes*Math.pow(10, -3)) * electrodeArea;
		//specEnergyCapacityVol / J L^-1:
		double specEnergyCapacityVol = energyCapacity / (VGes*Math.pow(10, -3));

		cellVoltage_Output.setText(String.valueOf(ECell));
		currentCapacity_Output.setText(String.valueOf(Qmax));
		power_Output.setText(String.valueOf(Pmax));
		energyCapacity_Output.setText(String.valueOf(energyCapacity));


		cellVoltageDet_Output.setText(String.valueOf(ECell));
		currentCapacityDet_Output.setText(String.valueOf(Qmax));
		powerDet_Output.setText(String.valueOf(Pmax));
		energyCapacityDet_Output.setText(String.valueOf(energyCapacity));


		volumeElectrolyte_Output.setText(String.valueOf(VGes));
		massElectrolyte_Output.setText(String.valueOf(mGes));
		currentGrav_Output.setText(String.valueOf(QmaxGrav));
		currentVol_Output.setText(String.valueOf(QmaxVol));



		/////////////////////////////
		//capacity limiting half cell:
		double mLimit = 0;
		double VLimit = 0;
		if (QAnolyte < QCatholyte) {
			mLimit = mAnolyte;
			VLimit = anolyteV;
		} else {
			mLimit = mAnolyte;
			VLimit = anolyteV;
		}
		//Qmax (grav.) Limit / C g^-1:
		double QmaxGravLimit = Qmax/mLimit;
		//Qmax (vol.) Limit / C L^-1:
		double QmaxVolLimit = Qmax/(VLimit*Math.pow(10, -3));

		//specPmax / W g^-1:
		double specPmaxLimit = Pmax / mLimit * electrodeArea;
		//specEnergyCapacity / J g^-1:
		double specEnergyCapacityLimit = energyCapacity / mLimit;
		//specVolPmax / W L^-1:
		double specPmaxVolLimit = Pmax /(VLimit*Math.pow(10, -3)) * electrodeArea;
		//specEnergyCapacityVol / J L^-1:
		double specEnergyCapacityVolLimit = energyCapacity / (VLimit*Math.pow(10, -3));


		volumeElectrolyteLimitSide_Output.setText(String.valueOf(VLimit));
		massElectrolyteLimitSide_Output.setText(String.valueOf(mLimit));
		currentGravLimitSide_Output.setText(String.valueOf(QmaxGravLimit));
		currentVolLimitSide_Output.setText(String.valueOf(QmaxVolLimit));


		/////////////////////////////
		//Ragone plot values:
		//specPmaxRag / W kg^-1:
		double specPmaxRag = specPmax * Math.pow(10, 3);
		//specEnergyCapacityRag / Wh kg^-1:
		double specEnergyCapacityRag = specEnergyCapacity/3600* Math.pow(10, 3);
		//specPmaxRagLimit / W kg^-1:
		double specPmaxRagLimit = specPmaxLimit * Math.pow(10, 3);
		//specEnergyCapacityRagLimit / Wh kg^-1:
		double specEnergyCapacityRagLimit = specEnergyCapacityLimit/3600* Math.pow(10, 3);

		//Vol:
		//specPmaxVolRag / W L^-1:
		double specPmaxVolRag = specPmaxVol;
		//specEnergyCapacityVolRag / Wh L^-1:
		double specEnergyCapacityVolRag = specEnergyCapacityVol/3600;
		//specPmaxVolRagLimit / W L^-1:
		double specPmaxVolRagLimit = specPmaxVolLimit;
		//specEnergyCapacityVolRagLimit / Wh L^-1:
		double specEnergyCapacityVolRagLimit = specEnergyCapacityVolLimit/3600;


		specPower_Output.setText(String.valueOf(specPmaxRag));
		specEnergyCapacity_Output.setText(String.valueOf(specEnergyCapacityRag));
		specPowerVol_Output.setText(String.valueOf(specPmaxVolRag));
		specEnergyCapacityVol_Output.setText(String.valueOf(specEnergyCapacityVolRag));


		specPowerDet_Output.setText(String.valueOf(specPmaxRag));
		specEnergyCapacityDet_Output.setText(String.valueOf(specEnergyCapacityRag));
		specPowerVolDet_Output.setText(String.valueOf(specPmaxVolRag));
		specEnergyCapacityVolDet_Output.setText(String.valueOf(specEnergyCapacityVolRag));


		specPowerDetLimitSide_Output.setText(String.valueOf(specPmaxRagLimit));
		specEnergyCapacityDetLimitSide_Output.setText(String.valueOf(specEnergyCapacityRagLimit));
		specPowerVolDetLimitSide_Output.setText(String.valueOf(specPmaxVolRagLimit));
		specEnergyCapacityVolDetLimitSide_Output.setText(String.valueOf(specEnergyCapacityVolRagLimit));

//		//Create data for Chart:
//		XYChart.Series<Double, Double> chartData = new XYChart.Series<>();
//		//Add data:
//		chartData.getData().add(new XYChart.Data<Double, Double>(specEnergyCapacityRag,specPmaxRag));
//		//Add XYChart to ScatterChart:
//		ragonePlot.getData().add(chartData);

		xValue = specEnergyCapacityRag;
		yValue = specPmaxRag;

		RagoneAnalysisController.this.drawNewChart(newChart, xValue, yValue);
	}




	private void addNames() {
		//CInput
		Text CInput1 = new Text("c");
		CInput1.setStyle("-fx-font-style: italic");
		Text CInput2 = new Text(" / mol L");
		Text CInput3 = new Text("-1");
		CInput3.setTranslateY(CInput1.getFont().getSize() * -0.3);
		CInput3.setFont(Font.font(CInput1.getFont().getStyle(),CInput1.getFont().getSize()*0.75));
		Text CInput4 = new Text(":");
		CInput_TextFlow.getChildren().addAll(CInput1, CInput2, CInput3, CInput4);

		//electrodeArea
		Text electrodeArea1 = new Text("Electrode surface area / cm");
		Text electrodeArea2 = new Text("2");
		electrodeArea2.setTranslateY(electrodeArea1.getFont().getSize() * -0.3);
		electrodeArea2.setFont(Font.font(electrodeArea1.getFont().getStyle(),electrodeArea1.getFont().getSize()*0.75));
		Text electrodeArea3 = new Text(":");
		electrodeArea_TextFlow.getChildren().addAll(electrodeArea1, electrodeArea2, electrodeArea3);

		//cellVoltage:
		Text E1 = new Text("E�");
		E1.setStyle("-fx-font-style: italic");
		Text E2 = new Text("cell");
		E2.setTranslateY(E1.getFont().getSize() * 0.3);
		E2.setFont(Font.font(E1.getFont().getStyle(),E1.getFont().getSize()*0.75));
		Text E3 = new Text(" vs NHE / V");
		E3.setStyle("-fx-font-weight: bold");
		cellVoltage_TextFlow.getChildren().addAll(E1, E2, E3);

		//currentCapacity:
		Text currentCapacity1 = new Text("Q");
		currentCapacity1.setStyle("-fx-font-style: italic");
		Text currentCapacity2 = new Text("max.");
		currentCapacity2.setTranslateY(currentCapacity1.getFont().getSize() * 0.3);
		currentCapacity2.setFont(Font.font(currentCapacity1.getFont().getStyle(),currentCapacity1.getFont().getSize()*0.75));
		Text currentCapacity3 = new Text(" / C");
		currentCapacity_TextFlow.getChildren().addAll(currentCapacity1, currentCapacity2, currentCapacity3);

		//power:
		Text power1 = new Text("P");
		power1.setStyle("-fx-font-style: italic");
		Text power2 = new Text("max.");
		power2.setTranslateY(power1.getFont().getSize() * 0.3);
		power2.setFont(Font.font(power1.getFont().getStyle(),power1.getFont().getSize()*0.75));
		Text power3 = new Text(" / W cm");
		Text power4 = new Text("-2");
		power4.setTranslateY(power1.getFont().getSize() * -0.3);
		power4.setFont(Font.font(power1.getFont().getStyle(),power1.getFont().getSize()*0.75));
		power_TextFlow.getChildren().addAll(power1, power2, power3, power4);


		//energyCapacity:
		Text energyCapacity1 = new Text("Q");
		energyCapacity1.setStyle("-fx-font-style: italic");
		Text energyCapacity2 = new Text("e");
		energyCapacity2.setTranslateY(energyCapacity1.getFont().getSize() * 0.3);
		energyCapacity2.setFont(Font.font(energyCapacity1.getFont().getStyle(),energyCapacity1.getFont().getSize()*0.75));
		Text energyCapacity3 = new Text(" / J");
		energyCapacity_TextFlow.getChildren().addAll(energyCapacity1, energyCapacity2, energyCapacity3);

		//specPower:
		Text specPower1 = new Text("spec. ");
		Text specPower2 = new Text("P");
		specPower2.setStyle("-fx-font-style: italic");
		Text specPower3 = new Text("max.");
		specPower3.setTranslateY(specPower1.getFont().getSize() * 0.3);
		specPower3.setFont(Font.font(specPower1.getFont().getStyle(),specPower1.getFont().getSize()*0.75));
		Text specPower4 = new Text(" / W kg");
		Text specPower5 = new Text("-1");
		specPower5.setTranslateY(specPower1.getFont().getSize() * -0.3);
		specPower5.setFont(Font.font(specPower1.getFont().getStyle(),specPower1.getFont().getSize()*0.75));
		specPower_TextFlow.getChildren().addAll(specPower1, specPower2, specPower3, specPower4, specPower5);

		//specEnergyCapacity:
		Text specEnergyCapacity1 = new Text("spec. ");
		Text specEnergyCapacity2 = new Text("Q");
		specEnergyCapacity2.setStyle("-fx-font-style: italic");
		Text specEnergyCapacity3 = new Text("e");
		specEnergyCapacity3.setTranslateY(specEnergyCapacity1.getFont().getSize() * 0.3);
		specEnergyCapacity3.setFont(Font.font(specEnergyCapacity1.getFont().getStyle(),specEnergyCapacity1.getFont().getSize()*0.75));
		Text specEnergyCapacity4 = new Text(" / Wh kg");
		Text specEnergyCapacity5 = new Text("-1");
		specEnergyCapacity5.setTranslateY(specEnergyCapacity1.getFont().getSize() * -0.3);
		specEnergyCapacity5.setFont(Font.font(specEnergyCapacity1.getFont().getStyle(),specEnergyCapacity1.getFont().getSize()*0.75));
		specEnergyCapacity_TextFlow.getChildren().addAll(specEnergyCapacity1, specEnergyCapacity2, specEnergyCapacity3, specEnergyCapacity4, specEnergyCapacity5);

		//specPowerVol:
		Text specPowerVol1 = new Text("spec. ");
		Text specPowerVol2 = new Text("P");
		specPowerVol2.setStyle("-fx-font-style: italic");
		Text specPowerVol3 = new Text("max.");
		specPowerVol3.setTranslateY(specPowerVol1.getFont().getSize() * 0.3);
		specPowerVol3.setFont(Font.font(specPowerVol1.getFont().getStyle(),specPowerVol1.getFont().getSize()*0.75));
		Text specPowerVol4 = new Text(" / W L");
		Text specPowerVol5 = new Text("-1");
		specPowerVol5.setTranslateY(specPowerVol1.getFont().getSize() * -0.3);
		specPowerVol5.setFont(Font.font(specPowerVol1.getFont().getStyle(),specPowerVol1.getFont().getSize()*0.75));
		specPowerVol_TextFlow.getChildren().addAll(specPowerVol1, specPowerVol2, specPowerVol3, specPowerVol4, specPowerVol5);

		//specEnergyCapacityVol_Input:
		Text specEnergyCapacityVol1 = new Text("spec. ");
		Text specEnergyCapacityVol2 = new Text("Q");
		specEnergyCapacityVol2.setStyle("-fx-font-style: italic");
		Text specEnergyCapacityVol3 = new Text("e");
		specEnergyCapacityVol3.setTranslateY(specEnergyCapacityVol1.getFont().getSize() * 0.3);
		specEnergyCapacityVol3.setFont(Font.font(specEnergyCapacityVol1.getFont().getStyle(),specEnergyCapacityVol1.getFont().getSize()*0.75));
		Text specEnergyCapacityVol4 = new Text(" / Wh L");
		Text specEnergyCapacityVol5 = new Text("-1");
		specEnergyCapacityVol5.setTranslateY(specEnergyCapacityVol1.getFont().getSize() * -0.3);
		specEnergyCapacityVol5.setFont(Font.font(specEnergyCapacityVol1.getFont().getStyle(),specEnergyCapacityVol1.getFont().getSize()*0.75));
		specEnergyCapacityVol_TextFlow.getChildren().addAll(specEnergyCapacityVol1, specEnergyCapacityVol2, specEnergyCapacityVol3, specEnergyCapacityVol4, specEnergyCapacityVol5);

		//cellVoltageDet:
		Text cellVoltageDet1 = new Text("E�");
		cellVoltageDet1.setStyle("-fx-font-style: italic");
		Text cellVoltageDet2 = new Text("cell");
		cellVoltageDet2.setTranslateY(cellVoltageDet1.getFont().getSize() * 0.3);
		cellVoltageDet2.setFont(Font.font(cellVoltageDet1.getFont().getStyle(),cellVoltageDet1.getFont().getSize()*0.75));
		Text cellVoltageDet3 = new Text(" vs NHE / V");
		cellVoltageDet3.setStyle("-fx-font-weight: bold");
		cellVoltageDet_TextFlow.getChildren().addAll(cellVoltageDet1, cellVoltageDet2, cellVoltageDet3);

		//currentCapacityDet:
		Text currentCapacityDet1 = new Text("Q");
		currentCapacity1.setStyle("-fx-font-style: italic");
		Text currentCapacityDet2 = new Text("max.");
		currentCapacityDet2.setTranslateY(currentCapacityDet1.getFont().getSize() * 0.3);
		currentCapacityDet2.setFont(Font.font(currentCapacityDet1.getFont().getStyle(),currentCapacityDet1.getFont().getSize()*0.75));
		Text currentCapacityDet3 = new Text(" / C");
		currentCapacityDet_TextFlow.getChildren().addAll(currentCapacityDet1, currentCapacityDet2, currentCapacityDet3);

		//powerDet:
		Text powerDet1 = new Text("P");
		powerDet1.setStyle("-fx-font-style: italic");
		Text powerDet2 = new Text("max.");
		powerDet2.setTranslateY(powerDet1.getFont().getSize() * 0.3);
		powerDet2.setFont(Font.font(powerDet1.getFont().getStyle(),powerDet1.getFont().getSize()*0.75));
		Text powerDet3 = new Text(" / W cm");
		Text powerDet4 = new Text("-2");
		power4.setTranslateY(powerDet1.getFont().getSize() * -0.3);
		power4.setFont(Font.font(powerDet1.getFont().getStyle(),powerDet1.getFont().getSize()*0.75));
		powerDet_TextFlow.getChildren().addAll(powerDet1, powerDet2, powerDet3, powerDet4);

		//energyCapacityDet:
		Text energyCapacityDet1 = new Text("Q");
		energyCapacityDet1.setStyle("-fx-font-style: italic");
		Text energyCapacityDet2 = new Text("e");
		energyCapacityDet2.setTranslateY(energyCapacityDet1.getFont().getSize() * 0.3);
		energyCapacityDet2.setFont(Font.font(energyCapacityDet1.getFont().getStyle(),energyCapacityDet1.getFont().getSize()*0.75));
		Text energyCapacityDet3 = new Text(" / J");
		energyCapacityDet_TextFlow.getChildren().addAll(energyCapacityDet1, energyCapacityDet2, energyCapacityDet3);

		//volumeElectrolyte:
		Text volumeElectrolyte1 = new Text("V");
		volumeElectrolyte1.setStyle("-fx-font-style: italic");
		Text volumeElectrolyte2 = new Text("electrolyte");
		volumeElectrolyte2.setTranslateY(volumeElectrolyte1.getFont().getSize() * 0.3);
		volumeElectrolyte2.setFont(Font.font(volumeElectrolyte1.getFont().getStyle(),volumeElectrolyte1.getFont().getSize()*0.75));
		Text volumeElectrolyte3 = new Text(" / mL");
		volumeElectrolyte_TextFlow.getChildren().addAll(volumeElectrolyte1, volumeElectrolyte2, volumeElectrolyte3);

		//massElectrolyte:
		Text massElectrolyte1 = new Text("m");
		massElectrolyte1.setStyle("-fx-font-style: italic");
		Text massElectrolyte2 = new Text("electrolyte");
		massElectrolyte2.setTranslateY(massElectrolyte1.getFont().getSize() * 0.3);
		massElectrolyte2.setFont(Font.font(massElectrolyte1.getFont().getStyle(),massElectrolyte1.getFont().getSize()*0.75));
		Text massElectrolyte3 = new Text(" / g");
		massElectrolyte_TextFlow.getChildren().addAll(massElectrolyte1, massElectrolyte2, massElectrolyte3);

		//currentGrav:
		Text currentGrav1 = new Text("Q");
		currentGrav1.setStyle("-fx-font-style: italic");
		Text currentGrav2 = new Text("max., grav.");
		currentGrav2.setTranslateY(currentGrav1.getFont().getSize() * 0.3);
		currentGrav2.setFont(Font.font(currentGrav1.getFont().getStyle(),currentGrav1.getFont().getSize()*0.75));
		Text currentGrav3 = new Text(" / C g");
		Text currentGrav4 = new Text("-1");
		currentGrav4.setTranslateY(currentGrav1.getFont().getSize() * -0.3);
		currentGrav4.setFont(Font.font(currentGrav1.getFont().getStyle(),currentGrav1.getFont().getSize()*0.75));
		currentGrav_TextFlow.getChildren().addAll(currentGrav1, currentGrav2, currentGrav3, currentGrav4);

		//currentVol:
		Text currentVol1 = new Text("Q");
		currentVol1.setStyle("-fx-font-style: italic");
		Text currentVol2 = new Text("max., vol.");
		currentVol2.setTranslateY(currentVol1.getFont().getSize() * 0.3);
		currentGrav2.setFont(Font.font(currentVol1.getFont().getStyle(),currentVol1.getFont().getSize()*0.75));
		Text currentVol3 = new Text(" / C L");
		Text currentVol4 = new Text("-1");
		currentVol4.setTranslateY(currentVol1.getFont().getSize() * -0.3);
		currentVol4.setFont(Font.font(currentVol1.getFont().getStyle(),currentVol1.getFont().getSize()*0.75));
		currentVol_TextFlow.getChildren().addAll(currentVol1, currentVol2, currentVol3, currentVol4);

		//specPowerDet:
		Text specPowerDet1 = new Text("spec. ");
		Text specPowerDet2 = new Text("P");
		specPowerDet2.setStyle("-fx-font-style: italic");
		Text specPowerDet3 = new Text("max.");
		specPowerDet3.setTranslateY(specPowerDet1.getFont().getSize() * 0.3);
		specPowerDet3.setFont(Font.font(specPowerDet1.getFont().getStyle(),specPowerDet1.getFont().getSize()*0.75));
		Text specPowerDet4 = new Text(" / W kg");
		Text specPowerDet5 = new Text("-1");
		specPowerDet5.setTranslateY(specPowerDet1.getFont().getSize() * -0.3);
		specPowerDet5.setFont(Font.font(specPowerDet1.getFont().getStyle(),specPowerDet1.getFont().getSize()*0.75));
		specPowerDet_TextFlow.getChildren().addAll(specPowerDet1, specPowerDet2, specPowerDet3, specPowerDet4, specPowerDet5);

		//specEnergyCapacityDet:
		Text specEnergyCapacityDet1 = new Text("spec. ");
		Text specEnergyCapacityDet2 = new Text("Q");
		specEnergyCapacityDet2.setStyle("-fx-font-style: italic");
		Text specEnergyCapacityDet3 = new Text("e");
		specEnergyCapacityDet3.setTranslateY(specEnergyCapacityDet1.getFont().getSize() * 0.3);
		specEnergyCapacityDet3.setFont(Font.font(specEnergyCapacityDet1.getFont().getStyle(),specEnergyCapacityDet1.getFont().getSize()*0.75));
		Text specEnergyCapacityDet4 = new Text(" / Wh kg");
		Text specEnergyCapacityDet5 = new Text("-1");
		specEnergyCapacityDet5.setTranslateY(specEnergyCapacityDet1.getFont().getSize() * -0.3);
		specEnergyCapacityDet5.setFont(Font.font(specEnergyCapacityDet1.getFont().getStyle(),specEnergyCapacityDet1.getFont().getSize()*0.75));
		specEnergyCapacityDet_TextFlow.getChildren().addAll(specEnergyCapacityDet1, specEnergyCapacityDet2, specEnergyCapacityDet3, specEnergyCapacityDet4, specEnergyCapacityDet5);

		//specPowerVolDet:
		Text specPowerVolDet1 = new Text("spec. ");
		Text specPowerVolDet2 = new Text("P");
		specPowerVolDet2.setStyle("-fx-font-style: italic");
		Text specPowerVolDet3 = new Text("max.");
		specPowerVolDet3.setTranslateY(specPowerVolDet1.getFont().getSize() * 0.3);
		specPowerVolDet3.setFont(Font.font(specPowerVolDet1.getFont().getStyle(),specPowerVolDet1.getFont().getSize()*0.75));
		Text specPowerVolDet4 = new Text(" / W L");
		Text specPowerVolDet5 = new Text("-1");
		specPowerVolDet5.setTranslateY(specPowerVolDet1.getFont().getSize() * -0.3);
		specPowerVolDet5.setFont(Font.font(specPowerVolDet1.getFont().getStyle(),specPowerVolDet1.getFont().getSize()*0.75));
		specPowerVolDet_TextFlow.getChildren().addAll(specPowerVolDet1, specPowerVolDet2, specPowerVolDet3, specPowerVolDet4, specPowerVolDet5);

		//specEnergyCapacityVolDet:
		Text specEnergyCapacityVolDet1 = new Text("spec. ");
		Text specEnergyCapacityVolDet2 = new Text("Q");
		specEnergyCapacityVolDet2.setStyle("-fx-font-style: italic");
		Text specEnergyCapacityVolDet3 = new Text("e");
		specEnergyCapacityVolDet3.setTranslateY(specEnergyCapacityVolDet1.getFont().getSize() * 0.3);
		specEnergyCapacityVolDet3.setFont(Font.font(specEnergyCapacityVolDet1.getFont().getStyle(),specEnergyCapacityVolDet1.getFont().getSize()*0.75));
		Text specEnergyCapacityVolDet4 = new Text(" / Wh L");
		Text specEnergyCapacityVolDet5 = new Text("-1");
		specEnergyCapacityVolDet5.setTranslateY(specEnergyCapacityVolDet1.getFont().getSize() * -0.3);
		specEnergyCapacityVolDet5.setFont(Font.font(specEnergyCapacityVolDet1.getFont().getStyle(),specEnergyCapacityVolDet1.getFont().getSize()*0.75));
		specEnergyCapacityVolDet_TextFlow.getChildren().addAll(specEnergyCapacityVolDet1, specEnergyCapacityVolDet2, specEnergyCapacityVolDet3, specEnergyCapacityVolDet4, specEnergyCapacityVolDet5);
	}
}
