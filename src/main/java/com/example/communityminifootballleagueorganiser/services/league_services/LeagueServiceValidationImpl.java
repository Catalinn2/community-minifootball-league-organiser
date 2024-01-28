package com.example.communityminifootballleagueorganiser.services.league_services;

import com.example.communityminifootballleagueorganiser.exceptions.League.LeagueNameAlreadyExistException;
import com.example.communityminifootballleagueorganiser.exceptions.League.LeagueNotFoundException;
import com.example.communityminifootballleagueorganiser.models.dtos.LeagueDTO;
import com.example.communityminifootballleagueorganiser.models.entities.League;
import com.example.communityminifootballleagueorganiser.repositories.LeagueRepository;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Null;

@Component
public class LeagueServiceValidationImpl implements LeagueServiceValidation {

    private final LeagueRepository leagueRepository;

    public LeagueServiceValidationImpl(LeagueRepository leagueRepository) {
        this.leagueRepository = leagueRepository;
    }

    @Override
    public void validateUniqueLeagueName(String leagueName) {
        if (leagueRepository.existsByName(leagueName)) {
            throw new LeagueNameAlreadyExistException("League with name :  " + leagueName + " already exist!");
        }
    }

    @Override
    public League getValidLeague(Long leagueId) {
        return leagueRepository.findById(leagueId)
                .orElseThrow(() -> new LeagueNotFoundException("League with id: " + leagueId + " not found"));
    }
}
