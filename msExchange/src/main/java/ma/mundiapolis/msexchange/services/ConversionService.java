package ma.mundiapolis.msexchange.services;

import ma.mundiapolis.msexchange.dto.ConversionResponse;
import ma.mundiapolis.msexchange.dto.ExchangeRateResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

/**
 Service de conversion de devises
 Appelle l'API ExchangeRate pour obtenir les taux de change
 */

@Service
public class ConversionService {

    private final WebClient.Builder webClientBuilder;

    // Lecture encore de l'URL depuis application.properties
    @Value("${exchangerate.api.url}")
    private String apiUrl;

    // Injection du WebClient par le constructeur
    public ConversionService(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }


    // cette fonction cest pour convertir depuis nimporte quel devise mais comme vous avez specifier depuis euro par defaut je ferai une autre plus simple qui utilisera celui ci
    public ConversionResponse convertir(double montant, String deviseOrigine, String deviseCible) {


        // https://v6.exchangerate-api.com/v6/dbb84eba92855fb029b13f67/latest/EUR
        String url = apiUrl + "/" + deviseOrigine;

        // 2. Appeler l'API ExchangeRate pour obtenir les taux
        ExchangeRateResponse response = webClientBuilder.build()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(ExchangeRateResponse.class)
                .block();

        // 3. Vérifier si l'API a répondu correctement
        if (response == null || !"success".equals(response.getResult())) {
            throw new RuntimeException("Erreur lors de l'appel à l'API ExchangeRate");
        }



        // 4. Récupérer le taux de conversion pour la devise cible
        Double tauxConversion = response.getConversion_rates().get(deviseCible);

        if (tauxConversion == null) {
            throw new RuntimeException("Devise cible introuvable : " + deviseCible);
        }

        // 5. Calculer le montant converti
        double montantConverti = montant * tauxConversion;

        // 6. Construire et renvoyer la réponse
        return ConversionResponse.builder()
                .montantOriginal(montant)
                .deviseOrigine(deviseOrigine)
                .montantConverti(montantConverti)
                .deviseCible(deviseCible)
                .tauxConversion(tauxConversion)
                .build();
    }


    // MÉTHODE SIMPLIFIÉE : Convertir depuis EUR (pour msAchat)

    public double convertirDepuisEUR(double montantEUR, String deviseCible) {

        // Si la devise cible est EUR, pas besoin de conversion
        if ("EUR".equalsIgnoreCase(deviseCible)) {
            return montantEUR;
        }

        // Sinon, on fait la conversion
        ConversionResponse response = convertir(montantEUR, "EUR", deviseCible);
        return response.getMontantConverti();
    }
}
