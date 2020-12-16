package application;

import java.awt.TextArea;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ForgotPasswordController extends Main {

	@FXML
	private TextField sqAnswerField;

	@FXML
	private Label sqPassword;

	@FXML
	private Button submitQuestion;

	@FXML
	private Button questionReturn;

	@FXML
	private TextField enteredEmail;

	@FXML
	private Label sqQuestion;

	@FXML
	private Button submitAnswer;

	@FXML
	private Button returnToLogin;

	public void getSecurityQuestion(ActionEvent f) {

		String userEmail = enteredEmail.getText();

		try {
			Connection conn = DBConnector.getConnection();
			PreparedStatement ForgotPassword = (PreparedStatement) conn
					.prepareStatement("SELECT SecurityQuestion FROM Login WHERE CustomerEmail ='" + userEmail + "' ");

			ResultSet rs = ForgotPassword.executeQuery();

			if (rs.next()) {
				String question = rs.getString("SecurityQuestion");
				sqQuestion.setText(question);
			}

			else {
				sqQuestion.setText("No account registered");
			}
		} catch (Exception e) {

		}
	}

	public void submitAnswer(ActionEvent e) {

		String userEmail = enteredEmail.getText();
		String userAnswer = sqAnswerField.getText();
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://35.196.80.119:3306/FlightProject", "root",
					"MyTeam");
			PreparedStatement CheckAnswer = (PreparedStatement) conn.prepareStatement(
					"SELECT SecurityQuestionAnswer, Password FROM Login WHERE CustomerEmail ='" + userEmail + "' ");
			ResultSet rs = CheckAnswer.executeQuery();

			if (rs.next()) {

				String answer = rs.getString("SecurityQuestionAnswer");
				String password = rs.getString("Password");
				System.out.println(answer);

				if (answer.equals(userAnswer)) {
					sqPassword.setText(password);

				} else {
					sqPassword.setText("Answer Incorrect");
				}
			}

		}

		catch (Exception f) {

		}

	}

	public void backToLogin(ActionEvent returnToLogin) {
		Stage stage = (Stage) ((Node) returnToLogin.getSource()).getScene().getWindow();
		stage.close();
		returnToLogin();

	}
}
