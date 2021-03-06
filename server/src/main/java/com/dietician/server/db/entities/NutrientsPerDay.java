package com.dietician.server.db.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NutrientsPerDay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int calories;

    @Column(nullable = false)
    private int carbohydrates;

    @Column(nullable = false)
    private int proteins;

    @Column(nullable = false)
    private int fat;
}
