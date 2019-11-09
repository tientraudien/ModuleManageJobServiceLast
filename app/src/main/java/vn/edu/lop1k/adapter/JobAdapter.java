package vn.edu.lop1k.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

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
import vn.edu.lop1k.modulemanagejobservice.R;

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

        TextView Id=customView.findViewById(R.id.txtId);
        TextView Name=customView.findViewById(R.id.txtName);
        TextView Note=customView.findViewById(R.id.txtNote);
        TextView TimeConlai=customView.findViewById(R.id.edtTimeConLai);

        Job job= this.objects.get(position);
        Id.setText(job.getId()+"");
        Name.setText(job.getName());
        Note.setText(job.getNote());
        Date todayd=java.util.Calendar.getInstance().getTime();


        String deadline=dateFormat.format(job.getNgayKeThuc())+" "+dateFormat.format(job.getGioKetThuc());
        try {
            Date dead = dateFormat.parse(deadline);
            Date today=dateFormat.parse(dateFormat.format(todayd));
            long diff = dead.getTime() - today.getTime();

            long diffSeconds = diff / 1000;

            long diffMinutes = diff / (60 * 1000);

            long diffHours = diff / (60 * 60 * 1000);

            //int thang=Integer.parseInt(tachNgay[1]);




            TimeConlai.setText("Còn lại" + diffHours + " giờ " + diffMinutes + " phút " + diffSeconds + " giây" );
        }
        catch (ParseException ex) {
            Log.v("Exception", ex.getLocalizedMessage());
        }


       // Time.setText(dateFormat.format(job.getTime()));
        return customView;
    }
}
