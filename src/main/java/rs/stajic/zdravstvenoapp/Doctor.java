package rs.stajic.zdravstvenoapp;

import java.util.ArrayList;
import java.util.List;

public class Doctor extends User {

    private String specialization;
    private List<Patient> patients;

    public Doctor(String specialization, String firstname, String lastname) {
        super(firstname, lastname);
        this.specialization = specialization;
        this.patients = new ArrayList<>();
    }

    public void addPatient(Patient p) {
        if (!patients.contains(p)) {
            patients.add(p);
        }
    }

    public void removePatients(Patient p) {
        if (patients.contains(p)) {
            patients.remove(p);
        }
    }

    @Override
    public boolean equals(Object that) {
        if (that instanceof Doctor && that != null) {
            Doctor thatz = (Doctor) that;
            return this.getFirstname().equals(thatz.getFirstname()) && this.getLastname().equals(thatz.getLastname());
        }
        return false;
    }

}
