public interface CalloutApplicationLayerInterface
{
    /**
     * Adds a new Callout to the underlying Data Layer
     *
     * @param registrationNumber The Callout's registrationNumber number
     * @param name The Callout's name
     * @param programme The Callout's programme
     * @return Message indicating outcome of adding Callout
     */
    public String addCallout(String registrationNumber, String name, String programme);

    /**
     * Updates an existing Callout record
     *
     * @param registrationNumber The Callout's registrationNumber number
     * @param name The Callout's updated name
     * @param programme The Callout's updated programme
     * @return Message indicating outcome of updating the Callout
     */
    public String updateCallout(String registrationNumber, String name, String programme);

    /**
     * Gets a Callout record
     *
     * @param registrationNumber The registrationNumber number of the Callout to get
     * @return Message indicating the outcome of getting the Callout
     */
    public String getCallout(String registrationNumber);

    /**
     * Removes a Callout record
     *
     * @param registrationNumber The registrationNumber number of the Callout to remove
     * @return Message indicating the oucome of removing the Callout
     */
    public String removeCallout(String registrationNumber);
}
