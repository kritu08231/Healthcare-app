package com.nero.healthcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;

public class HealthDoctor extends AppCompatActivity {

    private String[][] health_details =
            {
                    {"Walking Daily","","","","Click More Details"},
                    {"Home Care of COVID-19","","","","Click More Details"},
                    {"Stop Smoking","","","","Click More Details"},
                    {"Menstrual Cramps","","","","Click More Details"},
                    {"Healthy Gut","","","","Click More Details"}
            };
    private int[] images = {
            R.drawable.health1,
            R.drawable.health2,
            R.drawable.health3,
            R.drawable.health4,
            R.drawable.health5,
    };
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;

    private Button btnBack;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_doctor);
        initViews();
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HealthDoctor.this, HomePage.class));
            }
        });
        list= new ArrayList();
        for(int i=0;i<health_details.length;i++){
            item= new HashMap<String,String>();
            item.put("line1",health_details[i][0]);
            item.put("line2",health_details[i][1]);
            item.put("line3",health_details[i][2]);
            item.put("line4",health_details[i][3]);
            item.put("line5","Cons Fees:"+health_details[i][4]+"/-");
            list.add(item);
        }
        sa = new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e}
        );
        listView.setAdapter(sa);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent= new Intent(HealthDoctor.this, HealthArticlesDetails.class);
                intent.putExtra("text1",health_details[i][0]);
                intent.putExtra("text2",images[i]);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initViews() {
        listView=(ListView)findViewById(R.id.listViewHealthDoctor);
        btnBack=(Button) findViewById(R.id.btnBackHealthDoctor);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(HealthDoctor.this, HomePage.class);
        startActivity(intent);
        finish();
    }
}