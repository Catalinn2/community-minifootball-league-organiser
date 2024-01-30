package com.example.communityminifootballleagueorganiser.repositories;

import com.example.communityminifootballleagueorganiser.models.entities.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
    List<Match> findByLeague_LeagueId(Long leagueId);
    Optional<Match> findByMatchIdAndLeague_LeagueId(Long matchId, Long leagueId);
}
