package com.example.communityminifootballleagueorganiser.services.league_services;

import com.example.communityminifootballleagueorganiser.models.dtos.LeagueDTO;
import com.example.communityminifootballleagueorganiser.models.dtos.PlayerDTO;
import com.example.communityminifootballleagueorganiser.models.dtos.TeamDTO;
import com.example.communityminifootballleagueorganiser.models.entities.League;

import java.util.List;

public interface LeagueService {

    LeagueDTO createLeague(LeagueDTO leagueDTO);

    List<LeagueDTO> getAllLeagues();
    LeagueDTO getLeagueById(Long leagueId);

    LeagueDTO updateLeagueName(Long leagueId, LeagueDTO leagueDTO);
    void deleteLeague(Long leagueId);

    void addTeamToLeague(Long leagueId, Long teamId);

    List<TeamDTO> getTeamsByLeague(Long leagueId);

    List<PlayerDTO> getPlayersByLeague(Long leagueId);

    void startLeague(Long leagueId);

    void generateMatches(League league);
}
