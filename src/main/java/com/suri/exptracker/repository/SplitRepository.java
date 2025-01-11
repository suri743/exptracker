package com.suri.exptracker.repository;

import com.suri.exptracker.entity.Split;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SplitRepository extends JpaRepository<Split, Integer> {

    List<Split> findAllByExpenseId(int expenseId);
}
