package de.dominikemmel.reflowlab.controller.electrolytes;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import de.dominikemmel.reflowlab.Database;
import de.dominikemmel.reflowlab.MyConstants;
import de.dominikemmel.reflowlab.VariousMethods;
import de.dominikemmel.reflowlab.controller.costanalysistool.CostAnalysisToolController;
import de.dominikemmel.reflowlab.controller.references.ObjReference;
import de.dominikemmel.reflowlab.controller.references.ReferencesController;

public class AddElectrolytesController implements javafx.fxml.Initializable {
	@FXML private AnchorPane addPane;

	@FXML
	private TextField inputActiveMaterial;
	@FXML
	private TextField inputSolvent;
	@FXML
	private TextField inputSalt;
	@FXML
	private TextField inputcSalt;
	@FXML
	private TextField inputpH;
	@FXML
	private TextField inputmaxSolubility;
	@FXML
	private TextField inputDOx;
	@FXML
	private TextField inputDRed;
	@FXML
	private TextField inputkOx;
	@FXML
	private TextField inputAlphaOx;
	@FXML
	private TextField inputkRed;
	@FXML
	private TextField inputAlphaRed;
	@FXML
	private TextField inputdegRate;
	@FXML
	private TextField inputf;
	@FXML
	private TextField inputTheoMaxCap;
	@FXML
	private TextField inputNote;
	@FXML
	private TextField inputfEloVol;
	@FXML
	private TextField inputfConc;
	@FXML
	private CheckBox inputfSymCellCycl;
	
	@FXML
	private TextField inputRefDOIMaxSolubility;
	@FXML
	private TextField inputRefMaxSolubility;
	@FXML
	private TextField inputRefDOIDOx;
	@FXML
	private TextField inputRefDOx;
	@FXML
	private TextField inputRefDOIDRed;
	@FXML
	private TextField inputRefDRed;
	@FXML
	private TextField inputRefDOIkOx;
	@FXML
	private TextField inputRefkOx;
	@FXML
	private TextField inputRefDOIAlphaOx;
	@FXML
	private TextField inputRefAlphaOx;
	@FXML
	private TextField inputRefDOIkRed;
	@FXML
	private TextField inputRefkRed;
	@FXML
	private TextField inputRefDOIAlphaRed;
	@FXML
	private TextField inputRefAlphaRed;
	@FXML
	private TextField inputRefDOIdegRate;
	@FXML
	private TextField inputRefdegRate;
	@FXML
	private TextField inputRefDOIF;
	@FXML
	private TextField inputRefF;
	
	@FXML
	private Button btnInputRefMaxSolubility; 
	@FXML
	private Button btnInputRefDOx; 
	@FXML
	private Button btnInputRefDRed; 
	@FXML
	private Button btnInputRefkOx; 
	@FXML
	private Button btnInputRefAlphaOx; 
	@FXML
	private Button btnInputRefkRed; 
	@FXML
	private Button btnInputRefAlphaRed; 
	@FXML
	private Button btnInputRefdegRate; 
	@FXML
	private Button btnInputRefF; 
	
	@FXML
	private Button btnCalcQmax; 
	
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
	@FXML private TextFlow theoMaxCap_TextFlow;
	
	String table = "electrolyte";

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Platform.runLater(new Runnable() {

            @Override
            public void run() {
            	inputActiveMaterial.requestFocus();
            }
        });
		
		
    	//ActiveMaterial:
    	Text ActiveMaterial1 = new Text("Active material");
    	ActiveMaterial1.setStyle("-fx-font-weight: bold");
		Text ActiveMaterial2 = new Text(":");
		ActiveMaterial2.setStyle("-fx-font-weight: bold");

    	ActiveMaterial_TextFlow.getChildren().addAll(ActiveMaterial1,ActiveMaterial2);

    	//Solvent:
    	Text Solvent1 = new Text("Solvent");
    	Solvent1.setStyle("-fx-font-weight: bold");
		Text Solvent2 = new Text(":");
		Solvent2.setStyle("-fx-font-weight: bold");

    	Solvent_TextFlow.getChildren().addAll(Solvent1,Solvent2);

    	//Salt:
    	Text Salt1 = new Text("Salt");
    	Salt1.setStyle("-fx-font-weight: bold");
		Text Salt2 = new Text(":");
		Salt2.setStyle("-fx-font-weight: bold");

    	Salt_TextFlow.getChildren().addAll(Salt1,Salt2);

