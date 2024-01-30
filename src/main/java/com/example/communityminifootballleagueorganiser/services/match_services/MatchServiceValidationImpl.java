package com.example.communityminifootballleagueorganiser.services.match_services;

import com.example.communityminifootballleagueorganiser.exceptions.Match.MatchNotFoundException;
import com.example.communityminifootballleagueorganiser.repositories.MatchRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MatchServiceValidationImpl implements MatchServiceValidaton {

    private final MatchRepository matchRepository;

    public MatchServiceValidationImpl(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    @Override
    public void validateMatchExistInLeague(Long matchId, Long leagueId) {
        matchRepository.findByMatchIdAndLeague_LeagueId(matchId, leagueId)
                .orElseThrow(() -> new MatchNotFoundException("Match not found in the specified league"));
    }

    @Override
    public void validateMatchesExistForLeague(Long leagueId) {
        boolean exist = matchRepository.existsById(leagueId);
        if (!exist) {
            throw new MatchNotFoundException("No matches found for the specified league");
        }

    }
}
