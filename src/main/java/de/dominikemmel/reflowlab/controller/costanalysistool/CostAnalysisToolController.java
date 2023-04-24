package de.dominikemmel.reflowlab.controller.costanalysistool;

import static de.dominikemmel.reflowlab.MyConstants.*;

import java.awt.BasicStroke;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JFileChooser;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.Marker;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.ui.*;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import de.dominikemmel.reflowlab.Database;
import de.dominikemmel.reflowlab.FxmlLoader;
import de.dominikemmel.reflowlab.controller.activematerials.ObjActiveMaterial;
import de.dominikemmel.reflowlab.controller.costs.ObjCostsSalt;
import de.dominikemmel.reflowlab.controller.costs.ObjCostsSolvent;
import de.dominikemmel.reflowlab.controller.costs.ObjCostsStack;
import de.dominikemmel.reflowlab.controller.electrolytes.EditElectrolytesController;
import de.dominikemmel.reflowlab.controller.electrolytes.ElectrolytesController;
import de.dominikemmel.reflowlab.controller.electrolytes.ObjElectrolytes;
import de.dominikemmel.reflowlab.controller.solvent.ObjInorganicSolvent;
import de.dominikemmel.reflowlab.controller.solvent.ObjOrganicSolvent;

public class CostAnalysisToolController implements javafx.fxml.Initializable {
	@FXML
	private AnchorPane AnchorPane1;
	@FXML
	private CheckBox checkStandard;
	@FXML
	private CheckBox checkFullCell;
	@FXML
	private Button btnCalculate;
	@FXML
	private Button btnStackSelection;
	@FXML
	private Label labelLeft;
	@FXML
	private Label labelRight;
	@FXML
	private ComboBox comboLeftActiveMaterial;
	@FXML
	private ComboBox comboLeftSolvent;
	@FXML
	private ComboBox comboLeftSalt;
	@FXML
	private ComboBox comboRightActiveMaterial;
	@FXML
	private ComboBox comboRightSolvent;
	@FXML
	private ComboBox comboRightSalt;
	@FXML
	private ComboBox comboStack;

	@FXML
	private ComboBox comboExportSelection;
	@FXML
	private Button btnExport;

	@FXML
	private TextField peakPowerInput;
	@FXML
	private TextField voltageEfficiencyInput;
	@FXML
	private TextField currentDensityInput;
	@FXML
	private TextField specPowerInput;
	@FXML
	private CheckBox checkPeakPower;
	@FXML
	private CheckBox checkVoltageEfficiency;
	@FXML
	private CheckBox checkCurrentDensity;
	@FXML
	private CheckBox checkSpecPower;
	@FXML
	private CheckBox checkOhmicMode;
	@FXML
	private CheckBox checkChargeTransferMode;
	@FXML
	private CheckBox checkTransportationLimitationMode;
	
	
	@FXML
	private Text timeDischarge1;
	@FXML
	private Text timeDischarge2;
	@FXML
	private Text timeDischarge3;

	@FXML
	private Text powerDischarge1;
	@FXML
	private Text powerDischarge2;
	@FXML
	private Text powerDischarge3;

	@FXML
	private Text energyCapacity1;
	@FXML
	private Text energyCapacity2;
	@FXML
	private Text energyCapacity3;


	@FXML
	private Text anolyteActMatCost1;
	@FXML
	private Text anolyteActMatCost2;
	@FXML
	private Text anolyteActMatCost3;
	@FXML
	private Text anolyteActMatCost4;

	@FXML
	private Text anolyteActMatCoeff1;
	@FXML
	private Text anolyteActMatCoeff2;

	@FXML
	private Text anolyteActMatMolMass1;
	@FXML
	private Text anolyteActMatMolMass2;
	@FXML
	private Text anolyteActMatMolMass3;
	@FXML
	private Text anolyteActMatMolMass4;

	@FXML
	private Text anolyteSocRange1;
	@FXML
	private Text anolyteSocRange2;

	@FXML
	private Text anolyteActMatNumberElectron1;
	@FXML
	private Text anolyteActMatNumberElectron2;

	@FXML
	private Text anolyteActMatNumberProton1;
	@FXML
	private Text anolyteActMatNumberProton2;

	@FXML
	private Text anolyteActMatSol1;
	@FXML
	private Text anolyteActMatSol2;
	@FXML
	private Text anolyteActMatSol3;
	@FXML
	private Text anolyteActMatSol4;

	@FXML
	private Text anolyteSaltMolMass1;
	@FXML
	private Text anolyteSaltMolMass2;
	@FXML
	private Text anolyteSaltMolMass3;
	@FXML
	private Text anolyteSaltMolMass4;

	@FXML
	private Text anolyteSaltSol1;
	@FXML
	private Text anolyteSaltSol2;
	@FXML
	private Text anolyteSaltSol3;
	@FXML
	private Text anolyteSaltSol4;

	@FXML
	private Text anolytePotential1;
	@FXML
	private Text anolytePotential2;
	@FXML
	private Text anolytePotential3;


	@FXML
	private Text catholyteActMatCost1;
	@FXML
	private Text catholyteActMatCost2;
	@FXML
	private Text catholyteActMatCost3;
	@FXML
	private Text catholyteActMatCost4;

	@FXML
	private Text catholyteActMatCoeff1;
	@FXML
	private Text catholyteActMatCoeff2;

	@FXML
	private Text catholyteActMatMolMass1;
	@FXML
	private Text catholyteActMatMolMass2;
	@FXML
	private Text catholyteActMatMolMass3;
	@FXML
	private Text catholyteActMatMolMass4;

	@FXML
	private Text catholyteSocRange1;
	@FXML
	private Text catholyteSocRange2;

	@FXML
	private Text catholyteActMatNumberElectron1;
	@FXML
	private Text catholyteActMatNumberElectron2;

	@FXML
	private Text catholyteActMatNumberProton1;
	@FXML
	private Text catholyteActMatNumberProton2;

	@FXML
	private Text catholyteActMatSol1;
	@FXML
	private Text catholyteActMatSol2;
	@FXML
	private Text catholyteActMatSol3;
	@FXML
	private Text catholyteActMatSol4;

	@FXML
	private Text catholyteSaltMolMass1;
	@FXML
	private Text catholyteSaltMolMass2;
	@FXML
	private Text catholyteSaltMolMass3;
	@FXML
	private Text catholyteSaltMolMass4;

	@FXML
	private Text catholyteSaltSol1;
	@FXML
	private Text catholyteSaltSol2;
	@FXML
	private Text catholyteSaltSol3;
	@FXML
	private Text catholyteSaltSol4;

	@FXML
	private Text catholytePotential1;
	@FXML
	private Text catholytePotential2;
	@FXML
	private Text catholytePotential3;


	@FXML
	private Text standardCellVoltageEl1;
	@FXML
	private Text standardCellVoltageEl2;
	@FXML
	private Text standardCellVoltageEl3;

	@FXML
	private Text ravg1;
	@FXML
	private Text ravg2;
	@FXML
	private Text ravg3;
	@FXML
	private Text ravg4;

	@FXML
	private Text bavg1;
	@FXML
	private Text bavg2;
	@FXML
	private Text bavg3;
	@FXML
	private Text bavg4;

	@FXML
	private Text efficiencySysDischarge1;
	@FXML
	private Text efficiencySysDischarge2;

	@FXML
	private Text efficiencyVoltDischarge1;
	@FXML
	private Text efficiencyVoltDischarge2;

	@FXML
	private Text efficiencyCoulombicRoundTrip1;
	@FXML
	private Text efficiencyCoulombicRoundTrip2;


	@FXML
	private Text saltCost1;
	@FXML
	private Text saltCost2;
	@FXML
	private Text saltCost3;
	@FXML
	private Text saltCost4;

	@FXML
	private Text solventCost1;
	@FXML
	private Text solventCost2;
	@FXML
	private Text solventCost3;
	@FXML
	private Text solventCost4;

	@FXML
	private Text solventDensity1;
	@FXML
	private Text solventDensity2;
	@FXML
	private Text solventDensity3;

	@FXML
	private Text temp1;
	@FXML
	private Text temp2;

	@FXML
	private Text pH1;

	@FXML
	private Text anolyteTankCost1;
	@FXML
	private Text anolyteTankCost2;
	@FXML
	private Text anolyteTankCost3;
	@FXML
	private Text anolyteTankCost4;

	@FXML
	private Text catholyteTankCost1;
	@FXML
	private Text catholyteTankCost2;
	@FXML
	private Text catholyteTankCost3;
	@FXML
	private Text catholyteTankCost4;

	@FXML
	private Text tankFillLevel1;
	@FXML
	private Text tankFillLevel2;
	@FXML
	private Text tankFillLevel3;

	@FXML
	private Text tankCost1;
	@FXML
	private Text tankCost2;
	@FXML
	private Text tankCost3;
	@FXML
	private Text tankCost4;




	@FXML
	private Text stackCost1;
	@FXML
	private Text stackCost2;
	@FXML
	private Text stackCost3;
	@FXML
	private Text stackCost4;

	@FXML
	private Text ASR1;
	@FXML
	private Text ASR2;
	@FXML
	private Text ASR3;

	@FXML
	private Text socDischarge1;
	
	@FXML
	private Text costBOP1;
	@FXML
	private Text costBOP2;
	@FXML
	private Text costBOP3;
	@FXML
	private Text costBOP4;

	@FXML
	private Text costAdd1;
	@FXML
	private Text costAdd2;
	@FXML
	private Text costAdd3;
	@FXML
	private Text costAdd4;

	@FXML
	private Text currentDensityDischarge1;
	@FXML
	private Text currentDensityDischarge2;
	@FXML
	private Text currentDensityDischarge3;
	@FXML
	private Text currentDensityDischarge4;

	@FXML
	private Text voltageDischarge1;
	@FXML
	private Text voltageDischarge2;
	@FXML
	private Text voltageDischarge3;

	@FXML
	private Text standardCellVoltagePow1;
	@FXML
	private Text standardCellVoltagePow2;
	@FXML
	private Text standardCellVoltagePow3;

	@FXML
	private Text reversibleVoltage1;
	@FXML
	private Text reversibleVoltage2;
	@FXML
	private Text reversibleVoltage3;

	@FXML
	private Text chargeTranserOverPotDischarge1;
	@FXML
	private Text chargeTranserOverPotDischarge2;
	@FXML
	private Text chargeTranserOverPotDischarge3;

	@FXML
	private Text ohmicOverPotDischarge1;
	@FXML
	private Text ohmicOverPotDischarge2;
	@FXML
	private Text ohmicOverPotDischarge3;
	
	@FXML
	private Text concentrationOverPotDischarge1;
	@FXML
	private Text concentrationOverPotDischarge2;
	@FXML
	private Text concentrationOverPotDischarge3;

	@FXML
	private Text anolyteReactionRate1;
	@FXML
	private Text anolyteReactionRate2;
	@FXML
	private Text anolyteReactionRate3;
	@FXML
	private Text anolyteReactionRate4;

	@FXML
	private Text anolyteTransferCoeff1;
	@FXML
	private Text anolyteTransferCoeff2;

	@FXML
	private Text catholyteReactionRate1;
	@FXML
	private Text catholyteReactionRate2;
	@FXML
	private Text catholyteReactionRate3;
	@FXML
	private Text catholyteReactionRate4;

	@FXML
	private Text catholyteTransferCoeff1;
	@FXML
	private Text catholyteTransferCoeff2;

	@FXML
	private Text anolyteExchangeCurrentDensity1;
	@FXML
	private Text anolyteExchangeCurrentDensity2;
	@FXML
	private Text anolyteExchangeCurrentDensity3;
	@FXML
	private Text anolyteExchangeCurrentDensity4;

	@FXML
	private Text catholyteExchangeCurrentDensity1;
	@FXML
	private Text catholyteExchangeCurrentDensity2;
	@FXML
	private Text catholyteExchangeCurrentDensity3;
	@FXML
	private Text catholyteExchangeCurrentDensity4;

	@FXML
	private Text anolyteChargeTranserOverPotDischarge1;
	@FXML
	private Text anolyteChargeTranserOverPotDischarge2;
	@FXML
	private Text anolyteChargeTranserOverPotDischarge3;

	@FXML
	private Text catholyteChargeTranserOverPotDischarge1;
	@FXML
	private Text catholyteChargeTranserOverPotDischarge2;
	@FXML
	private Text catholyteChargeTranserOverPotDischarge3;
	
	@FXML
	private Text diaFiber1;
	@FXML
	private Text diaFiber2;
	@FXML
	private Text diaFiber3;
	
	@FXML
	private Text flowVelocity1;
	@FXML
	private Text flowVelocity2;
	@FXML
	private Text flowVelocity3;
	
	@FXML
	private Text anolyteDiffusionCoeff1;
	@FXML
	private Text anolyteDiffusionCoeff2;
	@FXML
	private Text anolyteDiffusionCoeff3;
	@FXML
	private Text anolyteDiffusionCoeff4;
	@FXML
	private Text anolyteDiffusionCoeff5;
	@FXML
	private Text anolyteDiffusionCoeff6;
	
	@FXML
	private Text catholyteDiffusionCoeff1;
	@FXML
	private Text catholyteDiffusionCoeff2;
	@FXML
	private Text catholyteDiffusionCoeff3;
	@FXML
	private Text catholyteDiffusionCoeff4;
	@FXML
	private Text catholyteDiffusionCoeff5;
	@FXML
	private Text catholyteDiffusionCoeff6;

	@FXML
	private Text anolyteConcentrationOverPotDischarge1;
	@FXML
	private Text anolyteConcentrationOverPotDischarge2;
	@FXML
	private Text anolyteConcentrationOverPotDischarge3;

	@FXML
	private Text catholyteConcentrationOverPotDischarge1;
	@FXML
	private Text catholyteConcentrationOverPotDischarge2;
	@FXML
	private Text catholyteConcentrationOverPotDischarge3;

	@FXML
	private Text cellArea1;
	@FXML
	private Text cellArea2;
	@FXML
	private Text cellArea3;
	@FXML
	private Text cellArea4;

	@FXML
	private Text totalArea1;
	@FXML
	private Text totalArea2;
	@FXML
	private Text totalArea3;
	@FXML
	private Text totalArea4;

	@FXML
	private Text numberCellsPerStack1;
	@FXML
	private Text numberCellsPerStack2;

	@FXML
	private Text numberCells1;
	@FXML
	private Text numberCells2;

	@FXML
	private Text numberStacks1;
	@FXML
	private Text numberStacks2;

	@FXML
	private Text reactorCost1;
	@FXML
	private Text reactorCost2;
	@FXML
	private Text reactorCost3;



	@FXML
	private Text operationalLifetime1;
	@FXML
	private Text operationalLifetime2;

	@FXML
	private Text interestRate1;

	@FXML
	private Text anolyteReplacementFraction1;
	@FXML
	private Text anolyteReplacementFraction2;
	@FXML
	private Text anolyteReplacementFraction3;
	@FXML
	private Text anolyteReplacementFraction4;

	@FXML
	private Text catholyteReplacementFraction1;
	@FXML
	private Text catholyteReplacementFraction2;
	@FXML
	private Text catholyteReplacementFraction3;
	@FXML
	private Text catholyteReplacementFraction4;

	@FXML
	private Text maxReplacementFraction1;
	@FXML
	private Text maxReplacementFraction2;
	@FXML
	private Text maxReplacementFraction3;
	@FXML
	private Text maxReplacementFraction4;

	@FXML
	private Text electrolyteDegradationNPVCost1;
	@FXML
	private Text electrolyteDegradationNPVCost2;
	@FXML
	private Text electrolyteDegradationNPVCost3;
	@FXML
	private Text electrolyteDegradationNPVCost4;




	@FXML
	private Text anolyteReplacementCostMass1;
	@FXML
	private Text anolyteReplacementCostMass2;
	@FXML
	private Text anolyteReplacementCostMass3;
	@FXML
	private Text anolyteReplacementCostMass4;

	@FXML
	private Text anolyteReplacementCost1;
	@FXML
	private Text anolyteReplacementCost2;
	@FXML
	private Text anolyteReplacementCost3;
	@FXML
	private Text anolyteReplacementCost4;

	@FXML
	private Text catholyteReplacementCostMass1;
	@FXML
	private Text catholyteReplacementCostMass2;
	@FXML
	private Text catholyteReplacementCostMass3;
	@FXML
	private Text catholyteReplacementCostMass4;

	@FXML
	private Text catholyteReplacementCost1;
	@FXML
	private Text catholyteReplacementCost2;
	@FXML
	private Text catholyteReplacementCost3;
	@FXML
	private Text catholyteReplacementCost4;

	@FXML
	private Text electrolyteOperationalTime1;
	@FXML
	private Text electrolyteOperationalTime2;
	@FXML
	private Text electrolyteOperationalTime3;

	@FXML
	private Text threshold1;




	@FXML
	private Text replacementNPVCost1;
	@FXML
	private Text replacementNPVCost2;
	@FXML
	private Text replacementNPVCost3;
	@FXML
	private Text replacementNPVCost4;





	@FXML
	private Text stackExchangeCost1;
	@FXML
	private Text stackExchangeCost2;
	@FXML
	private Text stackExchangeCost3;
	@FXML
	private Text stackExchangeCost4;

	@FXML
	private Text stackOperationalTime1;
	@FXML
	private Text stackOperationalTime2;
	@FXML
	private Text stackOperationalTime3;

	@FXML
	private Text stackExchangeNPVCost1;
	@FXML
	private Text stackExchangeNPVCost2;
	@FXML
	private Text stackExchangeNPVCost3;
	@FXML
	private Text stackExchangeNPVCost4;




	@FXML
	private Text anolyteCostTotal1;
	@FXML
	private Text anolyteCostTotal2;
	@FXML
	private Text anolyteCostTotal3;
	@FXML
	private Text anolyteCostTotal4;

	@FXML
	private Text anolyteCostTotalKAh1;
	@FXML
	private Text anolyteCostTotalKAh2;
	@FXML
	private Text anolyteCostTotalKAh3;
	@FXML
	private Text anolyteCostTotalKAh4;

	@FXML
	private Text catholyteCostTotal1;
	@FXML
	private Text catholyteCostTotal2;
	@FXML
	private Text catholyteCostTotal3;
	@FXML
	private Text catholyteCostTotal4;

	@FXML
	private Text catholyteCostTotalKAh1;
	@FXML
	private Text catholyteCostTotalKAh2;
	@FXML
	private Text catholyteCostTotalKAh3;
	@FXML
	private Text catholyteCostTotalKAh4;

	@FXML
	private Text energyDensity1;
	@FXML
	private Text energyDensity2;
	@FXML
	private Text energyDensity3;

	@FXML
	private Text costElectrolyte1;
	@FXML
	private Text costElectrolyte2;
	@FXML
	private Text costElectrolyte3;
	@FXML
	private Text costElectrolyte4;

	@FXML
	private Text costElectrolyteKWh1;
	@FXML
	private Text costElectrolyteKWh2;
	@FXML
	private Text costElectrolyteKWh3;
	@FXML
	private Text costElectrolyteKWh4;

	@FXML
	private Text costPower1;
	@FXML
	private Text costPower2;
	@FXML
	private Text costPower3;
	@FXML
	private Text costPower4;

	@FXML
	private Text costMaintenance1;
	@FXML
	private Text costMaintenance2;
	@FXML
	private Text costMaintenance3;
	@FXML
	private Text costMaintenance4;

	@FXML
	private Text costCapital1;
	@FXML
	private Text costCapital2;
	@FXML
	private Text costCapital3;
	@FXML
	private Text costCapital4;
	
	
	@FXML
	private Text costElectrolyteKWh11;
	@FXML
	private Text costElectrolyteKWh21;
	@FXML
	private Text costElectrolyteKWh31;
	@FXML
	private Text costElectrolyteKWh41;
	
	@FXML
	private Text costPower11;
	@FXML
	private Text costPower21;
	@FXML
	private Text costPower31;
	@FXML
	private Text costPower41;
	
	@FXML
	private Text costMaintenance11;
	@FXML
	private Text costMaintenance21;
	@FXML
	private Text costMaintenance31;
	@FXML
	private Text costMaintenance41;
	
	@FXML
	private Text costCapital11;
	@FXML
	private Text costCapital21;
	@FXML
	private Text costCapital31;
	@FXML
	private Text costCapital41;

	@FXML
	private Text voltageEfficiencyInputText1;
	@FXML
	private Text voltageEfficiencyInputText2;
	@FXML
	private Text voltageEfficiencyInputText3;
	@FXML
	private Text voltageEfficiencyInputText4;
	@FXML
	private Text voltageEfficiencyInputText5;

	@FXML
	private Text currentDensityInputText1;
	@FXML
	private Text currentDensityInputText2;
	@FXML
	private Text currentDensityInputText3;
	@FXML
	private Text currentDensityInputText4;
	@FXML
	private Text currentDensityInputText5;

	@FXML
	private Text specPowerInputText1;
	@FXML
	private Text specPowerInputText2;
	@FXML
	private Text specPowerInputText3;
	@FXML
	private Text specPowerInputText4;
	@FXML
	private Text specPowerInputText5;

	@FXML
	private Text checkOhmicModeText1;
	@FXML
	private Text checkOhmicModeText2;

	@FXML
	private Text checkChargeTransferModeText1;
	@FXML
	private Text checkChargeTransferModeText2;

	@FXML
	private Text checkTransportationLimitationModeText1;
	@FXML
	private Text checkTransportationLimitationModeText2;

	
	@FXML
	private TextField txtfbTimeDischarge;
	@FXML
	private TextField txtfbPowerDischarge;
	@FXML
	private TextField txtfbEnergyCapacity;

	//electrolyte:
	@FXML
	private TextField txtfdAnolyteActMatCost;
	@FXML
	private TextField txtfdAnolyteActMatCoeff;
	@FXML
	private TextField txtfdAnolyteActMatMolMass;
	@FXML
	private TextField txtfdAnolyteSocRange;
	@FXML
	private TextField txtfdAnolyteActMatNumberElectron;
	@FXML
	private TextField txtfdAnolyteActMatNumberProton;
	@FXML
	private TextField txtfdAnolyteActMatSol;
	@FXML
	private TextField txtfdAnolyteSaltMolMass;
	@FXML
	private TextField txtfdAnolyteSaltSol;
	@FXML
	private TextField txtfdAnolytePotential;

	@FXML
	private TextField txtfdCatholyteActMatCost;
	@FXML
	private TextField txtfdCatholyteActMatCoeff;
	@FXML
	private TextField txtfdCatholyteActMatMolMass;
	@FXML
	private TextField txtfdCatholyteSocRange;
	@FXML
	private TextField txtfdCatholyteActMatNumberElectron;
	@FXML
	private TextField txtfdCatholyteActMatNumberProton;
	@FXML
	private TextField txtfdCatholyteActMatSol;
	@FXML
	private TextField txtfdCatholyteSaltMolMass;
	@FXML
	private TextField txtfdCatholyteSaltSol;
	@FXML
	private TextField txtfdCatholytePotential;

	@FXML
	private TextField txtfdReversibleCellVoltageEl;
	@FXML
	private TextField txtfdRavg;
	@FXML
	private TextField txtfdBavg;
	@FXML
	private TextField txtfdEfficiencySysDischarge;
	@FXML
	private TextField txtfdEfficiencyVoltDischarge;
	@FXML
	private TextField txtfdEfficiencyCoulombicRoundTrip;

	@FXML
	private TextField txtfdSaltCost;
	@FXML
	private TextField txtfdSolventCost;
	@FXML
	private TextField txtfdSolventDensity;
	@FXML
	private TextField txtfdTemp;
	@FXML
	private TextField txtfdPH;

	@FXML
	private TextField txtfdAnolyteTankCost;
	@FXML
	private TextField txtfdCatholyteTankCost;
	@FXML
	private TextField txtfdTankFillLevel;
	@FXML
	private TextField txtfdTankCost;


	//power:
	@FXML
	private TextField txtfdStackCost;
	@FXML
	private TextField txtfbASR;
	@FXML
	private TextField txtfbSocDischarge;
	@FXML
	private TextField txtfbCostBOP;
	@FXML
	private TextField txtfbCostAdd;
	@FXML
	private TextField txtfbCurrentDensityDischarge;
	@FXML
	private TextField txtfbVoltageDischarge;
	
	@FXML
	private TextField txtfdStandardCellVoltagePow;
	@FXML
	private TextField txtfbReversibleVoltage;
	@FXML
	private TextField txtfbChargeTranserOverPotDischarge;
	@FXML
	private TextField txtfbOhmicOverPotDischarge;
	@FXML
	private TextField txtfbConcentrationOverPotDischarge;

	@FXML
	private TextField txtfbAnolyteReactionRate;
	@FXML
	private TextField txtfbAnolyteTransferCoeff;
	@FXML
	private TextField txtfbCatholyteReactionRate;
	@FXML
	private TextField txtfbCatholyteTransferCoeff;
	@FXML
	private TextField txtfbAnolyteExchangeCurrentDensity;
	@FXML
	private TextField txtfbCatholyteExchangeCurrentDensity;
	@FXML
	private TextField txtfbAnolyteChargeTranserOverPotDischarge;
	@FXML
	private TextField txtfbCatholyteChargeTranserOverPotDischarge;
	
	@FXML
	private TextField txtfbDiaFiber;
	@FXML
	private TextField txtfbFlowVelocity;
	@FXML
	private TextField txtfbAnolyteDiffusionCoeff;
	@FXML
	private TextField txtfbCatholyteDiffusionCoeff;
	@FXML
	private TextField txtfbAnolyteConcentrationOverPotDischarge;
	@FXML
	private TextField txtfbCatholyteConcentrationOverPotDischarge;

	@FXML
	private TextField txtfdCellArea;
	@FXML
	private TextField txtfdTotalArea;
	@FXML
	private TextField txtfdNumberCellsPerStack;
	@FXML
	private TextField txtfdNumberCells;
	@FXML
	private TextField txtfdNumberStacks;
	@FXML
	private TextField txtfdReactorCost;


	//maintenance:
	@FXML
	private TextField txtfdOperationalLifetime;
	@FXML
	private TextField txtfdInterestRate;
	@FXML
	private TextField txtfdAnolyteReplacementFraction;
	@FXML
	private TextField txtfdCatholyteReplacementFraction;
	@FXML
	private TextField txtfdMaxReplacementFraction;
	@FXML
	private TextField txtfdElectrolyteDegradationNPVCost;

	@FXML
	private TextField txtfdAnolyteReplacementCostMass;
	@FXML
	private TextField txtfdAnolyteReplacementCost;
	@FXML
	private TextField txtfdCatholyteReplacementCostMass;
	@FXML
	private TextField txtfdCatholyteReplacementCost;
	@FXML
	private TextField txtfdElectrolyteOperationalTime;
	@FXML
	private TextField txtfdThreshold;
	@FXML
	private TextField txtfdReplacementNPVCost;

	@FXML
	private TextField txtfdStackExchangeCost;
	@FXML
	private TextField txtfdStackOperationalTime;
	@FXML
	private TextField txtfdStackExchangeNPVCost;


	//results:
	@FXML
	private TextField txtfdAnolyteCostTotal;
	@FXML
	private TextField txtfdAnolyteCostTotalKAh;

	@FXML
	private TextField txtfdCatholyteCostTotal;
	@FXML
	private TextField txtfdCatholyteCostTotalKAh;

	@FXML
	private TextField txtfdEnergyDensity;

	@FXML
	private TextField txtfdCostElectrolyte;
	@FXML
	private TextField txtfdCostElectrolyteKWh;
	@FXML
	private TextField txtfdCostPower;
	@FXML
	private TextField txtfdCostMaintenance;
	
	@FXML
	private TextField txtfdCostCapital;
	
	@FXML
	private TextField txtfdCostElectrolyteKWh1;
	@FXML
	private TextField txtfdCostPower1;
	@FXML
	private TextField txtfdCostMaintenance1;

	@FXML
	private TextField txtfdCostCapital1;
	
	String titleSW = "";


	//plot:
	@FXML
	private Pane paneSpiderWeb;
	JFreeChart newSpiderWebChart;
	ArrayList<ObjSpiderWebData> DataList = new ArrayList<>();

	@FXML
	private Pane paneUdvsSOC;
	JFreeChart newScatterChart;

	@FXML
	private Pane paneUdvsId;
	JFreeChart newScatterChart2;

	@FXML
	private Pane panePdvsId;
	JFreeChart newScatterChart3;



	//connections for later SQL query:
	Connection con = null;
	Connection con1 = null;
	Connection con2 = null;
	Connection con21 = null;
	Connection con3 = null;
	Connection con4 = null;


//  define variables:
	//control variables:
	Boolean standardSelected = true;
	Boolean leftAqueous = false;
	Boolean rightAqueous = false;
	Boolean leftOrganicSolvent = false;
	Boolean rightOrganicSolvent = false;
	Boolean anolyteKred = true;
	Boolean catholyteKox = true;
	Boolean catholyteStandard = false;
	Boolean anolyteStandard = false;
	Boolean calculationCombo = false;
	Boolean custom = false;
	Boolean peakPowerWorkingPoint = true;
	Boolean voltageEfficiencyWorkingPoint = false;
	Boolean currentDensityWorkingPoint = false;
	Boolean specPowerWorkingPoint = false;
	Boolean ohmicMode = true;
	Boolean chargeTransferMode = true;
	Boolean transportationLimitationMode = true;
	Boolean materialSelected = false;

	//variables for ComboBox selection:
	String inputLeftActiveMaterial;
	String inputLeftSolvent;
	String leftSaltCombinationSelection;
	String inputLeftSalt;
	Double inputLeftSaltConc;

	String inputRightActiveMaterial;
	String inputRightSolvent;
	String rightSaltCombinationSelection;
	String inputRightSalt;
	Double inputRightSaltConc;

	String inputStackSystem;
	
	//variables for refilling ComboBox selection after unchecking working point modes without changing anything else:
	String basisInputLeftActiveMaterial;
	String basisInputLeftSolvent;
	String basisLeftSaltCombinationSelection;
	String basisInputLeftSalt;
	Double basisInputLeftSaltConc;

	String basisInputRightActiveMaterial;
	String basisInputRightSolvent;
	String basisRightSaltCombinationSelection;
	String basisInputRightSalt;
	Double basisInputRightSaltConc;

	String basisInputStackSystem;
	String basisInputStackElectrode;
	String basisInputStackMembrane;



	//Selection:
	String anolyteAbbreviation;
	double anolyteC;
	String anolyteSolvent;
	String anolyteSalt;
	double anolyteSaltC;

	String catholyteAbbreviation;
	double catholyteC;
	String catholyteSolvent;
	String catholyteSalt;
	double catholyteSaltC;

	//create objects to cache data from sql query:
	ObjActiveMaterial objActiveMaterialLeft = new ObjActiveMaterial();
	ObjActiveMaterial objActiveMaterialRight = new ObjActiveMaterial();
	ObjInorganicSolvent objSolventInorganicLeft = new ObjInorganicSolvent();
	ObjInorganicSolvent objSolventInorganicRight = new ObjInorganicSolvent();
	ObjOrganicSolvent objSolventOrganicLeft = new ObjOrganicSolvent();
	ObjOrganicSolvent objSolventOrganicRight = new ObjOrganicSolvent();
	ObjElectrolytes objElectrolytesLeft = new ObjElectrolytes();
	ObjElectrolytes objElectrolytesRight = new ObjElectrolytes();
	ObjCostsStack objCostsStack = new ObjCostsStack();
	ObjCostsSalt objCostsSaltLeft = new ObjCostsSalt();
	ObjCostsSalt objCostsSaltRight = new ObjCostsSalt();
	ObjCostsSolvent objCostsSolventLeft = new ObjCostsSolvent();
	ObjCostsSolvent objCostsSolventRight = new ObjCostsSolvent();

	// variables for calculation + results and display in txtfd (text fields)
	//electrolyte:
	double anolyteActMatCost = 0;
	double anolyteActMatCoeff = 0;
	double anolyteActMatMolMass = 0;
	double anolyteSocRange = 0;
	Integer anolyteActMatNumberElectron = 0;
	Integer anolyteActMatNumberProton = 0;
	double anolyteActMatConc = 0;
	double anolyteActMatSol = 0;
	double anolyteSaltMolMass = 0;
	double anolyteSaltConc = 0;
	double anolyteSaltSol = 0;
	double anolyteStandardPotential = 0;
	double anolyteSaltCost = 0;
	double anolyteSolventCost = 0;
	double anolyteSolventDensity = 0;

	double catholyteActMatCost = 0;
	double catholyteActMatCoeff = 0;
	double catholyteActMatMolMass = 0;
	double catholyteSocRange = 0;
	Integer catholyteActMatNumberElectron = 0;
	Integer catholyteActMatNumberProton = 0;
	double catholyteActMatConc = 0;
	double catholyteActMatSol = 0;
	double catholyteSaltMolMass = 0;
	double catholyteSaltConc = 0;
	double catholyteSaltSol = 0;
	double catholyteStandardPotential = 0;
	double catholyteSaltCost = 0;
	double catholyteSolventCost = 0;
	double catholyteSolventDensity = 0;
	
	double SA_SocRange = 0;

	double pHActMatAnolyte = 0;
	double pHActMatCatholyte = 0;
	double pHElectrolyte = 0;
	double protonConcActMatAnolyte = 0;
	double protonConcActMatCatholyte = 0;
	double protonConcElectrolyte = 0;
	double temp = rT;

	double reversibleCellVoltageEl = 0;
	double catholyteReversiblePotential = 0;
	double anolyteReversiblePotential = 0;
//	double catholyteReversiblePotentialPh0 = 0;
//	double anolyteReversiblePotentialPh0 = 0;
	double diffActMatElmitanodeCatholyte = 0;
	double diffActMatElmitanodeAnolyte = 0;
	double standardElimitanode = 0;
	double ravg = 0;
	double bavg = 0;
	double peakPowerRatio = 1;
	double efficiencySysDischarge = 0;
	double efficiencyVoltDischarge = 0.916;
	double efficiencyCoulombicRoundTrip = 0;

	double saltCost = 0;
	double solventCost = 0;
	double solventDensity = 0;

	double anolyteTankCost = 0;
	double catholyteTankCost = 0;
	double tankFillLevel = 0;
	double tankCost = 0;

	//power:
	double stackCost = 0;

	double separatorCost = 0;
	double electrodeCost = 0;
	double bipolarPlateCost = 0;
//	double cellFramesCost = 0;
//	double sealsCost = 0;
	double cellFramesSealsCost = 0;
	double currentCollectorCost = 0;
	double stackFrameCost = 0;
	double assemblyCost = 0;

