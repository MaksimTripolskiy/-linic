package com.geoclinic.model;

public enum CommentStatus {         ///  todo ap 6/10 keep enum or remove?

    PENDING("pending"),
    APPROVED("approved"),
    REJECTED("rejected");

    private String value;

    CommentStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
