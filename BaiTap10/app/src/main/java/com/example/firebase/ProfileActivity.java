package com.example.firebase;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {
    private ImageView avatarImageView;
    private TextView emailTextView;
    private TextView videoCountTextView;
    private Button updateAvatarButton;
    private Button backButton;

    private final ActivityResultLauncher<Intent> pickImageLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Uri imageUri = result.getData().getData();
                    avatarImageView.setImageURI(imageUri);
                    // TODO: Upload imageUri to Firebase Storage and save URL to user profile
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        avatarImageView = findViewById(R.id.avatarImageView);
        emailTextView = findViewById(R.id.emailTextView);
        videoCountTextView = findViewById(R.id.videoCountTextView);
        updateAvatarButton = findViewById(R.id.updateAvatarButton);
        backButton = findViewById(R.id.backButton);

        // Get user info
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String email = user != null ? user.getEmail() : "Unknown";
        emailTextView.setText(email);

        // Get video count from VideoShortFireBaseActivity
        // Note: This is a simplified approach; in a real app, you'd query Firebase Firestore
        int videoCount = ((VideoShortFireBaseActivity) getIntent().getSerializableExtra("context") != null)
                ? ((VideoShortFireBaseActivity) getIntent().getSerializableExtra("context")).getUserVideoCount()
                : 0;
        videoCountTextView.setText("Videos Uploaded: " + videoCount);

        // Set up avatar update
        updateAvatarButton.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            pickImageLauncher.launch(intent);
        });

        // Set up back button
        backButton.setOnClickListener(v -> finish());
    }
}