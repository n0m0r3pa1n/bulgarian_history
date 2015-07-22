package com.nmp90.bghistory.DB;

/**
 * Created by georgi.mirchev on 1/10/14.
 */
public class HistoryEventModel {

    private int id;
    private String title;
    private String year;
    private String place;
    private String leader;
    private String result;
    private String description;
    private String topic;
    private int topicID;

    public HistoryEventModel(int id, String title, String year, String place,
                             String leader, String result, String description, String topic, int topicID) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.place = place;
        this.leader = leader;
        this.result = result;
        this.description = description;
        this.topic = topic;
        this.topicID = topicID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getId() {
        return id;
    }

    public int getTopicID(){
        return topicID;
    }
}

