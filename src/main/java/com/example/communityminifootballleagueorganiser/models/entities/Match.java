package com.example.communityminifootballleagueorganiser.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "matches")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long matchId;
    @ManyToOne
    @JoinColumn(name = "league_id")
    private League league;
    @Column(name = "team1")
    private Team team1;
    @Column(name = "team2")
    private Team team2;

}
