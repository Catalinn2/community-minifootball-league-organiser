package com.example.communityminifootballleagueorganiser.controllers;

import com.example.communityminifootballleagueorganiser.exceptions.League.LeagueNameAlreadyExistException;
import com.example.communityminifootballleagueorganiser.exceptions.League.LeagueNotFoundException;
import com.example.communityminifootballleagueorganiser.exceptions.Player.PlayerAlreadyExistException;
import com.example.communityminifootballleagueorganiser.models.dtos.LeagueDTO;
import com.example.communityminifootballleagueorganiser.models.dtos.PlayerDTO;
import com.example.communityminifootballleagueorganiser.models.dtos.TeamDTO;
import com.example.communityminifootballleagueorganiser.models.entities.League;
import com.example.communityminifootballleagueorganiser.models.entities.Team;
import com.example.communityminifootballleagueorganiser.services.league_services.LeagueService;
import com.example.communityminifootballleagueorganiser.services.player_services.PlayerService;
import com.example.communityminifootballleagueorganiser.services.team_services.TeamService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/leagues")
public class LeagueController {


    private final LeagueService leagueService;
    private final TeamService teamService;
    private final PlayerService playerService;

    public LeagueController(LeagueService leagueService, TeamService teamService, PlayerService playerService) {
        this.leagueService = leagueService;
        this.teamService = teamService;
        this.playerService = playerService;
    }

    @PostMapping
    public ResponseEntity<LeagueDTO> createLeague(@Valid @RequestBody LeagueDTO leagueDTO) {
        return ResponseEntity.ok(leagueService.createLeague(leagueDTO));
    }

    @ExceptionHandler(LeagueNameAlreadyExistException.class)
    public ResponseEntity<String> handleDuplicateLeagueName(LeagueNameAlreadyExistException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @GetMapping("/{leagueId}")
    public ResponseEntity<LeagueDTO> getLeagueById(@PathVariable Long leagueId) {
        return ResponseEntity.ok(leagueService.getLeagueById(leagueId));
    }

    @ExceptionHandler(LeagueNotFoundException.class)
    public ResponseEntity<String> handleLeagueNotFound(LeagueNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @GetMapping
    public ResponseEntity<List<LeagueDTO>> leagueList() {
        return ResponseEntity.ok(leagueService.getAllLeagues());
    }

    @PutMapping("/{leagueId}")
    public ResponseEntity<LeagueDTO> updatedLeagueName(@PathVariable Long leagueId, @RequestBody LeagueDTO leagueDTO) {
        return ResponseEntity.ok(leagueService.updateLeagueName(leagueId, leagueDTO));
    }

    @DeleteMapping("/{leagueId}")
    public ResponseEntity<String> deleteLeagueById(@PathVariable Long leagueId) {
        leagueService.deleteLeague(leagueId);
        return ResponseEntity.ok("League with id: " + leagueId + " deleted.");
    }

    @PostMapping("/{leagueId}/teams/{teamId}")
    public ResponseEntity<String> addTeamToLeague(@PathVariable Long leagueId, @PathVariable Long teamId) {
        leagueService.addTeamToLeague(leagueId, teamId);
        return ResponseEntity.ok("Team with id: " + teamId + " added to the league with id: " + leagueId);
    }

    @GetMapping("/{leagueId}/teams")
    public ResponseEntity<List<TeamDTO>> getTeamNamesByLeague(@PathVariable Long leagueId) {
        List<TeamDTO> teamNames = leagueService.getTeamsByLeague(leagueId);
        return ResponseEntity.ok(teamNames);
    }

    @GetMapping("/{leagueId}/players")
    public ResponseEntity<List<PlayerDTO>> getPlayersByLeague(@PathVariable Long leagueId) {
        List<PlayerDTO> playerList = leagueService.getPlayersByLeague(leagueId);
        return ResponseEntity.ok(playerList);
    }

    @PostMapping("/{leagueId}/start")
    public ResponseEntity<String> startLeague(@PathVariable Long leagueId) {
        leagueService.startLeague(leagueId);
        return ResponseEntity.ok().body("League started and schedule generated");
    }

    @GetMapping("/{leagueId}/leaderboard")
    public ResponseEntity<List<TeamDTO>> getLeaderboardInLeague(@PathVariable Long leagueId) {
        return ResponseEntity.ok(leagueService.getLeagueLeaderboard(leagueId));
    }
    @GetMapping("/{leagueId}/topscorers")
    public ResponseEntity<List<PlayerDTO>> getTopScorers(@PathVariable Long leagueId) {
        List<PlayerDTO> topScorers = playerService.getTopScorers(leagueId);
        return ResponseEntity.ok(topScorers);
    }
}
