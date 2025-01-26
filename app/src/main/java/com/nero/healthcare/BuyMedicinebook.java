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

public class BuyMedicinebook extends AppCompatActivity {

    private EditText edtTxtFullName,edtTxtAddress,edtTxtPinCode,edtTxtContactNumber;
    private Button btnBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicinebook);
        initViews();
        Intent intent=getIntent();
        String[] price = intent.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));
        String date = intent.getStringExtra("date");

        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences= getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String Username = sharedPreferences.getString("Username","").toString();

                Database db = new Database(getApplicationContext(),"healthcare",null,1);
                db.addOrder(Username,edtTxtFullName.getText().toString(),edtTxtAddress.getText().toString(),edtTxtContactNumber.getText().toString(),Integer.parseInt(edtTxtPinCode.getText().toString()),date.toString(),"",Float.parseFloat(price[1].toString()),"medicine");
                db.removeCart(Username,"medicine");
                Toast.makeText(getApplicationContext(),"Your booking is done successfully",Toast.LENGTH_LONG).show();
                startActivity(new Intent(BuyMedicinebook.this,HomePage.class));
            }
        });

    }

    private void initViews() {
        edtTxtFullName=(EditText) findViewById(R.id.edtTxtFullNameBuyMedicineBook);
        edtTxtAddress=(EditText) findViewById(R.id.edtTxtAddressBuyMedicineBook);
        edtTxtContactNumber=(EditText) findViewById(R.id.edtTxtContactNumberBuyMedicineBook);
        edtTxtPinCode=(EditText) findViewById(R.id.edtTxtPinCodeBuyMedicineBook);
        btnBook=(Button) findViewById(R.id.btnBookBuyMedicineBook);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(BuyMedicinebook.this, BuyMedicineCart.class);
        startActivity(intent);
        finish();
    }
}