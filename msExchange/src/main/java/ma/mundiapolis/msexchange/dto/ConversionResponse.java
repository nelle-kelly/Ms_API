package ma.mundiapolis.msexchange.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 DTO pour renvoyer le résultat de conversion
 Exemple : Convertir 100 EUR en USD
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConversionResponse {
    private double montantOriginal;
    private String deviseOrigine;
    private double montantConverti;
    private String deviseCible;
    private double tauxConversion;
}
