package com.revature.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Entity
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int teamId;

    @Column(nullable = false)
    private String teamName;

    private String teamLocation;

    /*
    * Connection to user FK
    * mappedBy - Indicates the FK field in the user class
    * cascade - defines how changes to a Team records will affect the dependent User record
    *   CascadeType.ALL - any changes to a team record will affect dependent records
     */
    @OneToMany(mappedBy = "team", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<User> users;



    public Team() {
    }

    public Team(int teamId, String teamName, String teamLocation) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.teamLocation = teamLocation;
    }

    @Override
    public String toString() {
        return "Team{" +
                "teamId=" + teamId +
                ", teamName='" + teamName + '\'' +
                ", teamLocation='" + teamLocation + '\'' +
                '}';
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamLocation() {
        return teamLocation;
    }

    public void setTeamLocation(String teamLocation) {
        this.teamLocation = teamLocation;
    }
}
