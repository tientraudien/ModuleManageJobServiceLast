package vn.edu.lop1k.modulemanagejobservice;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import vn.edu.lop1k.adapter.JobAdapter;
import vn.edu.lop1k.model.Job;

public class MainActivity extends AppCompatActivity {
    JobAdapter adapter;
    ListView lvJobList;
    Button btnAdd;
    ArrayList<Job> arrJob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
       // fakeData();
        addEvents();



    }

    private void addEvents() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyAdd();
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
    }


    private void xuLyAdd() {
        Intent intent= new Intent(MainActivity.this,AddActivity.class);
        //intent.putExtra("N",R.layout.item_list);
        startActivityForResult(intent,111);
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
        adapter= new JobAdapter(MainActivity.this,R.layout.item_list,arrJob);
        lvJobList.setAdapter(adapter);
    }


}
