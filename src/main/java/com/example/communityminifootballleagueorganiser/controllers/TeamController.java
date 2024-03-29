package com.example.communityminifootballleagueorganiser.controllers;

import com.example.communityminifootballleagueorganiser.exceptions.League.LeagueNameAlreadyExistException;
import com.example.communityminifootballleagueorganiser.exceptions.League.LeagueNotFoundException;
import com.example.communityminifootballleagueorganiser.exceptions.Team.TeamNameAlreadyExistException;
import com.example.communityminifootballleagueorganiser.exceptions.Team.TeamNotFoundException;
import com.example.communityminifootballleagueorganiser.models.dtos.LeagueDTO;
import com.example.communityminifootballleagueorganiser.models.dtos.PlayerDTO;
import com.example.communityminifootballleagueorganiser.models.dtos.TeamDTO;
import com.example.communityminifootballleagueorganiser.models.entities.Team;
import com.example.communityminifootballleagueorganiser.services.team_services.TeamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    private final TeamService teamService;


    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping
    public ResponseEntity<TeamDTO> createTeam(@Valid @RequestBody TeamDTO teamDTO) {
        return ResponseEntity.ok(teamService.createTeam(teamDTO));
    }

    @ExceptionHandler({TeamNameAlreadyExistException.class})
    public ResponseEntity<String> handleDuplicateTeamName(TeamNameAlreadyExistException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @GetMapping("/{teamId}")
    public ResponseEntity<TeamDTO> getTeamById(@PathVariable Long teamId) {
        return ResponseEntity.ok(teamService.getTeam(teamId));
    }

    @ExceptionHandler({TeamNotFoundException.class})
    public ResponseEntity<String> handleTeamNotFound(TeamNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @GetMapping
    public ResponseEntity<List<TeamDTO>> teamList() {
        return ResponseEntity.ok(teamService.getAllTeams());
    }

    @PutMapping("/{teamId}")
    public ResponseEntity<TeamDTO> updatedTeamName(@PathVariable Long teamId, @RequestBody TeamDTO teamDTO) {
        return ResponseEntity.ok(teamService.updateTeamName(teamId, teamDTO));
    }

    @DeleteMapping("/{teamId}")
    public ResponseEntity<String> deleteTeamById(@PathVariable Long teamId) {
        teamService.deleteTeam(teamId);
        return ResponseEntity.ok("Team with id: " + teamId + " deleted.");
    }

    @PostMapping("/{teamId}/players/{playerId}")
    public ResponseEntity<String> addPlayerToTeam(@PathVariable Long teamId, @PathVariable Long playerId) {
        teamService.addPlayerToTeam(teamId, playerId);
        return ResponseEntity.ok("Player with id: " + playerId + " added to the team with id: " + teamId);
    }
    @GetMapping("/{teamId}/players")
    public ResponseEntity<List<PlayerDTO>> getPlayersByTeam(@PathVariable Long teamId){
        List<PlayerDTO> playersList = teamService.getPlayersByTeam(teamId);
        return ResponseEntity.ok(playersList);
    }
}
