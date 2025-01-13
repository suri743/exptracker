package com.suri.exptracker.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@Entity
@SequenceGenerator(name = "split_seq", sequenceName = "split_seq", allocationSize = 1)
public class Split extends BaseEntity {
    @OneToOne
    private User user;
    private double amount;

    @ManyToOne
    @JoinColumn(name = "expense_id")
    private Expense expense;
}
