package vn.edu.lop1k.model;

import android.app.TimePickerDialog;

import java.io.Serializable;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

public class Job implements Serializable {
    public int id;
    public String Name;
    public Time GioKetThuc;
    public Date NgayKeThuc;
    public String Note;

    public Job() {
    }

    public Job(int id, String name, Time gioKetThuc, Date ngayKeThuc, String note) {
        this.id = id;
        Name = name;
        GioKetThuc = gioKetThuc;
        NgayKeThuc = ngayKeThuc;
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

    public Time getGioKetThuc() {
        return GioKetThuc;
    }

    public void setGioKetThuc(Time gioKetThuc) {
        GioKetThuc = gioKetThuc;
    }

    public Date getNgayKeThuc() {
        return NgayKeThuc;
    }

    public void setNgayKeThuc(Date ngayKeThuc) {
        NgayKeThuc = ngayKeThuc;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }
}

