package com.example.communityminifootballleagueorganiser.services.match_services;

import com.example.communityminifootballleagueorganiser.exceptions.Match.MatchAlreadyPlayedException;
import com.example.communityminifootballleagueorganiser.exceptions.Match.MatchNotFoundException;
import com.example.communityminifootballleagueorganiser.models.dtos.MatchDTO;
import com.example.communityminifootballleagueorganiser.models.dtos.MatchResultDTO;
import com.example.communityminifootballleagueorganiser.models.entities.Match;
import com.example.communityminifootballleagueorganiser.models.entities.Player;
import com.example.communityminifootballleagueorganiser.models.entities.Team;
import com.example.communityminifootballleagueorganiser.repositories.MatchRepository;
import com.example.communityminifootballleagueorganiser.repositories.PlayerRepository;
import com.example.communityminifootballleagueorganiser.repositories.TeamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class MatchServiceImpl implements MatchService {

    private final MatchRepository matchRepository;
    private final ModelMapper modelMapper;
    private final MatchServiceValidaton matchServiceValidaton;
    private final Random random;
    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    @Override
    public List<MatchDTO> getMatchesByLeague(Long leagueId) {
        matchServiceValidaton.validateMatchesExistForLeague(leagueId);
        List<Match> matches = matchRepository.findByLeague_LeagueId(leagueId);
        return matches.stream()
                .map(match -> modelMapper.map(match, MatchDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public MatchDTO getMatchByIdAndLeagueId(Long matchId, Long leagueId) {
        matchServiceValidaton.validateMatchExistInLeague(matchId, leagueId);
        Match match = matchRepository.findByMatchIdAndLeague_LeagueId(matchId, leagueId)
                .orElseThrow(() -> new MatchNotFoundException("Match not found!"));
        return modelMapper.map(match, MatchDTO.class);
    }

    @Transactional
    @Override
    public MatchResultDTO playMatch(Long matchId) {
        Match match = matchRepository.findById(matchId)
                .orElseThrow(() -> new MatchNotFoundException("Match with id: " + matchId + " not found"));

        if (match.getTeam1Score() != null || match.getTeam2Score() != null) {
            throw new MatchAlreadyPlayedException("Match has already been played");
        }


        int team1Score = random.nextInt(6);
        int team2Score = random.nextInt(6);

        match.setTeam1Score(team1Score);
        match.setTeam2Score(team2Score);

        updateTeamStats(match.getTeam1(), team1Score, team2Score);
        updateTeamStats(match.getTeam2(), team2Score, team1Score);

        matchRepository.save(match);

        List<String> scorers = new ArrayList<>();
        scorers.addAll(distributeGoalsAmongPlayers(match.getTeam1(), team1Score));
        scorers.addAll(distributeGoalsAmongPlayers(match.getTeam2(), team2Score));

        MatchResultDTO result = new MatchResultDTO();
        result.setMatchId(matchId);
        result.setTeam1Score(team1Score);
        result.setTeam2Score(team2Score);
        result.setScorers(scorers);

        return result;
    }

    @Override
    public void updateTeamStats(Team team, int teamScore, int opponentScore) {
        team.setMatchesPlayed(team.getMatchesPlayed() + 1);


        if (teamScore > opponentScore) {
            team.setPoints(team.getPoints() + 3);
        } else if (teamScore == opponentScore) {
            team.setPoints(team.getPoints() + 1);
        }
        teamRepository.save(team);

    }

    @Override
    public List<String> distributeGoalsAmongPlayers(Team team, int goals) {
        List<String> scorers = new ArrayList<>();
        for (int index = 0; index < goals; index++) {
            if (team.getPlayerList().isEmpty()) {
                continue;
            }

            int randomPlayerIndex = random.nextInt(team.getPlayerList().size());
            Player player = team.getPlayerList().get(randomPlayerIndex);
            player.setGoals(player.getGoals() + 1);
            scorers.add(player.getLastName());
            playerRepository.save(player);
        }
        return scorers;
    }
}
