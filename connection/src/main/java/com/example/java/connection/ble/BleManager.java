package com.example.java.connection.ble;

import android.app.Service;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattServer;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class BleManager implements BleConnection.BleConnectionCallback {

    private BluetoothGattServer server;

    public BleManager() {
        super();
    }

    private static BleManager sInstance;

    public static BleManager getInstance() {
        if (sInstance == null) {
            synchronized (BleManager.class) {
                if (sInstance == null) {
                    sInstance = new BleManager();
                }
            }
        }
        return sInstance;
    }

    public void scan(BleScanner.BleScanCallback callback) {

    }

    public void connect() {

    }

    public void disconnect() {

    }

    @Override
    public void onDeviceConnected(BluetoothDevice device, int connectionState) {

    }

    @Override
    public void onDeviceDisconnected(BluetoothDevice device, int connectionState) {

    }
}
