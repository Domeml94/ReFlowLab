package de.dominikemmel.reflowlab.controller.costanalysistool;

import java.util.ArrayList;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ObjCostAnalysisOutput {
 
//	selection:
	public StringProperty catholyteAbbreviation = new SimpleStringProperty();
	public DoubleProperty catholyteC = new SimpleDoubleProperty();
	public StringProperty catholyteSolvent = new SimpleStringProperty();
	public StringProperty catholyteSalt = new SimpleStringProperty();
	public DoubleProperty catholyteSaltC = new SimpleDoubleProperty();

	public StringProperty anolyteAbbreviation = new SimpleStringProperty();
	public DoubleProperty anolyteC = new SimpleDoubleProperty();
	public StringProperty anolyteSolvent = new SimpleStringProperty();
	public StringProperty anolyteSalt = new SimpleStringProperty();
	public DoubleProperty anolyteSaltC = new SimpleDoubleProperty();

//  additional input:
	public DoubleProperty catholyteActMatCost = new SimpleDoubleProperty();
	public DoubleProperty catholyteActMatMolMass = new SimpleDoubleProperty();
	public DoubleProperty catholyteActMatNumberElectron = new SimpleDoubleProperty();
	public DoubleProperty catholyteActMatNumberProton = new SimpleDoubleProperty();
	public DoubleProperty catholyteActMatConc = new SimpleDoubleProperty();
	public DoubleProperty catholyteReactionRate = new SimpleDoubleProperty();
	public DoubleProperty catholyteTransferCoeff = new SimpleDoubleProperty();
	public DoubleProperty catholyteSolventCost = new SimpleDoubleProperty();
	public DoubleProperty catholyteSaltCost = new SimpleDoubleProperty();
	public DoubleProperty catholyteSolventDensity = new SimpleDoubleProperty();
	public DoubleProperty catholyteSaltConc = new SimpleDoubleProperty();

	public DoubleProperty anolyteActMatCost = new SimpleDoubleProperty();
	public DoubleProperty anolyteActMatMolMass = new SimpleDoubleProperty();
	public DoubleProperty anolyteActMatNumberElectron = new SimpleDoubleProperty();
	public DoubleProperty anolyteActMatNumberProton = new SimpleDoubleProperty();
	public DoubleProperty anolyteActMatConc = new SimpleDoubleProperty();
	public DoubleProperty anolyteReactionRate = new SimpleDoubleProperty();
	public DoubleProperty anolyteTransferCoeff = new SimpleDoubleProperty();
	public DoubleProperty anolyteSolventCost = new SimpleDoubleProperty();
	public DoubleProperty anolyteSaltCost = new SimpleDoubleProperty();
	public DoubleProperty anolyteSolventDensity = new SimpleDoubleProperty();
	public DoubleProperty anolyteSaltConc = new SimpleDoubleProperty();

	public DoubleProperty peakPowerRatio = new SimpleDoubleProperty();
	public DoubleProperty efficiencyVoltDischarge = new SimpleDoubleProperty();

	public DoubleProperty temp = new SimpleDoubleProperty();
	public DoubleProperty pH = new SimpleDoubleProperty();

	public DoubleProperty asr = new SimpleDoubleProperty();
	public DoubleProperty timeDischarge = new SimpleDoubleProperty();
	public DoubleProperty powerDischarge = new SimpleDoubleProperty();
	public DoubleProperty specificPowerDischarge = new SimpleDoubleProperty();
	public DoubleProperty energyCapacity = new SimpleDoubleProperty();

	public DoubleProperty cellArea = new SimpleDoubleProperty();
	public DoubleProperty numberCellsPerStack = new SimpleDoubleProperty();
	public DoubleProperty costBOP = new SimpleDoubleProperty();
	public DoubleProperty costAdd = new SimpleDoubleProperty();

	public DoubleProperty operationalLifetime = new SimpleDoubleProperty();
	public DoubleProperty interestRate = new SimpleDoubleProperty();
	public DoubleProperty catholyteReplacementCostMass = new SimpleDoubleProperty();
	public DoubleProperty anolyteReplacementCostMass = new SimpleDoubleProperty();
	public DoubleProperty threshold = new SimpleDoubleProperty();
	public DoubleProperty stackOperationalTime = new SimpleDoubleProperty();

//	power:
	// stack:
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
	// stoichiometric coeff:
	public DoubleProperty catholyteActMatCoeff = new SimpleDoubleProperty();
	public DoubleProperty anolyteActMatCoeff = new SimpleDoubleProperty();
	public DoubleProperty z = new SimpleDoubleProperty();
	//Ecell:
	public DoubleProperty stEcell = new SimpleDoubleProperty();
	//U, ocv:
	public DoubleProperty anolyteActMatRedConc = new SimpleDoubleProperty();
	public DoubleProperty anolyteActMatOxConc = new SimpleDoubleProperty();
	public DoubleProperty catholyteActMatRedConc = new SimpleDoubleProperty();
	public DoubleProperty catholyteActMatOxConc = new SimpleDoubleProperty();
	public DoubleProperty protonConc = new SimpleDoubleProperty();
	public DoubleProperty catholyteReversiblePotential = new SimpleDoubleProperty();
	public DoubleProperty anolyteReversiblePotential = new SimpleDoubleProperty();
	public DoubleProperty catholyteReversiblePotentialPh0 = new SimpleDoubleProperty();
	public DoubleProperty anolyteReversiblePotentialPh0 = new SimpleDoubleProperty();
	public DoubleProperty diffActMatElmitanodeCatholyte = new SimpleDoubleProperty();
	public DoubleProperty diffActMatElmitanodeAnolyte = new SimpleDoubleProperty();
	public DoubleProperty standardElimitanode = new SimpleDoubleProperty();
	public DoubleProperty reversibleVoltage = new SimpleDoubleProperty();
	// i0:
	public DoubleProperty catholyteExchangeCurrentDensity = new SimpleDoubleProperty();
	public DoubleProperty anolyteExchangeCurrentDensity = new SimpleDoubleProperty();
	// id:
	public DoubleProperty currentDensityDischarge = new SimpleDoubleProperty();
	public DoubleProperty voltageDischarge = new SimpleDoubleProperty();
	public DoubleProperty chargeTranserOverPotDischarge = new SimpleDoubleProperty();
	public DoubleProperty ohmicOverPotDischarge = new SimpleDoubleProperty();
	public DoubleProperty anolyteChargeTranserOverPotDischarge = new SimpleDoubleProperty();
	public DoubleProperty catholyteChargeTranserOverPotDischarge = new SimpleDoubleProperty();
	
	public DoubleProperty dFiber = new SimpleDoubleProperty();
	public DoubleProperty flowVelocity = new SimpleDoubleProperty();
	public DoubleProperty anolyteDiffusionCoeff = new SimpleDoubleProperty();
	public DoubleProperty catholyteDiffusionCoeff = new SimpleDoubleProperty();
	public DoubleProperty anolyteReynoldsNumber = new SimpleDoubleProperty();
	public DoubleProperty anolyteSchmidtNumber = new SimpleDoubleProperty();
	public DoubleProperty anolyteSherwoodNumber = new SimpleDoubleProperty();
	public DoubleProperty anolyteDiffLayerThickness = new SimpleDoubleProperty();
	public DoubleProperty catholyteReynoldsNumber = new SimpleDoubleProperty();
	public DoubleProperty catholyteSchmidtNumber = new SimpleDoubleProperty();
	public DoubleProperty catholyteSherwoodNumber = new SimpleDoubleProperty();
	public DoubleProperty catholyteDiffLayerThickness = new SimpleDoubleProperty();
	public DoubleProperty currentLimitCatholyte = new SimpleDoubleProperty();
	public DoubleProperty currentLimitAnolyte = new SimpleDoubleProperty();
	public DoubleProperty currentLimit = new SimpleDoubleProperty();
	public DoubleProperty anolyteConcentrationOverPotDischarge = new SimpleDoubleProperty();
	public DoubleProperty catholyteConcentrationOverPotDischarge = new SimpleDoubleProperty();
	public DoubleProperty concentrationOverPotDischarge = new SimpleDoubleProperty();
	
	//A total:
	public DoubleProperty totalArea = new SimpleDoubleProperty();
	public DoubleProperty numberCells = new SimpleDoubleProperty();
	public DoubleProperty numberStacks = new SimpleDoubleProperty();
	public DoubleProperty reactorCost = new SimpleDoubleProperty();
	public DoubleProperty costPower = new SimpleDoubleProperty();
//	electrolyte:
	public DoubleProperty anolyteSaltSol = new SimpleDoubleProperty();
	public DoubleProperty anolyteActMatSol = new SimpleDoubleProperty();
	public DoubleProperty catholyteSaltSol = new SimpleDoubleProperty();
	public DoubleProperty catholyteActMatSol = new SimpleDoubleProperty();

	public DoubleProperty ravg = new SimpleDoubleProperty();
	public DoubleProperty bavg = new SimpleDoubleProperty();
	public DoubleProperty tankCost = new SimpleDoubleProperty();
	public DoubleProperty costElectrolyte = new SimpleDoubleProperty();
	public DoubleProperty costElectrolyteKWh = new SimpleDoubleProperty();

//	maintenance:
	public DoubleProperty maxReplacementFraction = new SimpleDoubleProperty();
	public DoubleProperty electrolyteDegradationNPVCost = new SimpleDoubleProperty();
	public DoubleProperty electrolyteOperationalTime = new SimpleDoubleProperty();
	public DoubleProperty electrolyteReplacement = new SimpleDoubleProperty();

	public DoubleProperty catholyteReplacementCost = new SimpleDoubleProperty();
	public DoubleProperty anolyteReplacementCost = new SimpleDoubleProperty();
	public DoubleProperty replacementNPVCost = new SimpleDoubleProperty();

	public DoubleProperty pumpCosts = new SimpleDoubleProperty();
	public DoubleProperty stackExchangeCost = new SimpleDoubleProperty();
	public DoubleProperty stackExchangeNPVCost = new SimpleDoubleProperty();

	public DoubleProperty costMaintenance = new SimpleDoubleProperty();
	public DoubleProperty costCapital = new SimpleDoubleProperty();

	public DoubleProperty anolyteEnergyDensity = new SimpleDoubleProperty();
	public DoubleProperty catholyteEnergyDensity = new SimpleDoubleProperty();
	public DoubleProperty anolyteEnergyDensityKg = new SimpleDoubleProperty();
	public DoubleProperty catholyteEnergyDensityKg = new SimpleDoubleProperty();
	public DoubleProperty energyDensity = new SimpleDoubleProperty();
	public DoubleProperty energyDensityKg = new SimpleDoubleProperty();

	public DoubleProperty cActiveAnolyte = new SimpleDoubleProperty();
	public DoubleProperty cActiveCatholyte = new SimpleDoubleProperty();

	public DoubleProperty anolyteCostTotal = new SimpleDoubleProperty();
	public DoubleProperty anolyteCostTotalKAh = new SimpleDoubleProperty();
	public DoubleProperty catholyteCostTotal = new SimpleDoubleProperty();
	public DoubleProperty catholyteCostTotalKAh = new SimpleDoubleProperty();

	public DoubleProperty costInverter = new SimpleDoubleProperty();
	




//	Plot values output:
	public ArrayList<ObjScatterUdvsId> ArrayList_UdvsId = new ArrayList();
	public ArrayList<ObjScatterUdvsSOC> ArrayList_UdvsSOC = new ArrayList();
	public ArrayList<ObjScatterPdvsId> ArrayList_PdvsId = new ArrayList();


	// Default constructor
	public ObjCostAnalysisOutput(){}


}
