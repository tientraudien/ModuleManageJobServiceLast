package vn.edu.lop1k.modulemanagejobservice;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import vn.edu.lop1k.adapter.JobAdapter;
import vn.edu.lop1k.model.Job;

public class MainActivity extends AppCompatActivity {
    public static String DATABASE_NAME="JobManagement.db";
    String DB_PATH_SUFFIX="/databases/";
    public static SQLiteDatabase database=null;
    JobAdapter adapter;
    ListView lvJobList;
    Button btnAdd;
    ArrayList<Job> arrJob;
    MyDatabase myDatabase;
    DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
    SimpleDateFormat formattertime= new SimpleDateFormat("hh:mm");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        processCopy();
        addControls();
       // fakeData();
        addEvents();
       // hienThiJob();



    }

    private void processCopy() {
        try
        {
            File dbFile=getDatabasePath(DATABASE_NAME);
            if(! dbFile.exists())
            {
                copyDatabaseFromAsset();
                Toast.makeText(MainActivity.this,"Sao chép thành công",Toast.LENGTH_LONG).show();

            }

        }
        catch (Exception ex)
        {
            Toast.makeText(MainActivity.this,ex.toString(),Toast.LENGTH_LONG).show();
            Log.e("LOI",ex.toString());
        }
    }

    private void copyDatabaseFromAsset() {
        try
        {
            InputStream myinput=getAssets().open(DATABASE_NAME);
            String outFileName=getDatabasePath();
            File f= new File(getApplicationInfo().dataDir+DB_PATH_SUFFIX);
            if(!f.exists())

                f.mkdir();

            OutputStream myoutput= new FileOutputStream(outFileName);
            byte[] buffer= new byte[1024];
            int length;
            while ((length=myinput.read(buffer))>0)
            {
                myoutput.write(buffer,0,length);
            }
            myoutput.flush();
            myoutput.close();
            myinput.close();

        }
        catch ( Exception ex)
        {
            Log.e("LOI", ex.toString());
        }
    }

    private String getDatabasePath() {
        return getApplicationInfo().dataDir+DB_PATH_SUFFIX+DATABASE_NAME;
    }

    private void hienThiJob() {
       /* try {
            database = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
            Cursor cursor = database.rawQuery("SELECT * FROM Job", null);
            adapter.clear();
            while (cursor.moveToNext()) {
                Integer id = cursor.getInt(0);
                String name = cursor.getString(1);
                String note=cursor.getString(2);
                Date NgayBatDau=formatter.parse(cursor.getString(3));
                Date NgayketThuc=formatter.parse(cursor.getString(4));
                Integer trangThai=cursor.getInt(5);
              // Time GioBatDau = NgayBatDau.getTime();



                Job job = new Job(id, name, note,NgayBatDau,NgayketThuc,trangThai);
                adapter.add(job);
            }
            cursor.close();
        }
        catch (ParseException ex) {
            Log.v("Exception", ex.getLocalizedMessage());
        }*/


    }

    private void addEvents() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyAdd();
            }
        });
        lvJobList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent= new Intent(view.getContext(), AddActivity.class);
               Bundle bundle= new Bundle();
                Job job1=adapter.getItem(position);
                bundle.putInt("y",position);
               // bundle.putSerializable("x",job1);
                //intent.putExtra("mybudle",bundle);
                intent.putExtra("x",position);
                intent.putExtra("y", job1);

                startActivityForResult(intent,1);

            }
        });



    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==111 && resultCode==115)
        {
            Job job=(Job) data.getSerializableExtra("newJob");

            arrJob.add(job);
            adapter.notifyDataSetChanged(); // dòng này để update lại adapter của bạn
            //

            Log.d("TAG", "onActivityResult: "+job.Name);
        }
        if(requestCode==1 && resultCode==2)
        {
            int pos=data.getIntExtra("y",0);
            Job job=(Job) data.getSerializableExtra("editedJob");
            arrJob.set(pos, job);
            adapter.notifyDataSetChanged();
        }
    }


    private void xuLyAdd() {
        Intent intent= new Intent(MainActivity.this,AddActivity.class);
        //intent.putExtra("N",R.layout.item_list);
        startActivityForResult(intent,111);
       // lvJobList.setOnItemClickListener(this);

        //intent.putExtra("lv",)


    }


    private void fakeData() {
        //Bundle extras = getIntent().getExtras();
       // adapter.add(extras.I);
    }

    private void addControls() {
        lvJobList=findViewById(R.id.lvJobList);
        btnAdd=findViewById(R.id.btnAdd);
        arrJob= new ArrayList<Job>();
        myDatabase=new MyDatabase(MainActivity.this,DATABASE_NAME,null,1);
        arrJob.addAll(myDatabase.getAllJob());
        adapter= new JobAdapter(MainActivity.this,R.layout.item_list,arrJob);
        lvJobList.setAdapter(adapter);

    }


}
