package com.rumesh.horseharnes.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "HORSE")
public class Horse {

    @Id
    @Column(name = "HORSE_ID")
    private String horseId;

    @Column(name = "NAME")
    private String name;
}