    	//cSalt:
    	Text cSalt1 = new Text("c");
    	cSalt1.setStyle("-fx-font-weight: bold; -fx-font-style: italic");
		Text cSalt2 = new Text("salt");
		cSalt2.setStyle("-fx-font-weight: bold");
		cSalt2.setTranslateY(cSalt1.getFont().getSize() * 0.3);
		cSalt2.setFont(Font.font(cSalt1.getFont().getStyle(),cSalt1.getFont().getSize()*0.75));
		Text cSalt3 = new Text(" / mol L");
		cSalt3.setStyle("-fx-font-weight: bold");
		Text cSalt4 = new Text("⁻¹");
		cSalt4.setStyle("-fx-font-weight: bold");
		Text cSalt5 = new Text(":");
		cSalt5.setStyle("-fx-font-weight: bold");

    	cSalt_TextFlow.getChildren().addAll(cSalt1,cSalt2,cSalt3,cSalt4,cSalt5);

    	//pH:
    	Text pH1 = new Text("pH");
    	pH1.setStyle("-fx-font-weight: bold");
		Text pH2 = new Text(":");
		pH2.setStyle("-fx-font-weight: bold");

    	pH_TextFlow.getChildren().addAll(pH1,pH2);

    	//maxSolubility:
    	Text maxSolubility1 = new Text("solubility");
    	maxSolubility1.setStyle("-fx-font-weight: bold");
		Text maxSolubility2 = new Text(" / mol L");
		maxSolubility2.setStyle("-fx-font-weight: bold");
		Text maxSolubility3 = new Text("⁻¹");
		maxSolubility3.setStyle("-fx-font-weight: bold");
		Text maxSolubility4 = new Text(":");
		maxSolubility4.setStyle("-fx-font-weight: bold");

		maxSolubility_TextFlow.getChildren().addAll(maxSolubility1,maxSolubility2,maxSolubility3,maxSolubility4);

    	//D ox:
    	Text Dox1 = new Text("D ox");
    	Dox1.setStyle("-fx-font-weight: bold; -fx-font-style: italic");
		Text Dox2 = new Text(" / cm");
		Dox2.setStyle("-fx-font-weight: bold");
		Text Dox3 = new Text("²");
		Dox3.setStyle("-fx-font-weight: bold");
    	Text Dox4 = new Text(" s");
    	Dox4.setStyle("-fx-font-weight: bold");
		Text Dox5 = new Text("⁻¹");
		Dox5.setStyle("-fx-font-weight: bold");
		Text Dox6 = new Text(":");
		Dox6.setStyle("-fx-font-weight: bold");

    	DOx_TextFlow.getChildren().addAll(Dox1,Dox2,Dox3,Dox4,Dox5,Dox6);

    	//D red:
    	Text Dred1 = new Text("D red");
    	Dred1.setStyle("-fx-font-weight: bold; -fx-font-style: italic");
		Text Dred2 = new Text(" / cm");
		Dred2.setStyle("-fx-font-weight: bold");
		Text Dred3 = new Text("²");
		Dred3.setStyle("-fx-font-weight: bold");
    	Text Dred4 = new Text(" s");
    	Dred4.setStyle("-fx-font-weight: bold");
		Text Dred5 = new Text("⁻¹");
		Dred5.setStyle("-fx-font-weight: bold");
		Text Dred6 = new Text(":");
		Dred6.setStyle("-fx-font-weight: bold");

    	DRed_TextFlow.getChildren().addAll(Dred1,Dred2,Dred3,Dred4,Dred5,Dred6);

    	//k ox:
    	Text kox1 = new Text("k ox");
    	kox1.setStyle("-fx-font-weight: bold; -fx-font-style: italic");
		Text kox2 = new Text(" / cm");
		kox2.setStyle("-fx-font-weight: bold");
    	Text kox4 = new Text(" s");
    	kox4.setStyle("-fx-font-weight: bold");
		Text kox5 = new Text("⁻¹");
		kox5.setStyle("-fx-font-weight: bold");
		Text kox6 = new Text(":");
		kox6.setStyle("-fx-font-weight: bold");

    	kOx_TextFlow.getChildren().addAll(kox1,kox2,kox4,kox5,kox6);

    	//Alpha ox:
    	Text alphaOx1 = new Text("a ox");
    	alphaOx1.setStyle("-fx-font-weight: bold; -fx-font-style: italic");
		Text alphaOx2 = new Text(":");
		alphaOx2.setStyle("-fx-font-weight: bold");

    	AlphaOx_TextFlow.getChildren().addAll(alphaOx1,alphaOx2);

    	//Alpha red:
    	Text alphaRed1 = new Text("a red");
    	alphaRed1.setStyle("-fx-font-weight: bold; -fx-font-style: italic");
		Text alphaRed2 = new Text(":");
		alphaRed2.setStyle("-fx-font-weight: bold");

    	AlphaRed_TextFlow.getChildren().addAll(alphaRed1,alphaRed2);

