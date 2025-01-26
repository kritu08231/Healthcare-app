package com.nero.healthcare;

import static android.app.ProgressDialog.show;


import android.app.ProgressDialog;
import android.content.Intent;
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

public class Signup extends AppCompatActivity {
    private EditText edtTxtUsername,edtTxtEmail,edtTxtPassword,edtTxtConfirmPassword;
    private Button btnRegister;
    private TextView txtLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        initViews();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Username = edtTxtUsername.getText().toString().trim();
                String email = edtTxtEmail.getText().toString().trim();
                String password = edtTxtPassword.getText().toString().trim();
                String confirmPassword = edtTxtConfirmPassword.getText().toString().trim();
                Database db = new Database(getApplicationContext(),"healthcare",null,1);

                if(Username.length()==0 || email.length()==0 || password.length()==0 ||confirmPassword.length()==0){
                    Toast.makeText(getApplicationContext(),"Please fill all details",Toast.LENGTH_SHORT).show();
                }else {
                    if(password.compareTo(confirmPassword)==0) {
                        if (isValid(password)) {
                            db.register(Username, email, password);
                            Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Signup.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Password must contain at least 8 character, having letter,digit and special character", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(getApplicationContext(),"password and confirm password didn't match",Toast.LENGTH_SHORT).show();
                        }
                    }
                }


        });


        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Signup.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public static boolean isValid(String passwordhere){
        int f1=0,f2=0,f3=0;
        if(passwordhere.length() < 8){
            return false;
        }else {
            for(int p=0;p<passwordhere.length();p++){
                if(Character.isLetter(passwordhere.charAt(p))){
                    f1=1;
                }
            }
            for(int r=0;r<passwordhere.length();r++){
                if(Character.isDigit(passwordhere.charAt(r))){
                    f2=1;
                }
            }
            for(int s =0;s<passwordhere.length();s++){
                char c=passwordhere.charAt(s);
                if(c>=33 && c<=46 || c==64){
                    f3=1;
                }
            }
            if(f1==1 && f2==1 && f3==1)
                return true;
            return false;
        }
    }
        private void initViews() {
            edtTxtUsername=(EditText) findViewById(R.id.edtTxtUsernameSignup);
            edtTxtEmail=(EditText) findViewById(R.id.edtTxtEmailSignup);
            edtTxtPassword=(EditText)findViewById(R.id.edtTxtPasswordSignup);
            edtTxtConfirmPassword=(EditText) findViewById(R.id.edtTxtConfirmPasswordSignup);
            txtLogin=(TextView) findViewById(R.id.txtLoginSignup);
            btnRegister=(Button)findViewById(R.id.btnRegisterSignup);

        }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(Signup.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}






