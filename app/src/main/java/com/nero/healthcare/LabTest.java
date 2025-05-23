package com.nero.healthcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class LabTest extends AppCompatActivity {

    private String[][] packages =
            {
                    {"Package1 : Ful Body Checkup","","","","999"},
                    {"Package2 : Blood Glucose Fasting","","","","299"},
                    {"Package3 : COVID-19 Antibody-IgG","","","","899"},
                    {"Package4 : Thyroid Check","","","","499"},
                    {"Package5 : Immunity Check","","","","699"}

            };
    private String[] package_details= {
            "Blood Glucose Fasting\n" +
                    " Complete Hemogram\n" +
                    "HbA1c\n" +
                    "Iron Studies\n" +
                    "Kidney Function Test\n" +
                    "LDH Lactate Dehydrogenase, Serum\n" +
                    "Lipid Profile\n" +
                    "Liver Function Test",
            "Blood Glucose Fasting"+
                    "Complete Hemogram"+
                    "HbA1c"+
                    "Iron Studies"+
                    "Kidney Function Test (KFT)",
            "COVID-19 Antibody - IgG"+
                    "CRP (C-Reactive Protein)"+
                    "Ferritin Test"+
                    "COVID-19 RT-PCR (Optional Add-on)",
            "Thyroid Profile-Total (T3, T4 & TSH Ultra -sensitive)"+
                    "Complete Hemogram (CBC)"+
                    "Calcium and Vitamin D"+
                    "Anti-TPO Antibody (Optional Add-on)",
            "Complete Hemogram\n" +
                    "CRP (C Reactive Protein) Quantitative, Serum\n" +
                    "Iron Studies\n" +
                    "Kiney Fnction Test\n" +
                    "Vitamin D Total-25 Hydroxy\n" +
                    "Liver Function Test\n" +
                    "Lipid Profile"
    };
    HashMap<String,String>item;
    ArrayList list;
    SimpleAdapter sa;
    private Button btnGoToCart,btnBack;
    private ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test);
        initViews();
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LabTest.this, HomePage.class);
                startActivity(intent);
                finish();
            }
        });

        list = new ArrayList();
        for(int i=0;i<packages.length;i++){
            item = new HashMap<String,String>();
            item.put("line1",packages[i][0]);
            item.put("line2", packages[i][1]);
            item.put("line3",packages[i][2]);
            item.put("line4",packages[i][3]);
            item.put("line5","Total Cost:"+packages[i][4]+"/-");
            list.add(item);
        }

        sa= new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        listview.setAdapter(sa);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(LabTest.this,LabTestDetails.class);
                it.putExtra("text1",packages[i][0]);
                it.putExtra("text2",package_details[i]);
                it.putExtra("text3",packages[i][4]);
                startActivity(it);
            }
        });
        btnGoToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LabTest.this, CartLab.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initViews() {
        btnGoToCart=(Button) findViewById(R.id.btnGoToCartLabTest);
        btnBack=(Button) findViewById(R.id.btnBackLabTest);
        listview=(ListView) findViewById(R.id.listviewLabTest);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(LabTest.this, HomePage.class);
        startActivity(intent);
        finish();
    }
}