    	//k red:
    	Text kred1 = new Text("k red");
    	kred1.setStyle("-fx-font-weight: bold; -fx-font-style: italic");
		Text kred2 = new Text(" / cm");
		kred2.setStyle("-fx-font-weight: bold");
    	Text kred4 = new Text(" s");
    	kred4.setStyle("-fx-font-weight: bold");
		Text kred5 = new Text("⁻¹");
		kred5.setStyle("-fx-font-weight: bold");
		Text kred6 = new Text(":");
		kred6.setStyle("-fx-font-weight: bold");

    	kRed_TextFlow.getChildren().addAll(kred1,kred2,kred4,kred5,kred6);

    	//degRate:
    	Text degRate1 = new Text("fade rate / % d");
    	degRate1.setStyle("-fx-font-weight: bold; -fx-fill: #ffffff");
    	Text degRate2 = new Text("⁻¹");
    	degRate2.setStyle("-fx-font-weight: bold; -fx-fill: #ffffff");
		Text degRate3 = new Text(":");
		degRate3.setStyle("-fx-font-weight: bold; -fx-fill: #ffffff");

    	degRate_TextFlow.getChildren().addAll(degRate1, degRate2, degRate3);

    	//f:
    	Text f1 = new Text("f ");
    	f1.setStyle("-fx-font-weight: bold; -fx-font-style: italic; -fx-fill: #ffffff");
    	Text f2 = new Text("/ Y");
    	f2.setStyle("-fx-font-weight: bold; -fx-fill: #ffffff");
    	Text f3 = new Text("⁻¹");
    	f3.setStyle("-fx-font-weight: bold; -fx-fill: #ffffff");
		Text f4 = new Text(":");
		f4.setStyle("-fx-font-weight: bold; -fx-fill: #ffffff");

    	f_TextFlow.getChildren().addAll(f1, f2, f3, f4);
    	
    	//fEloVol:
    	Text fEloVol1 = new Text("V");
    	fEloVol1.setStyle("-fx-font-weight: bold; -fx-font-style: italic; -fx-fill: #ffffff");
    	Text fEloVol2 = new Text("theo., max., cap.");
    	fEloVol2.setStyle("-fx-font-weight: bold; -fx-fill: #ffffff");
    	fEloVol2.setTranslateY(fEloVol1.getFont().getSize() * 0.3);
    	fEloVol2.setFont(Font.font(fEloVol1.getFont().getStyle(),fEloVol1.getFont().getSize()*0.75));
    	Text fEloVol3 = new Text(" / mL");
    	fEloVol3.setStyle("-fx-font-weight: bold; -fx-fill: #ffffff");
		Text fEloVol4 = new Text(":");
		fEloVol4.setStyle("-fx-font-weight: bold; -fx-fill: #ffffff");
    	
    	fEloVol_TextFlow.getChildren().addAll(fEloVol1, fEloVol2, fEloVol3, fEloVol4);
    	
    	//fConc:
    	Text fConc1 = new Text("c");
    	fConc1.setStyle("-fx-font-weight: bold; -fx-font-style: italic; -fx-fill: #ffffff");
    	Text fConc2 = new Text("theo., max., cap.");
    	fConc2.setStyle("-fx-font-weight: bold; -fx-fill: #ffffff");
    	fConc2.setTranslateY(fConc1.getFont().getSize() * 0.3);
    	fConc2.setFont(Font.font(fConc1.getFont().getStyle(),fConc1.getFont().getSize()*0.75));
    	Text fConc3 = new Text(" / mol L");
    	fConc3.setStyle("-fx-font-weight: bold; -fx-fill: #ffffff");
    	Text fConc4 = new Text("⁻¹");
    	fConc4.setStyle("-fx-font-weight: bold; -fx-fill: #ffffff");
		Text fConc5 = new Text(":");
		fConc5.setStyle("-fx-font-weight: bold; -fx-fill: #ffffff");
    	
    	fConc_TextFlow.getChildren().addAll(fConc1, fConc2, fConc3, fConc4, fConc5);
    	
    	//note:
    	Text note1 = new Text("note");
    	note1.setStyle("-fx-font-weight: bold; -fx-fill: #ffffff");
		Text note2 = new Text(":");
		note2.setStyle("-fx-font-weight: bold; -fx-fill: #ffffff");
    	
    	note_TextFlow.getChildren().addAll(note1, note2);
    	
    	//theoMaxCap:
    	Text theoMaxCap1 = new Text("q");
    	theoMaxCap1.setStyle("-fx-font-weight: bold; -fx-font-style: italic; -fx-fill: #ffffff");
    	Text theoMaxCap2 = new Text("max., theo.");
    	theoMaxCap2.setStyle("-fx-font-weight: bold; -fx-fill: #ffffff");
    	theoMaxCap2.setTranslateY(theoMaxCap1.getFont().getSize() * 0.3);
    	Text theoMaxCap3 = new Text("/ mAh L");
    	theoMaxCap3.setStyle("-fx-font-weight: bold; -fx-fill: #ffffff");
    	Text theoMaxCap4 = new Text("⁻¹");
    	theoMaxCap4.setStyle("-fx-font-weight: bold; -fx-fill: #ffffff");
    	Text theoMaxCap5 = new Text(":");
    	theoMaxCap5.setStyle("-fx-font-weight: bold; -fx-fill: #ffffff");
    	
