package com.my.odos.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalTime;

@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalTime uploadTime;

    private int solveLimit;

    public Team() {
    }

    public int getSolveLimit() {
        return solveLimit;
    }

    public void setSolveLimit(int solveLimit) {
        this.solveLimit = solveLimit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalTime getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(LocalTime uploadTime) {
        this.uploadTime = uploadTime;
    }
}
