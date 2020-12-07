package com.example.KwikMedical.Application;

import com.example.KwikMedical.Data.DataLayerInterface;
import com.example.KwikMedical.Entities.CallInformation;
import com.example.KwikMedical.Entities.Callout;
import com.example.KwikMedical.Entities.Patient;

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
    public void addCallout(int patientId, String incident, CallInformation callInformation, String address, String patientCondition)
    {
        Callout Callout = new Callout(patientId, incident, callInformation.getTimestamp(), callInformation.getCallLength(), address, patientCondition);

        dataLayer.addCallout(Callout);
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
        dataLayer.removeCallout(calloutId);
    }

    @Override
    public void updateCalloutFromAmbulance(Callout callout, String respondingAmbulanceId, String actionTaken)
    {
        callout.setRespondingAmbulanceId(Integer.parseInt(respondingAmbulanceId));
        callout.setActionTaken(actionTaken);

        dataLayer.updateCallout(callout);
    }

    //currently just returns the first hospital with a free ambulance in table, full implementation with location/maps integration would calculate distance to patient
    public int getClosestAvailableHospital(String address)
    {
//        return dataLayer.get();
        return 1;
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


//    @Override
//    public void getClosestAvailableAmbulance()
//    {
//
//    }


}
