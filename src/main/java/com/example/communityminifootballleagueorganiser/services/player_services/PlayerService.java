package com.example.communityminifootballleagueorganiser.services.player_services;

import com.example.communityminifootballleagueorganiser.models.dtos.PlayerDTO;

import java.util.List;

public interface PlayerService {

    PlayerDTO createPlayer(PlayerDTO playerDTO);

    List<PlayerDTO> getAllPlayers();

    PlayerDTO getPlayerById(Long playerId);

    PlayerDTO updatePlayerById(Long playerId, PlayerDTO updatedPlayerDto);

    void deletePlayerById(Long playerId);
}