    	theoMaxCap_TextFlow.getChildren().addAll(theoMaxCap1, theoMaxCap2, theoMaxCap3, theoMaxCap4, theoMaxCap5);
		
		
		
		Image img = new Image(getClass().getResourceAsStream("/de/dominikemmel/reflowlab/img/ArrowDown/0.5x/Asset 2@0.5x.png"));
		ImageView imgViewBtnInputRefMaxSolubility = new ImageView(img);
		imgViewBtnInputRefMaxSolubility.setFitHeight(10);
		imgViewBtnInputRefMaxSolubility.setFitWidth(12);
		
		ImageView imgViewBtnInputRefDOx = new ImageView(img);
		imgViewBtnInputRefDOx.setFitHeight(10);
		imgViewBtnInputRefDOx.setFitWidth(12);
		
		ImageView imgViewBtnInputRefDRed = new ImageView(img);
		imgViewBtnInputRefDRed.setFitHeight(10);
		imgViewBtnInputRefDRed.setFitWidth(12);
		
		ImageView imgViewBtnInputRefkOx = new ImageView(img);
		imgViewBtnInputRefkOx.setFitHeight(10);
		imgViewBtnInputRefkOx.setFitWidth(12);
		
		ImageView imgViewBtnInputRefAlphaOx = new ImageView(img);
		imgViewBtnInputRefAlphaOx.setFitHeight(10);
		imgViewBtnInputRefAlphaOx.setFitWidth(12);
		
		ImageView imgViewBtnInputRefkRed = new ImageView(img);
		imgViewBtnInputRefkRed.setFitHeight(10);
		imgViewBtnInputRefkRed.setFitWidth(12);
		
		ImageView imgViewBtnInputRefAlphaRed = new ImageView(img);
		imgViewBtnInputRefAlphaRed.setFitHeight(10);
		imgViewBtnInputRefAlphaRed.setFitWidth(12);
		
		ImageView imgViewBtnInputRefdegRate = new ImageView(img);
		imgViewBtnInputRefdegRate.setFitHeight(10);
		imgViewBtnInputRefdegRate.setFitWidth(12);
		
		ImageView imgViewBtnInputRefF = new ImageView(img);
		imgViewBtnInputRefF.setFitHeight(10);
		imgViewBtnInputRefF.setFitWidth(12);
		
