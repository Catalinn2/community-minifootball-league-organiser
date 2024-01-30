package com.example.communityminifootballleagueorganiser.services.match_services;

import com.example.communityminifootballleagueorganiser.models.dtos.MatchDTO;
import com.example.communityminifootballleagueorganiser.models.dtos.MatchResultDTO;
import com.example.communityminifootballleagueorganiser.models.entities.Match;
import com.example.communityminifootballleagueorganiser.models.entities.Team;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MatchService {

    List<MatchDTO> getMatchesByLeague(Long leagueId);

    MatchDTO getMatchByIdAndLeagueId(Long matchId, Long leagueId);

    MatchResultDTO playMatch(Long matchId);

    void updateTeamStats(Team team, int teamScore, int opponentScore);

    List<String> distributeGoalsAmongPlayers(Team team, int goals);
}
