package rs.stajic.zdravstvenoapp;

import org.joda.time.DateTime;

public class SugarExam extends SugarCholesterolExam {

    public SugarExam(DateTime datetime, Patient p, Doctor d) {
        super(datetime, p, d);
    }
}
