package com.techelevator.model;

public class Brewery {
    private int breweryId;
    private String breweryName;
    private int ownerId;
    private String breweryImg;
    private String description;
    private boolean isActive;
    private String address;
    private String city;
    private String state;
    private String zip;

    public Brewery() {}

    public Brewery(int breweryId, String breweryName, int ownerId, String breweryImg, String description, boolean isActive, String address, String city, String state, String zip) {
        this.breweryId = breweryId;
        this.breweryName = breweryName;
        this.ownerId = ownerId;
        this.breweryImg = breweryImg;
        this.description = description;
        this.isActive = isActive;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    public int getBreweryId() {
        return breweryId;
    }

    public void setBreweryId(int breweryId) {
        this.breweryId = breweryId;
    }

    public String getBreweryName() {
        return breweryName;
    }

    public void setBreweryName(String breweryName) {
        this.breweryName = breweryName;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getBreweryImg() {
        return breweryImg;
    }

    public void setBreweryImg(String breweryImg) {
        this.breweryImg = breweryImg;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}
