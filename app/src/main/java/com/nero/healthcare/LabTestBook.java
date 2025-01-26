package com.nero.healthcare;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LabTestBook extends AppCompatActivity {
    private EditText edtTxtFullName,edtTxtAddress,edtTxtPinCode,edtTxtContactNumber;
    private Button btnBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_book);
        initViews();

        Intent intent=getIntent();
        String[] price = intent.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));
        String date = intent.getStringExtra("date");
        String time = intent.getStringExtra("time");

        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences= getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String Username = sharedPreferences.getString("Username","").toString();

                Database db = new Database(getApplicationContext(),"healthcare",null,1);
                db.addOrder(Username,edtTxtFullName.getText().toString(),edtTxtAddress.getText().toString(),edtTxtContactNumber.getText().toString(),Integer.parseInt(edtTxtPinCode.getText().toString()),date.toString(),time.toString(),Float.parseFloat(price[1].toString()),"lab");
                db.removeCart(Username,"lab");
                Toast.makeText(getApplicationContext(),"Your booking is done successfully",Toast.LENGTH_LONG).show();
                startActivity(new Intent(LabTestBook.this,HomePage.class));
            }
        });
    }

    private void initViews() {
        edtTxtFullName=(EditText) findViewById(R.id.edtTxtFullNameLabTestBook);
        edtTxtAddress=(EditText) findViewById(R.id.edtTxtAddressLabTestBook);
        edtTxtContactNumber=(EditText) findViewById(R.id.edtTxtContactNumberLabTextBook);
        edtTxtPinCode=(EditText) findViewById(R.id.edtTxtPinCodeLabTestBook);
        btnBook=(Button) findViewById(R.id.btnBookLabTestBook);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(LabTestBook.this, CartLab.class);
        startActivity(intent);
        finish();
    }
}