package com.example.KwikMedical;

import com.example.KwikMedical.Application.ApplicationLayer;
import com.example.KwikMedical.Data.DataLayer;
import com.example.KwikMedical.Entities.Callout;

import java.io.*;
import java.net.Socket;

public class DispatcherApp
{

    public static void main(String[] args)
    {
        var input = new BufferedReader(new InputStreamReader(System.in));
        var dataLayer = new DataLayer();
        var appLayer = new ApplicationLayer(dataLayer);


        try {
            // Loop until programme is exited (or an exception occurs)
            while (true) {

                System.out.print("\nPatient registrationNumber: ");
                int registrationNumber = Integer.parseInt(input.readLine());

                System.out.print("Incident: ");
                String incident = input.readLine();

                System.out.print("Patient condition: ");
                String patientCondition = input.readLine();

                System.out.print("Patient location: ");
                String address = input.readLine();

                var patient = appLayer.getPatient(registrationNumber);

                var hospital = appLayer.getClosestAvailableHospital(address);

                int port = 8080;

                Socket socket = new Socket(hospital.getServerAddress(), port);

                var callInformation = appLayer.getCallInformation();

                var callout = new Callout(patient.getRegistrationNumber(), incident, callInformation.getTimestamp(), callInformation.getCallLength(), address, patient.getMedicalRecord(), patientCondition);

                OutputStream outputStream = socket.getOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

                System.out.println("Sending callout request");
                objectOutputStream.writeObject(callout);

                System.out.println("Closing socket.");
                socket.close();
            }

        }
        catch (IOException ioe) {
            System.err.println("Error in I/O");
            System.err.println(ioe.getMessage());
            System.exit(-1);
        }
    }
}



