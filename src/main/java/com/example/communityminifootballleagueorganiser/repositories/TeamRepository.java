package com.example.communityminifootballleagueorganiser.repositories;

import com.example.communityminifootballleagueorganiser.models.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    boolean existByName(String teamName);
}
