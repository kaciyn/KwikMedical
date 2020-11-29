package Data;

import Entities.Callout;

import java.sql.Timestamp;

public class CalloutDataLayer
{
    // The underlying data layer this application layer sits upon
    private CalloutDataLayerInterface dataLayer;
    /**
     * Default constructor
     *
     * @param dataLayer The data layer that this layer sits upon
     */
    public CalloutApplicationLayer(CalloutDataLayerInterface dataLayer)
    {
        this.dataLayer = dataLayer;
    }


    public String addCallout(int patientId, int respondingHospitalId, String event, Timestamp time, String address, String actionTaken)
    {
        // First create a Callout record object
        Callout Callout = new Callout(patientId,respondingHospitalId,event,time,address,actionTaken);
        // Try and add the record to the data layer
        boolean success = dataLayer.addCallout(Callout);
        // Either the record was added or not.  Return an appropriate message
        if (success)
        {
            return "Callout " + registrationNumber + " added";
        }
        else
        {
            return "Failed to add Callout: " + registrationNumber;
        }
    }

    public String getCallout(String registrationNumber)
    {
        // Try and get the Callout record object from the data layer
        Callout Callout = dataLayer.getCallout(registrationNumber);
        // If the Callout record does not exist, the data layer will return null
        if (Callout != null)
        {
            // Return textual representation of the Callout record
            return Callout.getregistrationNumber() + "\n" + Callout.getName() + "\n" + Callout.getAddress();
        }
        else
        {
            // Return fail message
            return "Callout " + registrationNumber + " does not exist";
        }
    }



    public String updateCallout(String registrationNumber, String name, String programme)
    {
        // Create a new Callout record object
        Callout Callout = new Callout(registrationNumber, name, programme);
        // Try and update the Callout record with the data layer
        boolean success = dataLayer.updateCallout(registrationNumber, Callout);
        // Either we were successful or not.  Return appropriate message.
        if (success)
        {
            return "Callout " + registrationNumber + " successfully updated";
        }
        else
        {
            return "Callout " + registrationNumber + " not updated";
        }
    }


    public String removeCallout(String registrationNumber)
    {
        // Try and remove the Callout from the data layer
        boolean success = dataLayer.removeCallout(registrationNumber);
        // Either we were successful or not.  Return appropriate message
        if (success)
        {
            return "Callout " + registrationNumber + " removed";
        }
        else
        {
            return "Failed to remove Callout " + registrationNumber;
        }
    }
}

