package com.peaceandcode.quizapp.entity;

import com.peaceandcode.quizapp.compositekey.GameQuestionId;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity(name = "game_questions")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GameQuestions {
    @EmbeddedId
    private GameQuestionId id;
    @ManyToOne
    @MapsId("gameId")
    @JoinColumn(name = "game_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Game game;
    @ManyToOne
    @MapsId("questionId")
    @JoinColumn(name = "question_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Question question;
    @NotNull
    private Long insertionOrder;
    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;

}
