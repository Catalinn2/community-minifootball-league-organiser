package com.example.communityminifootballleagueorganiser.services.league_services;

import com.example.communityminifootballleagueorganiser.models.dtos.LeagueDTO;
import com.example.communityminifootballleagueorganiser.models.entities.League;
import com.example.communityminifootballleagueorganiser.repositories.LeagueRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class LeagueServiceImp implements LeagueService {

    private final LeagueRepository leagueRepository;
    private final ModelMapper modelMapper;
    private final LeagueServiceValidation leagueServiceValidation;

    @Override
    public LeagueDTO createLeague(LeagueDTO leagueDTO) {
        leagueServiceValidation.validateUniqueLeagueName(leagueDTO.getName());
        League league = modelMapper.map(leagueDTO, League.class);
        League savedLeague = leagueRepository.save(league);
        log.info("League with : id " + savedLeague.getId() + " : " + savedLeague.getName() + " created.");
        return modelMapper.map(savedLeague, LeagueDTO.class);
    }

    @Override
    public List<LeagueDTO> getAllLeagues() {
        List<League> leagueList = leagueRepository.findAll();
        log.info("League list accesed.");
        return leagueList.stream()
                .map(league -> modelMapper.map(league, LeagueDTO.class))
                .toList();
    }

    @Override
    public LeagueDTO getLeagueById(Long leagueId) {
       League league = leagueServiceValidation.getValidLeague(leagueId);
       log.info("League with id : " + leagueId + " retrived");
       return modelMapper.map(league, LeagueDTO.class);

    }

    @Override
    public LeagueDTO updateLeagueName(Long leagueId, LeagueDTO leagueDTO ) {
        League existingLeague = leagueServiceValidation.getValidLeague(leagueId);
        existingLeague.setName(leagueDTO.getName());
        League updatedLeague = leagueRepository.save(existingLeague);
        log.info("Name for league id : " + leagueId + " has changed. New name : " + leagueDTO.getName());
        return modelMapper.map(updatedLeague, LeagueDTO.class);
    }

    @Override
    public void deleteLeague(Long leagueId) {
        leagueRepository.deleteById(leagueId);
    }
}
