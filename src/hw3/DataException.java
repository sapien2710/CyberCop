//Abhijeet Purkar
//apurkar
package hw3;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

@SuppressWarnings("serial")

public class DataException extends IllegalArgumentException {

	public DataException(String message) 
	{
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Data Error");
		alert.setContentText(message);
		alert.showAndWait();
	}
	
	

}
