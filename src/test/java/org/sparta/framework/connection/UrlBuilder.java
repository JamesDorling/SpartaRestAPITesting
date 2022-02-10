package org.sparta.framework.connection;

import org.sparta.config.Config;

public class UrlBuilder {
    private final StringBuilder link;

    public UrlBuilder(String link) {this.link = new StringBuilder(link);}

    public UrlBuilder spartan() {
        link.append("/spartans?");
        return this;
    }

    public UrlBuilder course() {
        link.append("/courses?");
        return this;
    }

    public UrlBuilder firstName(String name) {
        link.append("firstName=").append(name).append("&");
        return this;
    }

    public UrlBuilder lastName(String name) {
        link.append("lastName=").append(name).append("&");
        return this;
    }

    public UrlBuilder courseId(Integer id) {
        link.append("courseId=").append(id).append("&");
        return this;
    }

    public UrlBuilder date(String date) {
        link.append("date=").append(date).append("&");
        return this;
    }

    public UrlBuilder courseName(String course) {
        link.append("courseName=").append(course).append("&");
        return this;
    }

    public UrlBuilder length(String length) {
        link.append("length=").append(length).append("&");
        return this;
    }

    public UrlBuilder BeforeAfter(TimeParameters parameter) {
        link.append("beforeAfter=");
        switch (parameter) {
            case BEFORE -> link.append("before&");
            case AFTER -> link.append("after&");
            case SAME -> link.append("now&");
            default -> throw new IllegalStateException("Unexpected value (Time Parameter): " + parameter.name());
        }
        return this;
    }

    public UrlBuilder StartEnd(TimeParameters parameter) {
        link.append("startEnd=");
        switch (parameter) {
            case START -> link.append("start&");
            case END -> link.append("end&");
            default -> throw new IllegalStateException("Unexpected value (Time Parameter): " + parameter.name());
        }
        return this;
    }

    public UrlBuilder searchByDate(TimeParameters BeforeOrAfter, TimeParameters StartOrEnd, String date) {
        return this.BeforeAfter(BeforeOrAfter).StartEnd(StartOrEnd).date(date);
    }

    public String getSpartanWithKey() {
        return link.append("/spartans/").append(Config.getApiKey()).toString();
    }

    public String getCourseWithKey() {
        return link.append("/courses/").append(Config.getApiKey()).toString();
    }

    public String getSpecificSpartan(String id) {
        return link.append("/spartans/").append(id).toString();
    }

    public String getSpecificCourse(Integer id) {
        return link.append("/courses/").append(id).toString();
    }

    public String deleteSpartan(String id) {
        return link.append("/spartans/").append(id).append("/").append(Config.getApiKey()).toString();
    }

    public String getAllActiveCourses() {
        return link.append("/courses/isActive").toString();
    }

    public String getAllInactiveCourses() {
        return link.append("/courses/nonActive").toString();
    }

    public String link() { //Trailing & is fine, still works
        return link.toString();
    }

    public enum TimeParameters {
        BEFORE,
        SAME,
        AFTER,
        START,
        END
    }
}