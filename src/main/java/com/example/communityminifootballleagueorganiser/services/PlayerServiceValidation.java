package com.example.communityminifootballleagueorganiser.services;

import com.example.communityminifootballleagueorganiser.models.entities.Player;

public interface PlayerServiceValidation {



    Player getValidPlayer(Long playerId, String methodName);
}
