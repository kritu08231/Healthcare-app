package com.nero.healthcare;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;

public class HomePage extends AppCompatActivity {
    private CardView cardLogout,cardLabTest,cardBuyMedicine,cardFindDoctor,cardHealthDoctor,cardOrderDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        initViews();
        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String Username = sharedPreferences.getString("Username", "").toString();


        cardLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor= sharedPreferences.edit();
                editor.clear();
                editor.apply();
                Intent intent=new Intent(HomePage.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        cardFindDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomePage.this, FindDoctor.class);
                startActivity(intent);
                finish();
            }
        });
        cardLabTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomePage.this, LabTest.class);
                startActivity(intent);
                finish();
            }
        });
        cardOrderDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomePage.this, OrderDetails.class);
                startActivity(intent);
                finish();
            }
        });
        cardBuyMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePage.this,BuyMedicine.class));
            }
        });

        cardHealthDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePage.this,HealthDoctor.class));
            }
        });
    }

    private void initViews() {
        cardLabTest=(CardView) findViewById(R.id.cardLabTestHome);
        cardBuyMedicine=(CardView) findViewById(R.id.cardBuyMedicineHome);
        cardFindDoctor=(CardView) findViewById(R.id.cardFindDoctorHome);
        cardHealthDoctor=(CardView) findViewById(R.id.cardHealthDoctorHome);
        cardOrderDetails=(CardView) findViewById(R.id.cardOrderDetailsHome);
        cardLogout=(CardView) findViewById(R.id.cardLogoutHome);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(HomePage.this, splashScreen.class);
        startActivity(intent);
        finish();
    }
}