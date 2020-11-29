public interface PatientApplicationLayerInterface
{
    /**
     * Adds a new Patient to the underlying Data Layer
     *
     * @param registrationNumber The Patient's registrationNumber number
     * @param name The Patient's name
     * @param programme The Patient's programme
     * @return Message indicating outcome of adding Patient
     */
    public String addPatient(String registrationNumber, String name, String programme);

    /**
     * Updates an existing Patient record
     *
     * @param registrationNumber The Patient's registrationNumber number
     * @param name The Patient's updated name
     * @param programme The Patient's updated programme
     * @return Message indicating outcome of updating the Patient
     */
    public String updatePatient(String registrationNumber, String name, String programme);

    /**
     * Gets a Patient record
     *
     * @param registrationNumber The registrationNumber number of the Patient to get
     * @return Message indicating the outcome of getting the Patient
     */
    public String getPatient(String registrationNumber);

    /**
     * Removes a Patient record
     *
     * @param registrationNumber The registrationNumber number of the Patient to remove
     * @return Message indicating the oucome of removing the Patient
     */
    public String removePatient(String registrationNumber);
}
