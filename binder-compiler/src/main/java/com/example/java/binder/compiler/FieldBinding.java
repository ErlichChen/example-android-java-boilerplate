package com.example.java.binder.compiler;

final class FieldBinding {

    private String name;
    private String type;

    FieldBinding(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
