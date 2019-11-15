package vn.edu.lop1k.modulemanagejobservice;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class TuyChinhTimeActivity extends AppCompatActivity {
    Button btnOKay, btnOKK;
    EditText edtTime;
    public int time=0;
    public int minute=2;
    public int day=0;
    public int week=0;
    public int laplai=-1;
    RadioButton radMinute,radDay,radWeek,radNo;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuy_chinh_time);
        addControls();
        addEvents();
    }
    public void TaoDialog(RadioButton rad,String tittle)
    {
        final Dialog timeDialog= new Dialog(TuyChinhTimeActivity.this);
        timeDialog.setTitle(tittle);
        timeDialog.setContentView(R.layout.tuychinhlaplai_dialog);
        timeDialog.setCanceledOnTouchOutside(false);
        btnOKay=timeDialog.findViewById(R.id.btnOKTime);
        edtTime=timeDialog.findViewById(R.id.edtTime);

        btnOKay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time=Integer.parseInt(edtTime.getText().toString());
                timeDialog.dismiss();
                if(radMinute.isChecked())
                {
                    minute=time;
                }
                else  if(radDay.isChecked())
                {
                    day=time;
                }
                else if(radWeek.isChecked())
                {
                    week=time;
                }
                else
                {
                    laplai=1;
                }
            }
        });
        timeDialog.show();
    }

    private void addEvents() {

        radMinute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TaoDialog(radMinute,"Nhập số phút");
                radMinute.isChecked();

            }
        });
        radDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TaoDialog(radDay,"Nhập số ngày");
                radDay.isChecked();
            }
        });
        radWeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TaoDialog(radWeek,"Nhập số tuần");
                radWeek.isChecked();
            }
        });
        radNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                radMinute.isChecked();
            }
        });

        btnOKK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("minute", minute);
                intent.putExtra("day", day);
                intent.putExtra("week", week);
                intent.putExtra("laplai",laplai);
                setResult(33,intent);
                finish();
            }
        });

    }

    private void addControls() {
        radMinute=findViewById(R.id.radMinute);
        radDay=findViewById(R.id.radDay);
        radWeek=findViewById(R.id.radWeek);
        radNo=findViewById(R.id.radNo);
        btnOKK=findViewById(R.id.btnOKK);
        intent=getIntent();


    }
}
