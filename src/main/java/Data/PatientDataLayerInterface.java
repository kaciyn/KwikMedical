package Data;

import Entities.Patient;

import java.util.List;

public interface PatientDataLayerInterface
{
    /**
     * Returns a Patient record from the data store
     *
     * @param registrationNumber The registrationNumber number of the Patient to get
     * @return The Patient record if it exists, null otherwise
     */
    public Patient getPatientByRegistrationNumber(String registrationNumber);

    public List<Patient> getPatientsByName(String name);

}
