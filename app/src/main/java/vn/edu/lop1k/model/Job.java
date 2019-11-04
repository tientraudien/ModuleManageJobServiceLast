package vn.edu.lop1k.model;

import java.io.Serializable;
import java.sql.Time;
import java.util.Calendar;

public class Job implements Serializable {
    public int id;
    public String Name;
    public Calendar Time;
    public String Note;

    public Job() {
    }

    public Job(int id, String name, Calendar time, String note) {
        this.id = id;
        Name = name;
        Time = time;
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

    public Calendar getTime() {
        return Time;
    }

    public void setTime(Calendar time) {
        Time = time;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }
}