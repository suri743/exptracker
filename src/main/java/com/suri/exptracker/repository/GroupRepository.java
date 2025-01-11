package com.suri.exptracker.repository;

import com.suri.exptracker.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {
    List<Group> findAllByCreatedBy_Id(int userId);
}
