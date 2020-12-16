package application;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class UserInterfaceController extends Main{

	@FXML
	private Button searchFlightBtn;
	
	
	public void availableFlights(ActionEvent returnButton) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/application/BookFlights.FXML"));
			Parent root1 = (Parent) fxmlLoader.load();
			Scene Login = new Scene(root1);
			Stage window = (Stage)((Node)returnButton.getSource()).getScene().getWindow();
			window.setScene(Login);
			window.show();
		}
		catch (Exception e) {
			
		}
	}
	
	public void bookedFlights(ActionEvent bookedButton) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/application/BookedFlights.FXML"));
			Parent root1 = (Parent) fxmlLoader.load();
			Scene Login = new Scene(root1);
			Stage window = (Stage)((Node)bookedButton.getSource()).getScene().getWindow();
			window.setScene(Login);
			window.show();
		}
		catch (Exception e) {
			
		}
	}
		
	}
	

