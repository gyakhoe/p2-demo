package com.revature.model;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Component
@Entity // this annotation will create DB table based on model
@Table(name = "users") // this annotation specify property like name here
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // this makes primary key auto increment
    private int userId;

    @Column(nullable = false) // Setting the column must have value for name
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false) // Setting the column must have value for role
    private String role = "player"; // Every user will be player by default

    /*
    * FK to team (every user has a team - many users can belong to one team)
    * fetch = defines when the dependency is loaded
    *   Lazy - loads dependency only when it's called
    *   eager = loads dependency at runtime (I usually use this)
    * @JoinColumn - defines the column taht will be used to link these tables in the DB
    * We have to provide the PK field in team
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "teamId") // This links our FK to PK in team (teamId)
    private Team team;

    public User() {
    }

    public User(int userId, String username, String password, String role, Team team) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
        this.team = team;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", team=" + team +
                '}';
    }
}
