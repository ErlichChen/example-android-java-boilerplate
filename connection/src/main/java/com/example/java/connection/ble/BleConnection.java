package com.example.java.connection.ble;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.content.Context;
import android.os.Handler;
import android.util.Log;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

class BleConnection extends BluetoothGattCallback {

    private static final String TAG = BleConnection.class.getSimpleName();

    private final Context mContext;
    private final Handler mHandler;

    private final Queue<Request> mQueue;

    private BluetoothGatt mBluetoothGatt;
    private Map<String, BluetoothGattCharacteristic> mCharacteristicMap;
    private List<BleGattCharacteristicMap> mCharacteristicList;

    private BleConnectionCallback mListener;

    public BleConnection(final Context context, final BluetoothDevice device, final List<BleGattCharacteristicMap> characteristicList) {
        this.mContext = context;
        this.mHandler = new Handler();
        this.mQueue   = new LinkedList<>();
        connect(device);
        this.mCharacteristicList = characteristicList;
        this.mCharacteristicMap  = new HashMap<>();
    }

    public interface BleConnectionCallback {
        void onDeviceConnected(BluetoothDevice device, int connectionState);
        void onDeviceDisconnected(BluetoothDevice device, int connectionState);
    }

    private void connect(final BluetoothDevice device) {
        mBluetoothGatt = device.connectGatt(mContext, false, this);
    }

    public final void disconnect() {
        if (mBluetoothGatt != null)
            mBluetoothGatt.disconnect();
    }

    public void readCharacteristic(String uuid) {
        BluetoothGattCharacteristic characteristic = mCharacteristicMap.get(uuid);
        if (characteristic != null) {
            mBluetoothGatt.readCharacteristic(characteristic);
        } else {
            Log.w(TAG, "uuid is not available");
        }
    }

    @Override
    public void onConnectionStateChange(final BluetoothGatt gatt, int status, int newState) {
        super.onConnectionStateChange(gatt, status, newState);
        BluetoothDevice device = gatt.getDevice();
        if (BluetoothGatt.GATT_SUCCESS != status) {
            Log.e(TAG, "Gatt error: " + status + " : " + device.getAddress());
            mListener.onDeviceDisconnected(device, status);
            gatt.close();
            return;
        }
        if (BluetoothGatt.GATT_SUCCESS == status) {
            Log.v(TAG, "Connected: " + status + " : " + device.getAddress());
            mListener.onDeviceConnected(device, status);

            Log.v(TAG, "Connected: " + status + " : " + device.getAddress());
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    gatt.discoverServices();
                }
            }, 200);

        } else if (BluetoothGatt.GATT_SUCCESS == status) {
            Log.v(TAG, "Disconnected: " + status + " : " + device.getAddress());
            mListener.onDeviceDisconnected(device, status);
            gatt.close();
        }
    }

    @Override
    public void onServicesDiscovered(BluetoothGatt gatt, int status) {
        super.onServicesDiscovered(gatt, status);
    }

    @Override
    public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
        super.onCharacteristicChanged(gatt, characteristic);
    }

    @Override
    public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
        super.onCharacteristicRead(gatt, characteristic, status);
    }

    @Override
    public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
        super.onCharacteristicWrite(gatt, characteristic, status);
    }

    @Override
    public void onDescriptorWrite(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
        super.onDescriptorWrite(gatt, descriptor, status);
    }

    @Override
    public void onDescriptorRead(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
        super.onDescriptorRead(gatt, descriptor, status);
    }

    private final class BleGattCharacteristicMap {
        private String serviceUUID;
        private List<String> characteristicUUIDList;
    }

    private enum RequestType {
        READ_CHARACTERISTIC,
        READ_DESCRIPTOR,
        WRITE_CHARACTERISTIC,
        WRITE_DESCRIPTOR
    }

    private final class Request {
        final RequestType requestType;
        BluetoothGattCharacteristic characteristic;
        BluetoothGattDescriptor descriptor;
        byte[] data;
        int writeType;

        Request(RequestType requestType, BluetoothGattCharacteristic characteristic, byte[] data, int writeType) {
            this.requestType = requestType;
            this.characteristic = characteristic;
            this.data = data;
            this.writeType = writeType;
        }

        Request(RequestType requestType, BluetoothGattCharacteristic characteristic) {
            this.requestType = requestType;
            this.characteristic = characteristic;
            this.data = null;
            this.writeType = 0;
        }

        Request(RequestType requestType, BluetoothGattDescriptor descriptor, byte[] data) {
            this.requestType = requestType;
            this.descriptor = descriptor;
            this.data = data;
            this.writeType = 0;
        }

        Request(RequestType requestType, BluetoothGattDescriptor descriptor) {
            this.requestType = requestType;
            this.descriptor = descriptor;
            this.data = null;
            this.writeType = 0;
        }
    }
}
