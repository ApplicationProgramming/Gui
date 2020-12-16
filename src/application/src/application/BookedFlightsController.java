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
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class BookedFlightsController implements Initializable{

	@FXML private TableView<BookedFlightsTable> table;
	@FXML private TableColumn<BookedFlightsTable, Integer> colFlightIDB;
	@FXML private TableColumn<BookedFlightsTable, String> colFlightDateB;
	@FXML private TableColumn<BookedFlightsTable, String> colFlightTimeB;
	@FXML private TableColumn<BookedFlightsTable, String> colFromCityB;
	@FXML private TableColumn<BookedFlightsTable, String> colToCityB;
	
	public List<BookedFlightsTable> getAllBookedFlightInfo(){
		
		List mm = new LinkedList();
		
		try {
			Connection conn = DBConnector.getConnection();
			PreparedStatement pst = conn.prepareStatement("select * from BookedFlights");
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()){
			String CustomerEmail = rs.getString("CustomerEmail");
			int flightID = rs.getInt("FlightID");
			String flightDate = rs.getString("FlightDate");
			String flightTime = rs.getString("FlightTime");
			String fromCity = rs.getString("FromCity");
			String toCity = rs.getString("ToCity");
	
			mm.add(new BookedFlightsTable(flightID, flightDate, flightTime, fromCity, toCity));
		}
	}catch(Exception e) {
		
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
}
