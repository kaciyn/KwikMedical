package Entities;

public class Patient
{
    // Attributes of Patient object
    private int registrationNumber;
    private String name;
    private String address;
    private String medicalRecord;


    public Patient(int registrationNumber, String name, String address, String medicalRecord)
    {
        this.registrationNumber = registrationNumber;
        this.name = name;
        this.address = address;
        this.medicalRecord = medicalRecord;
    }
//patient details shouldn't be editable other than the medical record hence no setters
    public int getRegistrationNumber()
    {
        return registrationNumber;
    }

    public String getName()
    {
        return name;
    }


    public String getAddress()
    {
        return address;
    }

    public String getMedicalRecord() { return medicalRecord; }

    public void setMedicalRecord(String medicalRecord)
    {
        this.medicalRecord = medicalRecord;
    }
}
