package com.nero.healthcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FindDoctor extends AppCompatActivity {
    private CardView cardBack,cardfamilyPhysician,cardDietician,cardDentist,cardSurgeon,cardCardiologists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_doctor);
        initViews();
        cardBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FindDoctor.this, HomePage.class);
                startActivity(intent);
                finish();
            }
        });
        cardfamilyPhysician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FindDoctor.this,DoctorDetails.class);
                intent.putExtra("title","Family Physician");
                startActivity(intent);
                finish();
            }
        });
        cardDietician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FindDoctor.this,DoctorDetails.class);
                intent.putExtra("title","Dietician");
                startActivity(intent);
                finish();
            }
        });
        cardDentist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FindDoctor.this,DoctorDetails.class);
                intent.putExtra("title","Dentist");
                startActivity(intent);
                finish();
            }
        });
        cardSurgeon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FindDoctor.this,DoctorDetails.class);
                intent.putExtra("title","Surgeon");
                startActivity(intent);
                finish();
            }
        });
        cardCardiologists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FindDoctor.this,DoctorDetails.class);
                intent.putExtra("title","Cardiologists");
                startActivity(intent);
                finish();
            }
        });
    }

    private void initViews() {
        cardBack=(CardView) findViewById(R.id.cardBackFindDoctor);
        cardfamilyPhysician=(CardView) findViewById(R.id.cardFamilyPhysicianFindDoctor);
        cardDietician=(CardView) findViewById(R.id.cardBuyMedicineFindDoctor);
        cardDentist=(CardView) findViewById(R.id.cardDentistFindDoctor);
        cardSurgeon=(CardView) findViewById(R.id.cardSurgeonFindDoctor);
        cardCardiologists=(CardView) findViewById(R.id.cardCardiologistsFindDoctor);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(FindDoctor.this, HomePage.class);
        startActivity(intent);
        finish();
    }
}