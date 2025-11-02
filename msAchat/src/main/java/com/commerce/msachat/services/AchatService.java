package com.commerce.msachat.services;

import com.commerce.msachat.dto.AchatReqDto;
import com.commerce.msachat.dto.AchatResDto;

import java.util.List;


public interface AchatService {


    AchatResDto getAchatById(Long id);

    /** La cette methode vas me permettre de créer un nouvel achat
     - Récupère les produits depuis msProduct
     - Calcule le prix total en EUR
     - Convertit le prix dans la devise demandée

     en param je passe achatReqDto
     et je retourne AchatResDto - L'achat créé avec le total calculé
     */

    AchatResDto createAchat(AchatReqDto achatReqDto);

    List<AchatResDto> getAllAchats();
}
