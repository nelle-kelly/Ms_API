package ma.mundiapolis.msexchange.web;

import ma.mundiapolis.msexchange.dto.ConversionResponse;
import ma.mundiapolis.msexchange.services.ConversionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exchange")
public class ExchangeController {

    private final ConversionService conversionService;

    // Injection du service par constructeur
    public ExchangeController(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    /**
     GET /api/exchange/convert?montant=100&from=EUR&to=USD
     Convertit un montant d'une devise vers une autre
     Renvoie un objet complet avec tous les détails
     */
    @GetMapping("/convert")
    public ConversionResponse convertir(
            @RequestParam double montant,
            @RequestParam String from,
            @RequestParam String to) {

        return conversionService.convertir(montant, from, to);
    }


     //api/exchange/from-eur?montant=100&to=USD

    @GetMapping("/from-eur")
    public double convertirDepuisEUR(
            @RequestParam double montant,
            @RequestParam String to) {

        return conversionService.convertirDepuisEUR(montant, to);
    }
}
