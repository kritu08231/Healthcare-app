package com.nero.healthcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HealthArticlesDetails extends AppCompatActivity {
    private TextView txtTitle;
    private ImageView imageView;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_articles_details);
        initViews();

        Intent intent=getIntent();
        txtTitle.setText(intent.getStringExtra("text1"));

        Bundle bundle=getIntent().getExtras();
        if(bundle != null){
            int resId = bundle.getInt("text2");
            imageView.setImageResource(resId);
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HealthArticlesDetails.this, HealthDoctor.class));
            }
        });
    }

    private void initViews() {
        txtTitle=(TextView) findViewById(R.id.txtTitleHealthArticlesDetails);
        imageView=(ImageView) findViewById(R.id.imageViewHealthArcticlesDetails);
        btnBack=(Button) findViewById(R.id.btnBackHealthArticlesDetails);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(HealthArticlesDetails.this, HealthDoctor.class);
        startActivity(intent);
        finish();
    }
}