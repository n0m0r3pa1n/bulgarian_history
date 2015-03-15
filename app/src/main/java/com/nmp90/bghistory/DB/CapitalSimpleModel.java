package com.nmp90.bghistory.DB;

/**
 * Created by georgi.mirchev on 1/14/14.
 */
public class CapitalSimpleModel {
    private int id;
    private String name;
    private String period;

    public CapitalSimpleModel(int id, String name, String period) {
        this.setId(id);
        this.setName(name);
        this.setPeriod(period);
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
}
