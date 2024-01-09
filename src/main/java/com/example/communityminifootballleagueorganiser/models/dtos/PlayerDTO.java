package com.example.communityminifootballleagueorganiser.models.dtos;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashMap;
import java.util.Map;

@Data
public class PlayerDTO {

    private Long id;
    @NotBlank
    @Size(min = 3, max = 20,, message = "first name must be between 3  and 20 characters")
    private String firstName;
    @NotBlank
    @Size(min = 3, max = 20,, message = "last name must be between 3  and 20 characters")
    private String lastName;
    @Size(min = 0)
    private int goals;
    private Map<String, String> playerPosition = new HashMap<>();
}
