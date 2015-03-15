package com.nmp90.bghistory.DB;

/**
 * Created by georgi.mirchev on 1/14/14.
 */
public class CapitalModel {
    private int id;
    private String name;
    private String period;
    private String lat;
    private String lng;
    private String picture;
    private String content;
    private int citizens;

    public CapitalModel(int id, String name, String period, String lat, String lng, String picture, String content, int citizens) {
        this.id = id;
        this.name = name;
        this.period = period;
        this.lat = lat;
        this.lng = lng;
        this.picture = picture;
        this.content = content;
        this.citizens = citizens;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCitizens() {
        return citizens;
    }

    public void setCitizens(int citizens) {
        this.citizens = citizens;
    }
}
