package com.example.communityminifootballleagueorganiser.exceptions.Match;

public class MatchAlreadyPlayedException extends RuntimeException{
    public MatchAlreadyPlayedException(String message) {
        super(message);
    }
}
