package com.myworks.ipldashboard.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private long totalMatches;
    private long totalWins;

    @Transient
    private List<Match> matches;

    public Team(String name, long totalMatches) {
        this.name = name;
        this.totalMatches = totalMatches;
    }

}
