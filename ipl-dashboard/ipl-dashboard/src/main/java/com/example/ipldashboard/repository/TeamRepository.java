package com.example.ipldashboard.repository;

import com.example.ipldashboard.model.Team;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends CrudRepository<Team,Long> {

    Optional<Team> findByTeamName(String teamName);
}
