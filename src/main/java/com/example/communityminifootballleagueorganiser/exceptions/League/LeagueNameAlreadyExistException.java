package com.example.communityminifootballleagueorganiser.exceptions.League;

public class LeagueNameAlreadyExistException extends RuntimeException{
    public LeagueNameAlreadyExistException(String message) {
        super(message);
    }
}
