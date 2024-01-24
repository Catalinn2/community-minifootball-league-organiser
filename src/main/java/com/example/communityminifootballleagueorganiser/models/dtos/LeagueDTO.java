package com.example.communityminifootballleagueorganiser.models.dtos;

import com.example.communityminifootballleagueorganiser.models.entities.Match;
import com.example.communityminifootballleagueorganiser.models.entities.Team;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LeagueDTO {

    private Long id;
    private String name;
    private List<TeamDTO> teamList;
    private List<MatchDTO> matchList;
}
