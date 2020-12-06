package com.example.KwikMedical.app;

import com.example.KwikMedical.Data.PatientDataLayerInterface;
import com.example.KwikMedical.Entities.Patient;

public class PatientApplicationLayer implements PatientApplicationLayerInterface
{
    // The underlying data layer this application layer sits upon
    private PatientDataLayerInterface dataLayer;

    /**
     * Default constructor
     *
     * @param dataLayer The data layer that this layer sits upon
     */
    public PatientApplicationLayer(PatientDataLayerInterface dataLayer)
    {
        this.dataLayer = dataLayer;
    }

//    public String addPatient(String registrationNumber, String name, String programme)
//    {
//        // First create a Patient record object
//        Patient Patient = new Patient(registrationNumber, name, programme);
//        // Try and add the record to the data layer
//        boolean success = dataLayer.addPatient(Patient);
//        // Either the record was added or not.  Return an appropriate message
//        if (success)
//        {
//            return "Patient " + registrationNumber + " added";
//        }
//        else
//        {
//            return "Failed to add Patient: " + registrationNumber;
//        }
//    }

    public int getPatient(int registrationNumber)
    {
        // Try and get the Patient record object from the data layer
        Patient Patient = dataLayer.getPatientByRegistrationNumber(registrationNumber);
        // If the Patient record does not exist, the data layer will return null
        if (Patient != null)
        {
            // Return textual representation of the Patient record
            return Integer.parseInt(Patient.getRegistrationNumber() + "\n" + Patient.getName() + "\n" + Patient.getAddress());
        }
        else
        {
            // Return fail message
            return Integer.parseInt("Patient " + registrationNumber + " does not exist");
        }
    }

//    public String removePatient(String registrationNumber)
//    {
//        // Try and remove the Patient from the data layer
//        boolean success = dataLayer.removePatient(registrationNumber);
//        // Either we were successful or not.  Return appropriate message
//        if (success)
//        {
//            return "Patient " + registrationNumber + " removed";
//        }
//        else
//        {
//            return "Failed to remove Patient " + registrationNumber;
//        }
//    }

//    public String updatePatient(String registrationNumber, String name, String programme)
//    {
//        // Create a new Patient record object
//        Patient Patient = new Patient(registrationNumber, name, programme);
//        // Try and update the Patient record with the data layer
//        boolean success = dataLayer.updatePatient(registrationNumber, Patient);
//        // Either we were successful or not.  Return appropriate message.
//        if (success)
//        {
//            return "Patient " + registrationNumber + " successfully updated";
//        }
//        else
//        {
//            return "Patient " + registrationNumber + " not updated";
//        }
//    }

}