	double separatorStackNumber = 0;
	double electrodeStackNumber = 0;
	double bipolarPlateStackNumber = 0;
//	double cellFramesStackNumber = 0;
//	double sealsStackNumber = 0;
	double cellFramesSealsStackNumber = 0;
	double currentCollectorStackNumber = 0;
	double stackFrameStackNumber = 0;
	double componentsNumber = 0;

	double separatorStackCost = 0;
	double electrodeStackCost = 0;
	double bipolarPlateStackCost = 0;
//	double cellFramesStackCost = 0;
//	double sealsStackCost = 0;
	double cellFramesSealsStackCost = 0;
	double currentCollectorStackCost = 0;
	double stackFrameStackCost = 0;
	double assemblyStackCost = 0;
	double stackCostTotal = 0;

	double asr = 0;
	double timeDischarge = 0;
	double powerDischarge = 0;
	double specificPowerDischargeInput = 0.1;
	double specificPowerDischarge = 0;
	double energyCapacity = 0;
	double stateOfCharge = 0.5;

	double stEcell = 0;
	double z = 0;
	double currentDensityDischarge = 0;
	double voltageDischarge = 0;
	double standardCellVoltagePow = 0;
	double reversibleVoltage = 0;
	double chargeTranserOverPotDischarge = 0;
	double ohmicOverPotDischarge = 0;

	double anolyteReactionRate = 0;
	double anolyteTransferCoeff = 0;
	double catholyteReactionRate = 0;
	double catholyteTransferCoeff = 0;
	double anolyteExchangeCurrentDensity = 0;
	double catholyteExchangeCurrentDensity = 0;
	double anolyteChargeTranserOverPotDischarge = 0;
	double catholyteChargeTranserOverPotDischarge = 0;

	double dFiber = 0;
	double flowVelocity = 0;
//	double DOx = 0;
//	double DRed = 0;
	double anolyteSolventViscosity = 0;
	double catholyteSolventViscosity = 0;

	double anolyteDiffusionCoeff = 0;
	double catholyteDiffusionCoeff = 0;
	
	double catholyteConcentrationOverPotDischarge = 0;
	double anolyteConcentrationOverPotDischarge = 0;
	double concentrationOverPotDischarge = 0;

	double cellArea = 0;
	double totalArea = 0;
	double numberCells = 0;
	double numberCellsPerStack = 0;
	double numberStacks = 0;
	double reactorCost = 0;

	double costBOP = 0;
	double costAdd = 0;

	//maintenance:
	double operationalLifetime = 0;
	double interestRate = 0;
	double anolyteReplacementFraction = 0;
	double catholyteReplacementFraction = 0;
	double maxReplacementFraction = 0;
	double electrolyteDegradationNPVCost = 0;

	double anolyteReplacementCostMass = 0;
	double anolyteReplacementCost = 0;
	double catholyteReplacementCostMass = 0;
	double catholyteReplacementCost = 0;
	double electrolyteOperationalTime = 0;
	double threshold = 0.8;
	double replacementNPVCost = 0;

	double stackExchangeCost = 0;
	double stackOperationalTime = 0;
	double stackExchangeNPVCost = 0;

	//results:
	double anolyteCostTotal = 0;
	double anolyteCostTotalKAh = 0;

	double catholyteCostTotal = 0;
	double catholyteCostTotalKAh = 0;

	double energyDensity = 0;
	double anolyteEnergyDensityKg = 0;
	double catholyteEnergyDensityKg = 0;
	double energyDensityKg = 0;

	double costElectrolyte = 0;
	double costElectrolyteKWh = 0;
	double costPower = 0;
	double costMaintenance = 0;

	double costCapital = 0;

	ObjCostAnalysisInput defaultInput = new ObjCostAnalysisInput();
	ObjCostAnalysisInput standardInput = new ObjCostAnalysisInput();
	
	ObjStackSelection objStackSelection = new ObjStackSelection();

//	Plot values output:
	ArrayList<ObjScatterUdvsId> ArrayList_UdvsId = new ArrayList();
	ArrayList<ObjScatterUdvsSOC> ArrayList_UdvsSOC = new ArrayList();
	ArrayList<ObjScatterPdvsId> ArrayList_PdvsId = new ArrayList();


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		comboLeftActiveMaterial.setValue("");
		comboRightActiveMaterial.setValue("");
		comboStack.setValue("");
		comboLeftSolvent.setValue("");
		comboLeftSalt.setValue("");
		comboRightSolvent.setValue("");
		comboRightSalt.setValue("");
		
		checkStandard.setSelected(true);
		checkFullCell.setSelected(false);
		
		defaultInput = DefaultInput.defaultInput();
		standardInput = DefaultInput.standardInput();
		
