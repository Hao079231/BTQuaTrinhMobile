package com.example.recycleview;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvSongs;
    private SongAdapter songAdapter;
    private List<SongModel> mSongs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvSongs = findViewById(R.id.rv_songs);

        mSongs = new ArrayList<>();
        mSongs.add(new SongModel("60696", "NẾU EM CÒN TỒN TẠI", "Khi anh bắt đầu 1 tình yêu là lúc anh tự thay", "Trịnh Đình Quang"));
        mSongs.add(new SongModel("60701", "NGỐC", "Có rất nhiều những câu chuyện Em giấu riêng mình em biết", "Khắc Việt"));
        mSongs.add(new SongModel("60650", "HÃY TIN ANH LẦN NỮA", "Dẫu cho ta đã sai khi ở bên nhau Cố yêu thương", "Thiên Dũng"));
        mSongs.add(new SongModel("60610", "CHUỖI NGÀY VẮNG EM", "Từ khi em buớc ra đi cõi lòng anh ngập tràn bao", "Duy Cường"));
        mSongs.add(new SongModel("60656", "KHI NGƯỜI MÌNH YÊU KHÓC", "Nước mắt em đang rơi trên những ngón tay Nước mắt em", "Phan Mạnh Quỳnh"));
        mSongs.add(new SongModel("60685", "MƠ", "Anh mơ được gặp em anh mơ được ôm anh mơ được gần", "Trịnh Thăng Bình"));

        songAdapter = new SongAdapter(mSongs, this);
        rvSongs.setAdapter(songAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvSongs.setLayoutManager(linearLayoutManager);

    }
}