package com.example.baitap01;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import java.util.ArrayList;

public class Cau4 extends AppCompatActivity {

    private static final String TAG = "Cau4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cau4); // CHÚ Ý: bạn phải có layout này

        EditText edtInput = findViewById(R.id.edtInput);
        Button btnProcess = findViewById(R.id.btnProcess);

        btnProcess.setOnClickListener(view -> {
            String input = edtInput.getText().toString().trim();

            if (input.isEmpty()) {
                Log.d(TAG, "Vui lòng nhập dữ liệu.");
                return;
            }

            String[] parts = input.split(",");
            ArrayList<Integer> numberList = new ArrayList<>();

            try {
                for (String part : parts) {
                    numberList.add(Integer.parseInt(part.trim()));
                }
            } catch (NumberFormatException e) {
                Log.d(TAG, "Lỗi định dạng số!");
                return;
            }

            ArrayList<Integer> evenList = new ArrayList<>();
            ArrayList<Integer> oddList = new ArrayList<>();

            for (int num : numberList) {
                if (num % 2 == 0) {
                    evenList.add(num);
                } else {
                    oddList.add(num);
                }
            }

            Log.d(TAG, "Các số chẵn: " + evenList);
            Log.d(TAG, "Các số lẻ: " + oddList);
        });
    }
}
