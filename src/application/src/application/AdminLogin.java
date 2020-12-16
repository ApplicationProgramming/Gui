package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AdminLogin extends Main {
	

	
	private String adminCheck;
	
	
	@FXML private TextField adminUsername;
	@FXML private TextField adminPassword;
	
	public void checkLoginAdmin(ActionEvent event) {
	
		
		try {
			
			setEnteredEmail(adminUsername.getText());
			String enteredUsername = getEnteredEmail();
			String EnteredPassword = adminPassword.getText();
			Connection conn = DBConnector.getConnection();
			PreparedStatement CheckLogin = (PreparedStatement) conn.prepareStatement("SELECT Password FROM Login WHERE CustomerEmail ='" + enteredUsername + "'");
			PreparedStatement CheckAdmin = (PreparedStatement) conn.prepareStatement("SELECT Admin From Login Where CustomerEmail ='" + enteredUsername + "'");
			ResultSet rs = CheckLogin.executeQuery();
			ResultSet admin = CheckAdmin.executeQuery();

			
			
			if (rs.next()) {
				String psw = rs.getString("Password");
			
			if (admin.next()) {
				String adminCheck = admin.getString("Admin");
				

			
			
			if(adminCheck.equals("Yes")) {
				
				if (psw.equals(EnteredPassword)) {
					
					Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
					stage.close();
					Stage login = new Stage();
					Parent root1 = FXMLLoader.load(getClass().getResource("/application/AdminInterface.fxml"));
					Scene returnToLogin = new Scene(root1);
					returnToLogin.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					login.setScene(returnToLogin);
					login.show();
					

				}
				}
				}

			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}
}
