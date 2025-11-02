package com.commerce.msachat.mappers;

import com.commerce.msachat.dto.AchatReqDto;
import com.commerce.msachat.dto.AchatResDto;
import com.commerce.msachat.entities.Achat;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AchatMapper {

    // Convertit l'Entity Achat → DTO de réponse
    AchatResDto toDto(Achat achat);

    // Convertit le DTO de requête → Entity Achat
    Achat toEntity(AchatReqDto achatReqDto);
}
