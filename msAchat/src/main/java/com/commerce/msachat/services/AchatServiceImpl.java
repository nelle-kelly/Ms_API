package com.commerce.msachat.services;

import com.commerce.msachat.dto.AchatReqDto;
import com.commerce.msachat.dto.AchatResDto;
import com.commerce.msachat.dto.ProductDTO;
import com.commerce.msachat.entities.Achat;
import com.commerce.msachat.mappers.AchatMapper;
import com.commerce.msachat.repo.AchatRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implémentation du service Achat
 * Gère la logique métier des achats
 */
@Service
public class AchatServiceImpl implements AchatService {

    private final AchatRepo achatRepo;
    private final AchatMapper mapper;

    // pour pouvoir appeler les autre microservices
    private final WebClient.Builder webClientBuilder;

    // Je li l'URL de msExchange depuis application.properties
    @Value("${msexchange.api.url}")
    private String exchangeServiceUrl;

    // Injection des dépendances par le constructeur
    public AchatServiceImpl(AchatRepo achatRepo, AchatMapper mapper, WebClient.Builder webClientBuilder) {
        this.achatRepo = achatRepo;
        this.mapper = mapper;
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public AchatResDto getAchatById(Long id) {
        Optional<Achat> achatOptional = achatRepo.findById(id);
        if (achatOptional.isPresent()) {
            Achat achat = achatOptional.get();
            AchatResDto achatResDto = mapper.toDto(achat);

            // Récupérer les détails des produits depuis msProduct
            List<ProductDTO> products = getProductsDetails(achat.getProductsId());
            achatResDto.setProducts(products);

            return achatResDto;
        } else {
            throw new EntityNotFoundException("Achat not found with id: " + id);
        }
    }

    @Override
    public AchatResDto createAchat(AchatReqDto achatReqDto) {
        // Je Convertis le DTO en Entity
        Achat achat = mapper.toEntity(achatReqDto);
        achat.setDate(LocalDate.now()); // Date automatique

        // Je récupère les produits depuis msProduct
        List<ProductDTO> products = getProductsDetails(achatReqDto.getProductsId());

        // Je Calcule le total en EUR (prix par défaut)
        double totalEUR = 0;

        for (ProductDTO product : products) {
            totalEUR += product.getPrice(); // On additionne chaque prix
        }

        // Convertir le prix dans la devise demandée via msExchange
        double totalConverted = convertCurrency(totalEUR, achatReqDto.getCurrency());

        achat.setTotal(totalConverted);

        // 5. Sauvegarder l'achat en base
        Achat savedAchat = achatRepo.save(achat);

        // 6. Convertir en DTO de réponse
        AchatResDto achatResDto = mapper.toDto(savedAchat);
        achatResDto.setProducts(products);

        return achatResDto;
    }

    @Override
    public List<AchatResDto> getAllAchats() {
        List<Achat> achats = achatRepo.findAll();
        List<AchatResDto> achatResDtos = new ArrayList<>();

        for (Achat achat : achats) {
            AchatResDto dto = mapper.toDto(achat);
            // Récupérer les détails des produits
            List<ProductDTO> products = getProductsDetails(achat.getProductsId());
            dto.setProducts(products);
            achatResDtos.add(dto);
        }

        return achatResDtos;
    }

    /**
     * MÉTHODE PRIVÉE pour récupérer les détails des produits depuis msProduct
     * Utilise WebClient pour faire des appels HTTP
     * 
     * @param productsId - Liste des IDs des produits
     * @return List<ProductDTO> - Liste des produits avec leurs détails
     */

    private List<ProductDTO> getProductsDetails(List<Long> productsId) {
        List<ProductDTO> products = new ArrayList<>();

        // URL du microservice Product (port 8080)
        String productServiceUrl = "http://localhost:8080/api/product";

        // Pour chaque ID, on fait un appel HTTP GET
        for (Long productId : productsId) {
            try {
                ProductDTO product = webClientBuilder.build()
                        .get()
                        .uri(productServiceUrl + "/" + productId)
                        .retrieve()
                        .bodyToMono(ProductDTO.class) // Convertit la réponse en ProductDTO
                        .block(); // Attend la réponse (synchrone)

                if (product != null) {
                    products.add(product);
                }
            } catch (Exception e) {
                // Si le produit n'existe pas, on l'ignore
                System.err.println("Erreur lors de la récupération du produit " + productId + ": " + e.getMessage());
            }
        }

        return products;
    }

    /*

     MÉTHODE PRIVÉE pour convertir un montant EUR vers une autre devise
     Utilise WebClient pour appeler msExchange

     @param montantEUR  - Montant en EUR
     @param deviseCible - Devise cible (USD, MAD, EUR, etc.)
     @return double - Montant converti (ou montant EUR si erreur)

     */

    private double convertCurrency(double montantEUR, String deviseCible) {
        // Si la devise est EUR, pas besoin de conversion
        if ("EUR".equalsIgnoreCase(deviseCible)) {
            return montantEUR;
        }

        try {
            // Appeler msExchange pour convertir
            // GET http://localhost:8082/api/exchange/from-eur?montant=450&to=USD
            Double montantConverti = webClientBuilder.build()
                    .get()
                    .uri(exchangeServiceUrl + "/from-eur?montant=" + montantEUR + "&to=" + deviseCible)
                    .retrieve()
                    .bodyToMono(Double.class)
                    .block();

            if (montantConverti != null) {
                return montantConverti;
            } else {
                // Fallback si réponse null
                System.err.println("Erreur : réponse null de msExchange, utilisation du montant EUR");
                return montantEUR;
            }

        } catch (Exception e) {
            // Fallback si msExchange est indisponible
            System.err.println("Erreur lors de l'appel à msExchange : " + e.getMessage());
            System.err.println("Fallback : utilisation du montant en EUR sans conversion");
            return montantEUR;
        }
    }
}
