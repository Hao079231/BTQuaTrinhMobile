package com.example.baitap01;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Cau5 extends AppCompatActivity {

    private EditText editInput;
    private Button btnProcess;
    private TextView tvResult;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cau5);  // Đảm bảo layout chính xác

        // Khởi tạo các View
        editInput = findViewById(R.id.edtInput);  // ID trong XML của EditText
        btnProcess = findViewById(R.id.btnProcess);  // ID trong XML của Button
        tvResult = findViewById(R.id.tvResult);  // ID trong XML của TextView

        // Xử lý sự kiện khi nhấn nút
        btnProcess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy chuỗi từ EditText
                String input = editInput.getText().toString().trim();

                // Kiểm tra nếu chuỗi nhập vào rỗng
                if (input.isEmpty()) {
                    Toast.makeText(Cau5.this, "Vui lòng nhập chuỗi", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Đảo ngược chuỗi
                String reversed = reverseString(input);

                // Chuyển tất cả các ký tự thành chữ in hoa
                String result = reversed.toUpperCase();

                // Hiển thị kết quả lên TextView
                tvResult.setText(result);

                // Hiển thị thông báo Toast
                Toast.makeText(Cau5.this, result, Toast.LENGTH_LONG).show();
            }
        });
    }

    // Phương thức đảo ngược chuỗi
    private String reverseString(String input) {
        String[] words = input.split(" ");  // Tách chuỗi thành các từ
        StringBuilder reversed = new StringBuilder();

        // Đảo ngược các từ trong chuỗi
        for (int i = words.length - 1; i >= 0; i--) {
            reversed.append(words[i]).append(" ");
        }

        return reversed.toString().trim();  // Loại bỏ dấu cách thừa
    }
}
