package com.example.communityminifootballleagueorganiser.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
    private Long id;
    @NotNull
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "league", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Team> teamList = new ArrayList<>();
    @OneToMany(mappedBy = "league", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Match> matchList = new ArrayList<>();

}
