package ma.mundiapolis.msexchange.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRateResponse {
    private String result;
    private String base_code;

    private Map<String, Double> conversion_rates;
}
