package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;

public class BookedFlightsTable {
	
	private int flightID;
	private String flightDate, flightTime, fromCity, toCity;
	
	public BookedFlightsTable() {
		
	}
	
	public BookedFlightsTable(int flightID, String flightDate, String flightTime, String fromCity, String toCity) {
		this.flightID = flightID;
		this.flightDate = flightDate;
		this.flightTime = flightTime;
		this.fromCity = fromCity;
		this.toCity = toCity;
		
	}

	public int getFlightID() {
		return flightID;
	}

	public void setFlightID(int flightID) {
		this.flightID = flightID;
	}

	public String getFlightDate() {
		return flightDate;
	}

	public void setFlightDate(String flightDate) {
		this.flightDate = flightDate;
	}

	public String getFlightTime() {
		return flightTime;
	}

	public void setFlightTime(String flightTime) {
		this.flightTime = flightTime;
	}

	public String getFromCity() {
		return fromCity;
	}

	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
	}

	public String getToCity() {
		return toCity;
	}

	public void setToCity(String toCity) {
		this.toCity = toCity;
	}


	
	
		
}


