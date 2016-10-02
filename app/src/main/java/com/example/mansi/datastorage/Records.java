package com.example.mansi.datastorage;

/**
 * Created by MANSI on 02-10-2016.
 */
public class Records {

    private String roll;
    private String name;
    private String stream;
    private String cgpa;

    public Records()
    {
    }
    public Records(String roll,String name,String stream, String cgpa)
    {
        this.roll = roll;
        this.name=name;
        this.stream=stream;
        this.cgpa=cgpa;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public String getCgpa() {
        return cgpa;
    }

    public void setCgpa(String cgpa) {
        this.cgpa = cgpa;
    }


}

