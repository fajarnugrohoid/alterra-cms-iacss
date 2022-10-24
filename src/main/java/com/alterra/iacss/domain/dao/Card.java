package com.alterra.iacss.domain.dao;

import com.alterra.iacss.domain.common.BaseDao;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "c_card")
@SQLDelete(sql = "UPDATE c_card SET is_deleted = true WHERE id = ?")
@Where(clause = "is_deleted = false")
public class Card extends BaseDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private Long id;

    @Column(name = "card_number", nullable = false)
    private String cardNumber;

    @Column(name = "card_holder")
    private String cardHolder;

    @Column(name = "cust_number")
    private String custNumber;

    @JoinColumn(name = "limit_profile")
    @ManyToOne
    private LimitProfile limitProfile;


    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "issue_date")
    private LocalDateTime issueDate;

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "expired_date")
    private LocalDateTime expireDate;

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "last_used_date")
    private LocalDateTime lastUsedDate;

    @Column(name = "branch_code")
    private String branchCode;

    @Column(name = "pin_offset")
    private String pinOffset;

    @Column(name = "pin_retry_attempt")
    private Integer pinRetryAttempt;

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "pin_retry_date")
    private LocalDateTime pinRetryDate;

    @JoinColumn(name = "status")
    @ManyToOne
    private CardStatus cardStatus;

    
}
