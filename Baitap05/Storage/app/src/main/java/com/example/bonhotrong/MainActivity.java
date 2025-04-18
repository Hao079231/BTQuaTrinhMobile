package com.example.bonhotrong;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private TextView txtFileContent;
    private EditText editname, editpass;
    private EditText getname, getpass;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ các view từ layout
        txtFileContent = findViewById(R.id.txtFileContent);
        editname = findViewById(R.id.editName);
        editpass = findViewById(R.id.editPass);
        getname = findViewById(R.id.getName);
        getpass = findViewById(R.id.getPass);

        // Lưu và đọc file Demo.txt
        saveToFile();
        String fileContent = readFromFile();
        txtFileContent.setText("Nội dung file: " + fileContent);
    }

    private void saveToFile() {
        String fileName = "Demo.txt";
        String data = "Hello!!";

        try {
            FileOutputStream fileObj = openFileOutput(fileName, Context.MODE_PRIVATE);
            fileObj.write(data.getBytes());
            fileObj.close();
            Toast.makeText(this, "Đã lưu file thành công!", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Lỗi khi lưu file!", Toast.LENGTH_SHORT).show();
        }
    }

    private String readFromFile() {
        String fileName = "Demo.txt";
        StringBuilder stringBuilder = new StringBuilder();

        try {
            FileInputStream fileInputStream = openFileInput(fileName);
            int ch;
            while ((ch = fileInputStream.read()) != -1) {
                stringBuilder.append((char) ch);
            }
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            return "Lỗi khi đọc file!";
        }

        return stringBuilder.toString();
    }

    public void saveMe(View view) {
        File file;
        String name = editname.getText().toString();
        String password = editpass.getText().toString();

        FileOutputStream fileOutputStream = null;
        try {
            name = name + " ";
            file = getFilesDir();
            fileOutputStream = openFileOutput("Code.txt", Context.MODE_PRIVATE);
            fileOutputStream.write(name.getBytes());
            fileOutputStream.write(password.getBytes());
            Toast.makeText(this, "Đã lưu!\nĐường dẫn: " + file + "/Code.txt", Toast.LENGTH_SHORT).show();
            editname.setText("");
            editpass.setText("");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (fileOutputStream != null)
                    fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void loadMe(View view) {
        try {
            FileInputStream fileInputStream = openFileInput("Code.txt");
            int read;
            StringBuilder buffer = new StringBuilder();
            while ((read = fileInputStream.read()) != -1) {
                buffer.append((char) read);
            }
            fileInputStream.close();

            String fullText = buffer.toString();
            String name = fullText.substring(0, fullText.indexOf(" "));
            String pass = fullText.substring(fullText.indexOf(" ") + 1);

            getname.setText(name);
            getpass.setText(pass);

            Log.d("Code", "Tên: " + name + ", Mật khẩu: " + pass);
            Toast.makeText(this, "Đã tải dữ liệu", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Lỗi khi đọc dữ liệu", Toast.LENGTH_SHORT).show();
        }
    }
}
