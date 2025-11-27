package com.karmakind.model;

public class User {
    private int id;
    private String username;
    private int karma;

    public User() {}

    public User(int id, String username, int karma) {
        this.id = id;
        this.username = username;
        this.karma = karma;
    }

    public void addKarma(int pts) { this.karma += pts; }

    // getters/setters omitted for brevity
}
