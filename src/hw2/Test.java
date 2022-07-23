package hw2;





import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Test extends Application {
	
	Stage stage1 = new Stage();
	BorderPane root = new BorderPane();
	
	
	

	@Override
	public void start(Stage arg0) throws Exception {
		this.stage1 = arg0;
		setupScreen();
		Scene scene = new Scene(root, 500, 500);
		stage1.setTitle("Testing..");
		stage1.setScene(scene);
		stage1.show();
		
	}
	
	public static void main(String [] args)
	{
		launch(args);
		
	}
	
	private void setupScreen()
	{
		Menu change = new Menu("Change");
		
		MenuItem add = new MenuItem("Add");
		MenuItem modify = new MenuItem("Modify");
		MenuItem delete = new MenuItem("Delete");
		add.setOnAction(new addHandler());
		modify.setOnAction(new modifyHandler());
		delete.setOnAction(new deleteHandler());
		
		MenuBar menu = new MenuBar();
		
		change.getItems().addAll(add, modify, delete);
		menu.getMenus().addAll(change);
		
		root.setTop(menu);
		
	}
	
	class deleteHandler implements EventHandler<ActionEvent>
	{
		DeleteCaseView sample = new DeleteCaseView("Delete");
		
	@Override
	public void handle(ActionEvent arg0) {
		
		    
			Stage stage4 = sample.buildView();
			stage4.show();
			
	}
	}
	class addHandler implements EventHandler<ActionEvent>
	{
		CaseView sample	= new AddCaseView("Add");
		
	@Override
	public void handle(ActionEvent arg0) {
			
			Stage stage2 = sample.buildView();
			stage2.show();
		
	}
	}
	class modifyHandler implements EventHandler<ActionEvent>
	{
		ModifyCaseView sample	= new ModifyCaseView("Modify");
	
	@Override
	public void handle(ActionEvent arg0) {
		
			Stage stage3 = sample.buildView();
			stage3.show();
	}
	}
}
