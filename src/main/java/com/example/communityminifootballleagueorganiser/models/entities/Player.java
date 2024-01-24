package com.example.communityminifootballleagueorganiser.models.entities;

import com.example.communityminifootballleagueorganiser.utils.enums.PlayerPosition;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.LinkedHashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    @NotEmpty(message = "First name cannot be empty or null")
    private String firstName;
    @Column(name = "last_name")
    @NotEmpty(message = "Last name cannot be empty or null")
    private String lastName;
    @Column(name = "player_position")
    private PlayerPosition playerPosition;
    @Column(name = "goals")
    private int goals;
    @Column(name = "legitimation_number", unique = true)
    private int legitimationNumber;
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

}
