package com.example.communityminifootballleagueorganiser.services.team_services;

import com.example.communityminifootballleagueorganiser.exceptions.League.LeagueNotFoundException;
import com.example.communityminifootballleagueorganiser.exceptions.Team.TeamNotFoundException;
import com.example.communityminifootballleagueorganiser.models.dtos.LeagueDTO;
import com.example.communityminifootballleagueorganiser.models.dtos.PlayerDTO;
import com.example.communityminifootballleagueorganiser.models.dtos.TeamDTO;
import com.example.communityminifootballleagueorganiser.models.entities.League;
import com.example.communityminifootballleagueorganiser.models.entities.Player;
import com.example.communityminifootballleagueorganiser.models.entities.Team;
import com.example.communityminifootballleagueorganiser.repositories.LeagueRepository;
import com.example.communityminifootballleagueorganiser.repositories.PlayerRepository;
import com.example.communityminifootballleagueorganiser.repositories.TeamRepository;
import com.example.communityminifootballleagueorganiser.services.player_services.PlayerServiceValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final ModelMapper modelMapper;
    private final TeamServiceValidation teamServiceValidation;
    private final PlayerServiceValidation playerServiceValidation;
    private final PlayerRepository playerRepository;

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
        TeamDTO teamDTO = modelMapper.map(team, TeamDTO.class);
        if (team.getLeague() != null) {
            teamDTO.setLeagueName(team.getLeague().getName());
        }
        return teamDTO;
    }

    @Override
    public TeamDTO updateTeamName(Long teamId, TeamDTO teamDTO) {
        if (teamDTO.getName() == null || teamDTO.getName().isEmpty()) {
            throw new IllegalArgumentException("Team name cannot be null or empty");
        }
        Team existingTeam = teamServiceValidation.getValidTeam(teamId);
        existingTeam.setName(teamDTO.getName());
        Team updatedTeam = teamRepository.save(existingTeam);
        log.info("Name for team with id{} changed into {}", teamId, updatedTeam.getName());
        return modelMapper.map(updatedTeam, TeamDTO.class);
    }

    @Override
    public void deleteTeam(Long teamId) {
        teamRepository.deleteById(teamId);
        log.info("Team with id{} deleted", teamId);
    }

    @Override
    public void addPlayerToTeam(Long teamId, Long playerId) {
        Team team = teamServiceValidation.getValidTeam(teamId);
        Player player = playerServiceValidation.getValidPlayer(playerId);
        team.getPlayerList().add(player);
        player.setTeam(team);
        teamRepository.save(team);
        playerRepository.save(player);
    }

    @Override
    public List<PlayerDTO> getPlayersByTeam(Long teamId) {
        Team team = teamServiceValidation.getValidTeam(teamId);
        return team.getPlayerList().stream()
                .map(player -> modelMapper.map(player , PlayerDTO.class))
                .collect(Collectors.toList());
    }
}
