package com.peaceandcode.quizapp.compositekey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@AllArgsConstructor
@EqualsAndHashCode
@Data
public class GamePlayerId implements Serializable {
    private Long playerId;
    private Long gameId;
}
