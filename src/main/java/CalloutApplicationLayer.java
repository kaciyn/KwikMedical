import Data.CalloutDataLayerInterface;
import Entities.CallInformation;
import Entities.Callout;

import java.sql.Timestamp;

public class CalloutApplicationLayer implements CalloutApplicationLayerInterface
{
    // The underlying data layer this application layer sits upon
    private CalloutDataLayerInterface dataLayer;

    /**
     * Default constructor
     *
     * @param dataLayer The data layer that this layer sits upon
     */
    public CalloutApplicationLayer(CalloutDataLayerInterface dataLayer)
    {
        this.dataLayer = dataLayer;
    }

    public void addCallout(String patientId, String event, CallInformation callInformation, String address)
    {
        Callout Callout = new Callout(Integer.parseInt(patientId), event, callInformation.getTimestamp(), callInformation.getCallLength(), address);

        dataLayer.addCallout(Callout);
    }

    public Callout getCallout(String registrationNumber, CallInformation callInformation) throws Exception
    {
        Callout Callout = dataLayer.getCalloutByPatientAndTime(registrationNumber, callInformation.getTimestamp());

        if (Callout != null) {
            return Callout;
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

    public void sendPatientRecord(){
        //TODO implement
    }



    @Override
    public void updateCallout(String calloutId)
    {

    }



    @Override
    public int getClosestAvailableHospital()
    {
        return 0;
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


}
