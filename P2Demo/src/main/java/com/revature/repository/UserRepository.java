package com.revature.repository;

import com.revature.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    // Find a user by their team id FK
    // This property expression will need to dig into the team field of user to find the ID
    public List<User> findByTeamTeamId(int teamId);

}
