package de.dominikemmel.reflowlab.controller.references;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import de.dominikemmel.reflowlab.Database;

public class ReferencesController implements javafx.fxml.Initializable {
	@FXML
	private TableView<ObjReference> tblReference;


	@FXML
	private TableColumn<ObjReference, Integer> ID;
	@FXML
	private TextFlow ID_TextFlow;
	@FXML
	private TableColumn<ObjReference, String> DOI;
	@FXML
	private TextFlow DOI_TextFlow;
	@FXML
	private TableColumn<ObjReference, String> Reference;
	@FXML
	private TextFlow Reference_TextFlow;
	@FXML
	private TableColumn<ObjReference, String> Comment;
	@FXML
	private TextFlow Comment_TextFlow;
	@FXML
	private TableColumn<ObjReference, String> editDate;
	@FXML
	private TextFlow editDate_TextFlow;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ReferencesController.this.addColumnNames();
		ReferencesController.this.reloadDataReferences();
	}



	public void reloadDataReferences() {

		try {
			tblReference.refresh();

			ResultSet res = Database.selectData("reference");

		    //extracting data from ResulSet to ArrayList
	    	ArrayList<ObjReference> dataReference = new ArrayList<>();
	    	while (res.next()) {
	    		ObjReference objReference = new ObjReference();

	    		objReference.ID.set(res.getInt("ID"));
	    		objReference.DOI.set(res.getString("DOI"));
	    		objReference.Reference.set(res.getString("Reference"));
	    		objReference.Comment.set(res.getString("Comment"));

		    		Timestamp timestamp = res.getTimestamp("editDate");
		    		String timestampString = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(timestamp);
		    		objReference.editDate.set(timestampString);

		    		dataReference.add(objReference);
		    	}

	    	ObservableList<ObjReference> dbData = FXCollections.observableArrayList(dataReference);

	    	ID.setCellValueFactory(new PropertyValueFactory<ObjReference, Integer>("ID"));
	    	DOI.setCellValueFactory(new PropertyValueFactory<ObjReference, String>("DOI"));
	    	Reference.setCellValueFactory(new PropertyValueFactory<ObjReference, String>("Reference"));
	    	Comment.setCellValueFactory(new PropertyValueFactory<ObjReference, String>("Comment"));
	    	editDate.setCellValueFactory(new PropertyValueFactory<ObjReference, String>("editDate"));

	    	//sorting data, condition: ID.
	    	SortedList<ObjReference> dbDataSorted = new SortedList<>(dbData);
	    	// this ensures the dbDataSorted is sorted according to the sort columns in the table:
	    	dbDataSorted.comparatorProperty().bind(tblReference.comparatorProperty());
	        //Filling up TableView with data.
	    	tblReference.setItems(dbDataSorted);
	    	// programmatically set a sort column:
	    	tblReference.getSortOrder().addAll(ID);


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

    	//DOI:
    	Text DOI1 = new Text("DOI");
    	DOI1.setStyle("-fx-font-weight: bold");

    	DOI_TextFlow.getChildren().addAll(DOI1);

    	//Reference:
    	Text Reference1 = new Text("Reference");
    	Reference1.setStyle("-fx-font-weight: bold");

    	Reference_TextFlow.getChildren().addAll(Reference1);
    	
    	//Reference:
    	Text Comment1 = new Text("Comment");
    	Comment1.setStyle("-fx-font-weight: bold");
    	
    	Comment_TextFlow.getChildren().addAll(Comment1);

    	//editDate:
		Text editDate1 = new Text("Date");
		editDate1.setStyle("-fx-font-weight: bold");
		Text editDate2 = new Text("edit");
		editDate2.setStyle("-fx-font-weight: bold");
		editDate2.setTranslateY(editDate1.getFont().getSize() * 0.3);
		editDate2.setFont(Font.font(editDate1.getFont().getStyle(),editDate1.getFont().getSize()*0.75));

		editDate_TextFlow.getChildren().addAll(editDate1,editDate2);
	}


	@FXML
	private void addReferences (ActionEvent event) {
		try {
			Parent rootAddReferences;
			rootAddReferences = FXMLLoader.load(getClass().getResource("/de/dominikemmel/reflowlab/controller/references/fxml/addReferences.fxml"));
			Stage stage = new Stage();
			stage.setScene(new Scene(rootAddReferences));
			stage.show();
			
			stage.setTitle("ReFlowLab - Add reference");
			stage.getIcons().add(new Image(getClass().getResourceAsStream("/de/dominikemmel/reflowlab/img/logo_simple/1x/logo_simple1x.png")));
			
			stage.getScene().getStylesheets().add(getClass().getResource("/de/dominikemmel/reflowlab/style/reflowlabStyle1.css").toExternalForm());



			stage.setOnCloseRequest(event2 -> {
				ReferencesController.this.reloadDataReferences();
			});

			stage.setOnHidden(event3 -> {
				ReferencesController.this.reloadDataReferences();
			});

		} catch (IOException e) {
			e.printStackTrace();
		}
	}




	@FXML
	private void editReferences (ActionEvent event) {
		ObservableList<ObjReference> selection = tblReference.getSelectionModel().getSelectedItems();

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/de/dominikemmel/reflowlab/controller/references/fxml/editReferences.fxml"));
			Parent rootEditReferences = loader.load();
			loader.setControllerFactory((Class<?> controllerType) -> {
				if (controllerType == EditReferencesController.class) {
					EditReferencesController controller = new EditReferencesController();
					controller.setSelectionData(selection);
					return controller;
				} else {
					try {
						return controllerType.newInstance();
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				}
			});

			EditReferencesController controller = loader.<EditReferencesController>getController();
			controller.setSelectionData(selection);

			Stage stage = new Stage();
			stage.setScene(new Scene(rootEditReferences));
			stage.show();

			stage.setTitle("ReFlowLab - Edit reference");
			stage.getIcons().add(new Image(getClass().getResourceAsStream("/de/dominikemmel/reflowlab/img/logo_simple/1x/logo_simple1x.png")));
			
			stage.getScene().getStylesheets().add(getClass().getResource("/de/dominikemmel/reflowlab/style/reflowlabStyle1.css").toExternalForm());


			stage.setOnCloseRequest(event2 -> {
				ReferencesController.this.reloadDataReferences();
			});

			stage.setOnHidden(event3 -> {
				ReferencesController.this.reloadDataReferences();
			});

		} catch (IOException e) {
			e.printStackTrace();
		}



	}
	
	
	
	
	public static String getRefCode(String table, String searchColoumn, String searchValue) {
		
		String searchID = null;
		
		try {
			Connection con = Database.getConnection(table);
			Statement state = con.createStatement();
			ResultSet res = state.executeQuery("SELECT ID FROM " + table
					+ " WHERE "+searchColoumn+ " = '"+ searchValue+"'");
			
			while (res.next()) {
				searchID = String.valueOf(res.getInt("ID"));
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String refCode = "";
		
		if (searchID != null) {
			refCode = getTableCode(table);
			refCode += searchID;
		} 
		
		return refCode;
	}
	
	private static String getTableCode(String table) {
		String tableCode = null;
		
		switch (table){
		case "activeMaterial":
			tableCode = "A";
			break;
		case "solventInorganic":
			tableCode = "B";
			break;
		case "solventOrganic":
			tableCode = "C";
			break;
		case "electrolyte":
			tableCode = "D";
			break;
		case "costStack":
			tableCode = "E";
			break;
		case "costSalt":
			tableCode = "F";
			break;
		case "costSolvent":
			tableCode = "G";
			break;
		case "separatorParameter":
			tableCode = "H";
			break;
		case "costPeripherals":
			tableCode = "I";
			break;
		}
		return tableCode;
		
	}
	
	private static String enterRefCode(String refCode, String DOI, String reference, String comment) {
		try {
			Connection con = Database.getConnection("reference");
			Statement state = con.createStatement();
			state.executeUpdate("INSERT INTO reference(ID, DOI, Bound, Reference, Comment, editDate)"
					+" VALUES(DEFAULT, '"+DOI+"', '"
					+refCode+"', '"+reference+"', '"
					+comment+"'"
					+", CURRENT_TIMESTAMP)");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String refID = null;
		return refID;
		
	}
	
	
	
	public static ObjReference checkRef(String DOI) throws ClassNotFoundException, SQLException {
		
		ObjReference objReference = new ObjReference();
		
		boolean refExist = false;
		ResultSet res = null;
		try {
			Connection con = Database.getConnection("reference");
			Statement state = con.createStatement();
			res = state.executeQuery("SELECT * FROM reference WHERE DOI = '"+ DOI +"'");
		} catch (SQLException e) {

			e.printStackTrace();
		}

		if (res.next()) {
			refExist = true; 
			
			objReference.ID.set(res.getInt("ID"));
			objReference.DOI.set(res.getString("DOI"));
			objReference.Reference.set(res.getString("Reference"));
			objReference.Bound.set(res.getString("Bound"));
			objReference.Comment.set(res.getString("Comment"));
			
			objReference.refExist.set(refExist);
			
			System.out.println("DOI found! - DOI: "+ objReference.DOI.get()+" | Ref: '" + objReference.Reference.get()+"'");
		}
		
		return objReference;
	}

	
	public static void updateInputTable(String table, String searchColoumn, String searchValue, String parameter, ObjReference objReferenceInput) {
		
		try {
			Connection con = Database.getConnection(table);
			Statement state = con.createStatement();
			state.executeUpdate("UPDATE "+table+
					" SET " +parameter+ " = "+objReferenceInput.ID.getValue()+
					", editDate = CURRENT_TIMESTAMP"
							+ " WHERE " +searchColoumn+ " = '"+searchValue+"'");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	

	public static ObjReference updateRef(String operation, String table, String searchColoumn, String searchValue, String oldSearchValue, String parameter, ObjReference objReferenceInput) throws ClassNotFoundException, SQLException {
		//check if ref already exists
		ObjReference objReference = checkRef(objReferenceInput.DOI.getValue());
		
		if (operation == "update") {
			
			if (objReference.refExist.get()) {
				//create refCode
				String refCode = getRefCode(table, searchColoumn, searchValue);
				
				String comment = null;
				String newRefCode = null;
				
				//Update ref
				String reference = null;
				
				reference = objReferenceInput.Reference.get();
				
				String bound = objReference.Bound.get();
				Pattern pattern = Pattern.compile(refCode+" ");
				Matcher matcher = pattern.matcher(bound);
				boolean boundMatch = matcher.find();
				
				if (boundMatch && searchValue.equals(oldSearchValue)) {
					// no change
					comment = objReference.Comment.get();
					objReference.Comment.set(comment);
					newRefCode = objReference.Bound.get();
					objReference.Bound.set(newRefCode);
					System.out.println("Ref already exists - connection to "+ searchValue + " updated!");
					
				} else if (boundMatch && !searchValue.equals(oldSearchValue) && oldSearchValue != null) {
					// update "search value" in comment
					String commentOld = objReference.Comment.get();

					if (commentOld != null) {
						Pattern pattern3 = Pattern.compile("tab-"+table+": "+oldSearchValue+"  ");
						Matcher matcher3 = pattern3.matcher(commentOld);
						
						String commentUpdate = matcher3.replaceAll("");
						
						commentUpdate = commentUpdate + "tab-"+table+": "+searchValue+"  ";
						objReference.Comment.set(commentUpdate);
					}
					
				} else if (boundMatch && oldSearchValue == null) {
					// if connection to "search value" already established --> no new comment but new bound/refCode
					comment = objReference.Comment.get();
					objReference.Comment.set(comment);
					newRefCode = objReference.Bound.get()+refCode+" ";
					objReference.Bound.set(newRefCode);
					System.out.println("Ref already exists - connection to "+ searchValue + " already established!");
					
				} else if (!boundMatch){
					// add new comment to existing ref
					comment = objReference.Comment.get()+"tab-"+table+": "+searchValue+"  ";
					objReference.Comment.set(comment);
					newRefCode = objReference.Bound.get()+refCode+" ";
					objReference.Bound.set(newRefCode);
					System.out.println("Ref already exists - connection to "+ searchValue + " established!");
				}
				
				try {
					Connection con = Database.getConnection("reference");
					Statement state = con.createStatement();
					state.executeUpdate("UPDATE reference "
							+ "SET ID = "+objReference.ID.get()+
							", DOI = '"+objReference.DOI.get()+"', Bound = '"+objReference.Bound.get()+
							"', Reference = '"+reference+"', Comment = '"+objReference.Comment.get()+
							"', editDate = CURRENT_TIMESTAMP"
									+ " WHERE ID = "+objReference.ID.get()+"");

				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
				
				updateInputTable(table, searchColoumn, searchValue, parameter, objReference);
				
				return objReference;
				
			} else {
				//create refCode
				String refCode = getRefCode(table, searchColoumn,searchValue)+" ";

				//Enter ref in tab-ref
				enterRefCode(refCode, objReferenceInput.DOI.getValue(), objReferenceInput.Reference.getValue(), "tab-"+table+": "+searchValue+"  ");
				
				ObjReference objReferenceNew = checkRef(objReferenceInput.DOI.getValue());
				updateInputTable(table, searchColoumn, searchValue, parameter, objReferenceNew);
				System.out.println("Ref "+ objReferenceInput.DOI.getValue() + " created - link to "+ searchValue + " established!");
				return objReferenceNew;
			}
				
				
		} else if (operation == "delete") {
			
			if (objReference.refExist.get()) {
				//create refCode
				String refCode = getRefCode(table, searchColoumn, searchValue);
				
				String comment = objReference.Comment.get();
				String bound = objReference.Bound.get();
				
				Pattern pattern = Pattern.compile(refCode+" ");
				Matcher matcher1 = pattern.matcher(bound);
				String boundUpdate = matcher1.replaceFirst("");

				objReference.Bound.set(boundUpdate);
				
				Pattern pattern2 = Pattern.compile(refCode+" ");
				Matcher matcher2 = pattern2.matcher(boundUpdate);
				boolean boundMatch = matcher2.find();
				
				String commentUpdate = comment;
				
				if (!boundMatch) {
					Pattern pattern3 = Pattern.compile("tab-"+table+": "+searchValue+"  ");
					Matcher matcher3 = pattern3.matcher(comment);
					commentUpdate = matcher3.replaceAll("");
					objReference.Comment.set(commentUpdate);
				}
				
				if (commentUpdate == "") {
					try {
						Connection con = Database.getConnection("reference");
						Statement state = con.createStatement();
						state.executeUpdate("DELETE FROM reference WHERE ID = "+objReference.ID.getValue()+"");

					} catch (ClassNotFoundException | SQLException e) {
						e.printStackTrace();
					}
					
					System.out.println("\u001b[31m"+"Ref " + objReferenceInput.DOI.getValue() +" deleted!");
					return null;
				}
				
				try {
					Connection con = Database.getConnection("reference");
					Statement state = con.createStatement();
					state.executeUpdate("UPDATE reference "
							+ "SET ID = "+objReference.ID.get()+
							", DOI = '"+objReference.DOI.get()+"', Bound = '"+boundUpdate+
							"', Reference = '"+objReferenceInput.Reference.get()+"', Comment = '"+commentUpdate+
							"', editDate = CURRENT_TIMESTAMP"
									+ " WHERE ID = "+objReference.ID.get()+"");

				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
				
				System.out.println("Ref " + objReferenceInput.DOI.getValue() +" updated - one link to "+ searchValue + " removed!");
				return objReference;
			}
				
		} 
		return null;
	}
	
	

	
	
	public static ObjReference checkRefID(int id) throws ClassNotFoundException, SQLException {
		
		ObjReference objReference = new ObjReference();
		
		boolean refExist = false;
		ResultSet res = null;
		try {
			Connection con = Database.getConnection("reference");
			Statement state = con.createStatement();
			res = state.executeQuery("SELECT * FROM reference WHERE ID = "+ id +"");
		} catch (SQLException e) {

			e.printStackTrace();
		}

		if (res.next()) {
			refExist = true; 
			
			objReference.ID.set(res.getInt("ID"));
			objReference.DOI.set(res.getString("DOI"));
			objReference.Reference.set(res.getString("Reference"));
			objReference.Bound.set(res.getString("Bound"));
			objReference.Comment.set(res.getString("Comment"));
			
			objReference.refExist.set(refExist);
		}
		
		return objReference;
	}
	
}
