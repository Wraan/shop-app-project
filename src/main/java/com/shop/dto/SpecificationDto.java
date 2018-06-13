package com.shop.dto;

public class SpecificationDto {

    private int number;
    private String name;
    private String value;

    public SpecificationDto(int number, String name, String value) {
        this.number = number;
        this.name = name;
        this.value = value;
    }

    public SpecificationDto() {
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
