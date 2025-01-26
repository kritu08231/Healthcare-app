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

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

public class BuyMedicineDetails extends AppCompatActivity {
    private TextView txtTotalCost,txtPackageName;
    private Button btnBack,btnAddToCart;
    private EditText edtTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine_details);
        initViews();
        Intent intent=getIntent();
        txtPackageName.setText(intent.getStringExtra("text1"));
        edtTxt.setText(intent.getStringExtra("text2"));
        txtTotalCost.setText(intent.getStringExtra("text3")+"/-");

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(BuyMedicineDetails.this, BuyMedicine.class);
                startActivity(intent);
                finish();
            }
        });
        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences= getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String Username = sharedPreferences.getString("Username","").toString();
                String product=txtPackageName.getText().toString();
                float price = Float.parseFloat(intent.getStringExtra("text3").toString());

                Database db = new Database(getApplicationContext(),"healthcare",null,1);

                if(db.checkCart(Username,product)==1){
                    Toast.makeText(getApplicationContext(),"Product Already Added",Toast.LENGTH_SHORT).show();
                }else {
                    db.addCart(Username,product,price, "medicine");
                    Toast.makeText(getApplicationContext(),"Record inserted to cart",Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(BuyMedicineDetails.this,BuyMedicine.class);
                    startActivity(intent);
                    finish();
                }
        };

    });
    }

    private void initViews() {
        txtPackageName=(TextView) findViewById(R.id.txtPackageNameBuyMedicineDetails);
        txtTotalCost=(TextView) findViewById(R.id.txtTotalCostBuyMedicineDetails);
        btnBack=(Button) findViewById(R.id.btnBackBuyMedicineDetails);
        btnAddToCart=(Button) findViewById(R.id.btnAddTocartBuyMedicineDetails);
        edtTxt=(EditText) findViewById(R.id.edtTxtBuyMedicineDetails);
        edtTxt.setKeyListener(null);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(BuyMedicineDetails.this, BuyMedicine.class);
        startActivity(intent);
        finish();
    }

}