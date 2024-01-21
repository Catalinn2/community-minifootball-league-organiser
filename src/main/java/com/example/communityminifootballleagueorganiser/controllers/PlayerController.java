package com.example.communityminifootballleagueorganiser.controllers;

import com.example.communityminifootballleagueorganiser.exceptions.Player.PlayerAlreadyExistException;
import com.example.communityminifootballleagueorganiser.exceptions.Player.PlayerNotFoundException;
import com.example.communityminifootballleagueorganiser.models.dtos.PlayerDTO;
import com.example.communityminifootballleagueorganiser.services.player_services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/players")
public class PlayerController {
    @Autowired
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping
    public ResponseEntity<PlayerDTO> createPlayer(@Valid @RequestBody PlayerDTO playerDTO) {
        return ResponseEntity.ok(playerService.createPlayer(playerDTO));
    }

    @ExceptionHandler(PlayerAlreadyExistException.class)
    public ResponseEntity<String> handlePlayerAlreadyExist(PlayerAlreadyExistException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @GetMapping
    public ResponseEntity<List<PlayerDTO>> getAllPlayers() {
        return ResponseEntity.ok(playerService.getAllPlayers());
    }

    @GetMapping("/{playerId}")
    public ResponseEntity<PlayerDTO> getPlayerById(@PathVariable Long playerId) {
        return ResponseEntity.ok(playerService.getPlayerById(playerId));
    }

    @ExceptionHandler(PlayerNotFoundException.class)
    public ResponseEntity<String> handlePlayerNotFoundException(PlayerNotFoundException exception, PlayerDTO playerDTO) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @DeleteMapping("/{playerId}")
    public ResponseEntity<String> deletePlayerById(@PathVariable Long playerId) {
        playerService.deletePlayerById(playerId);
        return ResponseEntity.ok("Player with " + playerId + " id deleted.");
    }

    @PutMapping("/{playerId}")
    public ResponseEntity<PlayerDTO> updatePlayerById(@PathVariable Long playerId, PlayerDTO playerDTO) {
        return ResponseEntity.ok(playerService.updatePlayerById(playerId, playerDTO));
    }
}
