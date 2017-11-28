package com.epam.pages.bean;

public class MenuItem {
    private String title;
    private String link;
    private String backgroundColor;
    private String fontColor;

    public MenuItem(String title, String link) {
        this.title = title;
        this.link = link;
    }

    public MenuItem(String title, String link, String backgroundColor, String fontColor) {
        this.title = title;
        this.link = link;
        this.backgroundColor = backgroundColor;
        this.fontColor = fontColor;
    }

    public MenuItem(String title, String link, RGBAColor backgroundColor, RGBAColor fontColor) {
        this.title = title;
        this.link = link;
        this.backgroundColor = backgroundColor.toString();
        this.fontColor = fontColor.toString();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLinkWithoutParameters() {
        return (link != null && link.contains("?"))
                ? link.substring(0, link.indexOf("?"))
                : link;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getFontColor() {
        return fontColor;
    }

    public void setFontColor(String fontColor) {
        this.fontColor = fontColor;
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
