package com.example.demo1.enums;

import com.example.demo1.testinterface.AbsoluteEnum;

public enum TestEnum implements AbsoluteEnum<TestEnum,String,String> {

    ENUM1("1","好")
    ;
    private String code;
    private String name;

    TestEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public String getNameByCode(String code) {
        for (TestEnum testEnum : values()) {
            if(testEnum.code.equals(code)){
                return name;
            }
        }
        return null;
    }
}
