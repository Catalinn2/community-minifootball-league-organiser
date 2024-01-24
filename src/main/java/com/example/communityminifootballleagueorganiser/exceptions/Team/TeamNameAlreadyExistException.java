package com.example.communityminifootballleagueorganiser.exceptions.Team;

public class TeamNameAlreadyExistException extends RuntimeException{
    public TeamNameAlreadyExistException(String message) {
        super(message);
    }
}
