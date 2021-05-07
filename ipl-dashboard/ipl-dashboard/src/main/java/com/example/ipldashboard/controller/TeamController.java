package com.example.ipldashboard.controller;

import com.example.ipldashboard.model.Team;
import com.example.ipldashboard.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class TeamController {

    @Autowired
    private TeamService teamService;

    @GetMapping("/teams/{teamName}")
    public ResponseEntity<Object> getTeam(@PathVariable String teamName) {
        try {
            return ResponseEntity.of(Optional.of(teamService.getTeamInfo(teamName)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
