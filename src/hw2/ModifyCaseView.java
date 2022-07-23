//Abhijeet Purkar
//apurkar

package hw2;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class ModifyCaseView extends CaseView {

	ModifyCaseView(String header) {
		super(header);
		// TODO Auto-generated constructor stub
	}

	@Override
	Stage buildView() {
		
		updateButton.setText("Modify case");
		Scene sam = new Scene(updateCaseGridPane);
		stage.setScene(sam);
		return stage;
		
	}
}
