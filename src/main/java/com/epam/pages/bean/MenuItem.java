package com.epam.pages.bean;

public class MenuItem {
    private String title;
    private String link;

    public MenuItem(String title, String link) {
        this.title = title;
        this.link = link;
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

    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof MenuItem)) return false;
        MenuItem otherMenuItem = (MenuItem) other;
        return title.equalsIgnoreCase(otherMenuItem.getTitle())
                && link.equalsIgnoreCase(otherMenuItem.getLink());
    }

    @Override
    public String toString() {
        return title + " " + link;
    }
}
