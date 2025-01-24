package com.suri.exptracker.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@Entity
@FieldNameConstants
public class User extends BaseEntity {
    private String name;
    private String email;
    private String mobile;

    @ManyToMany(mappedBy = "members", fetch = FetchType.LAZY)
    private List<Group> groups;
}
