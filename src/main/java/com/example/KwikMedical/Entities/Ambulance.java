package com.example.KwikMedical.Entities;

import java.io.Serializable;

public class Ambulance implements Serializable
{

  private int id;
  private boolean available;
  private final int hospitalID;
  private int hospitalId;
  private String serverAddress;

  public Ambulance(int id, boolean available, int hospitalID, String serverAddress)
  {

    this.id = id;
    this.available = available;
    this.hospitalID = hospitalID;
    this.serverAddress = serverAddress;
  }


  public long getId() {
    return id;
  }


  public boolean getAvailable() {
    return available;
  }

  public void setAvailable(boolean available) {
    this.available = available;
  }


  public long getHospitalId() {
    return hospitalId;
  }


  public String getServerAddress() {
    return serverAddress;
  }

  public void setServerAddress(String serverAddress) {
    this.serverAddress = serverAddress;
  }


}
