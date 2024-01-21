package com.example.communityminifootballleagueorganiser.services.player_services;

import com.example.communityminifootballleagueorganiser.exceptions.Player.PlayerAlreadyExistException;
import com.example.communityminifootballleagueorganiser.exceptions.Player.PlayerNotFoundException;
import com.example.communityminifootballleagueorganiser.models.dtos.PlayerDTO;
import com.example.communityminifootballleagueorganiser.models.entities.Player;
import com.example.communityminifootballleagueorganiser.repositories.PlayerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PlayerServiceValidationImpl implements PlayerServiceValidation {

    private final PlayerRepository playerRepository;


    public PlayerServiceValidationImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public void playerAlreadyExist(PlayerDTO playerDTO) {
        Player playerFound = playerRepository.findPlayerByLegitimationNumber(playerDTO.getLegitimationNumber());
        if (playerFound != null) {
            throw new PlayerAlreadyExistException("Player with " + playerDTO.getLegitimationNumber() + " legitimation number already exist!");
        }
    }


    @Override
    public Player getValidPlayer(Long playerId) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new PlayerNotFoundException("Player with the id " + playerId + " not found."));
        log.info("Player with the id {} not found", playerId);
        return player;
    }
}
