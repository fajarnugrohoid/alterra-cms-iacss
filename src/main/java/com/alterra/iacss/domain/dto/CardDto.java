package com.alterra.iacss.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.OneToMany;
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

    private String cardHolder;

    private String custNumber;

    private Long limitProfile;

    private String issueDate;

    private String expiredDate;

    private String lastUsedDate;

    private String branchCode;

    private String pinOffset;

    private String pinRetryAttempt;

    private String pinRetryDate;

    private Long cardStatus;
    
}
