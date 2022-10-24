package com.alterra.iacss.domain.dto;

import com.alterra.iacss.domain.dao.CardStatus;
import com.alterra.iacss.domain.dao.LimitProfile;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CardDto {

    private Long id;

    private String cardNumber;

    /*
    private String cardHolder;

    private String custNumber;

    private LimitProfile limitProfile;

    private LocalDateTime issueDate;

    private LocalDateTime expireDate;

    private LocalDateTime lastUsedDate;

    private String branchCode;

    private String pinOffset;

    private String pinRetryAttempt;

    private LocalDateTime pinRetryDate;

    private CardStatus cardStatus; */
    
}
