package com.example.KwikMedical.Data;

import com.example.KwikMedical.Entities.Ambulance;
import com.example.KwikMedical.Entities.Callout;
import com.example.KwikMedical.Entities.Hospital;
import com.example.KwikMedical.Entities.Patient;

import java.sql.Timestamp;
import java.util.List;

public interface DataLayerInterface
{
    public Patient getPatientByRegistrationNumber(int registrationNumber);

    public List<Patient> getPatientsByName(String name);

    public void addCallout(Callout Callout);

    public Callout getCalloutByPatientAndTime(String patientId, Timestamp time);

    public void updateCallout(Callout callout);

    public void removeCallout(int id);

    public Hospital getHospital(int id);

    public void updateAmbulanceAvailability(int id, boolean availability);

    public Ambulance getAvailableAmbulance(int hospitalId);
}

