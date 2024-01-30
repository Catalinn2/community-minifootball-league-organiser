package com.example.communityminifootballleagueorganiser.controllers;

import com.example.communityminifootballleagueorganiser.exceptions.Match.MatchAlreadyPlayedException;
import com.example.communityminifootballleagueorganiser.models.dtos.MatchDTO;
import com.example.communityminifootballleagueorganiser.models.dtos.MatchResultDTO;
import com.example.communityminifootballleagueorganiser.services.match_services.MatchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leagues/{leagueId}/matches")
public class MatchController {

    private MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping
    public ResponseEntity<List<MatchDTO>> getMatchesByLeague(@PathVariable Long leagueId) {
        List<MatchDTO> matches = matchService.getMatchesByLeague(leagueId);
        return ResponseEntity.ok(matches);
    }

    @GetMapping("/{matchId}")
    public ResponseEntity<MatchDTO> getMatchByIdAndLeague(@PathVariable Long leagueId, @PathVariable Long matchId) {
        MatchDTO match = matchService.getMatchByIdAndLeagueId(matchId, leagueId);
        return ResponseEntity.ok(match);
    }
    @PostMapping("/{matchId}/play")
    public ResponseEntity<MatchResultDTO> playMatch(@PathVariable Long matchId) {
        MatchResultDTO result = matchService.playMatch(matchId);
        return ResponseEntity.ok(result);
    }
    @ExceptionHandler(MatchAlreadyPlayedException.class)
    public ResponseEntity<String> handleMatchAlreadyPlayed(MatchAlreadyPlayedException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
