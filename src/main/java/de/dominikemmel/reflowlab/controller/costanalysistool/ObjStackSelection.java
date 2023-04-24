package de.dominikemmel.reflowlab.controller.costanalysistool;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ObjStackSelection {
	
	public StringProperty preselection = new SimpleStringProperty();
	
	public DoubleProperty numberCellsPerStack = new SimpleDoubleProperty();
	public DoubleProperty cellArea = new SimpleDoubleProperty();
	public DoubleProperty asr = new SimpleDoubleProperty();
	
	public StringProperty separatorCostComponentName = new SimpleStringProperty();
	public StringProperty electrodeCostComponentName = new SimpleStringProperty();
	public StringProperty bipolarPlateCostComponentName = new SimpleStringProperty();
	public StringProperty cellFramesSealsCostComponentName = new SimpleStringProperty();
	public StringProperty currentCollectorCostComponentName = new SimpleStringProperty();
	public StringProperty stackFrameCostComponentName = new SimpleStringProperty();
	public StringProperty assemblyCostComponentName = new SimpleStringProperty();
	
	public StringProperty separatorCostCostUnit = new SimpleStringProperty();
	public StringProperty electrodeCostCostUnit = new SimpleStringProperty();
	public StringProperty bipolarPlateCostCostUnit = new SimpleStringProperty();
	public StringProperty cellFramesSealsCostCostUnit = new SimpleStringProperty();
	public StringProperty currentCollectorCostCostUnit = new SimpleStringProperty();
	public StringProperty stackFrameCostCostUnit = new SimpleStringProperty();
	public StringProperty assemblyCostCostUnit = new SimpleStringProperty();
	
	public DoubleProperty separatorCost = new SimpleDoubleProperty();
	public DoubleProperty electrodeCost = new SimpleDoubleProperty();
	public DoubleProperty bipolarPlateCost = new SimpleDoubleProperty();
	public DoubleProperty cellFramesSealsCost = new SimpleDoubleProperty();
	public DoubleProperty currentCollectorCost = new SimpleDoubleProperty();
	public DoubleProperty stackFrameCost = new SimpleDoubleProperty();
	public DoubleProperty assemblyCost = new SimpleDoubleProperty();
	
	public DoubleProperty separatorStackNumber = new SimpleDoubleProperty();
	public DoubleProperty electrodeStackNumber = new SimpleDoubleProperty();
	public DoubleProperty bipolarPlateStackNumber = new SimpleDoubleProperty();
	public DoubleProperty cellFramesSealsStackNumber = new SimpleDoubleProperty();
	public DoubleProperty currentCollectorStackNumber = new SimpleDoubleProperty();
	public DoubleProperty stackFrameStackNumber = new SimpleDoubleProperty();
	public DoubleProperty componentsNumber = new SimpleDoubleProperty();
	
	public DoubleProperty separatorStackCost = new SimpleDoubleProperty();
	public DoubleProperty electrodeStackCost = new SimpleDoubleProperty();
	public DoubleProperty bipolarPlateStackCost = new SimpleDoubleProperty();
	public DoubleProperty cellFramesSealsStackCost = new SimpleDoubleProperty();
	public DoubleProperty currentCollectorStackCost = new SimpleDoubleProperty();
	public DoubleProperty stackFrameStackCost = new SimpleDoubleProperty();
	public DoubleProperty assemblyStackCost = new SimpleDoubleProperty();
	
	public DoubleProperty stackCostTotal = new SimpleDoubleProperty();
	public DoubleProperty stackCost = new SimpleDoubleProperty();
	
	
	
	// Default constructor
	public ObjStackSelection(){}
	

}
