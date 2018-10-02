package rs.stajic.zdravstvenoapp;

import org.joda.time.DateTime;

public class Patient extends User {

    private String idNumber;
    private String socialSecurityNumber;
    private Doctor selectedDoctor;

    public Doctor getSelectedDoctor() {
        return this.selectedDoctor;
    }

    public Patient(String idNumber, String socialSecurityNumber, String firstname, String lastname) {
        super(firstname, lastname);
        this.idNumber = idNumber;
        this.socialSecurityNumber = socialSecurityNumber;
        this.selectedDoctor = null;
        ActivityLogger.getInstance().userCreated(this, new DateTime(System.currentTimeMillis()));
    }

    public void selectDoctor(Doctor d) {
        selectedDoctor = d;
        d.addPatient(this);
        ActivityLogger.getInstance().patientChoosesDoctor(this, d, new DateTime(System.currentTimeMillis()));
    }

}
