package com.example.communityminifootballleagueorganiser.services.league_services;

import com.example.communityminifootballleagueorganiser.models.dtos.LeagueDTO;

import java.util.List;

public interface LeagueService {

    LeagueDTO createLeague(LeagueDTO leagueDTO);

    List<LeagueDTO> getAllLeagues();
    LeagueDTO getLeagueById(Long leagueId);

    LeagueDTO upDateLeagueName(Long leagueId, LeagueDTO leagueDTO);
    void deleteLeague(Long leagueId);
}
