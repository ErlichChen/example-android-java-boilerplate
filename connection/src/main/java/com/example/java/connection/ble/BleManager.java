package com.example.java.connection.ble;

import android.app.Service;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattServer;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class BleManager extends Service implements BleConnection.BleConnectionCallback {

    private BluetoothGattServer server;

    public BleManager() {
        super();
    }

    @Override
    public void onDeviceConnected(BluetoothDevice device, int connectionState) {

    }

    @Override
    public void onDeviceDisconnected(BluetoothDevice device, int connectionState) {

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void connect() {

    }

    public void disconnect() {

    }
}
