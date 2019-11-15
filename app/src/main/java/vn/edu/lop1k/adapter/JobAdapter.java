package vn.edu.lop1k.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import vn.edu.lop1k.model.Job;
import vn.edu.lop1k.modulemanagejobservice.AddActivity;
import vn.edu.lop1k.modulemanagejobservice.MainActivity;
import vn.edu.lop1k.modulemanagejobservice.R;

import static androidx.core.content.ContextCompat.startActivity;

public class JobAdapter extends ArrayAdapter<Job> {
    Activity context;
    int resource;
    ArrayList<Job> objects;

    public JobAdapter(@NonNull Activity context, int resource, ArrayList<Job> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       // LayoutInflater inflater=this.context.getLayoutInflater();
        LayoutInflater inflater=this.context.getLayoutInflater();
        View customView=inflater.inflate(this.resource,null);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        final Job job= this.objects.get(position);
       // TextView Id=customView.findViewById(R.id.txtId);
        TextView Name=customView.findViewById(R.id.txtName);
        TextView Note=customView.findViewById(R.id.txtNote);
        TextView NgayBatDau=customView.findViewById(R.id.txtNote);
        Button btnXoa=customView.findViewById(R.id.btnXoa);
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                xuLyXoa(job);
            }
        });



        Name.setText(job.getName());
        Note.setText(job.getNote());
        if(job.getTrangThai()==-1)
        {
            Name.setBackgroundColor(Color.RED);
        }
        if(job.getTrangThai()==0)
        {
            Name.setBackgroundColor(Color.YELLOW);
        }
        if(job.getTrangThai()==1)
        {
            Name.setBackgroundColor(Color.GREEN);
        }





       // Time.setText(dateFormat.format(job.getTime()));
        return customView;
    }

    private void xuLyXoa(Job job) {
        long kq=MainActivity.myDatabase.deleteJob(job);
        if(kq>0)
        {
            Toast.makeText(context,"Thành công",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(context,"Thất bại",Toast.LENGTH_LONG).show();
        }


        MainActivity.arrJob.clear();
        MainActivity.arrJob.addAll(MainActivity.myDatabase.getAllJob());
        notifyDataSetChanged();
    }


}
