package Data;

import Entities.Callout;
import Entities.Patient;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import static Data.DatabaseHelper.openDatabaseConnection;

public class CalloutDataLayer
{
    /**
     * Default constructor
     */
    public CalloutDataLayer()
    {
    }

    public boolean addCallout(Callout Callout)
    {
        try {
            var dbConnection = openDatabaseConnection("patients");
            Statement statement = dbConnection.createStatement();
            //TODO check if callout of same patient and time exists if so update it if not add
            String update = "INSERT INTO StudentRecord (StudentMatric, StudentName, StudentProgramme) " +
                    "VALUES ('" + matric + "', '" + name + "', '" + programme + "')";
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
        }
        catch (SQLException sqlException) {
            System.err.println("Error in SQL Update");
            System.err.println(sqlException.getMessage());
            System.exit(-1);
        }

        // First check that no Callout with the same matric number exists
        if (Callouts.containsKey(Callout.getMatric())) {
            // Callout with that matric number exists.  Return false
            return false;
        }
        else {
            // No Callout with that matric number exists.  Add Callout to data store
            Callouts.put(Callout.getMatric(), Callout);
            // Return true
            return true;
        }
    }

    public Callout getCallout(String matric)
    {
        // Retrieve the Callout from the data store
        Callout Callout = Callouts.get(matric);
        // If no Callout with that matric number is stored in the data store, then 
        // the previous operation will have returned null.  Therefore we simply return
        // whatever the outcome of the previous call was
        return Callout;
    }

    public boolean removeCallout(String matric)
    {
        // First check if a Callout with that matric number exists
        if (Callouts.containsKey(matric)) {
            // Callout exists.  Remove Callout record from data store
            Callouts.remove(matric);
            // Return true
            return true;
        }
        else {
            // Callout does not exist.  Return false
            return false;
        }
    }

    public boolean updateCallout(String matric, Callout Callout)
    {
        // First check if the Callout record exists
        if (Callouts.containsKey(matric)) {
            // The Callout record exists, therefore we can update it.
            // First retrieve the Callout record from the data store
            Callout toUpdate = Callouts.get(matric);
            // Now update the Callout record with the new name and programme
            toUpdate.setName(Callout.getName());
            toUpdate.setProgramme(Callout.getProgramme());
            // Return true
            return true;
        }
        else {
            // Callout does not exist.  Return false.
            return false;
        }
    }
}