		CostAnalysisToolController.this.uncheckFullCell(null);
		CostAnalysisToolController.this.uncheckPeakPower();
		CostAnalysisToolController.this.checkVoltageEfficiency();
		CostAnalysisToolController.this.uncheckCurrentDensity();
		CostAnalysisToolController.this.uncheckSpecPower();
		CostAnalysisToolController.this.checkOhmicMode();
		CostAnalysisToolController.this.checkChargeTransferMode();
		CostAnalysisToolController.this.checkTransportationLimitationMode();
		CostAnalysisToolController.this.addLabels();
		CostAnalysisToolController.this.populateComboLeftActiveMaterial();
		CostAnalysisToolController.this.populateComboRightActiveMaterial();
		CostAnalysisToolController.this.populateComboStack();
		CostAnalysisToolController.this.reloadValues();
		CostAnalysisToolController.this.populateComboExportSelection();
		CostAnalysisToolController.this.checkBoxListener();
		
		
		//default values: 
		txtfbTimeDischarge.setText("4.0");
		txtfbPowerDischarge.setText("1.0");
		txtfbEnergyCapacity.setText("4.0");

//		Matlab_test.Matlab_test();


//        SpiderPlot Chart = new SpiderPlot();
//		newChart = Chart.spiderPlot(DataList);
////		SpiderWebPane.getChildren().removeAll();
//
//        ChartCanvas chartCanvas = new ChartCanvas(newChart);
//
//        chartCanvas.widthProperty().bind(SpiderWebPane.widthProperty());
//        chartCanvas.heightProperty().bind(SpiderWebPane.heightProperty());
//
//        SpiderWebPane.getChildren().add(chartCanvas);

	}


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	//Labels:

	String colorTxtInput = "#f8f8ff";

	public void addLabels() {
		//anolyteActMatCost:
		anolyteActMatCost1.setText("C");
		anolyteActMatCost1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		anolyteActMatCost2.setText("active, a");
		anolyteActMatCost2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		anolyteActMatCost2.setTranslateY(anolyteActMatCost1.getFont().getSize() * 0.3);

		anolyteActMatCost3.setText(" / $ kg");
		anolyteActMatCost3.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");

		anolyteActMatCost4.setText("-1");
		anolyteActMatCost4.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-size: 9");
		anolyteActMatCost4.setTranslateY(anolyteActMatCost1.getFont().getSize() * -0.3);

		//anolyteActMatCoeff:
		anolyteActMatCoeff1.setText("s");
		anolyteActMatCoeff1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		anolyteActMatCoeff2.setText("a");
		anolyteActMatCoeff2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		anolyteActMatCoeff2.setTranslateY(anolyteActMatCoeff1.getFont().getSize() * 0.3);


		//anolyteActMatMolMass:
		anolyteActMatMolMass1.setText("MW");
		anolyteActMatMolMass1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");

		anolyteActMatMolMass2.setText("a");
		anolyteActMatMolMass2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		anolyteActMatMolMass2.setTranslateY(anolyteActMatMolMass1.getFont().getSize() * 0.3);

		anolyteActMatMolMass3.setText(" / g mol");
		anolyteActMatMolMass3.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");

		anolyteActMatMolMass4.setText("-1");
		anolyteActMatMolMass4.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-size: 9");
		anolyteActMatMolMass4.setTranslateY(anolyteActMatMolMass1.getFont().getSize() * -0.3);


		//anolyteSocRange:
		anolyteSocRange1.setText("Χ");
		anolyteSocRange1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		anolyteSocRange2.setText("a");
		anolyteSocRange2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		anolyteSocRange2.setTranslateY(anolyteSocRange1.getFont().getSize() * 0.3);


		//anolyteActMatNumberElectron:
		anolyteActMatNumberElectron1.setText("n");
		anolyteActMatNumberElectron1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		anolyteActMatNumberElectron2.setText("e, a");
		anolyteActMatNumberElectron2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		anolyteActMatNumberElectron2.setTranslateY(anolyteActMatNumberElectron1.getFont().getSize() * 0.3);


		//anolyteActMatNumberProton:
		anolyteActMatNumberProton1.setText("n");
		anolyteActMatNumberProton1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		anolyteActMatNumberProton2.setText("H, a");
		anolyteActMatNumberProton2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		anolyteActMatNumberProton2.setTranslateY(anolyteActMatNumberProton1.getFont().getSize() * 0.3);


		//anolyteActMatSol:
		anolyteActMatSol1.setText("L");
		anolyteActMatSol1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		anolyteActMatSol2.setText("a");
		anolyteActMatSol2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		anolyteActMatSol2.setTranslateY(anolyteActMatSol1.getFont().getSize() * 0.3);

		anolyteActMatSol3.setText(" / Kg Kg");
		anolyteActMatSol3.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");

		anolyteActMatSol4.setText("-1");
		anolyteActMatSol4.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-size: 9");
		anolyteActMatSol4.setTranslateY(anolyteActMatSol1.getFont().getSize() * -0.3);


		//anolyteSaltMolMass:
		anolyteSaltMolMass1.setText("M");
		anolyteSaltMolMass1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		anolyteSaltMolMass2.setText("salt, a");
		anolyteSaltMolMass2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		anolyteSaltMolMass2.setTranslateY(anolyteSaltMolMass1.getFont().getSize() * 0.3);

		anolyteSaltMolMass3.setText(" / g mol");
		anolyteSaltMolMass3.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");

		anolyteSaltMolMass4.setText("-1");
		anolyteSaltMolMass4.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-size: 9");
		anolyteSaltMolMass4.setTranslateY(anolyteSaltMolMass1.getFont().getSize() * -0.3);


		//anolyteSaltSol:
		anolyteSaltSol1.setText("L");
		anolyteSaltSol1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		anolyteSaltSol2.setText("salt, a");
		anolyteSaltSol2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		anolyteSaltSol2.setTranslateY(anolyteSaltSol1.getFont().getSize() * 0.3);

		anolyteSaltSol3.setText(" / Kg Kg");
		anolyteSaltSol3.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");

		anolyteSaltSol4.setText("-1");
		anolyteSaltSol4.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-size: 9");
		anolyteSaltSol4.setTranslateY(anolyteSaltSol1.getFont().getSize() * -0.3);


		//anolytePotential:
		anolytePotential1.setText("E");
		anolytePotential1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		anolytePotential2.setText("a");
		anolytePotential2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		anolytePotential2.setTranslateY(anolytePotential1.getFont().getSize() * 0.3);

		anolytePotential3.setText(" / V");
		anolytePotential3.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");




		//catholyteActMatCost:
		catholyteActMatCost1.setText("C");
		catholyteActMatCost1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		catholyteActMatCost2.setText("active, c");
		catholyteActMatCost2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		catholyteActMatCost2.setTranslateY(catholyteActMatCost1.getFont().getSize() * 0.3);

		catholyteActMatCost3.setText(" / $ kg");
		catholyteActMatCost3.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");

		catholyteActMatCost4.setText("-1");
		catholyteActMatCost4.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-size: 9");
		catholyteActMatCost4.setTranslateY(catholyteActMatCost1.getFont().getSize() * -0.3);

		//catholyteActMatCoeff:
		catholyteActMatCoeff1.setText("s");
		catholyteActMatCoeff1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		catholyteActMatCoeff2.setText("c");
		catholyteActMatCoeff2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		catholyteActMatCoeff2.setTranslateY(anolyteActMatCoeff1.getFont().getSize() * 0.3);


		//catholyteActMatMolMass:
		catholyteActMatMolMass1.setText("MW");
		catholyteActMatMolMass1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");

		catholyteActMatMolMass2.setText("c");
		catholyteActMatMolMass2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		catholyteActMatMolMass2.setTranslateY(catholyteActMatMolMass1.getFont().getSize() * 0.3);

		catholyteActMatMolMass3.setText(" / g mol");
		catholyteActMatMolMass3.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");

		catholyteActMatMolMass4.setText("-1");
		catholyteActMatMolMass4.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-size: 9");
		catholyteActMatMolMass4.setTranslateY(catholyteActMatMolMass1.getFont().getSize() * -0.3);


		//catholyteSocRange:
		catholyteSocRange1.setText("Χ");
		catholyteSocRange1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		catholyteSocRange2.setText("c");
		catholyteSocRange2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		catholyteSocRange2.setTranslateY(catholyteSocRange1.getFont().getSize() * 0.3);


		//catholyteActMatNumberElectron:
		catholyteActMatNumberElectron1.setText("n");
		catholyteActMatNumberElectron1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		catholyteActMatNumberElectron2.setText("e, a");
		catholyteActMatNumberElectron2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		catholyteActMatNumberElectron2.setTranslateY(catholyteActMatNumberElectron1.getFont().getSize() * 0.3);


		//catholyteActMatNumberProton:
		catholyteActMatNumberProton1.setText("n");
		catholyteActMatNumberProton1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		catholyteActMatNumberProton2.setText("H, c");
		catholyteActMatNumberProton2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		catholyteActMatNumberProton2.setTranslateY(catholyteActMatNumberProton1.getFont().getSize() * 0.3);


		//catholyteActMatSol:
		catholyteActMatSol1.setText("L");
		catholyteActMatSol1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		catholyteActMatSol2.setText("c");
		catholyteActMatSol2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		catholyteActMatSol2.setTranslateY(catholyteActMatSol1.getFont().getSize() * 0.3);

		catholyteActMatSol3.setText(" / Kg Kg");
		catholyteActMatSol3.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");

		catholyteActMatSol4.setText("-1");
		catholyteActMatSol4.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-size: 9");
		catholyteActMatSol4.setTranslateY(catholyteActMatSol1.getFont().getSize() * -0.3);


		//catholyteSaltMolMass:
		catholyteSaltMolMass1.setText("M");
		catholyteSaltMolMass1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		catholyteSaltMolMass2.setText("salt, c");
		catholyteSaltMolMass2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		catholyteSaltMolMass2.setTranslateY(catholyteSaltMolMass1.getFont().getSize() * 0.3);

		catholyteSaltMolMass3.setText(" / g mol");
		catholyteSaltMolMass3.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");

		catholyteSaltMolMass4.setText("-1");
		catholyteSaltMolMass4.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-size: 9");
		catholyteSaltMolMass4.setTranslateY(catholyteSaltMolMass1.getFont().getSize() * -0.3);


		//catholyteSaltSol:
		catholyteSaltSol1.setText("L");
		catholyteSaltSol1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		catholyteSaltSol2.setText("salt, c");
		catholyteSaltSol2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		catholyteSaltSol2.setTranslateY(anolyteSaltSol1.getFont().getSize() * 0.3);

		catholyteSaltSol3.setText(" / Kg Kg");
		catholyteSaltSol3.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");

		catholyteSaltSol4.setText("-1");
		catholyteSaltSol4.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-size: 9");
		catholyteSaltSol4.setTranslateY(catholyteSaltSol1.getFont().getSize() * -0.3);


		//catholytePotential:
		catholytePotential1.setText("E");
		catholytePotential1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		catholytePotential2.setText("c");
		catholytePotential2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		catholytePotential2.setTranslateY(catholytePotential1.getFont().getSize() * 0.3);

		catholytePotential3.setText(" / V");
		catholytePotential3.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");




		//standardCellVoltageEl:
		standardCellVoltageEl1.setText("U");
		standardCellVoltageEl1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		standardCellVoltageEl2.setText("cell");
		standardCellVoltageEl2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		standardCellVoltageEl2.setTranslateY(standardCellVoltageEl1.getFont().getSize() * 0.3);

		standardCellVoltageEl3.setText(" / V");
		standardCellVoltageEl3.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");

		//ravg:
		ravg1.setText("r");
		ravg1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		ravg2.setText("avg.");
		ravg2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		ravg2.setTranslateY(ravg1.getFont().getSize() * 0.3);

		ravg3.setText(" / mol mol");
		ravg3.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");

		ravg4.setText("-1");
		ravg4.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-size: 9");
		ravg4.setTranslateY(ravg1.getFont().getSize() * -0.3);

		//bavg:
		bavg1.setText("b");
		bavg1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		bavg2.setText("avg.");
		bavg2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		bavg2.setTranslateY(bavg1.getFont().getSize() * 0.3);

		bavg3.setText(" / mol Kg");
		bavg3.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");

		bavg4.setText("-1");
		bavg4.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-size: 9");
		bavg4.setTranslateY(bavg1.getFont().getSize() * -0.3);

		//efficiencySysDischarge:
		efficiencySysDischarge1.setText("ɛ");
		efficiencySysDischarge1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		efficiencySysDischarge2.setText("sys, d");
		efficiencySysDischarge2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		efficiencySysDischarge2.setTranslateY(efficiencySysDischarge1.getFont().getSize() * 0.3);

		//efficiencyVoltDischarge:
		efficiencyVoltDischarge1.setText("ɛ");
		efficiencyVoltDischarge1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		efficiencyVoltDischarge2.setText("v, d");
		efficiencyVoltDischarge2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		efficiencyVoltDischarge2.setTranslateY(efficiencyVoltDischarge1.getFont().getSize() * 0.3);

		//efficiencyCoulombicRoundTrip:
		efficiencyCoulombicRoundTrip1.setText("ɛ");
		efficiencyCoulombicRoundTrip1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		efficiencyCoulombicRoundTrip2.setText("q, rt");
		efficiencyCoulombicRoundTrip2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		efficiencyCoulombicRoundTrip2.setTranslateY(efficiencyCoulombicRoundTrip1.getFont().getSize() * 0.3);



		//saltCost:
		saltCost1.setText("C");
		saltCost1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		saltCost2.setText("salt");
		saltCost2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		saltCost2.setTranslateY(saltCost1.getFont().getSize() * 0.3);

		saltCost3.setText(" / $ Kg");
		saltCost3.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");

		saltCost4.setText("-1");
		saltCost4.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-size: 9");
		saltCost4.setTranslateY(saltCost1.getFont().getSize() * -0.3);

		//solventCost:
		solventCost1.setText("C");
		solventCost1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		solventCost2.setText("solvent");
		solventCost2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		solventCost2.setTranslateY(solventCost1.getFont().getSize() * 0.3);

		solventCost3.setText(" / $ Kg");
		solventCost3.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");

		solventCost4.setText("-1");
		solventCost4.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-size: 9");
		solventCost4.setTranslateY(solventCost1.getFont().getSize() * -0.3);

		//solventDensity:
		solventDensity1.setText("density");
		solventDensity1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");

		solventDensity2.setText(" / g cm");
		solventDensity2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");

		solventDensity3.setText("-3");
		solventDensity3.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-size: 9");
		solventDensity3.setTranslateY(solventDensity1.getFont().getSize() * -0.3);

		//temp:
		temp1.setText("T");
		temp1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		temp2.setText(" / K");
		temp2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");

		//pH:
		pH1.setText("pH");
		pH1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");



		//anolyteTankCost:
		anolyteTankCost1.setText("C");
		anolyteTankCost1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		anolyteTankCost2.setText("tank, a");
		anolyteTankCost2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		anolyteTankCost2.setTranslateY(anolyteTankCost1.getFont().getSize() * 0.3);

		anolyteTankCost3.setText(" / $ m");
		anolyteTankCost3.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");

		anolyteTankCost4.setText("-3");
		anolyteTankCost4.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-size: 9");
		anolyteTankCost4.setTranslateY(anolyteTankCost1.getFont().getSize() * -0.3);

		//catholyteTankCost:
		catholyteTankCost1.setText("C");
		catholyteTankCost1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		catholyteTankCost2.setText("tank, c");
		catholyteTankCost2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		catholyteTankCost2.setTranslateY(catholyteTankCost1.getFont().getSize() * 0.3);

		catholyteTankCost3.setText(" / $ m");
		catholyteTankCost3.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");

		catholyteTankCost4.setText("-3");
		catholyteTankCost4.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-size: 9");
		catholyteTankCost4.setTranslateY(catholyteTankCost1.getFont().getSize() * -0.3);

		//tankFillLevel:
		tankFillLevel1.setText("V");
		tankFillLevel1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		tankFillLevel2.setText("max.");
		tankFillLevel2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		tankFillLevel2.setTranslateY(tankFillLevel1.getFont().getSize() * 0.3);

		tankFillLevel3.setText(" / %");
		tankFillLevel3.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");

		//tankCost:
		tankCost1.setText("C");
		tankCost1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		tankCost2.setText("tank");
		tankCost2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		tankCost2.setTranslateY(tankCost1.getFont().getSize() * 0.3);

		tankCost3.setText(" / $ mol");
		tankCost3.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");

		tankCost4.setText("-1");
		tankCost4.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-size: 9");
		tankCost4.setTranslateY(tankCost1.getFont().getSize() * -0.3);


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////


		//stackCost:
		stackCost1.setText("C");
		stackCost1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		stackCost2.setText("a");
		stackCost2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		stackCost2.setTranslateY(stackCost1.getFont().getSize() * 0.3);

		stackCost3.setText(" / $ m");
		stackCost3.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");

		stackCost4.setText("-2");
		stackCost4.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-size: 9");
		stackCost4.setTranslateY(stackCost1.getFont().getSize() * -0.3);

		//ASR:
		ASR1.setText("ASR");
		ASR1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");

		ASR2.setText(" / Ohm cm");
		ASR2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");

		ASR3.setText("2");
		ASR3.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-size: 9");
		ASR3.setTranslateY(ASR1.getFont().getSize() * -0.3);

		//timeDischarge:
		timeDischarge1.setText("t");
		timeDischarge1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		timeDischarge2.setText("d");
		timeDischarge2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		timeDischarge2.setTranslateY(timeDischarge1.getFont().getSize() * 0.3);

		timeDischarge3.setText(" / h");
		timeDischarge3.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");

		//powerDischarge:
		powerDischarge1.setText("P");
		powerDischarge1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		powerDischarge2.setText("d");
		powerDischarge2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		powerDischarge2.setTranslateY(powerDischarge1.getFont().getSize() * 0.3);

		powerDischarge3.setText(" / MW");
		powerDischarge3.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");

		//energyCapacity:
		energyCapacity1.setText("E");
		energyCapacity1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		energyCapacity2.setText("d");
		energyCapacity2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		energyCapacity2.setTranslateY(energyCapacity1.getFont().getSize() * 0.3);

		energyCapacity3.setText(" / MWh");
		energyCapacity3.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");

		//socDischarge:
		socDischarge1.setText("SOC");
		socDischarge1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");
		
		//costBOP:
		costBOP1.setText("C");
		costBOP1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		costBOP2.setText("BOP");
		costBOP2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		costBOP2.setTranslateY(costBOP1.getFont().getSize() * 0.3);

		costBOP3.setText(" / $ kW");
		costBOP3.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");

		costBOP4.setText("-1");
		costBOP4.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-size: 9");
		costBOP4.setTranslateY(costBOP1.getFont().getSize() * -0.3);

		//costAdd:
		costAdd1.setText("C");
		costAdd1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		costAdd2.setText("Add");
		costAdd2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		costAdd2.setTranslateY(costAdd1.getFont().getSize() * 0.3);

		costAdd3.setText(" / $ kW");
		costAdd3.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");

		costAdd4.setText("-1");
		costAdd4.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-size: 9");
		costAdd4.setTranslateY(costAdd1.getFont().getSize() * -0.3);

		//currentDensityDischarge:
		currentDensityDischarge1.setText("i");
		currentDensityDischarge1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		currentDensityDischarge2.setText("d");
		currentDensityDischarge2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		currentDensityDischarge2.setTranslateY(currentDensityDischarge1.getFont().getSize() * 0.3);

		currentDensityDischarge3.setText(" / A cm");
		currentDensityDischarge3.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");

		currentDensityDischarge4.setText("-2");
		currentDensityDischarge4.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-size: 9");
		currentDensityDischarge4.setTranslateY(currentDensityDischarge1.getFont().getSize() * -0.3);

		//voltageDischarge:
		voltageDischarge1.setText("U");
		voltageDischarge1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		voltageDischarge2.setText("d");
		voltageDischarge2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		voltageDischarge2.setTranslateY(voltageDischarge1.getFont().getSize() * 0.3);

		voltageDischarge3.setText(" / V");
		voltageDischarge3.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");
		
		

		//standardCellVoltagePow:
		standardCellVoltagePow1.setText("U");
		standardCellVoltagePow1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		standardCellVoltagePow2.setText("0");
		standardCellVoltagePow2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		standardCellVoltagePow2.setTranslateY(standardCellVoltagePow1.getFont().getSize() * 0.3);

		standardCellVoltagePow3.setText(" / V");
		standardCellVoltagePow3.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");

		//reversibleVoltage:
		reversibleVoltage1.setText("U");
		reversibleVoltage1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		reversibleVoltage2.setText("ocv");
		reversibleVoltage2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		reversibleVoltage2.setTranslateY(reversibleVoltage1.getFont().getSize() * 0.3);

		reversibleVoltage3.setText(" / V");
		reversibleVoltage3.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");

		//chargeTranserOverPotDischarge:
		chargeTranserOverPotDischarge1.setText("η");
		chargeTranserOverPotDischarge1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		chargeTranserOverPotDischarge2.setText("ct");
		chargeTranserOverPotDischarge2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		chargeTranserOverPotDischarge2.setTranslateY(chargeTranserOverPotDischarge1.getFont().getSize() * 0.3);

		chargeTranserOverPotDischarge3.setText(" / V");
		chargeTranserOverPotDischarge3.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");

		//ohmicOverPotDischarge:
		ohmicOverPotDischarge1.setText("η");
		ohmicOverPotDischarge1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		ohmicOverPotDischarge2.setText("ohm");
		ohmicOverPotDischarge2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		ohmicOverPotDischarge2.setTranslateY(ohmicOverPotDischarge1.getFont().getSize() * 0.3);

		ohmicOverPotDischarge3.setText(" / V");
		ohmicOverPotDischarge3.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");

		//concentrationOverPotDischarge:
		concentrationOverPotDischarge1.setText("η");
		concentrationOverPotDischarge1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");
		
		concentrationOverPotDischarge2.setText("conc");
		concentrationOverPotDischarge2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		concentrationOverPotDischarge2.setTranslateY(concentrationOverPotDischarge1.getFont().getSize() * 0.3);
		
		concentrationOverPotDischarge3.setText(" / V");
		concentrationOverPotDischarge3.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");




		//anolyteReactionRate:
		anolyteReactionRate1.setText("k");
		anolyteReactionRate1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		anolyteReactionRate2.setText("0, a");
		anolyteReactionRate2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		anolyteReactionRate2.setTranslateY(anolyteReactionRate1.getFont().getSize() * 0.3);

		anolyteReactionRate3.setText(" / cm s");
		anolyteReactionRate3.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");

		anolyteReactionRate4.setText("-1");
		anolyteReactionRate4.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-size: 9");
		anolyteReactionRate4.setTranslateY(anolyteReactionRate1.getFont().getSize() * -0.3);

		//anolyteTransferCoeff:
		anolyteTransferCoeff1.setText("α");
		anolyteTransferCoeff1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		anolyteTransferCoeff2.setText("a");
		anolyteTransferCoeff2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		anolyteTransferCoeff2.setTranslateY(anolyteTransferCoeff1.getFont().getSize() * 0.3);

		//catholyteReactionRate:
		catholyteReactionRate1.setText("k");
		catholyteReactionRate1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		catholyteReactionRate2.setText("0, c");
		catholyteReactionRate2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		catholyteReactionRate2.setTranslateY(catholyteReactionRate1.getFont().getSize() * 0.3);

		catholyteReactionRate3.setText(" / cm s");
		catholyteReactionRate3.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");

		catholyteReactionRate4.setText("-1");
		catholyteReactionRate4.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-size: 9");
		catholyteReactionRate4.setTranslateY(catholyteReactionRate1.getFont().getSize() * -0.3);

		//catholyteTransferCoeff:
		catholyteTransferCoeff1.setText("α");
		catholyteTransferCoeff1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		catholyteTransferCoeff2.setText("c");
		catholyteTransferCoeff2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		catholyteTransferCoeff2.setTranslateY(catholyteTransferCoeff1.getFont().getSize() * 0.3);

		//anolyteExchangeCurrentDensity:
		anolyteExchangeCurrentDensity1.setText("i");
		anolyteExchangeCurrentDensity1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		anolyteExchangeCurrentDensity2.setText("0, a");
		anolyteExchangeCurrentDensity2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		anolyteExchangeCurrentDensity2.setTranslateY(anolyteExchangeCurrentDensity1.getFont().getSize() * 0.3);

		anolyteExchangeCurrentDensity3.setText(" / A cm");
		anolyteExchangeCurrentDensity3.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");

		anolyteExchangeCurrentDensity4.setText("-2");
		anolyteExchangeCurrentDensity4.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-size: 9");
		anolyteExchangeCurrentDensity4.setTranslateY(anolyteExchangeCurrentDensity1.getFont().getSize() * -0.3);

		//catholyteExchangeCurrentDensity:
		catholyteExchangeCurrentDensity1.setText("i");
		catholyteExchangeCurrentDensity1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		catholyteExchangeCurrentDensity2.setText("0, c");
		catholyteExchangeCurrentDensity2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		catholyteExchangeCurrentDensity2.setTranslateY(catholyteExchangeCurrentDensity1.getFont().getSize() * 0.3);

		catholyteExchangeCurrentDensity3.setText(" / A cm");
		catholyteExchangeCurrentDensity3.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");

		catholyteExchangeCurrentDensity4.setText("-2");
		catholyteExchangeCurrentDensity4.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-size: 9");
		catholyteExchangeCurrentDensity4.setTranslateY(catholyteExchangeCurrentDensity1.getFont().getSize() * -0.3);

		//anolyteChargeTranserOverPotDischarge:
		anolyteChargeTranserOverPotDischarge1.setText("η");
		anolyteChargeTranserOverPotDischarge1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		anolyteChargeTranserOverPotDischarge2.setText("ct, a");
		anolyteChargeTranserOverPotDischarge2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		anolyteChargeTranserOverPotDischarge2.setTranslateY(anolyteChargeTranserOverPotDischarge1.getFont().getSize() * 0.3);

		anolyteChargeTranserOverPotDischarge3.setText(" / V");
		anolyteChargeTranserOverPotDischarge3.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");

		//catholyteChargeTranserOverPotDischarge:
		catholyteChargeTranserOverPotDischarge1.setText("η");
		catholyteChargeTranserOverPotDischarge1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		catholyteChargeTranserOverPotDischarge2.setText("ct, c");
		catholyteChargeTranserOverPotDischarge2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		catholyteChargeTranserOverPotDischarge2.setTranslateY(catholyteChargeTranserOverPotDischarge1.getFont().getSize() * 0.3);

		catholyteChargeTranserOverPotDischarge3.setText(" / V");
		catholyteChargeTranserOverPotDischarge3.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");
		
		
		
		
		//diaFiber:
		diaFiber1.setText("d");
		diaFiber1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		diaFiber2.setText("fiber");
		diaFiber2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		diaFiber2.setTranslateY(diaFiber1.getFont().getSize() * 0.3);

		diaFiber3.setText(" / m");
		diaFiber3.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");
		
		//flowVelocity:
		flowVelocity1.setText("flow velocity");
		flowVelocity1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");

		flowVelocity2.setText(" / m s");
		flowVelocity2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");

		flowVelocity3.setText("-1");
		flowVelocity3.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		flowVelocity3.setTranslateY(anolyteConcentrationOverPotDischarge1.getFont().getSize() * -0.3);
		
		//anolyteDiffusionCoeff:
		anolyteDiffusionCoeff1.setText("D");
		anolyteDiffusionCoeff1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		anolyteDiffusionCoeff2.setText("a");
		anolyteDiffusionCoeff2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		anolyteDiffusionCoeff2.setTranslateY(anolyteDiffusionCoeff1.getFont().getSize() * 0.3);

		anolyteDiffusionCoeff3.setText(" / cm");
		anolyteDiffusionCoeff3.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");

		anolyteDiffusionCoeff4.setText("2");
		anolyteDiffusionCoeff4.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-size: 9");
		anolyteDiffusionCoeff4.setTranslateY(anolyteDiffusionCoeff1.getFont().getSize() * -0.3);
		
		anolyteDiffusionCoeff5.setText(" s");
		anolyteDiffusionCoeff5.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");
		
		anolyteDiffusionCoeff6.setText("-1");
		anolyteDiffusionCoeff6.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-size: 9");
		anolyteDiffusionCoeff6.setTranslateY(anolyteDiffusionCoeff1.getFont().getSize() * -0.3);
		
		//catholyteDiffusionCoeff:
		catholyteDiffusionCoeff1.setText("D");
		catholyteDiffusionCoeff1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		catholyteDiffusionCoeff2.setText("c");
		catholyteDiffusionCoeff2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		catholyteDiffusionCoeff2.setTranslateY(catholyteDiffusionCoeff1.getFont().getSize() * 0.3);

		catholyteDiffusionCoeff3.setText(" / cm");
		catholyteDiffusionCoeff3.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");

		catholyteDiffusionCoeff4.setText("2");
		catholyteDiffusionCoeff4.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-size: 9");
		catholyteDiffusionCoeff4.setTranslateY(catholyteDiffusionCoeff1.getFont().getSize() * -0.3);
		
		catholyteDiffusionCoeff5.setText(" s");
		catholyteDiffusionCoeff5.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");
		
		catholyteDiffusionCoeff6.setText("-1");
		catholyteDiffusionCoeff6.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-size: 9");
		catholyteDiffusionCoeff6.setTranslateY(catholyteDiffusionCoeff1.getFont().getSize() * -0.3);
		
		//anolyteConcentrationOverPotDischarge:
		anolyteConcentrationOverPotDischarge1.setText("η");
		anolyteConcentrationOverPotDischarge1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		anolyteConcentrationOverPotDischarge2.setText("conc, a");
		anolyteConcentrationOverPotDischarge2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		anolyteConcentrationOverPotDischarge2.setTranslateY(anolyteConcentrationOverPotDischarge1.getFont().getSize() * 0.3);

		anolyteConcentrationOverPotDischarge3.setText(" / V");
		anolyteConcentrationOverPotDischarge3.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");

		//catholyteConcentrationOverPotDischarge:
		catholyteConcentrationOverPotDischarge1.setText("η");
		catholyteConcentrationOverPotDischarge1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		catholyteConcentrationOverPotDischarge2.setText("conc, c");
		catholyteConcentrationOverPotDischarge2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		catholyteConcentrationOverPotDischarge2.setTranslateY(catholyteConcentrationOverPotDischarge1.getFont().getSize() * 0.3);

		catholyteConcentrationOverPotDischarge3.setText(" / V");
		catholyteConcentrationOverPotDischarge3.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");




		//cellArea:
		cellArea1.setText("A");
		cellArea1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		cellArea2.setText("cell");
		cellArea2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		cellArea2.setTranslateY(cellArea1.getFont().getSize() * 0.3);

		cellArea3.setText(" / m");
		cellArea3.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");

		cellArea4.setText("2");
		cellArea4.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-size: 9");
		cellArea4.setTranslateY(cellArea1.getFont().getSize() * -0.3);

		//totalArea:
		totalArea1.setText("A");
		totalArea1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		totalArea2.setText("total");
		totalArea2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		totalArea2.setTranslateY(totalArea1.getFont().getSize() * 0.3);

		totalArea3.setText(" / m");
		totalArea3.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");

		totalArea4.setText("2");
		totalArea4.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-size: 9");
		totalArea4.setTranslateY(totalArea1.getFont().getSize() * -0.3);

		//numberCellsPerStack:
		numberCellsPerStack1.setText("n");
		numberCellsPerStack1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		numberCellsPerStack2.setText("cells, stack");
		numberCellsPerStack2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		numberCellsPerStack2.setTranslateY(numberCellsPerStack1.getFont().getSize() * 0.3);

		//numberCells:
		numberCells1.setText("n");
		numberCells1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		numberCells2.setText("cells");
		numberCells2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		numberCells2.setTranslateY(numberCells1.getFont().getSize() * 0.3);

		//numberStacks:
		numberStacks1.setText("n");
		numberStacks1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		numberStacks2.setText("stacks");
		numberStacks2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		numberStacks2.setTranslateY(numberStacks1.getFont().getSize() * 0.3);

		//reactorCost:
		reactorCost1.setText("C");
		reactorCost1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		reactorCost2.setText("reactor");
		reactorCost2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		reactorCost2.setTranslateY(reactorCost1.getFont().getSize() * 0.3);

		reactorCost3.setText(" / $");
		reactorCost3.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		//operationalLifetime:
		operationalLifetime1.setText("n");
		operationalLifetime1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		operationalLifetime2.setText(" / Y");
		operationalLifetime2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");

		//interestRate:
		interestRate1.setText("r");
		interestRate1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		//anolyteReplacementFraction:
		anolyteReplacementFraction1.setText("f");
		anolyteReplacementFraction1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		anolyteReplacementFraction2.setText("a");
		anolyteReplacementFraction2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		anolyteReplacementFraction2.setTranslateY(anolyteReplacementFraction1.getFont().getSize() * 0.3);

		anolyteReplacementFraction3.setText(" / Y");
		anolyteReplacementFraction3.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");

		anolyteReplacementFraction4.setText("-1");
		anolyteReplacementFraction4.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-size: 9");
		anolyteReplacementFraction4.setTranslateY(anolyteReplacementFraction1.getFont().getSize() * -0.3);

		//catholyteReplacementFraction:
		catholyteReplacementFraction1.setText("f");
		catholyteReplacementFraction1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		catholyteReplacementFraction2.setText("c");
		catholyteReplacementFraction2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		catholyteReplacementFraction2.setTranslateY(catholyteReplacementFraction1.getFont().getSize() * 0.3);

		catholyteReplacementFraction3.setText(" / Y");
		catholyteReplacementFraction3.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");

		catholyteReplacementFraction4.setText("-1");
		catholyteReplacementFraction4.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-size: 9");
		catholyteReplacementFraction4.setTranslateY(catholyteReplacementFraction1.getFont().getSize() * -0.3);

		//maxReplacementFraction:
		maxReplacementFraction1.setText("f");
		maxReplacementFraction1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		maxReplacementFraction2.setText("max.");
		maxReplacementFraction2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		maxReplacementFraction2.setTranslateY(maxReplacementFraction1.getFont().getSize() * 0.3);

		maxReplacementFraction3.setText(" / Y");
		maxReplacementFraction3.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");

		maxReplacementFraction4.setText("-1");
		maxReplacementFraction4.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-size: 9");
		maxReplacementFraction4.setTranslateY(maxReplacementFraction1.getFont().getSize() * -0.3);

		//electrolyteDegradationNPVCost:
		electrolyteDegradationNPVCost1.setText("C");
		electrolyteDegradationNPVCost1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		electrolyteDegradationNPVCost2.setText("npv, el.deg.");
		electrolyteDegradationNPVCost2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		electrolyteDegradationNPVCost2.setTranslateY(electrolyteDegradationNPVCost1.getFont().getSize() * 0.3);

		electrolyteDegradationNPVCost3.setText(" / $ kWh");
		electrolyteDegradationNPVCost3.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");

		electrolyteDegradationNPVCost4.setText("-1");
		electrolyteDegradationNPVCost4.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-size: 9");
		electrolyteDegradationNPVCost4.setTranslateY(electrolyteDegradationNPVCost1.getFont().getSize() * -0.3);




		//anolyteReplacementCostMass:
		anolyteReplacementCostMass1.setText("C");
		anolyteReplacementCostMass1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		anolyteReplacementCostMass2.setText("repl., el.a.");
		anolyteReplacementCostMass2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		anolyteReplacementCostMass2.setTranslateY(anolyteReplacementCostMass1.getFont().getSize() * 0.3);

		anolyteReplacementCostMass3.setText(" / $ Kg");
		anolyteReplacementCostMass3.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");

		anolyteReplacementCostMass4.setText("-1");
		anolyteReplacementCostMass4.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-size: 9");
		anolyteReplacementCostMass4.setTranslateY(anolyteReplacementCostMass1.getFont().getSize() * -0.3);

		//anolyteReplacementCost:
		anolyteReplacementCost1.setText("C");
		anolyteReplacementCost1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		anolyteReplacementCost2.setText("replacement, a");
		anolyteReplacementCost2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		anolyteReplacementCost2.setTranslateY(anolyteReplacementCost1.getFont().getSize() * 0.3);

		anolyteReplacementCost3.setText(" / $ kWh");
		anolyteReplacementCost3.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");

		anolyteReplacementCost4.setText("-1");
		anolyteReplacementCost4.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-size: 9");
		anolyteReplacementCost4.setTranslateY(anolyteReplacementCost1.getFont().getSize() * -0.3);

		//catholyteReplacementCostMass:
		catholyteReplacementCostMass1.setText("C");
		catholyteReplacementCostMass1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		catholyteReplacementCostMass2.setText("repl., el.c.");
		catholyteReplacementCostMass2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		catholyteReplacementCostMass2.setTranslateY(catholyteReplacementCostMass1.getFont().getSize() * 0.3);

		catholyteReplacementCostMass3.setText(" / $ Kg");
		catholyteReplacementCostMass3.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");

		catholyteReplacementCostMass4.setText("-1");
		catholyteReplacementCostMass4.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-size: 9");
		catholyteReplacementCostMass4.setTranslateY(catholyteReplacementCostMass1.getFont().getSize() * -0.3);

		//catholyteReplacementCost:
		catholyteReplacementCost1.setText("C");
		catholyteReplacementCost1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		catholyteReplacementCost2.setText("replacement, c");
		catholyteReplacementCost2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		catholyteReplacementCost2.setTranslateY(catholyteReplacementCost1.getFont().getSize() * 0.3);

		catholyteReplacementCost3.setText(" / $ kWh");
		catholyteReplacementCost3.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");

		catholyteReplacementCost4.setText("-1");
		catholyteReplacementCost4.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-size: 9");
		catholyteReplacementCost4.setTranslateY(catholyteReplacementCost1.getFont().getSize() * -0.3);

		//stackExchangeCost:
		stackExchangeCost1.setText("C");
		stackExchangeCost1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		stackExchangeCost2.setText("replacement, a");
		stackExchangeCost2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		stackExchangeCost2.setTranslateY(stackExchangeCost1.getFont().getSize() * 0.3);

		stackExchangeCost3.setText(" / $ kWh");
		stackExchangeCost3.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");

		stackExchangeCost4.setText("-1");
		stackExchangeCost4.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-size: 9");
		stackExchangeCost4.setTranslateY(stackExchangeCost1.getFont().getSize() * -0.3);

		//electrolyteOperationalTime:
		electrolyteOperationalTime1.setText("n");
		electrolyteOperationalTime1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		electrolyteOperationalTime2.setText("repl.");
		electrolyteOperationalTime2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		electrolyteOperationalTime2.setTranslateY(electrolyteOperationalTime1.getFont().getSize() * 0.3);

		electrolyteOperationalTime3.setText(" / Y");
		electrolyteOperationalTime3.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");

		//threshold:
		threshold1.setText("T");
		threshold1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");




		//replacementNPVCost:
		replacementNPVCost1.setText("C");
		replacementNPVCost1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		replacementNPVCost2.setText("npv, repl.");
		replacementNPVCost2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		replacementNPVCost2.setTranslateY(replacementNPVCost1.getFont().getSize() * 0.3);

		replacementNPVCost3.setText(" / $ kWh");
		replacementNPVCost3.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");

		replacementNPVCost4.setText("-1");
		replacementNPVCost4.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-size: 9");
		replacementNPVCost4.setTranslateY(replacementNPVCost1.getFont().getSize() * -0.3);




		//stackExchangeCost:
		stackExchangeCost1.setText("C");
		stackExchangeCost1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		stackExchangeCost2.setText("stack");
		stackExchangeCost2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		stackExchangeCost2.setTranslateY(stackExchangeCost1.getFont().getSize() * 0.3);

		stackExchangeCost3.setText(" / $ kW");
		stackExchangeCost3.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");

		stackExchangeCost4.setText("-1");
		stackExchangeCost4.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-size: 9");
		stackExchangeCost4.setTranslateY(stackExchangeCost1.getFont().getSize() * -0.3);

		//stackOperationalTime:
		stackOperationalTime1.setText("n");
		stackOperationalTime1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		stackOperationalTime2.setText("stack");
		stackOperationalTime2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		stackOperationalTime2.setTranslateY(stackExchangeCost1.getFont().getSize() * 0.3);

		stackOperationalTime3.setText(" / Y");
		stackOperationalTime3.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");

		//stackExchangeNPVCost:
		stackExchangeNPVCost1.setText("C");
		stackExchangeNPVCost1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		stackExchangeNPVCost2.setText("npv, stack");
		stackExchangeNPVCost2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		stackExchangeNPVCost2.setTranslateY(stackExchangeNPVCost1.getFont().getSize() * 0.3);

		stackExchangeNPVCost3.setText(" / $ kWh");
		stackExchangeNPVCost3.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");

		stackExchangeNPVCost4.setText("-1");
		stackExchangeNPVCost4.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-size: 9");
		stackExchangeNPVCost4.setTranslateY(stackExchangeNPVCost1.getFont().getSize() * -0.3);



//////////////////////////////////////////////////////////////////////////////////////////////////////////////////



		//anolyteCostTotal:
		anolyteCostTotal1.setText("C");
		anolyteCostTotal1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		anolyteCostTotal2.setText("tot. act., a");
		anolyteCostTotal2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		anolyteCostTotal2.setTranslateY(anolyteCostTotal1.getFont().getSize() * 0.3);

		anolyteCostTotal3.setText(" / $ Kg");
		anolyteCostTotal3.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");

		anolyteCostTotal4.setText("-1");
		anolyteCostTotal4.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-size: 9");
		anolyteCostTotal4.setTranslateY(anolyteCostTotal1.getFont().getSize() * -0.3);

		//anolyteCostTotalKAh:
		anolyteCostTotalKAh1.setText("C");
		anolyteCostTotalKAh1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		anolyteCostTotalKAh2.setText("tot. act., a");
		anolyteCostTotalKAh2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		anolyteCostTotalKAh2.setTranslateY(anolyteCostTotalKAh1.getFont().getSize() * 0.3);

		anolyteCostTotalKAh3.setText(" / $ kAh");
		anolyteCostTotalKAh3.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");

		anolyteCostTotalKAh4.setText("-1");
		anolyteCostTotalKAh4.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-size: 9");
		anolyteCostTotalKAh4.setTranslateY(anolyteCostTotalKAh1.getFont().getSize() * -0.3);



		//catholyteCostTotal:
		catholyteCostTotal1.setText("C");
		catholyteCostTotal1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		catholyteCostTotal2.setText("tot. act., c");
		catholyteCostTotal2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		catholyteCostTotal2.setTranslateY(catholyteCostTotal1.getFont().getSize() * 0.3);

		catholyteCostTotal3.setText(" / $ Kg");
		catholyteCostTotal3.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");

		catholyteCostTotal4.setText("-1");
		catholyteCostTotal4.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-size: 9");
		catholyteCostTotal4.setTranslateY(catholyteCostTotal1.getFont().getSize() * -0.3);

		//catholyteCostTotalKAh:
		catholyteCostTotalKAh1.setText("C");
		catholyteCostTotalKAh1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		catholyteCostTotalKAh2.setText("tot. act., c");
		catholyteCostTotalKAh2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		catholyteCostTotalKAh2.setTranslateY(catholyteCostTotalKAh1.getFont().getSize() * 0.3);

		catholyteCostTotalKAh3.setText(" / $ kAh");
		catholyteCostTotalKAh3.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");

		catholyteCostTotalKAh4.setText("-1");
		catholyteCostTotalKAh4.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-size: 9");
		catholyteCostTotalKAh4.setTranslateY(catholyteCostTotalKAh1.getFont().getSize() * -0.3);




		//energyDensity:
		energyDensity1.setText("energy density");
		energyDensity1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");

		energyDensity2.setText(" / Wh L");
		energyDensity2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");

		energyDensity3.setText("-1");
		energyDensity3.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-size: 9");
		energyDensity3.setTranslateY(energyDensity1.getFont().getSize() * -0.3);



		//costElectrolyte:
		costElectrolyte1.setText("C");
		costElectrolyte1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		costElectrolyte2.setText("Electrolyte");
		costElectrolyte2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		costElectrolyte2.setTranslateY(costElectrolyte1.getFont().getSize() * 0.3);

		costElectrolyte3.setText(" / $ kAh");
		costElectrolyte3.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");

		costElectrolyte4.setText("-1");
		costElectrolyte4.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-size: 9");
		costElectrolyte4.setTranslateY(costElectrolyte1.getFont().getSize() * -0.3);

		//costElectrolyteKWh:
		costElectrolyteKWh1.setText("C");
		costElectrolyteKWh1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		costElectrolyteKWh2.setText("Electrolyte");
		costElectrolyteKWh2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		costElectrolyteKWh2.setTranslateY(costElectrolyteKWh1.getFont().getSize() * 0.3);

		costElectrolyteKWh3.setText(" / $ kWh");
		costElectrolyteKWh3.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");

		costElectrolyteKWh4.setText("-1");
		costElectrolyteKWh4.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-size: 9");
		costElectrolyteKWh4.setTranslateY(costElectrolyteKWh1.getFont().getSize() * -0.3);

		//costPower:
		costPower1.setText("C");
		costPower1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		costPower2.setText("Power");
		costPower2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		costPower2.setTranslateY(costPower1.getFont().getSize() * 0.3);

		costPower3.setText(" / $ kW");
		costPower3.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");

		costPower4.setText("-1");
		costPower4.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-size: 9");
		costPower4.setTranslateY(costPower1.getFont().getSize() * -0.3);

		//costMaintenance:
		costMaintenance1.setText("C");
		costMaintenance1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		costMaintenance2.setText("Maintenance");
		costMaintenance2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		costMaintenance2.setTranslateY(costMaintenance1.getFont().getSize() * 0.3);

		costMaintenance3.setText(" / $ kWh");
		costMaintenance3.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");

		costMaintenance4.setText("-1");
		costMaintenance4.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-size: 9");
		costMaintenance4.setTranslateY(costMaintenance1.getFont().getSize() * -0.3);




		//costCapital:
		costCapital1.setText("C");
		costCapital1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		costCapital2.setText("total");
		costCapital2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		costCapital2.setTranslateY(costCapital1.getFont().getSize() * 0.3);

		costCapital3.setText(" / $ kWh");
		costCapital3.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");

		costCapital4.setText("-1");
		costCapital4.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-size: 9");
		costCapital4.setTranslateY(costCapital1.getFont().getSize() * -0.3);
		
		
		//costElectrolyteKWh1:
		costElectrolyteKWh11.setText("C");
		costElectrolyteKWh11.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");
		
		costElectrolyteKWh21.setText("Electrolyte");
		costElectrolyteKWh21.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		costElectrolyteKWh21.setTranslateY(costElectrolyteKWh1.getFont().getSize() * 0.3);
		
		costElectrolyteKWh31.setText(" / $ kWh");
		costElectrolyteKWh31.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");
		
		costElectrolyteKWh41.setText("-1");
		costElectrolyteKWh41.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-size: 9");
		costElectrolyteKWh41.setTranslateY(costElectrolyteKWh1.getFont().getSize() * -0.3);
		
		//costPower:
		costPower11.setText("C");
		costPower11.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");
		
		costPower21.setText("Power");
		costPower21.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		costPower21.setTranslateY(costPower1.getFont().getSize() * 0.3);
		
		costPower31.setText(" / $ kW");
		costPower31.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");
		
		costPower41.setText("-1");
		costPower41.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-size: 9");
		costPower41.setTranslateY(costPower1.getFont().getSize() * -0.3);
		
		//costMaintenance:
		costMaintenance11.setText("C");
		costMaintenance11.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");
		
		costMaintenance21.setText("Maintenance");
		costMaintenance21.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		costMaintenance21.setTranslateY(costMaintenance1.getFont().getSize() * 0.3);
		
		costMaintenance31.setText(" / $ kWh");
		costMaintenance31.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");
		
		costMaintenance41.setText("-1");
		costMaintenance41.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-size: 9");
		costMaintenance41.setTranslateY(costMaintenance1.getFont().getSize() * -0.3);
		
		
		
		
		//costCapital:
		costCapital11.setText("C");
		costCapital11.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");
		
		costCapital21.setText("total");
		costCapital21.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		costCapital21.setTranslateY(costCapital1.getFont().getSize() * 0.3);
		
		costCapital31.setText(" / $ kWh");
		costCapital31.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");
		
		costCapital41.setText("-1");
		costCapital41.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-size: 9");
		costCapital41.setTranslateY(costCapital1.getFont().getSize() * -0.3);




//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		voltageEfficiencyInputText1.setText("ɛ");
		voltageEfficiencyInputText1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		voltageEfficiencyInputText2.setText("v, d");
		voltageEfficiencyInputText2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		voltageEfficiencyInputText2.setTranslateY(voltageEfficiencyInputText1.getFont().getSize() * 0.3);

		voltageEfficiencyInputText3.setText(":");
		voltageEfficiencyInputText3.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");



		//currentDensityInputText:

		currentDensityInputText1.setText("i");
		currentDensityInputText1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		currentDensityInputText2.setText("d");
		currentDensityInputText2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		currentDensityInputText2.setTranslateY(currentDensityInputText1.getFont().getSize() * 0.3);

		currentDensityInputText3.setText(" / A cm");
		currentDensityInputText3.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");

		currentDensityInputText4.setText("-2");
		currentDensityInputText4.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-size: 9");
		currentDensityInputText4.setTranslateY(currentDensityInputText1.getFont().getSize() * -0.3);

		currentDensityInputText5.setText(":");
		currentDensityInputText5.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");



		//specPowerInputText:

		specPowerInputText1.setText("P");
		specPowerInputText1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		specPowerInputText2.setText("d");
		specPowerInputText2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		specPowerInputText2.setTranslateY(specPowerInputText1.getFont().getSize() * 0.3);

		specPowerInputText3.setText(" / W cm");
		specPowerInputText3.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");

		specPowerInputText4.setText("-2");
		specPowerInputText4.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-size: 9");
		specPowerInputText4.setTranslateY(specPowerInputText1.getFont().getSize() * -0.3);

		specPowerInputText5.setText(":");
		specPowerInputText5.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold");



		//checkOhmicModeText:
		checkOhmicModeText1.setText("η");
		checkOhmicModeText1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		checkOhmicModeText2.setText("ohm");
		checkOhmicModeText2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		checkOhmicModeText2.setTranslateY(checkOhmicModeText1.getFont().getSize() * 0.3);

		//checkChargeTransferModeText:
		checkChargeTransferModeText1.setText("η");
		checkChargeTransferModeText1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		checkChargeTransferModeText2.setText("ct");
		checkChargeTransferModeText2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		checkChargeTransferModeText2.setTranslateY(checkChargeTransferModeText1.getFont().getSize() * 0.3);

		//checkTransportationLimitationModeText:
		checkTransportationLimitationModeText1.setText("η");
		checkTransportationLimitationModeText1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		checkTransportationLimitationModeText2.setText("conc");
		checkTransportationLimitationModeText2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		checkTransportationLimitationModeText2.setTranslateY(checkTransportationLimitationModeText1.getFont().getSize() * 0.3);

	}



	//controls:
	@FXML
	public void uncheckStandard(ActionEvent event) {
		checkStandard.setSelected(false);
		standardSelected = false;
		catholyteStandard = false;
		anolyteStandard = false;
		CostAnalysisToolController.this.changeLabelTitle();
		
		checkFullCell.setDisable(true);
		checkFullCell.setStyle("-fx-opacity: 1");
		checkStandard.setDisable(false);
		checkStandard.requestFocus();
	}


	@FXML
	public void uncheckFullCell(ActionEvent event) {
		checkFullCell.setSelected(false);
		standardSelected = true;
		CostAnalysisToolController.this.changeLabelTitle();
		checkStandard.setDisable(true);
		checkStandard.setStyle("-fx-opacity: 1");
		checkFullCell.setDisable(false);
		checkFullCell.requestFocus();
	}


	public void checkPeakPower() {
		
		checkPeakPower.setSelected(true);
		checkVoltageEfficiency.setSelected(false);
		checkCurrentDensity.setSelected(false);
		checkSpecPower.setSelected(false);

		checkPeakPower.setDisable(true);
		checkPeakPower.setStyle("-fx-opacity: 1");
		checkVoltageEfficiency.requestFocus();

		peakPowerInput.setDisable(false);
		peakPowerWorkingPoint = true;
		
		double peakPowerRatioNow = peakPowerRatio;
		
		CostAnalysisToolController.this.clearValues();
		
		CostAnalysisToolController.this.loadSQL();
		
		peakPowerRatio = peakPowerRatioNow;

		if (peakPowerRatio == 0) {
			peakPowerRatio = 1;
		}
		peakPowerInput.setText(String.valueOf(peakPowerRatio));

		peakPowerInput.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo && inputLeftActiveMaterial != null) {
				peakPowerRatio = Double.valueOf(newValue);
			}
		});

	}

	public void uncheckPeakPower() {
		checkPeakPower.setDisable(false);
		checkPeakPower.setSelected(false);
		peakPowerInput.setDisable(true);

		peakPowerWorkingPoint = false;
		peakPowerInput.setText(null);
	}



	public void checkVoltageEfficiency() {

//		basisInputLeftActiveMaterial = inputLeftActiveMaterial;
//		basisInputLeftSolvent = inputLeftSolvent;
//		basisLeftSaltCombinationSelection = leftSaltCombinationSelection;
//		basisInputLeftSalt = inputLeftSalt;
//		basisInputLeftSaltConc = inputLeftSaltConc;
//
//		basisInputRightActiveMaterial = inputRightActiveMaterial;
//		basisInputRightSolvent = inputRightSolvent;
//		basisRightSaltCombinationSelection = rightSaltCombinationSelection;
//		basisInputRightSalt = inputRightSalt;
//		basisInputRightSaltConc = inputRightSaltConc;
//
//		basisInputStackSystem = inputStackSystem;
//		basisInputStackElectrode = inputStackElectrode;
//		basisInputStackMembrane = inputStackMembrane;

		checkPeakPower.setSelected(false);
		checkVoltageEfficiency.setSelected(true);
		checkCurrentDensity.setSelected(false);
		checkSpecPower.setSelected(false);

		checkVoltageEfficiency.setDisable(true);
		checkVoltageEfficiency.setStyle("-fx-opacity: 1");
		checkCurrentDensity.requestFocus();

		voltageEfficiencyInput.setDisable(false);
		voltageEfficiencyWorkingPoint = true;
		
		double efficiencyVoltDischargeNow = efficiencyVoltDischarge;
		
		CostAnalysisToolController.this.clearValues();
		
		CostAnalysisToolController.this.loadSQL();
		
		efficiencyVoltDischarge = efficiencyVoltDischargeNow;

		if (efficiencyVoltDischarge == 0) {
			efficiencyVoltDischarge = 0.916;
		}
		voltageEfficiencyInput.setText(String.valueOf(efficiencyVoltDischarge));

		voltageEfficiencyInput.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo && inputLeftActiveMaterial != null) {
				txtfdEfficiencyVoltDischarge.setText(newValue);
//				custom = true;
//				comboLeftActiveMaterial.setValue(inputLeftActiveMaterial+" - volt. eff. mode");
//				custom = false;
			}
		});
	}

	public void uncheckVoltageEfficiency() {
		checkVoltageEfficiency.setDisable(false);
		checkVoltageEfficiency.setSelected(false);
		voltageEfficiencyInput.setDisable(true);

		voltageEfficiencyWorkingPoint = false;
		voltageEfficiencyInput.setText("");

//		if(basisInputLeftActiveMaterial != null && inputLeftActiveMaterial != "custom") {
//			comboLeftActiveMaterial.setValue(basisInputLeftActiveMaterial);
//			comboLeftSolvent.setValue(basisInputLeftSolvent);
//			comboLeftSalt.setValue(basisLeftSaltCombinationSelection);
//
//			comboRightActiveMaterial.setValue(basisInputRightActiveMaterial);
//			comboRightSolvent.setValue(basisInputRightSolvent);
//			comboRightSalt.setValue(basisRightSaltCombinationSelection);
//
//			comboStack.setValue(basisInputStackSystem+", "+basisInputStackElectrode+", "+basisInputStackMembrane);
//		}
	}


	public void checkCurrentDensity() {
		checkPeakPower.setSelected(false);
		checkVoltageEfficiency.setSelected(false);
		checkCurrentDensity.setSelected(true);
		checkSpecPower.setSelected(false);

		checkCurrentDensity.setDisable(true);
		checkCurrentDensity.setStyle("-fx-opacity: 1");
		checkPeakPower.requestFocus();

		currentDensityInput.setDisable(false);
		currentDensityWorkingPoint = true;
		
		double currentDensityDischargeNow = currentDensityDischarge;
		
		CostAnalysisToolController.this.clearValues();
		
		CostAnalysisToolController.this.loadSQL();
		
		currentDensityDischarge = currentDensityDischargeNow;

		currentDensityInput.setText(String.valueOf(currentDensityDischarge));
		currentDensityInput.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo && inputLeftActiveMaterial != null) {
				txtfbCurrentDensityDischarge.setText(newValue);
			}
		});
	}

	public void uncheckCurrentDensity() {
		checkCurrentDensity.setDisable(false);
		checkCurrentDensity.setSelected(false);
		currentDensityInput.setDisable(true);

		currentDensityWorkingPoint = false;
		currentDensityInput.setText(null);
	}



	public void checkSpecPower() {
		checkPeakPower.setSelected(false);
		checkVoltageEfficiency.setSelected(false);
		checkCurrentDensity.setSelected(false);
		checkSpecPower.setSelected(true);

		checkSpecPower.setDisable(true);
		checkSpecPower.setStyle("-fx-opacity: 1");
		checkPeakPower.requestFocus();

		specPowerInput.setDisable(false);
		specPowerWorkingPoint = true;
		
		double specificPowerDischargeInputNow = specificPowerDischarge;
		
		CostAnalysisToolController.this.clearValues();
		
		CostAnalysisToolController.this.loadSQL();
		
		specificPowerDischargeInput = specificPowerDischargeInputNow;

		if (specificPowerDischargeInput == 0) {
			specificPowerDischargeInput = 0.1;
		}
		specPowerInput.setText(String.valueOf(specificPowerDischargeInput));

		specPowerInput.textProperty().addListener((observable, oldValue, newValue) -> {
//			if (!calculationCombo && inputLeftActiveMaterial != null) {
				specificPowerDischargeInput = Double.valueOf(newValue);
//			}
		});

	}

	public void uncheckSpecPower() {
		checkSpecPower.setDisable(false);
		checkSpecPower.setSelected(false);
		specPowerInput.setDisable(true);

		specPowerWorkingPoint = false;
		specPowerInput.setText("");
	}



	public void checkOhmicMode() {
		checkOhmicMode.setSelected(true);
		ohmicMode = true;
		checkPeakPower.setDisable(false);
		checkPeakPower.setStyle("-fx-opacity: 1");
		checkVoltageEfficiency.setDisable(false);
		checkVoltageEfficiency.setStyle("-fx-opacity: 1");
		checkSpecPower.setDisable(false);
		checkSpecPower.setStyle("-fx-opacity: 1");
	}


	public void uncheckOhmicMode() {
		ohmicMode = false;
		checkCurrentDensity.setSelected(true);
		checkPeakPower.setDisable(true);
		checkPeakPower.setStyle("-fx-opacity: 0.5");
		checkVoltageEfficiency.setDisable(true);
		checkVoltageEfficiency.setStyle("-fx-opacity: 0.5");
		checkSpecPower.setDisable(true);
		checkSpecPower.setStyle("-fx-opacity: 0.5");
	}


	public void checkChargeTransferMode() {
		checkChargeTransferMode.setSelected(true);
		chargeTransferMode = true;

	}


	public void uncheckChargeTransferMode() {
		chargeTransferMode = false;

	}


	public void checkTransportationLimitationMode() {
		checkTransportationLimitationMode.setSelected(true);
		transportationLimitationMode = true;

	}


	public void uncheckTransportationLimitationMode() {
		transportationLimitationMode = false;

	}


	private void changeLabelTitle() {
		if (standardSelected) {
			labelLeft.setText("Active material");
			labelRight.setText("Standard");

			comboRightActiveMaterial.setDisable(true);
			comboRightSolvent.setDisable(true);
			comboRightSalt.setDisable(true);

			comboRightActiveMaterial.setItems(null);

		} else {
			labelLeft.setText("Anolyte");
			labelRight.setText("Catholyte");

			comboRightActiveMaterial.setDisable(false);
			comboRightSolvent.setDisable(false);
			comboRightSalt.setDisable(false);
			
			comboRightActiveMaterial.setValue("");
			comboRightSolvent.setValue("");
			comboRightSalt.setValue("");

			CostAnalysisToolController.this.populateComboRightActiveMaterial();
		}
	}


	private void populateComboLeftActiveMaterial() {
		CostAnalysisToolController.this.checkSelection();
		comboLeftActiveMaterial.setEditable(true);
		try {
			ResultSet res = Database.selectData("activeMaterial");

			ObservableList<String> dataABBREVIATION = FXCollections.observableArrayList();
	    	while (res.next()) {
	    		dataABBREVIATION.add(res.getString("ABBREVIATION"));
	    	}
			comboLeftActiveMaterial.setItems(dataABBREVIATION);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}


	private void populateComboRightActiveMaterial() {
		CostAnalysisToolController.this.checkSelection();
		comboRightActiveMaterial.setEditable(true);
		try {
			ResultSet res = Database.selectData("activeMaterial");

			ObservableList<String> dataABBREVIATION = FXCollections.observableArrayList();
			while (res.next()) {
				dataABBREVIATION.add(res.getString("ABBREVIATION"));
			}
			comboRightActiveMaterial.setItems(dataABBREVIATION);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}


	@FXML
	public void populateComboLeftSolvent(ActionEvent event) {
		CostAnalysisToolController.this.checkSelection();
		try {

			CostAnalysisToolController.this.clearValues();

			comboLeftSolvent.getSelectionModel().clearSelection();

			ResultSet res = Database.selectData("activeMaterial");

			ObservableList<String> dataSolvent = FXCollections.observableArrayList();

			String leftActiveMaterialSelection = comboLeftActiveMaterial.getSelectionModel().getSelectedItem().toString();

			while (res.next()) {
				if (res.getString("ABBREVIATION") == leftActiveMaterialSelection) {
					String dataSolventCombination =  res.getString("Solvent");
					dataSolvent.add(dataSolventCombination);
				}
			}

			comboLeftSolvent.setItems(dataSolvent);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}


	@FXML
	public void populateComboRightSolvent(ActionEvent event) {
		CostAnalysisToolController.this.checkSelection();
		try {

			CostAnalysisToolController.this.clearValues();

			comboRightSolvent.getSelectionModel().clearSelection();

			ResultSet res = Database.selectData("activeMaterial");

			ObservableList<String> dataSolvent = FXCollections.observableArrayList();

			String rightActiveMaterialSelection = comboRightActiveMaterial.getSelectionModel().getSelectedItem().toString();

			while (res.next()) {
				if (res.getString("ABBREVIATION") == rightActiveMaterialSelection) {
					String dataSolventCombination =  res.getString("Solvent");
					dataSolvent.add(dataSolventCombination);
				}
			}

			comboRightSolvent.setItems(dataSolvent);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}


	@FXML
	public void populateComboLeftSalt (ActionEvent event) {
		CostAnalysisToolController.this.checkSelection();

		try {
			comboLeftSalt.getSelectionModel().clearSelection();

			ResultSet res = Database.selectData("activeMaterial");

			ObservableList<String> dataSalt = FXCollections.observableArrayList();

			String leftActiveMaterialSelection = comboLeftActiveMaterial.getSelectionModel().getSelectedItem().toString();
			String leftSolventSelection = comboLeftSolvent.getSelectionModel().getSelectedItem().toString();

			while (res.next()) {
				if (res.getString("ABBREVIATION") == leftActiveMaterialSelection &&  res.getString("Solvent") == leftSolventSelection) {
					String dataSaltCombination =  res.getString("Salt") +", "+ res.getString("Saltc").toString() +"M";
					dataSalt.add(dataSaltCombination);
				}
			}

			comboLeftSalt.setItems(dataSalt);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}


	@FXML
	public void populateComboRightSalt (ActionEvent event) {
		CostAnalysisToolController.this.checkSelection();

		try {
			comboRightSalt.getSelectionModel().clearSelection();

			ResultSet res = Database.selectData("activeMaterial");

			ObservableList<String> dataSalt = FXCollections.observableArrayList();

			String rightActiveMaterialSelection = comboRightActiveMaterial.getSelectionModel().getSelectedItem().toString();
			String rightSolventSelection = comboRightSolvent.getSelectionModel().getSelectedItem().toString();

			while (res.next()) {
				if (res.getString("ABBREVIATION") == rightActiveMaterialSelection &&  res.getString("Solvent") == rightSolventSelection) {
					String dataSaltCombination =  res.getString("Salt") +", "+ res.getString("Saltc").toString() +"M";
					dataSalt.add(dataSaltCombination);
				}
			}

			comboRightSalt.setItems(dataSalt);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}


	public void populateComboStack () {
		
		String data1 = "0.06 m² | Nafion";
		String data2 = "0.06 m² | SizeSelective";
//		String data3 = "2.76 m² | Nafion";
//		String data4 = "2.76 m² | SizeSelective";
		ObservableList<String> dataStack = FXCollections.observableArrayList();
		
		dataStack.add(data1);
		dataStack.add(data2);
//		dataStack.add(data3);
//		dataStack.add(data4);
		
		comboStack.setItems(dataStack);	
	}

	@FXML
	public void selectComboStack(ActionEvent event) {
		if(!comboLeftActiveMaterial.getValue().equals("custom")) {
			CostAnalysisToolController.this.checkSelection();
			CostAnalysisToolController.this.clearValues();
			CostAnalysisToolController.this.clearStackValues();
			CostAnalysisToolController.this.loadSQL();
		}

	}
	@FXML
	public void selectComboLeftSalt(ActionEvent event) {
		if(!comboLeftActiveMaterial.getValue().equals("custom")) {
			CostAnalysisToolController.this.checkSelection();
			CostAnalysisToolController.this.clearValues();
			CostAnalysisToolController.this.loadSQL();
		}
	}
	@FXML
	public void selectComboRightSalt(ActionEvent event) {
		if(!comboLeftActiveMaterial.getValue().equals("custom")) {
			CostAnalysisToolController.this.checkSelection();
			CostAnalysisToolController.this.clearValues();
			CostAnalysisToolController.this.loadSQL();
		}
	}
	

	private void populateComboExportSelection() {
		comboExportSelection.setEditable(true);
		ObservableList<String> dataExportSelection = FXCollections.observableArrayList();

    	dataExportSelection.add("all_details_Nafion");
    	dataExportSelection.add("all_details_SizeSelective");
    	dataExportSelection.add("organic molecules_best case");
    	dataExportSelection.add("organic molecules_worst case");
    	dataExportSelection.add("vanadium_best case");
    	dataExportSelection.add("vanadium_worst case");

    	comboExportSelection.setItems(dataExportSelection);
	}
	
	
	private void checkSelection() {
		if (standardSelected) {
			if (!comboLeftActiveMaterial.getValue().equals("") && !comboLeftSolvent.getValue().equals("") && !comboLeftSalt.getValue().equals("") && !comboStack.getValue().equals("")) {
				materialSelected = true;
			} else {
				materialSelected = false;
			}
		} else if (!standardSelected) {
			
			if (!comboLeftActiveMaterial.getValue().equals("") && !comboLeftSolvent.getValue().equals("") && !comboLeftSalt.getValue().equals("") && !comboStack.getValue().equals("") && !comboRightActiveMaterial.getValue().equals("") && !comboRightSolvent.getValue().equals("") && !comboRightSalt.getValue().equals("")) {
				materialSelected = true;
			} else {
				materialSelected = false;
			}
		}
		
	}
	

//	TODO: cell potential, c solv / mol L^-1, Fixed textFields on pane for results, Potential value for each active material
	public void reloadValues () {
		txtfbTimeDischarge.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				timeDischarge = Double.valueOf(newValue);
				if (energyCapacity != 0 && timeDischarge != 0) {
					powerDischarge = energyCapacity/timeDischarge;
					txtfbPowerDischarge.setText(String.valueOf(powerDischarge));
				}
				if (powerDischarge != 0 && timeDischarge != 0) {
					energyCapacity = powerDischarge * timeDischarge;
					txtfbEnergyCapacity.setText(String.valueOf(energyCapacity));
				}
			}
		});
		txtfbPowerDischarge.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				powerDischarge = Double.valueOf(newValue);
				if (energyCapacity != 0 && powerDischarge != 0) {
					timeDischarge = energyCapacity/powerDischarge;
					txtfbTimeDischarge.setText(String.valueOf(timeDischarge));
				}
				if (powerDischarge != 0 && timeDischarge != 0) {
					energyCapacity = powerDischarge * timeDischarge;
					txtfbEnergyCapacity.setText(String.valueOf(energyCapacity));
				}
			}
		});
		txtfbEnergyCapacity.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				energyCapacity = Double.valueOf(newValue);
				if (energyCapacity != 0 && timeDischarge != 0) {
					powerDischarge = energyCapacity/timeDischarge;
					txtfbPowerDischarge.setText(String.valueOf(powerDischarge));
				}
				if (energyCapacity != 0 && powerDischarge != 0) {
					timeDischarge = energyCapacity/powerDischarge;
					txtfbTimeDischarge.setText(String.valueOf(timeDischarge));
				}
			}
		});
