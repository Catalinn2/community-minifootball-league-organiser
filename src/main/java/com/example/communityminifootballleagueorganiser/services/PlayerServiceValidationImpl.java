package com.example.communityminifootballleagueorganiser.services;

import com.example.communityminifootballleagueorganiser.exceptions.Player.PlayerNotFoundException;
import com.example.communityminifootballleagueorganiser.models.dtos.PlayerDTO;
import com.example.communityminifootballleagueorganiser.models.entities.Player;
import com.example.communityminifootballleagueorganiser.repositories.PlayerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
            String errorMessage = "Player with " + playerDTO.getLegitimationNumber() + " legitimation number already exist!";
            throw new errorMessage;
        }
    }


    @Override
    public Player getValidPlayer(Long playerId, String methodName) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new PlayerNotFoundException("Player with the id " + playerId + " not found."));
        log.info("Player with the id {} : {}", playerId, methodName);
        return player;

    }
}
