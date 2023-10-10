package com.file.data.entity.user;

import com.file.data.entity.CreatedDateAuditedEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * User Currency Entity.
 *
 * @author Volodymyr Lykhvar
 */
@Entity
@Table
public class UserCurrency extends CreatedDateAuditedEntity {

    @Id
    @GeneratedValue()
    private UUID id;

    private String userId;

    private BigDecimal lastPrice;

}
