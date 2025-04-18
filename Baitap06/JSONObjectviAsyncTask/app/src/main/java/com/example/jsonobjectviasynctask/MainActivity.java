package com.example.jsonobjectviasynctask;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Tìm TextView trong layout
        txtResult = findViewById(R.id.txtResult);

        // Gọi AsyncTask để tải dữ liệu từ URL
        new ReadJSONObject(txtResult).execute("https://jsonplaceholder.typicode.com/todos");
    }
}
