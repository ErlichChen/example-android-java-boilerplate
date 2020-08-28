package com.example.java.connection.ble;

import android.bluetooth.BluetoothDevice;

public class BleScanner {
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

    public interface BleScanCallback {
        void onLeScan(BluetoothDevice device);
        void onScanFinished();
    }
}
