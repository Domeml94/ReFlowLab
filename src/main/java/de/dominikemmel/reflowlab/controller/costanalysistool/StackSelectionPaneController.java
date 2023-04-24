package de.dominikemmel.reflowlab.controller.costanalysistool;

import java.awt.Choice;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.Stack;

import de.dominikemmel.reflowlab.Database;
import de.dominikemmel.reflowlab.controller.costs.ObjCostsStack;
import de.dominikemmel.reflowlab.controller.references.ReferencesController;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.Path;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StackSelectionPaneController implements javafx.fxml.Initializable {
	
	@FXML
	private AnchorPane AnchorPane1;
	
	@FXML
	private MediaView stackAnimation;
	
	@FXML
	private Label labelSeparator;
	
	
	@FXML
	private Text separator1;
	@FXML
	private Text separator2;
	
	@FXML
	private Text electrode1;
	@FXML
	private Text electrode2;
	
	@FXML
	private Text bipolarplate1;
	@FXML
	private Text bipolarplate2;
	@FXML
	private Text bipolarplate3;
	@FXML
	private Text bipolarplate4;
	
	@FXML
	private Text cellframes1;
	@FXML
	private Text cellframes2;
	
	@FXML
	private Text currentcollector1;
	@FXML
	private Text currentcollector2;
	
	@FXML
	private Text stackframe1;
	@FXML
	private Text stackframe2;
	
	@FXML
	private Text stackcost1;
	@FXML
	private Text stackcost2;
	@FXML
	private Text stackcost3;
	
	@FXML
	private ComboBox cbPreselection;
	@FXML
	private TextField cellAreaInput;
	@FXML
	private TextField numberCellsPerStackInput;
	
	@FXML
	private ComboBox cbSeparator;
	@FXML
	private ComboBox cbElectrode;
	@FXML
	private ComboBox cbBipolarPlate;
	@FXML
	private ComboBox cbCellFramesSeals;
	@FXML
	private ComboBox cbCurrentCollector;
	@FXML
	private ComboBox cbStackFrame;
	@FXML
	private ComboBox cbAssembly;
	
	@FXML
	private ComboBox cbASR;
	
	@FXML
	private TextField txfSeparatorStackNumber;
	@FXML
	private TextField txfElectrodeStackNumber;
	@FXML
	private TextField txfBipolarPlateStackNumber;
	@FXML
	private TextField txfCellFramesSealsStackNumber;
	@FXML
	private TextField txfCurrentCollectorStackNumber;
	@FXML
	private TextField txfStackFrameStackNumber;
	@FXML
	private TextField txfComponentsNumber;
	
	@FXML
	private TextField txfSeparatorCost;
	@FXML
	private TextField txfElectrodeCost;
	@FXML
	private TextField txfBipolarPlateCost;
	@FXML
	private TextField txfCellFramesSealsCost;
	@FXML
	private TextField txfCurrentCollectorCost;
	@FXML
	private TextField txfStackFrameCost;
	@FXML
	private TextField txfAssemblyCost;
	
	@FXML
	private TextField txfSeparatorStackCost;
	@FXML
	private TextField txfElectrodeStackCost;
	@FXML
	private TextField txfBipolarPlateStackCost;
	@FXML
	private TextField txfCellFramesSealsStackCost;
	@FXML
	private TextField txfCurrentCollectorStackCost;
	@FXML
	private TextField txfStackFrameStackCost;
	@FXML
	private TextField txfAssemblyStackCost;
	
	@FXML
	private TextField txfASR;
	@FXML
	private TextField txfStackCostTotal;
	@FXML
	private TextField txfStackCost;
	
	
	ObjCostsStack cbSeparatorSelection = new ObjCostsStack();
	ObjCostsStack cbElectrodeSelection = new ObjCostsStack();
	ObjCostsStack cbBipolarPlateSelection = new ObjCostsStack();
	ObjCostsStack cbCellFramesSealsSelection = new ObjCostsStack();
	ObjCostsStack cbCurrentCollectorSelection = new ObjCostsStack();
	ObjCostsStack cbStackFrameSelection = new ObjCostsStack();
	ObjCostsStack cbAssemblySelection = new ObjCostsStack();
	
	double input_cellAreaInput; 
	double input_numberCellsPerStackInput; 
	
	double input_txfSeparatorCost;
	double input_txfElectrodeCost;
	double input_txfBipolarPlateCost;
	double input_txfCellFramesSealsCost;
	double input_txfCurrentCollectorCost;
	double input_txfStackFrameCost;
	double input_txfAssemblyCost;
	
	double separatorStackNumber;
	double electrodeStackNumber;
	double bipolarPlateStackNumber;
	double currentCollectorStackNumber;
	double stackFrameStackNumber;
	double cellFramesSealsStackNumber;
	double componentsNumber;
	
	double input_txfSeparatorStackCost;
	double input_txfElectrodeStackCost;
	double input_txfBipolarPlateStackCost;
	double input_txfCellFramesSealsStackCost;
	double input_txfCurrentCollectorStackCost;
	double input_txfStackFrameStackCost;
	double input_txfAssemblyStackCost;
	double input_txfASR;
	
	boolean cellFrames = false; 
	

	ObjStackSelection objStackSelection = new ObjStackSelection();
	String preselection = null;
	double cellArea = 0;
	double numberCellsPerStack = 0;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		StackSelectionPaneController.this.addLabels();
		StackSelectionPaneController.this.populateCbPreselection();
		StackSelectionPaneController.this.populateCbs();
		StackSelectionPaneController.this.populateCbASR();
		
		cbPreselection.setEditable(true);
		
		labelSeparator.hoverProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
            if (newValue) {
//            	loadMedia();
            } else {
                
            }
        });
		
		cbPreselection.setValue(objStackSelection.preselection.getValue());
		
		numberCellsPerStackInput.textProperty().addListener((observable, oldValue, newValue) -> {
			
			input_numberCellsPerStackInput = Double.valueOf(newValue);
			StackSelectionPaneController.this.setCompNumbers(input_numberCellsPerStackInput);
			StackSelectionPaneController.this.numberCellsPerStackInputEvent();
			try {
				StackSelectionPaneController.this.setStackSystemSelection(cbPreselection.getSelectionModel().getSelectedItem().toString(), Double.valueOf(numberCellsPerStackInput.getText()), Double.valueOf(cellAreaInput.getText()));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		});
		
		cellAreaInput.textProperty().addListener((observable, oldValue, newValue) -> {
			
			input_cellAreaInput = Double.valueOf(newValue);
			StackSelectionPaneController.this.cellAreaInputEvent();
			StackSelectionPaneController.this.calcStackCosts();
			try {
				StackSelectionPaneController.this.setStackSystemSelection(cbPreselection.getSelectionModel().getSelectedItem().toString(), Double.valueOf(numberCellsPerStackInput.getText()), Double.valueOf(cellAreaInput.getText()));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});
		
	}
	
	
	
	public void setPreselection(ObjStackSelection objStackSelection) {
		this.objStackSelection = objStackSelection;
		
		preselection = objStackSelection.preselection.getValue();
		cellArea = objStackSelection.cellArea.getValue();
		numberCellsPerStack = objStackSelection.numberCellsPerStack.getValue();
		
		cbPreselection.setValue(preselection);
		cellAreaInput.setText(String.valueOf(cellArea));
		numberCellsPerStackInput.setText(String.valueOf(numberCellsPerStack));
	}
	
	
	public void populateCbPreselection () {
//		TODO Add all saved Presets
		
//		String data0 = "custom";
		String data1 = "0.06 m² | Nafion";
		String data2 = "0.06 m² | SizeSelective";
//		String data3 = "2.76 m² | Nafion";
//		String data4 = "2.76 m² | SizeSelective";
		ObservableList<String> datacbPreselection = FXCollections.observableArrayList();
		
//		datacbPreselection.add(data0);
		datacbPreselection.add(data1);
		datacbPreselection.add(data2);
//		datacbPreselection.add(data3);
//		datacbPreselection.add(data4);
		
		cbPreselection.setItems(datacbPreselection);	
	}

	public ObjStackSelection getReturn() {
		return objStackSelection;
	}
	
//	private void loadMedia() {
////		TODO add media
//		Media media = new Media(getClass().getResource("/de/dominikemmel/reflowlab/controller/costanalysistool/media/testVideo.mp4").toExternalForm());
//		
//		MediaPlayer mediaPlayer = new MediaPlayer(media);
//	    mediaPlayer.setAutoPlay(true);
//		stackAnimation.setMediaPlayer(mediaPlayer);
//	}
	
	private void addLabels() {
		String colorTxtInput = "#000000";
		String colorTxtInput2 = "#ffffff";
		
		//separator:
		separator1.setText("n");
		separator1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		separator2.setText("total");
		separator2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		separator2.setTranslateY(separator1.getFont().getSize() * 0.3);
		
		//electrode:
		electrode1.setText("2 n");
		electrode1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");

		electrode2.setText("total");
		electrode2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		electrode2.setTranslateY(electrode1.getFont().getSize() * 0.3);
		
		//bipolarplate:
		bipolarplate1.setText("n");
		bipolarplate1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");
		
		bipolarplate2.setText("total");
		bipolarplate2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		bipolarplate2.setTranslateY(bipolarplate1.getFont().getSize() * 0.3);
		
		bipolarplate3.setText("+ n");
		bipolarplate3.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");
		
		bipolarplate4.setText("stack");
		bipolarplate4.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		bipolarplate4.setTranslateY(bipolarplate1.getFont().getSize() * 0.3);
		
		//cellframes:
		cellframes1.setText("2 n");
		cellframes1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");
		
		cellframes2.setText("total");
		cellframes2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		cellframes2.setTranslateY(cellframes1.getFont().getSize() * 0.3);

		//currentcollector:
		currentcollector1.setText("2 n");
		currentcollector1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");
		
		currentcollector2.setText("stack");
		currentcollector2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		currentcollector2.setTranslateY(currentcollector1.getFont().getSize() * 0.3);

		
		//stackframe:
		stackframe1.setText("n");
		stackframe1.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold; -fx-font-style: italic");
		
		stackframe2.setText("stack");
		stackframe2.setStyle("-fx-fill: "+colorTxtInput+"; -fx-font-weight: bold;  -fx-font-size: 9");
		stackframe2.setTranslateY(stackframe1.getFont().getSize() * 0.3);
		
		//stackcost:
		stackcost1.setText("Cost / $ Stack");
		stackcost1.setStyle("-fx-fill: "+colorTxtInput2+"; -fx-font-weight: bold; -fx-font-size: 16; -fx-stroke: #000000; -fx-stroke-width: 0.5px; -fx-stroke-type: outside");
		
		
		stackcost2.setText("-1");
		stackcost2.setStyle("-fx-font-size: 12; -fx-fill: "+colorTxtInput2+"; -fx-font-weight: bold; -fx-stroke: #000000; -fx-stroke-width: 0.5px; -fx-stroke-type: outside");
		stackcost2.setTranslateY(stackcost1.getFont().getSize() * -0.3);
		
		stackcost3.setText(":");
		stackcost3.setStyle("-fx-fill: "+colorTxtInput2+"; -fx-font-weight: bold; -fx-font-size: 16; -fx-stroke: #000000; -fx-stroke-width: 0.5px; -fx-stroke-type: outside");
	}

	private void populateCbs() {

		try {
			ResultSet res = Database.selectData("costStack");

			ObservableList<String> dataSeparator = FXCollections.observableArrayList();
			ObservableList<String> dataElectrode = FXCollections.observableArrayList();
			ObservableList<String> dataBipolarPlate = FXCollections.observableArrayList();
			ObservableList<String> dataCellFramesSeals = FXCollections.observableArrayList();
			ObservableList<String> dataCurrentCollector = FXCollections.observableArrayList();
			ObservableList<String> dataStackFrame = FXCollections.observableArrayList();
			ObservableList<String> dataAssembly = FXCollections.observableArrayList();
			
			while (res.next()) {
				
				if (res.getString("ComponentType").equals("Separator")) {
					dataSeparator.add(res.getString("ComponentName"));
				}
				
				if (res.getString("ComponentType").equals("Electrode")) {
					dataElectrode.add(res.getString("ComponentName"));
				}
				
				if (res.getString("ComponentType").equals("Bipolar plate")) {
					dataBipolarPlate.add(res.getString("ComponentName"));
				}
				
				if (res.getString("ComponentType").equals("Cell frames and seals")) {
					dataCellFramesSeals.add(res.getString("ComponentName"));
				}
				
				if (res.getString("ComponentType").equals("Current collectors")) {
					dataCurrentCollector.add(res.getString("ComponentName"));
				}
				
				if (res.getString("ComponentType").equals("Stack frame")) {
					dataStackFrame.add(res.getString("ComponentName"));
				}
				
				if (res.getString("ComponentType").equals("Assembly")) {
					dataAssembly.add(res.getString("ComponentName"));
				}
			}
			
			cbSeparator.setItems(dataSeparator);
			cbElectrode.setItems(dataElectrode);
			cbBipolarPlate.setItems(dataBipolarPlate);
			cbCellFramesSeals.setItems(dataCellFramesSeals);
			cbCurrentCollector.setItems(dataCurrentCollector);
			cbStackFrame.setItems(dataStackFrame);
			cbAssembly.setItems(dataAssembly);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void clearValues() {
		
		cbSeparatorSelection = null;
		cbElectrodeSelection = null;
		cbBipolarPlateSelection = null;
		cbCellFramesSealsSelection = null;
		cbCurrentCollectorSelection = null;
		cbStackFrameSelection = null;
		cbAssemblySelection = null;
		
		cbSeparator.setValue("");
		cbElectrode.setValue("");
		cbBipolarPlate.setValue("");
		cbCellFramesSeals.setValue("");
		cbCurrentCollector.setValue("");
		cbStackFrame.setValue("");
		cbAssembly.setValue("");
		
		txfSeparatorCost.setText("");
		txfElectrodeCost.setText("");
		txfBipolarPlateCost.setText("");
		txfCellFramesSealsCost.setText("");
		txfCurrentCollectorCost.setText("");
		txfStackFrameCost.setText("");
		txfAssemblyCost.setText("");
		
		txfSeparatorStackCost.setText("");
		txfElectrodeStackCost.setText("");
		txfBipolarPlateStackCost.setText("");
		txfCellFramesSealsStackCost.setText("");
		txfCurrentCollectorStackCost.setText("");
		txfStackFrameStackCost.setText("");
		txfAssemblyStackCost.setText("");
		
		txfStackCost.setText("");
		txfStackCostTotal.setText("");
		
	}
	
	public void refreshValues() {
		
		cellArea = Double.valueOf(cellAreaInput.getText());
		numberCellsPerStack = Double.valueOf(numberCellsPerStackInput.getText());
		
		objStackSelection.cellArea.set(cellArea);
		objStackSelection.numberCellsPerStack.set(numberCellsPerStack);
		
		try {
			
			if (cbSeparator.getValue() != null) {
				StackSelectionPaneController.this.cbSeparatorEvent(null);
			}
			if (cbElectrode.getValue() != null) {
				StackSelectionPaneController.this.cbElectrodeEvent(null);
			}
			if (cbBipolarPlate.getValue() != null) {
				StackSelectionPaneController.this.cbBipolarPlateEvent(null);
			}
			if (cbCellFramesSeals.getValue() != null) {
				StackSelectionPaneController.this.cbCellFramesSealsEvent(null);
			}
			if (cbCurrentCollector.getValue() != null) {
				StackSelectionPaneController.this.cbCurrentCollectorEvent(null);
			}
			if (cbStackFrame.getValue() != null) {
				StackSelectionPaneController.this.cbStackFrameEvent(null);
			}
			if (cbAssembly.getValue() != null) {
				StackSelectionPaneController.this.cbAssemblyEvent(null);
			}
			
			StackSelectionPaneController.this.setCompNumbers(numberCellsPerStack);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	

	public void cellAreaInputEvent() {
		StackSelectionPaneController.this.refreshValues();
	}
	
	public void numberCellsPerStackInputEvent() {
		StackSelectionPaneController.this.refreshValues();
	}
	
	
	
	//selection:
	@FXML
	public void cbSeparatorEvent(ActionEvent event) throws ClassNotFoundException, SQLException {
		
		if (cbSeparator.getValue() != null) {
			cbSeparatorSelection = DefaultInput.stackFindComp(cbSeparator.getSelectionModel().getSelectedItem().toString());
		}

		input_txfSeparatorCost = cbSeparatorSelection.ComponentCost.get();
		
		objStackSelection.separatorCost.set(cbSeparatorSelection.ComponentCost.get());
		objStackSelection.separatorCostComponentName.set(cbSeparatorSelection.ComponentName.get());
		
		txfSeparatorCost.setText(String.valueOf(input_txfSeparatorCost) + " / " + cbSeparatorSelection.CostUnit.get());
		
		StackSelectionPaneController.this.calcStackCosts();
//		TODO "custom"! --> create additional method with content of this one and call the new one for calculation
//		cbPreselection.setValue("custom");
	}
	@FXML
	public void cbElectrodeEvent(ActionEvent event) throws ClassNotFoundException, SQLException {
		
		if (cbElectrode.getValue() != null) {
			cbElectrodeSelection = DefaultInput.stackFindComp(cbElectrode.getSelectionModel().getSelectedItem().toString());
		}
		
		input_txfElectrodeCost = cbElectrodeSelection.ComponentCost.get();
		
		objStackSelection.electrodeCost.set(cbElectrodeSelection.ComponentCost.get());
		objStackSelection.electrodeCostComponentName.set(cbElectrodeSelection.ComponentName.get());
		
		txfElectrodeCost.setText(String.valueOf(input_txfElectrodeCost) + " / " + cbElectrodeSelection.CostUnit.get());
		
		StackSelectionPaneController.this.calcStackCosts();
//		cbPreselection.setValue("custom");
	}
	@FXML
	public void cbBipolarPlateEvent(ActionEvent event) throws ClassNotFoundException, SQLException {
		
		if (cbBipolarPlate.getValue() != null) {
			cbBipolarPlateSelection = DefaultInput.stackFindComp(cbBipolarPlate.getSelectionModel().getSelectedItem().toString());
		}
		
		input_txfBipolarPlateCost = cbBipolarPlateSelection.ComponentCost.get();
		
		objStackSelection.bipolarPlateCost.set(cbBipolarPlateSelection.ComponentCost.get());
		objStackSelection.bipolarPlateCostComponentName.set(cbBipolarPlateSelection.ComponentName.get());
		
		txfBipolarPlateCost.setText(String.valueOf(input_txfBipolarPlateCost) + " / " + cbBipolarPlateSelection.CostUnit.get());
		
		StackSelectionPaneController.this.calcStackCosts();
//		cbPreselection.setValue("custom");
	}
	@FXML
	public void cbCellFramesSealsEvent(ActionEvent event) throws ClassNotFoundException, SQLException {

		if (cbCellFramesSeals.getValue() != null) {
			cbCellFramesSealsSelection = DefaultInput.stackFindComp(cbCellFramesSeals.getSelectionModel().getSelectedItem().toString());
		}
		
		input_txfCellFramesSealsCost = cbCellFramesSealsSelection.ComponentCost.get();
		
		objStackSelection.cellFramesSealsCost.set(cbCellFramesSealsSelection.ComponentCost.get());
		objStackSelection.cellFramesSealsCostComponentName.set(cbCellFramesSealsSelection.ComponentName.get());
		
		txfCellFramesSealsCost.setText(String.valueOf(input_txfCellFramesSealsCost) + " / " + cbCellFramesSealsSelection.CostUnit.get());
		
		StackSelectionPaneController.this.calcStackCosts();
		
		if (cbCellFramesSeals.getSelectionModel().getSelectedItem().toString() != "") {
			cellFrames = true;
		} else {
			cellFrames = false;
		}
		
//		cbPreselection.setValue("custom");
	}
	@FXML
	public void cbCurrentCollectorEvent(ActionEvent event) throws ClassNotFoundException, SQLException {
		
		if (cbCurrentCollector.getValue() != null) {
			cbCurrentCollectorSelection = DefaultInput.stackFindComp(cbCurrentCollector.getSelectionModel().getSelectedItem().toString());
		}
		
		input_txfCurrentCollectorCost = cbCurrentCollectorSelection.ComponentCost.get();
		
		objStackSelection.currentCollectorCost.set(cbCurrentCollectorSelection.ComponentCost.get());
		objStackSelection.currentCollectorCostComponentName.set(cbCurrentCollectorSelection.ComponentName.get());
		
		txfCurrentCollectorCost.setText(String.valueOf(input_txfCurrentCollectorCost) + " / " + cbCurrentCollectorSelection.CostUnit.get());
		
		StackSelectionPaneController.this.calcStackCosts();
//		cbPreselection.setValue("custom");
	}
	@FXML
	public void cbStackFrameEvent(ActionEvent event) throws ClassNotFoundException, SQLException {
		
		if (cbStackFrame.getValue() != null) {
			cbStackFrameSelection = DefaultInput.stackFindComp(cbStackFrame.getSelectionModel().getSelectedItem().toString());
		}
		
		input_txfStackFrameCost = cbStackFrameSelection.ComponentCost.get();
		
		objStackSelection.stackFrameCost.set(cbStackFrameSelection.ComponentCost.get());
		objStackSelection.stackFrameCostComponentName.set(cbStackFrameSelection.ComponentName.get());
		
		txfStackFrameCost.setText(String.valueOf(input_txfStackFrameCost) + " / " + cbStackFrameSelection.CostUnit.get());
		
		StackSelectionPaneController.this.calcStackCosts();
//		cbPreselection.setValue("custom");
	}
	@FXML
	public void cbAssemblyEvent(ActionEvent event) throws ClassNotFoundException, SQLException {
		
		if (cbAssembly.getValue() != null) {
			cbAssemblySelection = DefaultInput.stackFindComp(cbAssembly.getSelectionModel().getSelectedItem().toString());	
		}
		
		input_txfAssemblyCost = cbAssemblySelection.ComponentCost.get();
		
		objStackSelection.assemblyCost.set(cbAssemblySelection.ComponentCost.get());
		objStackSelection.assemblyCostComponentName.set(cbAssemblySelection.ComponentName.get());
		
		txfAssemblyCost.setText(String.valueOf(input_txfAssemblyCost) + " / " + cbAssemblySelection.CostUnit.get());
		
		StackSelectionPaneController.this.calcStackCosts();
//		cbPreselection.setValue("custom");
	}
	
	
	public void calcStackCosts() {
		
		if (input_txfSeparatorCost != 0) {
			input_txfSeparatorStackCost = input_txfSeparatorCost * separatorStackNumber * cellArea;
			txfSeparatorStackCost.setText(String.valueOf(input_txfSeparatorStackCost) + " / $ stack⁻¹");
			objStackSelection.separatorStackCost.set(input_txfSeparatorStackCost);
		}
		if (input_txfElectrodeCost != 0) {
			input_txfElectrodeStackCost = input_txfElectrodeCost * electrodeStackNumber * cellArea;
			txfElectrodeStackCost.setText(String.valueOf(input_txfElectrodeStackCost) + " / $ stack⁻¹");
			objStackSelection.electrodeStackCost.set(input_txfElectrodeStackCost);
		}
		if (input_txfBipolarPlateCost != 0) {
			input_txfBipolarPlateStackCost = input_txfBipolarPlateCost * bipolarPlateStackNumber * cellArea;
			txfBipolarPlateStackCost.setText(String.valueOf(input_txfBipolarPlateStackCost) + " / $ stack⁻¹");
			objStackSelection.bipolarPlateStackCost.set(input_txfBipolarPlateStackCost);
		}
		if (input_txfCellFramesSealsCost != 0) {
			input_txfCellFramesSealsStackCost = input_txfCellFramesSealsCost * cellFramesSealsStackNumber;
			txfCellFramesSealsStackCost.setText(String.valueOf(input_txfCellFramesSealsStackCost) + " / $ stack⁻¹");
			objStackSelection.cellFramesSealsStackCost.set(input_txfCellFramesSealsStackCost);
		}
		if (input_txfCurrentCollectorCost != 0) {
			input_txfCurrentCollectorStackCost = input_txfCurrentCollectorCost * currentCollectorStackNumber;
			txfCurrentCollectorStackCost.setText(String.valueOf(input_txfCurrentCollectorStackCost) + " / $ stack⁻¹");
			objStackSelection.currentCollectorStackCost.set(input_txfCurrentCollectorStackCost);
		}
		if (input_txfStackFrameCost != 0) {
			input_txfStackFrameStackCost = input_txfStackFrameCost * stackFrameStackNumber;
			txfStackFrameStackCost.setText(String.valueOf(input_txfStackFrameStackCost) + " / $ stack⁻¹");
			objStackSelection.stackFrameStackCost.set(input_txfStackFrameStackCost);
		}
		if (input_txfAssemblyCost != 0) {
			input_txfAssemblyStackCost = input_txfAssemblyCost * componentsNumber;
			txfAssemblyStackCost.setText(String.valueOf(input_txfAssemblyStackCost) + " / $ stack⁻¹");
			objStackSelection.assemblyStackCost.set(input_txfAssemblyStackCost);
		}
		
		ObjCostAnalysisOutput output = new ObjCostAnalysisOutput();
		output = CostAnalysisCalculation.stackCalculation(objStackSelection, output);
		txfStackCostTotal.setText(String.valueOf(output.stackCostTotal.get()));
		txfStackCost.setText(String.valueOf(output.stackCost.get()));
	}
	
	// save / delete:
	@FXML
	public void btnSavePreselectionEvent(ActionEvent event) throws ClassNotFoundException, SQLException {
		
		
		boolean assigned = false;
		
		Connection con = Database.getConnection("stackSystemSelection");
		Statement stateStackSystemSelection = con.createStatement();
		
		ResultSet resStackSystemSelection = stateStackSystemSelection.executeQuery("SELECT * FROM stackSystemSelection "
				+ "WHERE StackSystem = '" + cbPreselection.getSelectionModel().getSelectedItem().toString() + "'");

		while (resStackSystemSelection.next()) {
			assigned = true;
		}
		
		if (!assigned) {
			try {
				Connection con2 = Database.getConnection("stackSystemSelection");
				Statement state = con2.createStatement();
				if (cbCellFramesSealsSelection == null) {
					cbCellFramesSealsSelection.ID.set(0);
				}
				state.executeUpdate("INSERT INTO stackSystemSelection (ID, StackSystem, SeparatorID, ElectrodeID, BipolarPlateID, CellFramesSealsID, CurrentCollectorID, StackFrameID, AssemblyID, editDate)"
						+" VALUES(DEFAULT, '"+cbPreselection.getSelectionModel().getSelectedItem().toString()+"', "
						+cbSeparatorSelection.ID.get()+", "+cbElectrodeSelection.ID.get()+", "
						+cbBipolarPlateSelection.ID.get()+", "+cbCellFramesSealsSelection.ID.get()+", "
						+cbCurrentCollectorSelection.ID.get()+", "+cbStackFrameSelection.ID.get()+", "
						+cbAssemblySelection.ID.get()+", "
						+"CURRENT_TIMESTAMP)");

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			DefaultInput.lockTest("add", "costStack", "ID", String.valueOf(cbSeparatorSelection.ID.get()));
			DefaultInput.lockTest("add", "costStack", "ID", String.valueOf(cbElectrodeSelection.ID.get()));
			DefaultInput.lockTest("add", "costStack", "ID", String.valueOf(cbBipolarPlateSelection.ID.get()));
			DefaultInput.lockTest("add", "costStack", "ID", String.valueOf(cbCellFramesSealsSelection.ID.get()));
			DefaultInput.lockTest("add", "costStack", "ID", String.valueOf(cbCurrentCollectorSelection.ID.get()));
			DefaultInput.lockTest("add", "costStack", "ID", String.valueOf(cbStackFrameSelection.ID.get()));
			DefaultInput.lockTest("add", "costStack", "ID", String.valueOf(cbAssemblySelection.ID.get()));
			
			System.out.println("String.valueOf(cbSeparatorSelection.ID.get()) "+String.valueOf(cbSeparatorSelection.ID.get()));
		} else {
			System.out.println("Selection name is already assigned!");
		}
	}
	
	
	@FXML
	public void btnDeletePreselectionEvent(ActionEvent event) throws ClassNotFoundException, SQLException {
		
		try {
			Connection con = Database.getConnection("stackSystemSelection");
			Statement state = con.createStatement();
			state.executeUpdate("DELETE FROM stackSystemSelection WHERE StackSystem = '"+cbPreselection.getSelectionModel().getSelectedItem().toString()+"'");

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		DefaultInput.lockTest("delete", "costStack", "ID", String.valueOf(cbSeparatorSelection.ID.get()));
		DefaultInput.lockTest("delete", "costStack", "ID", String.valueOf(cbElectrodeSelection.ID.get()));
		DefaultInput.lockTest("delete", "costStack", "ID", String.valueOf(cbBipolarPlateSelection.ID.get()));
		DefaultInput.lockTest("delete", "costStack", "ID", String.valueOf(cbCellFramesSealsSelection.ID.get()));
		DefaultInput.lockTest("delete", "costStack", "ID", String.valueOf(cbCurrentCollectorSelection.ID.get()));
		DefaultInput.lockTest("delete", "costStack", "ID", String.valueOf(cbStackFrameSelection.ID.get()));
		DefaultInput.lockTest("delete", "costStack", "ID", String.valueOf(cbAssemblySelection.ID.get()));
		
	}
	
	
	@FXML
	public void cbPreselectionEvent(ActionEvent event) throws SQLException, ClassNotFoundException {
		StackSelectionPaneController.this.clearValues();
		StackSelectionPaneController.this.setStackSystemSelection(cbPreselection.getSelectionModel().getSelectedItem().toString(), Double.valueOf(numberCellsPerStackInput.getText()), Double.valueOf(cellAreaInput.getText()));
	}
	
	
	
	public void setCompNumbers(double numberCellsPerStack) {
		
		ObjStackSelection objStackSelectionNumb = StackSelectionPaneController.getCompNumbers(numberCellsPerStack, cellFrames);
		
		separatorStackNumber = objStackSelectionNumb.separatorStackNumber.getValue();
		electrodeStackNumber = objStackSelectionNumb.electrodeStackNumber.getValue();
		bipolarPlateStackNumber = objStackSelectionNumb.bipolarPlateStackNumber.getValue();
		currentCollectorStackNumber = objStackSelectionNumb.currentCollectorStackNumber.getValue();
		stackFrameStackNumber = objStackSelectionNumb.stackFrameStackNumber.getValue();
		cellFramesSealsStackNumber = objStackSelectionNumb.cellFramesSealsStackNumber.getValue();

		componentsNumber = objStackSelectionNumb.componentsNumber.getValue();
		
		objStackSelection.separatorStackNumber.set(separatorStackNumber);
		objStackSelection.electrodeStackNumber.set(electrodeStackNumber);
		objStackSelection.bipolarPlateStackNumber.set(bipolarPlateStackNumber);
		objStackSelection.currentCollectorStackNumber.set(currentCollectorStackNumber);
		objStackSelection.stackFrameStackNumber.set(stackFrameStackNumber);
		objStackSelection.cellFramesSealsStackNumber.set(cellFramesSealsStackNumber);
		objStackSelection.componentsNumber.set(componentsNumber);
		
		
		txfSeparatorStackNumber.setText("n | "+String.valueOf(separatorStackNumber));
		txfElectrodeStackNumber.setText("2 n | "+String.valueOf(electrodeStackNumber));
		txfBipolarPlateStackNumber.setText("n + 1 | "+String.valueOf(bipolarPlateStackNumber));
		txfCellFramesSealsStackNumber.setText("n | "+String.valueOf(cellFramesSealsStackNumber));
		txfCurrentCollectorStackNumber.setText("2 | "+String.valueOf(currentCollectorStackNumber));
		txfStackFrameStackNumber.setText("1 | "+String.valueOf(stackFrameStackNumber));
		txfComponentsNumber.setText(String.valueOf(componentsNumber));
	}
	
	public static ObjStackSelection getCompNumbers(double numberCellsPerStack, boolean cellFrames) {
		ObjStackSelection objStackSelection = new ObjStackSelection();
		
		double separatorStackNumber = numberCellsPerStack;
		double electrodeStackNumber = 2*numberCellsPerStack;
		double bipolarPlateStackNumber = numberCellsPerStack + 1;
		double currentCollectorStackNumber = 2;
		double stackFrameStackNumber = 1;
		
		double cellFramesSealsStackNumber = 0;
		
		if (cellFrames) {
			cellFramesSealsStackNumber = numberCellsPerStack;
		} else {
			cellFramesSealsStackNumber = 0;
		}
		
		double componentsNumber = separatorStackNumber + electrodeStackNumber + bipolarPlateStackNumber + cellFramesSealsStackNumber
				+ currentCollectorStackNumber + stackFrameStackNumber;
		
		objStackSelection.separatorStackNumber.set(separatorStackNumber);
		objStackSelection.electrodeStackNumber.set(electrodeStackNumber);
		objStackSelection.bipolarPlateStackNumber.set(bipolarPlateStackNumber);
		objStackSelection.currentCollectorStackNumber.set(currentCollectorStackNumber);
		objStackSelection.stackFrameStackNumber.set(stackFrameStackNumber);
		objStackSelection.cellFramesSealsStackNumber.set(cellFramesSealsStackNumber);
		objStackSelection.componentsNumber.set(componentsNumber);
		
		return objStackSelection;
	}
	
	

	
	public static ObjStackSelection getStackSystemSelection(ObjStackSelection objStackSelection, String stackSystemSelection, double numberCellsPerStack, double cellArea, boolean cellFrames) throws SQLException, ClassNotFoundException {
		//TODO Add ASR, CellArea (and NumberPerStack) to this selection? --> some parts are only available to specific systems sizes ...
		
		Connection con = Database.getConnection("stackSystemSelection");
		Statement stateStackSystemSelection = con.createStatement();
		
		ResultSet resStackSystemSelection = stateStackSystemSelection.executeQuery("SELECT * FROM stackSystemSelection "
				+ "WHERE StackSystem = '" + stackSystemSelection + "'");

		int separatorID = 0;
		int electrodeID = 0;
		int bipolarPlateID = 0;
		int cellFramesSealsID = 0;
		int currentCollectorID = 0;
		int stackFrameID = 0;
		int assemblyID = 0;
		
		while (resStackSystemSelection.next()) {
			separatorID = resStackSystemSelection.getInt("SeparatorID");
			electrodeID = resStackSystemSelection.getInt("ElectrodeID");
			bipolarPlateID = resStackSystemSelection.getInt("BipolarPlateID");
			cellFramesSealsID = resStackSystemSelection.getInt("CellFramesSealsID");
			currentCollectorID = resStackSystemSelection.getInt("CurrentCollectorID");
			stackFrameID = resStackSystemSelection.getInt("StackFrameID");
			assemblyID = resStackSystemSelection.getInt("AssemblyID");
		} 
		if (separatorID == 0) {
			System.out.println("Stack selection not found!");
			return null;
		}
		
		ObjStackSelection objStackSelectionNumb = StackSelectionPaneController.getCompNumbers(numberCellsPerStack, cellFrames);
		
		double separatorStackNumber = objStackSelectionNumb.separatorStackNumber.getValue();
		double electrodeStackNumber = objStackSelectionNumb.electrodeStackNumber.getValue();
		double bipolarPlateStackNumber = objStackSelectionNumb.bipolarPlateStackNumber.getValue();
		double currentCollectorStackNumber = objStackSelectionNumb.currentCollectorStackNumber.getValue();
		double stackFrameStackNumber = objStackSelectionNumb.stackFrameStackNumber.getValue();
		
		double cellFramesSealsStackNumber = objStackSelectionNumb.cellFramesSealsStackNumber.getValue();
		double componentsNumber = objStackSelectionNumb.componentsNumber.getValue();
		
		//separator
		Connection conSeparator = Database.getConnection("stackSystemSelection");
		Statement stateSeparator = conSeparator.createStatement();
		
		ResultSet resSeparator = stateSeparator.executeQuery("SELECT * FROM costStack "
				+ "WHERE ID = " + separatorID + "");
		
		while (resSeparator.next()) {
			objStackSelection.separatorCostComponentName.set(resSeparator.getString("ComponentName"));
			objStackSelection.separatorCost.set(resSeparator.getDouble("ComponentCost"));
			objStackSelection.separatorCostCostUnit.set(resSeparator.getString("CostUnit"));
			objStackSelection.separatorStackNumber.set(separatorStackNumber);
		}
		
		System.out.println("stackcost "+objStackSelection.separatorCost.get());
		
		//electrode
		Connection conElectrode = Database.getConnection("stackSystemSelection");
		Statement stateElectrode = conElectrode.createStatement();
		
		ResultSet resElectrode = stateElectrode.executeQuery("SELECT * FROM costStack "
				+ "WHERE ID = " + electrodeID + "");
		
		while (resElectrode.next()) {
			objStackSelection.electrodeCostComponentName.set(resElectrode.getString("ComponentName"));
			objStackSelection.electrodeCost.set(resElectrode.getDouble("ComponentCost"));
			objStackSelection.electrodeCostCostUnit.set(resElectrode.getString("CostUnit"));
			objStackSelection.electrodeStackNumber.set(electrodeStackNumber);
		}
		
		//bipolarPlate
		Connection conBipolarPlate = Database.getConnection("stackSystemSelection");
		Statement stateBipolarPlate = conBipolarPlate.createStatement();
		
		ResultSet resBipolarPlate = stateBipolarPlate.executeQuery("SELECT * FROM costStack "
				+ "WHERE ID = " + bipolarPlateID + "");
		
		while (resBipolarPlate.next()) {
			objStackSelection.bipolarPlateCostComponentName.set(resBipolarPlate.getString("ComponentName"));
			objStackSelection.bipolarPlateCost.set(resBipolarPlate.getDouble("ComponentCost"));
			objStackSelection.bipolarPlateCostCostUnit.set(resBipolarPlate.getString("CostUnit"));
			objStackSelection.bipolarPlateStackNumber.set(bipolarPlateStackNumber);
		}
		
		//cellFramesSeals
		Connection conCellFramesSeals = Database.getConnection("stackSystemSelection");
		Statement stateCellFramesSeals = conCellFramesSeals.createStatement();
		
		ResultSet resCellFramesSeals = stateCellFramesSeals.executeQuery("SELECT * FROM costStack "
				+ "WHERE ID = " + cellFramesSealsID + "");
		
		while (resCellFramesSeals.next()) {
			objStackSelection.cellFramesSealsCostComponentName.set(resCellFramesSeals.getString("ComponentName"));
			objStackSelection.cellFramesSealsCost.set(resCellFramesSeals.getDouble("ComponentCost"));
			objStackSelection.cellFramesSealsCostCostUnit.set(resCellFramesSeals.getString("CostUnit"));
			objStackSelection.cellFramesSealsStackNumber.set(cellFramesSealsStackNumber);
		}
		
		//currentCollector
		Connection conCurrentCollector = Database.getConnection("stackSystemSelection");
		Statement stateCurrentCollector = conCurrentCollector.createStatement();
		
		ResultSet resCurrentCollector = stateCurrentCollector.executeQuery("SELECT * FROM costStack "
				+ "WHERE ID = " + currentCollectorID + "");
		
		while (resCurrentCollector.next()) {
			objStackSelection.currentCollectorCostComponentName.set(resCurrentCollector.getString("ComponentName"));
			objStackSelection.currentCollectorCost.set(resCurrentCollector.getDouble("ComponentCost"));
			objStackSelection.currentCollectorCostCostUnit.set(resCurrentCollector.getString("CostUnit"));
			objStackSelection.currentCollectorStackNumber.set(currentCollectorStackNumber);
		}

		//stackFrame
		Connection conStackFrame = Database.getConnection("stackSystemSelection");
		Statement stateStackFrame = conStackFrame.createStatement();
		
		ResultSet resStackFrame = stateStackFrame.executeQuery("SELECT * FROM costStack "
				+ "WHERE ID = " + stackFrameID + "");
		
		while (resStackFrame.next()) {
			objStackSelection.stackFrameCostComponentName.set(resStackFrame.getString("ComponentName"));
			objStackSelection.stackFrameCost.set(resStackFrame.getDouble("ComponentCost"));
			objStackSelection.stackFrameCostCostUnit.set(resStackFrame.getString("CostUnit"));
			objStackSelection.stackFrameStackNumber.set(stackFrameStackNumber);
		}
		
		//assemlby
		Connection conAssembly = Database.getConnection("stackSystemSelection");
		Statement stateAssembly = conAssembly.createStatement();
		
		ResultSet resAssembly = stateAssembly.executeQuery("SELECT * FROM costStack "
				+ "WHERE ID = " + assemblyID + "");
		
		while (resAssembly.next()) {
			objStackSelection.assemblyCostComponentName.set(resAssembly.getString("ComponentName"));
			try {
				objStackSelection.assemblyCost.set(resAssembly.getDouble("ComponentCost"));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			objStackSelection.assemblyCostCostUnit.set(resAssembly.getString("CostUnit"));
			objStackSelection.componentsNumber.set(componentsNumber);
		}
		
		

		
		return objStackSelection;
		
	}
	
	public void setStackSystemSelection(String stackSystemSelection, double numberCellsPerStack, double cellArea) throws SQLException, ClassNotFoundException {
		
		boolean cellFrames = false;
		
		StackSelectionPaneController.getStackSystemSelection(objStackSelection, stackSystemSelection, numberCellsPerStack, cellArea, cellFrames);
		
		//add values to combo
		if (objStackSelection.separatorCostComponentName.getValue() != null) {
			cbSeparator.setValue(objStackSelection.separatorCostComponentName.getValue().toString());
		}
		if (objStackSelection.electrodeCostComponentName.getValue() != null) {
			cbElectrode.setValue(objStackSelection.electrodeCostComponentName.getValue().toString());
		}
		if (objStackSelection.bipolarPlateCostComponentName.getValue() != null) {
			cbBipolarPlate.setValue(objStackSelection.bipolarPlateCostComponentName.getValue().toString());
		}
		if (objStackSelection.cellFramesSealsCostComponentName.getValue() != null) {
			cbCellFramesSeals.setValue(objStackSelection.cellFramesSealsCostComponentName.getValue().toString());
		}
		if (objStackSelection.currentCollectorCostComponentName.getValue() != null) {
			cbCurrentCollector.setValue(objStackSelection.currentCollectorCostComponentName.getValue().toString());
		}
		if (objStackSelection.stackFrameCostComponentName.getValue() != null) {
			cbStackFrame.setValue(objStackSelection.stackFrameCostComponentName.getValue().toString());
		}
		if (objStackSelection.assemblyCostComponentName.getValue() != null) {
			cbAssembly.setValue(objStackSelection.assemblyCostComponentName.getValue().toString());
		}
		
		StackSelectionPaneController.this.refreshValues();
		StackSelectionPaneController.this.setCompNumbers(numberCellsPerStack);
		
		ObjCostAnalysisOutput output = new ObjCostAnalysisOutput();
		
		output = CostAnalysisCalculation.stackCalculation(objStackSelection, output);
		txfStackCostTotal.setText(String.valueOf(output.stackCostTotal.get()));
		txfStackCost.setText(String.valueOf(output.stackCost.get()));
		
//		TODO clear ASR after separator is changed?
		
	}
	
	public void populateCbASR () {
		
		try {
			ResultSet res = Database.selectData("separatorParameter");

			ObservableList<String> dataASR = FXCollections.observableArrayList();
	    	while (res.next()) {
	    		dataASR.add(res.getString("SeparatorName"));
	    	}
	    	cbASR.setItems(dataASR);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	@FXML
	public void cbASREvent(ActionEvent event) throws ClassNotFoundException, SQLException {
		
		Connection conSeparatorParameter = Database.getConnection("separatorParameter");
		Statement stateSeparatorParameter = conSeparatorParameter.createStatement();
		
		ResultSet resStackFrame = stateSeparatorParameter.executeQuery("SELECT * FROM separatorParameter "
				+ "WHERE SeparatorName = '" + cbASR.getSelectionModel().getSelectedItem().toString() + "'");
    	while (resStackFrame.next()) {
    		objStackSelection.asr.set(resStackFrame.getDouble("Rasr"));
    	}
    	txfASR.setText(String.valueOf(objStackSelection.asr.get()));
	}
	
	@FXML
	public void okEvent(ActionEvent event) {
		Stage stage = (Stage) AnchorPane1.getScene().getWindow();
		stage.close();
	}
	
}
