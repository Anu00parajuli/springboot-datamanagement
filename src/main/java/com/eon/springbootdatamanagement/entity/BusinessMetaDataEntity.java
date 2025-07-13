package com.eon.springbootdatamanagement.entity;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BusinessMetaDataEntity extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;
}
