package com.epam.pages.bean;

public enum RGBAColor {
    BLACK("rgba(0, 0, 0, 0)"),
    WHITE("rgba(255, 255, 255, 1)"),
    RED("rgba(201, 20, 31, 1)");

    private String rgbaValue;

    RGBAColor(String rgbaValue) {
        this.rgbaValue = rgbaValue;
    }

    public String toString() {
        return rgbaValue;
    }
}
