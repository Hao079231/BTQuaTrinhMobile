package com.example.bluetooth;

import android.Manifest;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.util.Set;
import java.util.UUID;

public class ControlActivity extends AppCompatActivity {
    ImageButton btnTb1, btnTb2, btnDis;
    TextView txt1, txtMAC;
    BluetoothAdapter myBluetooth = null;
    BluetoothSocket btSocket = null;
    private boolean isBtConnected = false;
    Set<BluetoothDevice> pairedDevices1;
    String address = null;
    private ProgressDialog progressDialog;
    int flaglamp1;
    int flaglamp2;
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);
        Intent intent = getIntent();
        address = intent.getStringExtra(MainActivity.EXTRA_ADDRESS);
        setContentView(R.layout.activity_control);
        btnTb1 = (ImageButton) findViewById(R.id.btnTb1);
        btnTb2 = (ImageButton) findViewById(R.id.btnTb2);
        txt1 = (TextView) findViewById(R.id.textV1);
        txtMAC = (TextView) findViewById(R.id.textViewMAC);
        btnDis = (ImageButton) findViewById(R.id.btnDisc);
        new ConnectBT().execute();
        btnTb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thietTbi1();
            }
        });
        btnTb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thietTbi2();
            }
        });
        btnDis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Disconnect();
            }
        });
    }

    private void Disconnect() {
        if(btSocket!= null){
            try {
                btSocket.close();
            }catch (IOException e){
                msg("Loi");
            }
        }
        finish();
    }

    private void thietTbi2() {
        if(btSocket !=null){
            try {
                if(this.flaglamp2 == 0){
                    this.flaglamp2 = 1;
                    this.btnTb2.setBackgroundResource(R.drawable.tb1on);
                    btSocket.getOutputStream().write("2".toString().getBytes());
                    txt1.setText("Thiet bi so 2 dang bat");
                    return;
                }else {
                    if(this.flaglamp2 !=1)return;
                    {
                        this.flaglamp2 = 0;
                        this.btnTb2.setBackgroundResource(R.drawable.btn_not_connect);
                        btSocket.getOutputStream().write("G".toString().getBytes());
                        txt1.setText("Thiet bi so 2 dang tat");
                        return;
                    }
                }
            }catch (IOException e){
                msg("Loi");
            }
        }
    }

    private void thietTbi1() {
        if(btSocket !=null){
            try {
                if(this.flaglamp1 == 0){
                    this.flaglamp1 = 1;
                    this.btnTb1.setBackgroundResource(R.drawable.tb1on);
                    btSocket.getOutputStream().write("1".toString().getBytes());
                    txt1.setText("Thiet bi so 1 dang bat");
                    return;
                }else {
                    if(this.flaglamp1 !=1)return;
                    {
                        this.flaglamp1 = 0;
                        this.btnTb1.setBackgroundResource(R.drawable.btn_not_connect);
                        btSocket.getOutputStream().write("A".toString().getBytes());
                        txt1.setText("Thiet bi so 1 dang tat");
                        return;
                    }
                }
            }catch (IOException e){
                msg("Loi");
            }
        }
    }

    private void msg(String loi) {
        Toast.makeText(getApplicationContext(), loi, Toast.LENGTH_LONG).show();
    }
    public class ConnectBT extends AsyncTask<Void, Void, Void> {
        private boolean ConnectSuccess = true;

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(ControlActivity.this, "Dang ket noi ...", "Xin vui long cho");
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try{
                if(btSocket == null || !isBtConnected){
                    myBluetooth = BluetoothAdapter.getDefaultAdapter();
                    BluetoothDevice dispositive = myBluetooth.getRemoteDevice(address);
                    if(ActivityCompat.checkSelfPermission(ControlActivity.this, Manifest.permission.BLUETOOTH_CONNECT)!= PackageManager.PERMISSION_GRANTED){
                        btSocket = dispositive.createInsecureRfcommSocketToServiceRecord(myUUID);
                        BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                        btSocket.connect();
                    }
                }
            }catch (IOException e){
                ConnectSuccess = false;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            if(!ConnectSuccess){
                msg("Ket noi that bai! Kiem tra thiet bi.");
                finish();
            }else {
                msg("Ket noi thanh cong.");
                isBtConnected = true;
                pairedDevicesList1();
            }
            progressDialog.dismiss();
        }
    }

    private void pairedDevicesList1() {
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT)!= PackageManager.PERMISSION_GRANTED){
            pairedDevices1 = myBluetooth.getBondedDevices();
            if(pairedDevices1.size()>0){
                for (BluetoothDevice bt : pairedDevices1){
                    txtMAC.setText(bt.getName() + " - " + bt.getAddress());
                }
            }else {
                Toast.makeText(getApplicationContext(), "Khong tim thay thiet bi ket noi,", Toast.LENGTH_LONG).show();
            }
        }
    }

}