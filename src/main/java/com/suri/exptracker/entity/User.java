package com.suri.exptracker.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@Entity
@SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
public class User extends BaseEntity {
    private String name;
    private String email;
    private String mobile;

    @ManyToMany(mappedBy = "members", fetch = FetchType.LAZY)
    private List<Group> groups;
}
