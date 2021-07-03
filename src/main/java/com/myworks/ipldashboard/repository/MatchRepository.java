package com.myworks.ipldashboard.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.myworks.ipldashboard.model.Match;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface MatchRepository extends CrudRepository<Match, Long> {
    Optional<List<Match>> getByTeam1OrTeam2OrderByDateDesc(String team1, String team2, Pageable pageable);

    @Query("select m from Match m where (m.team1 = :name OR m.team2 = :name) AND m.date between :fromDate and :toDate  order by date desc")
    Optional<List<Match>> getMatchesByTeamBetweenDates(String name, LocalDate fromDate, LocalDate toDate);

}
