package com.example.communityminifootballleagueorganiser.services.team_services;

import com.example.communityminifootballleagueorganiser.models.dtos.TeamDTO;
import com.example.communityminifootballleagueorganiser.models.entities.Team;
import com.example.communityminifootballleagueorganiser.repositories.TeamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final ModelMapper modelMapper;
    private final TeamServiceValidation teamServiceValidation;

    @Override
    public TeamDTO createTeam(TeamDTO teamDTO) {
        teamServiceValidation.validateUniqueName(teamDTO.getName());
        Team team = modelMapper.map(teamDTO, Team.class);
        Team savedTeam = teamRepository.save(team);
        log.info("Team with id: {} and name : {} created.", savedTeam.getTeamId(), savedTeam.getName());
        return modelMapper.map(savedTeam, TeamDTO.class);
    }

    @Override
    public List<TeamDTO> getAllTeams() {
        List<Team> teamList = teamRepository.findAll();
        log.info("Teamlist accessed.");
        return teamList.stream()
                .map(team -> modelMapper.map(team, TeamDTO.class))
                .toList();
    }

    @Override
    public TeamDTO getTeam(Long teamId) {
        Team team = teamServiceValidation.getValidTeam(teamId);
        log.info("League with id: {} retrieved", teamId);
        return modelMapper.map(team, TeamDTO.class);
    }

    @Override
    public TeamDTO updateTeamName(Long teamId, TeamDTO teamDTO) {
        Team existingTeam = teamServiceValidation.getValidTeam(teamId);
        existingTeam.setName(teamDTO.getName());
        Team updatedTeam = teamRepository.save(existingTeam);
        log.info("Name for team with id{} changed into {}", teamId, teamDTO.getName());
        return modelMapper.map(updatedTeam, TeamDTO.class);
    }

    @Override
    public void deleteTeam(Long teamId) {
        teamRepository.deleteById(teamId);
        log.info("Team with id{} deleted", teamId);
    }
}
