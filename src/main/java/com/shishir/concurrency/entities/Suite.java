package com.shishir.concurrency.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "suite")
public class Suite {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String status = "AVAILABLE";

    private String bookedBy;

    @Version
    private Integer version;


}
