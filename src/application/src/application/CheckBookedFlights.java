package application;

public class CheckBookedFlights {
	
	private String flightDateBooking, flightTimeBooking, flightFromCity, flightToCity;
	
	public CheckBookedFlights() {
		
	}

	public CheckBookedFlights(String flightDateBooking, String flightTimeBooking, String flightFromCity, String flightToCity) {
		this.flightDateBooking = flightDateBooking;
		this.flightTimeBooking = flightTimeBooking;
		this.flightFromCity = flightFromCity;
		this.flightToCity = flightToCity;
		
	}

	public String getFlightDateBooking() {
		return flightDateBooking;
	}

	public void setFlightDateBooking(String flightDateBooking) {
		this.flightDateBooking = flightDateBooking;
	}

	public String getFlightTimeBooking() {
		return flightTimeBooking;
	}

	public void setFlightTimeBooking(String flightTimeBooking) {
		this.flightTimeBooking = flightTimeBooking;
	}

	public String getFlightFromCity() {
		return flightFromCity;
	}

	public void setFlightFromCity(String flightFromCity) {
		this.flightFromCity = flightFromCity;
	}

	public String getFlightToCity() {
		return flightToCity;
	}

	public void setFlightToCity(String flightToCity) {
		this.flightToCity = flightToCity;
	}
	
	
	
}
