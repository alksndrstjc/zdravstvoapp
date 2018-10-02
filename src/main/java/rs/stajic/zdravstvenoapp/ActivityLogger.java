package rs.stajic.zdravstvenoapp;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import org.joda.time.DateTime;

public class ActivityLogger {

    private PrintWriter pw;
    private static final String FILE_PATH = "activity_log.txt";
    private static StringBuilder sb = new StringBuilder();

    private static ActivityLogger logger = null;

    private ActivityLogger() {
        try {
            pw = new PrintWriter(new BufferedWriter(new FileWriter(FILE_PATH)), true);
        } catch (IOException ex) {
        }
    }

    public static synchronized ActivityLogger getInstance() {
        if (logger == null) {
            logger = new ActivityLogger();
        }
        return logger;
    }

    public void userCreated(User u, DateTime createdAt) {
        String firstname = u.getFirstname();
        String log = u instanceof Doctor
                ? sb.append("[").append(HelperDateTimeString.getStringFromDateTime(createdAt)).append("] Kreiran doktor '").append(firstname).append("'").toString()
                : sb.append("[").append(HelperDateTimeString.getStringFromDateTime(createdAt)).append("] Kreiran pacijent '").append(firstname).append("'").toString();
        pw.println(log);
        sb.setLength(0);
    }

    public void patientChoosesDoctor(Patient p, Doctor d, DateTime choiceMadeAt) {
        String log = sb.append("[").append(HelperDateTimeString.getStringFromDateTime(choiceMadeAt)).append("] Pacijent '").
                append(p.getFirstname()).append("' bira doktora '").append(d.getFirstname()).append(" ").append(d.getLastname()).append("'").toString();
        pw.println(log);
        sb.setLength(0);
    }

    public void labExamScheduled(LabExamination exam) {
        String examType = "";
        if (exam instanceof BloodExam) {
            examType = "pregled krvnog pritiska ";
        } else if (exam instanceof SugarExam) {
            examType = "pregled nivoa secera u krvi ";
        } else if (exam instanceof CholesterolExam) {
            examType = "pregled nivoa holesterola u krvi ";
        }
        String docFirstname = exam.getD().getFirstname();
        String docLastname = exam.getD().getLastname();
        String patientFirstname = exam.getP().getFirstname();
        String examFinishedAt = HelperDateTimeString.getStringFromDateTime(exam.getDatetime());

        String log = sb.append("[").append(examFinishedAt).append("] Doktor '").append(docFirstname).append(" ").append(docLastname).
                append("' je zakazao ").append(examType).append("pacijentu '").append(patientFirstname).append("'").toString();

        pw.println(log);
        sb.setLength(0);
    }

    public void BloodPressureExamFinished(BloodExam be, DateTime examFinishedAt) {
        double upperBound = be.getUpperValue();
        double lowerBound = be.getLowerValue();
        int pulse = be.getPulse();
        String firstname = be.getP().getFirstname();

        String log = sb.append("[").append(examFinishedAt).append("] Pacijent '").append(firstname).
                append("' je obavio pregled krvnog pritiska.\n").append("Vrednosti: \n'").
                append("gornja vrednost: ").append(upperBound).append("\n").
                append("donja vrednost: ").append(lowerBound).append("\n").
                append("puls: ").append(pulse).append("\n").toString();

        pw.println(log);
        sb.setLength(0);
    }

    public void SugarCholesterolExamFinished(SugarCholesterolExam sce, DateTime examFinishedAt) {
        String examType = sce instanceof SugarExam ? "' je obavio pregled nivoa secera u krvi.\n" : "' je obavio pregled nivoa holesterola u krvi.\n";
        double value = sce.getValue();
        String lastMealDateTime = HelperDateTimeString.getStringFromDateTime(sce.getLastMealDateTime());
        String examFinishedAtString = HelperDateTimeString.getStringFromDateTime(examFinishedAt);
        String firstname = sce.getP().getFirstname();

        String log = sb.append("[").append(examFinishedAt).append("] Pacijent '").append(firstname).
                append(examType).append("Vrednosti: \n'").
                append("vrednost: ").append(value).append("\n").
                append("datum i vreme poslednjeg obroka: ").append(lastMealDateTime).append("\n").toString();

        pw.println(log);
        sb.setLength(0);
    }
}
