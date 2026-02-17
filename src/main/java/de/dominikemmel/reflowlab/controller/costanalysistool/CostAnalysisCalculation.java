package de.dominikemmel.reflowlab.controller.costanalysistool;

import static de.dominikemmel.reflowlab.MyConstants.F;
import static de.dominikemmel.reflowlab.MyConstants.G;

import java.util.*;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CostAnalysisCalculation {

	public ObjCostAnalysisOutput calculation(ObjCostAnalysisInput input) {
		ObjCostAnalysisOutput output = new ObjCostAnalysisOutput();
		ObjStackSelection inputStack = new ObjStackSelection();
		
		
		inputStack = CostAnalysisCalculation.this.objStackTranslation(input);
		
		output = CostAnalysisCalculation.this.stackCalculation(inputStack, output);
		output = CostAnalysisCalculation.this.calculationPolarisation(input, output);
		output = CostAnalysisCalculation.this.calculationPower(input, output);
		output = CostAnalysisCalculation.this.calculationElectrolyte(input, output);
		output = CostAnalysisCalculation.this.calculationMaintenance(input, output);
		 
		output = CostAnalysisCalculation.this.calculationTotalCosts(input, output);		
		
		return output;
	}
	
	
	public ObjStackSelection objStackTranslation(ObjCostAnalysisInput input) {
		ObjStackSelection outputStack = new ObjStackSelection();
		
		outputStack.numberCellsPerStack.set(input.numberCellsPerStack.doubleValue());
		outputStack.cellArea.set(input.cellArea.doubleValue());
		outputStack.asr.set(input.asr.doubleValue());
		
		outputStack.separatorCost.set(input.separatorCost.doubleValue());
		outputStack.electrodeCost.set(input.electrodeCost.doubleValue());
		outputStack.bipolarPlateCost.set(input.bipolarPlateCost.doubleValue());
		outputStack.cellFramesSealsCost.set(input.cellFramesSealsCost.doubleValue());
		outputStack.currentCollectorCost.set(input.currentCollectorCost.doubleValue());
		outputStack.stackFrameCost.set(input.stackFrameCost.doubleValue());
		outputStack.assemblyCost.set(input.assemblyCost.doubleValue());
		
		outputStack.separatorStackNumber.set(input.separatorStackNumber.doubleValue());
		outputStack.electrodeStackNumber.set(input.electrodeStackNumber.doubleValue());
		outputStack.bipolarPlateStackNumber.set(input.bipolarPlateStackNumber.doubleValue());
		outputStack.cellFramesSealsStackNumber.set(input.cellFramesSealsStackNumber.doubleValue());
		outputStack.currentCollectorStackNumber.set(input.currentCollectorStackNumber.doubleValue());
		outputStack.stackFrameStackNumber.set(input.stackFrameStackNumber.doubleValue());
		outputStack.componentsNumber.set(input.componentsNumber.doubleValue());
		
		outputStack.separatorStackCost.set(input.separatorStackCost.doubleValue());
		outputStack.electrodeStackCost.set(input.electrodeStackCost.doubleValue());
		outputStack.bipolarPlateStackCost.set(input.bipolarPlateStackCost.doubleValue());
		outputStack.cellFramesSealsStackCost.set(input.cellFramesSealsStackCost.doubleValue());
		outputStack.currentCollectorStackCost.set(input.currentCollectorStackCost.doubleValue());
		outputStack.stackFrameStackCost.set(input.stackFrameStackCost.doubleValue());
		outputStack.assemblyStackCost.set(input.assemblyStackCost.doubleValue());
		
		outputStack.stackCostTotal.set(input.stackCostTotal.doubleValue());
		outputStack.stackCost.set(input.stackCost.doubleValue());
		
		return outputStack;
		
	}
	
	
	public double calculationElectrolytePotential(String mode, double potentialInput, double temp, double actMatNumberElectron, double redConc, double oxConc, double actMatCoeff, double protonConc, double numberH) {
		
		switch(mode) {
		case "standardE_Input":
			
			double reversiblePotential = potentialInput-G*temp/(actMatNumberElectron*F)*
			Math.log(Math.pow(redConc,actMatCoeff)/(Math.pow(oxConc,actMatCoeff)
					*Math.pow(protonConc,numberH)));
			
			return reversiblePotential;
			
		case "formalE_Input":
			
			double formalPotential = potentialInput+G*298.15/(actMatNumberElectron*F)*
			Math.log(1/Math.pow(protonConc,numberH));
			
			return formalPotential;
		}	
		return 0;
	}
	
	
	
	
	//stack:
	public static ObjCostAnalysisOutput stackCalculation(ObjStackSelection input, ObjCostAnalysisOutput output) {
		
		double separatorCost = input.separatorCost.getValue();
		double separatorStackNumber = input.separatorStackNumber.getValue();
		double electrodeCost = input.electrodeCost.getValue();
		double electrodeStackNumber = input.electrodeStackNumber.getValue();
		double bipolarPlateCost = input.bipolarPlateCost.getValue();
		double bipolarPlateStackNumber = input.bipolarPlateStackNumber.getValue();
//		double cellFramesCost = input.cellFramesCost.getValue();
//		double cellFramesStackNumber = input.cellFramesStackNumber.getValue();
//		double sealsCost = input.sealsCost.getValue();
//		double sealsStackNumber = input.sealsStackNumber.getValue();
		double cellFramesSealsCost = input.cellFramesSealsCost.getValue();
		double cellFramesSealsStackNumber = input.cellFramesSealsStackNumber.getValue();
		double currentCollectorCost = input.currentCollectorCost.getValue();
		double currentCollectorStackNumber = input.currentCollectorStackNumber.getValue();
		double stackFrameCost = input.stackFrameCost.getValue();
		double stackFrameStackNumber = input.stackFrameStackNumber.getValue();
		double assemblyCost = input.assemblyCost.getValue();
		double componentsNumber = input.componentsNumber.getValue();
		double stackCost = input.stackCost.getValue();

		double cellArea = input.cellArea.getValue();
		double numberCellsPerStack = input.numberCellsPerStack.getValue();
		
		double separatorStackCost = 0;
		double electrodeStackCost = 0;
		double bipolarPlateStackCost = 0;
//		double cellFramesStackCost = 0;
//		double sealsStackCost =  0;
		double cellFramesSealsStackCost =  0;
		double currentCollectorStackCost = 0;
		double stackFrameStackCost = 0;
		double assemblyStackCost = 0;
		
//		separatorStackNumber = numberCellsPerStack;
//		
//		electrodeStackNumber = 2*numberCellsPerStack;
//		bipolarPlateStackNumber = numberCellsPerStack + 1;
//		currentCollectorStackNumber = 2;
//		stackFrameStackNumber = 1;
//
//		componentsNumber = separatorStackNumber + electrodeStackNumber + bipolarPlateStackNumber + cellFramesSealsStackNumber
//				+ currentCollectorStackNumber + stackFrameStackNumber;
		
		if (input.separatorStackCost.getValue() != 0) {
			separatorStackCost = input.separatorStackCost.getValue();
		} else {
			separatorStackCost = separatorCost * cellArea * separatorStackNumber;
		}

		if (input.electrodeStackCost.getValue() != 0) {
			electrodeStackCost = input.electrodeStackCost.getValue();
		} else {
			electrodeStackCost = electrodeCost * cellArea * electrodeStackNumber;
		}

		if (input.bipolarPlateStackCost.getValue() != 0) {
			bipolarPlateStackCost = input.bipolarPlateStackCost.getValue();
		} else {
			bipolarPlateStackCost = bipolarPlateCost * cellArea * bipolarPlateStackNumber;
		}

		if (input.cellFramesSealsStackCost.getValue() != 0) {
			cellFramesSealsStackCost = input.cellFramesSealsStackCost.getValue();
		} else {
			cellFramesSealsStackCost = cellFramesSealsCost * cellFramesSealsStackNumber;
		}
		
//		if (input.cellFramesStackCost.getValue() != 0) {
//			cellFramesStackCost = input.cellFramesStackCost.getValue();
//		} else {
//			cellFramesStackCost = cellFramesCost * cellFramesStackNumber;
//		}
//
//		if (input.sealsStackCost.getValue() != 0) {
//			sealsStackCost =  input.sealsStackCost.getValue();
//		} else {
//			sealsStackCost =  sealsCost * sealsStackNumber;
//		}

		if (input.currentCollectorStackCost.getValue() != 0) {
			currentCollectorStackCost =  input.currentCollectorStackCost.getValue();
		} else {
			currentCollectorStackCost = currentCollectorCost * currentCollectorStackNumber;
		}

		if (input.stackFrameStackCost.getValue() != 0) {
			stackFrameStackCost =  input.stackFrameStackCost.getValue();
		} else {
			stackFrameStackCost = stackFrameCost * stackFrameStackNumber;
		}

		if (input.assemblyStackCost.getValue() != 0) {
			assemblyStackCost =  input.assemblyStackCost.getValue();
		} else {
			assemblyStackCost = assemblyCost * componentsNumber;
		}
  

		//stackCostTotal / $ per stack:
		double stackCostTotal = 0;

		if (input.stackCostTotal.getValue() == 0) {
			stackCostTotal = separatorStackCost + electrodeStackCost + bipolarPlateStackCost + cellFramesSealsStackCost
					+ currentCollectorStackCost + stackFrameStackCost + assemblyStackCost;
		} else {
			stackCostTotal = input.stackCostTotal.getValue();
		}

		//stackCost / $ m^-2:
		if(stackCost == 0) {
			stackCost = stackCostTotal / (cellArea*numberCellsPerStack);
		} else {
			stackCostTotal = stackCost * (cellArea*numberCellsPerStack);
		}
		

		
		output.separatorStackCost.set(separatorStackCost);
		output.electrodeStackCost.set(electrodeStackCost);
		output.bipolarPlateStackCost.set(bipolarPlateStackCost);
		output.cellFramesSealsStackCost.set(cellFramesSealsStackCost);
//		output.cellFramesStackCost.set(cellFramesStackCost);
//		output.sealsStackCost.set(sealsStackCost);
		output.currentCollectorStackCost.set(currentCollectorStackCost);
		output.stackFrameStackCost.set(stackFrameStackCost);
		output.assemblyStackCost.set(assemblyStackCost);
		output.stackCostTotal.set(stackCostTotal);
		output.stackCost.set(stackCost);
		
		return output;
	}
	
	
	
	public ObjCostAnalysisOutput calculationPolarisation(ObjCostAnalysisInput input, ObjCostAnalysisOutput output) {
		
		ArrayList<ObjScatterUdvsId> ArrayList_UdvsId = new ArrayList();
		ArrayList<ObjScatterUdvsSOC> ArrayList_UdvsSOC = new ArrayList();
		ArrayList<ObjScatterPdvsId> ArrayList_PdvsId = new ArrayList();
		
		double anolyteActMatNumberElectron = input.anolyteActMatNumberElectron.getValue();
		double catholyteActMatNumberElectron = input.catholyteActMatNumberElectron.getValue();
		
		double stateOfCharge = input.stateOfCharge.getValue();
		double anolyteActMatConc = input.anolyteActMatConc.getValue();
		double catholyteActMatConc = input.catholyteActMatConc.getValue();
		double pH = input.pH.getValue();
		double catholyteActMatNumberProton = input.catholyteActMatNumberProton.getValue();
		double anolyteActMatNumberProton = input.anolyteActMatNumberProton.getValue();

		double catholyteStandardPotential = input.catholyteStandardPotential.getValue();
		double anolyteStandardPotential = input.anolyteStandardPotential.getValue();
		double catholyteReversiblePotential = input.catholyteReversiblePotential.getValue();
		double anolyteReversiblePotential = input.anolyteReversiblePotential.getValue();
		double reversibleVoltage = input.reversibleVoltage.getValue();

		boolean standardSelected = input.standardSelected.getValue();
		boolean peakPowerWorkingPoint = input.peakPowerWorkingPoint.getValue();
		boolean voltageEfficiencyWorkingPoint = input.voltageEfficiencyWorkingPoint.getValue();
		boolean currentDensityWorkingPoint = input.currentDensityWorkingPoint.getValue();
		boolean specPowerWorkingPoint = input.specPowerWorkingPoint.getValue();
		boolean ohmicMode = input.ohmicMode.getValue();
		boolean chargeTransferMode = input.chargeTransferMode.getValue();
		boolean transportationLimitationMode = input.transportationLimitationMode.getValue();
		boolean anolyteStandard = input.anolyteStandard.getValue();
		boolean catholyteStandard = input.catholyteStandard.getValue();
		boolean leftAqueous = input.leftAqueous.getValue();

		double temp = input.temp.getValue();

		// i0:
		boolean catholyteKox = input.catholyteKox.getValue();
		boolean anolyteKred = input.anolyteKred.getValue();
		double catholyteReactionRate = input.catholyteReactionRate.getValue();
		double catholyteTransferCoeff = input.catholyteTransferCoeff.getValue();
		double anolyteReactionRate = input.anolyteReactionRate.getValue();
		double anolyteTransferCoeff = input.anolyteTransferCoeff.getValue();
		// id:
		double asr = input.asr.getValue();
		double peakPowerRatio = input.peakPowerRatio.getValue();
		double efficiencyVoltDischarge = input.efficiencyVoltDischarge.getValue();
		double efficiencySysDischarge = input.efficiencySysDischarge.getValue();
		double currentDensityDischarge = input.currentDensityDischarge.getValue();
		double voltageDischarge = input.voltageDischarge.getValue();
		double specificPowerDischargeInput = input.specificPowerDischargeInput.getValue();
		double chargeTranserOverPotDischarge = 0;
		double ohmicOverPotDischarge = 0;
		double concentrationOverPotDischarge = 0;
		double anolyteChargeTranserOverPotDischarge = 0;
		double catholyteChargeTranserOverPotDischarge = 0;
		double catholyteConcentrationOverPotDischarge = 0;
		double anolyteConcentrationOverPotDischarge = 0;

		double dFiber = input.dFiber.getValue();
		double flowVelocity = input.flowVelocity.getValue();
		double anolyteDiffusionCoeff = input.anolyteDiffusionCoeff.getValue();
		double catholyteDiffusionCoeff = input.catholyteDiffusionCoeff.getValue();

		double anolyteDiffusionCoeffRed = input.anolyteDiffusionCoeff.getValue();
		double catholyteDiffusionCoeffRed = input.catholyteDiffusionCoeff.getValue();
		double anolyteDiffusionCoeffOx = input.anolyteDiffusionCoeff.getValue();
		double catholyteDiffusionCoeffOx = input.catholyteDiffusionCoeff.getValue();

		double anolyteSolventViscosity = input.anolyteSolventViscosity.getValue();
		double catholyteSolventViscosity = input.catholyteSolventViscosity.getValue();


		
		
		double anolyteSolventDensity = input.anolyteSolventDensity.getValue();
		double catholyteSolventDensity = input.catholyteSolventDensity.getValue();
		

		// define stoichiometric coeff.:
		double catholyteActMatCoeff = anolyteActMatNumberElectron * catholyteActMatNumberElectron / catholyteActMatNumberElectron;
		double anolyteActMatCoeff = anolyteActMatNumberElectron * catholyteActMatNumberElectron / anolyteActMatNumberElectron;

		double z = anolyteActMatNumberElectron*anolyteActMatCoeff;

		//Ecell / V:
		double stEcell = catholyteStandardPotential - anolyteStandardPotential;


		//U, ocv / V
		double anolyteActMatRedConc = anolyteActMatConc * stateOfCharge;
		double anolyteActMatOxConc = anolyteActMatConc * (1-stateOfCharge);
		double catholyteActMatRedConc = catholyteActMatConc * (1-stateOfCharge);
		double catholyteActMatOxConc = catholyteActMatConc * stateOfCharge;
		double protonConc = Math.pow(10, -pH);

		// full cell:

//		double catholyteReversiblePotential = 0;
//		double anolyteReversiblePotential = 0;

//		double catholyteReversiblePotentialPh0 = 0;
//		double anolyteReversiblePotentialPh0 = 0;
		
		double diffActMatElmitanodeCatholyte = 0;
		double diffActMatElmitanodeAnolyte = 0;
		double standardElimitanode = 0;
		
		if (leftAqueous) {
			standardElimitanode = -0.6 - G*temp/(2*F)*Math.log(1/Math.pow(protonConc,2));
		}

//		if (reversibleVoltage == 0) {
			if (!standardSelected) {
				if (catholyteReversiblePotential == 42) {
//					catholyteReversiblePotential = calculationElectrolytePotential("standardE_Input", catholyteStandardPotential, temp, z, catholyteActMatRedConc, catholyteActMatOxConc, catholyteActMatCoeff, protonConc, catholyteActMatNumberProton);

					catholyteReversiblePotential = catholyteStandardPotential - G*temp/(z*F)*
							Math.log(Math.pow(catholyteActMatRedConc,catholyteActMatCoeff)/(Math.pow(catholyteActMatOxConc,catholyteActMatCoeff)*Math.pow(protonConc,catholyteActMatNumberProton)));

//					catholyteReversiblePotentialPh0= catholyteStandardPotential - G*temp/(z*F)*
//							Math.log(Math.pow(catholyteActMatRedConc,catholyteActMatCoeff)/(Math.pow(catholyteActMatOxConc,catholyteActMatCoeff)*Math.pow(1,catholyteActMatNumberProton)));

				}
				if (anolyteReversiblePotential == 42) {
					anolyteReversiblePotential= anolyteStandardPotential - G*temp/(z*F)*
							Math.log(Math.pow(anolyteActMatRedConc,anolyteActMatCoeff)/(Math.pow(anolyteActMatOxConc,anolyteActMatCoeff)*Math.pow(protonConc,anolyteActMatNumberProton)));

//					anolyteReversiblePotentialPh0= anolyteStandardPotential - G*temp/(z*F)*
//							Math.log(Math.pow(anolyteActMatRedConc,anolyteActMatCoeff)/(Math.pow(anolyteActMatOxConc,anolyteActMatCoeff)*Math.pow(1,anolyteActMatNumberProton)));
				}

//				double q = Math.pow(catholyteActMatRedConc,catholyteActMatCoeff)/
//						(Math.pow(catholyteActMatOxConc,catholyteActMatCoeff)*Math.pow(protonConc,catholyteActMatNumberProton))*
//						(Math.pow(anolyteActMatOxConc,anolyteActMatCoeff)*Math.pow(protonConc,anolyteActMatNumberProton))/
//						Math.pow(anolyteActMatRedConc,anolyteActMatCoeff);
//
//				reversibleVoltage = stEcell - G*rT/(z*F)*Math.log(q);

				reversibleVoltage = catholyteReversiblePotential - anolyteReversiblePotential;

			//standard:
			} else {
				//Anolyte = standard:
				if (anolyteStandard) {
					if (anolyteReversiblePotential == 42) {
						if (leftAqueous) {
							anolyteReversiblePotential = -0.6 - G*temp/(2*F)*Math.log(1/Math.pow(protonConc,2));

//							anolyteReversiblePotentialPh0 = -0.6 - G*temp/(2*F)*Math.log(1/Math.pow(1,2));
						} else {
							//TODO: organic solvents
						}
					}
					if (catholyteReversiblePotential == 42) {
						catholyteReversiblePotential = catholyteStandardPotential - G*temp/(z*F)*
								Math.log(Math.pow(catholyteActMatRedConc,catholyteActMatCoeff)/(Math.pow(catholyteActMatOxConc,catholyteActMatCoeff)*Math.pow(protonConc,catholyteActMatNumberProton)));

//						catholyteReversiblePotentialPh0 = catholyteStandardPotential - G*temp/(z*F)*
//								Math.log(Math.pow(catholyteActMatRedConc,catholyteActMatCoeff)/(Math.pow(catholyteActMatOxConc,catholyteActMatCoeff)*Math.pow(1,catholyteActMatNumberProton)));
					}

					reversibleVoltage = catholyteReversiblePotential - anolyteReversiblePotential;
					
					diffActMatElmitanodeCatholyte = catholyteReversiblePotential - standardElimitanode;
				}
				//Catholyte = standard:
				if (catholyteStandard) {

					if (catholyteReversiblePotential == 42) {
						if (leftAqueous) {
							catholyteReversiblePotential = 1.6 - G*temp/(4*F)*Math.log(1/Math.pow(protonConc,4));

//							catholyteReversiblePotentialPh0 = 1.6 - G*temp/(4*F)*Math.log(1/Math.pow(1,4));
						} else {
							//TODO: organic solvents
						}
					}

					if (anolyteReversiblePotential == 42) {
						anolyteReversiblePotential = anolyteStandardPotential - G*temp/(z*F)*
								Math.log(Math.pow(anolyteActMatRedConc,anolyteActMatCoeff)/(Math.pow(anolyteActMatOxConc,anolyteActMatCoeff)*Math.pow(protonConc,anolyteActMatNumberProton)));
						
//						anolyteReversiblePotentialPh0= anolyteStandardPotential - G*temp/(z*F)*
//								Math.log(Math.pow(anolyteActMatRedConc,anolyteActMatCoeff)/(Math.pow(anolyteActMatOxConc,anolyteActMatCoeff)*Math.pow(1,anolyteActMatNumberProton)));
					}

					reversibleVoltage = catholyteReversiblePotential - anolyteReversiblePotential;
					
					diffActMatElmitanodeAnolyte = anolyteReversiblePotential - standardElimitanode;
					
				}
			}
//		}

		//i0 / A cm^-2:
		double catholyteExchangeCurrentDensity = 0;
		double anolyteExchangeCurrentDensity = 0;

		if (catholyteKox) {
			catholyteExchangeCurrentDensity = catholyteActMatNumberElectron * F * catholyteReactionRate
					* Math.pow(catholyteActMatOxConc*Math.pow(10,-3),(1-catholyteTransferCoeff))
					* Math.pow(catholyteActMatRedConc*Math.pow(10,-3),(catholyteTransferCoeff));
		} else {
			catholyteExchangeCurrentDensity = catholyteActMatNumberElectron * F * catholyteReactionRate
					* Math.pow(catholyteActMatOxConc*Math.pow(10,-3),(catholyteTransferCoeff))
					* Math.pow(catholyteActMatRedConc*Math.pow(10,-3),(1-catholyteTransferCoeff));
		}
		if (anolyteKred) {
			anolyteExchangeCurrentDensity = anolyteActMatNumberElectron * F * anolyteReactionRate * Math.pow(anolyteActMatRedConc
					* Math.pow(10,-3),(1-anolyteTransferCoeff))
					* Math.pow(anolyteActMatOxConc*Math.pow(10,-3),(anolyteTransferCoeff));
		} else {
			anolyteExchangeCurrentDensity = anolyteActMatNumberElectron * F * anolyteReactionRate * Math.pow(anolyteActMatRedConc
					* Math.pow(10,-3),(anolyteTransferCoeff))
					* Math.pow(anolyteActMatOxConc*Math.pow(10,-3),(1-anolyteTransferCoeff));
		}

		// id / A cm^-2:

		//flow behaviour:
		double anolyteReynoldsNumber = (flowVelocity * anolyteSolventDensity * Math.pow(10,3) * dFiber) / (anolyteSolventViscosity * Math.pow(10,-3));
		double anolyteSchmidtNumberRed = anolyteSolventViscosity * Math.pow(10,-3) / (anolyteDiffusionCoeffRed * Math.pow(10,-4) * anolyteSolventDensity * Math.pow(10,3));
		double anolyteSchmidtNumberOx = anolyteSolventViscosity * Math.pow(10,-3) / (anolyteDiffusionCoeffOx * Math.pow(10,-4) * anolyteSolventDensity * Math.pow(10,3));

		double anolyteSherwoodNumberRed = 0.07 * Math.pow(anolyteReynoldsNumber, 0.66) * Math.pow(anolyteSchmidtNumberRed, 0.45);
		double anolyteSherwoodNumberOx = 0.07 * Math.pow(anolyteReynoldsNumber, 0.66) * Math.pow(anolyteSchmidtNumberOx, 0.45);

		double anolyteDiffLayerThicknessRed = dFiber / anolyteSherwoodNumberRed;
		double anolyteDiffLayerThicknessOx = dFiber / anolyteSherwoodNumberOx;


		double catholyteReynoldsNumber = (flowVelocity * catholyteSolventDensity * Math.pow(10,3) * dFiber) / (catholyteSolventViscosity * Math.pow(10,-3));

		double catholyteSchmidtNumberOx = catholyteSolventViscosity * Math.pow(10,-3) / (catholyteDiffusionCoeffOx * Math.pow(10,-4) * catholyteSolventDensity * Math.pow(10,3));
		double catholyteSchmidtNumberRed = catholyteSolventViscosity * Math.pow(10,-3) / (catholyteDiffusionCoeffRed * Math.pow(10,-4) * catholyteSolventDensity * Math.pow(10,3));

		double catholyteSherwoodNumberOx = 0.07 * Math.pow(catholyteReynoldsNumber, 0.66) * Math.pow(catholyteSchmidtNumberOx, 0.45);
		double catholyteSherwoodNumberRed = 0.07 * Math.pow(catholyteReynoldsNumber, 0.66) * Math.pow(catholyteSchmidtNumberRed, 0.45);

		double catholyteDiffLayerThicknessOx = dFiber / catholyteSherwoodNumberOx;
		double catholyteDiffLayerThicknessRed = dFiber / catholyteSherwoodNumberRed;


		double ilimitCatholyteOx = catholyteActMatNumberElectron * F * catholyteDiffusionCoeffOx * catholyteActMatOxConc * Math.pow(10,-3) / (catholyteDiffLayerThicknessOx * Math.pow(10,2));
		double ilimitCatholyteRed = catholyteActMatNumberElectron * F * catholyteDiffusionCoeffRed * catholyteActMatRedConc * Math.pow(10,-3) / (catholyteDiffLayerThicknessRed * Math.pow(10,2));
		double ilimitAnolyteRed = anolyteActMatNumberElectron * F * anolyteDiffusionCoeffRed * anolyteActMatRedConc * Math.pow(10,-3) / (anolyteDiffLayerThicknessRed * Math.pow(10,2));
		double ilimitAnolyteOx = anolyteActMatNumberElectron * F * anolyteDiffusionCoeffOx * anolyteActMatOxConc * Math.pow(10,-3) / (anolyteDiffLayerThicknessOx * Math.pow(10,2));
		
		double iLimit = 0;
		if (!Double.isNaN(ilimitCatholyteOx) && !Double.isNaN(ilimitAnolyteRed)) {
			iLimit = Math.min(ilimitCatholyteOx, ilimitAnolyteRed);
		} else if (!Double.isNaN(ilimitCatholyteOx)) {
			iLimit = ilimitCatholyteOx;
		} else if (!Double.isNaN(ilimitAnolyteRed)) {
			iLimit = ilimitAnolyteRed;
		}

		double countMax = 0;
		
		boolean calculation = false;
		
		if (transportationLimitationMode && iLimit>0) {
			calculation = true;
		} else {
			calculation = false;
			System.out.println("no iLimit calculated - check parameters!");
		}
		if (!transportationLimitationMode) {
			calculation = true; 
		}
		
		
		double currentDensity = 0;
		
		if (calculation) {
			
			if (!ohmicMode) {
				// 'ohmicMode' disables all operational points besides the fixed current density mode - therefore, the user sets the limit in this case.
				countMax = currentDensityDischarge*2;
			} else {
				if (iLimit > 0) {
					countMax = iLimit * 10;
				} else {
					// In case no limit is set due to missing parameters - A limit must be set in dependency of the active material concentration. Otherwise this loop will run until out of memory. 
					System.out.println("iLimit set to alternative value as a calculation of it is not possible!");
					countMax = Math.max(catholyteActMatNumberElectron, anolyteActMatNumberElectron) * F * 10*Math.pow(10, -4) * Math.min(catholyteActMatOxConc, anolyteActMatOxConc) * Math.pow(10,-3) / (1*Math.pow(10, -7) * Math.pow(10,2));
				}	
			}
			
			ArrayList<ObjScatterUdvsId> ArrayList_UctAnolyte = new ArrayList();
			ArrayList<ObjScatterUdvsId> ArrayList_UctCatholyte = new ArrayList();

			if (chargeTransferMode) {
				double countMaxCt = reversibleVoltage;
				double incrementUCt = 0.0001;

				if (anolyteExchangeCurrentDensity > 0) {

					// calculate current in dependency of charge transfer resistance for anolyte side
					for (double UctAnolyte = 0; UctAnolyte <= countMaxCt; UctAnolyte+=incrementUCt) {
				
						double id = anolyteExchangeCurrentDensity * (Math.exp((anolyteActMatNumberElectron*anolyteTransferCoeff*F*UctAnolyte)/(G*temp)) 
								- Math.exp(-(anolyteActMatNumberElectron*(1-anolyteTransferCoeff)*F*UctAnolyte)/(G*temp)));
						
						ObjScatterUdvsId ObjUct = new ObjScatterUdvsId();
						ObjUct.UctAnolyte.set(UctAnolyte);
						ObjUct.id.set(id);
						
						ArrayList_UctAnolyte.add(ObjUct);

						if (id >= iLimit) {
							break;
						}
					}
				}
				

				if (catholyteExchangeCurrentDensity > 0) {
					// calculate current in dependency of charge transfer resistance for catholyte side
					for (double UctCatholyte = 0; UctCatholyte <= countMaxCt; UctCatholyte+=incrementUCt) {
						
						double id = catholyteExchangeCurrentDensity * (Math.exp((catholyteActMatNumberElectron*(1-catholyteTransferCoeff)*F*UctCatholyte)/(G*temp)) 
								- Math.exp(-(catholyteActMatNumberElectron*catholyteTransferCoeff*F*UctCatholyte)/(G*temp)));
						
						ObjScatterUdvsId ObjUct = new ObjScatterUdvsId();
						ObjUct.UctCatholyte.set(UctCatholyte);
						ObjUct.id.set(id);
						
						ArrayList_UctCatholyte.add(ObjUct);
						
						if (id >= iLimit) {
							break;
						}
					}
				}
			}
			
			
			double incrementCurrent = countMax * Math.pow(10, -4);
			
			for (double i = incrementCurrent; i<=countMax; i+=incrementCurrent) {
				
				currentDensity = i;

				double UConcCatholyte = 0;
				double UConcAnolyte = 0;

				
				if (transportationLimitationMode) {
					double catholyteActMatOxConcSurf = catholyteActMatOxConc + (catholyteDiffLayerThicknessOx * Math.pow(10,1) * currentDensity * Math.pow(10,2))/(catholyteDiffusionCoeffOx * Math.pow(10,-2)*F) * (-catholyteActMatCoeff/z);

					double anolyteActMatRedConcSurf = anolyteActMatRedConc + (anolyteDiffLayerThicknessRed * Math.pow(10,1) * currentDensity * Math.pow(10,2))/(anolyteDiffusionCoeffRed * Math.pow(10,-2)*F) * (-anolyteActMatCoeff/z);
					

					if (!Double.isNaN(catholyteActMatOxConcSurf)) {
//						UConcCatholyte = (G*temp)/(z*F)*(-catholyteActMatCoeff*Math.log(1-(currentDensity/ilimitCatholyteOx))+catholyteActMatCoeff*Math.log(1-(currentDensity/ilimitCatholyteRed)));
						UConcCatholyte = (G*temp)/(z*F)*(-catholyteActMatCoeff*Math.log(1-(currentDensity/ilimitCatholyteOx)));

					}

					if (!Double.isNaN(anolyteActMatRedConcSurf)) {
//						UConcAnolyte = (G*temp)/(z*F)*(-anolyteActMatCoeff*Math.log(1-(currentDensity/ilimitAnolyteRed))+anolyteActMatCoeff*Math.log(1-(currentDensity/ilimitAnolyteOx)));
						UConcAnolyte = (G*temp)/(z*F)*(-anolyteActMatCoeff*Math.log(1-(currentDensity/ilimitAnolyteRed)));

					}
				}


				double Uocv = reversibleVoltage;

				double UctCatholyte = 0;
				double UctAnolyte = 0;


				if (catholyteExchangeCurrentDensity > 0) {
//					UctCatholyte = (G*temp)/(catholyteActMatNumberElectron*catholyteTransferCoeff*F)*Math.log(currentDensity/catholyteExchangeCurrentDensity);

					for (int numbCt = 0; numbCt <= ArrayList_UctCatholyte.size()-1; numbCt+=1) {
						if (ArrayList_UctCatholyte.get(numbCt).id.doubleValue() > currentDensity) {
							break;
						};
						UctCatholyte = ArrayList_UctCatholyte.get(numbCt).UctCatholyte.doubleValue();
					}
				}
				

				if (anolyteExchangeCurrentDensity > 0) {
//					UctAnolyte = (G*temp)/(anolyteActMatNumberElectron*anolyteTransferCoeff*F)*Math.log(currentDensity/anolyteExchangeCurrentDensity);
					
					for (int numbCt = 0; numbCt <= ArrayList_UctAnolyte.size()-1; numbCt+=1) {
						if (ArrayList_UctAnolyte.get(numbCt).id.doubleValue() > currentDensity) {
							break;
						};
						UctAnolyte = ArrayList_UctAnolyte.get(numbCt).UctAnolyte.doubleValue();
					}					
				}

				double Uct = 0;
				if (chargeTransferMode) {
					Uct = UctCatholyte + UctAnolyte;
				}

				double Uohm = 0;
				if (ohmicMode) {
					Uohm = asr * currentDensity;
				}

				double UConc = 0;
				if (transportationLimitationMode) {
					UConc = UConcCatholyte + UConcAnolyte;
				}


				double Ud = Uocv-(Uct+Uohm+UConc);

				double Pd = Ud * currentDensity;
				
				if (Ud >= 0) {
					ObjScatterUdvsId UdvsId = new ObjScatterUdvsId();
					UdvsId.id.set(currentDensity);
					UdvsId.Ud.set(Ud);
					UdvsId.Uct.set(Uct);
					UdvsId.Uohm.set(Uohm);
					UdvsId.UctAnolyte.set(UctAnolyte);
					UdvsId.UctCatholyte.set(UctCatholyte);
					UdvsId.UConcAnolyte.set(UConcAnolyte);
					UdvsId.UConcCatholyte.set(UConcCatholyte);
					UdvsId.UConc.set(UConc);

					ArrayList_UdvsId.add(UdvsId);

					ObjScatterPdvsId PdvsId = new ObjScatterPdvsId();
					PdvsId.id.set(currentDensity);
					PdvsId.Pd.set(Pd);
					PdvsId.Ud.set(Ud);
					PdvsId.Uct.set(Uct);
					PdvsId.Uohm.set(Uohm);
					PdvsId.UctAnolyte.set(UctAnolyte);
					PdvsId.UctCatholyte.set(UctCatholyte);
					PdvsId.UConcAnolyte.set(UConcAnolyte);
					PdvsId.UConcCatholyte.set(UConcCatholyte);
					PdvsId.UConc.set(UConc);

					ArrayList_PdvsId.add(PdvsId);
				}
				
				if (i >= iLimit && transportationLimitationMode) {
					ObjScatterUdvsId UdvsId = new ObjScatterUdvsId();

					UdvsId.id.set(i);
					UdvsId.Ud.set(0);
					
					
					ArrayList_UdvsId.add(UdvsId);
					
					ObjScatterPdvsId PdvsId = new ObjScatterPdvsId();
					PdvsId.id.set(i);
					PdvsId.Pd.set(0);
					
					ArrayList_PdvsId.add(PdvsId);
					
					break;
				}
				
				if (Ud <= 0) {
					
					ObjScatterUdvsId UdvsId = new ObjScatterUdvsId();

					UdvsId.id.set(i);
					UdvsId.Ud.set(0);
					
					
					ArrayList_UdvsId.add(UdvsId);
					
					ObjScatterPdvsId PdvsId = new ObjScatterPdvsId();
					PdvsId.id.set(i);
					PdvsId.Pd.set(0);
					
					ArrayList_PdvsId.add(PdvsId);

					break;
				}

			}
		}
		
		double peakPower = 0;
		int peakPowerCounter = 0;
		boolean globalMaximum = false;
		
		for (int counter = 0; counter < ArrayList_PdvsId.size(); counter++) {
			if (ArrayList_PdvsId.get(counter).Pd.getValue() > peakPower) {
				peakPowerCounter = counter;
				peakPower = ArrayList_PdvsId.get(counter).Pd.getValue();
			}
			if (ArrayList_PdvsId.get(counter).Pd.getValue() < peakPower) {
				globalMaximum = true;
			}
		}
		if (!globalMaximum) {
			System.out.println("Peak power is no global maximum!");
		}
		
		double specificPowerDischarge = 0;

		if (peakPowerWorkingPoint) {

			for (int counter = 0; counter < ArrayList_PdvsId.size(); counter++) {
				if (ArrayList_PdvsId.get(counter).Pd.getValue() > peakPower*peakPowerRatio) {
					peakPowerCounter = counter;
					break;
				}
			}

			currentDensityDischarge = ArrayList_PdvsId.get(peakPowerCounter).id.getValue();
			specificPowerDischarge = ArrayList_PdvsId.get(peakPowerCounter).Pd.getValue();
			voltageDischarge = ArrayList_PdvsId.get(peakPowerCounter).Ud.getValue();
			chargeTranserOverPotDischarge = ArrayList_PdvsId.get(peakPowerCounter).Uct.getValue();
			ohmicOverPotDischarge = ArrayList_PdvsId.get(peakPowerCounter).Uohm.getValue();
			anolyteChargeTranserOverPotDischarge = ArrayList_PdvsId.get(peakPowerCounter).UctAnolyte.getValue();
			catholyteChargeTranserOverPotDischarge = ArrayList_PdvsId.get(peakPowerCounter).UctCatholyte.getValue();
			anolyteConcentrationOverPotDischarge = ArrayList_PdvsId.get(peakPowerCounter).UConcAnolyte.getValue();
			catholyteConcentrationOverPotDischarge = ArrayList_PdvsId.get(peakPowerCounter).UConcCatholyte.getValue();
			concentrationOverPotDischarge = ArrayList_PdvsId.get(peakPowerCounter).UConc.getValue();

			efficiencyVoltDischarge = voltageDischarge/reversibleVoltage;
		}

		if (voltageEfficiencyWorkingPoint) {
			for (ObjScatterUdvsId element : ArrayList_UdvsId) {

				double Ud = element.Ud.getValue();
				double Uocv = reversibleVoltage;

				if (Ud/Uocv<=efficiencyVoltDischarge) {

					currentDensityDischarge = element.id.getValue();
					specificPowerDischarge = element.id.getValue() * element.Ud.getValue();
					voltageDischarge = element.Ud.getValue();
					chargeTranserOverPotDischarge = element.Uct.getValue();
					ohmicOverPotDischarge = element.Uohm.getValue();
					anolyteChargeTranserOverPotDischarge = element.UctAnolyte.getValue();
					catholyteChargeTranserOverPotDischarge = element.UctCatholyte.getValue();
					anolyteConcentrationOverPotDischarge = element.UConcAnolyte.getValue();
					catholyteConcentrationOverPotDischarge = element.UConcCatholyte.getValue();
					concentrationOverPotDischarge = element.UConc.getValue();

					peakPowerRatio = specificPowerDischarge / peakPower;

					break;
				}
			}
		}

		if (currentDensityWorkingPoint) {
			for (ObjScatterUdvsId element : ArrayList_UdvsId) {

				double Id = element.id.getValue();

				if (Id >= currentDensityDischarge) {

					currentDensityDischarge = element.id.getValue();
					specificPowerDischarge = element.id.getValue() * element.Ud.getValue();
					voltageDischarge = element.Ud.getValue();
					chargeTranserOverPotDischarge = element.Uct.getValue();
					ohmicOverPotDischarge = element.Uohm.getValue();
					anolyteChargeTranserOverPotDischarge = element.UctAnolyte.getValue();
					catholyteChargeTranserOverPotDischarge = element.UctCatholyte.getValue();
					anolyteConcentrationOverPotDischarge = element.UConcAnolyte.getValue();
					catholyteConcentrationOverPotDischarge = element.UConcCatholyte.getValue();
					concentrationOverPotDischarge = element.UConc.getValue();

					efficiencyVoltDischarge = voltageDischarge/reversibleVoltage;

					peakPowerRatio = specificPowerDischarge / peakPower;

					break;
				}
			}
		}


		if (specPowerWorkingPoint) {
			for (int counter = 0; counter < ArrayList_UdvsId.size(); counter++) {
				if (peakPower < specificPowerDischargeInput) {
					System.out.println("Specific power value input is higher than peak power value!");
					break;
				}

				double Pd = ArrayList_PdvsId.get(counter).Pd.getValue();

				if (Pd >= specificPowerDischargeInput) {

					currentDensityDischarge = ArrayList_PdvsId.get(counter).id.getValue();
					specificPowerDischarge = ArrayList_PdvsId.get(counter).Pd.getValue();
					voltageDischarge = ArrayList_PdvsId.get(counter).Ud.getValue();
					chargeTranserOverPotDischarge = ArrayList_PdvsId.get(counter).Uct.getValue();
					ohmicOverPotDischarge = ArrayList_PdvsId.get(counter).Uohm.getValue();
					anolyteChargeTranserOverPotDischarge = ArrayList_PdvsId.get(counter).UctAnolyte.getValue();
					catholyteChargeTranserOverPotDischarge = ArrayList_PdvsId.get(counter).UctCatholyte.getValue();
					anolyteConcentrationOverPotDischarge = ArrayList_PdvsId.get(peakPowerCounter).UConcAnolyte.getValue();
					catholyteConcentrationOverPotDischarge = ArrayList_PdvsId.get(peakPowerCounter).UConcCatholyte.getValue();
					concentrationOverPotDischarge = ArrayList_PdvsId.get(peakPowerCounter).UConc.getValue();

					efficiencyVoltDischarge = voltageDischarge/reversibleVoltage;

					peakPowerRatio = specificPowerDischarge / peakPower;

					break;
				}
			}
		}
		
		//Ecell:
		output.stEcell.set(stEcell);
		//U, ocv:
		output.anolyteActMatRedConc.set(anolyteActMatRedConc);
		output.anolyteActMatOxConc.set(anolyteActMatOxConc);
		output.catholyteActMatRedConc.set(catholyteActMatRedConc);
		output.catholyteActMatOxConc.set(catholyteActMatOxConc);
		output.protonConc.set(protonConc);
		output.catholyteReversiblePotential.set(catholyteReversiblePotential);
		output.anolyteReversiblePotential.set(anolyteReversiblePotential);
//		output.catholyteReversiblePotentialPh0.set(catholyteReversiblePotentialPh0);
//		output.anolyteReversiblePotentialPh0.set(anolyteReversiblePotentialPh0);
		output.diffActMatElmitanodeCatholyte.set(diffActMatElmitanodeCatholyte);
		output.diffActMatElmitanodeAnolyte.set(diffActMatElmitanodeAnolyte);
		output.standardElimitanode.set(standardElimitanode);
		output.reversibleVoltage.set(reversibleVoltage);
		// i0:
		output.catholyteExchangeCurrentDensity.set(catholyteExchangeCurrentDensity);
		output.anolyteExchangeCurrentDensity.set(anolyteExchangeCurrentDensity);
		// id:
		output.currentDensityDischarge.set(currentDensityDischarge);
		output.voltageDischarge.set(voltageDischarge);
		output.specificPowerDischarge.set(specificPowerDischarge);
		output.chargeTranserOverPotDischarge.set(chargeTranserOverPotDischarge);
		output.ohmicOverPotDischarge.set(ohmicOverPotDischarge);
		output.anolyteChargeTranserOverPotDischarge.set(anolyteChargeTranserOverPotDischarge);
		output.catholyteChargeTranserOverPotDischarge.set(catholyteChargeTranserOverPotDischarge);
		
		output.dFiber.set(dFiber);
		output.flowVelocity.set(flowVelocity);
		output.anolyteDiffusionCoeff.set(anolyteDiffusionCoeff);
		output.catholyteDiffusionCoeff.set(catholyteDiffusionCoeff);
		output.anolyteReynoldsNumber.set(anolyteReynoldsNumber);
		output.anolyteSchmidtNumber.set(anolyteSchmidtNumberRed);
		output.anolyteSherwoodNumber.set(anolyteSherwoodNumberRed);
		output.anolyteDiffLayerThickness.set(anolyteDiffLayerThicknessRed);
		output.catholyteReynoldsNumber.set(catholyteReynoldsNumber);
		output.catholyteSchmidtNumber.set(catholyteSchmidtNumberOx);
		output.catholyteSherwoodNumber.set(catholyteSherwoodNumberOx);
		output.catholyteDiffLayerThickness.set(catholyteDiffLayerThicknessOx);
		output.currentLimitCatholyte.set(ilimitCatholyteOx);
		output.currentLimitAnolyte.set(ilimitAnolyteRed);
		output.currentLimit.set(iLimit);
		output.catholyteConcentrationOverPotDischarge.set(catholyteConcentrationOverPotDischarge);
		output.anolyteConcentrationOverPotDischarge.set(anolyteConcentrationOverPotDischarge);
		output.concentrationOverPotDischarge.set(concentrationOverPotDischarge);

		output.peakPowerRatio.set(peakPowerRatio);
		output.efficiencyVoltDischarge.set(efficiencyVoltDischarge);
		
		output.ArrayList_UdvsId.addAll(ArrayList_UdvsId);
		output.ArrayList_PdvsId.addAll(ArrayList_PdvsId);
		
		return output;
	}
	
	
	
	public ObjCostAnalysisOutput calculationPower(ObjCostAnalysisInput input, ObjCostAnalysisOutput output) {
		
		double powerDischarge = input.powerDischarge.getValue();
		//Cpower:
		double timeDischarge = input.timeDischarge.getValue();
		double energyCapacity = input.energyCapacity.getValue();
		double costBOP = input.costBOP.getValue();
		double costAdd = input.costAdd.getValue();
		
		double cellArea = input.cellArea.getValue();
		double numberCellsPerStack = input.numberCellsPerStack.getValue();
		
		double efficiencyVoltDischarge = input.efficiencyVoltDischarge.getValue();
		double efficiencySysDischarge = input.efficiencySysDischarge.getValue();
		
		
		double currentDensityDischarge = output.currentDensityDischarge.getValue();
		double stackCost = output.stackCost.getValue();
		double voltageDischarge = output.voltageDischarge.getValue();


		//A total / m^2:
		double totalArea = powerDischarge*Math.pow(10,6)/(efficiencySysDischarge*currentDensityDischarge*Math.pow(10,4)*voltageDischarge);
		
		
		//n total:
		double numberCells = totalArea / cellArea;

		//n stacks:
		double numberStacks = numberCells / numberCellsPerStack;

		//Cost reactor / $:
		double reactorCost = stackCost * totalArea;
		
		ObjCostAnalysisInput defaultInput =  DefaultInput.defaultInput();
		//C bop / $ kW^-1:
		double costHE = defaultInput.costHE.get();
		double costInverter = defaultInput.costInverter.get();
		double costPipelinesFittings = defaultInput.costPipelinesFittings.get();
		double costCabling = defaultInput.costCabling.get();
		double costProcessPerItem = defaultInput.costProcessPerItem.get();
		double costProcess = costProcessPerItem / (powerDischarge*Math.pow(10, 3));
		

		double pumpCostPerItem = defaultInput.pumpCostPerItem.get();
		double pumpNumberPerStack = defaultInput.pumpNumberPerStack.get();

		double pumpCosts = (pumpNumberPerStack * pumpCostPerItem * numberStacks) / (powerDischarge*Math.pow(10, 3));

		if (costBOP == 0) {
			costBOP = costHE + costInverter + costPipelinesFittings + costCabling + costProcess + pumpCosts;
		}

		//Cpower / $ kW^-1:
		double costPower = reactorCost*timeDischarge/(energyCapacity*Math.pow(10, 3)) + costBOP + costAdd;

		
		output.totalArea.set(totalArea);
		output.numberCells.set(numberCells);
		output.numberStacks.set(numberStacks);
		output.reactorCost.set(reactorCost);
		output.costBOP.set(costBOP);
		output.costPower.set(costPower);
		
		output.pumpCosts.set(pumpCosts);
		output.costInverter.set(costInverter);
		
		return output;
	}
	
	
	
	public ObjCostAnalysisOutput calculationElectrolyte(ObjCostAnalysisInput input, ObjCostAnalysisOutput output) {

		double anolyteActMatNumberElectron = input.anolyteActMatNumberElectron.getValue();
		double catholyteActMatNumberElectron = input.catholyteActMatNumberElectron.getValue();
		
		double stateOfCharge = input.stateOfCharge.getValue();
		double anolyteActMatConc = input.anolyteActMatConc.getValue();
		double catholyteActMatConc = input.catholyteActMatConc.getValue();
		double pH = input.pH.getValue();
		double catholyteActMatNumberProton = input.catholyteActMatNumberProton.getValue();
		double anolyteActMatNumberProton = input.anolyteActMatNumberProton.getValue();
		
		double efficiencySysDischarge = input.efficiencySysDischarge.getValue();
		
		// electrolyte:
		double anolyteSaltConc = input.anolyteSaltConc.getValue();
//		double anolyteSaltSol = input.anolyteSaltSol.getValue();
		double anolyteSaltMolMass = input.anolyteSaltMolMass.getValue();
		double anolyteSolventDensity = input.anolyteSolventDensity.getValue();
		double anolyteActMatMolMass = input.anolyteActMatMolMass.getValue();
		double catholyteSaltConc = input.catholyteSaltConc.getValue();
//		double catholyteSaltSol = input.catholyteSaltSol.getValue();
		double catholyteSaltMolMass = input.catholyteSaltMolMass.getValue();
		double catholyteSolventDensity = input.catholyteSolventDensity.getValue();
		double catholyteActMatMolMass = input.catholyteActMatMolMass.getValue();

		double anolyteActMatCost = input.anolyteActMatCost.getValue();
		double catholyteActMatCost = input.catholyteActMatCost.getValue();


		double solventCost = input.solventCost.getValue();
		double saltCost = input.saltCost.getValue();
		double catholyteSolventCost = input.catholyteSolventCost.getValue();
		double catholyteSaltCost = input.catholyteSaltCost.getValue();
		double anolyteSolventCost = input.anolyteSolventCost.getValue();
		double anolyteSaltCost = input.anolyteSaltCost.getValue();

		double catholyteSocRange = input.catholyteSocRange.getValue();
		double anolyteSocRange = input.anolyteSocRange.getValue();
		double efficiencyCoulombicRoundTrip = input.efficiencyCoulombicRoundTrip.getValue();

		double catholyteTankCost = input.catholyteTankCost.getValue();
		double anolyteTankCost = input.anolyteTankCost.getValue();
		double tankFillLevel = input.tankFillLevel.getValue();
		
		
		double voltageDischarge = output.voltageDischarge.getValue();
		
		// define stoichiometric coeff.:
		double catholyteActMatCoeff = anolyteActMatNumberElectron * catholyteActMatNumberElectron / catholyteActMatNumberElectron;
		double anolyteActMatCoeff = anolyteActMatNumberElectron * catholyteActMatNumberElectron / anolyteActMatNumberElectron;

		double z = anolyteActMatNumberElectron*anolyteActMatCoeff;
		

		// L / kg kg^-1:
		
		double anolyteSaltSol = anolyteSaltConc * anolyteSaltMolMass / (anolyteSolventDensity*1000);

		double anolyteActMatSol = anolyteActMatConc * anolyteActMatMolMass / (anolyteSolventDensity*1000);

		double catholyteSaltSol = catholyteSaltConc * catholyteSaltMolMass / (catholyteSolventDensity*1000);

		double catholyteActMatSol = catholyteActMatConc * catholyteActMatMolMass / (catholyteSolventDensity*1000);

		//TODO add best/worst case selection to export
		if (anolyteActMatCost == 0) {
			anolyteActMatCost = 3.48;
//			anolyteActMatCost = 9;
		}
		if (catholyteActMatCost == 0) {
			catholyteActMatCost = 3.48;
//			catholyteActMatCost = 9;
		}
		//rAvg / mol mol^-1:
		double ravg = 0.5 * ((catholyteActMatCoeff * catholyteActMatMolMass*Math.pow(10, -3) * catholyteSaltSol)
				/ (catholyteSocRange * z * catholyteSaltMolMass*Math.pow(10, -3) * catholyteActMatSol)
				+ (anolyteActMatCoeff * anolyteActMatMolMass*Math.pow(10, -3) * anolyteSaltSol)
				/ (anolyteSocRange * z * anolyteSaltMolMass*Math.pow(10, -3) * anolyteActMatSol));

		//bAvg / mol kg^-1:
		double bavg = Math.pow(0.5*((catholyteActMatCoeff * catholyteActMatMolMass*Math.pow(10, -3) * (1-catholyteSaltSol))
				/ (catholyteSocRange * z * catholyteActMatSol)
				+ (anolyteActMatCoeff * anolyteActMatMolMass*Math.pow(10, -3) * (1-anolyteSaltSol))
				/ (anolyteSocRange * z * anolyteActMatSol)),-1);
		

		//C tank / $ mol^-1:
		double tankCost = catholyteTankCost/(catholyteActMatConc*Math.pow(10, 3)*tankFillLevel)
				+ anolyteTankCost/(anolyteActMatConc*Math.pow(10, 3)*tankFillLevel);

		//C Electrolyte / $ kAh^-1:
		double costElectrolyte = ((catholyteActMatCost * catholyteActMatCoeff * catholyteActMatMolMass*Math.pow(10, -3))/(catholyteSocRange * z)
				+ (anolyteActMatCost * anolyteActMatCoeff * anolyteActMatMolMass*Math.pow(10, -3))/(anolyteSocRange * z)
				+ 2 * ravg * anolyteSaltMolMass*Math.pow(10, -3) * saltCost + (2/bavg) * solventCost + tankCost)
				/ (F * efficiencySysDischarge * efficiencyCoulombicRoundTrip)*3.6*Math.pow(10, 6);

//		//C Electrolyte / $ kWh^-1:
		double costElectrolyteKWh = costElectrolyte / voltageDischarge;
		
		
		output.catholyteActMatCoeff.set(catholyteActMatCoeff);
		output.anolyteActMatCoeff.set(anolyteActMatCoeff);
		output.z.set(z);
		output.anolyteSaltSol.set(anolyteSaltSol);
		output.anolyteActMatSol.set(anolyteActMatSol);
		output.catholyteSaltSol.set(catholyteSaltSol);
		output.catholyteActMatSol.set(catholyteActMatSol);
		output.catholyteActMatCost.set(catholyteActMatCost);
		output.anolyteActMatCost.set(anolyteActMatCost);

		output.ravg.set(ravg);
		output.bavg.set(bavg);
		output.tankCost.set(tankCost);
		output.costElectrolyte.set(costElectrolyte);
		output.costElectrolyteKWh.set(costElectrolyteKWh);
		
		return output;
	}
	

	
	public ObjCostAnalysisOutput calculationMaintenance(ObjCostAnalysisInput input, ObjCostAnalysisOutput output) {
		
		// maintenance:
		double catholyteReplacementFraction = input.catholyteReplacementFraction.getValue();
		double anolyteReplacementFraction = input.anolyteReplacementFraction.getValue();

		double interestRate = input.interestRate.getValue();
		double operationalLifetime = input.operationalLifetime.getValue();

		double threshold = input.threshold.getValue();

		double catholyteReplacementCostMass = input.catholyteReplacementCostMass.getValue();
		double anolyteReplacementCostMass = input.anolyteReplacementCostMass.getValue();
		double catholyteReplacementCost = input.catholyteReplacementCost.getValue();
		double anolyteReplacementCost = input.anolyteReplacementCost.getValue();

		double stackOperationalTime = input.stackOperationalTime.getValue();
		
		double catholyteSocRange = input.catholyteSocRange.getValue();
		double anolyteSocRange = input.anolyteSocRange.getValue();
		double efficiencyCoulombicRoundTrip = input.efficiencyCoulombicRoundTrip.getValue();
		double catholyteActMatMolMass = input.catholyteActMatMolMass.getValue();
		double anolyteActMatMolMass = input.anolyteActMatMolMass.getValue();
		double efficiencySysDischarge = input.efficiencySysDischarge.getValue();
		double timeDischarge = input.timeDischarge.getValue();
		double powerDischarge = input.powerDischarge.getValue();
		
		
		double pumpCosts = output.pumpCosts.getValue();
		
		double catholyteActMatSol = output.catholyteActMatSol.getValue();
		double anolyteActMatSol = output.anolyteActMatSol.getValue();
		double catholyteActMatCoeff = output.catholyteActMatCoeff.getValue();
		double anolyteActMatCoeff = output.anolyteActMatCoeff.getValue();
		double z = output.z.getValue();
		
		double voltageDischarge = output.voltageDischarge.getValue();
		double stackCostTotal = output.stackCostTotal.getValue();
		
		double costInverter = output.costInverter.getValue();

		double costElectrolyteKWh = output.costElectrolyteKWh.getValue();
		
		
//		maintenance:
		double maxReplacementFraction = 0;
		if (catholyteReplacementFraction > anolyteReplacementFraction) {
			maxReplacementFraction = catholyteReplacementFraction;
		} else {
			maxReplacementFraction = anolyteReplacementFraction;
		}


		//NPV replacement / $ kWh^-1:
		double electrolyteOperationalTime = 0;
		if (maxReplacementFraction > 0) {
			electrolyteOperationalTime = (1-threshold)/maxReplacementFraction;
		} else {
			electrolyteOperationalTime = 0;
		}

		double electrolyteReplacement = 0;

		if (electrolyteOperationalTime > 0) {
			for (double i=1; i*electrolyteOperationalTime <= operationalLifetime; i++) {
				double replDep = 1/Math.pow((1 + interestRate), electrolyteOperationalTime*(i-1));
				electrolyteReplacement = electrolyteReplacement + replDep;
			}
		}

//		catholyteReplacementCostMass = -23.45*catholyteActMatSol;
//		anolyteReplacementCostMass = -23.45*anolyteActMatSol;

		if (catholyteReplacementCost == 0) {
			catholyteReplacementCost = (((catholyteReplacementCostMass * catholyteActMatCoeff * catholyteActMatMolMass*Math.pow(10, -3))/
					(catholyteActMatSol * catholyteSocRange * z))
					/ (F * voltageDischarge * efficiencySysDischarge * efficiencyCoulombicRoundTrip))*3.6*Math.pow(10, 6);
		}

		if (anolyteReplacementCost == 0) {
			anolyteReplacementCost = (((anolyteReplacementCostMass * anolyteActMatCoeff * anolyteActMatMolMass*Math.pow(10, -3))/
					(anolyteActMatSol * anolyteSocRange * z))
					/ (F * voltageDischarge * efficiencySysDischarge * efficiencyCoulombicRoundTrip))*3.6*Math.pow(10, 6);
		}

		double replacementCost = catholyteReplacementCost + anolyteReplacementCost;

		double replacementNPVCost = 0;

		double replacementElectrolyteVolumeFraction = 1;

		if (replacementNPVCost == 0) {
			replacementNPVCost = replacementCost * 1/Math.pow((1 + interestRate), operationalLifetime) + (replacementCost + costElectrolyteKWh * replacementElectrolyteVolumeFraction) * electrolyteReplacement;
		}


		//NPV stack / $ kWh^-1:

		double stackExchangeCost = stackCostTotal/(powerDischarge*Math.pow(10, 3)) + costInverter + pumpCosts;

		double stackExchangeNPVCost = stackExchangeCost/timeDischarge * 1 / Math.pow((1 + interestRate), stackOperationalTime);
		

		//Maintenance cost / $ kWh^-1:
		double costMaintenance =  replacementNPVCost + stackExchangeNPVCost;

		output.maxReplacementFraction.set(maxReplacementFraction);
//		output.electrolyteDegradationNPVCost.set(electrolyteDegradationNPVCost);
		output.electrolyteOperationalTime.set(electrolyteOperationalTime);
		output.electrolyteReplacement.set(electrolyteReplacement);

		output.catholyteReplacementCost.set(catholyteReplacementCost);
		output.anolyteReplacementCost.set(anolyteReplacementCost);
		output.replacementNPVCost.set(replacementNPVCost);

		output.pumpCosts.set(pumpCosts);
		output.stackExchangeCost.set(stackExchangeCost);
		output.stackExchangeNPVCost.set(stackExchangeNPVCost);

		output.costMaintenance.set(costMaintenance);
		
		return output;
	}
	
	
	
	public ObjCostAnalysisOutput calculationTotalCosts(ObjCostAnalysisInput input, ObjCostAnalysisOutput output) {
		
		double timeDischarge = input.timeDischarge.getValue();
		boolean standardSelected = input.standardSelected.getValue();
		boolean anolyteStandard = input.anolyteStandard.getValue();
		boolean catholyteStandard = input.catholyteStandard.getValue();
		double anolyteActMatNumberElectron = input.anolyteActMatNumberElectron.getValue();
		double catholyteActMatNumberElectron = input.catholyteActMatNumberElectron.getValue();
		
		double anolyteActMatConc = input.anolyteActMatConc.getValue();
		double catholyteActMatConc = input.catholyteActMatConc.getValue();
		double anolyteSolventDensity = input.anolyteSolventDensity.getValue();
		double catholyteSolventDensity = input.catholyteSolventDensity.getValue();
		double catholyteActMatMolMass = input.catholyteActMatMolMass.getValue();
		double anolyteActMatMolMass = input.anolyteActMatMolMass.getValue();
		
		double interestRate = input.interestRate.getValue();
		double operationalLifetime = input.operationalLifetime.getValue();
		double catholyteReplacementFraction = input.catholyteReplacementFraction.getValue();
		double anolyteReplacementFraction = input.anolyteReplacementFraction.getValue();
		

		double anolyteActMatCost = output.anolyteActMatCost.getValue();
		double catholyteActMatCost = output.catholyteActMatCost.getValue();
		double reversibleVoltage = output.reversibleVoltage.getValue();
		
		double costElectrolyteKWh = output.costElectrolyteKWh.getValue();
		double costPower = output.costPower.getValue();
		double costMaintenance = output.costMaintenance.getValue();
		

		//Ccapital / $ kWh^-1:
		double costCapital = costElectrolyteKWh + costPower/timeDischarge + costMaintenance;

		//Energy density / Wh L^-1:
		double anolyteEnergyDensity = anolyteActMatNumberElectron * anolyteActMatConc * F * reversibleVoltage / 3600;
		double catholyteEnergyDensity = catholyteActMatNumberElectron * catholyteActMatConc * F * reversibleVoltage / 3600;
//		double energyDensity = Math.min(anolyteEnergyDensity, catholyteEnergyDensity);
		double energyDensity = 0;
		//TODO: Export with EnergyDensity(Working electrode) + Frontend: Add anolyte and catholyte EnergyDensity
		if (standardSelected) {
			//Anolyte = standard:
			if (anolyteStandard) {
				energyDensity = catholyteEnergyDensity;
			}
			//Catholyte = standard:
			if (catholyteStandard) {
				energyDensity = anolyteEnergyDensity;
			}
		}

		double anolyteEnergyDensityKg = anolyteEnergyDensity / (anolyteSolventDensity + (anolyteActMatMolMass * Math.pow(10, -3) * anolyteActMatConc));
		double catholyteEnergyDensityKg = catholyteEnergyDensity / (catholyteSolventDensity + (catholyteActMatMolMass * Math.pow(10, -3) * catholyteActMatConc));
		double energyDensityKg = 0;

		if (standardSelected) {
			//Anolyte = standard:
			if (anolyteStandard) {
				energyDensityKg = catholyteEnergyDensityKg;
			}
			//Catholyte = standard:
			if (catholyteStandard) {
				energyDensityKg = anolyteEnergyDensityKg;
			}
		}


		double PowerDensity = 0;
		double PowerDensityKg = 0;


		//C total (anolyte/catholyte):
		//C active, anolyte / $ kAh^-1:
		double cActiveAnolyte = anolyteActMatCost * anolyteActMatMolMass*Math.pow(10, -3)/(F * anolyteActMatNumberElectron) * 3.6 * Math.pow(10, 6);
		//C active, catholyte / $ kAh^-1:
		double cActiveCatholyte = catholyteActMatCost * catholyteActMatMolMass*Math.pow(10, -3)/(F * catholyteActMatNumberElectron) * 3.6 * Math.pow(10, 6);

		//depreciation:
		double dep = (Math.pow(1+interestRate,-1)-Math.pow(1+interestRate,-operationalLifetime)) / (1 - Math.pow(1+interestRate,-1));

		//C total, active_anolyte / $ kg^-1:
		double anolyteCostTotal = anolyteActMatCost * (1+anolyteReplacementFraction * dep);
		//C total, active_anolyte / $ kAh^-1:
		double anolyteCostTotalKAh = cActiveAnolyte * (1+anolyteReplacementFraction * dep);
		//C total, active_catholyte / $ kg^-1:
		double catholyteCostTotal = catholyteActMatCost * (1+catholyteReplacementFraction * dep);
		//C total, active_catholyte / $ kAh^-1:
		double catholyteCostTotalKAh = cActiveCatholyte * (1+catholyteReplacementFraction * dep);



		output.costCapital.set(costCapital);

		output.anolyteEnergyDensity.set(anolyteEnergyDensity);
		output.catholyteEnergyDensity.set(catholyteEnergyDensity);
		output.energyDensity.set(energyDensity);
		output.anolyteEnergyDensityKg.set(anolyteEnergyDensityKg);
		output.catholyteEnergyDensityKg.set(catholyteEnergyDensityKg);
		output.energyDensityKg.set(energyDensityKg);


		output.cActiveAnolyte.set(cActiveAnolyte);
		output.cActiveCatholyte.set(cActiveCatholyte);

		output.anolyteCostTotal.set(anolyteCostTotal);
		output.anolyteCostTotalKAh.set(anolyteCostTotalKAh);
		output.catholyteCostTotal.set(catholyteCostTotal);
		output.catholyteCostTotalKAh.set(catholyteCostTotalKAh);

		return output;
	}
	
}
