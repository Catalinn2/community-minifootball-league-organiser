package com.example.communityminifootballleagueorganiser.exceptions.Team;

public class TeamNotFoundException extends RuntimeException{
    public TeamNotFoundException(String message) {
        super(message);
    }
}
