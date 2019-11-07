package vn.edu.lop1k.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import vn.edu.lop1k.model.Job;
import vn.edu.lop1k.modulemanagejobservice.R;

public class JobAdapter extends ArrayAdapter<Job> {
    Activity context;
    int resource;
    List<Job> objects;

    public JobAdapter(@NonNull Activity context, int resource, List<Job> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       // LayoutInflater inflater=this.context.getLayoutInflater();
        View customView=this.context.getLayoutInflater().inflate(this.resource, null);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        TextView Id=customView.findViewById(R.id.txtId);
        TextView Name=customView.findViewById(R.id.txtName);
        TextView Note=customView.findViewById(R.id.txtNote);
        EditText TimeConlai=customView.findViewById(R.id.edtTimeConLai);

        Job job= this.objects.get(position);
        Id.setText(job.getId());
        Name.setText(job.getName());
        Note.setText(job.getNote());
        TimeConlai.setText(dateFormat.format(job.getTimConLai()));

       // Time.setText(dateFormat.format(job.getTime()));
        return customView;
    }
}
