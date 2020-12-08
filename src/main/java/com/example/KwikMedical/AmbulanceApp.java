package com.example.KwikMedical;

import com.example.KwikMedical.Application.ApplicationLayer;
import com.example.KwikMedical.Data.DataLayer;
import com.example.KwikMedical.Models.Callout;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class AmbulanceApp
{
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static DataLayer dataLayer;
    static ApplicationLayer appLayer;
    static int ambulanceID;

    public static void main(String[] args)
    {
        dataLayer = new DataLayer();
        appLayer = new ApplicationLayer(dataLayer);

        int port = 8080;

        try {
            ServerSocket server = new ServerSocket(port);
            System.out.println("Server started at address: " + server.getLocalSocketAddress());

            listenForCallouts(server);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    static void listenForCallouts(ServerSocket server)
    {
        while (true) {
            try {
                //default ambulance id, would be set on server startup login or similar
                ambulanceID = 3;


                // Accept an incoming client connection on the server socket
                Socket socket = server.accept();

                // get the input stream from the connected socket
                InputStream inputStream = socket.getInputStream();
                // create a DataInputStream so we can read data from it.

                DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

                Thread clientHandler = new HospitalClientHandlerThread(socket, ambulanceID);

                clientHandler.start();

            }
            catch (IOException ioe) {
                System.err.println("Error in I/O");
                System.err.println(ioe.getMessage());
                System.exit(-1);
            }
        }
    }

    public static void updateCalloutInfo(Callout callout)
    {
        try {
            System.out.print("\nUpdate with further information: ");

            System.out.print("\nPatient condition: ");

            String patientCondition = input.readLine();

            System.out.print("Incident: ");

            String incident = input.readLine();

            System.out.print("Action taken: ");
            String actionTaken = input.readLine();


            appLayer.updateCalloutFromAmbulance(callout, ambulanceID, patientCondition, incident, actionTaken);

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


}
