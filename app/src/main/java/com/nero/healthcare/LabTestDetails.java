package com.nero.healthcare;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LabTestDetails extends AppCompatActivity {

    private TextView txtPackageName,txtTotalCost;
    private EditText edtDetails;
    private Button btnAddToCart,btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_details);
        initViews();

        edtDetails.setKeyListener(null);

        Intent intent=getIntent();
        txtPackageName.setText(intent.getStringExtra("text1"));
        edtDetails.setText(intent.getStringExtra("text2"));
        txtTotalCost.setText(intent.getStringExtra("text3")+"/-");

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(LabTestDetails.this,LabTest.class);
                startActivity(intent);
                finish();
            }
        });
        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences= getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String Username = sharedPreferences.getString("Username","").toString();
                String product = txtPackageName.getText().toString();
                float price = Float.parseFloat(intent.getStringExtra("text3").toString());

                Database db = new Database(getApplicationContext(),"healthcare",null,1);

                if(db.checkCart(Username,product)==1){
                    Toast.makeText(getApplicationContext(),"Product Already Added",Toast.LENGTH_SHORT).show();
                }else {
                    db.addCart(Username,product,price, "lab");
                    Toast.makeText(getApplicationContext(),"Record inserted to cart",Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(LabTestDetails.this,LabTest.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }

    private void initViews() {
        txtPackageName=(TextView) findViewById(R.id.txtPackageNameBuyMedicineDetails);
        txtTotalCost=(TextView) findViewById(R.id.txtTotalCostLabTestDetails);
        edtDetails=(EditText) findViewById(R.id.edtDetailsLabTestDetails);
        btnAddToCart=(Button) findViewById(R.id.btnAddToCartLabTestDetails);
        btnBack=(Button) findViewById(R.id.btnBackLabTestDetails);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(LabTestDetails.this, LabTest.class);
        startActivity(intent);
        finish();
    }
}