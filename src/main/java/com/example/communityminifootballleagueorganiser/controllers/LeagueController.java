package com.example.communityminifootballleagueorganiser.controllers;

import com.example.communityminifootballleagueorganiser.exceptions.League.LeagueNameAlreadyExistException;
import com.example.communityminifootballleagueorganiser.exceptions.League.LeagueNotFoundException;
import com.example.communityminifootballleagueorganiser.exceptions.Player.PlayerAlreadyExistException;
import com.example.communityminifootballleagueorganiser.models.dtos.LeagueDTO;
import com.example.communityminifootballleagueorganiser.models.entities.League;
import com.example.communityminifootballleagueorganiser.services.league_services.LeagueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/league")
public class LeagueController {

    private final LeagueService leagueService;

    public LeagueController(LeagueService leagueService) {
        this.leagueService = leagueService;
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
    public ResponseEntity<LeagueDTO> updatedLeagueName(@PathVariable Long leagueId, LeagueDTO leagueDTO) {
        return ResponseEntity.ok(leagueService.updateLeagueName(leagueId, leagueDTO));
    }

    @DeleteMapping("/{leagueId}")
    public ResponseEntity<String> deleteLeagueById(@PathVariable Long leagueId) {
        leagueService.deleteLeague(leagueId);
        return ResponseEntity.ok("League with id: " + leagueId + " deleted.");
    }
}
