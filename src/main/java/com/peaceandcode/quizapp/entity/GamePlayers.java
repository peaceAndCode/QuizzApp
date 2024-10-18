package com.peaceandcode.quizapp.entity;

import com.peaceandcode.quizapp.compositekey.GamePlayerId;
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

@Entity(name = "game_players")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GamePlayers {
    @EmbeddedId
    private GamePlayerId id;
    @ManyToOne
    @MapsId("playerId")
    @JoinColumn(name = "player_user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;
    @ManyToOne
    @MapsId("gameId")
    @JoinColumn(name = "game_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Game game;
    @NotNull
    private Long insertionOrder;
    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;
}
