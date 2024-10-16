package com.peaceandcode.quizapp.compositekey;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@AllArgsConstructor
@EqualsAndHashCode
public class GamePlayerId implements Serializable {
    private Long playerId;
    private Long gameId;
}
