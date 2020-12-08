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
        DataLayer dataLayer = new DataLayer();
        ApplicationLayer appLayer = new ApplicationLayer(dataLayer);



        while (true) {
            receiveCallout();
        }

    }

    static void receiveCallout()
    {
        try {
            //default ambulance id, would be set on server startup login or similar
            ambulanceID = 1;
            int port = 8070;
            ServerSocket server = new ServerSocket(port);
            
            System.out.println("Server started at address: " + server.getInetAddress() );

            // Accept an incoming client connection on the server socket
            Socket socket = server.accept();

            // get the input stream from the connected socket
            InputStream inputStream = socket.getInputStream();
            // create a DataInputStream so we can read data from it.

            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            Callout callout = (Callout) objectInputStream.readObject();

            // Send message
            outputStream.writeUTF("Received callout info, en route to patient");

            //set ambulance to unavailable
            appLayer.updateAmbulanceAvailability(ambulanceID,false);

            updateCalloutInfo(callout);


            //set ambulance to available
            appLayer.updateAmbulanceAvailability(ambulanceID,true);

            outputStream.writeUTF("Callout completed & updated. Closing connection.");
            outputStream.writeUTF("done");

            // Close sockets.  This will cause the client to exit
            socket.close();
            server.close();
        }
        catch (IOException | ClassNotFoundException ioe) {
            System.err.println("Error in I/O");
            System.err.println(ioe.getMessage());
            System.exit(-1);
        }
    }

    private static void updateCalloutInfo(Callout callout)
    {
        try {
            System.out.print("\nUpdate with further information: ");

            System.out.print("\nPatient condition: ");

            String patientCondition = input.readLine();

            System.out.print("Incident: ");

            String incident = input.readLine();

            System.out.print("Action taken: ");
            String actionTaken = input.readLine();


            appLayer.updateCalloutFromAmbulance(callout,ambulanceID,patientCondition,incident,actionTaken);

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


}
