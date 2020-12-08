package com.example.KwikMedical;

import com.example.KwikMedical.Application.ApplicationLayer;
import com.example.KwikMedical.Application.ApplicationLayerInterface;
import com.example.KwikMedical.Data.DataLayer;
import com.example.KwikMedical.Data.DataLayerInterface;
import com.example.KwikMedical.Models.Ambulance;
import com.example.KwikMedical.Models.Callout;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class HospitalApp
{
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static DataLayer dataLayer;
    static ApplicationLayer appLayer;

    public static void main(String[] args)
    {
        DataLayerInterface dataLayer = new DataLayer();
        ApplicationLayerInterface appLayer = new ApplicationLayer(dataLayer);

        try {  //default hospital id, would be set on server startup login or similar

            int port = 8080;
            ServerSocket server = new ServerSocket(port);
            System.out.println("Server started at address: " + server.getLocalSocketAddress() );

            while (true) {
                receiveCallout(server);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    static void receiveCallout(ServerSocket server) throws IOException
    {
        int hospitalID = 2;

        try {

            Socket socket = server.accept();

            InputStream inputStream = socket.getInputStream();

            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

            Thread clientHandler = new HospitalClientHandler(socket, inputStream, outputStream, hospitalID);

            clientHandler.start();
        }
        catch (IOException ioe) {
            System.err.println("Error in I/O");
            System.err.println(ioe.getMessage());
            ioe.printStackTrace();
            server.close();

        }
        catch (Exception exception) {
            exception.printStackTrace();
            server.close();

        }
    }


    static void sendAmbulance(Ambulance ambulance, Callout callout)
    {
        try {
            int port = 8080;
            var ambulanceServerAddress = ambulance.getServerAddress();

            Socket socket = new Socket();
            var socketAddress = new InetSocketAddress(ambulanceServerAddress, port);

            socket.connect(socketAddress, 60000);

            OutputStream outputStream = socket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

            System.out.println("Sending information to ambulance");
            objectOutputStream.writeObject(callout);

            var scanner = new Scanner(socket.getInputStream());


            while (true) {
                String response = scanner.nextLine();

                if (response.length() != 0) {
                    System.out.println("Response: " + response);
                    if (response.equals("done")) {
                        scanner.close();
                        objectOutputStream.close();
                        break;
                    }
                }
            }

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
