package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AdminInterfaceController extends Main {

	@FXML
	private Button searchFlightBtn;

	public void availableFlights(ActionEvent returnButton) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/application/AdminBookFlights.FXML"));
			Parent root1 = (Parent) fxmlLoader.load();
			Scene Login = new Scene(root1);
			Stage window = (Stage) ((Node) returnButton.getSource()).getScene().getWindow();
			window.setScene(Login);
			window.show();
		} catch (Exception e) {

		}
	}

	public void bookedFlights(ActionEvent bookedButton) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/application/BookedFlights.FXML"));
			Parent root1 = (Parent) fxmlLoader.load();
			Scene Login = new Scene(root1);
			Stage window = (Stage) ((Node) bookedButton.getSource()).getScene().getWindow();
			window.setScene(Login);
			window.show();
		} catch (Exception e) {

		}
	}
	
	public void logout(ActionEvent logout) {
		try {
			Stage stage = (Stage) ((Node) logout.getSource()).getScene().getWindow();
			stage.close();
			Stage login = new Stage();
			Parent root1 = FXMLLoader.load(getClass().getResource("/application/Login.fxml"));
			Scene returnToLogin = new Scene(root1);
			returnToLogin.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			login.setScene(returnToLogin);
			login.show();
		} catch (Exception f) {

		}
	}
	}

