package vn.edu.lop1k.model;

import java.io.Serializable;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

public class Job implements Serializable {
    public int id;
    public String Name;
    public Date TimeBatDau;
    public  Date TimeKeThuc;
    public  Date TimConLai;
    public String Note;

    public Job() {
    }

    public Job(int id, String name, Date timeBatDau, Date timeKeThuc, Date timConLai, String note) {
        this.id = id;
        Name = name;
        TimeBatDau = timeBatDau;
        TimeKeThuc = timeKeThuc;
        TimConLai = timConLai;
        Note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Date getTimeBatDau() {
        return TimeBatDau;
    }

    public void setTimeBatDau(Date timeBatDau) {
        TimeBatDau = timeBatDau;
    }

    public Date getTimeKeThuc() {
        return TimeKeThuc;
    }

    public void setTimeKeThuc(Date timeKeThuc) {
        TimeKeThuc = timeKeThuc;
    }

    public Date getTimConLai() {
        return TimConLai;
    }

    public void setTimConLai(Date timConLai) {
        TimConLai = timConLai;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }
}