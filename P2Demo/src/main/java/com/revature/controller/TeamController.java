package com.revature.controller;

// The Controller layer handles HTTP Requests (sends back HTTP response)

import com.revature.model.Team;
import com.revature.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Combines @Controller and @ResponseBody
@RequestMapping("/teams") // This is base URL for this controller
@CrossOrigin(value = "http://localhost:5173", allowCredentials = "true")
public class TeamController {

    // @Autowired shouldn't do field injection is bad, mocking is hard, break encapsulation
    private final TeamService teamService;

    @Autowired // Constructor injection is better than field injection
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    // Insert new team (HTTP post request)
    @PostMapping
    public ResponseEntity<Team> insertTeam(@RequestBody Team team) {
        // TODO : send the team to the service which will send it to the DAO
        Team insertedTeam = teamService.insertTeam(team);

        // Helps use build  HTTP Response with OK status with team.
        return ResponseEntity.ok(insertedTeam);
    }

    // Select all team
    @GetMapping()
    public ResponseEntity<List<Team>> getAllTeams() {
        return ResponseEntity.ok(teamService.getAllTeams());
    }

    // Select all teams by location
    // Any get request ending in /teams/location/{location}
    @GetMapping("/location/{location}")
    public ResponseEntity<List<Team>> getAllTeams(@PathVariable String location) {
        final var teams = teamService.getAllTeams(location);

        return ResponseEntity.ok(teamService.getAllTeams(location));
    }


    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}
