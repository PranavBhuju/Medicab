package com.example.madlabproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.Window;

public class AidDetailsActivity extends AppCompatActivity {
    TextView tv1;
    ImageView img;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_aid_details);

        button = findViewById(R.id.buttonHADBack);
        tv1= findViewById(R.id.textViewHADtitle);
        img = findViewById(R.id.imageViewHAD);

        Intent intent=getIntent();
        tv1.setText(intent.getStringExtra("text1"));

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            int resId = bundle.getInt( "text2");
            img.setImageResource(resId);
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AidDetailsActivity.this,WaitActivity.class));
            }
        });

    }
}