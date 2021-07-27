package com.example.ipldashboard.service;

import java.util.List;

import com.example.ipldashboard.model.Match;
import com.example.ipldashboard.model.Team;

public interface TeamService {
    
    public void createAndSaveTeamData();

    public Team getTeamInfo(String teamName);

    public List<Match> getMatchesForTeamForYear(String teamName, int year);
}
