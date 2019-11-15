package vn.edu.lop1k.modulemanagejobservice;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import vn.edu.lop1k.model.Job;

public class AddActivity extends AppCompatActivity {
    MyDatabase myDatabase;
    public static String DATABASE_NAME="JobManagement.db";
    EditText edtTieuDe, edtDeadline, edtId, edtBatDau;
    EditText edtNoiDung, edtChonNgayDead, edtChonGioDead, edtCHonNgayBD, edtChonGioBD ;
    Button btnChonNgay, btnChonGio, btnOkDead;
    TextView txtTuyChinh;
    RadioButton radChuaXong, radDangLam, radDaXong, radMinute;
    Button btnSetDeadline, btnLuuCV,btnBatDau;
    private int mYear, mMonth, mDay, mHour, mMinute;
    Calendar calendar2, calendar3;
    Intent intent;
    int minute,day,week,laplai;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
    DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
    SimpleDateFormat formattertime= new SimpleDateFormat("hh:mm");

    AlarmManager alarmManager;
    PendingIntent pendingIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        addControls();
        addEvents();
    }

    private void addEvents() {
        btnLuuCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyLuu();
            }
        });
        btnSetDeadline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLySetDeadline();
            }
        });
        btnBatDau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLySetBatDau();
            }
        });
        txtTuyChinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLyLapLai();
            }
        });

    }

    private void xuLyLapLai() {
        Intent intent=new Intent(AddActivity.this,TuyChinhTimeActivity.class);
        startActivityForResult(intent,99);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 99&&resultCode==33) {

            minute = data.getIntExtra("minute", 0);
            day = data.getIntExtra("day", 0);
            week = data.getIntExtra("week", 0);
            laplai = data.getIntExtra("laplai", 0);
        }

    }



        private void xuLySetBatDau() {
        final Dialog deadDialog= new Dialog(AddActivity.this);
        deadDialog.setTitle("Set Deadline");
        deadDialog.setContentView(R.layout.itemfordialog);
        deadDialog.setCanceledOnTouchOutside(false);
        btnChonNgay=deadDialog.findViewById(R.id.btnChonNgayDead);
        btnChonGio=deadDialog.findViewById(R.id.btnChonGioDead);
        edtCHonNgayBD=deadDialog.findViewById(R.id.edtChonNgayDead);
        btnOkDead=deadDialog.findViewById(R.id.btnOkDead);
        btnOkDead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtBatDau.setText(edtCHonNgayBD.getText()+"|"+ edtChonGioBD.getText());
                deadDialog.dismiss();
            }
        });
        edtChonGioBD=deadDialog.findViewById(R.id.edtChonGioDead);

        btnChonGio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyChonGioBD();
            }
        });
        btnChonNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyChonNgayBD();
            }
        });
        deadDialog.show();

    }

    private void xuLyChonNgayBD() {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        edtCHonNgayBD.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                        calendar2.set(Calendar.YEAR,year);
                        calendar2.set(Calendar.MONTH,monthOfYear);
                        calendar2.set(Calendar.DAY_OF_MONTH,dayOfMonth);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    private void xuLyChonGioBD() {
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                        edtChonGioBD.setText(minute + ":" + hourOfDay);
                        calendar2.set(Calendar.HOUR_OF_DAY,view.getCurrentHour());
                        calendar2.set(Calendar.MINUTE,view.getCurrentMinute());

                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();


    }

    private void xuLySetDeadline( ) {
        final Dialog deadDialog= new Dialog(AddActivity.this);
        deadDialog.setTitle("Set Deadline");
        deadDialog.setContentView(R.layout.itemfordialog);
        deadDialog.setCanceledOnTouchOutside(false);
        btnChonNgay=deadDialog.findViewById(R.id.btnChonNgayDead);
        btnChonGio=deadDialog.findViewById(R.id.btnChonGioDead);
        edtChonNgayDead=deadDialog.findViewById(R.id.edtChonNgayDead);
        btnOkDead=deadDialog.findViewById(R.id.btnOkDead);
        btnOkDead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtDeadline.setText(edtChonNgayDead.getText()+"|"+ edtChonGioDead.getText());
               deadDialog.dismiss();
            }
        });
        edtChonGioDead=deadDialog.findViewById(R.id.edtChonGioDead);

        btnChonGio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyChonGioDead();
            }
        });
        btnChonNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyChonNgayDead();
            }
        });
        deadDialog.show();

    }

    private void xuLyChonGioDead() {
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                        edtChonGioDead.setText(minute + ":" + hourOfDay);
                        calendar3.set(Calendar.HOUR_OF_DAY,view.getCurrentHour());
                        calendar3.set(Calendar.MINUTE,view.getCurrentMinute());
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }

    private void xuLyChonNgayDead() {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        edtChonNgayDead.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                        calendar3.set(Calendar.YEAR,year);
                        calendar3.set(Calendar.MONTH,monthOfYear);
                        calendar3.set(Calendar.DAY_OF_MONTH,dayOfMonth);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }




    private void xuLyLuu() {
        try {
            Intent intent=getIntent();
            Intent intent1=new Intent(AddActivity.this,Receiver.class);
            alarmManager=(AlarmManager)getSystemService(ALARM_SERVICE);

        Job job1=new Job();
            job1.id=1;
            job1.Name=edtTieuDe.getText().toString();
            job1.Note=edtNoiDung.getText().toString();

            job1.NgayBatDau=edtCHonNgayBD.getText().toString()+" "+edtChonGioBD.getText().toString();
            job1.NgayKeThuc = edtChonNgayDead.getText().toString()+" "+edtChonGioDead.getText().toString();
            //job.GioKetThuc=new java.sql.Time(formattertime.parse(edtChonGioDead.getText().toString()).getTime());

            Date todayd = java.util.Calendar.getInstance().getTime();
            Date today = dateFormat.parse(dateFormat.format(todayd));

            String ngayBatDaud=edtCHonNgayBD.getText().toString();
            Date ngayBatDau=formatter.parse(ngayBatDaud);


            String ngayDeadlined=edtChonNgayDead.getText().toString();
            Date ngayDead=formatter.parse(ngayDeadlined);

            pendingIntent=PendingIntent.getBroadcast(
                    AddActivity.this,0,intent1,PendingIntent.FLAG_UPDATE_CURRENT);
            //pendingIntent=PendingIntent.getService(AddActivity.this,0,intent1,PendingIntent.FLAG_UPDATE_CURRENT);

            if(minute!=0)
            {
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(),
                        1000 * 60*minute, pendingIntent);
            }
            if(day!=0)
            {
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(),
                        AlarmManager.INTERVAL_DAY*day, pendingIntent);
            }
            if(week!=0)
            {
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(),
                        AlarmManager.INTERVAL_DAY*day*7, pendingIntent);
            }
            if(laplai==1)
            {
                alarmManager.set(AlarmManager.RTC_WAKEUP,calendar2.getTimeInMillis(),pendingIntent);
            }
            Intent cancellationIntent = new Intent(this, CancelReceiver.class);
            cancellationIntent.putExtra("key", pendingIntent);
            PendingIntent cancellationPendingIntent = PendingIntent.getBroadcast(this, 0, cancellationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar3.getTimeInMillis(), cancellationPendingIntent);



            if(today.before(ngayBatDau))
            {
                job1.TrangThai=-1;
            }
            if(today.after(ngayBatDau))
            {
                if(today.before(ngayDead)) {
                    job1.TrangThai = 0;
                }
                else
                {
                    job1.TrangThai=1;
                }
            }
            /*

           long kq= myDatabase.addJob(job1);
            if(kq>0)
            {
                Toast.makeText(AddActivity.this,"Thêm thành công",Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(AddActivity.this,"Thêm thất bại",Toast.LENGTH_LONG).show();
            }*/
            intent.putExtra("newJob", job1);
            setResult(115, intent);
            finish();
        }
        catch (ParseException ex) {
            Log.v("Exception", ex.getLocalizedMessage());
        }
    }

    private void addControls() {
        intent=getIntent();

        myDatabase=new MyDatabase(AddActivity.this,DATABASE_NAME,null,1);


        edtTieuDe=findViewById(R.id.edtTieuDe);
        edtNoiDung=findViewById(R.id.edtNoiDung);
        txtTuyChinh=findViewById(R.id.txtTuyChinh);
        edtDeadline=findViewById(R.id.edtDeadline);
        btnSetDeadline=findViewById(R.id.btnDeadline);
        radChuaXong=findViewById(R.id.radChuaLam);
        radDangLam=findViewById(R.id.radDangLam);
        radDaXong=findViewById(R.id.radDaXong);
        btnLuuCV=findViewById(R.id.btnLuuCV);
        btnBatDau=findViewById(R.id.btnBauDau);

        edtBatDau=findViewById(R.id.edtBatDau);
        alarmManager= (AlarmManager) getSystemService(ALARM_SERVICE);
        calendar2=Calendar.getInstance();
        calendar3=Calendar.getInstance();
        txtTuyChinh=findViewById(R.id.txtTuyChinh);
        radMinute=findViewById(R.id.radMinute);
    }
}
