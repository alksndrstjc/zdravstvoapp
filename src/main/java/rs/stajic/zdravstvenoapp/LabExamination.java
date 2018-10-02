package rs.stajic.zdravstvenoapp;

import org.joda.time.DateTime;

public abstract class LabExamination {

    private DateTime datetime;
    private Patient p;
    private Doctor d;

    public Patient getP() {
        return p;
    }

    public Doctor getD() {
        return d;
    }

    public LabExamination(DateTime datetime, Patient p, Doctor d) {
        if (p.getSelectedDoctor().equals(d)) {
            this.p = p;
            this.d = d;
            ActivityLogger.getInstance().labExamScheduled(this);
        } else {
            this.p = null;
            this.d = null;
        }
    }

    public DateTime getDatetime() {
        return datetime;
    }

}
