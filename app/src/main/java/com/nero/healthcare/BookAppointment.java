package com.nero.healthcare;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class BookAppointment extends AppCompatActivity {

    private EditText ed1,ed2,ed3,ed4;
    private TextView tv;
    private Button btnBack,btnTime,btnDate,btnbook;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);
        initViews();
        initDatePicker();
        initTimePicker();

        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });
        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerDialog.show();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(BookAppointment.this, FindDoctor.class);
                startActivity(intent);
                finish();
            }
        });
        ed1.setKeyListener(null);
        ed2.setKeyListener(null);
        ed3.setKeyListener(null);
        ed4.setKeyListener(null);

        Intent it=getIntent();
        String title =it.getStringExtra("text1");
        String fullname =it.getStringExtra("text2");
        String address =it.getStringExtra("text3");
        String contact =it.getStringExtra("text4");
        String fees =it.getStringExtra("text5");

        tv.setText(title);
        ed1.setText(fullname);
        ed2.setText(address);
        ed3.setText(contact);
        ed4.setText("Cons Fees:"+fees+"/-");

        btnbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Database db = new Database(getApplicationContext(),"healthcare",null,1);
                SharedPreferences sharedPreferences= getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String Username = sharedPreferences.getString("Username","").toString();
                if(db.checkAppointmentExists(Username,title+" => "+fullname,address,contact,btnDate.getText().toString(),btnTime.getText().toString())==1){
                    Toast.makeText(getApplicationContext(),"Appointment Already booked",Toast.LENGTH_LONG).show();
                }else {
                    db.addOrder(Username,title+" =>" +fullname,address,contact,0,btnDate.getText().toString(),btnTime.getText().toString(),Float.parseFloat(fees),"appointment");
                    Toast.makeText(getApplicationContext(),"Your Appointment is done successfully",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(BookAppointment.this, HomePage.class));
                }
            }
        });
    }


    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener= new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1=i1+1;
                btnDate.setText(i2+"/"+i1+"/"+i);
            }
        };
        Calendar cal= Calendar.getInstance();
        int year=cal.get(Calendar.YEAR);
        int month=cal.get(Calendar.MONTH);
        int day=cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_DARK;
        datePickerDialog= new DatePickerDialog(this,style,dateSetListener,year,month,day);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis()+864000000);
    }

    private void initTimePicker() {
        TimePickerDialog.OnTimeSetListener timeSetListener= new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
               btnTime.setText(i+":"+i1);
            }
        };
        Calendar cal=Calendar.getInstance();
        int hrs =cal.get(Calendar.HOUR);
        int mins=cal.get(Calendar.MINUTE);

        int style= AlertDialog.THEME_HOLO_DARK;
        timePickerDialog=new TimePickerDialog(this,style,timeSetListener,hrs,mins,true);

    }




    private void initViews() {
        tv=(TextView) findViewById(R.id.txtAppTitleBookA);
        ed1=(EditText) findViewById(R.id.edtTxtFullNameBppkA);
        ed2=(EditText) findViewById(R.id.edtTxtAddressBookA);
        ed3=(EditText) findViewById(R.id.edtTxtContactNumberBookA);
        ed4=(EditText) findViewById(R.id.edtTxtFeesBookA);
        btnBack=(Button) findViewById(R.id.btnBackBookA);
        btnDate=(Button) findViewById(R.id.btnDateBookA);
        btnTime=(Button) findViewById(R.id.btnTimeBookA);
        btnbook=(Button) findViewById(R.id.btnBookBookA);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(BookAppointment.this, FindDoctor.class);
        startActivity(intent);
        finish();
    }
}