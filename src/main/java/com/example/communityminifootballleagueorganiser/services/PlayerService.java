package com.example.communityminifootballleagueorganiser.services;

import com.example.communityminifootballleagueorganiser.models.dtos.PlayerDTO;

import java.util.List;

public interface PlayerService {

    PlayerDTO createPlayer(PlayerDTO playerDTO);

    List<PlayerDTO> getAllPlayers();

    PlayerDTO getPlayerById(Long playerId);

    PlayerDTO updatePlayer(Long playerId, PlayerDTO updatedPlayerDto);

    void deletePlayerById(Long playerId);
}
