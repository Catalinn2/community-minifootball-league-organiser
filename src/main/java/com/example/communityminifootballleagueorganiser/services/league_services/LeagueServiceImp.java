package com.example.communityminifootballleagueorganiser.services.league_services;

import com.example.communityminifootballleagueorganiser.models.dtos.LeagueDTO;
import com.example.communityminifootballleagueorganiser.models.dtos.PlayerDTO;
import com.example.communityminifootballleagueorganiser.models.dtos.TeamDTO;
import com.example.communityminifootballleagueorganiser.models.entities.League;
import com.example.communityminifootballleagueorganiser.models.entities.Match;
import com.example.communityminifootballleagueorganiser.models.entities.Team;
import com.example.communityminifootballleagueorganiser.repositories.LeagueRepository;
import com.example.communityminifootballleagueorganiser.repositories.MatchRepository;
import com.example.communityminifootballleagueorganiser.repositories.TeamRepository;
import com.example.communityminifootballleagueorganiser.services.team_services.TeamService;
import com.example.communityminifootballleagueorganiser.services.team_services.TeamServiceValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class LeagueServiceImp implements LeagueService {

    private final LeagueRepository leagueRepository;
    private final ModelMapper modelMapper;
    private final LeagueServiceValidation leagueServiceValidation;
    private final TeamRepository teamRepository;
    private final TeamServiceValidation teamServiceValidation;
    private final MatchRepository matchRepository;

    @Override
    public LeagueDTO createLeague(LeagueDTO leagueDTO) {
        leagueServiceValidation.validateUniqueLeagueName(leagueDTO.getName());
        League league = modelMapper.map(leagueDTO, League.class);
        League savedLeague = leagueRepository.save(league);
        log.info("League with : id " + savedLeague.getLeagueId() + " : " + savedLeague.getName() + " created.");
        return modelMapper.map(savedLeague, LeagueDTO.class);
    }

    @Override
    public List<LeagueDTO> getAllLeagues() {
        List<League> leagueList = leagueRepository.findAll();
        log.info("League list accesed.");
        return leagueList.stream()
                .map(league -> modelMapper.map(league, LeagueDTO.class))
                .toList();
    }

    @Override
    public LeagueDTO getLeagueById(Long leagueId) {
        League league = leagueServiceValidation.getValidLeague(leagueId);
        log.info("League with id : " + leagueId + " retrived");
        return modelMapper.map(league, LeagueDTO.class);

    }

    @Override
    public LeagueDTO updateLeagueName(Long leagueId, LeagueDTO leagueDTO) {
        League existingLeague = leagueServiceValidation.getValidLeague(leagueId);
        existingLeague.setName(leagueDTO.getName());
        League updatedLeague = leagueRepository.save(existingLeague);
        log.info("Name for league id : " + leagueId + " has changed. New name : " + leagueDTO.getName());
        return modelMapper.map(updatedLeague, LeagueDTO.class);
    }

    @Override
    public void deleteLeague(Long leagueId) {
        leagueRepository.deleteById(leagueId);
    }

    @Override
    public void addTeamToLeague(Long leagueId, Long teamId) {
        League league = leagueServiceValidation.getValidLeague(leagueId);
        Team team = teamServiceValidation.getValidTeam(teamId);
        league.getTeamList().add(team);
        team.setLeague(league);
        leagueRepository.save(league);
        teamRepository.save(team);
        log.info("Added " + team.getName() + " with id " + teamId + " to the league" + league.getName() + " with id " + leagueId);
    }

    @Override
    public List<TeamDTO> getTeamsByLeague(Long leagueId) {
        League league = leagueServiceValidation.getValidLeague(leagueId);
        return league.getTeamList().stream()
                .map(team -> modelMapper.map(team, TeamDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PlayerDTO> getPlayersByLeague(Long leagueId) {
        League league = leagueServiceValidation.getValidLeague(leagueId);
        return league.getTeamList().stream()
                .flatMap(team -> team.getPlayerList().stream())
                .map(player -> modelMapper.map(player, PlayerDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void startLeague(Long leagueId) {
        League league = leagueServiceValidation.getValidLeague(leagueId);

        if (league.getTeamList().size() < 2) {
            throw new IllegalStateException("Not enough teams to start the league");
        }

        if (!league.isStarded()) {
            league.setStarded(true);
            generateMatches(league);
            leagueRepository.save(league);
        }
    }

    @Override
    public void generateMatches(League league) {
        List<Team> teams = league.getTeamList();
        for (int index = 0; index < teams.size(); index++) {
            for (int jndex = index + 1; jndex < teams.size(); jndex++) {
                Match match = new Match();
                match.setTeam1(teams.get(index));
                match.setTeam2(teams.get(jndex));
                match.setLeague(league);
                matchRepository.save(match);
            }
        }
    }

    @Override
    public List<TeamDTO> getLeagueLeaderboard(Long leagueId) {
        League league = leagueServiceValidation.getValidLeague(leagueId);

        return league.getTeamList().stream()
                .sorted(Comparator.comparingInt(Team::getPoints).reversed())
                .map(team -> modelMapper.map(team, TeamDTO.class))
                .collect(Collectors.toList());
    }
}
