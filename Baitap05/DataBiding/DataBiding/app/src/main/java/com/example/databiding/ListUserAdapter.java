package com.example.databiding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableField;
import androidx.recyclerview.widget.RecyclerView;

import com.example.databiding.databinding.ItemListUserBinding;

import java.util.List;

public class ListUserAdapter extends RecyclerView.Adapter<ListUserAdapter.MyViewHolder> {
    private List<UserModel> userList;
    private OnItemClickListenner onItemClickListener; // Đổi kiểu dữ liệu cho đúng

    public ListUserAdapter(List<UserModel> userList) {
        this.userList = userList;
    }

    public interface OnItemClickListenner {
        void itemClick(UserModel user);
    }

    public void setOnItemClickListener(OnItemClickListenner onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemListUserBinding itemListUserBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_list_user, parent, false
        );
        return new MyViewHolder(itemListUserBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.setBinding(userList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ObservableField<String> stt = new ObservableField<>();
        public ObservableField<String> firstName = new ObservableField<>();
        public ObservableField<String> lastName = new ObservableField<>();
        private ItemListUserBinding itemListUserBinding;
        private UserModel user;

        public MyViewHolder(ItemListUserBinding itemView) {
            super(itemView.getRoot());
            this.itemListUserBinding = itemView;
            itemView.getRoot().setOnClickListener(this);
        }

        public void setBinding(UserModel user, int position) {
            if (itemListUserBinding.getViewHolder() == null) {
                itemListUserBinding.setViewHolder(this);
            }
            this.user = user;
            stt.set(String.valueOf(position + 1)); // Hiển thị chỉ số bắt đầu từ 1
            firstName.set(user.getFirstName());
            lastName.set(user.getLastName());
        }

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null && user != null) {
                onItemClickListener.itemClick(user);
            }
        }
    }
}
