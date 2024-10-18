package com.peaceandcode.quizapp.compositekey;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
@NoArgsConstructor
public class PlayerAnswerId {
    private Long playerId;
    private Long answerId;
}
