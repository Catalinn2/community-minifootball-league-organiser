package com.example.communityminifootballleagueorganiser.models.entities;

import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.LinkedHashMap;
import java.util.Map;

@Data
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
    @Column(name = "goals")
    private int goals;
    @ElementCollection
    @CollectionTable(name = "player_position",
            joinColumns = @JoinColumn(name = "player_id"),
    )
    @MapKeyColumn(name = "player")
    @Column(name = "player_position")
    private Map<String, String> playerPosition = new LinkedHashMap<>();
}
