package application;


import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class BookFlightsController implements Initializable{

	@FXML private TableView<flightTable> table;
	@FXML private TableColumn<flightTable, Integer> colFlightID;
	@FXML private TableColumn<flightTable, String> colFromCity;
	@FXML private TableColumn<flightTable, String> colToCity;
	@FXML private TableColumn<flightTable, String> colFlightDate;
	@FXML private TableColumn<flightTable, String> colFlightTime;
	@FXML private TableColumn<flightTable, Integer> colSeats;
	
	public List<flightTable> getAllFlightInfo(){
		
		List ll = new LinkedList();
		try {
			
			Connection conn = DBConnector.getConnection();
			PreparedStatement pst = conn.prepareStatement("select * from Flight");
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()){
			int flightID = rs.getInt("FlightID");
			String fromCity = rs.getString("FromCity");
			String toCity = rs.getString("ToCity");
			String flightDate = rs.getString("FlightDate");
			String flightTime = rs.getString("FlightTime");
			int numberOfPassengers = rs.getInt("NumberOfPassengers");
			
			
			ll.add(new flightTable(flightID, fromCity, toCity, flightDate, flightTime, numberOfPassengers));
			}
			
		}catch(Exception e) {
			
		}
		
		return ll;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
	
	
	}
	public void handle(ActionEvent event) {
		
		colFlightID.setCellValueFactory(new PropertyValueFactory<flightTable, Integer>("flightID"));
		colFromCity.setCellValueFactory(new PropertyValueFactory<flightTable, String>("fromCity"));
		colToCity.setCellValueFactory(new PropertyValueFactory<flightTable, String>("toCity"));
		colFlightDate.setCellValueFactory(new PropertyValueFactory<flightTable, String>("flightDate"));
		colFlightTime.setCellValueFactory(new PropertyValueFactory<flightTable, String>("flightTime"));
		colSeats.setCellValueFactory(new PropertyValueFactory<flightTable, Integer>("numberOfPassengers"));
		table.getItems().setAll(getAllFlightInfo());
	}
}
