package com.example.communityminifootballleagueorganiser.models.dtos;

import com.example.communityminifootballleagueorganiser.services.PlayerService;
import com.example.communityminifootballleagueorganiser.utils.enums.PlayerPosition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlayerDTO {

    private Long id;
    @NotBlank
    @Size(min = 3, max = 20, message = "first name must be between 3  and 20 characters")
    private String firstName;
    @NotBlank
    @Size(min = 3, max = 20, message = "last name must be between 3  and 20 characters")
    private String lastName;
    @NotBlank
    private PlayerPosition playerPosition;
    @Size(min = 0)
    private int goals;

    private int legitimationNumber;
}
