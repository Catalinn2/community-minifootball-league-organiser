package com.example.communityminifootballleagueorganiser.services.team_services;

import com.example.communityminifootballleagueorganiser.models.dtos.TeamDTO;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TeamService {

    TeamDTO createTeam(TeamDTO teamDTO);

    List<TeamDTO> getAllTeams();

    TeamDTO getTeam(Long teamId);

    TeamDTO updateTeamName(Long teamId, TeamDTO teamDTO);

    void deleteTeam(Long teamId);
}
