package com.example.KwikMedical.Data;

import com.example.KwikMedical.Entities.Ambulance;
import com.example.KwikMedical.Entities.Callout;
import com.example.KwikMedical.Entities.Hospital;
import com.example.KwikMedical.Entities.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.KwikMedical.Data.DatabaseHelpers.openDatabaseConnection;

public class DataLayer implements DataLayerInterface
{
    /**
     * Default constructor
     */
    public DataLayer()
    {
    }

    public Patient getPatientByRegistrationNumber(int registrationNumber)
    {
        Patient patient = null;
        try {
            Connection dbConnection = openDatabaseConnection();
            Statement statement = dbConnection.createStatement();
            // Now create a simple query to get all records from the database
            String query = "SELECT * FROM patients WHERE RegistrationNumber=" + registrationNumber;
            ResultSet results = statement.executeQuery(query);

            if (results.next()) {
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
            Connection dbConnection = openDatabaseConnection();
            Statement statement = dbConnection.createStatement();
            // Now create a simple query to get all records from the database
            String query = "SELECT * FROM patients WHERE Name=" + name;
            ResultSet results = statement.executeQuery(query);

            if (results.next()) {
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


    public void addCallout(Callout callout)
    {
        try {
            Connection dbConnection = openDatabaseConnection();
            Statement statement = dbConnection.createStatement();

            String insert = "INSERT INTO callouts (PatientID,RespondingAmbulanceID, Incident, Timestamp,CallLength,Address,ActionTaken) " +
                    "VALUES ('" + callout.getPatientId() + "', '" + callout.getRespondingAmbulanceId() + "', '" + callout.getIncident() + "', '" + callout.getTimestamp() + "', '" + callout.getCallLength() + "', '" + callout.getAddress() + "', '" + callout.getActionTaken() + "')";

            statement.executeUpdate(insert);
            statement.close();
            dbConnection.close();
            System.out.println("Update successful");

        }
        catch (SQLException sqlException) {
            System.err.println("Error in SQL Update");
            System.err.println(sqlException.getMessage());
            System.exit(-1);
        }
    }

    public Callout getCalloutByPatientAndTime(String patientId, Timestamp time)
    {
        Callout Callout = null;
        try {
            Connection dbConnection = openDatabaseConnection();
            Statement statement = dbConnection.createStatement();
            // Now create a simple query to get all records from the database
            String query = "SELECT * FROM callouts WHERE PatientID=" + patientId + " AND Timestamp =" + time;
            ResultSet results = statement.executeQuery(query);

            if (results.next()) {

                Callout = new Callout(results.getInt("ID"), results.getInt("PatientID"), results.getInt("RespondingAmbulanceID"), results.getString("Event"), results.getTimestamp("Timestamp"), results.getInt("CallLength"), results.getString("Address"), results.getString("ActionTaken"));
            }
            else {
                // No matching records. Display message
                System.out.println("No callout with matching patient " + patientId + " at " + time + "found.");
            }
            statement.close();
            dbConnection.close();
        }
        catch (SQLException sqlException) {
            System.err.println("Error in SQL Update");
            System.err.println(sqlException.getMessage());
            System.exit(-1);
        }

        return Callout;
    }


    public void updateCallout(Callout callout)
    {
        try {
            Connection dbConnection = openDatabaseConnection();
            Statement statement = dbConnection.createStatement();
            // Now create a simple query to get all records from the database
            String query = "SELECT * FROM callouts WHERE ID=" + callout.getId();

            ResultSet results = statement.executeQuery(query);

            if (results.next()) {
//these should be the only ones updated from the ambulance
                results.updateString("Incident", callout.getIncident());
                results.updateString("ActionTaken", callout.getActionTaken());
                results.updateRow();
            }
            else {
                // No matching records. Display message
                System.out.println("No callout with matching id " + callout.getId());
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

    public void removeCallout(int id)
    {
        try {
            Connection dbConnection = openDatabaseConnection();
            Statement statement = dbConnection.createStatement();
            // Now create a simple query to get all records from the database
            String query = "SELECT * FROM callouts WHERE ID=" + id;

            ResultSet results = statement.executeQuery(query);

            if (results.next()) {
//these should be the only ones updated from the ambulance
                results.deleteRow();
                System.out.println("Callout successfully deleted with id " + id);

            }
            else {
                // No matching records. Display message
                System.out.println("No callout with matching id " + id);
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

    @Override
    public Hospital getHospital(int id)
    {
        Hospital hospital = null;
        try {
            Connection dbConnection = openDatabaseConnection();
            Statement statement = dbConnection.createStatement();
            // Now create a simple query to get all records from the database
            String query = "SELECT * FROM hospitals WHERE ID=" + id;
            ResultSet results = statement.executeQuery(query);

            if (results.next()) {
                hospital = new Hospital(results.getInt("ID"), results.getString("Name"), results.getString("ServerAddress"));

            }
            else {
                // No matching records. Display message
                System.out.println("No hospital with ID " + id + " found.");
            }

            statement.close();
            dbConnection.close();
        }
        catch (SQLException sqlException) {
            System.err.println("Error in SQL Update");
            System.err.println(sqlException.getMessage());
            System.exit(-1);
        }

        return hospital;

    }

    public Callout getCalloutById(int id)
    {
        Callout callout = null;

        try {
            Connection dbConnection = openDatabaseConnection();
            Statement statement = dbConnection.createStatement();
            // Now create a simple query to get all records from the database
            String query = "SELECT * FROM callouts WHERE ID=" + id;
            ResultSet results = statement.executeQuery(query);

            if (results.next()) {
                callout = new Callout(results.getInt("ID"), results.getInt("PatientID"), results.getInt("RespondingAmbulanceID"), results.getString("Event"), results.getTimestamp("Timestamp"), results.getInt("CallLength"), results.getString("Address"), results.getString("ActionTaken"));
            }
            else {
                // No matching records. Display message
                System.out.println("No callout with matching id " + id);
            }
            statement.close();
            dbConnection.close();
        }
        catch (SQLException sqlException) {
            System.err.println("Error in SQL Update");
            System.err.println(sqlException.getMessage());
            System.exit(-1);
        }
        return callout;
    }

    public void updateAmbulanceAvailability(int id, boolean availability)
    {
        try {
            Connection dbConnection = openDatabaseConnection();
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

    @Override
    public Ambulance getAvailableAmbulance(int hospitalId)
    {
        Ambulance ambulance = null;
        try {
            Connection dbConnection = openDatabaseConnection();
            Statement statement = dbConnection.createStatement();
            // Now create a simple query to get all records from the database
            String query = "SELECT * FROM ambulances WHERE HospitalID=" + hospitalId + " AND Available =1";
            ResultSet results = statement.executeQuery(query);

            if (results.next()) {
                ambulance = new Ambulance(results.getInt("ID"), results.getInt("Available") > 0, results.getInt("HospitalID"), results.getString("ServerAddress"));
            }
            else {
                // No matching records. Display message
                System.out.println("No available ambulances at hospital with id " + hospitalId + "found.");
            }
            statement.close();
            dbConnection.close();
        }
        catch (SQLException sqlException) {
            System.err.println("Error in SQL Update");
            System.err.println(sqlException.getMessage());
            System.exit(-1);
        }

        return ambulance;
    }


}

