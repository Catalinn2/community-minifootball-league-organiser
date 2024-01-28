package com.example.communityminifootballleagueorganiser.repositories;

import com.example.communityminifootballleagueorganiser.models.entities.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
}
