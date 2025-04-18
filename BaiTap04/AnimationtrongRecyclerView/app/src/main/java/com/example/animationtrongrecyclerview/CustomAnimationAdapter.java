package com.example.animationtrongrecyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class CustomAnimationAdapter extends RecyclerView.Adapter<CustomAnimationAdapter.ViewHolder> {
    private List<String> data;

    public CustomAnimationAdapter(List<String> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(parent.getContext());
        View itemView = li.inflate(R.layout.row_animation, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String item = data.get(position);
        holder.tvItem.setText(item);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void addItem(String item){
        data.add(item);
        notifyItemInserted(data.size() - 1);
    }
    public void addItem(int position, String item){
        data.add(position, item);
        notifyItemInserted(position);
    }
    public void removeItem(int position){
        data.remove(position);
        notifyItemRemoved(position);
    }
    public void removeItem(String item){
        int index = data.indexOf(item);
        if(index <0)
            return;
        data.remove(index);
        notifyItemRemoved(index);
    }
    public void  replaceItem(int position, String item){
        data.remove(position);
        data.add(position, item);
        notifyItemChanged(position);
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = (TextView) itemView.findViewById(R.id.tv_item);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    removeItem(getAdapterPosition());
                    Toast.makeText(itemView.getContext(), "Removed Animation", Toast.LENGTH_SHORT).show();
                    return false;
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }
}
