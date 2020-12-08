package com.example.KwikMedical.Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseHelper
{
    public static Connection openDatabaseConnection()
    {
        try {


            // Load the driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            var localDatabaseUri="jdbc:mysql://localhost:3306/mydb";
            var remoteDatabaseUri="jdbc:mysql://freedb.tech:3306/mydb";

            var user="freedbtech_kaci";
            var password="password";

            // First we need to establish a connection to the database
            return DriverManager
                    .getConnection(remoteDatabaseUri, user, password);
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