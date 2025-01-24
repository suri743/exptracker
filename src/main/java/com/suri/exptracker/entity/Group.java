package com.suri.exptracker.entity;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapKeyJoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@Entity
@Table(name = "`group`")
public class Group extends BaseEntity {
    private String name;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;
    private String description;

    @ManyToMany
    @JoinTable(name = "group_members", // Join table name
        joinColumns = @JoinColumn(name = "group_id"), // Foreign key to `Group`
        inverseJoinColumns = @JoinColumn(name = "user_id") // Foreign key to `User`
    )
    private List<User> members;

//    @ElementCollection
//    @CollectionTable(name = "group_balances", joinColumns = @JoinColumn(name = "group_id"))
//    @MapKeyJoinColumn(name = "user_id")
//    @Column(name = "balance")
//    private Map<User, Double> balances;

    @OneToMany(mappedBy = "group")
    private List<Expense> expenses;

    @OneToMany(mappedBy = "group")
    private List<Transaction> transactions;
}
