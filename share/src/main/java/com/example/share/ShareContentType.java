package com.example.share;

import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@StringDef({
        ShareContentType.TEXT,
        ShareContentType.IMAGE,
        ShareContentType.AUDIO,
        ShareContentType.VIDEO,
        ShareContentType.FILE,
})
@Retention(RetentionPolicy.SOURCE)
@interface ShareContentType {

    String TEXT  = "text/plain";
    String IMAGE = "image/*";
    String AUDIO = "audio/*";
    String VIDEO = "video/*";
    String FILE  = "*/*";

}
