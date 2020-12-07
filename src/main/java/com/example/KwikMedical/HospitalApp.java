package com.example.KwikMedical;

import com.example.KwikMedical.Application.ApplicationLayer;
import com.example.KwikMedical.Data.DataLayer;
import com.example.KwikMedical.Entities.Ambulance;
import com.example.KwikMedical.Entities.Callout;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class HospitalApp
{
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static DataLayer dataLayer;
    static ApplicationLayer appLayer;

    public static void main(String[] args)
    {
        DataLayer dataLayer = new DataLayer();
        ApplicationLayer appLayer = new ApplicationLayer(dataLayer);


        while (true) {
            ReceiveCallout();
        }

    }

    static void ReceiveCallout()
    {
        try {
            //default hospital id, would be set on server startup login or similar
            int hospitalID = 1;

            int port = 8080;
            ServerSocket server = new ServerSocket(port);

            // Accept an incoming client connection on the server socket
            Socket socket = server.accept();

            // get the input stream from the connected socket
            InputStream inputStream = socket.getInputStream();
            // create a DataInputStream so we can read data from it.

            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

            Thread clientHandler = new HospitalClientHandler(socket, inputStream, outputStream, hospitalID);

            clientHandler.start();

            // Close sockets.  This will cause the client to exit
            socket.close();
            server.close();
        }
        catch (IOException ioe) {
            System.err.println("Error in I/O");
            System.err.println(ioe.getMessage());
            System.exit(-1);
        }
    }


    static void SendAmbulance(Ambulance ambulance, Callout callout)
    {
        try {
            int port = 8070;

            Socket socket = new Socket(ambulance.getServerAddress(), port);

            OutputStream outputStream = socket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

            System.out.println("Sending information to ambulance");
            objectOutputStream.writeObject(callout);

            System.out.println("Closing socket.");
            socket.close();
        }
        catch (UnknownHostException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
