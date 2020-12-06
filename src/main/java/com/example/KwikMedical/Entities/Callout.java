package com.example.KwikMedical.Entities;

import java.sql.Timestamp;

public class Callout
{
    private Integer Id;
    private Integer PatientId;
    private Integer RespondingAmbulanceId;
    private String Event;
    private Timestamp Timestamp;
    private int CallLength;
    private String Address;
    private String ActionTaken;


    //for initial callout request
    public Callout(int patientId, String event, Timestamp timestamp, int callLength, String address)
    {
        this.PatientId = patientId;
        this.Event = event;
        this.Timestamp = timestamp;
        this.CallLength = callLength;
        this.Address = address;

        //unknown until added to db/received info from ambulance
        this.Id = null;
        this.RespondingAmbulanceId = null;
        this.ActionTaken = null;

    }


    public Callout(int id, int patientId, int respondingAmbulanceId, String event, Timestamp timestamp, int callLength, String address, String actionTaken)
    {
        this.Id = id;
        this.PatientId = patientId;
        this.RespondingAmbulanceId = respondingAmbulanceId;
        this.Event = event;
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

    public String getEvent()
    {
        return Event;
    }

    public void setEvent(String event)
    {
        Event = event;
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
}
