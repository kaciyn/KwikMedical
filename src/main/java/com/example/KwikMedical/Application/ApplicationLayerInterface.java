package com.example.KwikMedical.Application;

import com.example.KwikMedical.Models.*;

public interface ApplicationLayerInterface
{

    public Patient getPatient(int registrationNumber);

    public CallInformation getCallInformation();

    public void addCallout(Callout callout);

    public Callout getCallout(String patientId, CallInformation callInformation) throws Exception;

//    public void updateCallout(String calloutId);

    public void removeCallout(String calloutId);

    public Hospital getClosestAvailableHospital(String address);
//    public void getClosestAvailableAmbulance();//future GPS or coordinate-based integration
//
//    public void sendCalloutRequest();
//
//    public void sendPatientRecord();

    public void updateCalloutFromAmbulance(Callout callout, int ambulanceID, String patientCondition, String incident, String actionTaken);

    public Ambulance getAvailableAmbulance(int hospitalId);

    public void updateAmbulanceAvailability(int id, boolean availability);


}
