package com.example.KwikMedical;

import com.example.KwikMedical.Application.ApplicationLayer;
import com.example.KwikMedical.Data.DataLayer;
import com.example.KwikMedical.Models.Callout;

import java.io.*;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class DispatcherApp
{

    public static void main(String[] args)
    {
        var input = new BufferedReader(new InputStreamReader(System.in));
        var dataLayer = new DataLayer();
        var appLayer = new ApplicationLayer(dataLayer);

//        while (true) {
            try {
                // Loop until programme is exited

//                System.out.print("\nPatient registrationNumber: ");
//                int registrationNumber = Integer.parseInt(input.readLine());
                int registrationNumber = 1;

//                System.out.print("Incident: ");
//                String incident = input.readLine();
                String incident = "sdfsdf";

//                System.out.print("Patient condition: ");
//                String patientCondition = input.readLine();
                String patientCondition = "sdfsdf";

//                System.out.print("Patient location: ");
//                String address = input.readLine();
                String address = "sdfsdf";

                var patient = appLayer.getPatient(registrationNumber);

                var hospital = appLayer.getClosestAvailableHospital(address);

                int port = 8080;

                var hospitalServerAddress = hospital.getServerAddress();

                Socket socket = new Socket();
                var socketAddress = new InetSocketAddress(hospitalServerAddress, port);

                socket.connect(socketAddress, 100000);

                var callInformation = appLayer.getCallInformation();

                var callout = new Callout(patient.getRegistrationNumber(), incident, callInformation.getTimestamp(), callInformation.getCallLength(), address, patient.getMedicalRecord(), patientCondition);

                OutputStream outputStream = socket.getOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

                System.out.println("Sending callout request");
                objectOutputStream.writeObject(callout);

                //TODO add wait for confirmation before closing

                System.out.println("Closing socket.");
                socket.close();


            }
            catch (SocketTimeoutException socketTimeoutException) {
                System.err.println("Socket timeout");
                System.err.println(socketTimeoutException.getMessage());
                socketTimeoutException.printStackTrace();
            }
            catch (ConnectException connectException) {
                System.err.println("Connection exception");
                System.err.println(connectException.getMessage());
                connectException.printStackTrace();
            }
            catch (IOException ioe) {
                System.err.println("Error in I/O");
                System.err.println(ioe.getMessage());
                ioe.printStackTrace();
            }
            catch (Exception exception) {
                exception.printStackTrace();

//            }
        }
    }
}



