package com.example.menu;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private Button btnButton, btnButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        btnButton = findViewById(R.id.btnButton); // gán id
        btnButton.setOnClickListener(v -> ShowPopupMenu()); // gọi popup khi bấm nút
        Button btnButton2 = findViewById(R.id.btnButton2); // ID phải đúng và tồn tại trong XML
        registerForContextMenu(btnButton2);
        Button btnShow = findViewById(R.id.btnShowDialog);
        btnShow.setOnClickListener(v -> XacNhanXoa(0));
        Button btnShow2 = findViewById(R.id.btnShowCustomDialog);
        btnShow2.setOnClickListener(v -> DiaLog1());

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_setting,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menuSetting) {
            // lệnh
        } else if (id == R.id.menuShare) {
            // lệnh
        } else if (id == R.id.menuLogout) {
            // lệnh
        }

        return super.onOptionsItemSelected(item);
    }
    private void ShowPopupMenu(){
        PopupMenu popupMenu = new PopupMenu(this,btnButton);
        popupMenu.getMenuInflater().inflate(R.menu.menu_setting,popupMenu.getMenu());
//bắt sự kiện
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.menuSetting) {
                    Toast.makeText(MainActivity.this,"Bạn đang chọn Setting",Toast.LENGTH_LONG).show();
                } else if (id == R.id.menuShare) {
                    btnButton.setText("Chia sẻ");
                } else if (id == R.id.menuLogout) {
                    // lệnh
                }
                return false;
            }
        });
        popupMenu.show();
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_setting,menu);
        menu.setHeaderTitle("Context Menu");
        menu.setHeaderIcon(R.mipmap.ic_launcher);
        super.onCreateContextMenu(menu, v, menuInfo);
    }
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menuSetting) {
            Toast.makeText(MainActivity.this,"Bạn đang chọn Setting",Toast.LENGTH_LONG).show();
        } else if (id == R.id.menuShare) {
            btnButton.setText("Chia sẻ");
        } else if (id == R.id.menuLogout) {
            // lệnh
        }
        return super.onContextItemSelected(item);
    }
    private void DiaLog1(){
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog);
        dialog.setCanceledOnTouchOutside(false);
//ánh xạ
        EditText editText1 = (EditText)
                dialog.findViewById(R.id.etMSSV);
//viết code sự kiện
//bắt sự kiện Dialog
        dialog.show(); //hủy gọi dialog.dismiss();
    }
    private void XacNhanXoa( final int vitri){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Thông báo");
        alert.setMessage("Bạn có muốn đăng xuất không");
        alert.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//lệnh nút có
            }
        });
        alert.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//lệnh nút không
            }
        });
        alert.show();

    }
}