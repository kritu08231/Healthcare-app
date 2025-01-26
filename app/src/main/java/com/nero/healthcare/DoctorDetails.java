package com.nero.healthcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetails extends AppCompatActivity {
    private String[][] doctor_details1=
            {
                    {"Doctor Name:Dr. Sanjay Ramteke","Hospital Address:Dhantoli","Exp:27yrs","Mobile No:9518951959","1000"},
                    {"Doctor Name:Dr. Jaspal Arneja","Hospital Address:Ramdaspeth.","Exp:33yrs","Mobile No:0712-6661800 ","900"},
                    {"Doctor Name:Dr. Tushar Pande","Hospital Address:Manish Nagar.","Exp:28yrs","Mobile No:7126464139","500"},
                    {"Doctor Name:Dr. Vijay Agrawal","Hospital Address:Akruti Hospital, Ring Road.","Exp:27yrs","Mobile No:0712-2222222","600"},
                    {"Doctor Name:Dr. Pranit Khandait","Hospital Address:Hanuman Nagar","Exp:8yrs","Mobile No:7126464139","800"}
            };
    private String[][] doctor_details2=
            {
                    {"Doctor Name:Meghana Kumare","Hospital Address:Dharampeth","Exp:9yrs","Mobile No:09673523660","1000"},
                    {"Doctor Name:Sneha Fafat","Hospital Address:Sitabuldi,","Exp:18yrs","Mobile No:09890601345","1000"},
                    {"Doctor Name:Ms.Neha Indorkar","Hospital Address:Indora","Exp:8yrs","Mobile No:8421008103","500"},
                    {"Doctor Name:Suneeti Khandekar","Hospital Address:Ravi Nagar","Exp:20yrs","Mobile No:08379869168","2000"},
                    {"Doctor Name:Rupshree Mayanil","Hospital Address:Ramdaspeth,","Exp:15yrs","Mobile No: 9884555001","500"}
            };
    private String[][] doctor_details3=
            {
                    {"Doctor Name:Dr. Shashwat Magarkar","Hospital Address: Dhantoli","Exp:18yrs","Mobile No:08605071200","500"},
                    {"Doctor Name:Dr. Rashmi Gupta","Hospital Address:Dharampeth","Exp:27yrs","Mobile No:09049132663","400"},
                    {"Doctor Name:Dr. Sanjog Chandak","Hospital Address:Dhantoli","Exp:20yrs","Mobile No:09158886642","200"},
                    {"Doctor Name:Dr. Piyush Kothari","Hospital Address:Dhantoli","Exp:23yrs","Mobile No:09822702717","300"},
                    {"Doctor Name:Dr. Mukesh Chandak","Hospital Address:Gandhibagh.","Exp:29yrs","Mobile No:07122771911","400"}
            };
    private String[][] doctor_details4=
            {
                    {"Doctor Name:Dr. Jitendra Mehta","Hospital Address:Ramdaspeth","Exp:45yrs","Mobile No:07122445755","1000"},
                    {"Doctor Name:Dr. Yogesh Saboo","Hospital Address:Central Avenue Road","Exp:42yrs","Mobile No:09909965559","700"},
                    {"Doctor Name:Dr. Rajesh Singhania","Hospital Address:Katol Road","Exp:30yrs","Mobile No:09823092208","400"},
                    {"Doctor Name:Dr. Nilesh Junankar","Hospital Address:Ambazari Road","Exp:35yrs","Mobile No:07775039977","400"},
                    {"Doctor Name:Dr. Sunil Ramji Pandey","Hospital Address:Telephone Exchange ","Exp:13yrs","Mobile No:08055997799","500"}
            };
    private String[][] doctor_details5=
            {
                    {"Doctor Name:Dr. Sanjay Gidhwani","Hospital Address:Manewada","Exp:19yrs","Mobile No:07702035544","600"},
                    {"Doctor Name:Dr. Sachin Patil","Hospital Address:Wardha Road","Exp:16yrs","Mobile No:09930480570","600"},
                    {"Doctor Name:Dr. Chetan Rathi","Hospital Address:Ramdaspeth","Exp:13yrs","Mobile No:09028669543","800"},
                    {"Doctor Name:Dr. Kshitij Dadhe","Hospital Address:Dhantoli","Exp:12yrs","Mobile No: 8999851480","750"},
                    {"Doctor Name:Dr. Nitin Tiwari","Hospital Address:Ambazari Road","Exp:27yrs","Mobile No:08605604444","1000"}
            };
    TextView tv;
    private Button btnBack;
    String[][] doctor_details={};
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        tv=findViewById(R.id.txtTitleDoctorDetails);
        btnBack=(Button) findViewById(R.id.btnBackDoctorDetails);

        Intent intent=getIntent();
        String title= intent.getStringExtra("title");
        tv.setText(title);

        if(title.compareTo("Family Physician")==0)
            doctor_details=doctor_details1;
        else
        if(title.compareTo("Dietician")==0)
            doctor_details=doctor_details2;
        else
        if(title.compareTo("Dentist")==0)
            doctor_details=doctor_details3;
        else
        if(title.compareTo("Surgeon")==0)
            doctor_details=doctor_details4;
        else
            doctor_details=doctor_details5;



        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DoctorDetails.this, FindDoctor.class);
                startActivity(intent);
                finish();
            }
        });
        list= new ArrayList();
        for(int i=0;i<doctor_details.length;i++){
            item= new HashMap<String,String>();
            item.put("line1",doctor_details[i][0]);
            item.put("line2",doctor_details[i][1]);
            item.put("line3",doctor_details[i][2]);
            item.put("line4",doctor_details[i][3]);
            item.put("line5","Cons Fees:"+doctor_details[i][4]+"/-");
            list.add(item);
        }
        sa = new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e}
        );
        ListView lst=findViewById(R.id.listViewDoctordetsils);
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent= new Intent(DoctorDetails.this, BookAppointment.class);
                intent.putExtra("text1",title);
                intent.putExtra("text2",doctor_details[i][0]);
                intent.putExtra("text3",doctor_details[i][1]);
                intent.putExtra("text4",doctor_details[i][3]);
                intent.putExtra("text5",doctor_details[i][4]);
                startActivity(intent);
                finish();
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(DoctorDetails.this, FindDoctor.class);
        startActivity(intent);
        finish();
    }
}