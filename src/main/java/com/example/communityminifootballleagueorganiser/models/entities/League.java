package com.example.communityminifootballleagueorganiser.models.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    private Long leagueId;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "league")
    @JsonManagedReference
    private List<Team> teamList = new ArrayList<>();
    @OneToMany
    private List<Match> matchList = new ArrayList<>();
    boolean isStarded;
}
