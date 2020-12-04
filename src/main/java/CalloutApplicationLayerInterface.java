import Entities.CallInformation;
import Entities.Callout;

public interface CalloutApplicationLayerInterface
{

    public void addCallout(String patientId, String event, CallInformation callInformation, String address);

    public Callout getCallout(String patientId, CallInformation callInformation) throws Exception;

    public void updateCallout(String calloutId);

    public void removeCallout(String calloutId);

    public int getClosestAvailableHospital();

    public void getClosestAvailableAmbulance();//for location-based pickup

    public void adviseHelp();

    public void sendCalloutRequest();

    public void sendPatientRecord();

}
