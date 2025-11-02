package com.commerce.msachat.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Achat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private String currency;

    // ce que jai compris ici cest juste je veux stocker une liste de valeurs simples sans entité ni logique si non jaurai fait une liaison
    @ElementCollection
    private List<Long> productsId = new ArrayList<>();

    private double total;

}
