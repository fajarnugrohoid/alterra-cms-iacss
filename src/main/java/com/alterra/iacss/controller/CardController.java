package com.alterra.iacss.controller;

import com.alterra.iacss.domain.dto.CardDto;
import com.alterra.iacss.domain.dto.CardListDto;
import com.alterra.iacss.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/card")
public class CardController {

    @Autowired
    private CardService cardService;

    @PostMapping(value = "")
    public ResponseEntity<Object> createNewCard(@RequestBody CardDto request) {
        return cardService.addCard(request);
    }

    @GetMapping(value = "")
    public ResponseEntity<Object> getAllCard() {
        return cardService.getAllCard();
    }

    @GetMapping(value = "/status")
    public ResponseEntity<Object> getCardByStatus(@RequestParam(value = "status", required = false) Long cardStatusId) {
        return cardService.getCardByStatus(cardStatusId);
    }


    @PostMapping(value = "/pagination")
    public ResponseEntity<Object> getAllCardPagination(@RequestBody CardListDto request) {
        return cardService.getAllCardPagination(request);
    }

    @GetMapping(value = "/sort-by-card-number")
    public ResponseEntity<Object> getAllCardSortByCardNumber(@RequestParam(value = "sort", required = true) Sort.Direction direction) {
        return cardService.getAllCardSortByCardNumber(direction);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<Object> searchCard(@RequestParam(value = "card_number") String cardName) {
        return cardService.searchCardByName(cardName);
    }
    
    
}
