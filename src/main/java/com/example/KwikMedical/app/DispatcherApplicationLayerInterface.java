package com.example.KwikMedical.app;

import com.example.KwikMedical.Entities.CallInformation;

public interface DispatcherApplicationLayerInterface
{
    public CallInformation getCallInformation();

    public void getClosestAvailableAmbulance();//for location-based pickup

    public void adviseHelp();

    public void sendCalloutRequest();

    public void sendPatientRecord();

}
