package com.revature.services;

import com.revature.model.Team;
import com.revature.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * The service layer is where we have our business logic
 * User input validation, data manipulation/reformatting, user authentication, etc.
 *
 */

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    // This method will insert new Teams into the DB once they have been validated
    public Team insertTeam(Team team) {

        if(team.getTeamName() == null || team.getTeamName().isBlank()) {
            throw new IllegalArgumentException("Team name can't be null or blank");
        }
        if(team.getTeamLocation() == null || team.getTeamLocation().isBlank()) {
            throw new IllegalArgumentException("Team Location can't be null or blank");
        }

        // Everything is good
        Team insertedTeam = teamRepository.save(team);
        return insertedTeam;
    }

    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    // This method will get all teams by location
    public List<Team> getAllTeams(String location) {
        // make sure location is valid
        if(location == null || location.isBlank()) {
            throw  new IllegalArgumentException("Location can't be blank or null");
        }

        // attempt to get list of teams
        List<Team> teams = teamRepository.findByTeamLocation(location);

        // throw an exception if the returned list is empty
        return teams;
    }
}
