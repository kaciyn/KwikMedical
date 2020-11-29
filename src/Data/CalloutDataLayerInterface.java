package Data;

import Entities.Callout;

public interface CalloutDataLayerInterface
{

    /**
     * Adds a new Callout to the data store
     *
     * @param Callout Callout to add to the data store
     * @return True if Callout added, false otherwise
     */
    public boolean addCallout(Callout Callout);

    /**
     * Returns a Callout record from the data store
     *
     * @param registrationNumber The registrationNumber number of the Callout to get
     * @return The Callout record if it exists, null otherwise
     */
    public Callout getCallout(String registrationNumber);

    /**
     * Removes a Callout record from the data store
     *
     * @param registrationNumber The registrationNumber number of the Callout record to remove
     * @return True if the Callout was removed, false otherwise
     */
    public boolean removeCallout(String registrationNumber);

    /**
     * Updates a Callout record in the data store
     *
     * @param registrationNumber The registrationNumber number of the Callout record to update
     * @param Callout            The new Callout record
     * @return True if the record was updated, false otherwise
     */
    public boolean updateCallout(String registrationNumber, Callout Callout);
}

