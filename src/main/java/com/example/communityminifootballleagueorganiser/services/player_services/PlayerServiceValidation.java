package com.example.communityminifootballleagueorganiser.services.player_services;

import com.example.communityminifootballleagueorganiser.models.dtos.PlayerDTO;
import com.example.communityminifootballleagueorganiser.models.entities.Player;

public interface PlayerServiceValidation {

    void playerAlreadyExist(PlayerDTO playerDTO);

    Player getValidPlayer(Long playerId);
}
