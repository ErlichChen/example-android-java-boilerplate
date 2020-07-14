package com.example.java.db;

import androidx.annotation.NonNull;

public class Result<T> {

    private Result() { }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }

    public final static class Success<T> extends Result {
        private T data;
        public Success(T data) {
            this.data = data;
        }

        public T getData() {
            return this.data;
        }
    }

    public final static class Fail<T> extends Result {
        private Exception error;

        public Fail(Exception error) {
            this.error = error;
        }

        public Exception getError() {
            return this.error;
        }
    }
}
