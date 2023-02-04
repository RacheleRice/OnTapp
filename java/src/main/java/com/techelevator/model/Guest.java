package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Guest {

    private String username;
    @JsonIgnore
    private boolean activated;


    public Guest() { }

    public Guest( String username) {
        this.username = "guest";
        setActivated(true);
    }

    public String getUsername() {
        return username;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    @Override
    public String toString() {
        return "User{" +
                " username='" + username + '\'' +
                ", activated=" + activated + '}';
    }
}

