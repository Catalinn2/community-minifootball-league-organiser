package com.example.communityminifootballleagueorganiser.services.team_services;

import com.example.communityminifootballleagueorganiser.exceptions.Team.TeamNameAlreadyExistException;
import com.example.communityminifootballleagueorganiser.exceptions.Team.TeamNotFoundException;
import com.example.communityminifootballleagueorganiser.models.entities.Team;
import com.example.communityminifootballleagueorganiser.repositories.TeamRepository;
import org.springframework.stereotype.Component;

@Component
public class TeamServiceValidationImpl implements TeamServiceValidation {

    private final TeamRepository teamRepository;

    public TeamServiceValidationImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public void validateUniqueName(String teamName) {
        if (teamRepository.existByName(teamName)) {
            throw new TeamNameAlreadyExistException("Team with " + teamName + " name already exist! Please try another name!");
        }
    }

    @Override
    public Team getValidTeam(Long teamId) {
        return teamRepository.findById(teamId).
                orElseThrow(() -> new TeamNotFoundException("Team with id : " + teamId + " not found!"));
    }
}
