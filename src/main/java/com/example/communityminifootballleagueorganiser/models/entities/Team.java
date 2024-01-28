package com.example.communityminifootballleagueorganiser.models.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teamId;
    @Column(name = "name")
    private String name;
    @ManyToOne
    @JoinColumn(name = "league_id", nullable = true)
    @JsonBackReference
    private League league;
    @OneToMany
    private List<Player> playerList = new ArrayList<>();
    @Column(name = "points")
    private int points;
}
