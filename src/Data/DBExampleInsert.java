package Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBExampleInsert 
{
	public static void main(String[] args)
	{
		try
		{
			// Load the driver
			Class.forName("com.mysql.jdbc.Driver");
			// First we need to establish a connection to the database
			Connection conn = DriverManager
					.getConnection("jdbc:mysql://localhost/students?user=Java&password=Java");
			// Set up keyboard input
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			// Prompt for new Student details
			System.out.print("New Student matric: ");
			String matric = input.readLine();
			System.out.print("New Student name: ");
			String name = input.readLine();
			System.out.print("New Student programme: ");
			String programme = input.readLine();
			// Create a new SQL statement
			Statement statement = conn.createStatement();
			// Build the INSERT statement
			String update = "INSERT INTO StudentRecord (StudentMatric, StudentName, StudentProgramme) " +
							"VALUES ('" + matric + "', '" + name + "', '" + programme + "')";
			// Execute the statement
			statement.executeUpdate(update);
			// Release resources held by the statement
			statement.close();
			// Release resources held by the connection.  This also ensures that the INSERT completes
			conn.close();
			System.out.println("Update successful");
		}
		catch (ClassNotFoundException cnf)
		{
			System.err.println("Could not load driver");
			System.err.println(cnf.getMessage());
			System.exit(-1);
		}
		catch (IOException ioe)
		{
			System.err.println("Error in I/O");
			System.err.println(ioe.getMessage());
			System.exit(-1);
		}
		catch (SQLException sqe)
		{
			System.err.println("Error performing SQL Update");
			System.err.println(sqe.getMessage());
			System.exit(-1);
		}
	}
}
