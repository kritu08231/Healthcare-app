package com.nero.healthcare;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private EditText edtTxtUsername, edtTxtPassword;
    private Button btnLogin;
    private TextView txtSignUp;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Username = edtTxtUsername.getText().toString().trim();
                String password = edtTxtPassword.getText().toString().trim();
                Database db = new Database(getApplicationContext(), "healthcare", null, 1);
                if (Username.length() == 0 || password.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please fill all details", Toast.LENGTH_SHORT).show();
                } else {
                    if (db.login(Username, password) == 1) {
                        Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();
                        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("Username", Username);
                        editor.apply();
                        startActivity(new Intent(LoginActivity.this, HomePage.class));
                        Toast.makeText(getApplicationContext(), "Welcome " + Username, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid Username and password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, Signup.class);
                startActivity(intent);
                finish();
            }
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(LoginActivity.this, splashScreen.class);
        startActivity(intent);
        finish();
    }

    private void initViews() {
        edtTxtUsername = (EditText) findViewById(R.id.edtTxtUsernameLogin);
        edtTxtPassword = (EditText) findViewById(R.id.edtTxtPasswordLogin);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        txtSignUp = (TextView) findViewById(R.id.txtSignUpLogin);
        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();
    }
}




