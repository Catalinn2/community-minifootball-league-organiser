package com.example.communityminifootballleagueorganiser.exceptions.League;

public class LeagueNotFoundException extends RuntimeException{
    public LeagueNotFoundException(String message) {
        super(message);
    }
}
