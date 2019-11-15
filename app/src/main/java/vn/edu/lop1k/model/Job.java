package vn.edu.lop1k.model;

import android.app.TimePickerDialog;

import java.io.Serializable;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

public class Job implements Serializable {
    public int id;
    public String Name;
    public String Note;
    // public Time GioBatDau;
    public String NgayBatDau;
    // public Time GioKetThuc;
    public String NgayKeThuc;

    public Date DayBatDau,DayKeThuc;
    public Time GioBatDau,GioKetThuc;
    public  int TrangThai;
    public boolean isEnabled;

    public Job() {
    }

    public Job(int id, String name, String note, String ngayBatDau, String ngayKeThuc, int trangThai) {
        this.id = id;
        this.Name = name;
        this.Note = note;
        this.NgayBatDau = ngayBatDau;
        this.NgayKeThuc = ngayKeThuc;
        this.TrangThai = trangThai;

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

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    public String getNgayBatDau() {
        return NgayBatDau;
    }

    public void setNgayBatDau(String ngayBatDau) {
        NgayBatDau = ngayBatDau;
    }

    public String getNgayKeThuc() {
        return NgayKeThuc;
    }

    public void setNgayKeThuc(String ngayKeThuc) {
        NgayKeThuc = ngayKeThuc;
    }

    public int getTrangThai() {
        return TrangThai;
    }


    public void setTrangThai(int trangThai) {
        TrangThai = trangThai;
    }

    public boolean isEnabled() {
        return this.isEnabled;
    }

    public void setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }





}

