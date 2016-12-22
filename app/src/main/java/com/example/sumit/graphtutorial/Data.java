package com.example.sumit.graphtutorial;

/**
 * Created by sumit on 12/22/2016.
 */

public class Data {


    private String id,subject,marks;
    private int r, g, b;

    public Data(String id, String subject, String marks,int r,int g ,int b) {
        this.id = id;
        this.subject = subject;
        this.marks = marks;
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }
}