//		electrolyte:
		txtfdAnolyteActMatCost.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
			    anolyteActMatCost = Double.valueOf(newValue);
			    CostAnalysisToolController.this.uncheckCombo();
			}
		});
		txtfdAnolyteActMatCoeff.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				anolyteActMatCoeff = Double.valueOf(newValue);
			    CostAnalysisToolController.this.uncheckCombo();
			}
		});
		txtfdAnolyteActMatMolMass.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				anolyteActMatMolMass = Double.valueOf(newValue);
			    CostAnalysisToolController.this.uncheckCombo();
			}
		});
		txtfdAnolyteSocRange.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				anolyteSocRange = Double.valueOf(newValue);
			    CostAnalysisToolController.this.uncheckCombo();
			}
		});
		txtfdAnolyteActMatNumberElectron.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				anolyteActMatNumberElectron = Integer.valueOf(newValue);
			    CostAnalysisToolController.this.uncheckCombo();
			}
		});
		txtfdAnolyteActMatNumberProton.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				anolyteActMatNumberProton = Integer.valueOf(newValue);
				CostAnalysisToolController.this.uncheckCombo();
			}
		});
		txtfdAnolyteActMatSol.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				anolyteActMatSol = Double.valueOf(newValue);
			    CostAnalysisToolController.this.uncheckCombo();
			}
		});
		txtfdAnolyteSaltMolMass.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				anolyteSaltMolMass = Double.valueOf(newValue);
			    CostAnalysisToolController.this.uncheckCombo();
			}
		});
		txtfdAnolyteSaltSol.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				anolyteSaltSol = Double.valueOf(newValue);
			    CostAnalysisToolController.this.uncheckCombo();
			}
		});
		txtfdAnolytePotential.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				anolyteReversiblePotential = Double.valueOf(newValue);
				CostAnalysisToolController.this.uncheckCombo();
			}
		});

		txtfdCatholyteActMatCost.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				catholyteActMatCost = Double.valueOf(newValue);
				CostAnalysisToolController.this.uncheckCombo();
			}
		});
		txtfdCatholyteActMatCoeff.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				catholyteActMatCoeff = Double.valueOf(newValue);
				CostAnalysisToolController.this.uncheckCombo();
			}
		});
		txtfdCatholyteActMatMolMass.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				catholyteActMatMolMass = Double.valueOf(newValue);
				CostAnalysisToolController.this.uncheckCombo();
			}
		});
		txtfdCatholyteSocRange.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				catholyteSocRange = Double.valueOf(newValue);
				CostAnalysisToolController.this.uncheckCombo();
			}
		});
		txtfdCatholyteActMatNumberElectron.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				catholyteActMatNumberElectron = Integer.valueOf(newValue);
				CostAnalysisToolController.this.uncheckCombo();
			}
		});
		txtfdCatholyteActMatNumberProton.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				catholyteActMatNumberProton = Integer.valueOf(newValue);
				CostAnalysisToolController.this.uncheckCombo();
			}
		});
		txtfdCatholyteActMatSol.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				catholyteActMatSol = Double.valueOf(newValue);
				CostAnalysisToolController.this.uncheckCombo();
			}
		});
		txtfdCatholyteSaltMolMass.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				catholyteSaltMolMass = Double.valueOf(newValue);
				CostAnalysisToolController.this.uncheckCombo();
			}
		});
		txtfdCatholyteSaltSol.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				catholyteSaltSol = Double.valueOf(newValue);
				CostAnalysisToolController.this.uncheckCombo();
			}
		});
		txtfdCatholytePotential.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				catholyteReversiblePotential = Double.valueOf(newValue);
				CostAnalysisToolController.this.uncheckCombo();
			}
		});

		txtfdReversibleCellVoltageEl.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				reversibleCellVoltageEl = Double.valueOf(newValue);
				CostAnalysisToolController.this.uncheckCombo();
			}
		});
		txtfdRavg.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				ravg = Double.valueOf(newValue);
				CostAnalysisToolController.this.uncheckCombo();
			}
		});
		txtfdBavg.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				bavg = Double.valueOf(newValue);
				CostAnalysisToolController.this.uncheckCombo();
			}
		});
		txtfdEfficiencySysDischarge.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				efficiencySysDischarge = Double.valueOf(newValue);
				CostAnalysisToolController.this.uncheckCombo();
			}
		});
		txtfdEfficiencyVoltDischarge.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				efficiencyVoltDischarge = Double.valueOf(newValue);
				if (!voltageEfficiencyWorkingPoint) {
					CostAnalysisToolController.this.uncheckCombo();
				}
			}
		});
		txtfdEfficiencyCoulombicRoundTrip.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				efficiencyCoulombicRoundTrip = Double.valueOf(newValue);
				CostAnalysisToolController.this.uncheckCombo();
			}
		});

		txtfdSaltCost.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				saltCost = Double.valueOf(newValue);
				CostAnalysisToolController.this.uncheckCombo();
			}
		});
		txtfdSolventCost.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				solventCost = Double.valueOf(newValue);
				CostAnalysisToolController.this.uncheckCombo();
			}
		});
		txtfdSolventDensity.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				solventDensity = Double.valueOf(newValue);
				CostAnalysisToolController.this.uncheckCombo();
			}
		});
		txtfdTemp.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				temp = Double.valueOf(newValue);
				CostAnalysisToolController.this.uncheckCombo();
			}
		});
		txtfdPH.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				pHElectrolyte = Double.valueOf(newValue);
				reversibleVoltage = 0;
				catholyteReversiblePotential = 0;
				anolyteReversiblePotential = 0;
				CostAnalysisToolController.this.uncheckCombo();
			}
		});

		txtfdAnolyteTankCost.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				anolyteTankCost = Double.valueOf(newValue);
				CostAnalysisToolController.this.uncheckCombo();
			}
		});
		txtfdCatholyteTankCost.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				catholyteTankCost = Double.valueOf(newValue);
				CostAnalysisToolController.this.uncheckCombo();
			}
		});
		txtfdTankFillLevel.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				tankFillLevel = Double.valueOf(newValue);
				CostAnalysisToolController.this.uncheckCombo();
			}
		});
		txtfdTankCost.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				tankCost = Double.valueOf(newValue);
				CostAnalysisToolController.this.uncheckCombo();
			}
		});

//		power:
		txtfdStackCost.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				stackCost = Double.valueOf(newValue);
				CostAnalysisToolController.this.uncheckCombo();
			}
		});
		txtfbASR.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				asr = Double.valueOf(newValue);
				CostAnalysisToolController.this.uncheckCombo();
			}
		});
		txtfbSocDischarge.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				stateOfCharge = Double.valueOf(newValue);
				CostAnalysisToolController.this.uncheckCombo();
			}
		});
		txtfbCostBOP.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				costBOP = Double.valueOf(newValue);
				CostAnalysisToolController.this.uncheckCombo();
			}
		});
		txtfbCostAdd.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				costAdd = Double.valueOf(newValue);
				CostAnalysisToolController.this.uncheckCombo();
			}
		});
		txtfbCurrentDensityDischarge.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo && currentDensityWorkingPoint) {
				currentDensityDischarge = Double.valueOf(newValue);
				if (!currentDensityWorkingPoint) {
					CostAnalysisToolController.this.uncheckCombo();
				}
			}
		});
		txtfbVoltageDischarge.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				voltageDischarge = Double.valueOf(newValue);
				CostAnalysisToolController.this.uncheckCombo();
			}
		});
		txtfdStandardCellVoltagePow.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				standardCellVoltagePow = Double.valueOf(newValue);
				CostAnalysisToolController.this.uncheckCombo();
			}
		});
		txtfbReversibleVoltage.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				reversibleVoltage = Double.valueOf(newValue);
				CostAnalysisToolController.this.uncheckCombo();
			}
		});
		txtfbChargeTranserOverPotDischarge.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				chargeTranserOverPotDischarge = Double.valueOf(newValue);
				CostAnalysisToolController.this.uncheckCombo();
			}
		});
		txtfbOhmicOverPotDischarge.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				ohmicOverPotDischarge = Double.valueOf(newValue);
				CostAnalysisToolController.this.uncheckCombo();
			}
		});
		txtfbAnolyteReactionRate.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				anolyteReactionRate = Double.valueOf(newValue);
				CostAnalysisToolController.this.uncheckCombo();
			}
		});
		txtfbAnolyteTransferCoeff.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				anolyteTransferCoeff = Double.valueOf(newValue);
				CostAnalysisToolController.this.uncheckCombo();
			}
		});
		txtfbCatholyteReactionRate.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				catholyteReactionRate = Double.valueOf(newValue);
				CostAnalysisToolController.this.uncheckCombo();
			}
		});
		txtfbCatholyteTransferCoeff.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				catholyteTransferCoeff = Double.valueOf(newValue);
				CostAnalysisToolController.this.uncheckCombo();
			}
		});
		txtfbAnolyteExchangeCurrentDensity.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				anolyteExchangeCurrentDensity = Double.valueOf(newValue);
				CostAnalysisToolController.this.uncheckCombo();
			}
		});
		txtfbCatholyteExchangeCurrentDensity.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				catholyteExchangeCurrentDensity = Double.valueOf(newValue);
				CostAnalysisToolController.this.uncheckCombo();
			}
		});
		txtfbAnolyteChargeTranserOverPotDischarge.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				anolyteChargeTranserOverPotDischarge = Double.valueOf(newValue);
				CostAnalysisToolController.this.uncheckCombo();
			}
		});
		txtfbCatholyteChargeTranserOverPotDischarge.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				catholyteChargeTranserOverPotDischarge = Double.valueOf(newValue);
				CostAnalysisToolController.this.uncheckCombo();
			}
		});
		txtfbDiaFiber.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				dFiber = Double.valueOf(newValue);
				CostAnalysisToolController.this.uncheckCombo();
			}
		});
		txtfbFlowVelocity.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				flowVelocity = Double.valueOf(newValue);
				CostAnalysisToolController.this.uncheckCombo();
			}
		});
		txtfbAnolyteDiffusionCoeff.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				anolyteDiffusionCoeff = Double.valueOf(newValue);
				CostAnalysisToolController.this.uncheckCombo();
			}
		});
//		TODO: Add solvent density & viscosity (for catholyte and anolyte side) for custom mode --> currently those parameters are set by the previously selected system 
		txtfbCatholyteDiffusionCoeff.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				catholyteDiffusionCoeff = Double.valueOf(newValue);
				CostAnalysisToolController.this.uncheckCombo();
			}
		});
		txtfbAnolyteConcentrationOverPotDischarge.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				anolyteConcentrationOverPotDischarge = Double.valueOf(newValue);
				CostAnalysisToolController.this.uncheckCombo();
			}
		});
		txtfbCatholyteConcentrationOverPotDischarge.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				catholyteConcentrationOverPotDischarge = Double.valueOf(newValue);
				CostAnalysisToolController.this.uncheckCombo();
			}
		});
		txtfdCellArea.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				cellArea = Double.valueOf(newValue);
				CostAnalysisToolController.this.uncheckCombo();
			}
		});
		txtfdTotalArea.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				totalArea = Double.valueOf(newValue);
				CostAnalysisToolController.this.uncheckCombo();
			}
		});
		txtfdNumberCellsPerStack.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				numberCellsPerStack = Double.valueOf(newValue);
				CostAnalysisToolController.this.uncheckCombo();
			}
		});
		txtfdNumberCells.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				numberCells = Double.valueOf(newValue);
				CostAnalysisToolController.this.uncheckCombo();
			}
		});
		txtfdNumberStacks.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				numberStacks = Double.valueOf(newValue);
				CostAnalysisToolController.this.uncheckCombo();
			}
		});
		txtfdReactorCost.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				reactorCost = Double.valueOf(newValue);
				CostAnalysisToolController.this.uncheckCombo();
			}
		});

//		maintenance:
		txtfdOperationalLifetime.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				operationalLifetime = Double.valueOf(newValue);
				CostAnalysisToolController.this.uncheckCombo();
			}
		});
		txtfdInterestRate.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				interestRate = Double.valueOf(newValue);
				CostAnalysisToolController.this.uncheckCombo();
			}
		});
		txtfdAnolyteReplacementFraction.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				anolyteReplacementFraction = Double.valueOf(newValue);
				CostAnalysisToolController.this.uncheckCombo();
			}
		});
		txtfdCatholyteReplacementFraction.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				catholyteReplacementFraction = Double.valueOf(newValue);
				CostAnalysisToolController.this.uncheckCombo();
			}
		});
		txtfdMaxReplacementFraction.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				maxReplacementFraction = Double.valueOf(newValue);
				CostAnalysisToolController.this.uncheckCombo();
			}
		});
		txtfdElectrolyteDegradationNPVCost.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				electrolyteDegradationNPVCost = Double.valueOf(newValue);
				CostAnalysisToolController.this.uncheckCombo();
			}
		});
		txtfdAnolyteReplacementCostMass.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				anolyteReplacementCostMass = Double.valueOf(newValue);
				CostAnalysisToolController.this.uncheckCombo();
			}
		});
		txtfdAnolyteReplacementCost.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				anolyteReplacementCost = Double.valueOf(newValue);
				CostAnalysisToolController.this.uncheckCombo();
			}
		});
		txtfdCatholyteReplacementCostMass.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				catholyteReplacementCostMass = Double.valueOf(newValue);
				CostAnalysisToolController.this.uncheckCombo();
			}
		});
		txtfdCatholyteReplacementCost.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				catholyteReplacementCost = Double.valueOf(newValue);
				CostAnalysisToolController.this.uncheckCombo();
			}
		});
		txtfdElectrolyteOperationalTime.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				electrolyteOperationalTime = Double.valueOf(newValue);
				CostAnalysisToolController.this.uncheckCombo();
			}
		});
		txtfdThreshold.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				threshold = Double.valueOf(newValue);
				CostAnalysisToolController.this.uncheckCombo();
			}
		});
		txtfdReplacementNPVCost.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				replacementNPVCost = Double.valueOf(newValue);
				CostAnalysisToolController.this.uncheckCombo();
			}
		});
		txtfdStackExchangeCost.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				stackExchangeCost = Double.valueOf(newValue);
				CostAnalysisToolController.this.uncheckCombo();
			}
		});
		txtfdStackOperationalTime.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				stackOperationalTime = Double.valueOf(newValue);
				CostAnalysisToolController.this.uncheckCombo();
			}
		});
		txtfdStackExchangeNPVCost.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!calculationCombo) {
				stackExchangeNPVCost = Double.valueOf(newValue);
				CostAnalysisToolController.this.uncheckCombo();
			}
		});
	}


	@SuppressWarnings("unchecked")
	public void uncheckCombo () {
		custom = true;
		
		comboLeftActiveMaterial.setValue("custom");
		comboRightActiveMaterial.setValue("custom");
		comboStack.setValue("custom");

		inputLeftActiveMaterial = "custom";
		inputRightActiveMaterial = "custom";
		inputLeftSalt = "custom";
		inputRightSalt = "custom";
		inputLeftSolvent = "custom";
		inputRightSolvent = "custom";
		inputStackSystem = "custom";
		custom = false;


//		TODO: Maybe remove plots? Or use a counter for title (e.g. custom1, custom2, etc.)
	}


	public void checkBoxListener () {
		checkPeakPower.selectedProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue) {
				checkPeakPower();
			}
			if (!newValue) {
				uncheckPeakPower();
			}
		});
		checkVoltageEfficiency.selectedProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue) {
				checkVoltageEfficiency();
			}
			if (!newValue) {
				uncheckVoltageEfficiency();
			}
		});
		checkCurrentDensity.selectedProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue) {
				checkCurrentDensity();
			}
			if (!newValue) {
				uncheckCurrentDensity();
			}
		});
		checkSpecPower.selectedProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue) {
				checkSpecPower();
			}
			if (!newValue) {
				uncheckSpecPower();
			}
		});
		checkOhmicMode.selectedProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue) {
				checkOhmicMode();
			}
			if (!newValue) {
				uncheckOhmicMode();
			}
		});
		checkChargeTransferMode.selectedProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue) {
				checkChargeTransferMode();
			}
			if (!newValue) {
				uncheckChargeTransferMode();
			}
		});
		checkTransportationLimitationMode.selectedProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue) {
				checkTransportationLimitationMode();
			}
			if (!newValue) {
				uncheckTransportationLimitationMode();
			}
		});
	}



	public void clearValues () {
		if (!custom) {
			anolyteActMatCost = 0;
			anolyteActMatCoeff = 0;
			anolyteActMatMolMass = 0;
			anolyteSocRange = 0;
			anolyteActMatNumberElectron = 0;
			anolyteActMatNumberProton = 0;
			anolyteActMatConc = 0;
			anolyteActMatSol = 0;
			anolyteSaltMolMass = 0;
			anolyteSaltConc = 0;
			anolyteSaltSol = 0;
			anolyteStandardPotential = 0;
			anolyteSaltCost = 0;
			anolyteSolventCost = 0;
			anolyteSolventDensity = 0;

			catholyteActMatCost = 0;
			catholyteActMatCoeff = 0;
			catholyteActMatMolMass = 0;
			catholyteSocRange = 0;
			catholyteActMatNumberElectron = 0;
			catholyteActMatNumberProton = 0;
			catholyteActMatConc = 0;
			catholyteActMatSol = 0;
			catholyteSaltMolMass = 0;
			catholyteSaltConc = 0;
			catholyteSaltSol = 0;
			catholyteStandardPotential = 0;
			catholyteSaltCost = 0;
			catholyteSolventCost = 0;
			catholyteSolventDensity = 0;
			
			SA_SocRange = 0;

			pHActMatAnolyte = 0;
			pHActMatCatholyte = 0;
			pHElectrolyte = 0;
			protonConcActMatAnolyte = 0;
			protonConcActMatCatholyte = 0;
			protonConcElectrolyte = 0;
			temp = rT;

			reversibleCellVoltageEl = 0;
			catholyteReversiblePotential = 0;
			anolyteReversiblePotential = 0;
//			catholyteReversiblePotentialPh0 = 0;
//			anolyteReversiblePotentialPh0 = 0;
			ravg = 0;
			bavg = 0;
			efficiencySysDischarge = 0;
			if (!voltageEfficiencyWorkingPoint) {
				efficiencyVoltDischarge = 0;
			}
			efficiencyCoulombicRoundTrip = 0;

			saltCost = 0;
			solventCost = 0;
			solventDensity = 0;

			anolyteTankCost = 0;
			catholyteTankCost = 0;
			tankFillLevel = 0;
			tankCost = 0;

			//power:
//			stackCost = 0;
//
//			separatorCost = 0;
//			electrodeCost = 0;
//			bipolarPlateCost = 0;
////			cellFramesCost = 0;
////			sealsCost = 0;
//			cellFramesSealsCost = 0;
//			currentCollectorCost = 0;
//			stackFrameCost = 0;
//			assemblyCost = 0;

//			separatorStackNumber = 0;
//			electrodeStackNumber = 0;
//			bipolarPlateStackNumber = 0;
////			cellFramesStackNumber = 0;
////			sealsStackNumber = 0;
//			cellFramesSealsStackNumber = 0;
//			currentCollectorStackNumber = 0;
//			stackFrameStackNumber = 0;
//			componentsNumber = 0;

//			separatorStackCost = 0;
//			electrodeStackCost = 0;
//			bipolarPlateStackCost = 0;
////			cellFramesStackCost = 0;
////			sealsStackCost = 0;
//			cellFramesSealsStackCost = 0;
//			currentCollectorStackCost = 0;
//			stackFrameStackCost = 0;
//			assemblyStackCost = 0;
//			stackCostTotal = 0;

//			asr = 0;
			timeDischarge = 0;
			powerDischarge = 0;
			if (!specPowerWorkingPoint) {
				specificPowerDischarge = 0;
			}
			if (!peakPowerWorkingPoint) {
				peakPowerRatio = 0;
			}
			energyCapacity = 0;
			stateOfCharge = 0;

			stEcell = 0;
			z = 0;
			if (!currentDensityWorkingPoint) {
				currentDensityDischarge = 0;
			}
			voltageDischarge = 0;
			standardCellVoltagePow = 0;
			reversibleVoltage = 0;
			chargeTranserOverPotDischarge = 0;
			ohmicOverPotDischarge = 0;
			concentrationOverPotDischarge = 0;

			anolyteReactionRate = 0;
			anolyteTransferCoeff = 0;
			catholyteReactionRate = 0;
			catholyteTransferCoeff = 0;
			anolyteExchangeCurrentDensity = 0;
			catholyteExchangeCurrentDensity = 0;
			anolyteChargeTranserOverPotDischarge = 0;
			catholyteChargeTranserOverPotDischarge = 0;

			dFiber = 0;
			flowVelocity = 0;
//			DOx = 0;
//			DRed = 0;
			anolyteDiffusionCoeff = 0;
			catholyteDiffusionCoeff = 0;
			anolyteSolventViscosity = 0;
			catholyteSolventViscosity = 0;
			
			catholyteConcentrationOverPotDischarge = 0;
			anolyteConcentrationOverPotDischarge = 0;
			concentrationOverPotDischarge = 0;

			cellArea = 0;
			totalArea = 0;
			numberCells = 0;
			numberCellsPerStack = 0;
			numberStacks = 0;
			reactorCost = 0;

			costBOP = 0;
			costAdd = 0;

			//maintenance:
			operationalLifetime = 0;
			interestRate = 0;
			anolyteReplacementFraction = 0;
			catholyteReplacementFraction = 0;
			maxReplacementFraction = 0;
			electrolyteDegradationNPVCost = 0;

			anolyteReplacementCostMass = 0;
			anolyteReplacementCost = 0;
			catholyteReplacementCostMass = 0;
			catholyteReplacementCost = 0;
			electrolyteOperationalTime = 0;
			threshold = 0;
			replacementNPVCost = 0;

			stackExchangeCost = 0;
			stackOperationalTime = 0;
			stackExchangeNPVCost = 0;

			//results:
			anolyteCostTotal = 0;
			anolyteCostTotalKAh = 0;

			catholyteCostTotal = 0;
			catholyteCostTotalKAh = 0;

			energyDensity = 0;
			anolyteEnergyDensityKg = 0;
			catholyteEnergyDensityKg = 0;
			energyDensityKg = 0;

			costElectrolyte = 0;
			costElectrolyteKWh = 0;
			costPower = 0;
			costMaintenance = 0;

			costCapital = 0;
			
		}
	}
	public void clearStackValues () {
		if (!custom) {
			
			//power:
			stackCost = 0;

			separatorCost = 0;
			electrodeCost = 0;
			bipolarPlateCost = 0;
//			cellFramesCost = 0;
//			sealsCost = 0;
			cellFramesSealsCost = 0;
			currentCollectorCost = 0;
			stackFrameCost = 0;
			assemblyCost = 0;
			
			separatorStackNumber = 0;
			electrodeStackNumber = 0;
			bipolarPlateStackNumber = 0;
//			cellFramesStackNumber = 0;
//			sealsStackNumber = 0;
			cellFramesSealsStackNumber = 0;
			currentCollectorStackNumber = 0;
			stackFrameStackNumber = 0;
			componentsNumber = 0;
			
			separatorStackCost = 0;
			electrodeStackCost = 0;
			bipolarPlateStackCost = 0;
//			cellFramesStackCost = 0;
//			sealsStackCost = 0;
			cellFramesSealsStackCost = 0;
			currentCollectorStackCost = 0;
			stackFrameStackCost = 0;
			assemblyStackCost = 0;
			stackCostTotal = 0;
			
			asr = 0;
			
		}
	}




//	public void checkPotential () {
//		anolyteStandard = false;
//		catholyteStandard = false;
//
//		if (Math.abs(leftReversiblePotential - standardElimitcathode) > Math.abs(leftReversiblePotential - standardElimitanode) ) {
//			catholyteStandard = true;
//			} else {
//				anolyteStandard = false;
//			}
//	}



