package de.dominikemmel.reflowlab.controller.costanalysistool;

import static de.dominikemmel.reflowlab.MyConstants.F;
import static de.dominikemmel.reflowlab.MyConstants.G;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import de.dominikemmel.reflowlab.Database;
import de.dominikemmel.reflowlab.controller.costs.ObjCostsStack;
import de.dominikemmel.reflowlab.controller.references.ReferencesController;

public class DefaultInput {
	
	public static ObjCostAnalysisInput defaultInput() {
		ObjCostAnalysisInput objCostAnalysisInput = new ObjCostAnalysisInput();
		
		objCostAnalysisInput.numberCellsPerStack.set(40);
		objCostAnalysisInput.cellArea.set(0.06);
		objCostAnalysisInput.dFiber.set(10 * Math.pow(10,-6));
		
		
		objCostAnalysisInput.standardSocRange.set(0.8);
		objCostAnalysisInput.stateOfCharge.set(0.5);
		
		objCostAnalysisInput.efficiencyVoltDischarge.set(0.916);
		objCostAnalysisInput.standardTransferCoeff.set(0.5);
		
		objCostAnalysisInput.interestRate.set(0.02);
		objCostAnalysisInput.operationalLifetime.set(20);
		objCostAnalysisInput.stackOperationalTime.set(10);
		objCostAnalysisInput.threshold.set(0.8);
		
		objCostAnalysisInput.efficiencySysDischarge.set(0.94);
		objCostAnalysisInput.efficiencyCoulombicRoundTrip.set(0.97);
		objCostAnalysisInput.standardTankCost.set(411);
		objCostAnalysisInput.tankFillLevel.set(0.8);
		
		objCostAnalysisInput.timeDischarge.set(4);
		objCostAnalysisInput.powerDischarge.set(1);
		objCostAnalysisInput.energyCapacity.set(4);
		
		objCostAnalysisInput.flowVelocity.set(0.101);
		
		objCostAnalysisInput.costHE.set(11);
		objCostAnalysisInput.costInverter.set(103);
		objCostAnalysisInput.costPipelinesFittings.set(25);
		objCostAnalysisInput.costCabling.set(2);
		objCostAnalysisInput.costProcessPerItem.set(153960);
		objCostAnalysisInput.pumpCostPerItem.set(205);
		objCostAnalysisInput.pumpNumberPerStack.set(2);
		
		return objCostAnalysisInput;
	}
	
	
	
	
	public static ObjCostAnalysisInput standardInput() {
		ObjCostAnalysisInput objCostAnalysisInput = new ObjCostAnalysisInput();
		
		return objCostAnalysisInput;
	}
	
	
	
	
	public static ObjStackSelection defaultStackInput(String inputStackSystem) throws ClassNotFoundException, SQLException {
		ObjStackSelection objStackSelection = new ObjStackSelection();

		boolean cellFrames = false;
		
		if (inputStackSystem.equals("0.06 m² | Nafion")) {

//			objStackSelection.separatorCost.set(stackFindCompCost("Nafion N-117"));
//			objStackSelection.electrodeCost.set(stackFindCompCost("GFD 4.6 EA"));
//			objStackSelection.bipolarPlateCost.set(stackFindCompCost("PPG86 (Small cell)"));
//			objStackSelection.cellFramesSealsCost.set(0); 
//			objStackSelection.currentCollectorCost.set(stackFindCompCost("Cu (Small cell)"));
//			objStackSelection.stackFrameCost.set(stackFindCompCost("PVC (PVC housing) (Small cell)"));
//			objStackSelection.assemblyCost.set(stackFindCompCost("0.06 m² Stack (Small cell)"));
			
			cellFrames = false;
			objStackSelection.cellArea.set(0.06);
			objStackSelection.numberCellsPerStack.set(40);
//			TODO Add ASR to selection?! 
			objStackSelection.asr.set(1.00); 
		}
		if (inputStackSystem.equals("0.06 m² | SizeSelective")) {
			
//			objStackSelection.separatorCost.set(stackFindCompCost("Size-selective separator"));
//			objStackSelection.electrodeCost.set(stackFindCompCost("GFD 4.6 EA"));
//			objStackSelection.bipolarPlateCost.set(stackFindCompCost("PPG86 (Small cell)"));
//			objStackSelection.cellFramesSealsCost.set(0); 
//			objStackSelection.currentCollectorCost.set(stackFindCompCost("Cu (Small cell)"));
//			objStackSelection.stackFrameCost.set(stackFindCompCost("PVC (PVC housing) (Small cell)"));
//			objStackSelection.assemblyCost.set(stackFindCompCost("0.06 m² Stack (Small cell)"));
			
			cellFrames = false;
			objStackSelection.cellArea.set(0.06);
			objStackSelection.numberCellsPerStack.set(40);
			objStackSelection.asr.set(0.42);  
		}
		if (inputStackSystem.equals("2.76 m² | Nafion")) {
//			separatorCost = 358;
//			electrodeCost = 60;
//			bipolarPlateCost = 241;
//			cellFramesCost = 241;
//			sealsCost = 241;
//			currentCollectorCost = 3621;
//			stackFrameCost = 30178;
//			assemblyCost = 60;
			
			cellFrames = true;
			objStackSelection.cellArea.set(2.76);
			objStackSelection.numberCellsPerStack.set(40);
			objStackSelection.asr.set(1.00); 


//			sealsStackNumber = 2*numberCellsPerStack;
//			cellFramesStackNumber = numberCellsPerStack;
		}
		if (inputStackSystem.equals("2.76 m² | SizeSelective")) {
//			separatorCost = 30;
//			electrodeCost = 60;
//			bipolarPlateCost = 241;
//			cellFramesCost = 241;
//			sealsCost = 241;
//			currentCollectorCost = 3621;
//			stackFrameCost = 30178;
//			assemblyCost = 60;
			
			cellFrames = true;
			objStackSelection.cellArea.set(2.76);
			objStackSelection.numberCellsPerStack.set(40);
			objStackSelection.asr.set(0.42); 

//			sealsStackNumber = 2*numberCellsPerStack;
//			cellFramesStackNumber = numberCellsPerStack;
		}
		
		
		objStackSelection = StackSelectionPaneController.getStackSystemSelection(objStackSelection, inputStackSystem, objStackSelection.numberCellsPerStack.get(), objStackSelection.cellArea.get(), cellFrames);
		
		return objStackSelection;
	}
	
	
	public static ObjCostsStack stackFindComp(String inputComponentName) throws SQLException, ClassNotFoundException {
		
		ObjCostsStack output = new ObjCostsStack();
		
		Connection con1 = Database.getConnection("costStack");
		Statement stateCostStack = con1.createStatement();
		
		ResultSet resCostStack = stateCostStack.executeQuery("SELECT * FROM costStack "
				+ "WHERE ComponentName = '" + inputComponentName + "'");

		while (resCostStack.next()) {
			output.ID.set(resCostStack.getInt("ID"));
			output.ComponentName.set(resCostStack.getString("ComponentName"));
			output.ComponentType.set(resCostStack.getString("ComponentType"));
			output.ComponentCost.set(resCostStack.getDouble("ComponentCost"));
			output.RefIDComponentCost.set(resCostStack.getInt("RefIDComponentCost"));
			output.CostUnit.set(resCostStack.getString("CostUnit"));
		}
		
		return output;
	}
	
	
	public static boolean lockTest(String operation, String table, String searchColoumn, String searchValue) throws SQLException, ClassNotFoundException {
		
		boolean locked = false;
		int ID = 0;
		
		String refCode = ReferencesController.getRefCode(table, searchColoumn, searchValue);
		
		if (refCode != "") {
			
			Connection con1 = Database.getConnection("lockedIDs");
			Statement stateLockedIDs = con1.createStatement();
			
			ResultSet resLockedIDs = stateLockedIDs.executeQuery("SELECT * FROM lockedIDs "
					+ "WHERE Bound = '" + refCode + "'");

			while (resLockedIDs.next()) {
				ID = resLockedIDs.getInt("ID");
				locked = true;
			}
		}
		
		if (operation.equals("check")) {
			return locked;
		}
		
		if (operation.equals("add")) {
			if (!locked) {
				
				try {
					Connection con = Database.getConnection("lockedIDs");
					Statement state = con.createStatement();
					state.executeUpdate("INSERT INTO lockedIDs(ID, Bound, editDate)"
							+" VALUES(DEFAULT, '"+refCode+"', "
							+"CURRENT_TIMESTAMP)");

				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			} else {
				System.out.println("ID is already locked (code: "+refCode+")");
			}
		}
		
		if (operation.equals("delete")) {
			if (locked) {
				try {
					Connection con = Database.getConnection("lockedIDs");
					Statement state = con.createStatement();
					state.executeUpdate("DELETE FROM lockedIDs WHERE ID = "+ID+"");

				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return locked;
		
	}
	
	
	public double standardPotential(boolean aqueous, String halfCellSide, double temp, double protonConc) {
		double reversibleStandardElimit = 0;
		
		if (aqueous) {
			switch (halfCellSide){
			case "anode":
				reversibleStandardElimit = -0.6 - G*temp/(2*F)*Math.log(1/Math.pow(protonConc,2));
				
			case "cathode":
				reversibleStandardElimit = 1.6 - G*temp/(4*F)*Math.log(1/Math.pow(protonConc,4));
			}
		}
		
		return reversibleStandardElimit;
	}
	

}
