package com.example.KwikMedical;

import com.example.KwikMedical.Application.ApplicationLayer;
import com.example.KwikMedical.Data.DataLayer;
import com.example.KwikMedical.Models.Callout;

import java.io.*;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Objects;
import java.util.Scanner;

public class DispatcherApp
{
    
    public static void main(String[] args)
    {
        var input = new BufferedReader(new InputStreamReader(System.in));
        var dataLayer = new DataLayer();
        var appLayer = new ApplicationLayer(dataLayer);
    
        var completed=false;
    
        while (!completed) {
            try {

//                System.out.print("\nPatient registrationNumber: ");
//                int registrationNumber = Integer.parseInt(input.readLine());
                int registrationNumber = 1;

//                System.out.print("Incident: ");
//                String incident = input.readLine();
                String incident = "Dragon";

//                System.out.print("Patient condition: ");
//                String patientCondition = input.readLine();
                String patientCondition = "Mortal";

//                System.out.print("Patient location: ");
//                String address = input.readLine();
                String address = "Lair";
                
                var patient = appLayer.getPatient(registrationNumber);
                
                var hospital = appLayer.getClosestAvailableHospital(address);
                
                int port = 8080;
                
                var hospitalServerAddress = hospital.getServerAddress();
                
                Socket socket = new Socket();
                var socketAddress = new InetSocketAddress(hospitalServerAddress, port);
                
                socket.connect(socketAddress, 10000);
                
                var callInformation = appLayer.getCallInformation();
                
                var callout = new Callout(patient.getRegistrationNumber(), incident, callInformation.getTimestamp(), callInformation.getCallLength(), address, patient.getMedicalRecord(), patientCondition);
                
                OutputStream outputStream = socket.getOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                
                System.out.println("Sending callout request");
                objectOutputStream.writeObject(callout);
                
                Scanner scanner = new Scanner(socket.getInputStream());
                
                String response = scanner.nextLine();
                
                while (true) {
                    if (response.length() != 0) {
                        System.out.println("Response: " + response);
                        if (Objects.equals(response, "no ambulances available")) {
                            System.out.println("Trying to find different hospital.");
                            break;
                        }
                        else if(Objects.equals(response, "done")){
                            System.out.println("Request completed.");
                            completed=true;
                            break;
                        }
                    }
                }
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

            }
        }
    }
}



