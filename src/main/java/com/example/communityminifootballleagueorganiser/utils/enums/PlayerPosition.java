package com.example.communityminifootballleagueorganiser.utils.enums;

import lombok.Getter;

@Getter
public enum PlayerPosition {

        GOALKEEPER("goalkeeper"),
        DEFFENDER("deffender"),
        MIDFIELDER("midfielder"),
        ATTACKER("Attacker");

        private final String playerPosition;

    PlayerPosition(String playerPosition) {
        this.playerPosition = playerPosition;
    }
}
