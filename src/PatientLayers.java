import Data.PatientDataLayer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PatientLayers
{
	// As other methods will be accessing these, make them globally accessible
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static PatientApplicationLayer appLayer;
	
	public static void main(String[] args)
	{
		// Create the data layer
		PatientDataLayer dataLayer = new PatientDataLayer();
		// Create the application layer, passing in the data layer
		appLayer = new PatientApplicationLayer(dataLayer);
		try
		{
			// Loop until programme is exited (or an exception occurs)
			while (true)
			{
				// Display the user menu
				displayMenu();
				// Get the user input
				int choice = Integer.parseInt(input.readLine());
				// Behave based on the user input
				switch (choice)
				{
					case 1:
						// 1 selected, add a new Patient
						addPatient();
						break;
					case 2:
						// 2 selected, get an existing Patient
						getPatient();
						break;
					case 3:
						// 3 selected, update an existing Patient
						updatePatient();
						break;
					case 4:
						// 4 selected, remove an existing Patient
						removePatient();
						break;
					default:
						// Another value was entered.  Display error message
						System.out.println("\nInvalid choice");
						break;
				}
				System.out.println();
			}
		}
		catch (IOException ioe)
		{
			System.err.println("Error in I/O");
			System.err.println(ioe.getMessage());
			System.exit(-1);
		}
	}
	
	/**
	 * Displays the user menu
	 */
	private static void displayMenu()
	{
		System.out.println("Patient Database");
		System.out.println("----------------");
		System.out.println("1) Add Patient");
		System.out.println("2) Get Patient Details");
		System.out.println("3) Update Patient Details");
		System.out.println("4) Remove Patient");
		System.out.print("\nEnter choice: ");
	}
	
	/**
	 * Adds a new Patient record
	 * 
	 * @throws IOException Thrown if a problem occurs during keyboard I/O
	 */
	private static void addPatient() throws IOException
	{
		// Prompt and get all the Patient details
		System.out.print("\nPatient registrationNumber: ");
		String registrationNumber = input.readLine();
		System.out.print("Patient name: ");
		String name = input.readLine();
		System.out.print("Patient programme: ");
		String programme = input.readLine();
		// Get the result from trying to add the Patient record to the app layer
		String result = appLayer.addPatient(registrationNumber, name, programme);
		// Display result
		System.out.println("\n" + result);
	}
	
	/**
	 * Gets an existing Patient record
	 * 
	 * @throws IOException Thrown if a problem occurs during keyboard I/O
	 */
	private static void getPatient() throws IOException
	{
		// Prompt and get the Patient's registrationNumber number
		System.out.print("\nPatient registrationNumber: ");
		String registrationNumber = input.readLine();
		// Get result from trying to retrieve the Patient record
		String result = appLayer.getPatient(registrationNumber);
		// Display result
		System.out.println("\n" + result);
	}
	
	/**
	 * Updates an existing Patient record
	 * 
	 * @throws IOException Thrown if a problem occurs during keyboard I/O
	 */
	private static void updatePatient() throws IOException
	{
		// Prompt and get the Patient's registrationNumber number
		System.out.print("\nPatient registrationNumber: ");
		String registrationNumber = input.readLine();
		// Prompt and get the new Patient's details
		System.out.print("New Patient name: ");
		String name = input.readLine();
		System.out.print("New Patient programme: ");
		String programme = input.readLine();
		// Get result from trying to update the Patient record
		String result = appLayer.updatePatient(registrationNumber, name, programme);
		// Display result
		System.out.println("\n" + result);
	}
	
	/**
	 * Removes an existing Patient record
	 * 
	 * @throws IOException Thrown if a problem occurs during keyboard I/O
	 */
	private static void removePatient() throws IOException
	{
		// Prompt and get Patient's registrationNumber number
		System.out.print("\nPatient registrationNumber: ");
		String registrationNumber = input.readLine();
		// Get result from trying to remove the Patient record
		String result = appLayer.removePatient(registrationNumber);
		// Display the result
		System.out.println("\n" + result);
	}
}
