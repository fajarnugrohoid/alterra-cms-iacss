package com.alterra.iacss.domain.dao;

import com.alterra.iacss.domain.common.BaseDao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "m_limit_profile")
@SQLDelete(sql = "UPDATE m_limit SET is_deleted = true WHERE id = ?")
@Where(clause = "is_deleted = false")
public class LimitProfile extends BaseDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "limit_name", nullable = false)
    private String limitName;

    @Column(name = "description")
    private String description;

    @Column(name = "charges", nullable = false)
    private Double charges;
    
}
