package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.sql.*;

public class ModelController extends Main {

	@FXML
	private Label lblLogin;

	@FXML
	private Label loginLbl;

	@FXML
	private TextField UserEmail;

	@FXML
	private TextField UserPassword;

	@FXML
	private TextField RegisterEmail;

	@FXML
	private TextField RegisterPassword;

	@FXML
	private TextField RegisterFirst;

	@FXML
	private TextField RegisterLast;

	@FXML
	private TextField RegisterSSN;

	@FXML
	private TextField RegisterAddress;

	@FXML
	private TextField RegisterCity;

	@FXML
	private TextField RegisterState;

	@FXML
	private TextField RegisterZip;

	@FXML
	private Button LoginRegister;

	@FXML
	private Label registerStatus;

	@FXML
	private TextField RegisterSecurityQ;

	@FXML
	private TextField RegisterSecurityA;

	@FXML
	private Button registerButton;

	@FXML
	private Button returnButton;

	@FXML
	private StackPane parentContainer;

	@FXML
	private Button forgotPassword;
	

	

	public void Login(ActionEvent event) {

		setEnteredEmail(UserEmail.getText());
		String enteredEmail = getEnteredEmail();
		String EnteredPassword = UserPassword.getText();

		try {
			Connection conn = DBConnector.getConnection();
			PreparedStatement CheckLogin = (PreparedStatement) conn
					.prepareStatement("SELECT Password FROM Login WHERE CustomerEmail ='" + enteredEmail + "' ");
			ResultSet rs = CheckLogin.executeQuery();
			if (rs.next()) {

				String psw = rs.getString("Password");
				if (psw.equals(EnteredPassword)) {
					loginComplete();

				} 

			}
		} catch (Exception e) {
		}

		
		loginLbl.setText("Login Failed");
	}

	public void loginRegisterButton(ActionEvent event) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/application/Register.FXML"));
			Parent root1 = (Parent) fxmlLoader.load();
			Scene Register = new Scene(root1, 515, 600);
			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			window.setScene(Register);
			window.show();

		} catch (Exception e) {

		}
	}


	public void returnButton(ActionEvent returnButton) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/application/Login.FXML"));
			Parent root1 = (Parent) fxmlLoader.load();
			Scene Login = new Scene(root1);
			Stage window = (Stage) ((Node) returnButton.getSource()).getScene().getWindow();
			window.setScene(Login);
			window.show();
		} catch (Exception e) {

		}
	}

	public void RegisterButton(ActionEvent event) {

		try {

			if (RegisterPassword.getText().length() >= 8) {

				Connection conn = DBConnector.getConnection();
				PreparedStatement pst = (PreparedStatement) conn.prepareStatement(
						"INSERT INTO Login(CustomerEmail, Password, SecurityQuestion, SecurityQuestionAnswer) VALUES (?,?,?,?)");
				PreparedStatement ps = (PreparedStatement) conn.prepareStatement(
						"insert into Customer(CustomerSSN, CustomerFirst, CustomerLast, CustomerStreet, CustomerCity, CustomerZip,CustomerState, CustomerEmail) values(?,?,?,?,?,?,?,?)");

				pst.setString(1, RegisterEmail.getText());
				pst.setString(2, RegisterPassword.getText());
				pst.setString(3, RegisterSecurityQ.getText());
				pst.setString(4, RegisterSecurityA.getText());
				pst.executeUpdate();

				ps.setString(1, RegisterSSN.getText());
				ps.setString(2, RegisterFirst.getText());
				ps.setString(3, RegisterLast.getText());
				ps.setString(4, RegisterAddress.getText());
				ps.setString(5, RegisterCity.getText());
				ps.setString(6, RegisterZip.getText());
				ps.setString(7, RegisterState.getText());
				ps.setString(8, RegisterEmail.getText());
				ps.executeUpdate();

				Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				stage.close();
				loginLbl.setText("Registration Successful");

			} else {
				registerStatus.setText("Password Does Not Meet Requirements");
			}
		}

		catch (Exception e) {
			System.out.println(e);
		}

	}

	public void forgotPassword(ActionEvent forgotPassword) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/application/ForgotPassword.FXML"));
			Parent root1 = (Parent) fxmlLoader.load();
			Scene ForgotPassword = new Scene(root1, 500, 181);
			Stage window = (Stage) ((Node) forgotPassword.getSource()).getScene().getWindow();
			window.setScene(ForgotPassword);
			window.show();
		} catch (Exception e) {

		}
	}
	
	public void adminLogin(ActionEvent forgotPassword) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/application/AdminLogin.FXML"));
			Parent root1 = (Parent) fxmlLoader.load();
			Scene AdminLogin = new Scene(root1, 500, 181);
			Stage window = (Stage) ((Node) forgotPassword.getSource()).getScene().getWindow();
			window.setScene(AdminLogin);
			window.show();
		} catch (Exception e) {

		}
	}

}
