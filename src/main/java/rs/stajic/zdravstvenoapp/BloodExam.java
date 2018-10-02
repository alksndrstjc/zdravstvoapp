package rs.stajic.zdravstvenoapp;

import org.joda.time.DateTime;

public class BloodExam extends LabExamination {

    private double upperValue;
    private double lowerValue;
    private int pulse;

    public BloodExam(DateTime datetime, Patient p, Doctor d) {
        super(datetime, p, d);
        this.upperValue = 0;
        this.lowerValue = 0;
        this.pulse = 0;
    }

    public double getUpperValue() {
        return upperValue;
    }

    public double getLowerValue() {
        return lowerValue;
    }

    public int getPulse() {
        return pulse;
    }

    public void performExam(double upperValue, double lowerValue, int pulse, DateTime examFinishedAt) throws IllegalArgumentException {
        if (this.getD() == null) {
            throw new IllegalArgumentException("Nije dobra veza odabrani lekar-pacijent!");
        }
        this.upperValue = upperValue;
        this.lowerValue = lowerValue;
        this.pulse = pulse;
        ActivityLogger.getInstance().BloodPressureExamFinished(this, examFinishedAt);
    }

}
