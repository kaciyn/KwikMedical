package com.example.KwikMedical;

import com.example.KwikMedical.Entities.Callout;

import java.io.*;
import java.net.Socket;

import static com.example.KwikMedical.HospitalApp.SendAmbulance;
import static com.example.KwikMedical.HospitalApp.appLayer;

public class HospitalClientHandler extends Thread
{
    private final Socket socket;
    private final InputStream inputStream;
    private final DataOutputStream outputStream;
    private final int hospitalID;

    public HospitalClientHandler(Socket socket, InputStream inputStream, DataOutputStream outputStream, int hospitalID)
    {
        this.socket = socket;
        this.inputStream = inputStream;
        this.outputStream = outputStream;

        this.hospitalID = hospitalID;
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
                outputStream.writeUTF("Received callout request, looking for free ambulance");

                var ambulance = appLayer.getAvailableAmbulance(hospitalID);

                SendAmbulance(ambulance,callout);

                outputStream.writeUTF("Ambulance despatched. Type x to terminate connection.");

                response = objectInputStream.readUTF();

                if (response.equals("x")) {
                    System.out.println("Client " + this.socket + " disconnecting...");
                    System.out.println("Closing  connection.");
                    this.socket.close();
                    System.out.println("Connection closed");
                    break;
                }


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
