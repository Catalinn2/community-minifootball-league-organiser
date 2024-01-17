package com.example.communityminifootballleagueorganiser.services;

import com.example.communityminifootballleagueorganiser.models.dtos.PlayerDTO;
import com.example.communityminifootballleagueorganiser.models.entities.Player;
import com.example.communityminifootballleagueorganiser.repositories.PlayerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    private final ModelMapper modelMapper;
    private final PlayerServiceValidation playerServiceValidation;

    @Override
    public PlayerDTO createPlayer(PlayerDTO playerDTO) {
        Player player = modelMapper.map(playerDTO, Player.class);
        Player savedPlayer = playerRepository.save(player);
        log.info("Player {} / {} created.", savedPlayer.getId(), savedPlayer.getFirstName());
        return modelMapper.map(savedPlayer, PlayerDTO.class);
    }

    @Override
    public List<PlayerDTO> getAllPlayers() {
        List<Player> playerList = playerRepository.findAll();
        log.info("Player list : {}", "getAllPlayers");
        return playerList.stream()
                .map(player -> modelMapper.map(player, PlayerDTO.class))
                .toList();
    }

    @Override
    public PlayerDTO getPlayerById(Long playerId) {
        Player player = playerServiceValidation.getValidPlayer(playerId, "getPlayerById");
        log.info("Player with id {} : {}", playerId, "getPlayerById");
        return modelMapper.map(player, PlayerDTO.class);
    }

    @Override
    public PlayerDTO updatePlayerById(Long playerId, PlayerDTO updatedPlayerDto) {
        Player existingPlayer = playerServiceValidation.getValidPlayer(playerId, "updatePlayer");
        existingPlayer.setFirstName(updatedPlayerDto.getFirstName());
        existingPlayer.setLastName(updatedPlayerDto.getLastName());
        existingPlayer.setPlayerPosition(updatedPlayerDto.getPlayerPosition());
        existingPlayer.setGoals(updatedPlayerDto.getGoals());

        Player updatedPlayer = playerRepository.save(existingPlayer);
        log.info("Player with id {} updated", playerId);
        return modelMapper.map(updatedPlayer, PlayerDTO.class);
    }

    @Override
    public void deletePlayerById(Long playerId) {
        playerServiceValidation.getValidPlayer(playerId, "deletePlayerById");
        playerRepository.deleteById(playerId);
        log.info("Player with id {} deleted");
    }
}