//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private void loadSQL() {
		
		CostAnalysisToolController.this.checkSelection();
		
		if (materialSelected) {
			leftAqueous = false;
			rightAqueous = false;
			leftOrganicSolvent = false;
			rightOrganicSolvent = false;
			anolyteKred = true;
			catholyteKox = true;

			try {
				CostAnalysisToolController.this.getSelectedItems();
				CostAnalysisToolController.this.solventTest();
				CostAnalysisToolController.this.createObjActiveMaterial();
				CostAnalysisToolController.this.createObjSolvent();
				CostAnalysisToolController.this.createObjElectrolyte();
				CostAnalysisToolController.this.createObjStack();
				CostAnalysisToolController.this.createObjCostsSalt();
				CostAnalysisToolController.this.createObjCostsSolvent();
				CostAnalysisToolController.this.defineAnolyteCatholyte();

			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
	}



	private void getSelectedItems() {
		//get selection active material:
		inputLeftActiveMaterial = comboLeftActiveMaterial.getSelectionModel().getSelectedItem().toString();
		System.out.println("Active material (left) " + inputLeftActiveMaterial + " selected");
		// get selected solvent + Salt + concentration:
		inputLeftSolvent = comboLeftSolvent.getSelectionModel().getSelectedItem().toString();
		System.out.println("Solvent (left) " + inputLeftSolvent + " selected");
		// get selected salt + concentration:
		leftSaltCombinationSelection = comboLeftSalt.getSelectionModel().getSelectedItem().toString();
		String[] leftSaltCombinationSelectionSplit = leftSaltCombinationSelection.split(", ");
		inputLeftSalt = leftSaltCombinationSelectionSplit[0];
		System.out.println("Salt (left) " + inputLeftSalt + " selected");
		String leftSaltcSelectionSplit[] = leftSaltCombinationSelectionSplit[1].split("M");
		inputLeftSaltConc = Double.valueOf(leftSaltcSelectionSplit[0]);

		//get stack input:
		if (!comboStack.getSelectionModel().isEmpty()) {
			inputStackSystem = comboStack.getSelectionModel().getSelectedItem().toString();
		}


		// get right side if no standard selected:
		if (!standardSelected) {
			//get selection active material:
			inputRightActiveMaterial = comboRightActiveMaterial.getSelectionModel().getSelectedItem().toString();
			System.out.println("Active material (right) " + inputRightActiveMaterial + " selected");
			// get selected solvent + Salt + concentration:
			inputRightSolvent = comboRightSolvent.getSelectionModel().getSelectedItem().toString();
			System.out.println("Solvent (right) " + inputRightSolvent + " selected");
			// get selected salt + concentration:
			rightSaltCombinationSelection = comboRightSalt.getSelectionModel().getSelectedItem().toString();
			String[] rightSaltCombinationSelectionSplit = rightSaltCombinationSelection.split(", ");
			inputRightSalt = rightSaltCombinationSelectionSplit[0];
			System.out.println("Salt (right) " + inputRightSalt + " selected");
			String rightSaltcSelectionSplit[] = rightSaltCombinationSelectionSplit[1].split("M");
			inputRightSaltConc = Double.valueOf(rightSaltcSelectionSplit[0]);
		}
		System.out.println("standard counter half cell selected");
	}


	private void stackSelection() {
		CostAnalysisToolController.this.clearStackValues();
		
		objStackSelection.preselection.set(comboStack.getValue().toString());
		objStackSelection.cellArea.set(cellArea);
		objStackSelection.numberCellsPerStack.set(numberCellsPerStack);
		
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/de/dominikemmel/reflowlab/controller/costanalysistool/fxml/stackSelectionPane.fxml"));
			Parent rootStackSelectionPane = loader.load();
			loader.setControllerFactory((Class<?> controllerType) -> {
				if (controllerType == StackSelectionPaneController.class) {
					StackSelectionPaneController controller = new StackSelectionPaneController();
					controller.setPreselection(objStackSelection);
					controller.getReturn();
					return controller;
				} else {
					try {
						return controllerType.newInstance();
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				}
			});
			
			StackSelectionPaneController controller = loader.<StackSelectionPaneController>getController();
			controller.setPreselection(objStackSelection);
			
			controller.getReturn();
			
			rootStackSelectionPane.getStylesheets().add(getClass().getResource("/de/dominikemmel/reflowlab/style/reflowlabStyle1.css").toExternalForm());
			Stage stage = new Stage();
			stage.setScene(new Scene(rootStackSelectionPane));
			
			stage.show();
			
			stage.setTitle("ReFlowLab - Stack Selection");
		
			
			Stage costAnalysisStage = (Stage) AnchorPane1.getScene().getWindow();
			costAnalysisStage.hide();
			
			stage.setOnCloseRequest(event2 -> {
				costAnalysisStage.show();
				transerStackSelection(objStackSelection);
			});
			stage.setOnHidden(event3 -> {
				costAnalysisStage.show();
				transerStackSelection(objStackSelection);
			});
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	private void transerStackSelection(ObjStackSelection objStackSelection) {
//		preselection = objStackSelection.preselection;
		
		numberCellsPerStack = objStackSelection.numberCellsPerStack.getValue();
		cellArea = objStackSelection.cellArea.getValue();
		asr = objStackSelection.asr.getValue();
		
		separatorCost = objStackSelection.separatorCost.getValue();
		electrodeCost = objStackSelection.electrodeCost.getValue();
		bipolarPlateCost = objStackSelection.bipolarPlateCost.getValue();
		cellFramesSealsCost = objStackSelection.cellFramesSealsCost.getValue();
		currentCollectorCost = objStackSelection.currentCollectorCost.getValue();
		stackFrameCost = objStackSelection.stackFrameCost.getValue();
		assemblyCost = objStackSelection.assemblyCost.getValue();
		
		separatorStackNumber = objStackSelection.separatorStackNumber.getValue();
		electrodeStackNumber = objStackSelection.electrodeStackNumber.getValue();
		bipolarPlateStackNumber = objStackSelection.bipolarPlateStackNumber.getValue();
		cellFramesSealsStackNumber = objStackSelection.cellFramesSealsStackNumber.getValue();
		currentCollectorStackNumber = objStackSelection.currentCollectorStackNumber.getValue();
		stackFrameStackNumber = objStackSelection.stackFrameStackNumber.getValue();
		componentsNumber = objStackSelection.componentsNumber.getValue();
		
		separatorStackCost = objStackSelection.separatorStackCost.getValue();
		electrodeStackCost = objStackSelection.electrodeStackCost.getValue();
		bipolarPlateStackCost = objStackSelection.bipolarPlateStackCost.getValue();
		cellFramesSealsStackCost = objStackSelection.cellFramesSealsStackCost.getValue();
		currentCollectorStackCost = objStackSelection.currentCollectorStackCost.getValue();
		stackFrameStackCost = objStackSelection.stackFrameStackCost.getValue();
		assemblyStackCost = objStackSelection.assemblyStackCost.getValue();
		
		stackCostTotal = objStackSelection.stackCostTotal.getValue();
		stackCost = objStackSelection.stackCost.getValue();
	}
	

	private void solventTest() throws ClassNotFoundException, SQLException {
		//Test if selected solvent is from aqueous or organic list:
//		TODO: change inorganic to aqueous!!!
		con = Database.getConnection("solventInorganic");
		//left:
		Statement stateLeft = con.createStatement();
		ResultSet resLeft = stateLeft.executeQuery("SELECT *"
				+ " FROM solventInorganic"
				+ " WHERE SolventStructuralFormula = '" + inputLeftSalt + "'");
		if (resLeft.next()) {
			leftAqueous = true;
		} else {
			leftAqueous = false;
		}

		con = Database.getConnection("solventOrganic");
		Statement stateLeft1 = con.createStatement();
		ResultSet resLeft1 = stateLeft1.executeQuery("SELECT *"
				+ " FROM solventOrganic"
				+ " WHERE SolventName = '" + inputLeftSolvent + "'");
		if (resLeft1.next()) {
			leftOrganicSolvent = true;
		} else {
			leftOrganicSolvent = false;
		}
		if (!leftAqueous && !leftOrganicSolvent) {
			System.out.println("left solvent is not in list!");
		}
		if (leftAqueous == true && leftOrganicSolvent == true) {
			System.out.println("left solvent is on both lists?!");
		}


		//right:
		Statement stateRight = con.createStatement();
		ResultSet resRight = stateRight.executeQuery("SELECT *"
				+ " FROM solventInorganic"
				+ " WHERE SolventStructuralFormula = '" + inputRightSalt + "'");
		if (resRight.next()) {
			rightAqueous = true;
		} else {
			rightAqueous = false;
		}

		Statement stateRight1 = con.createStatement();
		ResultSet resRight1 = stateRight1.executeQuery("SELECT *"
				+ " FROM solventOrganic"
				+ " WHERE SolventName = '" + inputRightSolvent + "'");
		if (resRight1.next()) {
			leftOrganicSolvent = true;
		} else {
			leftOrganicSolvent = false;
		}

		if(!standardSelected) {
			if (!rightAqueous && !rightOrganicSolvent) {
				System.out.println("right solvent is not in list!");
			}
			if (rightAqueous == true && rightOrganicSolvent == true) {
				System.out.println("right solvent is on both lists?!");
			}
		}
	}



	private void createObjActiveMaterial() throws ClassNotFoundException, SQLException {
		//objActiveMaterial:

		con1 = Database.getConnection("activeMaterial");

		//left:
		Statement stateLeft2 = con1.createStatement();
		ResultSet resLeft2 = stateLeft2.executeQuery("SELECT *"
				+ " FROM activeMaterial"
				+ " WHERE Abbreviation = '" + inputLeftActiveMaterial
				+ "' AND Solvent = '" + inputLeftSolvent
				+ "' AND Salt = '" + inputLeftSalt
				+ "' AND Saltc = " + inputLeftSaltConc);

		while (resLeft2.next()) {
			objActiveMaterialLeft.ID.set(resLeft2.getInt("ID"));
			objActiveMaterialLeft.ABBREVIATION.set(resLeft2.getString("ABBREVIATION"));
			objActiveMaterialLeft.NAME.set(resLeft2.getString("NAME"));
			objActiveMaterialLeft.STRUCTURALFORMULA.set(resLeft2.getString("STRUCTURALFORMULA"));
			objActiveMaterialLeft.M.set(resLeft2.getDouble("M"));
			objActiveMaterialLeft.N.set(resLeft2.getInt("N"));
			objActiveMaterialLeft.NumberH.set(resLeft2.getInt("NumberH"));
			objActiveMaterialLeft.CAM.set(resLeft2.getDouble("CAM"));
			objActiveMaterialLeft.SOLVENT.set(resLeft2.getString("SOLVENT"));
			objActiveMaterialLeft.Salt.set(resLeft2.getString("Salt"));
			objActiveMaterialLeft.SaltC.set(resLeft2.getDouble("Saltc"));
			objActiveMaterialLeft.PH.set(resLeft2.getDouble("PH"));
			objActiveMaterialLeft.E.set(resLeft2.getDouble("E"));
		}

		//right:
		if (!standardSelected) {
			Statement stateRight2 = con1.createStatement();
			ResultSet resRight2 = stateRight2.executeQuery("SELECT *"
					+ " FROM activeMaterial"
					+ " WHERE Abbreviation = '" +inputRightActiveMaterial
					+ "' AND Solvent = '" + inputRightSolvent
					+ "' AND Salt = '" + inputRightSalt
					+ "' AND Saltc = " + inputRightSaltConc);
			while (resRight2.next()) {
				objActiveMaterialRight.ID.set(resRight2.getInt("ID"));
				objActiveMaterialRight.ABBREVIATION.set(resRight2.getString("ABBREVIATION"));
				objActiveMaterialRight.NAME.set(resRight2.getString("NAME"));
				objActiveMaterialRight.STRUCTURALFORMULA.set(resRight2.getString("STRUCTURALFORMULA"));
				objActiveMaterialRight.M.set(resRight2.getDouble("M"));
				objActiveMaterialRight.N.set(resRight2.getInt("N"));
				objActiveMaterialRight.NumberH.set(resRight2.getInt("NumberH"));
				objActiveMaterialRight.CAM.set(resRight2.getDouble("CAM"));
				objActiveMaterialRight.SOLVENT.set(resRight2.getString("SOLVENT"));
				objActiveMaterialRight.Salt.set(resRight2.getString("Salt"));
				objActiveMaterialRight.SaltC.set(resRight2.getDouble("Saltc"));
				objActiveMaterialRight.PH.set(resRight2.getDouble("PH"));
				objActiveMaterialRight.E.set(resRight2.getDouble("E"));
			}
		}
	}


	private void createObjSolvent() throws ClassNotFoundException, SQLException {
		con2 = Database.getConnection("solventInorganic");
		Statement stateSolvent = con2.createStatement();
		
		con21 = Database.getConnection("solventOrganic");
		Statement stateSolventOrg = con21.createStatement();
		//left:
		if (leftAqueous) {
			ResultSet resSolventLeft = stateSolvent.executeQuery("SELECT * FROM solventInorganic "
					+ "WHERE SolventStructuralFormula = '" + inputLeftSalt +
					"' ORDER BY ABS(" + inputLeftSaltConc + "- c) LIMIT 1");
			while (resSolventLeft.next()) {
				objSolventInorganicLeft.ID.set(resSolventLeft.getInt("ID"));
				objSolventInorganicLeft.SolventStructuralFormula.set(resSolventLeft.getString("SolventStructuralFormula"));
				objSolventInorganicLeft.c.set(resSolventLeft.getDouble("c"));
				objSolventInorganicLeft.density.set(resSolventLeft.getDouble("density"));
				objSolventInorganicLeft.dynViscosity.set(resSolventLeft.getDouble("dynViscosity"));
				objSolventInorganicLeft.kinViscosity.set(resSolventLeft.getDouble("kinViscosity"));
			}
		} else {
//			objSolventInorganicAnolyte = null;
		}
		
		if (leftOrganicSolvent) {
			ResultSet resSolventLeft = stateSolventOrg.executeQuery("SELECT * FROM solventOrganic "
					+ "WHERE SolventName = '" + inputLeftSolvent +
					"' LIMIT 1");
			while (resSolventLeft.next()) {
				objSolventOrganicLeft.ID.set(resSolventLeft.getInt("ID"));
				objSolventOrganicLeft.SolventName.set(resSolventLeft.getString("SolventName"));
				objSolventOrganicLeft.SolventStructuralFormula.set(resSolventLeft.getString("SolventStructuralFormula"));
				objSolventOrganicLeft.density.set(resSolventLeft.getDouble("density"));
				objSolventOrganicLeft.dynViscosity.set(resSolventLeft.getDouble("dynViscosity"));
				objSolventOrganicLeft.kinViscosity.set(resSolventLeft.getDouble("kinViscosity"));
			}
		} else {
//			objSolventOrganicAnolyte = null;
		}

		//right:
		if (!standardSelected) {
			if (rightAqueous) {
				ResultSet resSolventRight = stateSolvent.executeQuery("SELECT * FROM solventInorganic "
						+ "WHERE SolventStructuralFormula = '" + inputRightSalt +
						"' ORDER BY ABS(" + inputRightSaltConc + "- c) LIMIT 1");
				while (resSolventRight.next()) {
					objSolventInorganicRight.ID.set(resSolventRight.getInt("ID"));
					objSolventInorganicRight.SolventStructuralFormula.set(resSolventRight.getString("SolventStructuralFormula"));
					objSolventInorganicRight.c.set(resSolventRight.getDouble("c"));
					objSolventInorganicRight.density.set(resSolventRight.getDouble("density"));
					objSolventInorganicRight.dynViscosity.set(resSolventRight.getDouble("dynViscosity"));
					objSolventInorganicRight.kinViscosity.set(resSolventRight.getDouble("kinViscosity"));
				}
			} else {
//				objSolventInorganicCatholyte = null;

			}

			if (rightOrganicSolvent) {
				ResultSet resSolventRight = stateSolventOrg.executeQuery("SELECT * FROM solventOrganic "
						+ "WHERE SolventName = '" + inputRightSolvent +
						"' LIMIT 1");
				while (resSolventRight.next()) {
					objSolventOrganicRight.ID.set(resSolventRight.getInt("ID"));
					objSolventOrganicRight.SolventName.set(resSolventRight.getString("SolventName"));
					objSolventOrganicRight.SolventStructuralFormula.set(resSolventRight.getString("SolventStructuralFormula"));
					objSolventOrganicRight.density.set(resSolventRight.getDouble("density"));
					objSolventOrganicRight.dynViscosity.set(resSolventRight.getDouble("dynViscosity"));
					objSolventOrganicRight.kinViscosity.set(resSolventRight.getDouble("kinViscosity"));
				}
			} else {
//				objSolventOrganicCatholyte = null;
			}
		}
	}




	private void createObjElectrolyte() throws ClassNotFoundException, SQLException {
		con3 = Database.getConnection("electrolyte");
		Statement stateElectrolytes = con3.createStatement();
		//left:
		ResultSet resElectrolytesLeft = stateElectrolytes.executeQuery("SELECT * FROM electrolyte "
				+ "WHERE ActiveMaterial = '" + inputLeftActiveMaterial +
				"' AND Solvent = '" + inputLeftSolvent
				+ "' AND Salt = '" + inputLeftSalt
				+ "' ORDER BY ABS(" + inputLeftSaltConc + "- cSalt) LIMIT 1");
		while (resElectrolytesLeft.next()) {
			objElectrolytesLeft.ID.set(resElectrolytesLeft.getInt("ID"));
			objElectrolytesLeft.ActiveMaterial.set(resElectrolytesLeft.getString("ActiveMaterial"));
			objElectrolytesLeft.Solvent.set(resElectrolytesLeft.getString("Solvent"));
			objElectrolytesLeft.Salt.set(resElectrolytesLeft.getString("Salt"));
			objElectrolytesLeft.cSalt.set(resElectrolytesLeft.getDouble("cSalt"));
			objElectrolytesLeft.pH.set(resElectrolytesLeft.getDouble("pH"));
			objElectrolytesLeft.maxSolubility.set(resElectrolytesLeft.getDouble("maxSolubility"));
			objElectrolytesLeft.DOx.set(resElectrolytesLeft.getDouble("DOx"));
			objElectrolytesLeft.DRed.set(resElectrolytesLeft.getDouble("DRed"));
			objElectrolytesLeft.kOx.set(resElectrolytesLeft.getDouble("kOx"));
			objElectrolytesLeft.AlphaOx.set(resElectrolytesLeft.getDouble("AlphaOx"));
			objElectrolytesLeft.kRed.set(resElectrolytesLeft.getDouble("kRed"));
			objElectrolytesLeft.AlphaRed.set(resElectrolytesLeft.getDouble("AlphaRed"));
			objElectrolytesLeft.f.set(resElectrolytesLeft.getDouble("f"));
		}

		if (!standardSelected) {
			//right:
			ResultSet resElectrolytesRight = stateElectrolytes.executeQuery("SELECT * FROM electrolyte "
					+ "WHERE ActiveMaterial = '" + inputRightActiveMaterial +
					"' AND Solvent = '" + inputRightSolvent
					+ "' AND Salt = '" + inputRightSalt
					+ "' ORDER BY ABS(" + inputRightSaltConc + "- cSalt) LIMIT 1");
			while (resElectrolytesRight.next()) {
				objElectrolytesRight.ID.set(resElectrolytesRight.getInt("ID"));
				objElectrolytesRight.ActiveMaterial.set(resElectrolytesRight.getString("ActiveMaterial"));
				objElectrolytesRight.Solvent.set(resElectrolytesRight.getString("Solvent"));
				objElectrolytesRight.Salt.set(resElectrolytesRight.getString("Salt"));
				objElectrolytesRight.cSalt.set(resElectrolytesRight.getDouble("cSalt"));
				objElectrolytesRight.pH.set(resElectrolytesRight.getDouble("pH"));
				objElectrolytesRight.maxSolubility.set(resElectrolytesRight.getDouble("maxSolubility"));
				objElectrolytesRight.DOx.set(resElectrolytesRight.getDouble("DOx"));
				objElectrolytesRight.DRed.set(resElectrolytesRight.getDouble("DRed"));
				objElectrolytesRight.kOx.set(resElectrolytesRight.getDouble("kOx"));
				objElectrolytesRight.AlphaOx.set(resElectrolytesRight.getDouble("AlphaOx"));
				objElectrolytesRight.kRed.set(resElectrolytesRight.getDouble("kRed"));
				objElectrolytesRight.AlphaRed.set(resElectrolytesRight.getDouble("AlphaRed"));
				objElectrolytesRight.f.set(resElectrolytesRight.getDouble("f"));
			}
		}
	}


	
	private void createObjStack() throws ClassNotFoundException, SQLException {
		
		//default values:
		if (numberCellsPerStack == 0) {
			numberCellsPerStack = defaultInput.numberCellsPerStack.get();
		}
		
		if (cellArea == 0) {
			cellArea = defaultInput.cellArea.get();
		}
		
		//dFiber [m]
		if (dFiber == 0) {
			dFiber = defaultInput.dFiber.get();
		}
		
		if (comboStack.getValue() != null && comboStack.getValue() != "") {
			
			ObjStackSelection objStackDefault = DefaultInput.defaultStackInput(inputStackSystem);
			
			separatorCost = objStackDefault.separatorCost.get();
			electrodeCost = objStackDefault.electrodeCost.get();
			bipolarPlateCost = objStackDefault.bipolarPlateCost.get();
			cellFramesSealsCost = objStackDefault.cellFramesSealsCost.get();
			currentCollectorCost = objStackDefault.currentCollectorCost.get();
			stackFrameCost = objStackDefault.stackFrameCost.get();
			assemblyCost = objStackDefault.assemblyCost.get();
			
			cellArea = objStackDefault.cellArea.get();
			
			numberCellsPerStack = objStackDefault.numberCellsPerStack.get();
			
			asr = objStackDefault.asr.get();
			
			separatorStackNumber = objStackDefault.separatorStackNumber.get();
			electrodeStackNumber = objStackDefault.electrodeStackNumber.get();
			bipolarPlateStackNumber = objStackDefault.bipolarPlateStackNumber.get();
			currentCollectorStackNumber = objStackDefault.currentCollectorStackNumber.get();
			stackFrameStackNumber = objStackDefault.stackFrameStackNumber.get();
			cellFramesSealsStackNumber = objStackDefault.cellFramesSealsStackNumber.get();
			componentsNumber = objStackDefault.componentsNumber.get();
		}
	}



	private void createObjCostsSalt() throws ClassNotFoundException, SQLException {
		con4 = Database.getConnection("costSalt");
		Statement stateCostSalt = con4.createStatement();

		//anoylte:
		ResultSet resCostSaltAnolyte = stateCostSalt.executeQuery("SELECT * FROM costSalt "
				+ "WHERE Salt = '" + inputLeftSalt + "'");

		while (resCostSaltAnolyte.next()) {
			objCostsSaltLeft.ID.set(resCostSaltAnolyte.getInt("ID"));
			objCostsSaltLeft.Salt.set(resCostSaltAnolyte.getString("Salt"));
			objCostsSaltLeft.MSalt.set(resCostSaltAnolyte.getDouble("MSalt"));
			objCostsSaltLeft.CSalt.set(resCostSaltAnolyte.getDouble("CSalt"));
		}

		if (!standardSelected) {
		//catholyte:
			ResultSet resCostSaltCatholyte = stateCostSalt.executeQuery("SELECT * FROM costSalt "
					+ "WHERE Salt = '" + inputRightSalt + "'");

			while (resCostSaltCatholyte.next()) {
				objCostsSaltRight.ID.set(resCostSaltCatholyte.getInt("ID"));
				objCostsSaltRight.Salt.set(resCostSaltCatholyte.getString("Salt"));
				objCostsSaltRight.MSalt.set(resCostSaltCatholyte.getDouble("MSalt"));
				objCostsSaltRight.CSalt.set(resCostSaltCatholyte.getDouble("CSalt"));
			}
		}

	}



	private void createObjCostsSolvent() throws ClassNotFoundException, SQLException {
		con4 = Database.getConnection("costSolvent");
		Statement stateCostSolvent = con4.createStatement();

		//anoylte:
		ResultSet resCostSolventAnolyte = stateCostSolvent.executeQuery("SELECT * FROM costSolvent "
				+ "WHERE Solvent = '" + comboLeftSolvent.getSelectionModel().getSelectedItem().toString() + "'");

		while (resCostSolventAnolyte.next()) {
			objCostsSolventLeft.ID.set(resCostSolventAnolyte.getInt("ID"));
			objCostsSolventLeft.Solvent.set(resCostSolventAnolyte.getString("Solvent"));
			objCostsSolventLeft.MSolvent.set(resCostSolventAnolyte.getDouble("MSolvent"));
			objCostsSolventLeft.CSolvent.set(resCostSolventAnolyte.getDouble("CSolvent"));
		}

		if (!standardSelected) {
		//catholyte:
			ResultSet resCostSolventCatholyte = stateCostSolvent.executeQuery("SELECT * FROM costSolvent "
					+ "WHERE Solvent = '" + comboRightSolvent.getSelectionModel().getSelectedItem().toString() + "'");

			while (resCostSolventCatholyte.next()) {
				objCostsSolventRight.ID.set(resCostSolventCatholyte.getInt("ID"));
				objCostsSolventRight.Solvent.set(resCostSolventCatholyte.getString("Solvent"));
				objCostsSolventRight.MSolvent.set(resCostSolventCatholyte.getDouble("MSolvent"));
				objCostsSolventRight.CSolvent.set(resCostSolventCatholyte.getDouble("CSolvent"));
			}
		}

	}



	// TODO: Use Preferences for standard values to store changes to (default) values inside application? (https://www.youtube.com/watch?v=Uxe7ZkX_Msw) Also storing in Obj could be nicer?
	private void defineAnolyteCatholyte() {

		if (voltageEfficiencyWorkingPoint && efficiencyVoltDischarge == 0) {
			efficiencyVoltDischarge = defaultInput.efficiencyVoltDischarge.get();
			}
		if (stateOfCharge == 0) {
			stateOfCharge = defaultInput.stateOfCharge.get();
		}
		 
		
		// Note:
		// pH value of "electrolyte": Electrolyte of cyclization data (--> fade rate) and if available also the corresponding solubility value. 
		// Potential values might be tested under different conditions like a different pH value. Those differences are taken into account via a simple Nernst equation. 
		// For better calculations a Pourbaix diagram needs to be considered to determine the potential value for the cycled electrolyte. 
		pHElectrolyte = objElectrolytesLeft.pH.getValue();
		protonConcElectrolyte = Math.pow(10, -pHElectrolyte);

		double pHLeft = objActiveMaterialLeft.PH.getValue();
		double protonConcLeft = Math.pow(10, -pHLeft);
		double pHRight = objActiveMaterialLeft.PH.getValue();
		double protonConcRight = Math.pow(10, -pHRight);
		double leftReversiblePotential = 0;
		double rightReversiblePotential = 0;

		if (pHElectrolyte != pHLeft) {
			System.out.println("pH value of electrolyte ["+ objElectrolytesLeft.pH.getValue() + "] does not fit the pH value of LEFT active material ["+ objActiveMaterialLeft.ABBREVIATION.getValue() + " - "+objActiveMaterialLeft.PH.getValue()+ "] in which the redox potential was determined. - Calculated cell voltage might differ from reality!");
		}

		if (!standardSelected) {
			if (pHElectrolyte != pHRight) {
				System.out.println("pH value of electrolyte ["+ objElectrolytesLeft.pH.getValue() + "] does not fit the pH value of LEFT active material ["+ objActiveMaterialRight.ABBREVIATION.getValue() + " - "+objActiveMaterialRight.PH.getValue()+ "] in which the redox potential was determined. - Calculated cell voltage might differ from reality!");
			}
			if (objElectrolytesLeft.pH.getValue() != pHElectrolyte) {
				System.out.println("Electrolyte pH value of anolyte and catholyte side are not machting - Electrolyte pH will be set to: " + objActiveMaterialLeft.ABBREVIATION.getValue() + "; " + objElectrolytesLeft.pH.getValue());
			}
		}
		
		
		double leftFormalPotential = 0;
		double rightFormalPotential = 0;
		
		// Note:
		// Here "formal potential" means the calculated potential at pH 0 assuming the same amount of involved protons over the full range of pH. 
		// As this assumption should not fit for all active materials - Those parameters are not often involved during the calculation of the final results.
		// Only in case the electrolyte pH value differs from the pH value in which the redox potential of the active material was determined - 
		// this value will be basis for calculating the potential at pH of the cycled electrolyte. (In this case - see also the warning in console)
		leftFormalPotential = objActiveMaterialLeft.E.getValue()+G*298.15/(objActiveMaterialLeft.N.getValue()*F)*
				Math.log(1/Math.pow(protonConcLeft,objActiveMaterialLeft.NumberH.getValue()));	

		if (!standardSelected) {
			rightFormalPotential = objActiveMaterialRight.E.getValue()+G*298.15/(objActiveMaterialRight.N.getValue()*F)*
					Math.log(1/Math.pow(protonConcRight,objActiveMaterialRight.NumberH.getValue()));
		}


		
		leftReversiblePotential = leftFormalPotential-G*temp/(objActiveMaterialLeft.N.getValue()*F)*
				Math.log(Math.pow(objElectrolytesLeft.maxSolubility.getValue()*0.5,1)/(Math.pow(objElectrolytesLeft.maxSolubility.getValue()*0.5,1)
						*Math.pow(protonConcElectrolyte,objActiveMaterialLeft.NumberH.getValue())));

		if (!standardSelected) {
			rightReversiblePotential = rightFormalPotential-G*temp/(objActiveMaterialRight.N.getValue()*F)*
					Math.log(Math.pow(objElectrolytesRight.maxSolubility.getValue()*0.5,1)/(Math.pow(objElectrolytesRight.maxSolubility.getValue()*0.5,1)
							*Math.pow(protonConcElectrolyte,objActiveMaterialRight.NumberH.getValue())));
		}

		
		double standardTransferCoeff = defaultInput.standardTransferCoeff.get();

		//maintenance:
		double standardInterestRate = defaultInput.interestRate.get();
		double standardOperationalLifetime = defaultInput.operationalLifetime.get();
		
		if (threshold == 0) {
			threshold = defaultInput.threshold.get();
		}
		
		
		
		if (objActiveMaterialLeft.ABBREVIATION.getValue() != null) {
			
			if (efficiencySysDischarge == 0) {
				efficiencySysDischarge = defaultInput.efficiencySysDischarge.get();
			}
			if (efficiencyCoulombicRoundTrip == 0) {
				efficiencyCoulombicRoundTrip = defaultInput.efficiencyCoulombicRoundTrip.get();
			}
			if (anolyteTankCost == 0) {
				anolyteTankCost = defaultInput.standardTankCost.get();
			}
			if (catholyteTankCost == 0) {
				catholyteTankCost = defaultInput.standardTankCost.get();
			}
			if (tankFillLevel == 0) {
				tankFillLevel = defaultInput.tankFillLevel.get();
			}

			
			//power:
			if (timeDischarge == 0) {
				timeDischarge = defaultInput.timeDischarge.get();
			}
			if (powerDischarge == 0) {
				powerDischarge = defaultInput.powerDischarge.get();
			}
			if (energyCapacity == 0) {
				energyCapacity = defaultInput.energyCapacity.get();
			}
			
//			costBOP = 102.5;
			
			//flowVelocity [m s^-1] (part of "defineAnolyteCatholyte" as there might be differences with change of electrolyte / viscosity in future calculationes?)
			if (flowVelocity == 0) {
				flowVelocity = defaultInput.flowVelocity.get();
			}

			if (leftAqueous && costAdd == 0) {
				//C add / $ kW^-1:
				costAdd = 87.5;
				}
			else if (!leftAqueous && costAdd == 0){
				//C add / $ kW^-1:
//				costAdd = 112.5;
				}
			
			if (operationalLifetime == 0) {
				operationalLifetime = standardOperationalLifetime;
			}
			if (interestRate == 0) {
				interestRate = standardInterestRate;
			}

			

			anolyteReplacementCostMass = 0;
			anolyteReplacementCost = 0;
			catholyteReplacementCostMass = 0;
			catholyteReplacementCost = 0;
			electrolyteOperationalTime = 0;

			stackExchangeCost = 0;
			if (stackOperationalTime == 0) {
				stackOperationalTime = defaultInput.stackOperationalTime.get();
			}

		}
		

		if(!standardSelected) {
			//left = Anolyte; right = Catholyte:
			if(leftReversiblePotential < rightReversiblePotential) {
				//selection:
				anolyteAbbreviation = inputLeftActiveMaterial;
				anolyteC = anolyteActMatConc;
				anolyteSolvent = inputLeftSolvent;
				anolyteSalt = leftSaltCombinationSelection;
				anolyteSaltC = inputLeftSaltConc;

				catholyteAbbreviation = inputRightActiveMaterial;
				catholyteC = catholyteActMatConc;
				catholyteSolvent = inputRightSolvent;
				catholyteSalt = inputRightSalt;
				catholyteSaltC = inputRightSaltConc;

				//electrolyte:
				anolyteActMatCost = objActiveMaterialLeft.CAM.getValue();
				anolyteActMatMolMass = objActiveMaterialLeft.M.getValue();
				if (anolyteSocRange == 0) {
					anolyteSocRange = defaultInput.standardSocRange.get();	
				}
				anolyteActMatNumberElectron = objActiveMaterialLeft.N.getValue();
				anolyteActMatNumberProton = objActiveMaterialLeft.NumberH.getValue();
				anolyteActMatConc = objElectrolytesLeft.maxSolubility.getValue();
				anolyteSaltMolMass = objCostsSaltLeft.MSalt.getValue();
				anolyteSaltConc = objElectrolytesLeft.cSalt.getValue();

				catholyteActMatCost = objActiveMaterialRight.CAM.getValue();
				catholyteActMatMolMass = objActiveMaterialRight.M.getValue();
				if (catholyteSocRange == 0) {
					catholyteSocRange = defaultInput.standardSocRange.get();
				}
				catholyteActMatNumberElectron = objActiveMaterialRight.N.getValue();
				catholyteActMatNumberProton = objActiveMaterialRight.NumberH.getValue();
				catholyteActMatConc = objElectrolytesRight.maxSolubility.getValue();
				catholyteSaltMolMass = objCostsSaltRight.MSalt.getValue();
				catholyteSaltConc = objElectrolytesRight.cSalt.getValue();

				anolyteStandardPotential = leftFormalPotential;
				catholyteStandardPotential = rightFormalPotential;

				anolyteSaltCost = objCostsSaltLeft.CSalt.getValue();
				anolyteSolventCost = objCostsSolventLeft.CSolvent.getValue();
				if (leftAqueous) {
					anolyteSolventDensity = objSolventInorganicLeft.density.getValue();
					anolyteSolventViscosity = objSolventInorganicLeft.dynViscosity.getValue();
				} else {
					anolyteSolventDensity = objSolventOrganicLeft.density.getValue();
					anolyteSolventViscosity = objSolventOrganicLeft.dynViscosity.getValue();
				}

				catholyteSaltCost = objCostsSaltRight.CSalt.getValue();
				catholyteSolventCost = objCostsSolventRight.CSolvent.getValue();
				if (rightAqueous) {
					catholyteSolventDensity = objSolventInorganicRight.density.getValue();
					catholyteSolventViscosity = objSolventInorganicRight.dynViscosity.getValue();
				} else {
					catholyteSolventDensity = objSolventOrganicRight.density.getValue();
					catholyteSolventViscosity = objSolventOrganicRight.dynViscosity.getValue();
				}

				//power:
//				stackCost = objCostAnalysisASR.cA.getValue();
//				asr = objCostAnalysisASR.ASR.getValue();


				if (objElectrolytesLeft.kRed.getValue() != 0) {
					anolyteReactionRate = objElectrolytesLeft.kRed.getValue();
					anolyteKred = true;
					if (objElectrolytesLeft.AlphaRed.getValue() != 0) {
						anolyteTransferCoeff = objElectrolytesLeft.AlphaRed.getValue();
					} else {
						anolyteTransferCoeff = standardTransferCoeff;
					}

				} else if (objElectrolytesLeft.kOx.getValue() != 0) {
					anolyteReactionRate = objElectrolytesLeft.kOx.getValue();
					anolyteKred = false;
					if (objElectrolytesLeft.AlphaOx.getValue() != 0) {
						anolyteTransferCoeff = objElectrolytesLeft.AlphaOx.getValue();
					} else {
						anolyteTransferCoeff = standardTransferCoeff;
					}
				} else {
					System.out.println("no reaction rate defined for active material on left side");
				}

				if (objElectrolytesLeft.DRed.getValue() != 0) {
					anolyteDiffusionCoeff = objElectrolytesLeft.DRed.getValue();

//					anolyteKred = true;

				} else if (objElectrolytesLeft.DOx.getValue() != 0) {
					anolyteDiffusionCoeff = objElectrolytesLeft.DOx.getValue();
//					anolyteKred = false;
				} else {
					System.out.println("no diffusion coefficient defined for active material on left side");
				}



				if (objElectrolytesRight.kOx.getValue() != 0) {
					catholyteReactionRate = objElectrolytesRight.kOx.getValue();
					catholyteKox = true;
					if (objElectrolytesRight.AlphaOx.getValue() != 0) {
						catholyteTransferCoeff = objElectrolytesRight.AlphaOx.getValue();
					} else {
						catholyteTransferCoeff = standardTransferCoeff;
					}

				} else if (objElectrolytesRight.kRed.getValue() != 0) {
					catholyteReactionRate = objElectrolytesRight.kRed.getValue();
					catholyteKox = false;
					if (objElectrolytesRight.AlphaRed.getValue() != 0) {
						catholyteTransferCoeff = objElectrolytesRight.AlphaRed.getValue();
					} else {
						catholyteTransferCoeff = standardTransferCoeff;
					}
				} else {
					System.out.println("no reaction rate defined for active material on right side");
				}


				if (objElectrolytesRight.DOx.getValue() != 0) {
					catholyteDiffusionCoeff = objElectrolytesRight.DOx.getValue();
//					catholyteKox = true;

				} else if (objElectrolytesRight.DRed.getValue() != 0) {
					catholyteDiffusionCoeff = objElectrolytesRight.DRed.getValue();
//					catholyteKox = false;
				} else {
					System.out.println("no diffusion coefficient defined for active material on right side");
				}

				//maintenance:
				anolyteReplacementFraction = objElectrolytesLeft.f.getValue();
				catholyteReplacementFraction = objElectrolytesRight.f.getValue();

//				if (catholyteAbbreviation.equals("VOSO4")) {
//					catholyteSocRange = 1;
//					anolyteSocRange = 1;
//					System.out.println("VRFB calculation mode");
//				}

			//left = Catholyte; right = Anolyte:
			} else {
				//selection:
				catholyteAbbreviation = inputLeftActiveMaterial;
				catholyteC = catholyteActMatConc;
				catholyteSolvent = inputLeftSolvent;
				catholyteSalt = leftSaltCombinationSelection;
				catholyteSaltC = inputLeftSaltConc;

				anolyteAbbreviation = inputRightActiveMaterial;
				anolyteC = anolyteActMatConc;
				anolyteSolvent = inputRightSolvent;
				anolyteSalt = inputRightSalt;
				anolyteSaltC = inputRightSaltConc;

				//electrolyte:
				catholyteActMatCost = objActiveMaterialLeft.CAM.getValue();
				catholyteActMatMolMass = objActiveMaterialLeft.M.getValue();
				if (catholyteSocRange == 0) {
					catholyteSocRange = defaultInput.standardSocRange.get();
				}

				catholyteActMatNumberElectron = objActiveMaterialLeft.N.getValue();
				catholyteActMatNumberProton = objActiveMaterialLeft.NumberH.getValue();
				catholyteActMatConc = objElectrolytesLeft.maxSolubility.getValue();
				catholyteSaltMolMass = objCostsSaltLeft.MSalt.getValue();
				catholyteSaltConc = objElectrolytesLeft.cSalt.getValue();

				anolyteActMatCost = objActiveMaterialRight.CAM.getValue();
				anolyteActMatMolMass = objActiveMaterialRight.M.getValue();
				if (anolyteSocRange == 0) {
					anolyteSocRange = defaultInput.standardSocRange.get();
				}

				anolyteActMatNumberElectron = objActiveMaterialRight.N.getValue();
				anolyteActMatNumberProton = objActiveMaterialRight.NumberH.getValue();
				anolyteActMatConc = objElectrolytesRight.maxSolubility.getValue();
				anolyteSaltMolMass = objCostsSaltRight.MSalt.getValue();
				anolyteSaltConc = objElectrolytesRight.cSalt.getValue();

				catholyteStandardPotential = leftFormalPotential;
				anolyteStandardPotential = rightFormalPotential;

				catholyteSaltCost = objCostsSaltLeft.CSalt.getValue();
				catholyteSolventCost = objCostsSolventLeft.CSolvent.getValue();
				if (leftAqueous) {
					catholyteSolventDensity = objSolventInorganicLeft.density.getValue();
					catholyteSolventViscosity = objSolventInorganicLeft.dynViscosity.getValue();
				} else {
					catholyteSolventDensity = objSolventOrganicLeft.density.getValue();
					catholyteSolventViscosity = objSolventOrganicLeft.dynViscosity.getValue();
				}

				anolyteSaltCost = objCostsSaltRight.CSalt.getValue();
				anolyteSolventCost = objCostsSolventRight.CSolvent.getValue();
				if (rightAqueous) {
					anolyteSolventDensity = objSolventInorganicRight.density.getValue();
					anolyteSolventViscosity = objSolventInorganicRight.dynViscosity.getValue();
				} else {
					anolyteSolventDensity = objSolventOrganicRight.density.getValue();
					anolyteSolventViscosity = objSolventInorganicRight.dynViscosity.getValue();
				}

				//power:
//				stackCost = objCostAnalysisASR.cA.getValue();
//				asr = objCostAnalysisASR.ASR.getValue();

				if (objElectrolytesRight.kRed.getValue() != 0) {
					anolyteReactionRate = objElectrolytesRight.kRed.getValue();
					anolyteKred = true;
					if (objElectrolytesRight.AlphaRed.getValue() != 0) {
						anolyteTransferCoeff = objElectrolytesRight.AlphaRed.getValue();
					} else {
						anolyteTransferCoeff = standardTransferCoeff;
					}

				} else if (objElectrolytesRight.kOx.getValue() != 0) {
					anolyteReactionRate = objElectrolytesRight.kOx.getValue();
					anolyteKred = false;
					if (objElectrolytesRight.AlphaOx.getValue() != 0) {
						anolyteTransferCoeff = objElectrolytesRight.AlphaOx.getValue();
					} else {
						anolyteTransferCoeff = standardTransferCoeff;
					}
				} else {
					System.out.println("no reaction rate defined for active material on right side");
				}

				if (objElectrolytesRight.DOx.getValue() != 0) {
					catholyteDiffusionCoeff = objElectrolytesRight.DOx.getValue();
//					catholyteKox = true;

				} else if (objElectrolytesRight.DRed.getValue() != 0) {
					catholyteDiffusionCoeff = objElectrolytesRight.DRed.getValue();
//					catholyteKox = false;
				} else {
					System.out.println("no diffusion coefficient defined for active material on right side");
				}


				if (objElectrolytesLeft.kOx.getValue() != 0) {
					catholyteReactionRate = objElectrolytesLeft.kOx.getValue();
					catholyteKox = true;
					if (objElectrolytesLeft.AlphaOx.getValue() != 0) {
						catholyteTransferCoeff = objElectrolytesLeft.AlphaOx.getValue();
					} else {
						catholyteTransferCoeff = standardTransferCoeff;
					}

				} else if (objElectrolytesLeft.kRed.getValue() != 0) {
					catholyteReactionRate = objElectrolytesLeft.kRed.getValue();
					catholyteKox = false;
					if (objElectrolytesLeft.AlphaRed.getValue() != 0) {
						catholyteTransferCoeff = objElectrolytesLeft.AlphaRed.getValue();
					} else {
						catholyteTransferCoeff = standardTransferCoeff;
					}
				} else {
					System.out.println("no reaction rate defined for active material on left side");
				}

				if (objElectrolytesLeft.DRed.getValue() != 0) {
					catholyteDiffusionCoeff = objElectrolytesLeft.DRed.getValue();
//					catholyteKred = true;

				} else if (objElectrolytesLeft.DOx.getValue() != 0) {
					catholyteDiffusionCoeff = objElectrolytesLeft.DOx.getValue();
//					catholyteKred = false;
				} else {
					System.out.println("no diffusion coefficient defined for active material on left side");
				}

				//maintenance:
				catholyteReplacementFraction = objElectrolytesLeft.f.getValue();
				anolyteReplacementFraction = objElectrolytesRight.f.getValue();
			}


		// Standard:
		} else {
			//electrolyte:
//			double standardActMatCost = 5;
			double standardActMatCost = objActiveMaterialLeft.CAM.getValue();
			double standardActMatMolMass = 100;
			Integer standardActMatNumberElectron = 1;
			Integer standardActMatNumberProton = 0;
			double standardActMatSol = 0;
			double standardActMatConc = 0;
			double standardElimitcathode = 0;
			double standardElimitanode = 0;
			double reversibleStandardElimitcathode = 0;
			double reversibleStandardElimitanode = 0;
			double standardReplacementFraction = 0;

			String standardSolvent;
			String standardSalt;

			if (leftAqueous) {
				standardActMatSol = 0.2;
				standardActMatConc = 2;
				reversibleStandardElimitcathode = 1.6 - G*temp/(4*F)*Math.log(1/Math.pow(protonConcLeft,4));
				reversibleStandardElimitanode = -0.6 - G*temp/(2*F)*Math.log(1/Math.pow(protonConcLeft,2));
				standardElimitcathode = 1.6;
				standardElimitanode = -0.6;

//				standardElimitcathode = 1.6;
//				standardElimitanode = -0.6;

				standardSolvent = "H2O";
				standardSalt = "StandardSalt, xM";
			}

//			if (leftOrganicSolvent == 1) {
//				standardActMatSol = 1;
//
//				ECatholyteStandard = 3.16;
//				EAnolyteStandard = -1.68;
			
			// left = Anolyte; standard = Catholyte:
			if (Math.abs(leftReversiblePotential - reversibleStandardElimitcathode) > Math.abs(leftReversiblePotential - reversibleStandardElimitanode) ) {
				System.out.println("left = Anolyte; standard = Catholyte!");

				catholyteStandard = true;
				anolyteStandard = false;

				//selection:
				anolyteAbbreviation = inputLeftActiveMaterial;
				anolyteC = catholyteActMatConc;
				anolyteSolvent = inputLeftSolvent;
				anolyteSalt = leftSaltCombinationSelection;
				anolyteSaltC = inputLeftSaltConc;

				catholyteAbbreviation = "standard";
				catholyteC = 1;
				catholyteSolvent = "standard";
				catholyteSalt = "standard";
				catholyteSaltC = 1;


				//electrolyte:
				anolyteActMatCost = objActiveMaterialLeft.CAM.getValue();
				anolyteActMatMolMass = objActiveMaterialLeft.M.getValue();
				if (anolyteAbbreviation != null) {
					anolyteSocRange = defaultInput.standardSocRange.get();
				} else {
					System.out.println("ActMat blank");
					anolyteSocRange = SA_SocRange;
				}

				anolyteActMatNumberElectron = objActiveMaterialLeft.N.getValue();
				anolyteActMatNumberProton = objActiveMaterialLeft.NumberH.getValue();
				anolyteActMatConc = objElectrolytesLeft.maxSolubility.getValue();
				anolyteSaltMolMass = objCostsSaltLeft.MSalt.getValue();
				anolyteSaltConc = objElectrolytesLeft.cSalt.getValue();

				catholyteActMatCost = standardActMatCost;
				catholyteActMatMolMass = standardActMatMolMass;
				
				catholyteSocRange = defaultInput.standardSocRange.get();
				
				catholyteActMatNumberElectron = standardActMatNumberElectron;
				catholyteActMatNumberProton = standardActMatNumberProton;
				catholyteActMatConc = standardActMatConc;
				catholyteSaltMolMass = anolyteSaltMolMass;
				catholyteSaltConc = anolyteSaltConc;

				anolyteStandardPotential = leftFormalPotential;
				catholyteStandardPotential = standardElimitcathode;

				anolyteSaltCost = objCostsSaltLeft.CSalt.getValue();
				anolyteSolventCost = objCostsSolventLeft.CSolvent.getValue();
				if (leftAqueous) {
					anolyteSolventDensity = objSolventInorganicLeft.density.getValue();
					anolyteSolventViscosity = objSolventInorganicLeft.dynViscosity.getValue();
				} else {
					anolyteSolventDensity = objSolventOrganicLeft.density.getValue();
					anolyteSolventViscosity = objSolventOrganicLeft.dynViscosity.getValue();
				}

				catholyteSaltCost = anolyteSaltCost;
				catholyteSolventCost = anolyteSolventCost;
				saltCost = anolyteSaltCost;
				solventCost = anolyteSolventCost;

				catholyteSolventDensity = anolyteSolventDensity;
				catholyteSolventViscosity = anolyteSolventViscosity;

				//power:
//				stackCost = objCostAnalysisASR.cA.getValue();
//				asr = objCostAnalysisASR.ASR.getValue();

				if (objElectrolytesLeft.kRed.getValue() != 0) {
					anolyteReactionRate = objElectrolytesLeft.kRed.getValue();
					anolyteKred = true;
					if (objElectrolytesLeft.AlphaRed.getValue() != 0) {
						anolyteTransferCoeff = objElectrolytesLeft.AlphaRed.getValue();
					} else {
						anolyteTransferCoeff = standardTransferCoeff;
					}

				} else if (objElectrolytesLeft.kOx.getValue() != 0) {
					anolyteReactionRate = objElectrolytesLeft.kOx.getValue();
					anolyteKred = false;
					if (objElectrolytesLeft.AlphaOx.getValue() != 0) {
						anolyteTransferCoeff = objElectrolytesLeft.AlphaOx.getValue();
					} else {
						anolyteTransferCoeff = standardTransferCoeff;
					}
				} else {
					System.out.println("no reaction rate defined for active material on left side");
				}


				if (objElectrolytesLeft.DRed.getValue() != 0) {
					anolyteDiffusionCoeff = objElectrolytesLeft.DRed.getValue();
//					anolyteKred = true;

				} else if (objElectrolytesLeft.DOx.getValue() != 0) {
					anolyteDiffusionCoeff = objElectrolytesLeft.DOx.getValue();
//					anolyteKred = false;
				} else {
					System.out.println("no diffusion coefficient defined for active material on left side");
				}

				catholyteReactionRate = 0;
				catholyteTransferCoeff = 0;

				//maintenance:
				anolyteReplacementFraction = objElectrolytesLeft.f.getValue();
				catholyteReplacementFraction = standardReplacementFraction;



			// left = Catholyte; standard = Anolyte:
			}	else {

				anolyteStandard = true;
				catholyteStandard = false;

				//selection:
				catholyteAbbreviation = inputLeftActiveMaterial;
				catholyteC = catholyteActMatConc;
				catholyteSolvent = inputLeftSolvent;
				catholyteSalt = leftSaltCombinationSelection;
				catholyteSaltC = inputLeftSaltConc;

				anolyteAbbreviation = "standard";
				anolyteC = 1;
				anolyteSolvent = "standard";
				anolyteSalt = "standard";
				anolyteSaltC = 1;

				//electrolyte:
				catholyteActMatCost = objActiveMaterialLeft.CAM.getValue();
				catholyteActMatMolMass = objActiveMaterialLeft.M.getValue();
//				if (catholyteAbbreviation != null) {
//					catholyteSocRange = standardSocRange;
//				} else {
//					System.out.println("ActMat blank");
//					catholyteSocRange = SA_SocRange; 
//				}
				if (catholyteSocRange == 0) {
					catholyteSocRange = defaultInput.standardSocRange.get();
				}

				catholyteActMatNumberElectron = objActiveMaterialLeft.N.getValue();
				catholyteActMatNumberProton = objActiveMaterialLeft.NumberH.getValue();
				catholyteActMatConc = objElectrolytesLeft.maxSolubility.getValue();
				catholyteSaltMolMass = objCostsSaltLeft.MSalt.getValue();
				catholyteSaltConc = objElectrolytesLeft.cSalt.getValue();

				anolyteActMatCost = standardActMatCost;
				anolyteActMatMolMass = standardActMatMolMass;
				
				if (anolyteSocRange == 0) {
					anolyteSocRange = defaultInput.standardSocRange.get();
				}

				
				anolyteActMatNumberElectron = standardActMatNumberElectron;
				anolyteActMatNumberProton = standardActMatNumberProton;
				anolyteActMatConc = standardActMatConc;
				anolyteSaltMolMass = catholyteSaltMolMass;
				anolyteSaltConc = catholyteSaltConc;

				catholyteStandardPotential = leftFormalPotential;
				anolyteStandardPotential = standardElimitanode;

				catholyteSaltCost = objCostsSaltLeft.CSalt.getValue();
				catholyteSolventCost = objCostsSolventLeft.CSolvent.getValue();
				if (leftAqueous) {
					catholyteSolventDensity = objSolventInorganicLeft.density.getValue();
					catholyteSolventViscosity = objSolventInorganicLeft.dynViscosity.getValue();
				} else {
					catholyteSolventDensity = objSolventOrganicLeft.density.getValue();
					catholyteSolventViscosity = objSolventOrganicLeft.dynViscosity.getValue();
				}

				anolyteSaltCost = catholyteSaltCost;
				anolyteSolventCost = catholyteSolventCost;
				saltCost = catholyteSaltCost;
				solventCost = catholyteSolventCost;

				anolyteSolventDensity = catholyteSolventDensity;
				anolyteSolventViscosity = catholyteSolventViscosity;

				//power:
//				stackCost = objCostAnalysisASR.cA.getValue();
//				asr = objCostAnalysisASR.ASR.getValue();

				anolyteReactionRate = 0;
				anolyteTransferCoeff = 0;

				if (objElectrolytesLeft.kOx.getValue() != 0) {
					catholyteReactionRate = objElectrolytesLeft.kOx.getValue();
					catholyteKox = true;
					if (objElectrolytesLeft.AlphaOx.getValue() != 0) {
						catholyteTransferCoeff = objElectrolytesLeft.AlphaOx.getValue();
					} else {
						catholyteTransferCoeff = standardTransferCoeff;
					}

				} else if (objElectrolytesLeft.kRed.getValue() != 0) {
					catholyteReactionRate = objElectrolytesLeft.kRed.getValue();
					catholyteKox = false;
					if (objElectrolytesLeft.AlphaRed.getValue() != 0) {
						catholyteTransferCoeff = objElectrolytesLeft.AlphaRed.getValue();
					} else {
						catholyteTransferCoeff = standardTransferCoeff;
					}
				} else {
					System.out.println("no reaction rate defined for active material on left side");
				}


				if (objElectrolytesLeft.DRed.getValue() != 0) {
					catholyteDiffusionCoeff = objElectrolytesLeft.DRed.getValue();
//					catholyteKred = true;

				} else if (objElectrolytesLeft.DOx.getValue() != 0) {
					catholyteDiffusionCoeff = objElectrolytesLeft.DOx.getValue();
//					catholyteKred = false;
				} else {
					System.out.println("no diffusion coefficient defined for active material on left side");
				}

				//maintenance:
				catholyteReplacementFraction = objElectrolytesLeft.f.getValue();
				anolyteReplacementFraction = standardReplacementFraction;
			}
		}
	}



//	SpiderWeb Plot:
	private void drawNewChart(JFreeChart newChart, ArrayList<ObjSpiderWebData> DataList) {
		SpiderPlot Chart = new SpiderPlot();
		newChart = Chart.spiderPlot(DataList);
//		SpiderWebPane.getChildren().removeAll();

        ChartCanvasJFreeChart chartCanvas = new ChartCanvasJFreeChart(newChart);

        chartCanvas.widthProperty().bind(paneSpiderWeb.widthProperty());
        double spiderWebHeight = paneSpiderWeb.heightProperty().doubleValue() * 0.9;
        chartCanvas.heightProperty().set(spiderWebHeight);

        paneSpiderWeb.getChildren().add(chartCanvas);
	}


//	TODO: round numbers for labels
//	Ud vs SOC plot:
	private void drawNewChartLineChartSOC(JFreeChart newScatterChart, XYSeriesCollection SOCDataset) {

		String title = "";

		if (standardSelected) {
			title = inputLeftActiveMaterial;
		}

		if (!standardSelected) {
			title = inputLeftActiveMaterial+" / " +inputRightActiveMaterial;
		}

		JFreeChart lineChart = ChartFactory.createXYLineChart(title, "SOC", "Ud / [V]", SOCDataset, PlotOrientation.VERTICAL, true, true, true);
		ChartCanvasJFreeChart chartCanvas = new ChartCanvasJFreeChart(lineChart);
//		CategoryPlot pi = lineChart.getCategoryPlot();
//	    pi.setRangeGridlinePaint(Color.WHITE);
//	    pi.getRenderer().setSeriesPaint(0, Color.GREEN);
//	    pi.setBackgroundPaint(Color.BLACK);

		XYPlot plot = lineChart.getXYPlot( );
//	    ChartPanel chartPanel = new ChartPanel( lineChart );
//	    chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );

        Marker resultSOC = new ValueMarker(stateOfCharge);
        resultSOC.setPaint(Color.GREEN);
        resultSOC.setLabel("SOC: " +Double.toString(stateOfCharge)+ "; U: " +Double.toString(voltageDischarge));
		resultSOC.setLabelAnchor(RectangleAnchor.BOTTOM_LEFT);
		resultSOC.setLabelTextAnchor(TextAnchor.BASELINE_CENTER);
		plot.addDomainMarker(resultSOC);

		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
	    renderer.setSeriesPaint( 0 , Color.GREEN );
	    renderer.setSeriesPaint( 1 , Color.RED );
	    renderer.setSeriesStroke( 0 , new BasicStroke( 1f ) );
	    renderer.setSeriesStroke( 1 , new BasicStroke( 4f ) );
	    renderer.setDefaultShapesVisible(false);
	    plot.setRenderer( renderer );

        chartCanvas.widthProperty().bind(paneUdvsSOC.widthProperty());
        chartCanvas.heightProperty().bind(paneUdvsSOC.heightProperty());

		paneUdvsSOC.getChildren().add(chartCanvas);

	}



//	Ud vs Id plot:
	private void drawNewChartLineChartId(JFreeChart newScatterChart2, XYSeriesCollection IdDataset) {

		String title = "";

		if (standardSelected) {
			title = inputLeftActiveMaterial;
		}

		if (!standardSelected) {
			title = inputLeftActiveMaterial+" / " +inputRightActiveMaterial;
		}

		JFreeChart lineChart2 = ChartFactory.createXYLineChart(title, "Id / [A/cm²]", "Ud / [V]", IdDataset, PlotOrientation.VERTICAL, true, true, true);
		ChartCanvasJFreeChart chartCanvas = new ChartCanvasJFreeChart(lineChart2);

		XYPlot plot2= lineChart2.getXYPlot( );

		Marker resultId = new ValueMarker(currentDensityDischarge);
		resultId.setPaint(Color.GREEN);
		resultId.setLabel("Id: " +Double.toString(currentDensityDischarge)+ "; Ud: " +Double.toString(voltageDischarge));
		resultId.setLabelAnchor(RectangleAnchor.BOTTOM_LEFT);
		resultId.setLabelTextAnchor(TextAnchor.BASELINE_CENTER);
		plot2.addDomainMarker(resultId);

		XYLineAndShapeRenderer renderer2 = new XYLineAndShapeRenderer( );
		renderer2.setSeriesPaint( 0 , Color.GREEN );
		renderer2.setSeriesPaint( 1 , Color.RED );
		renderer2.setSeriesStroke( 0 , new BasicStroke( 1f ) );
		renderer2.setSeriesStroke( 1 , new BasicStroke( 4f ) );
		renderer2.setDefaultShapesVisible(false);
		plot2.setRenderer( renderer2 );


		chartCanvas.widthProperty().bind(paneUdvsId.widthProperty());
		chartCanvas.heightProperty().bind(paneUdvsId.heightProperty());

		paneUdvsId.getChildren().add(chartCanvas);

	}




//	Pd vs Id plot:
	private void drawNewChartLineChartPdId(JFreeChart newScatterChart3, XYSeriesCollection PdIdDataset) {

		String title = "";

		if (standardSelected) {
			title = inputLeftActiveMaterial;
		}

		if (!standardSelected) {
			title = inputLeftActiveMaterial+" / " +inputRightActiveMaterial;
		}

		JFreeChart lineChart3 = ChartFactory.createXYLineChart(title, "Id / [A/cm²]", "Pd / [W/cm²]", PdIdDataset, PlotOrientation.VERTICAL, true, true, true);
		ChartCanvasJFreeChart chartCanvas = new ChartCanvasJFreeChart(lineChart3);

		XYPlot plot3 = lineChart3.getXYPlot( );

		Marker resultPdId = new ValueMarker(currentDensityDischarge);
		resultPdId.setPaint(Color.GREEN);
		resultPdId.setLabel("Id: " +Double.toString(currentDensityDischarge)+ "; Pd: " +Double.toString(specificPowerDischarge));
		resultPdId.setLabelAnchor(RectangleAnchor.BOTTOM_LEFT);
		resultPdId.setLabelTextAnchor(TextAnchor.BASELINE_CENTER);
		plot3.addDomainMarker(resultPdId);

		XYLineAndShapeRenderer renderer3 = new XYLineAndShapeRenderer( );
		renderer3.setSeriesPaint( 0 , Color.GREEN );
		renderer3.setSeriesPaint( 1 , Color.RED );
		renderer3.setSeriesStroke( 0 , new BasicStroke( 1f ) );
		renderer3.setSeriesStroke( 1 , new BasicStroke( 4f ) );
		renderer3.setDefaultShapesVisible(false);
		plot3.setRenderer( renderer3 );



		chartCanvas.widthProperty().bind(panePdvsId.widthProperty());
		chartCanvas.heightProperty().bind(panePdvsId.heightProperty());

		panePdvsId.getChildren().add(chartCanvas);
	}




	public void calculation() {

		calculationCombo = true;

//		define input values:
		ObjCostAnalysisInput input = new ObjCostAnalysisInput();
//		power:
		// stack:
		input.separatorCost.set(separatorCost);
		input.separatorStackNumber.set(separatorStackNumber);
		input.electrodeCost.set(electrodeCost);
		input.electrodeStackNumber.set(electrodeStackNumber);
		input.bipolarPlateCost.set(bipolarPlateCost);
		input.bipolarPlateStackNumber.set(bipolarPlateStackNumber);
//		input.cellFramesCost.set(cellFramesCost);
//		input.cellFramesStackNumber.set(cellFramesStackNumber);
//		input.sealsCost.set(sealsCost);
//		input.sealsStackNumber.set(sealsStackNumber);
		input.cellFramesSealsCost.set(cellFramesSealsCost);
		input.cellFramesSealsStackNumber.set(cellFramesSealsStackNumber);
		input.currentCollectorCost.set(currentCollectorCost);
		input.currentCollectorStackNumber.set(currentCollectorStackNumber);
		input.stackFrameCost.set(stackFrameCost);
		input.stackFrameStackNumber.set(stackFrameStackNumber);
		input.assemblyCost.set(assemblyCost);
		input.componentsNumber.set(componentsNumber);

		input.separatorStackCost.set(separatorStackCost);
		input.electrodeStackCost.set(electrodeStackCost);
		input.bipolarPlateStackCost.set(bipolarPlateStackCost);
//		input.cellFramesStackCost.set(cellFramesStackCost);
//		input.sealsStackCost.set(sealsStackCost);
		input.cellFramesSealsStackCost.set(cellFramesSealsStackCost);
		input.currentCollectorStackCost.set(currentCollectorStackCost);
		input.stackFrameStackCost.set(stackFrameStackCost);
		input.assemblyStackCost.set(assemblyStackCost);
		input.stackCostTotal.set(stackCostTotal);
		input.stackCost.set(stackCost);
		input.cellArea.set(cellArea);
		input.numberCellsPerStack.set(numberCellsPerStack);
		// stoichiometric coeff:
		input.anolyteActMatNumberElectron.set(anolyteActMatNumberElectron);
		input.catholyteActMatNumberElectron.set(catholyteActMatNumberElectron);
		//Ecell:
		input.catholyteStandardPotential.set(catholyteStandardPotential);
		input.anolyteStandardPotential.set(anolyteStandardPotential);
		input.catholyteReversiblePotential.set(catholyteReversiblePotential);
		input.anolyteReversiblePotential.set(anolyteReversiblePotential);
		input.reversibleVoltage.set(reversibleVoltage);
		//U, ocv:
		input.stateOfCharge.set(stateOfCharge);
		input.anolyteActMatConc.set(anolyteActMatConc);
		input.catholyteActMatConc.set(catholyteActMatConc);
		input.pH.set(pHElectrolyte);
		input.catholyteActMatNumberProton.set(catholyteActMatNumberProton);
		input.anolyteActMatNumberProton.set(anolyteActMatNumberProton);

		input.standardSelected.set(standardSelected);
		input.peakPowerWorkingPoint.set(peakPowerWorkingPoint);
		input.voltageEfficiencyWorkingPoint.set(voltageEfficiencyWorkingPoint);
		input.currentDensityWorkingPoint.set(currentDensityWorkingPoint);
		input.specPowerWorkingPoint.set(specPowerWorkingPoint);
		input.ohmicMode.set(ohmicMode);
		input.chargeTransferMode.set(chargeTransferMode);
		input.transportationLimitationMode.set(transportationLimitationMode);
		input.anolyteStandard.set(anolyteStandard);
		input.catholyteStandard.set(catholyteStandard);
		input.leftAqueous.set(leftAqueous);

		input.temp.set(temp);
		// i0:
		input.catholyteKox.set(catholyteKox);
		input.anolyteKred.set(anolyteKred);
		input.catholyteReactionRate.set(catholyteReactionRate);
		input.catholyteTransferCoeff.set(catholyteTransferCoeff);
		input.anolyteReactionRate.set(anolyteReactionRate);
		input.anolyteTransferCoeff.set(anolyteTransferCoeff);
		// id:
		input.asr.set(asr);
		if (peakPowerWorkingPoint) {
			peakPowerRatio = Double.valueOf(peakPowerInput.getText());
		}
		input.peakPowerRatio.set(peakPowerRatio);
		if (voltageEfficiencyWorkingPoint) {
			efficiencyVoltDischarge = Double.valueOf(voltageEfficiencyInput.getText());
		}
		input.efficiencyVoltDischarge.set(efficiencyVoltDischarge);
		input.efficiencySysDischarge.set(efficiencySysDischarge);
		if (currentDensityWorkingPoint) {
			currentDensityDischarge = Double.valueOf(currentDensityInput.getText());
		}
		input.currentDensityDischarge.set(currentDensityDischarge);
		input.voltageDischarge.set(voltageDischarge);
		input.specificPowerDischargeInput.set(specificPowerDischargeInput);
		
		input.chargeTranserOverPotDischarge.set(chargeTranserOverPotDischarge);
		input.ohmicOverPotDischarge.set(ohmicOverPotDischarge);
		input.anolyteChargeTranserOverPotDischarge.set(anolyteChargeTranserOverPotDischarge);
		input.catholyteChargeTranserOverPotDischarge.set(catholyteChargeTranserOverPotDischarge);
		input.concentrationOverPotDischarge.set(concentrationOverPotDischarge);

		input.dFiber.set(dFiber);
		input.flowVelocity.set(flowVelocity);
		input.anolyteDiffusionCoeff.set(anolyteDiffusionCoeff);
		input.catholyteDiffusionCoeff.set(catholyteDiffusionCoeff);
		input.anolyteSolventViscosity.set(anolyteSolventViscosity);
		input.catholyteSolventViscosity.set(catholyteSolventViscosity);
		input.catholyteConcentrationOverPotDischarge.set(catholyteConcentrationOverPotDischarge);
		input.anolyteConcentrationOverPotDischarge.set(anolyteConcentrationOverPotDischarge);

		//A total:
		input.powerDischarge.set(powerDischarge);
		//Cpower:
		input.timeDischarge.set(timeDischarge);
		input.energyCapacity.set(energyCapacity);
		input.costBOP.set(costBOP);
		input.costAdd.set(costAdd);
//		electrolyte:
		input.anolyteSaltConc.set(anolyteSaltConc);
		input.anolyteSaltSol.set(anolyteSaltSol);
		input.anolyteSaltMolMass.set(anolyteSaltMolMass);
		input.anolyteSolventDensity.set(anolyteSolventDensity);
		input.anolyteActMatMolMass.set(anolyteActMatMolMass);
		input.catholyteSaltConc.set(catholyteSaltConc);
		input.catholyteSaltSol.set(catholyteSaltSol);
		input.catholyteSaltMolMass.set(catholyteSaltMolMass);
		input.catholyteSolventDensity.set(catholyteSolventDensity);
		input.catholyteActMatMolMass.set(catholyteActMatMolMass);

		input.anolyteActMatCost.set(anolyteActMatCost);
		input.catholyteActMatCost.set(catholyteActMatCost);
		input.solventCost.set(solventCost);
		input.saltCost.set(saltCost);
		input.catholyteSocRange.set(catholyteSocRange);
		input.anolyteSocRange.set(anolyteSocRange);
		input.efficiencyCoulombicRoundTrip.set(efficiencyCoulombicRoundTrip);

		input.catholyteTankCost.set(catholyteTankCost);
		input.anolyteTankCost.set(anolyteTankCost);
		input.tankFillLevel.set(tankFillLevel);

//		maintenance:
		input.catholyteReplacementFraction.set(catholyteReplacementFraction);
		input.anolyteReplacementFraction.set(anolyteReplacementFraction);

		input.interestRate.set(interestRate);
		input.operationalLifetime.set(operationalLifetime);

		input.threshold.set(threshold);

		input.catholyteReplacementCostMass.set(catholyteReplacementCostMass);
		input.anolyteReplacementCostMass.set(anolyteReplacementCostMass);
		input.catholyteReplacementCost.set(catholyteReplacementCost);
		input.anolyteReplacementCost.set(anolyteReplacementCost);

		input.stackOperationalTime.set(stackOperationalTime);





//		calculation:
		CostAnalysisCalculation calculation = new CostAnalysisCalculation();
		ObjCostAnalysisOutput result = calculation.calculation(input);

//		power:
		// stack:
		separatorStackCost = result.separatorStackCost.getValue();
		electrodeStackCost = result.electrodeStackCost.getValue();
		bipolarPlateStackCost = result.bipolarPlateStackCost.getValue();
//		cellFramesStackCost = result.cellFramesStackCost.getValue();
//		sealsStackCost = result.sealsStackCost.getValue();
		cellFramesSealsStackCost = result.cellFramesSealsStackCost.getValue();
		currentCollectorStackCost = result.currentCollectorStackCost.getValue();
		stackFrameStackCost = result.stackFrameStackCost.getValue();
		assemblyStackCost = result.assemblyStackCost.getValue();
		stackCostTotal = result.stackCostTotal.getValue();
		stackCost = result.stackCost.getValue();
		// stoichiometric coeff:
		catholyteActMatCoeff = result.catholyteActMatCoeff.getValue();
		anolyteActMatCoeff = result.anolyteActMatCoeff.getValue();
		z = result.z.getValue();
		//Ecell:
		stEcell = result.stEcell.getValue();
		//U, ocv:
		double anolyteActMatRedConc = result.anolyteActMatRedConc.getValue();
		double anolyteActMatOxConc = result.anolyteActMatOxConc.getValue();
		double catholyteActMatRedConc = result.catholyteActMatRedConc.getValue();
		double catholyteActMatOxConc = result.catholyteActMatOxConc.getValue();
		protonConcElectrolyte = result.protonConc.getValue();
		catholyteReversiblePotential = result.catholyteReversiblePotential.getValue();
		anolyteReversiblePotential = result.anolyteReversiblePotential.getValue();
//		catholyteReversiblePotentialPh0 = result.catholyteReversiblePotentialPh0.getValue();
//		anolyteReversiblePotentialPh0 = result.anolyteReversiblePotentialPh0.getValue();
		diffActMatElmitanodeCatholyte = result.diffActMatElmitanodeCatholyte.getValue();
		diffActMatElmitanodeAnolyte = result.diffActMatElmitanodeAnolyte.getValue();
		standardElimitanode = result.standardElimitanode.getValue();
		reversibleVoltage = result.reversibleVoltage.getValue();
		// i0:
		catholyteExchangeCurrentDensity = result.catholyteExchangeCurrentDensity.getValue();
		anolyteExchangeCurrentDensity = result.anolyteExchangeCurrentDensity.getValue();
		// id:
		currentDensityDischarge = result.currentDensityDischarge.getValue();
		voltageDischarge = result.voltageDischarge.getValue();
		specificPowerDischarge = result.specificPowerDischarge.getValue();
		chargeTranserOverPotDischarge = result.chargeTranserOverPotDischarge.getValue();
		ohmicOverPotDischarge = result.ohmicOverPotDischarge.getValue();
		concentrationOverPotDischarge = result.concentrationOverPotDischarge.getValue();
		anolyteChargeTranserOverPotDischarge = result.anolyteChargeTranserOverPotDischarge.getValue();
		catholyteChargeTranserOverPotDischarge = result.catholyteChargeTranserOverPotDischarge.getValue();
		anolyteConcentrationOverPotDischarge = result.anolyteConcentrationOverPotDischarge.getValue();
		catholyteConcentrationOverPotDischarge = result.catholyteConcentrationOverPotDischarge.getValue();

		efficiencyVoltDischarge = result.efficiencyVoltDischarge.getValue();
		peakPowerRatio = result.peakPowerRatio.getValue();
		//A total:
		totalArea = result.totalArea.getValue();
		numberCells = result.numberCells.getValue();
		numberStacks = result.numberStacks.getValue();
		reactorCost = result.reactorCost.getValue();
		costBOP = result.costBOP.getValue();
		costPower = result.costPower.getValue();
//		electrolyte:
		anolyteSaltSol = result.anolyteSaltSol.getValue();
		anolyteActMatSol = result.anolyteActMatSol.getValue();
		catholyteSaltSol = result.catholyteSaltSol.getValue();
		catholyteActMatSol = result.catholyteActMatSol.getValue();
		catholyteActMatCost = result.catholyteActMatCost.getValue();
		anolyteActMatCost = result.anolyteActMatCost.getValue();

		ravg = result.ravg.getValue();
		bavg = result.bavg.getValue();
		tankCost = result.tankCost.getValue();
		costElectrolyte = result.costElectrolyte.getValue();
		costElectrolyteKWh = result.costElectrolyteKWh.getValue();

//		maintenance:
		maxReplacementFraction = result.maxReplacementFraction.getValue();
		electrolyteDegradationNPVCost = result.electrolyteDegradationNPVCost.getValue();
		electrolyteOperationalTime = result.electrolyteOperationalTime.getValue();
		double electrolyteReplacement = result.electrolyteReplacement.getValue();

		catholyteReplacementCost = result.catholyteReplacementCost.getValue();
		anolyteReplacementCost = result.anolyteReplacementCost.getValue();
		replacementNPVCost = result.replacementNPVCost.getValue();

		double pumpCosts = result.pumpCosts.getValue();
		stackExchangeCost = result.stackExchangeCost.getValue();
		stackExchangeNPVCost = result.stackExchangeNPVCost.getValue();

		costMaintenance = result.costMaintenance.getValue();
		costCapital = result.costCapital.getValue();

		double anolyteEnergyDensity = result.anolyteEnergyDensity.getValue();
		double catholyteEnergyDensity = result.catholyteEnergyDensity.getValue();
		energyDensity = result.energyDensity.getValue();
//		anolyteEnergyDensityKg = result.anolyteEnergyDensityKg.getValue();
//		catholyteEnergyDensityKg = result.catholyteEnergyDensityKg.getValue();
		energyDensityKg = result.energyDensityKg.getValue();

		double cActiveAnolyte = result.cActiveAnolyte.getValue();
		double cActiveCatholyte = result.cActiveCatholyte.getValue();

		anolyteCostTotal = result.anolyteCostTotal.getValue();
		anolyteCostTotalKAh = result.anolyteCostTotalKAh.getValue();
		catholyteCostTotal = result.catholyteCostTotal.getValue();
		catholyteCostTotalKAh = result.catholyteCostTotalKAh.getValue();

		reversibleCellVoltageEl = reversibleVoltage;
		standardCellVoltagePow = stEcell;

		ArrayList_UdvsId.clear();
		ArrayList_PdvsId.clear();
		ArrayList_UdvsSOC.clear();

		ArrayList_UdvsId.addAll(result.ArrayList_UdvsId);
		ArrayList_PdvsId.addAll(result.ArrayList_PdvsId);
		ArrayList_UdvsSOC.addAll(result.ArrayList_UdvsSOC);
	}

	public void output() {
		
//		CostAnalysisToolController.this.exportUdvsIdCurve();

		//Output:

		//electrolyte:
		txtfdAnolyteActMatCost.setText(String.valueOf(anolyteActMatCost));
		txtfdAnolyteActMatCoeff.setText(String.valueOf(anolyteActMatCoeff));
		txtfdAnolyteActMatMolMass.setText(String.valueOf(anolyteActMatMolMass));
		txtfdAnolyteSocRange.setText(String.valueOf(anolyteSocRange));
		txtfdAnolyteActMatNumberElectron.setText(String.valueOf(anolyteActMatNumberElectron));
		txtfdAnolyteActMatNumberProton.setText(String.valueOf(anolyteActMatNumberProton));
		txtfdAnolyteActMatSol.setText(String.valueOf(anolyteActMatSol));
		txtfdAnolyteSaltMolMass.setText(String.valueOf(anolyteSaltMolMass));
		txtfdAnolyteSaltSol.setText(String.valueOf(anolyteSaltSol));
		txtfdAnolytePotential.setText(String.valueOf(anolyteReversiblePotential));

		txtfdCatholyteActMatCost.setText(String.valueOf(catholyteActMatCost));
		txtfdCatholyteActMatCoeff.setText(String.valueOf(catholyteActMatCoeff));
		txtfdCatholyteActMatMolMass.setText(String.valueOf(catholyteActMatMolMass));
		txtfdCatholyteSocRange.setText(String.valueOf(catholyteSocRange));
		txtfdCatholyteActMatNumberElectron.setText(String.valueOf(catholyteActMatNumberElectron));
		txtfdCatholyteActMatNumberProton.setText(String.valueOf(catholyteActMatNumberProton));
		txtfdCatholyteActMatNumberElectron.setText(String.valueOf(catholyteActMatNumberElectron));
		txtfdCatholyteActMatSol.setText(String.valueOf(catholyteActMatSol));
		txtfdCatholyteSaltMolMass.setText(String.valueOf(catholyteSaltMolMass));
		txtfdCatholyteSaltSol.setText(String.valueOf(catholyteSaltSol));
		txtfdCatholytePotential.setText(String.valueOf(catholyteReversiblePotential));

		txtfdReversibleCellVoltageEl.setText(String.valueOf(reversibleCellVoltageEl) + " (" + String.valueOf(catholyteReversiblePotential)
				+ " - " + String.valueOf(anolyteReversiblePotential) + ")");
		txtfdRavg.setText(String.valueOf(ravg));
		txtfdBavg.setText(String.valueOf(bavg));
		txtfdEfficiencySysDischarge.setText(String.valueOf(efficiencySysDischarge));
		txtfdEfficiencyVoltDischarge.setText(String.valueOf(efficiencyVoltDischarge));
		txtfdEfficiencyCoulombicRoundTrip.setText(String.valueOf(efficiencyCoulombicRoundTrip));

		txtfdSaltCost.setText(String.valueOf(anolyteSaltCost));
		txtfdSolventCost.setText(String.valueOf(anolyteSolventCost));
		txtfdSolventDensity.setText(String.valueOf(anolyteSolventDensity));
		txtfdTemp.setText(String.valueOf(temp));
		txtfdPH.setText(String.valueOf(pHElectrolyte));

		txtfdAnolyteTankCost.setText(String.valueOf(anolyteTankCost));
		txtfdCatholyteTankCost.setText(String.valueOf(catholyteTankCost));
		txtfdTankFillLevel.setText(String.valueOf(tankFillLevel));
		txtfdTankCost.setText(String.valueOf(tankCost));


		//power:
		txtfdStackCost.setText(String.valueOf(stackCost));
		txtfbASR.setText(String.valueOf(asr));
		txtfbTimeDischarge.setText(String.valueOf(timeDischarge));
		txtfbPowerDischarge.setText(String.valueOf(powerDischarge));
		txtfbEnergyCapacity.setText(String.valueOf(energyCapacity));
		txtfbSocDischarge.setText(String.valueOf(stateOfCharge));

		txtfbCurrentDensityDischarge.setText(String.valueOf(currentDensityDischarge));
		txtfbVoltageDischarge.setText(String.valueOf(voltageDischarge));
		txtfdStandardCellVoltagePow.setText(String.valueOf(standardCellVoltagePow + " (" + String.valueOf(catholyteStandardPotential)
		+ " - " + String.valueOf(anolyteStandardPotential) + ")"));
		txtfbReversibleVoltage.setText(String.valueOf(reversibleVoltage) + " (" + String.valueOf(catholyteReversiblePotential)
				+ " - " + String.valueOf(anolyteReversiblePotential) + ")");
		txtfbChargeTranserOverPotDischarge.setText(String.valueOf(chargeTranserOverPotDischarge));
		txtfbOhmicOverPotDischarge.setText(String.valueOf(ohmicOverPotDischarge));
		txtfbConcentrationOverPotDischarge.setText(String.valueOf(concentrationOverPotDischarge));

		txtfbAnolyteReactionRate.setText(String.valueOf(anolyteReactionRate));
		txtfbAnolyteTransferCoeff.setText(String.valueOf(anolyteTransferCoeff));
		txtfbCatholyteReactionRate.setText(String.valueOf(catholyteReactionRate));
		txtfbCatholyteTransferCoeff.setText(String.valueOf(catholyteTransferCoeff));
		txtfbAnolyteExchangeCurrentDensity.setText(String.valueOf(anolyteExchangeCurrentDensity));
		txtfbCatholyteExchangeCurrentDensity.setText(String.valueOf(catholyteExchangeCurrentDensity));
		txtfbAnolyteChargeTranserOverPotDischarge.setText(String.valueOf(anolyteChargeTranserOverPotDischarge));
		txtfbCatholyteChargeTranserOverPotDischarge.setText(String.valueOf(catholyteChargeTranserOverPotDischarge));
		txtfbAnolyteConcentrationOverPotDischarge.setText(String.valueOf(anolyteConcentrationOverPotDischarge));
		txtfbCatholyteConcentrationOverPotDischarge.setText(String.valueOf(catholyteConcentrationOverPotDischarge));
		
		txtfbDiaFiber.setText(String.valueOf(dFiber));
		txtfbFlowVelocity.setText(String.valueOf(flowVelocity));
		txtfbAnolyteDiffusionCoeff.setText(String.valueOf(anolyteDiffusionCoeff));
		txtfbCatholyteDiffusionCoeff.setText(String.valueOf(catholyteDiffusionCoeff));

		txtfdCellArea.setText(String.valueOf(cellArea));
		txtfdTotalArea.setText(String.valueOf(totalArea));
		txtfdNumberCellsPerStack.setText(String.valueOf(numberCellsPerStack));
		txtfdNumberCells.setText(String.valueOf(numberCells));
		txtfdNumberStacks.setText(String.valueOf(numberStacks));
		txtfdReactorCost.setText(String.valueOf(reactorCost));

		txtfbCostBOP.setText(String.valueOf(costBOP));
		txtfbCostAdd.setText(String.valueOf(costAdd));



		//maintenance:
		txtfdOperationalLifetime.setText(String.valueOf(operationalLifetime));
		txtfdInterestRate.setText(String.valueOf(interestRate));
		txtfdAnolyteReplacementFraction.setText(String.valueOf(anolyteReplacementFraction));
		txtfdCatholyteReplacementFraction.setText(String.valueOf(catholyteReplacementFraction));
		txtfdMaxReplacementFraction.setText(String.valueOf(maxReplacementFraction));
		txtfdElectrolyteDegradationNPVCost.setText(String.valueOf(electrolyteDegradationNPVCost));

		txtfdAnolyteReplacementCostMass.setText(String.valueOf(anolyteReplacementCostMass));
		txtfdAnolyteReplacementCost.setText(String.valueOf(anolyteReplacementCost));
		txtfdCatholyteReplacementCostMass.setText(String.valueOf(catholyteReplacementCostMass));
		txtfdCatholyteReplacementCost.setText(String.valueOf(catholyteReplacementCost));
		txtfdElectrolyteOperationalTime.setText(String.valueOf(electrolyteOperationalTime));
		txtfdThreshold.setText(String.valueOf(threshold));
		txtfdReplacementNPVCost.setText(String.valueOf(replacementNPVCost));

		txtfdStackExchangeCost.setText(String.valueOf(stackExchangeCost));
		txtfdStackOperationalTime.setText(String.valueOf(stackOperationalTime));
		txtfdStackExchangeNPVCost.setText(String.valueOf(stackExchangeNPVCost));


		//results:
		txtfdAnolyteCostTotal.setText(String.valueOf(anolyteCostTotal));
		txtfdAnolyteCostTotalKAh.setText(String.valueOf(anolyteCostTotalKAh));

		txtfdCatholyteCostTotal.setText(String.valueOf(catholyteCostTotal));
		txtfdCatholyteCostTotalKAh.setText(String.valueOf(catholyteCostTotalKAh));

		txtfdEnergyDensity.setText(String.valueOf(energyDensity));

		txtfdCostElectrolyte.setText(String.valueOf(costElectrolyte));
		txtfdCostElectrolyteKWh.setText(String.valueOf(costElectrolyteKWh));
		txtfdCostPower.setText(String.valueOf(costPower));
		txtfdCostMaintenance.setText(String.valueOf(costMaintenance));

		txtfdCostCapital.setText(String.valueOf(costCapital));
		
		txtfdCostElectrolyteKWh1.setText(String.valueOf(costElectrolyteKWh));
		txtfdCostPower1.setText(String.valueOf(costPower));
		txtfdCostMaintenance1.setText(String.valueOf(costMaintenance));
		
		txtfdCostCapital1.setText(String.valueOf(costCapital));


//		Plot:
		//Spiderweb:
		double cTotal = costCapital;
		double cPowerkWh = costPower/timeDischarge;
		double cElectrolyte = costElectrolyteKWh;
		double cNPV = costMaintenance;

		titleSW = "";
		String WPmode = "";
		if (peakPowerWorkingPoint) {
			WPmode = "WP-pP";
		}
		if (voltageEfficiencyWorkingPoint) {
			WPmode = "WP-V";
		}
		if (currentDensityWorkingPoint) {
			WPmode = "WP-I";
		}
		if (specPowerWorkingPoint) {
			WPmode = "WP-sP";
		}

		String PolMode = "";
		if (ohmicMode && chargeTransferMode && transportationLimitationMode) {
			PolMode = "Pol: ohm, ct, conc";
		}
		if (!ohmicMode && chargeTransferMode && transportationLimitationMode) {
			PolMode = "Pol: ct, conc";
		}
		if (ohmicMode && !chargeTransferMode && transportationLimitationMode) {
			PolMode = "Pol: ohm, conc";
		}
		if (ohmicMode && chargeTransferMode && !transportationLimitationMode) {
			PolMode = "Pol: ohm, ct";
		}
		if (!ohmicMode && !chargeTransferMode && transportationLimitationMode) {
			PolMode = "Pol: conc"; 
		}
		if (ohmicMode && !chargeTransferMode && !transportationLimitationMode) {
			PolMode = "Pol: ohm";
		}
		if (!ohmicMode && chargeTransferMode && !transportationLimitationMode) {
			PolMode = "Pol: ct";
		}
		if (!ohmicMode && !chargeTransferMode && !transportationLimitationMode) {
			PolMode = "Pol: -";
		}

		if (standardSelected) {
			titleSW = inputLeftActiveMaterial+"; "+WPmode+"; "+PolMode;
		}

		if (!standardSelected) {
			titleSW = inputLeftActiveMaterial+" / " +inputRightActiveMaterial+"; "+WPmode+"; "+PolMode;
		}

		ObjSpiderWebData objSpiderWebData = new ObjSpiderWebData();
		objSpiderWebData.title.set(titleSW);
		objSpiderWebData.cTotal.set(cTotal);
		objSpiderWebData.cPower.set(cPowerkWh);
		objSpiderWebData.cElectrolyte.set(cElectrolyte);
		objSpiderWebData.cNPV.set(cNPV);

		DataList.add(objSpiderWebData);

		CostAnalysisToolController.this.drawNewChart(newSpiderWebChart, DataList);





		//Scatter Ud vs SOC:
//		DefaultCategoryDataset lineChartUdvsSOCDataset = new DefaultCategoryDataset();
		XYSeries lineChartUdvsSOCDataset = new XYSeries ("SOC trend");

		for (double i = 0.001; i<=1; i+=0.001) {
			ObjScatterUdvsSOC objScatterUdvsSOC = new ObjScatterUdvsSOC();
			objScatterUdvsSOC.title.set("");


			//U, ocv / V
			double anolyteRedConc = anolyteActMatConc * i;
			double anolyteOxConc = anolyteActMatConc * (1-i);
			double catholyteRedConc = catholyteActMatConc * (1-i);
			double catholyteOxConc = catholyteActMatConc * i;
			double Uocv = 0;
			double cathRevPot = 0;
			double anRevPot = 0;

			// full cell:

//
//			double pHLeft = objActiveMaterialLeft.PH.getValue();
//			double protonConcLeft = Math.pow(10, -pHLeft);
//			double pHRight = objActiveMaterialLeft.PH.getValue();
//			double protonConcRight = Math.pow(10, -pHRight);
//			double leftReversiblePotential = 0;
//			double rightReversiblePotential = 0;
//
//			double pHStandard = 0;
//			double protonConcStandard = Math.pow(10, -pHStandard);
//
//			double DeltaProtonConcCatholyte = protonConcStandard - protonConcCatholyte ;
//			double DeltaProtonConcAnolyte = protonConcStandard - protonConcAnolyte;
//
//			double leftFormalPotential = 0;
//			double rightFormalPotential = 0;
//			leftFormalPotential = objActiveMaterialLeft.E.getValue()-G*temp/(objActiveMaterialLeft.N.getValue()*F)*
//			Math.log(Math.pow(DeltaProtonConcLeft,objActiveMaterialLeft.NumberH.getValue()));
//
//
//			if (!standardSelected) {
//				rightFormalPotential = objActiveMaterialRight.E.getValue()-G*temp/(objActiveMaterialRight.N.getValue()*F)*
//						Math.log(Math.pow(DeltaProtonConcRight,objActiveMaterialRight.NumberH.getValue()));
//			}

			
//			TODO Add this as method in calculation class!
			if (!standardSelected) {
				cathRevPot = catholyteStandardPotential - G*temp/(catholyteActMatNumberElectron*F)*
						Math.log(Math.pow(catholyteRedConc,catholyteActMatCoeff)/(Math.pow(catholyteOxConc,catholyteActMatCoeff)*Math.pow(protonConcElectrolyte,catholyteActMatNumberProton)));
				anRevPot = anolyteStandardPotential - G*temp/(anolyteActMatNumberElectron*F)*
						Math.log(Math.pow(anolyteRedConc,anolyteActMatCoeff)/(Math.pow(anolyteOxConc,anolyteActMatCoeff)*Math.pow(protonConcElectrolyte,anolyteActMatNumberProton)));

				double q = Math.pow(catholyteRedConc,catholyteActMatCoeff)/
						(Math.pow(catholyteOxConc,catholyteActMatCoeff)*Math.pow(protonConcElectrolyte,catholyteActMatNumberProton))*
						(Math.pow(anolyteOxConc,anolyteActMatCoeff)*Math.pow(protonConcElectrolyte,anolyteActMatNumberProton))/
						Math.pow(anolyteRedConc,anolyteActMatCoeff);

				Uocv = stEcell - G*rT/(z*F)*Math.log(q);

			//standard:
			} else {
				//Anolyte = standard:
				if (anolyteStandard) {
					anRevPot = -0.6 - G*temp/(2*F)*Math.log(1/Math.pow(protonConcElectrolyte,2));

					cathRevPot = catholyteStandardPotential - G*temp/(catholyteActMatNumberElectron*F)*
							Math.log(Math.pow(catholyteRedConc,catholyteActMatCoeff)/(Math.pow(catholyteOxConc,catholyteActMatCoeff)*Math.pow(protonConcElectrolyte,catholyteActMatNumberProton)));
				}
				//Catholyte = standard:
				if (catholyteStandard) {
					cathRevPot = 1.6 - G*temp/(4*F)*Math.log(1/Math.pow(protonConcElectrolyte,4));

					anRevPot= anolyteStandardPotential - G*temp/(anolyteActMatNumberElectron*F)*
							Math.log(Math.pow(anolyteRedConc,anolyteActMatCoeff)/(Math.pow(anolyteOxConc,anolyteActMatCoeff)*Math.pow(protonConcElectrolyte,anolyteActMatNumberProton)));
				}
				Uocv = cathRevPot - anRevPot;
			}

			double UctCatholyte = 0;
			double UctAnolyte = 0;
			UctCatholyte = catholyteChargeTranserOverPotDischarge;
			UctAnolyte = anolyteChargeTranserOverPotDischarge;



			double Uct = 0;
			if (chargeTransferMode) {
				Uct = catholyteChargeTranserOverPotDischarge + anolyteChargeTranserOverPotDischarge;
			}

			double Uohm = 0;
			if (ohmicMode) {
				 Uohm = ohmicOverPotDischarge;
			}
			
			double UConc = 0;
			if (transportationLimitationMode) {
				UConc = catholyteConcentrationOverPotDischarge + anolyteConcentrationOverPotDischarge;
			}

			double Ud = Uocv-Uct-Uohm-UConc;

			objScatterUdvsSOC.Ud.set(Ud);
			objScatterUdvsSOC.soc.set(i);
			lineChartUdvsSOCDataset.add( objScatterUdvsSOC.soc.getValue(), objScatterUdvsSOC.Ud.getValue());
		}

		ObjScatterUdvsSOC objScatterUdvsSOCresult = new ObjScatterUdvsSOC();
		objScatterUdvsSOCresult.title.set("calculation");
		objScatterUdvsSOCresult.Ud.set(voltageDischarge);
		objScatterUdvsSOCresult.soc.set(stateOfCharge);

		XYSeries resultData = new XYSeries("calculation");

		resultData.add(objScatterUdvsSOCresult.soc.getValue(), objScatterUdvsSOCresult.Ud.getValue());

		XYSeriesCollection SOCDataset = new XYSeriesCollection();
		SOCDataset.addSeries(resultData);
		SOCDataset.addSeries(lineChartUdvsSOCDataset);

		CostAnalysisToolController.this.drawNewChartLineChartSOC(newScatterChart, SOCDataset);




		//Scatter Ud vs Id:
		XYSeries lineChartUdvsIdDataset = new XYSeries ("Id trend");

		for (ObjScatterUdvsId element : ArrayList_UdvsId) {
			lineChartUdvsIdDataset.add(element.id.getValue(), element.Ud.getValue());
		}

		ObjScatterUdvsId objScatterUdvsIdresult = new ObjScatterUdvsId();
		objScatterUdvsIdresult.title.set("calculation");
		objScatterUdvsIdresult.Ud.set(voltageDischarge);
		objScatterUdvsIdresult.id.set(currentDensityDischarge);

		XYSeries resultDataId = new XYSeries("calculation");

		resultDataId.add(objScatterUdvsIdresult.id.getValue(), objScatterUdvsIdresult.Ud.getValue());

		XYSeriesCollection IdDataset = new XYSeriesCollection();
		IdDataset.addSeries(resultDataId);
		IdDataset.addSeries(lineChartUdvsIdDataset);

		CostAnalysisToolController.this.drawNewChartLineChartId(newScatterChart2, IdDataset);





		//Scatter Pd vs Id:
		XYSeries lineChartPdvsIdDataset = new XYSeries ("Id trend");

		for (ObjScatterPdvsId element : ArrayList_PdvsId) {
			lineChartPdvsIdDataset.add(element.id.getValue(), element.Pd.getValue());
		}

		ObjScatterPdvsId objScatterPdvsIdresult = new ObjScatterPdvsId();
		objScatterPdvsIdresult.title.set("calculation");
		objScatterPdvsIdresult.Pd.set(specificPowerDischarge);
		objScatterPdvsIdresult.id.set(currentDensityDischarge);

		XYSeries resultDataPdId = new XYSeries("calculation");

		resultDataPdId.add(objScatterPdvsIdresult.id.getValue(), objScatterPdvsIdresult.Pd.getValue());

		XYSeriesCollection PdIdDataset = new XYSeriesCollection();
		PdIdDataset.addSeries(resultDataPdId);
		PdIdDataset.addSeries(lineChartPdvsIdDataset);

		CostAnalysisToolController.this.drawNewChartLineChartPdId(newScatterChart3, PdIdDataset);

		calculationCombo = false;
	}



	@FXML
	public void export(ActionEvent event) {
		CostAnalysisToolController.this.exportData();
	}



	public void exportData() {
		String exportSelection = comboExportSelection.getSelectionModel().getSelectedItem().toString();
		int i = 0;
		if(standardSelected) {
//			TODO: More options?
			if (exportSelection.equals("all_details_Nafion")) {
				comboStack.setValue("0.06m2_Nafion, null, null"); //TODO add/refresh here new options!
			}
			if (exportSelection.equals("all_details_SizeSelective")) {
				comboStack.setValue("0.06m2_SizeSelective, null, null"); //TODO add/refresh here new options!
			}

		try {
			ResultSet resExport = Database.selectData("activeMaterial");

			ArrayList<ObjExportSelection> exportDataSelection = new ArrayList();

	    	while (resExport.next()) {
	    		ObjExportSelection selection = new ObjExportSelection();

	    		selection.dataAbbreviation.set(resExport.getString("ABBREVIATION"));
	    		selection.dataSolvent.set(resExport.getString("Solvent"));

				String dataSaltSQL =  resExport.getString("Salt") +", "+ resExport.getString("Saltc").toString() +"M";

	    		selection.dataSaltSQL.set(dataSaltSQL);

	    		exportDataSelection.add(selection);
	    	}

			ArrayList<ObjCostAnalysisOutput> exportDataList = new ArrayList();

	    	for (int counter = 0; counter < exportDataSelection.size(); counter++) {

				ObjCostAnalysisOutput data = new ObjCostAnalysisOutput();

	    		comboLeftActiveMaterial.setValue(exportDataSelection.get(counter).dataAbbreviation.getValue());
	    		comboLeftSolvent.setValue(exportDataSelection.get(counter).dataSolvent.getValue());
				comboLeftSalt.setValue(exportDataSelection.get(counter).dataSaltSQL.getValue());

				CostAnalysisToolController.this.calculation();

				data.catholyteAbbreviation.set(catholyteAbbreviation);
				data.catholyteActMatMolMass.set(catholyteActMatMolMass);
				data.catholyteActMatNumberElectron.set(catholyteActMatNumberElectron);
				data.catholyteActMatNumberProton.set(catholyteActMatNumberProton);
				data.catholyteActMatConc.set(catholyteActMatConc);
				data.catholyteActMatSol.set(catholyteActMatSol);
				data.catholyteReversiblePotential.set(catholyteReversiblePotential);
//				data.catholyteReversiblePotentialPh0.set(catholyteReversiblePotentialPh0);
				data.diffActMatElmitanodeCatholyte.set(diffActMatElmitanodeCatholyte);
				data.standardElimitanode.set(standardElimitanode);
				
				data.catholyteActMatCost.set(catholyteActMatCost);
				data.catholyteReactionRate.set(catholyteReactionRate);
				data.catholyteTransferCoeff.set(catholyteTransferCoeff);
				data.catholyteSolvent.set(catholyteSolvent);
				data.catholyteSolventCost.set(catholyteSolventCost);
				data.catholyteSalt.set(catholyteSalt);
				data.catholyteSaltCost.set(catholyteSaltCost);
				data.catholyteSolventDensity.set(catholyteSolventDensity);
				data.catholyteSaltConc.set(catholyteSaltConc);
				data.catholyteSaltSol.set(catholyteSaltSol);

				data.anolyteAbbreviation.set(anolyteAbbreviation);
				data.anolyteActMatMolMass.set(anolyteActMatMolMass);
				data.anolyteActMatNumberElectron.set(anolyteActMatNumberElectron);
				data.anolyteActMatNumberProton.set(anolyteActMatNumberProton);
				data.anolyteActMatConc.set(anolyteActMatConc);
				data.anolyteActMatSol.set(anolyteActMatSol);
				data.anolyteReversiblePotential.set(anolyteReversiblePotential);
//				data.anolyteReversiblePotentialPh0.set(anolyteReversiblePotentialPh0);
				data.diffActMatElmitanodeAnolyte.set(diffActMatElmitanodeAnolyte);
				data.standardElimitanode.set(standardElimitanode);
				data.anolyteActMatCost.set(anolyteActMatCost);
				data.anolyteReactionRate.set(anolyteReactionRate);
				data.anolyteTransferCoeff.set(anolyteTransferCoeff);
				data.anolyteSolvent.set(anolyteSolvent);
				data.anolyteSolventCost.set(anolyteSolventCost);
				data.anolyteSalt.set(anolyteSalt);
				data.anolyteSaltCost.set(anolyteSaltCost);
				data.anolyteSolventDensity.set(anolyteSolventDensity);
				data.anolyteSaltConc.set(anolyteSaltConc);
				data.anolyteSaltSol.set(anolyteSaltSol);

				data.reversibleVoltage.set(reversibleVoltage);
				data.temp.set(temp);
				data.pH.set(pHElectrolyte);
				data.tankCost.set(tankCost);

				data.stackCost.set(stackCost);
				data.asr.set(asr);
				data.timeDischarge.set(timeDischarge);
				data.powerDischarge.set(powerDischarge);
				data.energyCapacity.set(energyCapacity);

				data.currentDensityDischarge.set(currentDensityDischarge);
				data.voltageDischarge.set(voltageDischarge);
				data.stEcell.set(stEcell);
				data.reversibleVoltage.set(reversibleVoltage);
				data.chargeTranserOverPotDischarge.set(chargeTranserOverPotDischarge);
				data.ohmicOverPotDischarge.set(ohmicOverPotDischarge);

				data.cellArea.set(cellArea);
				data.totalArea.set(totalArea);
				data.numberCellsPerStack.set(numberCellsPerStack);
				data.numberCells.set(numberCells);
				data.numberStacks.set(numberStacks);
				data.reactorCost.set(reactorCost);
				data.costBOP.set(costBOP);
				data.costAdd.set(costAdd);

				data.operationalLifetime.set(operationalLifetime);
				data.interestRate.set(interestRate);
				data.maxReplacementFraction.set(maxReplacementFraction);
				data.electrolyteDegradationNPVCost.set(electrolyteDegradationNPVCost);
				data.catholyteReplacementCostMass.set(catholyteReplacementCostMass);
				data.catholyteReplacementCost.set(catholyteReplacementCost);
				data.anolyteReplacementCostMass.set(anolyteReplacementCostMass);
				data.anolyteReplacementCost.set(anolyteReplacementCost);
				data.electrolyteOperationalTime.set(electrolyteOperationalTime);
				data.threshold.set(threshold);
				data.replacementNPVCost.set(replacementNPVCost);
				data.stackExchangeCost.set(stackExchangeCost);
				data.stackOperationalTime.set(stackOperationalTime);
				data.stackExchangeNPVCost.set(stackExchangeNPVCost);

				data.energyDensity.set(energyDensity);
				data.energyDensityKg.set(energyDensityKg);
				data.catholyteCostTotal.set(catholyteCostTotal);
				data.catholyteCostTotalKAh.set(catholyteCostTotalKAh);
				data.anolyteCostTotal.set(anolyteCostTotal);
				data.anolyteCostTotalKAh.set(anolyteCostTotalKAh);
				data.costElectrolyteKWh.set(costElectrolyteKWh);
				data.costPower.set(costPower);
				data.costMaintenance.set(costMaintenance);
				data.costCapital.set(costCapital);

				exportDataList.add(data);
	    	}


			CostAnalysisToolController.this.exportTableToTxt(exportDataList);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}


	}

	public void exportTableToTxt(ArrayList<ObjCostAnalysisOutput> output) {
		String exportSelection = comboExportSelection.getSelectionModel().getSelectedItem().toString();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
		LocalDateTime now = LocalDateTime.now();
		String time = dtf.format(now);
		
		String mode = "";
		if (specPowerWorkingPoint) {
			mode = "specPowerWorkingPoint";
		}
		if (currentDensityWorkingPoint) {
			mode = "currentDensityWorkingPoint";
		}
		if (peakPowerWorkingPoint) {
			mode = "peakPowerWorkingPoint";
		}
		if (voltageEfficiencyWorkingPoint) {
			mode = "voltageEfficiencyWorkingPoint";
		}
		
		String fileName = "export_"+ exportSelection+"_"+mode+"_"+time+".txt";
		FileWriter writer;

		try {
			File file = new File(fileName);
			writer = new FileWriter(file, true);
			
//TODO: Add Rynolds values etc.
			
//			Header:
			writer.write("No");
			writer.write("\t");
			writer.write("catholyte");
			writer.write("\t");
			writer.write("cath_M/g_mol^-1");
			writer.write("\t");
			writer.write("cath_#e-");
			writer.write("\t");
			writer.write("cath_#H+");
			writer.write("\t");
			writer.write("cath_conc/mol_L^-1");
			writer.write("\t");
			writer.write("cath_L/kg_kg^-1");
			writer.write("\t");
			writer.write("cath_potRev/V");
			writer.write("\t");
//			writer.write("cath_potRev-Ph0/V");
//			writer.write("\t");
			writer.write("diffActMatElmitanodeCatholyte/V");
			writer.write("\t");
			writer.write("standardElimitanode/V");
			writer.write("\t");
			writer.write("cath_C/$_kg^-1");
			writer.write("\t");
			writer.write("cath_k/cm_s^-1");
			writer.write("\t");
			writer.write("cath_alpha");
			writer.write("\t");
			writer.write("cath_solvent");
			writer.write("\t");
			writer.write("cath_Csolvent/$_kg^-1");
			writer.write("\t");
			writer.write("cath_salt");
			writer.write("\t");
			writer.write("cath_Csalt/$_kg^-1");
			writer.write("\t");
			writer.write("cath_electrolyteDensity/g_cm^-3");
			writer.write("\t");
			writer.write("cath_saltConc/mol_L^-1");
			writer.write("\t");
			writer.write("cath_saltL/kg_kg^-1");
			writer.write("\t");

			writer.write("anolyte");
			writer.write("\t");
			writer.write("an_M/g_mol^-1");
			writer.write("\t");
			writer.write("an_#e-");
			writer.write("\t");
			writer.write("an_#H+");
			writer.write("\t");
			writer.write("an_conc/mol_L^-1");
			writer.write("\t");
			writer.write("an_L/kg_kg^-1");
			writer.write("\t");
			writer.write("an_potRev/V");
			writer.write("\t");
//			writer.write("an_potRev-Ph0/V");
//			writer.write("\t");
			writer.write("diffActMatElmitanodeAnolyte/V");
			writer.write("\t");
			writer.write("standardElimitanode/V");
			writer.write("\t");
			writer.write("an_C/$_kg^-1");
			writer.write("\t");
			writer.write("an__k/cm_s^-1");
			writer.write("\t");
			writer.write("an_alpha");
			writer.write("\t");
			writer.write("an_solvent");
			writer.write("\t");
			writer.write("an_Csolvent/$_kg^-1");
			writer.write("\t");
			writer.write("an_salt");
			writer.write("\t");
			writer.write("an_Csalt/$_kg^-1");
			writer.write("\t");
			writer.write("an_electrolyteDensity/g_cm^-3");
			writer.write("\t");
			writer.write("an_saltConc/mol_L^-1");
			writer.write("\t");
			writer.write("an_saltL/kg_kg^-1");
			writer.write("\t");

			writer.write("Ucell/V");
			writer.write("\t");
			writer.write("T/K");
			writer.write("\t");
			writer.write("pH");
			writer.write("\t");
			writer.write("CTank_$_mol^-1");
			writer.write("\t");

			writer.write("Ca/$_m^-2");
			writer.write("\t");
			writer.write("ASR/Ohm_cm^2");
			writer.write("\t");
			writer.write("t,d/h");
			writer.write("\t");
			writer.write("P,d/MW");
			writer.write("\t");
			writer.write("E,d/MWh");
			writer.write("\t");

			writer.write("i,d/A_cm^-2");
			writer.write("\t");
			writer.write("U,d/V");
			writer.write("\t");
			writer.write("U,0/V");
			writer.write("\t");
			writer.write("U,ocv/V");
			writer.write("\t");
			writer.write("Eta,ct/V");
			writer.write("\t");
			writer.write("Eta,ohm/V");
			writer.write("\t");

			writer.write("Acell/m^2");
			writer.write("\t");
			writer.write("Atotal/m^2");
			writer.write("\t");
			writer.write("nCells(stack)");
			writer.write("\t");
			writer.write("nCells");
			writer.write("\t");
			writer.write("nStacks");
			writer.write("\t");
			writer.write("C,reactor/$");
			writer.write("\t");
			writer.write("C_BOP/$_kW^-1");
			writer.write("\t");
			writer.write("C_add/$_kW^-1");
			writer.write("\t");

			writer.write("operationalLifetime/y");
			writer.write("\t");
			writer.write("interestRate");
			writer.write("\t");
			writer.write("maxReplacementFraction/y^-1");
			writer.write("\t");
			writer.write("electrolyteDegradationNPVCost/$_kWh^-1");
			writer.write("\t");
			writer.write("catholyteReplacementCostMass/$_kg^-1");
			writer.write("\t");
			writer.write("catholyteReplacementCost/$_kWh^-1");
			writer.write("\t");
			writer.write("anolyteReplacementCostMass/$_kg^-1");
			writer.write("\t");
			writer.write("anolyteReplacementCost/$_kWh^-1");
			writer.write("\t");
			writer.write("electrolyteOperationalTime/y");
			writer.write("\t");
			writer.write("threshold");
			writer.write("\t");
			writer.write("replacementNPVCost/$_kWh^-1");
			writer.write("\t");
			writer.write("Mstack/$_kWh^-1");
			writer.write("\t");
			writer.write("stackOperationalTime/y");
			writer.write("\t");
			writer.write("C,npv,stack/$_kWh^-1");
			writer.write("\t");

			writer.write("energyDensity/Wh_L^-1");
			writer.write("\t");
			writer.write("energyDensityKg/Wh_Kg^-1");
			writer.write("\t");
			writer.write("CTotalActive,catholyte/$_kg^-1");
			writer.write("\t");
			writer.write("CTotalActive,catholyte/$_kAh^-1");
			writer.write("\t");
			writer.write("CTotalActive,anolyte/$_kg^-1");
			writer.write("\t");
			writer.write("CTotalActive,anolyte/$_kAh^-1");
			writer.write("\t");
			writer.write("CElectrolyte/$_kWh^-1");
			writer.write("\t");
			writer.write("CPower/$_kW^-1");
			writer.write("\t");
			writer.write("CMaintenance/$_kWh^-1");
			writer.write("\t");
			writer.write("Ctotal/$_kWh^-1");
			writer.write("\r\n");

			for (int counter = 0; counter < output.size(); counter++) {

				writer.write(String.valueOf(counter+1));
				writer.write("\t");

				writer.write(output.get(counter).catholyteAbbreviation.getValue().toString());
				writer.write("\t");
				writer.write(output.get(counter).catholyteActMatMolMass.getValue().toString());
				writer.write("\t");
				writer.write(output.get(counter).catholyteActMatNumberElectron.toString());
				writer.write("\t");
				writer.write(output.get(counter).catholyteActMatNumberProton.getValue().toString());
				writer.write("\t");
				writer.write(output.get(counter).catholyteActMatConc.getValue().toString());
				writer.write("\t");
				writer.write(output.get(counter).catholyteActMatSol.getValue().toString());
				writer.write("\t");
				writer.write(output.get(counter).catholyteReversiblePotential.getValue().toString());
				writer.write("\t");
//				writer.write(output.get(counter).catholyteReversiblePotentialPh0.getValue().toString());
//				writer.write("\t");
				writer.write(output.get(counter).diffActMatElmitanodeCatholyte.getValue().toString());
				writer.write("\t");
				writer.write(output.get(counter).standardElimitanode.getValue().toString());
				writer.write("\t");
				writer.write(output.get(counter).catholyteActMatCost.getValue().toString());
				writer.write("\t");
				writer.write(output.get(counter).catholyteReactionRate.getValue().toString());
				writer.write("\t");
				writer.write(output.get(counter).catholyteTransferCoeff.getValue().toString());
				writer.write("\t");
				writer.write(output.get(counter).catholyteSolvent.getValue().toString());
				writer.write("\t");
				writer.write(output.get(counter).catholyteSolventCost.getValue().toString());
				writer.write("\t");
				writer.write(output.get(counter).catholyteSalt.getValue().toString());
				writer.write("\t");
				writer.write(output.get(counter).catholyteSaltCost.getValue().toString());
				writer.write("\t");
				writer.write(output.get(counter).catholyteSolventDensity.getValue().toString());
				writer.write("\t");
				writer.write(output.get(counter).catholyteSaltConc.getValue().toString());
				writer.write("\t");
				writer.write(output.get(counter).catholyteSaltSol.getValue().toString());
				writer.write("\t");

				writer.write(output.get(counter).anolyteAbbreviation.getValue().toString());
				writer.write("\t");
				writer.write(output.get(counter).anolyteActMatMolMass.getValue().toString());
				writer.write("\t");
				writer.write(output.get(counter).anolyteActMatNumberElectron.getValue().toString());
				writer.write("\t");
				writer.write(output.get(counter).anolyteActMatNumberProton.getValue().toString());
				writer.write("\t");
				writer.write(output.get(counter).anolyteActMatConc.getValue().toString());
				writer.write("\t");
				writer.write(output.get(counter).anolyteActMatSol.getValue().toString());
				writer.write("\t");
				writer.write(output.get(counter).anolyteReversiblePotential.getValue().toString());
				writer.write("\t");
//				writer.write(output.get(counter).anolyteReversiblePotentialPh0.getValue().toString());
//				writer.write("\t");
				writer.write(output.get(counter).diffActMatElmitanodeAnolyte.getValue().toString());
				writer.write("\t");
				writer.write(output.get(counter).standardElimitanode.getValue().toString());
				writer.write("\t");
				writer.write(output.get(counter).anolyteActMatCost.getValue().toString());
				writer.write("\t");
				writer.write(output.get(counter).anolyteReactionRate.getValue().toString());
				writer.write("\t");
				writer.write(output.get(counter).anolyteTransferCoeff.getValue().toString());
				writer.write("\t");
				writer.write(output.get(counter).anolyteSolvent.getValue().toString());
				writer.write("\t");
				writer.write(output.get(counter).anolyteSolventCost.getValue().toString());
				writer.write("\t");
				writer.write(output.get(counter).anolyteSalt.getValue().toString());
				writer.write("\t");
				writer.write(output.get(counter).anolyteSaltCost.getValue().toString());
				writer.write("\t");
				writer.write(output.get(counter).anolyteSolventDensity.getValue().toString());
				writer.write("\t");
				writer.write(output.get(counter).anolyteSaltConc.getValue().toString());
				writer.write("\t");
				writer.write(output.get(counter).anolyteSaltSol.getValue().toString());
				writer.write("\t");

				writer.write(output.get(counter).reversibleVoltage.getValue().toString());
				writer.write("\t");
				writer.write(output.get(counter).temp.getValue().toString());
				writer.write("\t");
				writer.write(output.get(counter).pH.getValue().toString());
				writer.write("\t");
				writer.write(output.get(counter).tankCost.getValue().toString());
				writer.write("\t");

				writer.write(output.get(counter).stackCost.getValue().toString());
				writer.write("\t");
				writer.write(output.get(counter).asr.getValue().toString());
				writer.write("\t");
				writer.write(output.get(counter).timeDischarge.getValue().toString());
				writer.write("\t");
				writer.write(output.get(counter).powerDischarge.getValue().toString());
				writer.write("\t");
				writer.write(output.get(counter).energyCapacity.getValue().toString());
				writer.write("\t");

				writer.write(output.get(counter).currentDensityDischarge.getValue().toString());
				writer.write("\t");
				writer.write(output.get(counter).voltageDischarge.getValue().toString());
				writer.write("\t");
				writer.write(output.get(counter).stEcell.getValue().toString());
				writer.write("\t");
				writer.write(output.get(counter).reversibleVoltage.getValue().toString());
				writer.write("\t");
				writer.write(output.get(counter).chargeTranserOverPotDischarge.getValue().toString());
				writer.write("\t");
				writer.write(output.get(counter).ohmicOverPotDischarge.getValue().toString());
				writer.write("\t");

				writer.write(output.get(counter).cellArea.getValue().toString());
				writer.write("\t");
				writer.write(output.get(counter).totalArea.getValue().toString());
				writer.write("\t");
				writer.write(output.get(counter).numberCellsPerStack.getValue().toString());
				writer.write("\t");
				writer.write(output.get(counter).numberCells.getValue().toString());
				writer.write("\t");
				writer.write(output.get(counter).numberStacks.getValue().toString());
				writer.write("\t");
				writer.write(output.get(counter).reactorCost.getValue().toString());
				writer.write("\t");
				writer.write(output.get(counter).costBOP.getValue().toString());
				writer.write("\t");
				writer.write(output.get(counter).costAdd.getValue().toString());
				writer.write("\t");

				writer.write(output.get(counter).operationalLifetime.getValue().toString());
				writer.write("\t");
				writer.write(output.get(counter).interestRate.getValue().toString());
				writer.write("\t");
				writer.write(output.get(counter).maxReplacementFraction.getValue().toString());
				writer.write("\t");
				writer.write(output.get(counter).electrolyteDegradationNPVCost.getValue().toString());
				writer.write("\t");
				writer.write(output.get(counter).catholyteReplacementCostMass.getValue().toString());
				writer.write("\t");
				writer.write(output.get(counter).catholyteReplacementCost.getValue().toString());
				writer.write("\t");
				writer.write(output.get(counter).anolyteReplacementCostMass.getValue().toString());
				writer.write("\t");
				writer.write(output.get(counter).anolyteReplacementCost.getValue().toString());
				writer.write("\t");
				writer.write(output.get(counter).electrolyteOperationalTime.getValue().toString());
				writer.write("\t");
				writer.write(output.get(counter).threshold.getValue().toString());
				writer.write("\t");
				writer.write(output.get(counter).replacementNPVCost.getValue().toString());
				writer.write("\t");
				writer.write(output.get(counter).stackExchangeCost.getValue().toString());
				writer.write("\t");
				writer.write(output.get(counter).stackOperationalTime.getValue().toString());
				writer.write("\t");
				writer.write(output.get(counter).stackExchangeNPVCost.getValue().toString());
				writer.write("\t");

				writer.write(output.get(counter).energyDensity.getValue().toString());
				writer.write("\t");
				writer.write(output.get(counter).energyDensityKg.getValue().toString());
				writer.write("\t");
				writer.write(output.get(counter).catholyteCostTotal.getValue().toString());
				writer.write("\t");
				writer.write(output.get(counter).catholyteCostTotalKAh.getValue().toString());
				writer.write("\t");
				writer.write(output.get(counter).anolyteCostTotal.getValue().toString());
				writer.write("\t");
				writer.write(output.get(counter).anolyteCostTotalKAh.getValue().toString());
				writer.write("\t");
				writer.write(output.get(counter).costElectrolyteKWh.getValue().toString());
				writer.write("\t");
				writer.write(output.get(counter).costPower.getValue().toString());
				writer.write("\t");
				writer.write(output.get(counter).costMaintenance.getValue().toString());
				writer.write("\t");
				writer.write(output.get(counter).costCapital.getValue().toString());
				writer.write("\r\n");
			}
			writer.close();

			System.out.println("Export successful!");

		} catch (IOException e) {

			e.printStackTrace(); }

	}

	@FXML
	public void btnCalculate(ActionEvent event) {
		CostAnalysisToolController.this.calculation();
		CostAnalysisToolController.this.output();
	}



	@FXML
	public void btnStackSelection(ActionEvent event) {
		CostAnalysisToolController.this.stackSelection();
	}
	
	

	public void exportSA() {
		
		CostAnalysisToolController.this.checkSpecPower();

		System.out.println("SA export");

	    ArrayList<ObjFileImport> fileImport = new ArrayList();

		//Create a file chooser
		final JFileChooser fc = new JFileChooser();

		//In response to a button click:
		fc.showOpenDialog(null);


	    File f = new File(fc.getSelectedFile().getAbsoluteFile().toString());
	    System.out.println(f);
	    
	    String mode = "allParametersMode";
//	    String mode = "stackMode";


	    // test if file is existing:
	    if (f.exists() && f.isFile()) {
	    	System.out.println(f  + " exists");
	        BufferedReader br = null;
	        FileReader fr = null;

            try {
				fr = new FileReader(f);
	            br = new BufferedReader(fr);

	            String l;

	            // read data:
					while ((l = br.readLine()) != null) {
					    // split at tabs:
						
// TODO: Split customizable?
					    String[] col = l.split(",");

					    ObjFileImport importValues = new ObjFileImport();
					    
					    if (mode == "allParametersMode") {
					    	importValues.timeDischarge.set(Double.valueOf(col[0]));
						    importValues.energyCapacity.set(Double.valueOf(col[1]));					
						    importValues.costAdd.set(Double.valueOf(col[2]));
						    importValues.efficiencySysDischarge.set(Double.valueOf(col[3]));
						    importValues.efficiencyCoulombicRoundTrip.set(Double.valueOf(col[4]));
						    importValues.numberCellsPerStack.set(Double.valueOf(col[5]));
						    importValues.cellArea.set(Double.valueOf(col[6]));
						 
						    importValues.specificPowerDischarge.set(Double.valueOf(col[7]));		
						    importValues.actMatReversiblePotential.set(Double.valueOf(col[8]));
						    importValues.actMatReactionRate.set(Double.valueOf(col[9]));
						    importValues.actMatTransferCoeff.set(Double.valueOf(col[10]));
						    importValues.actMatDiffusionCoeff.set(Double.valueOf(col[11]));
						    importValues.actMatConc.set(Double.valueOf(col[12]));
						    importValues.dFiber.set(Double.valueOf(col[13]));
						    importValues.solventViscosity.set(Double.valueOf(col[14]));
						    importValues.solventDensity.set(Double.valueOf(col[15]));
						    importValues.flowVelocity.set(Double.valueOf(col[16]));
						    importValues.asr.set(Double.valueOf(col[17]));
						    importValues.costBOP.set(Double.valueOf(col[18]));
						    importValues.actMatCost.set(Double.valueOf(col[19]));
						    importValues.saltCost.set(Double.valueOf(col[20]));
						    importValues.solventCost.set(Double.valueOf(col[21]));
						    importValues.tankCost.set(Double.valueOf(col[22]));
						    importValues.tankFillLevel.set(Double.valueOf(col[23]));
						    importValues.saltConc.set(Double.valueOf(col[24]));
						    importValues.actMatMolMass.set(Double.valueOf(col[25]));
						    importValues.socRange.set(Double.valueOf(col[26]));
						    importValues.replacementFraction.set(Double.valueOf(col[27]));
						    importValues.interestRate.set(Double.valueOf(col[28]));
						    importValues.operationalLifetime.set(Double.valueOf(col[29]));
						    importValues.stackOperationalTime.set(Double.valueOf(col[30]));
						    importValues.threshold.set(Double.valueOf(col[31]));
//						    importValues.replacementCostMass.set(Double.valueOf(col[32]));
//						    importValues.stackExchangeCost.set(Double.valueOf(col[33]));
						    importValues.saltMolMass.set(Double.valueOf(col[32]));
						    importValues.stackCost.set(Double.valueOf(col[33]));
						    
						    //importValues..set(Double.valueOf(col[36]));
						    
						    
//						    importValues.pH.set(Double.valueOf(col[36]));	//kein Pourbaix verfügbar --> keine pH Abhängigkeit möglich?
//						    importValues.actMatNumberElectron.set(Integer.valueOf(col[37]));
//						    importValues.actMatNumberProton.set(Integer.valueOf(col[38]));
						    
						    
						    
					    } else if (mode == "stackMode") {
					    	importValues.timeDischarge.set(Double.valueOf(col[0]));
						    importValues.energyCapacity.set(Double.valueOf(col[1]));					
						    importValues.costAdd.set(Double.valueOf(col[2]));
						    importValues.efficiencySysDischarge.set(Double.valueOf(col[3]));
						    importValues.efficiencyCoulombicRoundTrip.set(Double.valueOf(col[4]));
						    importValues.numberCellsPerStack.set(Double.valueOf(col[5]));
						    importValues.cellArea.set(Double.valueOf(col[6]));
						 
						    importValues.specificPowerDischarge.set(Double.valueOf(col[7]));		
						    importValues.actMatReversiblePotential.set(Double.valueOf(col[8]));
						    importValues.actMatReactionRate.set(Double.valueOf(col[9]));
						    importValues.actMatTransferCoeff.set(Double.valueOf(col[10]));
						    importValues.actMatDiffusionCoeff.set(Double.valueOf(col[11]));
						    importValues.actMatConc.set(Double.valueOf(col[12]));
						    importValues.dFiber.set(Double.valueOf(col[13]));
						    importValues.solventViscosity.set(Double.valueOf(col[14]));
						    importValues.solventDensity.set(Double.valueOf(col[15]));
						    importValues.flowVelocity.set(Double.valueOf(col[16]));
						    importValues.asr.set(Double.valueOf(col[17]));
						    importValues.costBOP.set(Double.valueOf(col[18]));
						    importValues.actMatCost.set(Double.valueOf(col[19]));
						    importValues.saltCost.set(Double.valueOf(col[20]));
						    importValues.solventCost.set(Double.valueOf(col[21]));
						    importValues.tankCost.set(Double.valueOf(col[22]));
						    importValues.tankFillLevel.set(Double.valueOf(col[23]));
						    importValues.saltConc.set(Double.valueOf(col[24]));
						    importValues.actMatMolMass.set(Double.valueOf(col[25]));
						    importValues.socRange.set(Double.valueOf(col[26]));
						    importValues.replacementFraction.set(Double.valueOf(col[27]));
						    importValues.interestRate.set(Double.valueOf(col[28]));
						    importValues.operationalLifetime.set(Double.valueOf(col[29]));
						    importValues.stackOperationalTime.set(Double.valueOf(col[30]));
						    importValues.threshold.set(Double.valueOf(col[31]));
						    importValues.saltMolMass.set(Double.valueOf(col[32]));
						    
						    importValues.separatorCost.set(Double.valueOf(col[33]));
						    importValues.electrodeCost.set(Double.valueOf(col[34]));
						    importValues.bipolarPlateCost.set(Double.valueOf(col[35]));
						    importValues.currentCollectorCost.set(Double.valueOf(col[36]));
						    importValues.stackFrameCost.set(Double.valueOf(col[37]));
						    importValues.assemblyCost.set(Double.valueOf(col[38]));
						    
					    }

					    

					    fileImport.add(importValues);
					}
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
	            if (br != null) {
	                try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
	            }

	            if (fr != null) {
	                try {
						fr.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
	            }
	        }
	    }

		ArrayList<ObjCostAnalysisOutput> exportDataList = new ArrayList();
		
		
		
		
//		export:

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
		LocalDateTime now = LocalDateTime.now();
		String time = dtf.format(now);
		String fileName = "export_SA_" + fc.getSelectedFile().getName() + time + ".txt";
		FileWriter writer;

		try {
			File file = new File(fileName);
			writer = new FileWriter(file, true);

//			Header:
//			writer.write("No");
//			writer.write("\t");
//			writer.write("CElectrolyte/$_kWh^-1");
//			writer.write("\t");
//			writer.write("CPower/$_kW^-1");
//			writer.write("\t");
//			writer.write("CMaintenance/$_kWh^-1");
//			writer.write("\t");
			writer.write("Ctotal/$_kWh^-1");
			writer.write("\r\n");
		
		


	    for (int calcCounter = 0; calcCounter < fileImport.size(); calcCounter++) {
//	    	clear values:
	    	CostAnalysisToolController.this.clearValues();
	    	
			standardSelected = true;
			leftAqueous = true;
			rightAqueous = true;
			leftOrganicSolvent = false;
			rightOrganicSolvent = false;
			peakPowerWorkingPoint = false;
			voltageEfficiencyWorkingPoint = false;
			currentDensityWorkingPoint = false;
			specPowerWorkingPoint = true;
			ohmicMode = true;
			chargeTransferMode = true;
			transportationLimitationMode = true;
			

			
			specificPowerDischarge = fileImport.get(calcCounter).specificPowerDischarge.getValue();
	    	
//	    	double standardSocRange = 0.8;
//			double standardActMatCost = 5;
//			double standardActMatMolMass = 100;
//			int standardActMatNumberElectron = 1;
//			int standardActMatNumberProton = 0;
//			double standardActMatSol = 0.2;
//			double standardActMatConc = 2;
//
//			numberCellsPerStack = 40;

//	   		calculation:
	    	//Input values: (Note: Standard = anolyte)

//			power:
			// stack:
//			separatorCost = fileImport.get(calcCounter).separatorStackCost.getValue();
//			System.out.println("separatorStackCost value index 0_"+fileImport.get(0).separatorStackCost.getValue());
//			separatorStackNumber = numberCellsPerStack;
//			electrodeCost = 60;
//			electrodeStackNumber = 2*numberCellsPerStack;
//			bipolarPlateCost = 120;
//			bipolarPlateStackNumber = numberCellsPerStack + 1;
//			cellFramesCost = 0;
//			cellFramesStackNumber = 0;
//			sealsCost = 0;
//			sealsStackNumber = 0;
//			currentCollectorCost = 19;
//			currentCollectorStackNumber = 2;
//			stackFrameCost = 359;
//			stackFrameStackNumber = 1;
//			assemblyCost = 0.6;
//			componentsNumber = separatorStackNumber + electrodeStackNumber + bipolarPlateStackNumber + cellFramesStackNumber
//					+ sealsStackNumber + currentCollectorStackNumber + stackFrameStackNumber;

//			separatorStackCost = 0;
//			electrodeStackCost = 0;
//			bipolarPlateStackCost = 0;
//			cellFramesStackCost = 0;
//			sealsStackCost = 0;
//			currentCollectorStackCost = 0;
//			stackFrameStackCost = 0;
//			assemblyStackCost = 0;
			
			if (mode == "allParametersMode") {
				stackCostTotal = fileImport.get(calcCounter).stackCost.getValue();
			} else if (mode == "stackMode") {
				separatorCost = fileImport.get(calcCounter).separatorCost.getValue();
				electrodeCost = fileImport.get(calcCounter).electrodeCost.getValue();
				bipolarPlateCost = fileImport.get(calcCounter).bipolarPlateCost.getValue();
//				cellFramesStackCost = 0
//				sealsStackCost = 0;
				currentCollectorCost = fileImport.get(calcCounter).currentCollectorCost.getValue();
				stackFrameCost = fileImport.get(calcCounter).stackFrameCost.getValue();
				assemblyCost = fileImport.get(calcCounter).assemblyCost.getValue();
			}

			
			cellArea  = fileImport.get(calcCounter).cellArea.getValue();
			numberCellsPerStack = fileImport.get(calcCounter).numberCellsPerStack.getValue();

//			// stoichiometric coeff:
//			anolyteActMatNumberElectron = standardActMatNumberElectron;
//			catholyteActMatNumberElectron = 1;

//			//Ecell:
//			catholyteStandardPotential = 0;
//			anolyteStandardPotential = 0;
//			reversibleVoltage = fileImport.get(calcCounter).reversibleVoltage.getValue();

//			//U, ocv:
//			stateOfCharge = 0.5;
//			anolyteActMatConc = standardActMatConc;
//			catholyteActMatConc = fileImport.get(calcCounter).actMatConc.getValue();
//			pHElectrolyte = 0;
//			pHActMatAnolyte = 0;
//			pHActMatCatholyte = 0;
//			catholyteActMatNumberProton = 0;
//			anolyteActMatNumberProton = standardActMatNumberProton;
//
//			standardSelected = true;
//			peakPowerWorkingPoint = true;
//			anolyteStandard = true;
//			catholyteStandard = false;
//			leftAqueous = false;
//
//			temp = rT;
//
//			// i0:
//			catholyteKox = true;
//			anolyteKred = false;
//			catholyteReactionRate = fileImport.get(calcCounter).reactionRate.getValue();
//			catholyteTransferCoeff = fileImport.get(calcCounter).transferCoeff.getValue();
//			anolyteReactionRate = 0;
//			anolyteTransferCoeff = 0.5;
			// id:
			
			asr = fileImport.get(calcCounter).asr.getValue();
//			efficiencyVoltDischarge = fileImport.get(calcCounter).efficiencyVoltDischarge.getValue();
			efficiencySysDischarge = fileImport.get(calcCounter).efficiencySysDischarge.getValue();

			//Cpower:
			timeDischarge = fileImport.get(calcCounter).timeDischarge.getValue();
			energyCapacity = fileImport.get(calcCounter).energyCapacity.getValue();
			costBOP = fileImport.get(calcCounter).costBOP.getValue();
			costAdd = fileImport.get(calcCounter).costAdd.getValue();

			dFiber = fileImport.get(calcCounter).dFiber.getValue(); 
			flowVelocity = fileImport.get(calcCounter).flowVelocity.getValue(); 
			
			//A total:
			powerDischarge = energyCapacity/timeDischarge;

//			electrolyte:
//			anolyteSaltSol = fileImport.get(calcCounter).saltSol.getValue();
//			anolyteSaltMolMass = 98.07;
//			anolyteSolventDensity = 1;
//			anolyteActMatMolMass = standardActMatMolMass;
//			catholyteSaltSol = fileImport.get(calcCounter).saltSol.getValue();
//			catholyteSaltMolMass = 98.07;
//			catholyteSolventDensity = 1;
//			catholyteActMatMolMass = fileImport.get(calcCounter).actMatMolMass.getValue();

//			anolyteActMatCost = standardActMatCost;
//			catholyteActMatCost = fileImport.get(calcCounter).actMatCost.getValue();
			objCostsSolventLeft.CSolvent.set(fileImport.get(calcCounter).solventCost.getValue());
			
			objCostsSaltLeft.CSalt.set(fileImport.get(calcCounter).saltCost.getValue());
//			catholyteSolventCost = 0;
//			catholyteSaltCost = 0;
//			anolyteSolventCost = 0;
//			anolyteSaltCost = 0;
//			anolyteSocRange = standardSocRange;
			
			objActiveMaterialLeft.M.set(fileImport.get(calcCounter).actMatMolMass.getValue());
			objActiveMaterialLeft.N.set(2);
			objActiveMaterialLeft.NumberH.set(2);
//			objActiveMaterialLeft.N.set(fileImport.get(calcCounter).actMatNumberElectron.getValue());
//			objActiveMaterialLeft.NumberH.set(fileImport.get(calcCounter).actMatNumberProton.getValue());
			objActiveMaterialLeft.CAM.set(fileImport.get(calcCounter).actMatCost.getValue());
			objActiveMaterialLeft.SOLVENT.set("H2O");
			objCostsSaltLeft.MSalt.set(fileImport.get(calcCounter).saltMolMass.getValue());
		
			inputLeftSaltConc = fileImport.get(calcCounter).saltConc.getValue();
			objActiveMaterialLeft.SaltC.set(inputLeftSaltConc);
			
			objActiveMaterialLeft.E.set(fileImport.get(calcCounter).actMatReversiblePotential.getValue());
			
			objSolventInorganicLeft.c.set(fileImport.get(calcCounter).saltConc.getValue());
			objSolventInorganicLeft.density.set(fileImport.get(calcCounter).solventDensity.getValue());
			objSolventInorganicLeft.dynViscosity.set(fileImport.get(calcCounter).solventViscosity.getValue());
			
			
			objElectrolytesLeft.cSalt.set(inputLeftSaltConc);
//			objElectrolytesLeft.pH.set(fileImport.get(calcCounter).pH.getValue());
			
			objActiveMaterialLeft.PH.set(12);
			objElectrolytesLeft.pH.setValue(8);
			
			
			objElectrolytesLeft.maxSolubility.set(fileImport.get(calcCounter).actMatConc.getValue());
			objElectrolytesLeft.DOx.set(fileImport.get(calcCounter).actMatDiffusionCoeff.getValue());
			objElectrolytesLeft.DRed.set(fileImport.get(calcCounter).actMatDiffusionCoeff.getValue());
			objElectrolytesLeft.kOx.set(fileImport.get(calcCounter).actMatReactionRate.getValue());
			objElectrolytesLeft.AlphaOx.set(fileImport.get(calcCounter).actMatTransferCoeff.getValue());
			objElectrolytesLeft.kRed.set(fileImport.get(calcCounter).actMatReactionRate.getValue());
			objElectrolytesLeft.AlphaRed.set(fileImport.get(calcCounter).actMatTransferCoeff.getValue());
			objElectrolytesLeft.f.set(fileImport.get(calcCounter).replacementFraction.getValue());
			
			SA_SocRange = fileImport.get(calcCounter).socRange.getValue();
			
			efficiencyCoulombicRoundTrip = fileImport.get(calcCounter).efficiencyCoulombicRoundTrip.getValue();

			catholyteTankCost = fileImport.get(calcCounter).tankCost.getValue();
			anolyteTankCost = fileImport.get(calcCounter).tankCost.getValue();
			tankFillLevel = fileImport.get(calcCounter).tankFillLevel.getValue();

//			maintenance:
//			catholyteReplacementFraction = fileImport.get(calcCounter).replacementFraction.getValue();
//			anolyteReplacementFraction = 0;

			interestRate = fileImport.get(calcCounter).interestRate.getValue();
			operationalLifetime = fileImport.get(calcCounter).operationalLifetime.getValue();

			threshold = fileImport.get(calcCounter).threshold.getValue();

			catholyteReplacementCostMass = fileImport.get(calcCounter).replacementCostMass.getValue();
			anolyteReplacementCostMass = fileImport.get(calcCounter).replacementCostMass.getValue();

			stackOperationalTime = fileImport.get(calcCounter).stackOperationalTime.getValue();
			stackExchangeCost = fileImport.get(calcCounter).stackExchangeCost.getValue();
			
			specificPowerDischargeInput = fileImport.get(calcCounter).specificPowerDischarge.getValue();

			
			CostAnalysisToolController.this.defineAnolyteCatholyte();
			
//			calculation:
			CostAnalysisToolController.this.calculation();
//			System.out.println("costCapital"+costCapital);

		    ObjCostAnalysisOutput data = new ObjCostAnalysisOutput();

//			data.costElectrolyteKWh.set(costElectrolyteKWh);
//			data.costPower.set(costPower);
//			data.costMaintenance.set(costMaintenance);
		    
		    if (Double.isNaN(costCapital)) {
		    	costCapital = 0;
		    }
		    
		    
		    data.costCapital.set(costCapital);

//			exportDataList.add(data);
			writer.write(data.costCapital.getValue().toString());
			writer.write("\r\n");
//			System.out.println("costCapital: " + costCapital);
//			System.out.println("costElectrolyteKWh: " + costElectrolyteKWh);
//			System.out.println("costPower: " + costPower);
//			System.out.println("costMaintenance: " + costMaintenance);
//			System.out.println("reversibleVoltage: " + reversibleVoltage);
//			
//			System.out.println("Export data");
	    }

////		export:
//
//		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
//		LocalDateTime now = LocalDateTime.now();
//		String time = dtf.format(now);
//		String fileName = "export_SA_"+time+".txt";
//		FileWriter writer;
//
//		try {
//			File file = new File(fileName);
//			writer = new FileWriter(file, true);
//
////			Header:
////			writer.write("No");
////			writer.write("\t");
////			writer.write("CElectrolyte/$_kWh^-1");
////			writer.write("\t");
////			writer.write("CPower/$_kW^-1");
////			writer.write("\t");
////			writer.write("CMaintenance/$_kWh^-1");
////			writer.write("\t");
//			writer.write("Ctotal/$_kWh^-1");
//			writer.write("\r\n");

//			for (int counter = 0; counter < exportDataList.size(); counter++) {
//
////				writer.write(String.valueOf(counter+1));
////				writer.write("\t");
//
////				writer.write(exportDataList.get(counter).costElectrolyteKWh.getValue().toString());
////				writer.write("\t");
////				writer.write(exportDataList.get(counter).costPower.getValue().toString());
////				writer.write("\t");
////				writer.write(exportDataList.get(counter).costMaintenance.getValue().toString());
////				writer.write("\t");
//				writer.write(exportDataList.get(counter).costCapital.getValue().toString());
//				writer.write("\r\n");
//			}
			writer.close();

			System.out.println(fc.getSelectedFile().getName() + " - SA Export successful!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	@FXML
	public void btnSA(ActionEvent event) {
		CostAnalysisToolController.this.exportSA();
	}
	
	
	public void exportUdvsIdCurve() {
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
		LocalDateTime now = LocalDateTime.now();
		String time = dtf.format(now);
		String fileName = "export_UdvsId_" + titleSW + time + ".txt";
		FileWriter writer;
		
		File file = new File(fileName);
		try {
			writer = new FileWriter(file, true);
			
//			Header:
			writer.write("id / A cm^-2");
			writer.write("\t");
			writer.write("Ud / V");
			writer.write("\r\n");
			
			System.out.println(" ArrayList_UdvsId.size();" + ArrayList_UdvsId.size());
		
			for (int i = 0; i < ArrayList_UdvsId.size(); i++) {
				
				writer.write(ArrayList_UdvsId.get(i).id.getValue().toString());
				writer.write("\t");
				writer.write(ArrayList_UdvsId.get(i).Ud.getValue().toString());
				writer.write("\r\n");
				
				System.out.println(i);
				
			}
			
			writer.close();
		} 
		
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Export UdvsId successful!");
		
	}

}
