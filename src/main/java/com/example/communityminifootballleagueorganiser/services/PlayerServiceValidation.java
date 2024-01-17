package com.example.communityminifootballleagueorganiser.services;

import com.example.communityminifootballleagueorganiser.models.dtos.PlayerDTO;
import com.example.communityminifootballleagueorganiser.models.entities.Player;

public interface PlayerServiceValidation {

    void validatePlayerAlreadyExist(PlayerDTO playerDTO);

    Player getValidPlayer(Long playerId, String methodName);
}
