package Data;

import java.sql.*;

public class DBExampleQuery 
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
			// Next we create a statement to access the database
			Statement statement = conn.createStatement();
			// Now create a simple query to get all records from the database
			String query = "SELECT * FROM StudentRecord";
			// And then get the results from executing the query
			ResultSet results = statement.executeQuery(query);
			// Loop until no records are left
			while (results.next())
			{
				// Retrieve each field of the currently selected record
				String matric = results.getString("StudentMatric");
				String name = results.getString("StudentName");
				String programme = results.getString("StudentProgramme");
				// Display the student details
				System.out.println(matric);
				System.out.println(name);
				System.out.println(programme);
				System.out.println();
			}
			// Release resources held by statement
			statement.close();
			// Release resources held by DB connection
			conn.close();
		}
		catch (ClassNotFoundException cnf)
		{
			System.err.println("Could not load driver");
			System.err.println(cnf.getMessage());
			System.exit(-1);
		}
		catch (SQLException sqe)
		{
			System.out.println("Error performing SQL Query");
			System.out.println(sqe.getMessage());
			System.exit(-1);
		}
	}
}
