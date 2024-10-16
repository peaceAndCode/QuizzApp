package com.peaceandcode.quizapp.compositekey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class PlayerAnswerId {
    private Long playerId;
    private Long answerId;
}
