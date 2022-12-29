package com.metalinko.fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class slider_one extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider_one);
        overridePendingTransition( R.anim.slide_in_right,R.anim.slide_out_left);

    }

    public void next(View view) {
        Intent intent = new Intent(getApplicationContext() , slider_two.class);
        startActivity(intent);
        finish();
    }
}