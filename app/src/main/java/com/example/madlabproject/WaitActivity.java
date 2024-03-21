package com.example.madlabproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.view.Window;

import java.util.ArrayList;
import java.util.HashMap;

public class WaitActivity extends AppCompatActivity {

    private String[][] health_details=
            {
                    {"First Aid", "", "", "","Click For More Details"},
                    {"Home care of COVID-19", "", "", "","Click For More Details"},
                    {"CPR Resuscitation ", "", "", "","Click For More Details"},
                    {"Bleeding", "", "", "", "Click For More Details"},
                    {"Fractures", "", "", "","Click For More Details"}
            };
    private int[] images = {
            R.drawable.health1,
            R.drawable.health2,
            R.drawable.health3,
            R.drawable.health4,
            R.drawable.health5
    };
    HashMap<String,String > item;
    ArrayList list;
    SimpleAdapter sa;
    ListView lst;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_wait);
        lst=findViewById(R.id.listView);
        list = new ArrayList();
        for(int i=0;i<health_details.length;i++) {
            item = new HashMap<String, String>();
            item.put("line1", health_details[i][0]);
            item.put("line2", health_details[i][1]);
            item.put("line3", health_details[i][2]);
            item.put("line4", health_details[i][3]);
            item.put("line5", health_details[i][4]);
            list.add(item );
        }
        sa = new SimpleAdapter(this, list,
                R.layout.multi_lines,
                new String[] { "line1","line2", "line3", "line4", "line5" },
                new int[] {R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e});
        lst.setAdapter(sa);



        lst.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(WaitActivity.this, AidDetailsActivity.class);
                it.putExtra("text1", health_details[i][0]);
                it.putExtra("text2", images[i]);
                startActivity(it);
            }
        });

    }
}