package rs.stajic.zdravstvenoapp;

import org.joda.time.DateTime;

public class CholesterolExam extends SugarCholesterolExam {

    public CholesterolExam(DateTime datetime, Patient p, Doctor d) {
        super(datetime, p, d);
    }

}
