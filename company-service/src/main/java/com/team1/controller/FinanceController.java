package com.team1.controller;

import com.team1.dto.request.FinanceDto;
import com.team1.dto.request.WorkerDto;
import com.team1.repository.entity.Finance;
import com.team1.repository.entity.Worker;
import com.team1.service.FinanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.team1.constant.EndPoints.*;
@RestController
@RequestMapping(FINANCE)
@RequiredArgsConstructor
public class FinanceController {

    private final FinanceService financeService;

    /*@PostMapping("/finance-create")
    public ResponseEntity<Boolean> createFinance(@RequestParam String token, @RequestBody FinanceDto dto){
        return ResponseEntity.ok(financeService.createFinance(token, dto));
    }*/
    @GetMapping(FINDALL_FINANCE)
    public ResponseEntity<List<Finance>> findAllFinance(){
        return ResponseEntity.ok(financeService.findAllFinance());
    }

    @DeleteMapping(DELETE_BY_ID_FINANCE)
    public ResponseEntity<Boolean> deleteFinance(String token, String companyid){
        return ResponseEntity.ok(financeService.deleteFinance(token, companyid));
    }

}
