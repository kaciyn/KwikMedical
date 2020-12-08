package com.example.KwikMedical;

import com.example.KwikMedical.Application.ApplicationLayer;
import com.example.KwikMedical.Data.DataLayer;
import com.example.KwikMedical.Models.Callout;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

import static com.example.KwikMedical.AmbulanceApp.updateCalloutInfo;

public class AmbulanceClientHandlerThread extends Thread
{
    private final Socket socket;
    private final int ambulanceId;

    public AmbulanceClientHandlerThread(Socket socket, int ambulanceId)
    {
        this.socket = socket;

        this.ambulanceId = ambulanceId;
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

            Callout callout = (Callout) objectInputStream.readObject();

            // Send message
            outputStream.writeUTF("Received callout info, en route to patient");

            //set ambulance to unavailable
            appLayer.updateAmbulanceAvailability(this.ambulanceId, false);

            updateCalloutInfo(callout);

            //set ambulance to available
            appLayer.updateAmbulanceAvailability(this.ambulanceId, true);

            outputStream.writeUTF("Callout completed & updated. Closing connection.");
            outputStream.writeUTF("done");

            // closing resources
            inputStream.close();
            outputStream.close();
            this.socket.close();
            System.out.println("Connection closed");

        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}

