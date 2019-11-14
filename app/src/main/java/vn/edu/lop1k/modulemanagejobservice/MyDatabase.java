package vn.edu.lop1k.modulemanagejobservice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import vn.edu.lop1k.model.Job;

import static vn.edu.lop1k.modulemanagejobservice.MainActivity.DATABASE_NAME;

public class MyDatabase extends SQLiteOpenHelper {
    DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");


    public MyDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context,DATABASE_NAME,null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public List<Job> getAllJob() {
        List<Job> Jobs = new ArrayList<>();
            SQLiteDatabase database = getWritableDatabase();
            Cursor cursor = database.rawQuery("SELECT * FROM Job", null);
            while (cursor.moveToNext()) {
                Integer id = cursor.getInt(0);
                String name = cursor.getString(1);
                String note = cursor.getString(2);
                String NgayBatDau = cursor.getString(3);
                String NgayketThuc = cursor.getString(4);
                Integer trangThai = cursor.getInt(5);
                // Time GioBatDau = NgayBatDau.getTime();
                Job job = new Job(id, name, note, NgayBatDau, NgayketThuc, trangThai);
                Jobs.add(job);
            }

        return Jobs;


}
    public long addJob(Job job){
        SQLiteDatabase database=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("Id",job.id);
        values.put("Name",job.Name);
        values.put("Note",job.Note);
        values.put("NgayBatDau",job.NgayBatDau);
        values.put("NgayKetThuc",job.NgayKeThuc);
        values.put("TrangThai",job.TrangThai);
       long kq= database.insert("Job",null,values);

       return  kq;

    }
}
