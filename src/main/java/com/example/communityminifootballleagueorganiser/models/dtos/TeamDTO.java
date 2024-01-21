package com.example.communityminifootballleagueorganiser.models.dtos;

import com.example.communityminifootballleagueorganiser.models.entities.League;
import com.example.communityminifootballleagueorganiser.models.entities.Player;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeamDTO {


    private Long teamId;
    @NotEmpty
    private String teamName;

    private League league;

    private List<PlayerDTO> playerList;
}


