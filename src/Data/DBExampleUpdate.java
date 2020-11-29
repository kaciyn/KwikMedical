package Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class DBExampleUpdate {
	public static void main(String[] args) {
		try {
			// Load the driver
			Class.forName("com.mysql.jdbc.Driver");
			// First we need to establish a connection to the database
			Connection conn = DriverManager
					.getConnection("jdbc:mysql://localhost/students?user=Java&password=Java");
			// Set up keyboard input
			BufferedReader input = new BufferedReader(new InputStreamReader(
					System.in));
			// Prompt for student matric number
			System.out.print("Enter student matric: ");
			String matric = input.readLine();
			// Create statement object. We need to traverse results so allow
			// scrolling
			Statement statement = conn
					.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE);
			// Create query string
			String query = "SELECT * FROM StudentRecord WHERE StudentMatric = '"
					+ matric + "'";
			// Get results from query
			ResultSet results = statement.executeQuery(query);
			// Check if we have results
			if (results.next()) {
				// We will update the first hit (there should be only one)
				results.first();
				// Prompt for new name and programme
				System.out.print("Enter new Student name: ");
				String name = input.readLine();
				System.out.print("Enter new Student programme: ");
				String programme = input.readLine();
				// Update the relevant columns in the DB
				results.updateString("StudentName", name);
				results.updateString("StudentProgramme", programme);
				// Update the row in the DB
				results.updateRow();
				System.out.println("Record updated");
			} else {
				// No matching records. Display message
				System.out.println("Record does not exist");
			}
			// Free statement resources
			statement.close();
			// Free connection resources and commit updates
			conn.close();
		} catch (ClassNotFoundException cnf) {
			System.err.println("Could not load driver");
			System.err.println(cnf.getMessage());
			System.exit(-1);
		} catch (IOException ioe) {
			System.err.println("Error in I/O");
			System.err.println(ioe.getMessage());
			System.exit(-1);
		} catch (SQLException sqe) {
			System.err.println("Error in SQL Update");
			System.err.println(sqe.getMessage());
			System.exit(-1);
		}
	}
}
