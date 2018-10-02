package rs.stajic.zdravstvenoapp;

import org.joda.time.DateTime;

public class SugarCholesterolExam extends LabExamination {

    private double value;
    private DateTime lastMealDateTime;

    public double getValue() {
        return value;
    }

    public DateTime getLastMealDateTime() {
        return lastMealDateTime;
    }

    public SugarCholesterolExam(DateTime datetime, Patient p, Doctor d) {
        super(datetime, p, d);
        this.value = 0;
        this.lastMealDateTime = null;
    }

    public void performExam(double value, DateTime lastMealDateTime, DateTime examFinishedAt) throws IllegalArgumentException {
        if (this.getD() == null) {
            throw new IllegalArgumentException("Nije dobra veza odabrani lekar-pacijent!");
        }
        this.value = value;
        this.lastMealDateTime = lastMealDateTime;
        ActivityLogger.getInstance().SugarCholesterolExamFinished(this, examFinishedAt);
    }
}
