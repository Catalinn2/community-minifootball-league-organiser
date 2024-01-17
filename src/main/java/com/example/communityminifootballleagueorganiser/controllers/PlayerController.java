package com.example.communityminifootballleagueorganiser.controllers;

import com.example.communityminifootballleagueorganiser.models.dtos.PlayerDTO;
import com.example.communityminifootballleagueorganiser.services.PlayerService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@NoArgsConstructor(force = true)
@AllArgsConstructor
@RequestMapping("/api/players")
public class PlayerController {

    private final PlayerService playerService;


    @PostMapping
    public ResponseEntity<PlayerDTO> createPlayer(@Valid @RequestBody PlayerDTO playerDTO) {
        return ResponseEntity.ok(playerService.createPlayer(playerDTO));
    }

    @GetMapping
    public ResponseEntity<List<PlayerDTO>> getAllPlayers() {
        return ResponseEntity.ok(playerService.getAllPlayers());
    }

    @GetMapping("/{playerId}")
    public ResponseEntity<PlayerDTO> getPlayerById(@PathVariable Long playerId) {
        return ResponseEntity.ok(playerService.getPlayerById(playerId));
    }

    @DeleteMapping("/{playerId")
    public ResponseEntity<String> deletePlayerById(@PathVariable Long playerId) {
        playerService.deletePlayerById(playerId);
        return ResponseEntity.ok("Player with " + playerId + " id deleted.");
    }

    @PutMapping
    public ResponseEntity<PlayerDTO> updatePlayerById(@PathVariable Long playerId, PlayerDTO playerDTO) {
        return ResponseEntity.ok(playerService.updatePlayerById(playerId, playerDTO));
    }
}
