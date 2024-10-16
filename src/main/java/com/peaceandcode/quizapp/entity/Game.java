package com.peaceandcode.quizapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Game {
    @Id
    @GeneratedValue(generator = "game_sequence",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "game_sequence",sequenceName = "game_sequence",allocationSize = 1)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "master_user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User master;
    private Boolean open=true;
    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;
}
