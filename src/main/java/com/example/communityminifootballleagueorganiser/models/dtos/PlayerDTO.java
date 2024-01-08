package com.example.communityminifootballleagueorganiser.models.dtos;

import lombok.Data;

@Data
public class PlayerDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String position;
    private int goals;
}
