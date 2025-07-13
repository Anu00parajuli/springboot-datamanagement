package com.eon.springbootdatamanagement.entity;

import com.eon.springbootdatamanagement.enums.StatusEnum;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "currency_table")
public class CurrencyEntity extends BaseEntity {
    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @PrePersist
    void prePersist() {
        this.status = StatusEnum.ACTIVE;
    }
}
