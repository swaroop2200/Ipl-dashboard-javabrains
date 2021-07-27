package com.example.ipldashboard.repository;

import com.example.ipldashboard.model.Match;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MatchRepository extends CrudRepository<Match,Long> {

    @Query("select m.team1, count(*) from Match m group by m.team1")
    List<Object[]> getMatchesPlayedByTeam1(); //value can be "team1" or "team2"

    @Query("select m.team1, count(*) from Match m group by m.team1")
    List<Object[]> getMatchesPlayedByTeam2(); //value can be "team1" or "team2"

    @Query("select m.matchWinner, count(*) from Match m group by m.matchWinner")
    List<Object[]> getTotalMatchWinsPerTeam();

    List<Match> getByTeam1OrTeam2OrderByDateDesc(String team1, String team2, Pageable pageable);

    default List<Match> findLatestMatchesByTeam(String teamName, int count) {
        return getByTeam1OrTeam2OrderByDateDesc(teamName, teamName, PageRequest.of(0, count));
    }

    @Query("select m from Match m where (m.team1 = :teamName or m.team2 = :teamName) and m.date between :startDate and :endDate order by date desc")
    List<Match> getMatchesByTeamBetweenDates(String teamName, LocalDate startDate, LocalDate endDate);
}
