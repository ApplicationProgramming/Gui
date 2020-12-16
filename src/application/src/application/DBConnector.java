package application;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class DBConnector {

	public static Connection getConnection() throws SQLException {

		Connection conn = DriverManager.getConnection("jdbc:mysql://35.196.80.119:3306/FlightProject", "root",
				"MyTeam");

		return conn;
	}

}
