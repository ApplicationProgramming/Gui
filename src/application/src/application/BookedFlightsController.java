package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class BookedFlightsController extends Main implements Initializable  {

	@FXML
	private TableView<BookedFlightsTable> table;
	@FXML
	private TableColumn<BookedFlightsTable, Integer> colFlightIDB;
	@FXML
	private TableColumn<BookedFlightsTable, String> colFlightDateB;
	@FXML
	private TableColumn<BookedFlightsTable, String> colFlightTimeB;
	@FXML
	private TableColumn<BookedFlightsTable, String> colFromCityB;
	@FXML
	private TableColumn<BookedFlightsTable, String> colToCityB;
	
	@FXML
	private TextField deleteFlightField;
	
	int numberOfSeatsLeft;


	public List<BookedFlightsTable> getAllBookedFlightInfo() {


	
		List mm = new LinkedList();

		try {
			Connection conn = DBConnector.getConnection();
			PreparedStatement pst = conn.prepareStatement("select * from BookedFlights Where CustomerEmail ='" + getEnteredEmail() + "'");
			ResultSet rs = pst.executeQuery();


			while (rs.next()) {
				String CustomerEmail = rs.getString("CustomerEmail");
				int flightID = rs.getInt("FlightID");
				String flightDate = rs.getString("FlightDate");
				String flightTime = rs.getString("FlightTime");
				String fromCity = rs.getString("FromCity");
				String toCity = rs.getString("ToCity");

				mm.add(new BookedFlightsTable(flightID, flightDate, flightTime, fromCity, toCity));
			}
		} catch (Exception e) {

		}

		return mm;

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	public void handleb(ActionEvent event) {

		colFlightIDB.setCellValueFactory(new PropertyValueFactory<BookedFlightsTable, Integer>("flightID"));
		colFlightDateB.setCellValueFactory(new PropertyValueFactory<BookedFlightsTable, String>("flightDate"));
		colFlightTimeB.setCellValueFactory(new PropertyValueFactory<BookedFlightsTable, String>("flightTime"));
		colFromCityB.setCellValueFactory(new PropertyValueFactory<BookedFlightsTable, String>("fromCity"));
		colToCityB.setCellValueFactory(new PropertyValueFactory<BookedFlightsTable, String>("toCity"));
		table.getItems().setAll(getAllBookedFlightInfo());
	}
	
	public void removeFlight(ActionEvent removeFlight) {
	
		
		String enteredFlightID = deleteFlightField.getText();
	
		
		try{
			Connection conn = DBConnector.getConnection();
			PreparedStatement pst = conn.prepareStatement("DELETE FROM BookedFlights Where CustomerEmail ='" + getEnteredEmail() + "'" + " AND FlightID ='" + enteredFlightID + "'");
			pst.executeUpdate();
		 addFlightSeat(enteredFlightID);			
		}catch(Exception e) {
			
		}
	}
	
	public void returnButton(ActionEvent event) {
		try {
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.close();
			Stage login = new Stage();
			Parent root1 = FXMLLoader.load(getClass().getResource("/application/UserInterface.fxml"));
			Scene returnToLogin = new Scene(root1, 305, 239);
			returnToLogin.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			login.setScene(returnToLogin);
			login.show();
		} catch (Exception f) {

		}
	}
	
	public void addFlightSeat(String FlightID) {
		try{
			Connection conn = DBConnector.getConnection();
			PreparedStatement ps = conn.prepareStatement("Select NumberOfPassengers FROM Flight Where FlightID ='" + FlightID + "'");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				numberOfSeatsLeft = rs.getInt("NumberOfPassengers");;
				if (numberOfSeatsLeft > 0) {
					PreparedStatement pst = conn.prepareStatement("Update Flight Set NumberOfPassengers = NumberOfPassengers + 1 Where FlightID = '" + FlightID + "'");	
					pst.executeUpdate();
			}
			}
		} catch(Exception e) {
			
		}
	}

	
	
}
