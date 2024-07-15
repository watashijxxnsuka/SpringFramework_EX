package com.bit.springboard.dto;

public class NameTelDto {
    private String name;
    private String tel;

    public String getName() {
        return name;
    }

    public String getTel() {
        return tel;
    }

    public void setName(String name) {
        System.out.println("setName method called");
        this.name = name;
    }

    public void setTel(String tel) {
        System.out.println("setTel method called");
        this.tel = tel;
    }


}
