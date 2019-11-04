package vn.edu.lop1k.modulemanagejobservice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import vn.edu.lop1k.adapter.JobAdapter;

public class MainActivity extends AppCompatActivity {
    JobAdapter adapter;
    ListView lvJobList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        fakeData();

    }

    private void fakeData() {
        adapter.add(new JobAdapter(1,"Học Tiếng Anh",123,"1h23"));
    }

    private void addControls() {
        lvJobList=findViewById(R.id.lvJobList);
        adapter= new JobAdapter(MainActivity.this,R.layout.item_list);
        lvJobList.setAdapter(adapter);
    }
}
