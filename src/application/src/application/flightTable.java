package application;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class flightTable {

	private int flightID, numberOfPassengers;
	private String fromCity, toCity, flightDate, flightTime;

	public flightTable() {

	}

	public flightTable(int flightID, String fromCity, String toCity, String flightDate, String flightTime,
			int numberOfPassengers) {
		this.flightID = flightID;
		this.fromCity = fromCity;
		this.toCity = toCity;
		this.flightDate = flightDate;
		this.flightTime = flightTime;
		this.numberOfPassengers = numberOfPassengers;
	}

	public int getFlightID() {
		return flightID;
	}

	public void setFlightID(int flightID) {
		this.flightID = flightID;
	}

	public int getNumberOfPassengers() {
		return numberOfPassengers;
	}

	public void setNumberOfPassengers(int numberOfPassengers) {
		this.numberOfPassengers = numberOfPassengers;
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

}
