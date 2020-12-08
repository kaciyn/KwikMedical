package com.example.KwikMedical;

import com.example.KwikMedical.Application.ApplicationLayer;
import com.example.KwikMedical.Data.DataLayer;
import com.example.KwikMedical.Models.Ambulance;
import com.example.KwikMedical.Models.Callout;

import java.io.*;
import java.net.InetSocketAddress;
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
            receiveCallout();
        }

    }

    static void receiveCallout()
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

           
        }
        catch (IOException ioe) {
            System.err.println("Error in I/O");
            System.err.println(ioe.getMessage());
            ioe.printStackTrace();
        }
        catch (Exception exception){
            exception.printStackTrace();
    
        }
    }


    static void sendAmbulance(Ambulance ambulance, Callout callout)
    {
        try {
            int port = 8070;
            var ambulanceServerAddress = ambulance.getServerAddress();

            Socket socket = new Socket();
            var socketAddress = new InetSocketAddress(ambulanceServerAddress, port);

            socket.connect(socketAddress, 100000);

            OutputStream outputStream = socket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

            System.out.println("Sending information to ambulance");
            objectOutputStream.writeObject(callout);

            //TODO add wait for confirmation before closing

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
