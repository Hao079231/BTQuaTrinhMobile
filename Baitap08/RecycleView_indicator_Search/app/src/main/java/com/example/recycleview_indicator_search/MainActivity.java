package com.example.recycleview_indicator_search;

import android.os.Bundle;
import androidx.appcompat.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rclIcon;
    private IconAdapter iconAdapter;
    private List<IconModel> iconList;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rclIcon = findViewById(R.id.rcIcon);
        searchView = findViewById(R.id.searchView);
        searchView.clearFocus();

        iconList = new ArrayList<>();
        iconList.add(new IconModel(R.drawable.profile, "User"));
        iconList.add(new IconModel(R.drawable.profile, "Profile"));
        iconList.add(new IconModel(R.drawable.setting, "Settings"));
        iconList.add(new IconModel(R.drawable.logout, "Logout"));
        iconList.add(new IconModel(R.drawable.profile, "User"));
        iconList.add(new IconModel(R.drawable.profile, "Profile"));
        iconList.add(new IconModel(R.drawable.setting, "Settings"));
        iconList.add(new IconModel(R.drawable.logout, "Logout"));
        iconList.add(new IconModel(R.drawable.profile, "User"));
        iconList.add(new IconModel(R.drawable.profile, "Profile"));
        iconList.add(new IconModel(R.drawable.setting, "Settings"));
        iconList.add(new IconModel(R.drawable.logout, "Logout"));
        iconList.add(new IconModel(R.drawable.profile, "User"));
        iconList.add(new IconModel(R.drawable.profile, "Profile"));
        iconList.add(new IconModel(R.drawable.setting, "Settings"));
        iconList.add(new IconModel(R.drawable.logout, "Logout"));
        iconList.add(new IconModel(R.drawable.profile, "User"));
        iconList.add(new IconModel(R.drawable.profile, "Profile"));
        iconList.add(new IconModel(R.drawable.setting, "Settings"));
        iconList.add(new IconModel(R.drawable.logout, "Logout"));
        iconList.add(new IconModel(R.drawable.profile, "User"));
        iconList.add(new IconModel(R.drawable.profile, "Profile"));
        iconList.add(new IconModel(R.drawable.setting, "Settings"));
        iconList.add(new IconModel(R.drawable.logout, "Logout"));

        iconAdapter = new IconAdapter(this, iconList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        rclIcon.setLayoutManager(gridLayoutManager);
        rclIcon.setAdapter(iconAdapter);
        rclIcon.addItemDecoration(new LinePagerIndicatorDecoration());
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterListener(newText);
                return true;
            }
        });
    }

    private void filterListener(String text) {
        List<IconModel> filteredList = new ArrayList<>();
        for (IconModel iconModel : iconList) {
            if (iconModel.getDesc().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(iconModel);
            }
        }

        if (filteredList.isEmpty()) {
            Toast.makeText(this, "Không có dữ liệu", Toast.LENGTH_SHORT).show();
        } else {
            iconAdapter.setListenerList(filteredList);
        }
    }
}
