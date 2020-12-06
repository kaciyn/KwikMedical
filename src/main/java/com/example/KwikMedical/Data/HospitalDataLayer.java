package com.example.KwikMedical.Data;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.example.KwikMedical.Data.DatabaseHelpers.openDatabaseConnection;

public class HospitalDataLayer implements HospitalDataLayerInterface
{

    public void updateAmbulanceAvailability(int id, boolean availability)
    {
        try {
            Connection dbConnection = openDatabaseConnection("callouts");
            Statement statement = dbConnection.createStatement();
            // Now create a simple query to get all records from the database
            String query = "SELECT * FROM callouts WHERE ID=" + id;

            ResultSet results = statement.executeQuery(query);

            if (results.next()) {
//these should be the only ones updated from the ambulance
                results.updateInt("Available", availability ? 1 : 0);
                results.updateRow();
            }
            else {
                // No matching records. Display message
                System.out.println("No ambulance found with  id " + id);
            }
            statement.close();
            dbConnection.close();
        }
        catch (SQLException sqlException) {
            System.err.println("Error in SQL Update");
            System.err.println(sqlException.getMessage());
            System.exit(-1);
        }
    }


}

