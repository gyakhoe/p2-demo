package com.revature.repository;

import com.revature.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 * JPA Repository takes two generics
 *  * The type of entity we are working with
 *  * The type of primary key of that Entity
 */
@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {

    // This method will find all team by location
    // It is referred to property expression method
    // NOTE: the method must be named by findByXyz,
    // where the Xyz  is the name of the filed we're selecting by
    public List<Team> findByTeamLocation(String location);

}
