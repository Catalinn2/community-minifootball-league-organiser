package com.example.communityminifootballleagueorganiser.exceptions.Match;

public class MatchNotFoundException extends RuntimeException{
    public MatchNotFoundException(String message) {
        super(message);
    }
}
