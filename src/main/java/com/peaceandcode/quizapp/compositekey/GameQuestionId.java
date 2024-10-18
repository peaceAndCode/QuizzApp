package com.peaceandcode.quizapp.compositekey;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@EqualsAndHashCode
@Embeddable
@NoArgsConstructor
public class GameQuestionId {
    private Long questionId;
    private Long gameId;
}
