package com.peaceandcode.quizapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Category {
    @Id
    @GeneratedValue(generator = "category_sequence",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequenceName = "category_sequence",name = "category_sequence",allocationSize = 1)
    private Long id;
    @NotBlank
    private String name;
    @CreationTimestamp
    private Timestamp created;
    @UpdateTimestamp
    private Timestamp updated;
}
