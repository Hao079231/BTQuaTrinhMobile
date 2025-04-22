package com.example.firebase;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;

import java.util.HashMap;
import java.util.Map;

public class UploadVideoActivity extends AppCompatActivity {

    private Button btnSelectVideo, btnUploadVideo, btnBack;
    private EditText editVideoTitle, editVideoDescription;
    private ProgressBar uploadProgressBar;
    private Uri videoUri;
    private boolean isCloudinaryInitialized = false;

    private final ActivityResultLauncher<Intent> videoPickerLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    videoUri = result.getData().getData();
                    btnUploadVideo.setEnabled(true);
                    Toast.makeText(this, "Video selected", Toast.LENGTH_SHORT).show();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_video);

        btnSelectVideo = findViewById(R.id.btnSelectVideo);
        btnUploadVideo = findViewById(R.id.btnUploadVideo);
        btnBack = findViewById(R.id.btnBack);
        editVideoTitle = findViewById(R.id.editVideoTitle);
        editVideoDescription = findViewById(R.id.editVideoDescription);
        uploadProgressBar = findViewById(R.id.uploadProgressBar);

        if (uploadProgressBar == null) {
            Log.e("UploadVideoActivity", "ProgressBar with ID uploadProgressBar not found in layout");
        }

        btnUploadVideo.setEnabled(false); // Disable upload button until a video is selected

        // Initialize Cloudinary
        try {
            Map config = new HashMap();
            config.put("cloud_name", "dmgy0rmjw"); // Replace with your Cloudinary cloud name
            config.put("api_key", "837512297995526"); // Replace with your Cloudinary API key
            config.put("api_secret", "_rTfRBXCi7E4ljh-lp63e7L_gCI"); // Replace with your Cloudinary API secret
            MediaManager.init(this, config);
            isCloudinaryInitialized = true;
        } catch (Exception e) {
            Toast.makeText(this, "Cloudinary initialization failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        // Select video button
        btnSelectVideo.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("video/*");
            videoPickerLauncher.launch(intent);
        });

        // Upload video button
        btnUploadVideo.setOnClickListener(v -> {
            String title = editVideoTitle.getText().toString().trim();
            String description = editVideoDescription.getText().toString().trim();

            if (videoUri == null) {
                Toast.makeText(this, "Please select a video", Toast.LENGTH_SHORT).show();
                return;
            }
            if (title.isEmpty()) {
                Toast.makeText(this, "Please enter a video title", Toast.LENGTH_SHORT).show();
                return;
            }
            if (description.isEmpty()) {
                Toast.makeText(this, "Please enter a video description", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!isCloudinaryInitialized) {
                Toast.makeText(this, "Cloudinary not initialized", Toast.LENGTH_SHORT).show();
                return;
            }

            if (uploadProgressBar != null) {
                uploadProgressBar.setVisibility(View.VISIBLE);
            } else {
                Log.e("UploadVideoActivity", "Cannot show ProgressBar, it is null");
            }
            uploadToCloudinary(videoUri, title, description);
        });

        btnBack.setOnClickListener(v -> finish());
    }

    private void uploadToCloudinary(Uri videoUri, String title, String description) {
        MediaManager.get().upload(videoUri)
                .option("resource_type", "video")
                .callback(new UploadCallback() {
                    @Override
                    public void onStart(String requestId) {
                        Toast.makeText(UploadVideoActivity.this, "Upload started", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onProgress(String requestId, long bytes, long totalBytes) {
                        // Update progress if needed
                    }

                    @Override
                    public void onSuccess(String requestId, Map resultData) {
                        if (uploadProgressBar != null) {
                            uploadProgressBar.setVisibility(View.GONE);
                        }
                        String videoUrl = (String) resultData.get("url");
                        Toast.makeText(UploadVideoActivity.this, "Upload successful", Toast.LENGTH_SHORT).show();

                        // Pass the new video URL, title, and description back to VideoShortFireBaseActivity
                        Intent resultIntent = new Intent();
                        resultIntent.putExtra("video_url", videoUrl);
                        resultIntent.putExtra("video_title", title);
                        resultIntent.putExtra("video_desc", description);
                        setResult(RESULT_OK, resultIntent);
                        finish();
                    }

                    @Override
                    public void onError(String requestId, ErrorInfo error) {
                        if (uploadProgressBar != null) {
                            uploadProgressBar.setVisibility(View.GONE);
                        }
                        Toast.makeText(UploadVideoActivity.this, "Upload failed: " + error.getDescription(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onReschedule(String requestId, ErrorInfo error) {
                        // Handle reschedule if needed
                    }
                })
                .dispatch();
    }
}