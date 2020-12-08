package com.example.KwikMedical;

import com.example.KwikMedical.Application.ApplicationLayer;
import com.example.KwikMedical.Data.DataLayer;
import com.example.KwikMedical.Models.Ambulance;
import com.example.KwikMedical.Models.Callout;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class HospitalApp
{
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static DataLayer dataLayer;
    static ApplicationLayer appLayer;
    static int hospitalID;

    public static void main(String[] args)
    {
        dataLayer = new DataLayer();
        appLayer = new ApplicationLayer(dataLayer);

        //default hospital id, would be set on server startup login or similar
         hospitalID = 2;

        int port = 8080;
        try {
            ServerSocket server = new ServerSocket(port);
            System.out.println("Hospital " + hospitalID + " listening for callouts  on port: " + port);

            listenForCallouts(server);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void listenForCallouts(ServerSocket server)
    {
        while (true) {
            try {

                Socket socket = server.accept();

                Thread clientHandler = new HospitalClientHandlerThread(socket,hospitalID);

                clientHandler.start();
            }
            catch (IOException ioe) {
                System.err.println("Error in I/O");
                System.err.println(ioe.getMessage());
                ioe.printStackTrace();

                break;
            }
            catch (Exception exception) {
                exception.printStackTrace();
                break;

            }
        }
        try {
            server.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    static void sendAmbulance(Ambulance ambulance, Callout callout)
    {
        var done = false;
        while (!done) {
            try {
                int port = 8080;
                var ambulanceServerAddress = ambulance.getServerAddress();

                Socket socket = new Socket();
                var socketAddress = new InetSocketAddress(ambulanceServerAddress, port);

                socket.connect(socketAddress, 30000);

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
                            done = true;
                            scanner.close();
                            objectOutputStream.close();
                            break;
                        }
                    }
                }

                System.out.println("Closing socket.");
                socket.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
