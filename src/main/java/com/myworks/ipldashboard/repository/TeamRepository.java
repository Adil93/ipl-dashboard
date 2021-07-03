package com.myworks.ipldashboard.repository;

import java.util.Optional;

import com.myworks.ipldashboard.model.Team;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends CrudRepository<Team, Long> {
    Optional<Team> findByName(String name);
}
