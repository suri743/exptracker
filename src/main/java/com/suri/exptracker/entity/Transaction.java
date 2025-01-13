package com.suri.exptracker.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@Entity
public class Transaction extends  BaseEntity {
    private String name;
    private String note;
    private double amount;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @ManyToOne
    @JoinColumn(name = "paid_by")
    private User paidBy;

    @ManyToOne
    @JoinColumn(name = "paid_to")
    private User paidTo;
}
