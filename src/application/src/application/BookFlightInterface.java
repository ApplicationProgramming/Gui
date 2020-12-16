package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public interface BookFlightInterface {

	ObservableList<flightTable> getAllFlightInfo();

	void initialize(URL location, ResourceBundle resources);

	void handle(ActionEvent event);

	boolean checkBookedTickets(String flightID);

	void bookFlight(ActionEvent event);

	void returnButton(ActionEvent event);

	void deleteFlight(ActionEvent event);

}