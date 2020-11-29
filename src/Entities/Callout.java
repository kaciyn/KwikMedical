package Entities;

import java.sql.Timestamp;

public class  Callout
{
    private int PatientId;
    private int RespondingHospitalId;
    private String Event;
    private Timestamp Time;
    private String Address;
    private String ActionTaken;

    public Callout(int patientId, int respondingHospitalId, String event, Timestamp time, String address, String actionTaken)
    {
        this.PatientId = patientId;
        this.RespondingHospitalId = respondingHospitalId;
        this.Event = event;
        this.Time = time;
        this.Address = address;
        this.ActionTaken = actionTaken;
    }

    public int getPatientId()
    {
        return PatientId;
    }

    public void setPatientId(int patientId)
    {
        PatientId = patientId;
    }

    public int getRespondingHospitalId()
    {
        return RespondingHospitalId;
    }

    public void setRespondingHospitalId(int respondingHospitalId)
    {
        RespondingHospitalId = respondingHospitalId;
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
