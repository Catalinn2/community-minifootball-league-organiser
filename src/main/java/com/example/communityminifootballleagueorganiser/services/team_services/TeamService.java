package com.example.communityminifootballleagueorganiser.services.team_services;

import com.example.communityminifootballleagueorganiser.models.dtos.PlayerDTO;
import com.example.communityminifootballleagueorganiser.models.dtos.TeamDTO;
import com.example.communityminifootballleagueorganiser.models.entities.League;
import com.example.communityminifootballleagueorganiser.models.entities.Team;

import java.util.List;
import java.util.Optional;


public interface TeamService {

    TeamDTO createTeam(TeamDTO teamDTO);

    List<TeamDTO> getAllTeams();

    TeamDTO getTeam(Long teamId);

    TeamDTO updateTeamName(Long teamId, TeamDTO teamDTO);

    void deleteTeam(Long teamId);

    void addPlayerToTeam(Long teamId, Long playerId);

    List<PlayerDTO> getPlayersByTeam(Long teamId);
}
