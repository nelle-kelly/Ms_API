package com.commerce.msachat.web;

import com.commerce.msachat.dto.AchatReqDto;
import com.commerce.msachat.dto.AchatResDto;
import com.commerce.msachat.services.AchatService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/achat")
public class AchatController {

    private final AchatService achatService;

    // Injection du service par constructeur
    public AchatController(AchatService achatService) {
        this.achatService = achatService;
    }

    @GetMapping("/{id}")
    public AchatResDto getAchat(@PathVariable Long id) {
        return achatService.getAchatById(id);
    }

    /**
     POST /api/achat/create
     Crée un nouvel achat
     Body exemple: { "currency": "USD", "productsId": [1, 2, 3] }
     */

    @PostMapping("/create")
    public AchatResDto createAchat(@RequestBody AchatReqDto achatReqDto) {
        return achatService.createAchat(achatReqDto);
    }

    /**
     GET /api/achat/all
     Récupère tous les achats
     */

    @GetMapping("/all")
    public List<AchatResDto> getAllAchats() {
        return achatService.getAllAchats();
    }
}
