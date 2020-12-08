package com.example.KwikMedical.Data;

import com.example.KwikMedical.Models.Ambulance;
import com.example.KwikMedical.Models.Callout;
import com.example.KwikMedical.Models.Hospital;
import com.example.KwikMedical.Models.Patient;

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

