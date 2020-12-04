package Data;

import Entities.Patient;
import java.util.ArrayList;
import java.sql.*;
import java.util.List;

import static Data.DatabaseHelpers.openDatabaseConnection;

public class PatientDataLayer implements PatientDataLayerInterface
{


    public Patient getPatientByRegistrationNumber(String registrationNumber)
    {
        Patient patient = null;
        try {
            Connection dbConnection = openDatabaseConnection("patients");
            Statement statement = dbConnection.createStatement();
            // Now create a simple query to get all records from the database
            String query = "SELECT * FROM patients WHERE RegistrationNumber=" + registrationNumber;
            ResultSet results = statement.executeQuery(query);

            if (results.next()) {
                // We will update the first hit (there should be only one)
                results.first();

                patient = new Patient(results.getInt("RegistrationNumber"), results.getString("Name"), results.getString("Address"), results.getString("MedicalRecord"));

            }
            else {
                // No matching records. Display message
                System.out.println("No patient with registration number " + registrationNumber + " found.");
            }

            statement.close();
            dbConnection.close();
        }
        catch (SQLException sqlException) {
            System.err.println("Error in SQL Update");
            System.err.println(sqlException.getMessage());
            System.exit(-1);
        }

        return patient;
    }


    public List<Patient> getPatientsByName(String name)
    {
        ArrayList<Patient> patients = new ArrayList<Patient>();
        try {
            Connection dbConnection = openDatabaseConnection("patients");
            Statement statement = dbConnection.createStatement();
            // Now create a simple query to get all records from the database
            String query = "SELECT * FROM patients WHERE Name=" + name;
            ResultSet results = statement.executeQuery(query);

            if (results.next()) {
                // We will update the first hit (there should be only one)
                results.first();

                Patient patient = new Patient(results.getInt("RegistrationNumber"), results.getString("Name"), results.getString("Address"), results.getString("MedicalRecord"));

                patients.add(patient);
            }
            else {
                // No matching records. Display message
                System.out.println("No patients named " + name + " found.");
            }
            statement.close();
            dbConnection.close();
        }
        catch (SQLException sqlException) {
            System.err.println("Error in SQL Update");
            System.err.println(sqlException.getMessage());
            System.exit(-1);
        }

        return patients;
    }


}
