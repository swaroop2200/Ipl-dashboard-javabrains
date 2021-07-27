package com.example.ipldashboard.controller;

import com.example.ipldashboard.model.Match;
import com.example.ipldashboard.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@CrossOrigin // allow pages from other damain to can these apis
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

    // You are getting the year as a query parameter coz its an attribute
    // year is a attribute where you will search or filter out
    @GetMapping("/teams/{teamName}/matches")
    public ResponseEntity<Object> getMatchesForTeam(@PathVariable String teamName, @RequestParam int year) {
        try {
            return ResponseEntity.of(Optional.of(teamService.getMatchesForTeamForYear(teamName, year)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
