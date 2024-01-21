package com.example.communityminifootballleagueorganiser.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "league")
public class League {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long leagueId;
    @NotEmpty
    @Column(name = "league_name")
    private String leagueName;
    @OneToMany(mappedBy = "league", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Team> teamList = new ArrayList<>();
    @OneToMany(mappedBy = "league", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Match> matchList = new ArrayList<>();

}
