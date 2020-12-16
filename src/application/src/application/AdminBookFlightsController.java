package application;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class AdminBookFlightsController extends Main implements Initializable, BookFlightInterface {

	@FXML
	private TableView<flightTable> table;
	@FXML
	private TableColumn<flightTable, Integer> colFlightID;
	@FXML
	private TableColumn<flightTable, String> colFromCity;
	@FXML
	private TableColumn<flightTable, String> colToCity;
	@FXML
	private TableColumn<flightTable, String> colFlightDate;
	@FXML
	private TableColumn<flightTable, String> colFlightTime;
	@FXML
	private TableColumn<flightTable, Integer> colSeats;
	
	@FXML private TextField filterField;
	@FXML private TextField filterDate;
	@FXML private TextField filterTime;
	@FXML private TextField deleteFlightField;
	@FXML private Label bookLbl;
	
	@FXML
	private TextField bookFlightID;
	
	private final ObservableList<flightTable> dataList = FXCollections.observableArrayList();
	
	@Override
	public ObservableList<flightTable> getAllFlightInfo() {

		
		try {

			Connection conn = DBConnector.getConnection();
			PreparedStatement pst = conn.prepareStatement("select * from Flight");
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				int flightID = rs.getInt("FlightID");
				String fromCity = rs.getString("FromCity");
				String toCity = rs.getString("ToCity");
				String flightDate = rs.getString("FlightDate");
				String flightTime = rs.getString("FlightTime");
				int numberOfPassengers = rs.getInt("NumberOfPassengers");

				dataList.add(new flightTable(flightID, fromCity, toCity, flightDate, flightTime, numberOfPassengers));
			}

		} catch (Exception e) {

		}

		return dataList;
	}

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		FilteredList<flightTable> filteredData = new FilteredList<>(dataList, b -> true);
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(flight -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (flight.getFromCity().toLowerCase().contains(lowerCaseFilter)) {
					return true;
				}
				
				if(flight.getToCity().toLowerCase().contains(lowerCaseFilter)) {
					return true;
				}
				
				if(flight.getFlightTime().toLowerCase().contains(lowerCaseFilter)) {
					return true;
				}
				
				if(flight.getFlightDate().toLowerCase().contains(lowerCaseFilter)) {
					return true;
				}

				else 
					return false;
			});
		});
		
		SortedList<flightTable> sortedData = new SortedList<>(filteredData);
		
		sortedData.comparatorProperty().bind(table.comparatorProperty());
		
		table.setItems(sortedData);
		

		
	}
	

	@Override
	public void handle(ActionEvent event) {

		colFlightID.setCellValueFactory(new PropertyValueFactory<flightTable, Integer>("flightID"));
		colFromCity.setCellValueFactory(new PropertyValueFactory<flightTable, String>("fromCity"));
		colToCity.setCellValueFactory(new PropertyValueFactory<flightTable, String>("toCity"));
		colFlightDate.setCellValueFactory(new PropertyValueFactory<flightTable, String>("flightDate"));
		colFlightTime.setCellValueFactory(new PropertyValueFactory<flightTable, String>("flightTime"));
		colSeats.setCellValueFactory(new PropertyValueFactory<flightTable, Integer>("numberOfPassengers"));
		table.getItems().setAll(getAllFlightInfo());
	}
	
	@Override
	public boolean checkBookedTickets(String flightID) {
		try{
			Connection conn = DBConnector.getConnection();
			PreparedStatement pst = conn.prepareStatement("Select FlightID FROM BookedFlights Where CustomerEmail ='" + getEnteredEmail() + "' AND FlightID ='" + bookFlightID.getText() + "'" );
			ResultSet rs = pst.executeQuery();
			
			if (rs.next()) {
				return false;

				}
			else 
				return true;
			
			}catch(Exception e) {
				
			}
		return true;
			

	}
	
	@Override
	public void bookFlight(ActionEvent event) {
		try{
			Connection conn = DBConnector.getConnection();
			PreparedStatement ps = conn.prepareStatement("Select * FROM Flight Where FlightID ='" + bookFlightID.getText() + "'");
			ResultSet rs = ps.executeQuery();
			
			PreparedStatement pst = (PreparedStatement) conn.prepareStatement(
					"INSERT INTO BookedFlights(CustomerEmail, FlightID, FlightDate, FlightTime, FromCity, ToCity) VALUES (?,?,?,?,?,?)");
			
			
			while (rs.next()) {
			String flightDateBooking = rs.getString("FlightDate");
			String flightTimeBook = rs.getString("FlightTime");
			String flightFromCity = rs.getString("FromCity");
			String flightToCity = rs.getString("FlightTime");
			
			
				if (checkBookedTickets(bookFlightID.getText()) == true) {
				pst.setString(1, getEnteredEmail());
				pst.setString(2, bookFlightID.getText());
				pst.setString(3, flightDateBooking);
				pst.setString(4, flightTimeBook);
				pst.setString(5, flightFromCity);
				pst.setString(6, flightToCity);
				pst.executeUpdate();
				
				bookLbl.setText("Flight has been Booked!");
				}
				else 
					bookLbl.setText("You have already Booked this flight!");
			}
		
		} catch(Exception e) {
			
		  }
		
	}
	
	@Override
	public void returnButton(ActionEvent event) {
		try {
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.close();
			stage.close();
			Stage login = new Stage();
			Parent root1 = FXMLLoader.load(getClass().getResource("/application/UserInterface.fxml"));
			Scene returnToLogin = new Scene(root1, 777, 565);
			returnToLogin.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			login.setScene(returnToLogin);
			login.show();
		} catch (Exception f) {

		}
	}
	
	@Override
	public void deleteFlight(ActionEvent event) {
		
		System.out.println("aa");
		String enteredFlightID = deleteFlightField.getText();
	
		
		try{
			Connection conn = DBConnector.getConnection();
			System.out.println("a");
			PreparedStatement pst = conn.prepareStatement("DELETE FROM Flight Where FlightID = '" + enteredFlightID + "';");
			System.out.println("b");
			pst.executeUpdate();
			System.out.println("c");
		
		
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
		
}


