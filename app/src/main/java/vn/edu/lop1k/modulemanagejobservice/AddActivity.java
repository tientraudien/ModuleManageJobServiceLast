package vn.edu.lop1k.modulemanagejobservice;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import vn.edu.lop1k.model.Job;

public class AddActivity extends AppCompatActivity {

    EditText edtTieuDe, edtDeadline, edtId, edtBatDau;
    EditText edtNoiDung, edtChonNgayDead, edtChonGioDead, edtCHonNgayBD, edtChonGioBD ;
    Button btnChonNgay, btnChonGio, btnOkDead;
    TextView txtTuyChinh;
    RadioButton radChuaXong, radDangLam, radDaXong;
    Button btnSetDeadline, btnLuuCV,btnBatDau;
    private int mYear, mMonth, mDay, mHour, mMinute;
    Calendar calendar2=Calendar.getInstance();
    //SimpleDateFormat spdf1=new SimpleDateFormat("HH:mm");
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
    DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
    SimpleDateFormat formattertime= new SimpleDateFormat("hh:mm");

    AlarmManager alarmManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnSetDeadline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyDatGioBatDau();
            }
        });
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

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }




    private void xuLyLuu() {
        try {

        Intent intent=getIntent();

        Job job=new Job();
        job.id=1;
        job.Name=edtTieuDe.getText().toString();

            job.NgayKeThuc = formatter.parse(edtChonNgayDead.getText().toString());
            job.GioKetThuc=new java.sql.Time(formattertime.parse(edtChonGioDead.getText().toString()).getTime());

            Date todayd = java.util.Calendar.getInstance().getTime();
            Date today = dateFormat.parse(dateFormat.format(todayd));
            String ngayBatDaud=edtCHonNgayBD.getText().toString();
            Date ngayBatDau=formatter.parse(ngayBatDaud);
            String ngayDeadlined=edtChonNgayDead.getText().toString();
            Date ngayDead=formatter.parse(ngayDeadlined);
            if(today.before(ngayBatDau))
            {
                job.TrangThai=-1;
            }
            if(today.after(ngayBatDau))
            {
                if(today.before(ngayDead)) {
                    job.TrangThai = 0;
                }
                else
                {
                    job.TrangThai=1;
                }
            }
          job.Note = edtNoiDung.getText().toString();
            intent.putExtra("newJob", job);

            setResult(115, intent);
            finish();
        }
        catch (ParseException ex) {
            Log.v("Exception", ex.getLocalizedMessage());
        }




    }

    private Date xuLyDatGioBatDau() {
        TimePickerDialog.OnTimeSetListener callBack=new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                calendar2.set(Calendar.HOUR_OF_DAY,hourOfDay);
                calendar2.set(Calendar.MINUTE,minute);
               // edtGioBatDau.setText(spdf1.format(calendar2.getTime()));
            }
        };
        TimePickerDialog timePickerDialog=new TimePickerDialog(AddActivity.this,callBack,calendar2.get(Calendar.HOUR_OF_DAY),calendar2.get(Calendar.MINUTE),true);
        timePickerDialog.show();
        edtDeadline.setText(dateFormat.format(calendar2.getTime()));
        return  calendar2.getTime();
    }

    private void addControls() {
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

    }
}
