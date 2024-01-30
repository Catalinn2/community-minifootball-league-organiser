package com.example.communityminifootballleagueorganiser.services.match_services;

public interface MatchServiceValidaton {

    void validateMatchExistInLeague(Long matchId, Long leagueId);

    void validateMatchesExistForLeague(Long leagueId);
}
