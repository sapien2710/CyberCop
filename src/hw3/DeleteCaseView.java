//Abhijeet Purkar
//apurkar

package hw3;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class DeleteCaseView extends CaseView {

	DeleteCaseView(String header) {
		super(header);
		// TODO Auto-generated constructor stub
	}

	@Override
	Stage buildView() {
		updateButton.setText("Delete case");
		Scene sam = new Scene(updateCaseGridPane);
		stage.setScene(sam);
		return stage;
	}

}
