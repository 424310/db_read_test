package com.example.db_read_test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Next extends AppCompatActivity {

    private TextView textView1, textView2, textView3, textView4;
    private String Title, Author,ISBN, Category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        Intent intent = getIntent();

        Title = intent.getStringExtra("Title");
        Author = intent.getStringExtra("Author");
        Category = intent.getStringExtra("Category");
        ISBN = intent.getStringExtra("ISBN");

        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);
        textView4 = (TextView) findViewById(R.id.textView4);

        textView1.setText(Title);
        textView2.setText(Author);
        textView3.setText(Category);
        textView4.setText(ISBN);

    }
}
