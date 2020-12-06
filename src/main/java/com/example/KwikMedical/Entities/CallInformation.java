package com.example.KwikMedical.Entities;

import java.sql.Timestamp;

public class CallInformation
{
    private Timestamp Timestamp;
    private Integer CallLength;

    public CallInformation(java.sql.Timestamp timestamp, Integer callLength)
    {
        Timestamp = timestamp;
        CallLength = callLength;
    }

    public java.sql.Timestamp getTimestamp()
    {
        return Timestamp;
    }

    public void setTimestamp(java.sql.Timestamp timestamp)
    {
        Timestamp = timestamp;
    }

    public Integer getCallLength()
    {
        return CallLength;
    }

    public void setCallLength(Integer callLength)
    {
        CallLength = callLength;
    }
}
