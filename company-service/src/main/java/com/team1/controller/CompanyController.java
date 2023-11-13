package com.team1.controller;
import com.team1.dto.request.IncomeDto;
import com.team1.dto.request.SpendingDto;
import com.team1.dto.request.SaveWorkerDto;
import com.team1.dto.response.CompanyListenerResponseDto;
import com.team1.dto.response.ResponceIncomeDto;
import com.team1.dto.response.ResponseSpendingDto;
import com.team1.dto.response.WorkerListResponse;
import com.team1.service.CompanyService;
import com.team1.service.FinanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import  static com.team1.constant.EndPoints.*;


@RestController
@RequestMapping(COMPANY)
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;
    private final FinanceService financeService;


    @PostMapping("/save/worker/{token}")
    public ResponseEntity<String> saveWorker(@PathVariable String token, @RequestBody SaveWorkerDto dto) {
        return ResponseEntity.ok(companyService.saveWorker(token, dto));
    }
//FIND_ALL_WORKER
    @GetMapping("/findAllWorker/{token}")
    public ResponseEntity<List<WorkerListResponse>> findAllWorker(@PathVariable String token){
        return ResponseEntity.ok(companyService.findAllCompanyWorker(token));
    }


    @PostMapping("/finance/spending/{token}")
    public ResponseEntity<ResponseSpendingDto> spending(@PathVariable String token, @RequestBody SpendingDto dto) {
        return ResponseEntity.ok(financeService.spending(token, dto));
    }

    @PostMapping("/finance/income/{token}")
    public ResponseEntity<ResponceIncomeDto> income(@PathVariable String token, @RequestBody IncomeDto dto) {
        return ResponseEntity.ok(financeService.income(token, dto));
    }

    @GetMapping("/financial/situation/{token}")
    public ResponseEntity<String> financialSituation(@PathVariable String token){
        return ResponseEntity.ok(financeService.financialSituation(token));
    }

    @PostMapping("/finance/income/total/{token}")
    public ResponseEntity<Double> incomeTotal(@PathVariable String token) {
        return ResponseEntity.ok(financeService.incomeTotal(token));
    }

    @GetMapping("list/company")
    public ResponseEntity<List<CompanyListenerResponseDto>> findAllCompany(){
        return ResponseEntity.ok(companyService.findAllCompany());
    }
}
