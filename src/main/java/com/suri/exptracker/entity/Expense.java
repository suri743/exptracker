package com.suri.exptracker.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Entity
public class Expense extends BaseEntity {
    private String name;
    private String note;

    @OneToOne
    private Group group;
    private double amount;

    @OneToOne
    private User paidBy;

    @OneToMany(mappedBy = "expense", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Split> owed;
}
