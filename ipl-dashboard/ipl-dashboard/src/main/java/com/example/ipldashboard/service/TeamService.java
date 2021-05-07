package com.example.ipldashboard.service;

import com.example.ipldashboard.model.Match;
import com.example.ipldashboard.model.Team;

import java.util.List;

public interface TeamService {
    public void createAndSaveTeamData();
    public Team getTeamInfo(String teamName);
}
