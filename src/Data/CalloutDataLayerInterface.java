package Data;

import Entities.Callout;

import java.sql.Timestamp;

public interface CalloutDataLayerInterface
{
    public void addCallout(Callout Callout);

    public Callout getCalloutByPatientAndTime(String patientId, Timestamp time);

    public void updateCallout(Callout callout);

    public void removeCallout(String id);

}

