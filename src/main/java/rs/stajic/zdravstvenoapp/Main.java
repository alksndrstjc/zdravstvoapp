package rs.stajic.zdravstvenoapp;

import org.joda.time.DateTime;

public class Main {

    public static void main(String[] args) {

        Doctor doctor = new Doctor("kardiolog", "Milan", "Markovic");
        Patient patient = new Patient("135125993141", "1413124", "Dragan", "Aleksic");

        patient.selectDoctor(doctor);
        SugarCholesterolExam se = new SugarExam(new DateTime(System.currentTimeMillis()), patient, doctor);
        BloodExam be = new BloodExam(new DateTime(System.currentTimeMillis()), patient, doctor);

        try {
            se.performExam(45, HelperDateTimeString.getDateTimeFromString("2018-10-02 11:59:34"), HelperDateTimeString.getDateTimeFromString("2018-10-02 12:00:00"));
            be.performExam(120, 70, 50, HelperDateTimeString.getDateTimeFromString("2018-10-02 13:00:00"));
        } catch (IllegalArgumentException ex) {
            System.err.println("Error!");
        }
    }
}
