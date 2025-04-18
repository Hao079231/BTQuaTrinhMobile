package com.example.recycleview_indicator_search;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import androidx.recyclerview.widget.RecyclerView;

public class LinePagerIndicatorDecoration extends RecyclerView.ItemDecoration {
    private final Paint paint = new Paint();

    public LinePagerIndicatorDecoration() {
        paint.setColor(Color.GRAY);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    public void onDrawOver(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        int itemCount = parent.getAdapter().getItemCount();
        float indicatorWidth = 20f;
        float indicatorHeight = 10f;
        float spacing = 30f;

        float startX = (parent.getWidth() - (itemCount * indicatorWidth + (itemCount - 1) * spacing)) / 2;
        float y = parent.getHeight() - 50f;

        for (int i = 0; i < itemCount; i++) {
            float x = startX + i * (indicatorWidth + spacing);
            canvas.drawCircle(x, y, indicatorHeight / 2, paint);
        }
    }
}
