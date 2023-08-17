package com.zhangsiyao.common.constant;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Language {

    JAVA("Java"),
    CPP("C++"),
    PYTHON("Python");
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

    public static Language get(String name){
        if (name.equals(CPP.name)){
            return CPP;
        }else if(name.equals(JAVA.name)){
            return JAVA;
        }else {
            return PYTHON;
        }
    }
}
