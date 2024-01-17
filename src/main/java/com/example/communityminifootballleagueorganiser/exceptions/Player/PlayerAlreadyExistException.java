package com.example.communityminifootballleagueorganiser.exceptions.Player;

public class PlayerAlreadyExistException extends RuntimeException{
    public PlayerAlreadyExistException(String message) {
        super(message);
    }
}
