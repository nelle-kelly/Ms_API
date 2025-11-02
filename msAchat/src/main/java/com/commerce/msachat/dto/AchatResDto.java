package com.commerce.msachat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

/**
 * DTO pour RENVOYER un achat (Response)
 * Contient toutes les informations + l'ID généré
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AchatResDto {
    private Long id;
    private LocalDate date;
    private String currency;
    private List<Long> productsId;
    private double total;

    //Liste des détails des produits (pour affichage)
    private List<ProductDTO> products;
}
