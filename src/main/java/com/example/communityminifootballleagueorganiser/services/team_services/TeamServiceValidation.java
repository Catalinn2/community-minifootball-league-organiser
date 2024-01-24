package com.example.communityminifootballleagueorganiser.services.team_services;

import com.example.communityminifootballleagueorganiser.models.entities.Team;

public interface TeamServiceValidation {

    void validateUniqueName(String teamName);

    Team getValidTeam(Long teamId);
}
