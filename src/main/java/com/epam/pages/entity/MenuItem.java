package com.epam.pages.entity;

public class MenuItem extends Link {

    private String backgroundColor;
    private String fontColor;

    public MenuItem(String title, String link, String backgroundColor, String fontColor) {
        super(title, link);
        this.backgroundColor = backgroundColor;
        this.fontColor = fontColor;
    }

    private String getBackgroundColor() {
        return backgroundColor;
    }

    private String getFontColor() {
        return fontColor;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof MenuItem)) return false;
        MenuItem otherItem = (MenuItem) other;

        String firstLink = getLinkWithoutParameters();
        String secondLink = otherItem.getLinkWithoutParameters();

        String secondBackground = otherItem.getBackgroundColor();
        String secondFontColor = otherItem.getFontColor();


        return title.equalsIgnoreCase(otherItem.getTitle())
                && ((firstLink == null && secondLink == null)
                || firstLink.equalsIgnoreCase(secondLink))
                && ((backgroundColor == null && secondBackground == null)
                || backgroundColor.equalsIgnoreCase(otherItem.getBackgroundColor()))
                && ((fontColor == null && secondFontColor == null)
                || fontColor.equalsIgnoreCase(otherItem.getFontColor()));
    }

    @Override
    public String toString() {
        return title + " Link: " + link + " Background: " + backgroundColor
                + " Font: " + fontColor;
    }
}
