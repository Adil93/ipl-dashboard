package com.myworks.ipldashboard.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private long totalMatches;
    private long totalWins;

    public Team(String name, long totalMatches) {
        this.name = name;
        this.totalMatches = totalMatches;
    }

}
