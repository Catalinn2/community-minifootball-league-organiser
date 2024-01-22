package com.example.communityminifootballleagueorganiser.controllers;

import com.example.communityminifootballleagueorganiser.models.dtos.LeagueDTO;
import com.example.communityminifootballleagueorganiser.models.entities.League;
import com.example.communityminifootballleagueorganiser.services.league_services.LeagueService;
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

    @GetMapping("/{leagueId}")
    public ResponseEntity<LeagueDTO> getLeagueById(@PathVariable Long leagueId) {
        return ResponseEntity.ok(leagueService.getLeagueById(leagueId));
    }

    @GetMapping
    public ResponseEntity<List<LeagueDTO>> leagueList() {
        return ResponseEntity.ok(leagueService.getAllLeagues());
    }

    @PutMapping("/{leagueId}")
    public ResponseEntity<LeagueDTO> updatedLeagueName(@PathVariable Long leagueId, LeagueDTO leagueDTO) {
        return ResponseEntity.ok(leagueService.upDateLeagueName(leagueId, leagueDTO));
    }
    @DeleteMapping("/{leagueId}")
    public ResponseEntity<String> deleteLeagueById(@PathVariable Long leagueId){
        leagueService.deleteLeague(leagueId);
        return ResponseEntity.ok("League with id: " + leagueId + " deleted.");
    }
}
