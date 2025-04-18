package com.example.recycleview_indicator_search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class IconAdapter extends RecyclerView.Adapter<IconAdapter.IconViewHolder> {
    private List<IconModel> iconList;
    private Context context;

    public IconAdapter(Context context, List<IconModel> iconList) {
        this.context = context;
        this.iconList = iconList;
    }

    @NonNull
    @Override
    public IconViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_icon_promotion, parent, false);
        return new IconViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IconViewHolder holder, int position) {
        IconModel icon = iconList.get(position);

        if (context != null) {
            Glide.with(context)
                    .load(icon.getImgId())
                    .into(holder.imgIcon);
        }

        holder.tvIcon.setText(icon.getDesc());
    }

    @Override
    public int getItemCount() {
        return (iconList != null) ? iconList.size() : 0;
    }

    public static class IconViewHolder extends RecyclerView.ViewHolder {
        ImageView imgIcon;
        TextView tvIcon;

        public IconViewHolder(@NonNull View itemView) {
            super(itemView);
            imgIcon = itemView.findViewById(R.id.imgIcon);
            tvIcon = itemView.findViewById(R.id.tvIcon);
        }
    }

    public void setListenerList(List<IconModel> iconModelList) {
        this.iconList = iconModelList;
        notifyDataSetChanged();
    }
}
