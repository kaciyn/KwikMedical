package com.example.KwikMedical;

import com.example.KwikMedical.Application.ApplicationLayer;
import com.example.KwikMedical.Models.Callout;

import java.io.*;
import java.net.Socket;

import static com.example.KwikMedical.HospitalApp.sendAmbulance;
import static com.example.KwikMedical.HospitalApp.appLayer;

public class HospitalClientHandler extends Thread
{
    private ApplicationLayer applicationLayer;
    private final Socket socket;
    private final InputStream inputStream;
    private final DataOutputStream outputStream;
    private final int hospitalID;

    public HospitalClientHandler(ApplicationLayer appLayer,Socket socket, InputStream inputStream, DataOutputStream outputStream, int hospitalId)
    {
        this.applicationLayer = appLayer;
        this.socket = socket;
        this.inputStream = inputStream;
        this.outputStream = outputStream;
        this.hospitalID = hospitalId;
    }

    @Override
    public void run()
    {
        String response;
        while (true) {
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

                var callout = (Callout) objectInputStream.readObject();

                // Send message
//                outputStream.writeUTF("Received callout request, looking for free ambulance");

                var ambulance = appLayer.getAvailableAmbulance(hospitalID);

                sendAmbulance(ambulance,callout);

//                outputStream.writeUTF("Ambulance despatched. Type x to terminate connection.");

//                response = objectInputStream.readUTF();

//                if (response.equals("x")) {
                    System.out.println("Client " + this.socket + " disconnecting...");
                    System.out.println("Closing  connection.");
                    this.socket.close();
                    System.out.println("Connection closed");
                    break;
//                }


            }
            catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        try {
            // closing resources
            this.inputStream.close();
            this.outputStream.close();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