		btnInputRefMaxSolubility.setGraphic(imgViewBtnInputRefMaxSolubility);
		btnInputRefDOx.setGraphic(imgViewBtnInputRefDOx);
		btnInputRefDRed.setGraphic(imgViewBtnInputRefDRed);
		btnInputRefkOx.setGraphic(imgViewBtnInputRefkOx);
		btnInputRefAlphaOx.setGraphic(imgViewBtnInputRefAlphaOx);
		btnInputRefkRed.setGraphic(imgViewBtnInputRefkRed);
		btnInputRefAlphaRed.setGraphic(imgViewBtnInputRefAlphaRed);
		btnInputRefdegRate.setGraphic(imgViewBtnInputRefdegRate);
		btnInputRefF.setGraphic(imgViewBtnInputRefF);
		
		
		inputRefDOIMaxSolubility.textProperty().addListener((observable, oldValue, newValue) -> {
		    if(newValue == "" || newValue == null) {
		    	disableBtn(btnInputRefMaxSolubility);
		    } else {
		    	enableBtn(btnInputRefMaxSolubility);
		    	
				ObjReference objReference;
				try {
					objReference = ReferencesController.checkRef(newValue);
					
					if (objReference.Reference.get() != null) {
						inputRefMaxSolubility.setText(objReference.Reference.get());
					}
					
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
		    }
		});
		inputRefDOIDOx.textProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue == "" || newValue == null) {
				disableBtn(btnInputRefDOx);
			} else {
				enableBtn(btnInputRefDOx);
				
				ObjReference objReference;
				try {
					objReference = ReferencesController.checkRef(newValue);
					
					if (objReference.Reference.get() != null) {
						inputRefDOx.setText(objReference.Reference.get());
					}
					
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}
		});
		inputRefDOIDRed.textProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue == "" || newValue == null) {
				disableBtn(btnInputRefDRed);
			} else {
				enableBtn(btnInputRefDRed);
				
				ObjReference objReference;
				try {
					objReference = ReferencesController.checkRef(newValue);
					
					if (objReference.Reference.get() != null) {
						inputRefDRed.setText(objReference.Reference.get());
					}
					
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}
		});
		inputRefDOIkOx.textProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue == "" || newValue == null) {
				disableBtn(btnInputRefkOx);
			} else {
				enableBtn(btnInputRefkOx);
				
				ObjReference objReference;
				try {
					objReference = ReferencesController.checkRef(newValue);
					
					if (objReference.Reference.get() != null) {
						inputRefkOx.setText(objReference.Reference.get());
					}
					
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}
		});
		
		inputRefDOIAlphaOx.textProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue == "" || newValue == null) {

			} else {
				
				ObjReference objReference;
				try {
					objReference = ReferencesController.checkRef(newValue);
					
					if (objReference.Reference.get() != null) {
						inputRefAlphaOx.setText(objReference.Reference.get());
					}
					
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}
		});
		
		inputRefDOIkRed.textProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue == "" || newValue == null) {
				disableBtn(btnInputRefkRed);
			} else {
				enableBtn(btnInputRefkRed);
				
				ObjReference objReference;
				try {
					objReference = ReferencesController.checkRef(newValue);
					
					if (objReference.Reference.get() != null) {
						inputRefkRed.setText(objReference.Reference.get());
					}
					
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}
		});
		
		inputRefDOIAlphaRed.textProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue == "" || newValue == null) {

			} else {
				
				ObjReference objReference;
				try {
					objReference = ReferencesController.checkRef(newValue);
					
					if (objReference.Reference.get() != null) {
						inputRefAlphaRed.setText(objReference.Reference.get());
					}
					
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}
		});
		
		inputRefDOIdegRate.textProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue == "" || newValue == null) {
				disableBtn(btnInputRefdegRate);
			} else {
				enableBtn(btnInputRefdegRate);
				
				ObjReference objReference;
				try {
					objReference = ReferencesController.checkRef(newValue);
					
					if (objReference.Reference.get() != null) {
						inputRefdegRate.setText(objReference.Reference.get());
					}
					
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}
		});
		
		inputRefDOIF.textProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue == "" || newValue == null) {

			} else {
				
				ObjReference objReference;
				try {
					objReference = ReferencesController.checkRef(newValue);
					
					if (objReference.Reference.get() != null) {
						inputRefF.setText(objReference.Reference.get());
					}
					
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}
		});
		
		
		Image imgCalculator = new Image(getClass().getResourceAsStream("/de/dominikemmel/reflowlab/img/Calculator/calculator.png"));
		ImageView imgViewBtnInputCalculator = new ImageView(imgCalculator);
		imgViewBtnInputCalculator.setFitHeight(20);
		imgViewBtnInputCalculator.setFitWidth(20);
		
		btnCalcQmax.setGraphic(imgViewBtnInputCalculator);
	}

	public void enableBtn(Button btn) {
		btn.setDisable(false);
		btn.setVisible(true);
	}
	public void disableBtn(Button btn) {
		btn.setDisable(true);
		btn.setVisible(false);
	}
	
	@FXML
	public void btnInputRefMaxSolubilityEvent(ActionEvent event) {
		inputRefDOIDOx.setText(inputRefDOIMaxSolubility.getText());
		inputRefDOx.setText(inputRefMaxSolubility.getText());
		inputRefDOIDRed.setText(inputRefDOIMaxSolubility.getText());
		inputRefDRed.setText(inputRefMaxSolubility.getText());
		inputRefDOIkOx.setText(inputRefDOIMaxSolubility.getText());
		inputRefkOx.setText(inputRefMaxSolubility.getText());
		inputRefDOIAlphaOx.setText(inputRefDOIMaxSolubility.getText());
		inputRefAlphaOx.setText(inputRefMaxSolubility.getText());
		inputRefDOIkRed.setText(inputRefDOIMaxSolubility.getText());
		inputRefkRed.setText(inputRefMaxSolubility.getText());
		inputRefDOIAlphaRed.setText(inputRefDOIMaxSolubility.getText());
		inputRefAlphaRed.setText(inputRefMaxSolubility.getText());
		inputRefDOIdegRate.setText(inputRefDOIMaxSolubility.getText());
		inputRefdegRate.setText(inputRefMaxSolubility.getText());
		inputRefDOIF.setText(inputRefDOIMaxSolubility.getText());
		inputRefF.setText(inputRefMaxSolubility.getText());
	}
	
	@FXML
	public void btnInputRefDOxEvent(ActionEvent event) {
		inputRefDOIkOx.setText(inputRefDOIDOx.getText());
		inputRefkOx.setText(inputRefDOx.getText());
		inputRefDOIAlphaOx.setText(inputRefDOIDOx.getText());
		inputRefAlphaOx.setText(inputRefDOx.getText());
		inputRefDOIdegRate.setText(inputRefDOIDOx.getText());
		inputRefdegRate.setText(inputRefDOx.getText());
		inputRefDOIF.setText(inputRefDOIDOx.getText());
		inputRefF.setText(inputRefDOx.getText());
	}
	
	@FXML
	public void btnInputRefDRedEvent(ActionEvent event) {
		inputRefDOIkRed.setText(inputRefDOIDRed.getText());
		inputRefkRed.setText(inputRefDRed.getText());
		inputRefDOIAlphaRed.setText(inputRefDOIDRed.getText());
		inputRefAlphaRed.setText(inputRefDRed.getText());
		inputRefDOIdegRate.setText(inputRefDOIDRed.getText());
		inputRefdegRate.setText(inputRefDRed.getText());
		inputRefDOIF.setText(inputRefDOIDRed.getText());
		inputRefF.setText(inputRefDRed.getText());
	}
	
	@FXML
	public void btnInputRefkOxEvent(ActionEvent event) {
		inputRefDOIAlphaOx.setText(inputRefDOIkOx.getText());
		inputRefAlphaOx.setText(inputRefkOx.getText());
	}
	
	@FXML
	public void btnInputRefkRedEvent(ActionEvent event) {
		inputRefDOIAlphaRed.setText(inputRefDOIkRed.getText());
		inputRefAlphaRed.setText(inputRefkRed.getText());
	}
	
	@FXML
	public void btnInputRefdegRateEvent(ActionEvent event) {
		inputRefDOIF.setText(inputRefDOIdegRate.getText());
		inputRefF.setText(inputRefdegRate.getText());
	}
	
	@FXML
	public void btnCalcQmaxEvent(ActionEvent event) {
		
		double vol = Double.valueOf(inputfEloVol.getText()) * Math.pow(10, -3); 
		double conc = Double.valueOf(inputfConc.getText()); 
		
		int numberEl = 0;
		
		try {
			
			ResultSet res = Database.selectData("activeMaterial");

			ObservableList<Integer> dataActiveMaterial = FXCollections.observableArrayList();

			while (res.next()) {
				String sqlAbbrev = res.getString("ABBREVIATION");
				
				if (sqlAbbrev.equals(inputActiveMaterial.getText())) {
					int dataNumberEl = res.getInt("N");
					dataActiveMaterial.add(dataNumberEl);
				}
			}

			if (!dataActiveMaterial.isEmpty()) {
				numberEl = dataActiveMaterial.get(0).intValue();
			}
 

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		// theo. max. capacity / mAh
		double maxCap = 0;
		if (numberEl > 0) {
			maxCap = MyConstants.F * vol * conc * numberEl * Math.pow(10, 3) / (60 * 60);
		}
		
		inputTheoMaxCap.setText(String.valueOf(maxCap));
	}
	
	
	// Event Listener on Button.onAction
	@FXML
	public void cancelAddElectrolytes(ActionEvent event) {
		//close Add-Pane:
		Stage stage = (Stage) addPane.getScene().getWindow();
		stage.close();
	}
	// Event Listener on Button.onAction
	@FXML
	public void addAddElectrolytes(ActionEvent event) throws ClassNotFoundException, SQLException {
		ObjElectrolytes objElectrolytes = new ObjElectrolytes();

		String inputActiveMaterial_Value = VariousMethods.getTextFieldInput(inputActiveMaterial, "stringInput");
		String inputSolvent_Value = VariousMethods.getTextFieldInput(inputSolvent, "stringInput");
		String inputSalt_Value = VariousMethods.getTextFieldInput(inputSalt, "stringInput");
		Double inputcSalt_Value = VariousMethods.getTextFieldInput(inputcSalt, "doubleInput");
		Double inputpH_Value = VariousMethods.getTextFieldInput(inputpH, "doubleInput");
		Double inputmaxSolubility_Value = VariousMethods.getTextFieldInput(inputmaxSolubility, "doubleInput");
		Double inputDOx_Value = VariousMethods.getTextFieldInput(inputDOx, "doubleInput");
		Double inputDRed_Value = VariousMethods.getTextFieldInput(inputDRed, "doubleInput");
		Double inputkOx_Value = VariousMethods.getTextFieldInput(inputkOx, "doubleInput");
		Double inputAlphaOx_Value = VariousMethods.getTextFieldInput(inputAlphaOx, "doubleInput");
		Double inputkRed_Value = VariousMethods.getTextFieldInput(inputkRed, "doubleInput");
		Double inputAlphaRed_Value = VariousMethods.getTextFieldInput(inputAlphaRed, "doubleInput");
		Double inputdegRate_Value = VariousMethods.getTextFieldInput(inputdegRate, "doubleInput");
		Double inputf_Value = VariousMethods.getTextFieldInput(inputf, "doubleInput");
		Double inputTheoMaxCap_Value = VariousMethods.getTextFieldInput(inputTheoMaxCap, "doubleInput");
		String inputNote_Value = VariousMethods.getTextFieldInput(inputNote, "stringInput");
		Double inputfEloVol_Value = VariousMethods.getTextFieldInput(inputfEloVol, "doubleInput");
		Double inputfConc_Value = VariousMethods.getTextFieldInput(inputfConc, "doubleInput");
		Boolean inputfSymCellCycl_Value = inputfSymCellCycl.isSelected();

		objElectrolytes.ActiveMaterial.set(inputActiveMaterial_Value);
		objElectrolytes.Solvent.set(inputSolvent_Value);
		objElectrolytes.Salt.set(inputSalt_Value);
		objElectrolytes.cSalt.set(inputcSalt_Value);
		objElectrolytes.pH.set(inputpH_Value);
		objElectrolytes.maxSolubility.set(inputmaxSolubility_Value);
		objElectrolytes.DOx.set(inputDOx_Value);
		objElectrolytes.DRed.set(inputDRed_Value);
		objElectrolytes.kOx.set(inputkOx_Value);
		objElectrolytes.AlphaOx.set(inputAlphaOx_Value);
		objElectrolytes.kRed.set(inputkRed_Value);
		objElectrolytes.AlphaRed.set(inputAlphaRed_Value);
		objElectrolytes.degRate.set(inputdegRate_Value);
		objElectrolytes.f.set(inputf_Value);
		objElectrolytes.theoMaxCap.set(inputTheoMaxCap_Value);
		objElectrolytes.note.set(inputNote_Value);
		objElectrolytes.fEloVol.set(inputfEloVol_Value);
		objElectrolytes.fConc.set(inputfConc_Value);
		objElectrolytes.fSymCellCycl.set(inputfSymCellCycl_Value);
		
		double fSymCellCycl_sql = 0;
		if (inputfSymCellCycl_Value) {
			fSymCellCycl_sql = 1;
		} else {
			fSymCellCycl_sql = 0;
		}

		try {
			Connection con = Database.getConnection("electrolyte");
			Statement state = con.createStatement();
			state.executeUpdate("INSERT INTO electrolyte(ID, ActiveMaterial, Solvent, Salt, cSalt, pH, maxSolubility, DOx, DRed, kOx, AlphaOx, kRed, AlphaRed, degRate, f, theoMaxCap, fEloVol, fConc, note, fSymCellCycl, editDate)"
					+" VALUES(DEFAULT, '"+objElectrolytes.ActiveMaterial.getValue()+"', '"
					+objElectrolytes.Solvent.getValue()+"', '"+objElectrolytes.Salt.getValue()+"', "
					+objElectrolytes.cSalt.getValue()+", "
					+objElectrolytes.pH.getValue()+", "+objElectrolytes.maxSolubility.getValue()+", "
					+objElectrolytes.DOx.getValue()+", "+objElectrolytes.DRed.getValue()+", "+objElectrolytes.kOx.getValue()+", "
					+objElectrolytes.AlphaOx.getValue()+", "+objElectrolytes.kRed.getValue()+", "+objElectrolytes.AlphaRed.getValue()+", "
					+objElectrolytes.degRate.getValue()+", "+objElectrolytes.f.getValue()+", "+objElectrolytes.theoMaxCap.getValue()+", "+objElectrolytes.fEloVol.getValue()+", "+objElectrolytes.fConc.getValue()+", '"+objElectrolytes.note.getValue()+"', "+fSymCellCycl_sql+", CURRENT_TIMESTAMP)");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//Ref Input
		String inputRefDOIMaxSolubility_Value = VariousMethods.getTextFieldInput(inputRefDOIMaxSolubility, "stringInput");
		String inputRefMaxSolubility_Value = VariousMethods.getTextFieldInput(inputRefMaxSolubility, "stringInput");
		
		String inputRefDOIDOx_Value = VariousMethods.getTextFieldInput(inputRefDOIDOx, "stringInput");
		String inputRefDOx_Value = VariousMethods.getTextFieldInput(inputRefDOx, "stringInput");
		
		String inputRefDOIDRed_Value = VariousMethods.getTextFieldInput(inputRefDOIDRed, "stringInput");
		String inputRefDRed_Value = VariousMethods.getTextFieldInput(inputRefDRed, "stringInput");
		
		String inputRefDOIkOx_Value = VariousMethods.getTextFieldInput(inputRefDOIkOx, "stringInput");
		String inputRefkOx_Value = VariousMethods.getTextFieldInput(inputRefkOx, "stringInput");
		
		String inputRefDOIAlphaOx_Value = VariousMethods.getTextFieldInput(inputRefDOIAlphaOx, "stringInput");
		String inputRefAlphaOx_Value = VariousMethods.getTextFieldInput(inputRefAlphaOx, "stringInput");
		
		String inputRefDOIkRed_Value = VariousMethods.getTextFieldInput(inputRefDOIkRed, "stringInput");
		String inputRefkRed_Value = VariousMethods.getTextFieldInput(inputRefkRed, "stringInput");
		
		String inputRefDOIAlphaRed_Value = VariousMethods.getTextFieldInput(inputRefDOIAlphaRed, "stringInput");
		String inputRefAlphaRed_Value = VariousMethods.getTextFieldInput(inputRefAlphaRed, "stringInput");
		
		String inputRefDOIdegRate_Value = VariousMethods.getTextFieldInput(inputRefDOIdegRate, "stringInput");
		String inputRefdegRate_Value = VariousMethods.getTextFieldInput(inputRefdegRate, "stringInput");
		
		String inputRefDOIF_Value = VariousMethods.getTextFieldInput(inputRefDOIF, "stringInput");
		String inputRefF_Value = VariousMethods.getTextFieldInput(inputRefF, "stringInput");
		
		ObjReference objReferenceMaxSolubility = new ObjReference();
		objReferenceMaxSolubility.DOI.set(inputRefDOIMaxSolubility_Value);
		objReferenceMaxSolubility.Reference.set(inputRefMaxSolubility_Value);
		
		ObjReference objReferenceDOx = new ObjReference();
		objReferenceDOx.DOI.set(inputRefDOIDOx_Value);
		objReferenceDOx.Reference.set(inputRefDOx_Value);
		
		ObjReference objReferenceDRed = new ObjReference();
		objReferenceDRed.DOI.set(inputRefDOIDRed_Value);
		objReferenceDRed.Reference.set(inputRefDRed_Value);
		
		ObjReference objReferencekOx = new ObjReference();
		objReferencekOx.DOI.set(inputRefDOIkOx_Value);
		objReferencekOx.Reference.set(inputRefkOx_Value);
		
		ObjReference objReferenceAlphaOx = new ObjReference();
		objReferenceAlphaOx.DOI.set(inputRefDOIAlphaOx_Value);
		objReferenceAlphaOx.Reference.set(inputRefAlphaOx_Value);
		
		ObjReference objReferencekRed = new ObjReference();
		objReferencekRed.DOI.set(inputRefDOIkRed_Value);
		objReferencekRed.Reference.set(inputRefkRed_Value);
		
		ObjReference objReferenceAlphaRed = new ObjReference();
		objReferenceAlphaRed.DOI.set(inputRefDOIAlphaRed_Value);
		objReferenceAlphaRed.Reference.set(inputRefAlphaRed_Value);
		
		ObjReference objReferenceDegRate = new ObjReference();
		objReferenceDegRate.DOI.set(inputRefDOIdegRate_Value);
		objReferenceDegRate.Reference.set(inputRefdegRate_Value);
		
		ObjReference objReferenceF = new ObjReference();
		objReferenceF.DOI.set(inputRefDOIF_Value);
		objReferenceF.Reference.set(inputRefF_Value);
		
		objReferenceMaxSolubility = ReferencesController.updateRef("update", table, "ActiveMaterial",inputActiveMaterial_Value, null, "RefIDmaxSolubility", objReferenceMaxSolubility);
		
		objReferenceMaxSolubility = ReferencesController.updateRef("update", table, "ActiveMaterial",inputActiveMaterial_Value, null, "RefIDDOx", objReferenceDOx);
		
		objReferenceMaxSolubility = ReferencesController.updateRef("update", table, "ActiveMaterial",inputActiveMaterial_Value, null, "RefIDDRed", objReferenceDRed);
		
		objReferenceMaxSolubility = ReferencesController.updateRef("update", table, "ActiveMaterial",inputActiveMaterial_Value, null, "RefIDkOx", objReferencekOx);
		
		objReferenceMaxSolubility = ReferencesController.updateRef("update", table, "ActiveMaterial",inputActiveMaterial_Value, null, "RefIDAlphaOx", objReferenceAlphaOx);
		
		objReferenceMaxSolubility = ReferencesController.updateRef("update", table, "ActiveMaterial",inputActiveMaterial_Value, null, "RefIDkRed", objReferencekRed);
		
		objReferenceMaxSolubility = ReferencesController.updateRef("update", table, "ActiveMaterial",inputActiveMaterial_Value, null, "RefIDAlphaRed", objReferenceAlphaRed);
		
		objReferenceMaxSolubility = ReferencesController.updateRef("update", table, "ActiveMaterial",inputActiveMaterial_Value, null, "RefIDdegRate", objReferenceDegRate);
		
		objReferenceMaxSolubility = ReferencesController.updateRef("update", table, "ActiveMaterial",inputActiveMaterial_Value, null, "RefIDf", objReferenceF);

		//close Add-Pane:
		AddElectrolytesController.this.cancelAddElectrolytes(event);


	}

}
