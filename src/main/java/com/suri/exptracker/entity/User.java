package com.suri.exptracker.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
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
public class User extends BaseEntity {
    private String name;
    private String email;
    private String mobile;

    @ManyToMany(mappedBy = "members")
    private List<Group> groups;
}
