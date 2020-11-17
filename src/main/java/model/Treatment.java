package model;

import utils.DateConverter;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Treatments for patients are run through by caregivers.
 */
public class Treatment {
    private long tid;
    private long pid;
    private LocalDate date;
    private LocalTime begin;
    private LocalTime end;
    private String description;
    private String remarks;
    private String caregiver;


    /**
     * constructs a treatment from the given params.
     *
     * @param pid
     * @param date
     * @param begin
     * @param end
     * @param description
     * @param remarks
     * @param caregiver
     */
    public Treatment(long pid, LocalDate date, LocalTime begin,
                     LocalTime end, String description, String remarks, String caregiver) {
        this.pid = pid;
        this.date = date;
        this.begin = begin;
        this.end = end;
        this.description = description;
        this.remarks = remarks;
        this.caregiver = caregiver;
    }

    /**
     * constructs a treatment from the given params.
     *
     * @param tid
     * @param pid
     * @param date
     * @param begin
     * @param end
     * @param description
     * @param remarks
     * @param caregiver
     */
    public Treatment(long tid, long pid, LocalDate date, LocalTime begin,
                     LocalTime end, String description, String remarks, String caregiver) {
        this.tid = tid;
        this.pid = pid;
        this.date = date;
        this.date = date;
        this.begin = begin;
        this.end = end;
        this.description = description;
        this.remarks = remarks;
        this.caregiver = caregiver;
    }

    /**
     * @return treatment id
     */
    public long getTid() {
        return tid;
    }

    /**
     * @return patient id
     */
    public long getPid() {
        return this.pid;
    }

    /**
     * @return date of a treatment
     */
    public String getDate() {
        return date.toString();
    }

    /**
     * @return begin of a treatment
     */
    public String getBegin() {
        return begin.toString();
    }

    /**
     * @return end of a treatment
     */
    public String getEnd() {
        return end.toString();
    }

    /**
     * @return caregiver
     */
    public String getCaregiver() {
        return caregiver;
    }

    /**
     * convert given param to a localDate and store as new <code>s_date</code>
     *
     * @param s_date as string in the following format: YYYY-MM-DD
     */
    public void setDate(String s_date) {
        LocalDate date = DateConverter.convertStringToLocalDate(s_date);
        this.date = date;
    }

    /**
     * convert given param to a localTime and store as new <code>begin</code>
     *
     * @param begin as string in the following format: HH-MM
     */
    public void setBegin(String begin) {
        LocalTime time = DateConverter.convertStringToLocalTime(begin);
        this.begin = time;
    }

    /**
     * convert given param to a localTime and store as new <code>end</code>
     *
     * @param end as string in the following format: HH-MM
     */
    public void setEnd(String end) {
        LocalTime time = DateConverter.convertStringToLocalTime(end);
        this.end = time;
    }

    /**
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description new description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return remarks
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * @param remarks new remarks
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * @return string-representation of the treatment
     */
    public String toString() {
        return "\nBehandlung" + "\nTID: " + this.tid +
                "\nPID: " + this.pid +
                "\nDate: " + this.date +
                "\nBegin: " + this.begin +
                "\nEnd: " + this.end +
                "\nDescription: " + this.description +
                "\nRemarks: " + this.remarks +
                "\nCaregiver: " + this.caregiver + "\n";
    }
}