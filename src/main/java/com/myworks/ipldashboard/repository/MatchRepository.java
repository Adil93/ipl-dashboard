package com.myworks.ipldashboard.repository;

import java.util.List;
import java.util.Optional;

import com.myworks.ipldashboard.model.Match;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface MatchRepository extends CrudRepository<Match, Long> {
    Optional<List<Match>> getByTeam1OrTeam2OrderByDateDesc(String team1, String team2, Pageable pageable);
}
