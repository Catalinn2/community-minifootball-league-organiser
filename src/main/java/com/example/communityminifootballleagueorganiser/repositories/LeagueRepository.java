package com.example.communityminifootballleagueorganiser.repositories;

import com.example.communityminifootballleagueorganiser.models.entities.League;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeagueRepository extends JpaRepository<League, Long> {


    boolean existsByName(String name);
}
