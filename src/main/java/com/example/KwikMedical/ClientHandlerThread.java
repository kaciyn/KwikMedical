package com.example.KwikMedical;

import com.example.KwikMedical.Application.ApplicationLayer;
import com.example.KwikMedical.Data.DataLayer;
import com.example.KwikMedical.Models.Callout;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import static com.example.KwikMedical.HospitalApp.sendAmbulance;

public  class ClientHandlerThread extends Thread
{
    private final Socket socket;

    private final int id;

    public ClientHandlerThread(Socket socket, int id)
    {
        this.socket = socket;

        this.id = id;
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

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

