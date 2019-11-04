package vn.edu.lop1k.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import vn.edu.lop1k.model.Job;
import vn.edu.lop1k.modulemanagejobservice.R;

public class JobAdapter extends ArrayAdapter {
    Activity context;
    int resource;
    public JobAdapter(@NonNull Activity context, int resource) {
        super(context, resource);
        this.context=context;
        this.resource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=this.context.getLayoutInflater();
        View customView=inflater.inflate(this.resource,null);
        TextView Id=customView.findViewById(R.id.txtId);
        TextView Name=customView.findViewById(R.id.txtName);
        TextView Note=customView.findViewById(R.id.txtNote);
        EditText Time=customView.findViewById(R.id.edtTime);
        Job job= (vn.edu.lop1k.model.Job) getItem(position);
        Id.setText(job.getId());
        Name.setText(job.getName());
        Note.setText(job.getNote());
        //Time.setText();


        return customView;
    }
}
