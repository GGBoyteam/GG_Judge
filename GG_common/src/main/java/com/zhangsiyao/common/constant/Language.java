package com.zhangsiyao.common.constant;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Language {

    Java("Java"),
    Cpp("Cpp"),
    Python("Python");
    private String name;


    Language(String name) {
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
