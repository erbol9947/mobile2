package com.example.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TextView;

public class Detailed extends AppCompatActivity {

    TextView title, article;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        title = (TextView) findViewById(R.id.textView);
        article = (TextView) findViewById(R.id.textView2);

        title.setText(getIntent().getStringExtra("title"));
        article.setText(getIntent().getStringExtra("article"));
    }


}
