package Data;

import Entities.Callout;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import static Data.DatabaseHelpers.openDatabaseConnection;

public class CalloutDataLayer implements CalloutDataLayerInterface
{
    /**
     * Default constructor
     */
    public CalloutDataLayer()
    {
    }

    public void addCallout(Callout callout)
    {
        try {
            var dbConnection = openDatabaseConnection("patients");
            Statement statement = dbConnection.createStatement();

            String insert = "INSERT INTO callouts (PatientID,RespondingAmbulanceID, Event, Timestamp,CallLength,Address,ActionTaken) " +
                    "VALUES ('" + callout.getPatientId() + "', '" + callout.getRespondingAmbulanceId() + "', '" + callout.getEvent() + "', '" + callout.getTimestamp() + "', '" + callout.getCallLength() + "', '" + callout.getAddress() + "', '" + callout.getActionTaken() + "')";

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
            var dbConnection = openDatabaseConnection("callouts");
            Statement statement = dbConnection.createStatement();
            // Now create a simple query to get all records from the database
            String query = "SELECT * FROM callouts WHERE PatientID=" + patientId + " AND Timestamp =" + time;
            ResultSet results = statement.executeQuery(query);

            if (results.next()) {
                // We will update the first hit (there should be only one)
                results.first();

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
            var dbConnection = openDatabaseConnection("callouts");
            Statement statement = dbConnection.createStatement();
            // Now create a simple query to get all records from the database
            String query = "SELECT * FROM callouts WHERE ID=" + callout.getId();

            ResultSet results = statement.executeQuery(query);

            if (results.next()) {
//these should be the only ones updated from the ambulance
                results.updateString("Event", callout.getEvent());
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

    public void removeCallout(String id)
    {
        try {
            var dbConnection = openDatabaseConnection("callouts");
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

    public Callout getCalloutById(int id)
    {
        Callout callout = null;

        try {
            var dbConnection = openDatabaseConnection("callouts");
            Statement statement = dbConnection.createStatement();
            // Now create a simple query to get all records from the database
            String query = "SELECT * FROM callouts WHERE ID=" + id;
            ResultSet results = statement.executeQuery(query);

            if (results.next()) {
                // We will update the first hit (there should be only one)
                results.first();

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


}

