package com.example.KwikMedical.Application;

import com.example.KwikMedical.Data.DataLayerInterface;
import com.example.KwikMedical.Models.*;

import java.sql.Timestamp;

public class ApplicationLayer implements ApplicationLayerInterface
{
    // The underlying data layer this application layer sits upon
    private DataLayerInterface dataLayer;

    /**
     * Default constructor
     *
     * @param dataLayer The data layer that this layer sits upon
     */
    public ApplicationLayer(DataLayerInterface dataLayer)
    {
        this.dataLayer = dataLayer;
    }

    @Override
    public Patient getPatient(int registrationNumber)
    {
        return dataLayer.getPatientByRegistrationNumber(registrationNumber);
    }


    @Override
    public CallInformation getCallInformation()
    {
        return new CallInformation(new Timestamp(System.currentTimeMillis()), 20000);
    }


    @Override
    public void addCallout(Callout callout)
    {
        dataLayer.addCallout(callout);
    }

    @Override
    public Callout getCallout(String registrationNumber, CallInformation callInformation) throws Exception
    {
        Callout Callout = dataLayer.getCalloutByPatientAndTime(registrationNumber, callInformation.getTimestamp());

        if (Callout != null) {
            return Callout;
//                    .getRegistrationNumber() + "\n" + Callout.getName() + "\n" + Callout.getAddress();
        }
        else {
            throw new Exception("No callout found");
        }
    }

    @Override
    public void removeCallout(String calloutId)
    {
        dataLayer.removeCallout(Integer.parseInt(calloutId));
    }

    @Override
    public void updateCalloutFromAmbulance(Callout callout, int respondingAmbulanceID, String patientCondition, String incident, String actionTaken)
    {
        callout.setRespondingAmbulanceId(respondingAmbulanceID);

        //appends to initial info
        callout.setPatientCondition(callout.getPatientCondition() + "\nAmbulance Update: " + patientCondition);
        callout.setIncident(callout.getPatientCondition() + "\nAmbulance Update: " + incident);

        dataLayer.updateCallout(callout);
    }

    @Override
    public Ambulance getAvailableAmbulance(int hospitalId)
    {
        return dataLayer.getAvailableAmbulance(hospitalId);
    }

    @Override
    public void updateAmbulanceAvailability(int id, boolean availability)
    {
        dataLayer.updateAmbulanceAvailability(id,availability);
    }

    //currently just returns the first hospital in table, full implementation with location/maps integration would calculate distance to patient
    public Hospital getClosestAvailableHospital(String address)
    {
        return dataLayer.getHospital(2);
    }

    @Override
    public void sendPatientRecord()
    {
        //TODO implement
    }


    @Override
    public void updateCallout(String calloutId)
    {

    }


    @Override
    public void sendCalloutRequest()
    {

    }


}
