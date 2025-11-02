package ma.mundiapolis.msexchange.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 DTO pour mapper la réponse de l'API ExchangeRate
 Exemple de réponse :
 {
 "result": "success",
 "base_code": "USD",
 "conversion_rates": {
 "EUR": 0.8635,
 "MAD": 9.2561,
    }
 }
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRateResponse {
    private String result;
    private String base_code;

    private Map<String, Double> conversion_rates;
}
