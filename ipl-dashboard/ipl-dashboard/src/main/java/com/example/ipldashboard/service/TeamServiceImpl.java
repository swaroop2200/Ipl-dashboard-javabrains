package com.example.ipldashboard.service;

import com.example.ipldashboard.model.Match;
import com.example.ipldashboard.model.Team;
import com.example.ipldashboard.repository.MatchRepository;
import com.example.ipldashboard.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TeamServiceImpl implements TeamService{

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Override
    @Transactional
    public void createAndSaveTeamData(){

        Map<String, Team> teamData = new HashMap<>();

        matchRepository.getMatchesPlayedByTeam1()
                .stream()
                .map(e -> new Team((String) e[0], (long) e[1]))
                .forEach(team -> teamData.put(team.getTeamName(), team));

        matchRepository.getMatchesPlayedByTeam2()
                .forEach(e -> {
                    Team team = teamData.get((String) e[0]);
                    team.setTotalMatches(team.getTotalMatches() + (long) e[1]);
                });

        matchRepository.getTotalMatchWinsPerTeam()
                .forEach(e -> {
                    Team team = teamData.get((String) e[0]);
                    if (team != null) team.setTotalWins((long) e[1]);
                });

        teamData.values().forEach(team -> teamRepository.save(team));
        teamData.values().forEach(System.out::println);
    }

    @Override
    @Transactional
    public Team getTeamInfo(String teamName){
        Team team =  teamRepository.findByTeamName(teamName).orElseThrow(() -> new RuntimeException("Team requested is not available"));
        team.setMatches(matchRepository.findLatestMatchesByTeam(teamName, 4));
        return team;
    }

    @Override
    public List<Match> getMatchesForTeamForYear(String teamName, int year){
        LocalDate startDate = LocalDate.of(year, 1, 1);
        LocalDate endDate = LocalDate.of(year+1, 1, 1);
        return matchRepository.getMatchesByTeamBetweenDates(teamName, startDate, endDate);
    }
}
