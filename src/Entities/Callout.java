package Entities;

import java.sql.Timestamp;

public class Callout
{
    private int Id;
    private int PatientId;
    private int RespondingAmbulanceId;
    private String Event;
    private Timestamp Time;
    private int CallLength;
    private String Address;
    private String ActionTaken;


    public Callout(int id, int patientId, int respondingAmbulanceId, String event, Timestamp time, int callLength, String address, String actionTaken)
    {
        this.Id = id;
        this.PatientId = patientId;
        this.RespondingAmbulanceId = respondingAmbulanceId;
        this.Event = event;
        this.Time = time;
        this.CallLength = callLength;
        this.Address = address;
        this.ActionTaken = actionTaken;

    }

    public int getId()
    {
        return Id;
    }

    public void setId(int id)
    {
        Id = id;
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

    public Timestamp getTime()
    {
        return Time;
    }

    public void setTime(Timestamp time)
    {
        Time = time;
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
