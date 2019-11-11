package vn.edu.lop1k.model;

import android.app.TimePickerDialog;

import java.io.Serializable;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

public class Job implements Serializable {
    public int id;
    public String Name;
    public Time GioBatDau;
    public Date NgayBatDau;
    public Time GioKetThuc;
    public Date NgayKeThuc;
    public String Note;
    public  int TrangThai;

    public Job() {
    }

    public Job(int id, String name, Time gioBatDau, Date ngayBatDau, Time gioKetThuc, Date ngayKeThuc, String note, int trangThai) {
        this.id = id;
        Name = name;
        GioBatDau = gioBatDau;
        NgayBatDau = ngayBatDau;
        GioKetThuc = gioKetThuc;
        NgayKeThuc = ngayKeThuc;
        Note = note;
        TrangThai = trangThai;
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

    public Time getGioBatDau() {
        return GioBatDau;
    }

    public void setGioBatDau(Time gioBatDau) {
        GioBatDau = gioBatDau;
    }

    public Date getNgayBatDau() {
        return NgayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        NgayBatDau = ngayBatDau;
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

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int trangThai) {
        TrangThai = trangThai;
    }
}

