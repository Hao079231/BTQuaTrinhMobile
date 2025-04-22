package com.example.firebase;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VideosFireBaseAdapter extends RecyclerView.Adapter<VideosFireBaseAdapter.MyHolder> {

    private List<Video1Model> videoList;

    public VideosFireBaseAdapter(List<Video1Model> videoList) {
        this.videoList = videoList;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        Video1Model model = videoList.get(position);
        holder.textVideoTitle.setText(model.getTitle());
        holder.textVideoDescription.setText(model.getDesc());
        holder.textUploaderEmail.setText(model.getUploaderEmail());
        holder.textLikeCount.setText(String.valueOf(model.getLikeCount()));
        holder.textDislikeCount.setText(String.valueOf(model.getDislikeCount()));

        // Cập nhật trạng thái like/dislike từ model
        holder.favorites.setImageResource(model.isLiked() ? R.drawable.ic_fill_favorite : R.drawable.ic_favorite);
        holder.dislike.setImageResource(model.isDisliked() ? R.drawable.ic_fill_dislike : R.drawable.ic_dislike);

        // Set up video from URL
        String videoUrl = model.getUrl();
        Log.d("VideosFireBaseAdapter", "Video URL: " + videoUrl);
        if (videoUrl != null && !videoUrl.isEmpty()) {
            try {
                Uri videoUri = Uri.parse(videoUrl);
                holder.videoView.setVideoURI(videoUri);

                // Show progress bar while preparing video
                holder.videoProgressBar.setVisibility(View.VISIBLE);
                // Start video when ready
                holder.videoView.setOnPreparedListener(mp -> {
                    holder.videoProgressBar.setVisibility(View.GONE);
                    try {
                        mp.start();
                        float videoRatio = mp.getVideoWidth() / (float) mp.getVideoHeight();
                        float screenRatio = holder.videoView.getWidth() / (float) holder.videoView.getHeight();
                        float scale = videoRatio / screenRatio;
                        if (scale >= 1f) {
                            holder.videoView.setScaleX(scale);
                        } else {
                            holder.videoView.setScaleY(1f / scale);
                        }
                    } catch (IllegalStateException e) {
                        Log.e("VideosFireBaseAdapter", "MediaPlayer start failed", e);
                    }
                });
                // Handle video errors
                holder.videoView.setOnErrorListener((mp, what, extra) -> {
                    Log.e("VideosFireBaseAdapter", "MediaPlayer error: what=" + what + ", extra=" + extra);
                    holder.videoProgressBar.setVisibility(View.GONE);
                    holder.textVideoTitle.setText("Không thể phát video");
                    return true;
                });
                // Loop video on completion
                holder.videoView.setOnCompletionListener(mp -> {
                    try {
                        mp.start();
                    } catch (IllegalStateException e) {
                        Log.e("VideosFireBaseAdapter", "MediaPlayer restart failed", e);
                    }
                });
            } catch (Exception e) {
                Log.e("VideosFireBaseAdapter", "Invalid video URL: " + videoUrl, e);
                holder.videoProgressBar.setVisibility(View.GONE);
                holder.textVideoTitle.setText("URL video không hợp lệ");
            }
        } else {
            Log.e("VideosFireBaseAdapter", "Video URL is null or empty");
            holder.videoProgressBar.setVisibility(View.GONE);
            holder.textVideoTitle.setText("Không có URL video");
        }

        // Handle favorite (like) button
        holder.favorites.setOnClickListener(v -> {
            if (!model.isLiked()) {
                holder.favorites.setImageResource(R.drawable.ic_fill_favorite);
                model.setLikeCount(model.getLikeCount() + 1);
                model.setLiked(true);
            } else {
                holder.favorites.setImageResource(R.drawable.ic_favorite);
                model.setLikeCount(Math.max(0, model.getLikeCount() - 1));
                model.setLiked(false);
            }
            holder.textLikeCount.setText(String.valueOf(model.getLikeCount()));
            notifyDataSetChanged();
        });

        // Handle dislike button
        holder.dislike.setOnClickListener(v -> {
            if (!model.isDisliked()) {
                holder.dislike.setImageResource(R.drawable.ic_fill_dislike);
                model.setDislikeCount(model.getDislikeCount() + 1);
                model.setDisliked(true);
            } else {
                holder.dislike.setImageResource(R.drawable.ic_dislike);
                model.setDislikeCount(Math.max(0, model.getDislikeCount() - 1));
                model.setDisliked(false);
            }
            holder.textDislikeCount.setText(String.valueOf(model.getDislikeCount()));
            notifyDataSetChanged();
        });

        // Handle profile icon click
        holder.imPerson.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), ProfileActivity.class);
            intent.putExtra("uploader_email", model.getUploaderEmail());
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_video_row, parent, false);
        return new MyHolder(view);
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    @Override
    public void onViewRecycled(@NonNull MyHolder holder) {
        super.onViewRecycled(holder);
        holder.videoView.stopPlayback();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        private VideoView videoView;
        private ProgressBar videoProgressBar;
        private TextView textVideoTitle;
        private TextView textVideoDescription;
        private TextView textUploaderEmail;
        private TextView textLikeCount;
        private TextView textDislikeCount;
        private ImageView imPerson, favorites, imShare, imMore, dislike;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            videoView = itemView.findViewById(R.id.videoView);
            videoProgressBar = itemView.findViewById(R.id.videoProgressBar);
            textVideoTitle = itemView.findViewById(R.id.textVideoTitle);
            textVideoDescription = itemView.findViewById(R.id.textVideoDescription);
            textUploaderEmail = itemView.findViewById(R.id.textUploaderEmail);
            textLikeCount = itemView.findViewById(R.id.textLikeCount);
            textDislikeCount = itemView.findViewById(R.id.textDislikeCount);
            imPerson = itemView.findViewById(R.id.imPerson);
            favorites = itemView.findViewById(R.id.favorites);
            imShare = itemView.findViewById(R.id.imShare);
            imMore = itemView.findViewById(R.id.imMore);
            dislike = itemView.findViewById(R.id.dislike);
        }
    }
}