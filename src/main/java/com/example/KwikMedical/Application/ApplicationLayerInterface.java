package com.example.KwikMedical.Application;

import com.example.KwikMedical.Entities.CallInformation;
import com.example.KwikMedical.Entities.Callout;
import com.example.KwikMedical.Entities.Patient;

public interface ApplicationLayerInterface
{

    public Patient getPatient(int registrationNumber);

    public CallInformation getCallInformation();

    void addCallout(int patientId, String incident, CallInformation callInformation, String address, String patientCondition);

    public Callout getCallout(String patientId, CallInformation callInformation) throws Exception;

    public void updateCallout(String calloutId);

    public void removeCallout(String calloutId);

    public int getClosestAvailableHospital(String address);
//    public void getClosestAvailableAmbulance();//future GPS or coordinate-based integration

    public void sendCalloutRequest();

    public void sendPatientRecord();

    public void updateCalloutFromAmbulance(Callout callout, String respondingAmbulanceId, String actionTaken);

}
