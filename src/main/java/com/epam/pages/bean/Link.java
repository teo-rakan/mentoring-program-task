package com.epam.pages.bean;

public class Link {
    protected String title;
    protected String link;

    public Link(String title, String link) {
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

    String getLinkWithoutParameters() {
        return (link != null && link.contains("?"))
                ? link.substring(0, link.indexOf("?"))
                : link;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof Link)) return false;
        Link otherLink = (Link) other;

        String firstLink = getLinkWithoutParameters();
        String secondLink = otherLink.getLinkWithoutParameters();


        return title.equalsIgnoreCase(otherLink.getTitle())
                && ((firstLink == null && secondLink == null)
                || firstLink.equalsIgnoreCase(secondLink));
    }

    @Override
    public String toString() {
        return "Title:" + title + " Link: " + link;
    }
}
