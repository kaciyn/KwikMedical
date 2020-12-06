package com.example.KwikMedical.Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHelpers
{
    public static Connection openDatabaseConnection(String database)
    {
        try {   // Load the driver
            Class.forName("com.mysql.jdbc.Driver");
            // First we need to establish a connection to the database
            return DriverManager
                    .getConnection("jdbc:mysql://localhost/" + database + "?user=Java&password=Java");
        }
        catch (ClassNotFoundException classNotFoundException) {
            System.err.println("Could not load driver");
            System.err.println(classNotFoundException.getMessage());
            System.exit(-1);
        }
        catch (SQLException sqlException) {
            System.err.println("Error in SQL Update");
            System.err.println(sqlException.getMessage());
            System.exit(-1);
        }
        return null;
    }

}
