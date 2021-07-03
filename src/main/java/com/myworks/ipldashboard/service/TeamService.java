package com.myworks.ipldashboard.service;

import java.time.LocalDate;
import java.util.List;

import com.myworks.ipldashboard.model.Match;
import com.myworks.ipldashboard.model.Team;
import com.myworks.ipldashboard.repository.MatchRepository;
import com.myworks.ipldashboard.repository.TeamRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepo;

    @Autowired
    private MatchRepository matchRepo;

    public Team getTeamByName(String name) {
        return teamRepo.findByName(name).get();
    }

    public List<Match> GetAllMatchesOfATeam(String name) {
        Pageable pageable = PageRequest.of(0, 5);
        return matchRepo.getByTeam1OrTeam2OrderByDateDesc(name, name, pageable).get();
    }

    public List<Match> getMatchesForTeam(String name, int year) {
        LocalDate from = LocalDate.of(year, 1, 1);
        LocalDate to = LocalDate.of(year + 1, 1, 1);
        List<Match> matches = matchRepo.getMatchesByTeamBetweenDates(name, from, to).get();
        System.out.println(matches.size());
        return matches;
    }
}
