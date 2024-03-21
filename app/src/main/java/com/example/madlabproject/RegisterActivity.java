package com.example.madlabproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Window;
public class RegisterActivity extends AppCompatActivity {
    EditText RegUsername,RegEmail,RegPassword,RegConfirm;
    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_register);
        RegUsername = findViewById(R.id.Regusername);
        RegEmail= findViewById(R.id.Regemail);
        RegPassword = findViewById(R.id.Regpassword);
        RegConfirm = findViewById(R.id.Regconfirmpassword);
        btn = findViewById(R.id.accept);
        tv=findViewById(R.id.already);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this,MainActivity.class));
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = RegUsername.getText().toString();
                String email = RegEmail.getText().toString();
                String password = RegPassword.getText().toString();
                String confirm = RegConfirm.getText().toString();
                Database db = new Database(getApplicationContext(),"ambulance",null,1);
                if(username.length()==0 || email.length()==0|| password.length()==0|| confirm.length()==0){
                    Toast.makeText(getApplicationContext(), "Please fill all details", Toast.LENGTH_SHORT).show();
                }else{
                    if(password.compareTo(confirm)==0){
                        if(isValid(password)){
                            db.register(username,email,password);

                            Toast.makeText(getApplicationContext(), "Account Created ", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this,MainActivity.class));
                        }else{
                            Toast.makeText(getApplicationContext(), " Password must contain combination of letters,numbers and symbols ", Toast.LENGTH_SHORT).show();
                        }
                    }
                    Toast.makeText(getApplicationContext(), " Password Mismatch ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public static boolean isValid(String passwordhere) {
        int f1 = 0, f2 = 0, f3 = 0;
        if (passwordhere.length() < 8) {
            return false;
        } else {
            for (int p = 0; p < passwordhere.length(); p++) {
                if (Character.isLetter(passwordhere.charAt(p))) {
                    f1 = 1;
                }
            }
            for (int r = 0; r < passwordhere.length(); r++) {
                if (Character.isDigit(passwordhere.charAt(r))) {
                    f2 = 1;
                }
            }
            for (int s = 0; s < passwordhere.length(); s++) {
                char c = passwordhere.charAt(s);
                if (c >= 33 && c <= 46 || c == 64) {
                    f3 = 1;
                }
            }
            if (f1 == 1 && f2 == 1 && f3 == 1)
                return true;
            return false;
        }
    }
}