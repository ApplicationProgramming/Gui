package application;

//import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;

import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;

public class Main extends javafx.application.Application {
	static String enteredEmail;


	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/Login.fxml"));
			Scene scene = new Scene(root, 300, 300);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void Login() {
		try {
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/application/Main.fxml"));
			Scene scene = new Scene(root, 300, 300);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {

		}
	}

	public void returnToLogin() {
		try {
			Stage login = new Stage();
			Parent root1 = FXMLLoader.load(getClass().getResource("/application/Login.fxml"));
			Scene returnToLogin = new Scene(root1);
			returnToLogin.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			login.setScene(returnToLogin);
			login.show();
		} catch (Exception f) {

		}
	}

	public void checkLogin(String EnteredEmail, String EnteredPassword) {
		try {
			Connection conn = DBConnector.getConnection();
			PreparedStatement CheckLogin = (PreparedStatement) conn
					.prepareStatement("SELECT Password FROM Login WHERE CustomerEmail ='" + EnteredEmail + "' ");
			ResultSet rs = CheckLogin.executeQuery();
			if (rs.next()) {

				String psw = rs.getString("Password");
				if (psw.equals(EnteredPassword)) {
					loginComplete();

				} 

			}
		} catch (Exception e) {
		}

	}

	public void loginComplete() {
		try {
			Stage login = new Stage();
			Parent root1 = FXMLLoader.load(getClass().getResource("/application/UserInterface.fxml"));
			Scene returnToLogin = new Scene(root1, 305, 239);
			returnToLogin.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			login.setScene(returnToLogin);
			login.show();
		} catch (Exception f) {

		}
	}

	
	public void setEnteredEmail(String enteredEmail) {
		this.enteredEmail = enteredEmail;
	}

	public String getEnteredEmail() {
		return enteredEmail;
	}

}
