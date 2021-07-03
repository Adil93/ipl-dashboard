package com.myworks.ipldashboard.controller;

import java.util.List;

import com.myworks.ipldashboard.model.Match;
import com.myworks.ipldashboard.model.Team;
import com.myworks.ipldashboard.service.TeamService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/team")
@CrossOrigin
public class TeamController {

    @Autowired
    private TeamService teamService;

    @GetMapping("/{name}")
    public Team getTeam(@PathVariable String name) {
        Team team = teamService.getTeamByName(name);
        List<Match> teamMatches = teamService.GetAllMatchesOfATeam(name);
        team.setMatches(teamMatches);
        return team;

    }
}
