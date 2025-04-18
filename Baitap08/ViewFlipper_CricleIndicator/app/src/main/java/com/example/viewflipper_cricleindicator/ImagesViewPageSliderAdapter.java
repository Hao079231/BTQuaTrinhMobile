package com.example.viewflipper_cricleindicator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;

import java.util.List;

public class ImagesViewPageSliderAdapter extends PagerAdapter {
    private Context context;
    private List<ImagesSlider> imagesSliders;

    public ImagesViewPageSliderAdapter(Context context, List<ImagesSlider> imagesSliders) {
        this.context = context;
        this.imagesSliders = imagesSliders;
    }

    @Override
    public int getCount() {
        return imagesSliders.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_images, container, false);
        ImageView imageView = view.findViewById(R.id.imgView);

        Glide.with(context)
                .load(imagesSliders.get(position).getAvatar())
                .into(imageView);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}