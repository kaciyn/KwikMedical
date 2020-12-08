package com.example.KwikMedical;

import com.example.KwikMedical.Application.ApplicationLayer;
import com.example.KwikMedical.Data.DataLayer;
import com.example.KwikMedical.Models.Callout;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

import static com.example.KwikMedical.HospitalApp.sendAmbulance;
import static com.example.KwikMedical.HospitalApp.appLayer;

public class HospitalClientHandlerThread extends Thread
{
    private final Socket socket;

    private final int hospitalID;

    public HospitalClientHandlerThread(Socket socket, int hospitalId)
    {
        this.socket = socket;

        this.hospitalID = hospitalId;
    }

    @Override
    public void run()
    {
        var dataLayer = new DataLayer();
        var appLayer = new ApplicationLayer(dataLayer);

        try {

            InputStream inputStream = socket.getInputStream();

            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            var callout = (Callout) objectInputStream.readObject();

            outputStream.writeUTF("Received callout request, looking for free ambulance");

            var ambulance = appLayer.getAvailableAmbulance(hospitalID);

            if (ambulance != null) {
                sendAmbulance(ambulance, callout);
            }
            else {
                outputStream.writeUTF("no ambulances free");
                return;
            }

            outputStream.writeUTF("Ambulance dispatched.");
            outputStream.writeUTF("done");


            //confirmation message from ambulance
            var scanner = new Scanner(socket.getInputStream());
            while (true) {
                String response = scanner.nextLine();

                if (response.length() != 0) {
                    System.out.println("Response: " + response);
                    if (response.contains("done")) {
                        scanner.close();
                        break;
                    }
                }
            }

            System.out.println("Client " + this.socket + " disconnecting...");
            System.out.println("Closing  connection.");
            try {
                // closing resources
                inputStream.close();
                outputStream.close();
                this.socket.close();
                System.out.println("Connection closed");

            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }


        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }



    }

}

