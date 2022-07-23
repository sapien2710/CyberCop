//Abhijeet Purkar
//apurkar

package hw3;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import java.net.URL;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
 
public class CyberCop extends Application{

	public static final String DEFAULT_PATH = "C:/Users/admin/eclipse-workspace/HW2/data"; //folder name where data files are stored
	public static final String DEFAULT_HTML = "/CyberCop.html"; //local HTML
	public static final String APP_TITLE = "Cyber Cop"; //displayed on top of app

	CCView ccView = new CCView();
	CCModel ccModel = new CCModel();
	CCChart ccchart = new CCChart();

	CaseView sample = null; //UI for Add/Modify/Delete menu option

	GridPane cyberCopRoot;
	Stage stage;

	static Case currentCase; //points to the case selected in TableView.

	public static void main(String[] args) {
		launch(args);
	}

	/** start the application and show the opening scene */
	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		primaryStage.setTitle("Cyber Cop");
		cyberCopRoot = ccView.setupScreen();  
		setupBindings();
		Scene scene = new Scene(cyberCopRoot, ccView.ccWidth, ccView.ccHeight);
		primaryStage.setScene(scene);
		primaryStage.setMaximized(true);
		ccView.webEngine.load(getClass().getResource(DEFAULT_HTML).toExternalForm());
		primaryStage.show();
	}

	/** setupBindings() binds all GUI components to their handlers.
	 * It also binds disableProperty of menu items and text-fields 
	 * with ccView.isFileOpen so that they are enabled as needed
	 */
	void setupBindings() {
		
		
		//Binding menuItems with disable properties
		ccView.closeFileMenuItem.disableProperty().bind(ccView.isFileOpen.not());
		ccView.modifyCaseMenuItem.disableProperty().bind(ccView.isFileOpen.not());
		ccView.addCaseMenuItem.disableProperty().bind(ccView.isFileOpen.not());
		ccView.deleteCaseMenuItem.disableProperty().bind(ccView.isFileOpen.not());
		
		
		
		
		//Getting current selected item from the tableView and adding URLs
		TableViewSelectionModel<Case> selectionModel = ccView.caseTableView.getSelectionModel();
		selectionModel.setSelectionMode(SelectionMode.SINGLE);
		ObservableList<Case> selectedItems = selectionModel.getSelectedItems();
		
		selectedItems.addListener(new ListChangeListener<Case>() {
			  @Override
			  public void onChanged(Change<? extends Case> change) {
			    Iterator iter = change.getList().iterator();
			    while(iter.hasNext())
			    {
			    	currentCase = (Case) iter.next();
			    	ccView.titleTextField.setText(currentCase.caseTitle.get());
			    	ccView.caseTypeTextField.setText(currentCase.caseType.get());
			    	ccView.caseNumberTextField.setText(currentCase.caseNumber.get());
			    	ccView.caseNotesTextArea.setText(currentCase.caseNotes.get());
			    	ccView.yearComboBox.setValue(currentCase.caseDate.get().substring(0, 4));
			    	
			    	if (currentCase.getCaseLink() == null || currentCase.getCaseLink().isBlank()) {  //if no link in data
						URL url = getClass().getClassLoader().getResource(DEFAULT_HTML);  //default html
						if (url != null) ccView.webEngine.load(url.toExternalForm());
					} else if (currentCase.getCaseLink().toLowerCase().startsWith("http")){  //if external link
						ccView.webEngine.load(currentCase.getCaseLink());
				} else {
						URL url = getClass().getClassLoader().getResource(currentCase.getCaseLink().trim());  //local link
						if (url != null) ccView.webEngine.load(url.toExternalForm());
					}
			    }
			      
			  }
			});
	
		//Adding event Handlers to GUI components
		ccView.addCaseMenuItem.setOnAction(new updateCaseHandler());
		ccView.modifyCaseMenuItem.setOnAction(new updateCaseHandler());
		ccView.deleteCaseMenuItem.setOnAction(new updateCaseHandler());
		ccView.exitMenuItem.setOnAction(actionEvent->Platform.exit());
		ccView.searchButton.setOnAction(new searchButtonhandler());
		ccView.openFileMenuItem.setOnAction(new openFileMenuItemHandler());
		ccView.clearButton.setOnAction(new clearButtonHandler());
		ccView.saveFileMenuItem.setOnAction(new saveFileMenuItemHandler());
		ccView.caseCountChartMenuItem.setOnAction(new caseCountChartMenuItem());
		ccView.closeFileMenuItem.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				ccModel.caseList.clear();
				
			}});
		
	}
	
	//EventHandler for openFileMenuItem
	public class openFileMenuItemHandler implements EventHandler<ActionEvent>
	{

		@Override
		public void handle(ActionEvent arg0) {
			
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Select File");
			fileChooser.setInitialDirectory(new File(DEFAULT_PATH));
			fileChooser.getExtensionFilters().addAll(
				new ExtensionFilter("TSV files", "*.tsv"),
				new ExtensionFilter("CSV Files", "*.csv"),
				new ExtensionFilter("All Files", "*.*")
					);
			File file = null;
			if((file = fileChooser.showOpenDialog(stage)) != null)
			{
				ccView.isFileOpen.set(true);
				String fileName = file.getName();
				ccModel.readCases(DEFAULT_PATH + "/" +fileName);
				ccModel.buildYearMapAndList();
				ccView.caseTableView.setItems(ccModel.caseList);
				StringProperty tableSize = new SimpleStringProperty();
				tableSize.set(Integer.toString(ccModel.caseList.size()));
				ccView.messageLabel.textProperty().bind(tableSize);
				ccView.yearComboBox.setItems(ccModel.yearList);	
			}
			
		}
		
	}
	
	
	//EventHandler for saveButton
	public class saveFileMenuItemHandler implements EventHandler<ActionEvent>
	{

		@Override
		public void handle(ActionEvent arg0) {
			
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Save File");
			fileChooser.setInitialDirectory(new File(DEFAULT_PATH));	
			fileChooser.getExtensionFilters().addAll( new ExtensionFilter("TSV files", "*.tsv"));
			File file = fileChooser.showSaveDialog(stage);
			 
            if (file != null) {
            	String filename = file.getName();
                ccModel.writeCases(filename);
               
                if(ccModel.writeCases(filename))
                {
                	ccView.messageLabel.textProperty().unbind();
                	ccView.messageLabel.setText(filename + " saved.");
                }
            }
		}
		
	}
	
	//EventHandler for caseCountChart filemenuitem
	public class caseCountChartMenuItem implements EventHandler<ActionEvent>
	{

		@Override
		public void handle(ActionEvent arg0)
		{
			
			ccView.showChartView(ccModel.yearMap);
			
			

		}
		
	}
	
	
	//EventHandler for searchButtonItem
	public class searchButtonhandler implements EventHandler<ActionEvent>
	{

		@Override
		public void handle(ActionEvent arg0) {
			
			String title = ccView.titleTextField.getText();
			String caseType = ccView.caseTypeTextField.getText();
			String caseNumber = ccView.caseNumberTextField.getText();
			String caseYear = ccView.yearComboBox.getValue();
			
			List<Case> result = ccModel.searchCases(title, caseType, caseYear, caseNumber);
			
			ObservableList<Case> resultList =  FXCollections.observableArrayList(result);  
			ccView.caseTableView.setItems(resultList);
				
		}
		
	}
	
	
	//EventHandler for clearButtonItem
	public class clearButtonHandler implements EventHandler<ActionEvent>
	{

		@Override
		public void handle(ActionEvent arg0) {
			
			ccView.titleTextField.textProperty().unbind();
			ccView.titleTextField.clear();
			ccView.caseTypeTextField.textProperty().unbind();
			ccView.caseTypeTextField.clear();
			ccView.caseNumberTextField.textProperty().unbind();
			ccView.caseNumberTextField.clear();
			ccView.caseNotesTextArea.clear();
			ccView.yearComboBox.getItems().clear();
		}
		
	}
	
	//EventHandler for changing case
	public class updateCaseHandler implements EventHandler<ActionEvent>
	{
		@Override
		public void handle(ActionEvent arg0) {
			MenuItem selection = (MenuItem) arg0.getSource();
			String action = selection.getText();
			switch(action)
			{
			case "Add case":                             							//When add case item is clicked
				sample	= new AddCaseView("Add Case");
				Stage stage1 = sample.buildView();
				stage1.show();
				
				//Event Handler for Add Case's update button
				sample.updateButton.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						String title = sample.titleTextField.getText();
						String number = sample.caseNumberTextField.getText();
						String type = sample.caseTypeTextField.getText();
						String notes = sample.caseNotesTextArea.getText();
						String date = sample.caseDatePicker.getValue().toString();
						String category = sample.categoryTextField.getText();
						String link = sample.caseLinkTextField.getText();
						
						
						Case added = new Case(date, title, type, number, link, category, notes);
						boolean duplicate = ccModel.caseMap.containsKey(number);
						
						try
						{
							if(added.getCaseDate().length() == 0 || added.getCaseTitle().length() == 0 || added.getCaseType().length() == 0 || added.getCaseNumber().length() == 0)
						
						{
							String message = "Case must have date, title, type and number";
							throw new DataException(message);
						}
							if(duplicate)
							{
								String message = "Duplicate case number";
								throw new DataException(message);
							}
							
						ccModel.caseList.add(added);
						StringProperty tableSize = new SimpleStringProperty();
						tableSize.set(Integer.toString(ccModel.caseList.size()));
						ccView.messageLabel.textProperty().bind(tableSize);
					}
						catch(Exception e)
						{
							
						}
						
						
					}});
				
				//Event Handler for Add Case's close button
				sample.closeButton.setOnAction(actionEvent->stage1.close());
				
				//Event Handler for Add Case's clear button
				sample.clearButton.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
					
						sample.titleTextField.clear();
						sample.caseNumberTextField.clear();
						sample.caseTypeTextField.clear();
						sample.caseNotesTextArea.clear();
						sample.categoryTextField.clear();
						sample.caseLinkTextField.clear();
						
					}});
				break;														
			
			
			case "Modify case":														//When Modify case item is clicked
				sample	= new ModifyCaseView("Modify Case");
				Stage stage2 = sample.buildView();
				stage2.show();
				
				//Setting text fields to selected row
				sample.titleTextField.setText(currentCase.caseTitle.get());
				sample.caseNumberTextField.setText(currentCase.caseNumber.get());
				sample.caseTypeTextField.setText(currentCase.caseType.get());
				sample.caseNotesTextArea.setText(currentCase.caseNotes.get());
				sample.categoryTextField.setText(currentCase.caseCategory.get());
				sample.caseLinkTextField.setText(currentCase.caseLink.get());
				
				//Event Handler for Modify Case's update button
				sample.updateButton.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
					
						String title = sample.titleTextField.getText();
						String number = sample.caseNumberTextField.getText();
						String type = sample.caseTypeTextField.getText();
						String notes = sample.caseNotesTextArea.getText();
						String date = sample.caseDatePicker.getValue().toString();
						String category = sample.categoryTextField.getText();
						String link = sample.caseLinkTextField.getText();
						
						boolean duplicate = ccModel.caseMap.containsKey(number);
						
						try
						{
							if(title.length() == 0 || type.length() == 0 || number.length() == 0)
						
						{
							String message = "Case must have date, title, type and number";
							throw new DataException(message);
						}
							if(duplicate)
							{
								String message = "Duplicate case number";
								throw new DataException(message);
							}
							
							currentCase.setCaseTitle(title);
							currentCase.setCaseType(type);
							currentCase.setCaseNumber(number);
							currentCase.setCaseCategory(category);
							currentCase.setCaseNotes(notes);
							currentCase.setCaseLink(link);
							currentCase.setCaseDate(date);
					}
						catch(Exception e)
						{
						}																																			
					}});
				
				//Event Handler for Modify Case's close button
				sample.closeButton.setOnAction(actionEvent->stage2.close());
				
				//Event Handler for Modify Case's clear button
				sample.clearButton.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
					
						sample.titleTextField.clear();
						sample.caseNumberTextField.clear();
						sample.caseTypeTextField.clear();
						sample.caseNotesTextArea.clear();
						sample.categoryTextField.clear();
						sample.caseLinkTextField.clear();
						
					}});
				break;
				
				
			case "Delete case": 													//When Delete case item is clicked
				sample	= new DeleteCaseView("Delete Case");
				Stage stage3 = sample.buildView();
				stage3.show();
				
				//Setting text fields to selected row
				if(currentCase != null)
				{
				sample.titleTextField.setText(currentCase.caseTitle.get());
				sample.caseNumberTextField.setText(currentCase.caseNumber.get());
				sample.caseTypeTextField.setText(currentCase.caseType.get());
				sample.caseNotesTextArea.setText(currentCase.caseNotes.get());
				sample.categoryTextField.setText(currentCase.caseCategory.get());
				sample.caseLinkTextField.setText(currentCase.caseLink.get());
				}
				
				//Event Handler for Delete Case's update button
				sample.updateButton.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
					
						int retval = ccModel.caseList.indexOf(currentCase);
						ccModel.caseList.remove(retval);
						StringProperty tableSize = new SimpleStringProperty();
						tableSize.set(Integer.toString(ccModel.caseList.size()));
						ccView.messageLabel.textProperty().bind(tableSize);
						
					}});
				
				//Event Handler for Delete Case's close button
				sample.closeButton.setOnAction(actionEvent->stage3.close());
				
				//Event Handler for Delete Case's clear button
				sample.clearButton.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
					
						sample.titleTextField.clear();
						sample.caseNumberTextField.clear();
						sample.caseTypeTextField.clear();
						sample.caseNotesTextArea.clear();
						sample.categoryTextField.clear();
						sample.caseLinkTextField.clear();
						
					}});
				break;
			}			
		}		
	}
	
	
}

