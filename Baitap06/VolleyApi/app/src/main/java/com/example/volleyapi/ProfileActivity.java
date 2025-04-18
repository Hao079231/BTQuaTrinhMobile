package com.example.volleyapi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

public class ProfileActivity extends AppCompatActivity {
    TextView id, userName, userEmail, gender;
    Button btnLogout;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        if(SharedPrefManager.getInstance(this).isLoggedIn()){
            id = findViewById(R.id.tvId);
            userName = findViewById(R.id.tvUsername);
            userEmail = findViewById(R.id.tvEmail);
            gender = findViewById(R.id.tvGender);
            btnLogout = findViewById(R.id.btnLogout);
            imageView = findViewById(R.id.imgAvatar);
            User user = SharedPrefManager.getInstance(this).getUser();
            id.setText(String.valueOf(user.getId()));
            userEmail.setText(user.getEmail());
            gender.setText(user.getGender());
            userName.setText(user.getName());
            Glide.with(getApplicationContext()).load(user.getImages())
                    .into(imageView);
            btnLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(view.equals(btnLogout)){
                        SharedPrefManager.getInstance(getApplicationContext()).logout();
                    }
                }
            });
        }else {
            Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}