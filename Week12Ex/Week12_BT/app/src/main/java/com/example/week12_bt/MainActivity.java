package com.example.week12_bt;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_ENABLE_BT = 0;
    private static final int REQUEST_DISCOVER_BT = 1;
    private TextView statusTv, pairedTv, checkEnabled;
    private Button onBtn, offBtn, disBtn, pairedBtn;
    BluetoothAdapter bluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        statusTv = findViewById(R.id.statusTv);
        pairedTv = findViewById(R.id.pairedTv);
        checkEnabled = findViewById(R.id.checkEnabledTv);

        onBtn = findViewById(R.id.turnOn);
        offBtn = findViewById(R.id.turnOff);
        pairedBtn = findViewById(R.id.paired);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        ActivityResultLauncher<Intent> enableBTLauncher =
                registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                        result -> {
                            if (result.getResultCode() == RESULT_OK) {
                                // Bluetooth was enabled
                                showToast("Bluetooth is now enabled");
                            } else {
                                // User didn't enable Bluetooth
                                showToast("Bluetooth enable request was declined");
                            }
                        });

        ActivityResultLauncher<Intent> discoverBTLauncher =
                registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        showToast("Device is now discoverable");
                    }
                });


        if (bluetoothAdapter == null) {
            statusTv.setText("Bluetooth is not available");
        } else {
            statusTv.setText("Bluetooth is available");
        }

        if (bluetoothAdapter.isEnabled()) {
            checkEnabled.setText("Bluetooth Enabled");
        } else {
            checkEnabled.setText("Bluetooth Disabled");
        }

        onBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!bluetoothAdapter.isEnabled()) {
                    Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.BLUETOOTH_CONNECT}, REQUEST_ENABLE_BT);
                    enableBTLauncher.launch(intent);
                    checkEnabled.setText("Bluetooth Enabled");
                } else {
                    showToast("Bluetooth is already on");
                }

            }
        });

        offBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bluetoothAdapter.isEnabled()) {
                    bluetoothAdapter.disable();
                    showToast("Bluetooth Off");
                    checkEnabled.setText("Bluetooth Disabled");
                } else {
                    showToast("Bluetooth is already off");
                }
            }
        });

        pairedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bluetoothAdapter.isEnabled()) {
                    pairedTv.setText("Paired Devices");
                    if (ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {

                        return;
                    }
                    Set<BluetoothDevice> devices = bluetoothAdapter.getBondedDevices();

                    for (BluetoothDevice device : devices) {
                        pairedTv.append("\n" + device.getName() + ", " + device);
                    }
                } else {
                    showToast("Turn on Bluetooth first");
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {


        switch (requestCode) {
            case REQUEST_ENABLE_BT:
                if (resultCode == RESULT_OK) {
                    checkEnabled.setText("Bluetooth Enabled");
                    showToast("Bluetooth is on");
                } else {
                    showToast("Bluetooth is off");
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void showToast(String arg) {
        Toast.makeText(getApplicationContext(), arg, Toast.LENGTH_SHORT).show();
    }
}