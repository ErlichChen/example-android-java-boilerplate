package com.example.utils;

import android.os.Environment;

public enum DirectoryType {
    MUSIC(Environment.DIRECTORY_MUSIC),
    PODCASTS(Environment.DIRECTORY_PODCASTS),
    RINGTONES(Environment.DIRECTORY_RINGTONES),
    ALARMS(Environment.DIRECTORY_ALARMS),
    NOTIFICATIONS(Environment.DIRECTORY_NOTIFICATIONS),
    PICTURES(Environment.DIRECTORY_PICTURES),
    MOVIES(Environment.DIRECTORY_MOVIES),
    DOWNLOADS(Environment.DIRECTORY_DOWNLOADS),
    DCIM(Environment.DIRECTORY_DCIM),
    DOCUMENTS(Environment.DIRECTORY_DOCUMENTS);

    private String mType;
    DirectoryType(String type) {
        this.mType = type;
    }

    public String getType() {
        return this.mType;
    }

}