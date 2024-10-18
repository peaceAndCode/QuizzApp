package com.peaceandcode.quizapp.entity;

import com.peaceandcode.quizapp.compositekey.PlayerAnswerId;
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

@Entity(name = "player_answers")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlayerAnswers {
    @EmbeddedId
    private PlayerAnswerId id;
    @ManyToOne
    @MapsId("playerId")
    @JoinColumn(name = "player_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;
    @ManyToOne
    @MapsId("answerId")
    @JoinColumn(name = "answer_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Answer answer;
    @NotNull
    private Long insertionOrder;
    @CreationTimestamp
    Timestamp createdAt;
    @UpdateTimestamp
    Timestamp updatedAt;
}
