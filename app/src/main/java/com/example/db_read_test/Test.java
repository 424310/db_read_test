package com.example.db_read_test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Test extends AppCompatActivity {

    private TextView textView1, textView2, textView3;
    private String n, p, c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Intent intent = getIntent();

        n = intent.getStringExtra("n");
        p = intent.getStringExtra("p");
        c = intent.getStringExtra("c");

        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);

        textView1.setText(n);
        textView2.setText(p);
        textView3.setText(c);
    }
}
