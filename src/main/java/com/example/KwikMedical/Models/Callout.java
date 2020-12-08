package com.example.KwikMedical.Models;

import java.io.Serializable;
import java.sql.Timestamp;

public class Callout implements Serializable
{
    private Integer Id;
    private Integer PatientId;
    private Integer RespondingAmbulanceId;
    private String MedicalInformation;
    private String PatientCondition;
    private String Incident;
    private Timestamp Timestamp;
    private int CallLength;
    private String Address;
    private String ActionTaken;


    //for initial callout creation
    public Callout(int patientId, String incident, Timestamp timestamp, int callLength, String address, String medicalInformation,String patientCondition)
    {
        this.PatientId = patientId;
        this.Incident = incident;
        this.Timestamp = timestamp;
        this.CallLength = callLength;
        this.MedicalInformation = medicalInformation;
        this.PatientCondition = patientCondition;
        this.Address = address;
    }


    public Callout(int id, int patientId, int respondingAmbulanceId, String incident, Timestamp timestamp, int callLength, String address, String actionTaken)
    {
        this.Id = id;
        this.PatientId = patientId;
        this.RespondingAmbulanceId = respondingAmbulanceId;
        this.Incident = incident;
        this.Timestamp = timestamp;
        this.CallLength = callLength;
        this.Address = address;
        this.ActionTaken = actionTaken;
    }

    public int getId()
    {
        return Id;
    }


    public int getPatientId()
    {
        return PatientId;
    }

    public void setPatientId(int patientId)
    {
        PatientId = patientId;
    }

    public int getRespondingAmbulanceId()
    {
        return RespondingAmbulanceId;
    }

    public void setRespondingAmbulanceId(int respondingAmbulanceId)
    {
        RespondingAmbulanceId = respondingAmbulanceId;
    }

    public String getIncident()
    {
        return Incident;
    }

    public void setIncident(String incident)
    {
        Incident = incident;
    }

    public Timestamp getTimestamp()
    {
        return Timestamp;
    }

    public void setTimestamp(Timestamp timestamp)
    {
        Timestamp = timestamp;
    }

    public int getCallLength()
    {
        return CallLength;
    }

    public void setCallLength(int callLength)
    {
        CallLength = callLength;
    }

    public String getAddress()
    {
        return Address;
    }

    public void setAddress(String address)
    {
        Address = address;
    }

    public String getActionTaken()
    {
        return ActionTaken;
    }

    public void setActionTaken(String actionTaken)
    {
        ActionTaken = actionTaken;
    }

    public String getPatientCondition()
    {
        return PatientCondition;
    }

    public void setPatientCondition(String patientCondition)
    {
        PatientCondition = patientCondition;
    }

    public String getMedicalInformation()
    {
        return MedicalInformation;
    }

    public void setMedicalInformation(String medicalInformation)
    {
        MedicalInformation = medicalInformation;
    }
}
