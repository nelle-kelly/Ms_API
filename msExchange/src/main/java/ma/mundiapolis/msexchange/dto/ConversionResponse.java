package ma.mundiapolis.msexchange.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



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
