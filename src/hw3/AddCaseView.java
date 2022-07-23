//Abhijeet Purkar
//apurkar

package hw3;


import javafx.scene.Scene;
import javafx.stage.Stage;

public class AddCaseView extends CaseView {
	
	Case added = null;
	

	AddCaseView(String header) {
		super(header);
		
	}
	
	@Override
	Stage buildView() {
		updateButton.setText("Add case");
		Scene sam = new Scene(updateCaseGridPane);
		stage.setScene(sam);
		return stage;
	}
}
