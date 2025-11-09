package com.commerce.msachat.services;

import com.commerce.msachat.dto.AchatReqDto;
import com.commerce.msachat.dto.AchatResDto;

import java.util.List;


public interface AchatService {


    AchatResDto getAchatById(Long id);



    AchatResDto createAchat(AchatReqDto achatReqDto);

    List<AchatResDto> getAllAchats();
}
