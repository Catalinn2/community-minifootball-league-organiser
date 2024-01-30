package com.example.communityminifootballleagueorganiser.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class MatchResultDTO {
    private Long matchId;
    private int team1Score;
    private int team2Score;
    private List<String> scorers;
}
