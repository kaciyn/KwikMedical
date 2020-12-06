package com.example.KwikMedical.app;

import com.example.KwikMedical.Data.CalloutDataLayerInterface;
import com.example.KwikMedical.Entities.CallInformation;
import com.example.KwikMedical.Entities.Callout;

import java.sql.Timestamp;

public class DispatcherApplicationLayer implements DispatcherApplicationLayerInterface
{
    // The underlying data layer this application layer sits upon
    private CalloutDataLayerInterface dataLayer;

    /**
     * Default constructor
     *
     * @param dataLayer The data layer that this layer sits upon
     */
    public DispatcherApplicationLayer(CalloutDataLayerInterface dataLayer)
    {
        this.dataLayer = dataLayer;
    }

    public void addCallout(String patientId, String event, CallInformation callInformation, String address)
    {
        Callout callout = new Callout(Integer.parseInt(patientId), event, callInformation.getTimestamp(), callInformation.getCallLength(), address);

        dataLayer.addCallout(callout);
    }

    public Callout getCallout(String registrationNumber, CallInformation callInformation) throws Exception
    {
        Callout callout = dataLayer.getCalloutByPatientAndTime(registrationNumber, callInformation.getTimestamp());

        if (callout != null) {
            return callout;
//                    .getRegistrationNumber() + "\n" + Callout.getName() + "\n" + Callout.getAddress();
        }
        else {
            throw new Exception("No callout found");
        }
    }

    public void removeCallout(String calloutId)
    {
        dataLayer.removeCallout(calloutId);
    }

    public void updateCalloutFromAmbulance(Callout callout, String respondingAmbulanceId, String actionTaken)
    {
        callout.setRespondingAmbulanceId(Integer.parseInt(respondingAmbulanceId));
        callout.setActionTaken(actionTaken);

        dataLayer.updateCallout(callout);
    }

    //currently just returns the first hospital with a free ambulance in table, full implementation with location/maps integration would calculate distance to patient
    public int getClosestAvailableHospital(String address){
//        return dataLayer.get();
return 1;
    }

    @Override
    public CallInformation getCallInformation()
    {
        return new CallInformation(new Timestamp(System.currentTimeMillis()),20000);
    }

    @Override
    public void getClosestAvailableAmbulance()
    {

    }

    @Override
    public void adviseHelp()
    {

    }

    @Override
    public void sendCalloutRequest()
    {

    }

    @Override
    public void sendPatientRecord()
    {

    }


}
