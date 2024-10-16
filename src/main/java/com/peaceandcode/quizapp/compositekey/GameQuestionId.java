package com.peaceandcode.quizapp.compositekey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@Data
@EqualsAndHashCode
public class GameQuestionId {
    private Long questionId;
    private Long gameId;
}
