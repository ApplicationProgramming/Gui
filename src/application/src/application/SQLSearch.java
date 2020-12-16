package application;

public class SQLSearch {

	public String ToCitySearch(String toCity) {
		String s = "Select * From Flights Where ToCity ='" + toCity +"'";
		return s;
	}
}
