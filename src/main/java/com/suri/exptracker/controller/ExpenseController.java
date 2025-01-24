package com.suri.exptracker.controller;

import com.suri.exptracker.dto.ExpenseDto;
import com.suri.exptracker.dto.requestdtos.ExpenseRequestDto;
import com.suri.exptracker.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/exptracker")
public class ExpenseController {

    private final ExpenseService expenseService;

    @PostMapping("/expense")
    public ResponseEntity<ExpenseDto> createExpense(@RequestBody ExpenseRequestDto expenseRequestDto) {
        return ResponseEntity.ok(expenseService.createExpense(expenseRequestDto));
    }

    @GetMapping("/expense/{id}")
    public ResponseEntity<ExpenseDto> getExpenses(@PathVariable("id") int id) {
        return ResponseEntity.ok(expenseService.getExpense(id));
    }

    @GetMapping("/expense")
    public ResponseEntity<List<ExpenseDto>> getExpenses() {
        return ResponseEntity.ok(expenseService.getExpenses());
    }

}
