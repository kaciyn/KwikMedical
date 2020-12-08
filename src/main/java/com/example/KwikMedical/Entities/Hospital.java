package com.example.KwikMedical.Entities;


import java.io.Serializable;

public class Hospital  implements Serializable
{

  private int id;
  private String name;
  private String serverAddress;

  public Hospital(int id, String name, String serverAddress)
  {

    this.id = id;
    this.name = name;
    this.serverAddress = serverAddress;
  }


  public long getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getServerAddress() {
    return serverAddress;
  }

  public void setServerAddress(String serverAddress) {
    this.serverAddress = serverAddress;
  }

}
