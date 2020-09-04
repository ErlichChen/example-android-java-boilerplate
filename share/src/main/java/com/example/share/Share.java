package com.example.share;

import android.content.Context;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

public class Share {

    private Share() { }

    private void show() {

    }

    public static class Builder {

        private @ShareContentType String mContentType = ShareContentType.TEXT;

        private Builder() { }

        private Builder(@NonNull Context context) {

        }

        public Builder setTitle(@StringRes int resId) {
            return this;
        }

        public Builder setTitle(@NonNull CharSequence title) {
            return this;
        }

        public Builder setContextType(@ShareContentType String type) {
            this.mContentType = type;
            return this;
        }

        public Builder setContextType(@StringRes int resId) {
            return this;
        }

        public Builder setContextType(@NonNull CharSequence title) {
            return this;
        }

        public Share create(@NonNull Context context) {
            final Share share = new Share();
            return new Builder(context);
        }

    }
}
