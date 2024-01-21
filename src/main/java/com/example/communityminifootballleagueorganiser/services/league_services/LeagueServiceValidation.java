package com.example.communityminifootballleagueorganiser.services.league_services;

import com.example.communityminifootballleagueorganiser.models.dtos.LeagueDTO;
import com.example.communityminifootballleagueorganiser.models.entities.League;

public interface LeagueServiceValidation {

    void validateUniqueLeagueName(String leagueName);

    League getValidLeague(Long leagueId);

}
