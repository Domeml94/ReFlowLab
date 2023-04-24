package de.dominikemmel.reflowlab.controller.costanalysistool;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class ObjCostAnalysisInput {
//	power:
	// stack:
	public DoubleProperty separatorCost = new SimpleDoubleProperty();
	public DoubleProperty separatorStackNumber = new SimpleDoubleProperty();
	public DoubleProperty electrodeCost = new SimpleDoubleProperty();
	public DoubleProperty electrodeStackNumber = new SimpleDoubleProperty();
	public DoubleProperty bipolarPlateCost = new SimpleDoubleProperty();
	public DoubleProperty bipolarPlateStackNumber = new SimpleDoubleProperty();
	public DoubleProperty cellFramesSealsCost = new SimpleDoubleProperty();
	public DoubleProperty cellFramesSealsStackNumber = new SimpleDoubleProperty();
//	public DoubleProperty cellFramesCost = new SimpleDoubleProperty();
//	public DoubleProperty cellFramesStackNumber = new SimpleDoubleProperty();
//	public DoubleProperty sealsCost = new SimpleDoubleProperty();
//	public DoubleProperty sealsStackNumber = new SimpleDoubleProperty();
	public DoubleProperty currentCollectorCost = new SimpleDoubleProperty();
	public DoubleProperty currentCollectorStackNumber = new SimpleDoubleProperty();
	public DoubleProperty stackFrameCost = new SimpleDoubleProperty();
	public DoubleProperty stackFrameStackNumber = new SimpleDoubleProperty();
	public DoubleProperty assemblyCost = new SimpleDoubleProperty();
	public DoubleProperty componentsNumber = new SimpleDoubleProperty();

	public DoubleProperty separatorStackCost = new SimpleDoubleProperty();
	public DoubleProperty electrodeStackCost = new SimpleDoubleProperty();
	public DoubleProperty bipolarPlateStackCost = new SimpleDoubleProperty();
//	public DoubleProperty cellFramesStackCost = new SimpleDoubleProperty();
//	public DoubleProperty sealsStackCost = new SimpleDoubleProperty();
	public DoubleProperty cellFramesSealsStackCost = new SimpleDoubleProperty();
	public DoubleProperty currentCollectorStackCost = new SimpleDoubleProperty();
	public DoubleProperty stackFrameStackCost = new SimpleDoubleProperty();
	public DoubleProperty assemblyStackCost = new SimpleDoubleProperty();
	public DoubleProperty stackCostTotal = new SimpleDoubleProperty();
	public DoubleProperty stackCost = new SimpleDoubleProperty();
	public DoubleProperty cellArea = new SimpleDoubleProperty();
	public DoubleProperty numberCellsPerStack = new SimpleDoubleProperty();

	// stoichiometric coeff:
	public IntegerProperty anolyteActMatNumberElectron = new SimpleIntegerProperty();
	public IntegerProperty catholyteActMatNumberElectron = new SimpleIntegerProperty();

	//Ecell:
	public DoubleProperty catholyteStandardPotential = new SimpleDoubleProperty();
	public DoubleProperty anolyteStandardPotential = new SimpleDoubleProperty();
	public DoubleProperty catholyteReversiblePotential = new SimpleDoubleProperty();
	public DoubleProperty anolyteReversiblePotential = new SimpleDoubleProperty();
	public DoubleProperty reversibleVoltage = new SimpleDoubleProperty();

	//U, ocv:
	public DoubleProperty stateOfCharge = new SimpleDoubleProperty();
	public DoubleProperty anolyteActMatConc = new SimpleDoubleProperty();
	public DoubleProperty catholyteActMatConc = new SimpleDoubleProperty();
	public DoubleProperty pH = new SimpleDoubleProperty();
	public DoubleProperty catholyteActMatNumberProton = new SimpleDoubleProperty();
	public DoubleProperty anolyteActMatNumberProton = new SimpleDoubleProperty();

	public BooleanProperty standardSelected = new SimpleBooleanProperty();
	public BooleanProperty peakPowerWorkingPoint = new SimpleBooleanProperty();
	public BooleanProperty voltageEfficiencyWorkingPoint = new SimpleBooleanProperty();
	public BooleanProperty currentDensityWorkingPoint = new SimpleBooleanProperty();
	public BooleanProperty specPowerWorkingPoint = new SimpleBooleanProperty();
	public BooleanProperty ohmicMode = new SimpleBooleanProperty();
	public BooleanProperty chargeTransferMode = new SimpleBooleanProperty();
	public BooleanProperty transportationLimitationMode = new SimpleBooleanProperty();
	public BooleanProperty anolyteStandard = new SimpleBooleanProperty();
	public BooleanProperty catholyteStandard = new SimpleBooleanProperty();
	public BooleanProperty leftAqueous = new SimpleBooleanProperty();

	public DoubleProperty temp = new SimpleDoubleProperty();

	// i0:
	public BooleanProperty catholyteKox = new SimpleBooleanProperty();
	public BooleanProperty anolyteKred = new SimpleBooleanProperty();
	public DoubleProperty standardTransferCoeff = new SimpleDoubleProperty();
	public DoubleProperty catholyteReactionRate = new SimpleDoubleProperty();
	public DoubleProperty catholyteTransferCoeff = new SimpleDoubleProperty();
	public DoubleProperty anolyteReactionRate = new SimpleDoubleProperty();
	public DoubleProperty anolyteTransferCoeff = new SimpleDoubleProperty();
	// id:
	public DoubleProperty asr = new SimpleDoubleProperty();
	public DoubleProperty peakPowerRatio = new SimpleDoubleProperty();
	public DoubleProperty efficiencyVoltDischarge = new SimpleDoubleProperty();
	public DoubleProperty efficiencySysDischarge = new SimpleDoubleProperty();
	public DoubleProperty currentDensityDischarge = new SimpleDoubleProperty();
	public DoubleProperty voltageDischarge = new SimpleDoubleProperty();
	public DoubleProperty specificPowerDischargeInput = new SimpleDoubleProperty();
	public DoubleProperty chargeTranserOverPotDischarge = new SimpleDoubleProperty();
	public DoubleProperty ohmicOverPotDischarge = new SimpleDoubleProperty();
	public DoubleProperty anolyteChargeTranserOverPotDischarge = new SimpleDoubleProperty();
	public DoubleProperty catholyteChargeTranserOverPotDischarge = new SimpleDoubleProperty();

	public DoubleProperty dFiber = new SimpleDoubleProperty();
	public DoubleProperty flowVelocity = new SimpleDoubleProperty();
	public DoubleProperty anolyteDiffusionCoeff = new SimpleDoubleProperty();
	public DoubleProperty catholyteDiffusionCoeff = new SimpleDoubleProperty();
	public DoubleProperty anolyteSolventViscosity = new SimpleDoubleProperty();
	public DoubleProperty catholyteSolventViscosity = new SimpleDoubleProperty();
	public DoubleProperty catholyteConcentrationOverPotDischarge = new SimpleDoubleProperty();
	public DoubleProperty anolyteConcentrationOverPotDischarge = new SimpleDoubleProperty();
	public DoubleProperty concentrationOverPotDischarge = new SimpleDoubleProperty();
	
	public DoubleProperty costHE = new SimpleDoubleProperty();
	public DoubleProperty costInverter = new SimpleDoubleProperty();
	public DoubleProperty costPipelinesFittings = new SimpleDoubleProperty();
	public DoubleProperty costCabling = new SimpleDoubleProperty();
	public DoubleProperty costProcessPerItem = new SimpleDoubleProperty();
	public DoubleProperty costProcess = new SimpleDoubleProperty();
	public DoubleProperty pumpCostPerItem = new SimpleDoubleProperty();
	public DoubleProperty pumpNumberPerStack = new SimpleDoubleProperty();
	public DoubleProperty pumpCosts = new SimpleDoubleProperty();

	//A total:
	public DoubleProperty powerDischarge = new SimpleDoubleProperty();
	//Cpower:
	public DoubleProperty timeDischarge = new SimpleDoubleProperty();
	public DoubleProperty energyCapacity = new SimpleDoubleProperty();
	public DoubleProperty costBOP = new SimpleDoubleProperty();
	public DoubleProperty costAdd = new SimpleDoubleProperty();

//	electrolyte:
	public DoubleProperty anolyteSaltConc = new SimpleDoubleProperty();
	public DoubleProperty anolyteSaltSol = new SimpleDoubleProperty();
	public DoubleProperty anolyteSaltMolMass = new SimpleDoubleProperty();
	public DoubleProperty anolyteSolventDensity = new SimpleDoubleProperty();
	public DoubleProperty anolyteActMatMolMass = new SimpleDoubleProperty();
	public DoubleProperty catholyteSaltConc = new SimpleDoubleProperty();
	public DoubleProperty catholyteSaltSol = new SimpleDoubleProperty();
	public DoubleProperty catholyteSaltMolMass = new SimpleDoubleProperty();
	public DoubleProperty catholyteSolventDensity = new SimpleDoubleProperty();
	public DoubleProperty catholyteActMatMolMass = new SimpleDoubleProperty();

	public DoubleProperty anolyteActMatCost = new SimpleDoubleProperty();
	public DoubleProperty catholyteActMatCost = new SimpleDoubleProperty();
	public DoubleProperty solventCost = new SimpleDoubleProperty();
	public DoubleProperty saltCost = new SimpleDoubleProperty();
	public DoubleProperty catholyteSolventCost = new SimpleDoubleProperty();
	public DoubleProperty catholyteSaltCost = new SimpleDoubleProperty();
	public DoubleProperty anolyteSolventCost = new SimpleDoubleProperty();
	public DoubleProperty anolyteSaltCost = new SimpleDoubleProperty();
	public DoubleProperty standardSocRange = new SimpleDoubleProperty();
	public DoubleProperty catholyteSocRange = new SimpleDoubleProperty();
	public DoubleProperty anolyteSocRange = new SimpleDoubleProperty();
	public DoubleProperty efficiencyCoulombicRoundTrip = new SimpleDoubleProperty();

	public DoubleProperty standardTankCost = new SimpleDoubleProperty();
	public DoubleProperty catholyteTankCost = new SimpleDoubleProperty();
	public DoubleProperty anolyteTankCost = new SimpleDoubleProperty();
	public DoubleProperty tankFillLevel = new SimpleDoubleProperty();

//	maintenance:
	public DoubleProperty catholyteReplacementFraction = new SimpleDoubleProperty();
	public DoubleProperty anolyteReplacementFraction = new SimpleDoubleProperty();

	public DoubleProperty interestRate = new SimpleDoubleProperty();
	public DoubleProperty operationalLifetime = new SimpleDoubleProperty();

	public DoubleProperty threshold = new SimpleDoubleProperty();

	public DoubleProperty catholyteReplacementCostMass = new SimpleDoubleProperty();
	public DoubleProperty anolyteReplacementCostMass = new SimpleDoubleProperty();
	public DoubleProperty catholyteReplacementCost = new SimpleDoubleProperty();
	public DoubleProperty anolyteReplacementCost = new SimpleDoubleProperty();

	public DoubleProperty stackOperationalTime = new SimpleDoubleProperty();
	

	// Default constructor.
	public ObjCostAnalysisInput(){}
}
