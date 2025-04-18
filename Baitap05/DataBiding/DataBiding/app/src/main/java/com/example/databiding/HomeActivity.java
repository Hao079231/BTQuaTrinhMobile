package com.example.databiding;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableField;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.databiding.databinding.ActivityHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements ListUserAdapter.OnItemClickListenner {
    public ObservableField<String> title = new ObservableField<>();
    private ListUserAdapter listUserAdapter;
    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        title.set("Ví dụ về DataBinding cho RecyclerView");
        binding.setHome(this);

        // Set LayoutManager TRƯỚC khi gán Adapter
        binding.rcView.setLayoutManager(new LinearLayoutManager(this));

        setData(); // Gọi setData() sau khi RecyclerView đã sẵn sàng
    }


    private void setData() {
        List<UserModel> userList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            UserModel user = new UserModel();
            user.setFirstName("Trung " + i);
            user.setLastName("Hao " + i);
            userList.add(user);
        }

        listUserAdapter = new ListUserAdapter(userList);
        binding.rcView.setAdapter(listUserAdapter); // Đặt adapter vào RecyclerView trước
        listUserAdapter.setOnItemClickListener(this); // Đặt listener sau khi Adapter đã được set
        listUserAdapter.notifyDataSetChanged();
    }


    @Override
    public void itemClick(UserModel user) {
        Toast.makeText(this, "Bạn vừa click vào: " + user.getFirstName(), Toast.LENGTH_SHORT).show();
    }
}
