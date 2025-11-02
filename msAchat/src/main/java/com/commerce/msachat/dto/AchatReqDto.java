package com.commerce.msachat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO pour CRÉER un achat (Request)
 * Contient seulement les données nécessaires à l'entrée
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AchatReqDto {
    // La devise dans laquelle on veut le total (ex: "USD", "MAD", "EUR")
    private String currency;

    // Liste des IDs des produits à acheter
    private List<Long> productsId;
}
