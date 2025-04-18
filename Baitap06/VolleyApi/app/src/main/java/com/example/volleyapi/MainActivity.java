package com.example.volleyapi;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText etName, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Kiểm tra nếu đã đăng nhập thì chuyển sang ProfileActivity
        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, ProfileActivity.class));
            return;
        }

        // Khởi tạo các view
        etName = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);

        // Xử lý sự kiện nhấn nút đăng nhập
        findViewById(R.id.btnLogin).setOnClickListener(view -> userLogin());
    }

    private void userLogin() {
        final String username = etName.getText().toString().trim();
        final String password = etPassword.getText().toString().trim();

        // Kiểm tra dữ liệu đầu vào
        if (TextUtils.isEmpty(username)) {
            etName.setError("Please enter your username");
            etName.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            etPassword.setError("Please enter your password");
            etPassword.requestFocus();
            return;
        }

        // Tạo yêu cầu Volley
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_LOGIN,
                response -> {
                    try {
                        JSONObject obj = new JSONObject(response);
                        if (!obj.getBoolean("error")) {
                            Toast.makeText(MainActivity.this, obj.getString("message"), Toast.LENGTH_SHORT).show();
                            JSONObject userJson = obj.getJSONObject("user");
                            User user = new User(
                                    userJson.getInt("id"),
                                    userJson.getString("username"),
                                    userJson.getString("email"),
                                    userJson.getString("gender"),
                                    userJson.getString("images")
                            );
                            SharedPrefManager.getInstance(MainActivity.this).userLogin(user);
                            finish();
                            startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                        } else {
                            Toast.makeText(MainActivity.this, obj.getString("message"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(MainActivity.this, "Lỗi phân tích dữ liệu", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> {
                    // Xử lý lỗi Volley, đảm bảo Toast không nhận giá trị null
                    String errorMessage = error.getMessage();
                    Toast.makeText(MainActivity.this,
                            errorMessage != null ? errorMessage : "Lỗi kết nối, vui lòng thử lại",
                            Toast.LENGTH_SHORT).show();
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("password", password);
                return params;
            }
        };

        // Thêm yêu cầu vào hàng đợi
        VolleySingle.getInstance(this).addToRequestQueue(stringRequest);
    }
}   