package com.alterra.iacss.service;

import com.alterra.iacss.constant.AppConstant;
import com.alterra.iacss.domain.dao.*;
import com.alterra.iacss.domain.dto.CardDto;
import com.alterra.iacss.domain.dto.CardListDto;
import com.alterra.iacss.repository.*;
import com.alterra.iacss.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CardService {

    @Autowired
    private LimitProfileRepository limitProfileRepository;

    @Autowired
    private CardStatusRepository cardStatusRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private ModelMapper mapper;

    public ResponseEntity<Object> addCard(CardDto request) {
        log.info("Executing add new card");
        try {

            log.info( "request.getLimitProfile().getId:" + request.getLimitProfile() );


            Optional<LimitProfile> limitProfile = limitProfileRepository.findById( request.getLimitProfile() );
            if (limitProfile.isEmpty()) {
                log.info("LimitProfile [{}] not found", request.getCardStatus() );
                return ResponseUtil.build(AppConstant.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.BAD_REQUEST);
            }

            Optional<CardStatus> cardStatus = cardStatusRepository.findById(request.getCardStatus());
            if (cardStatus.isEmpty()) {
                log.info("CardStatus [{}] not found", request.getCardStatus());
                return ResponseUtil.build(AppConstant.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.BAD_REQUEST);
            }

            Card card = mapper.map(request, Card.class);
            //log.info( "limitProfile.get:" + limitProfile.get() );
            //log.info( "cardStatus.get:" + cardStatus.get() );
            card.setLimitProfile( limitProfile.get() );
            card.setCardStatus( cardStatus.get() );
            cardRepository.save(card);
            
            return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, mapper.map(card, CardDto.class), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Got an error when saving new card. Error: {}", e.getMessage());
            return ResponseUtil.build(AppConstant.ResponseCode.UNKNOWN_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getAllCard() {
        log.info("Executing get all card");
        try {
            List<Card> cards = cardRepository.findAll();
            log.info( "cards.size:" + cards.size() );
            for ( Card x : cards ){
                log.info( "x:" + x.getCardNumber() );
            }
            return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, cards, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Got an error when saving new card. Error: {}", e.getMessage());
            return ResponseUtil.build(AppConstant.ResponseCode.UNKNOWN_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<Object> getCardByStatus(Long cardStatusId) {
        try {
            log.info("Executing get all cards by status [{}]", cardStatusId);
            List<Card> cards;
            List<CardDto> cardDtoList = new ArrayList<>();

            /*
            if (cardStatusId != null) cards = cardRepository.findAllByCardStatus(cardStatusId);
            else cards = cardRepository.findAll();

            for (Card card : cards) {
                cardDtoList.add(mapper.map(card, CardDto.class));
            }*/

            return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, cardDtoList, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Got an error when get all card by status. Error: {}", e.getMessage());
            return ResponseUtil.build(AppConstant.ResponseCode.UNKNOWN_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<Object> getAllCardPagination(CardListDto request) {
        try {
            log.info("Executing get all card with pagination");
            int page = null == request.getPage() ? 0 : request.getPage();
            int size = null == request.getSize() ? 1 : request.getSize();
            Pageable pageable = PageRequest.of(page, size);
            Page<Card> cardPage = cardRepository.findAll(pageable);

            log.info("Mapping page into dtos. Size: [{}]", cardPage.getTotalElements());
            List<CardDto> cardDtoList = new ArrayList<>();
            
            for (Card card : cardPage.getContent()) {
                cardDtoList.add(mapper.map(card, CardDto.class));
            }

            CardListDto cardListDto = CardListDto.builder()
                .cards(cardDtoList)
                .size(cardPage.getSize())
                .page(cardPage.getNumber())
                .totalPage(cardPage.getTotalPages())
                .build();

                return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, cardListDto, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Got an error when get all card with pagination. Error: {}", e.getMessage());
            return ResponseUtil.build(AppConstant.ResponseCode.UNKNOWN_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getAllCardSortByCardNumber(Sort.Direction direction) {
        try {
            log.info("Executing get all card sort by category [{}]", direction);
            List<Card> cards = cardRepository.findAll(Sort.by(direction, "category.id"));
            List<CardDto> cardDtoList = new ArrayList<>();
        
            for (Card card : cards) {
                cardDtoList.add(mapper.map(card, CardDto.class));
            }

            return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, cardDtoList, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Got an error when get all card sort by category. Error: {}", e.getMessage());
            return ResponseUtil.build(AppConstant.ResponseCode.UNKNOWN_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> searchCardByName(String cardNumber) {
        try {
            log.info("Executing search card by name: [{}]", cardNumber);
            Card card = cardRepository.findByCardNumberContaining(cardNumber);

            return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, mapper.map(card, CardDto.class), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Got an error when search card by card name. Error: {}", e.getMessage());
            return ResponseUtil.build(AppConstant.ResponseCode.UNKNOWN_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
