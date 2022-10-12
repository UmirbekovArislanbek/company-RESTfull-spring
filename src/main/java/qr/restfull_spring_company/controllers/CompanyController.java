package qr.restfull_spring_company.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import qr.restfull_spring_company.dto.CompanyDto;
import qr.restfull_spring_company.entities.Company;
import qr.restfull_spring_company.payloads.Result;
import qr.restfull_spring_company.services.CompanyService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/company")
public class CompanyController {
    CompanyService companyService;

    @GetMapping
    public ResponseEntity<List<Company>> getAll(){
        return ResponseEntity.ok(companyService.getAllCompanies());
    }
    @GetMapping("/{companyId}")
    private ResponseEntity<Company> getCompany(@Valid @PathVariable Integer companyId) {
        return ResponseEntity.ok(companyService.getCompanyById(companyId));
    }
    @PostMapping
    private ResponseEntity<Result> addCompany(@Valid @RequestBody CompanyDto companyDTO) {
        return ResponseEntity.status(companyService.addNewCompany(companyDTO).getStatus()).body(companyService.addNewCompany(companyDTO));
    }
    @PutMapping("/{companyId}")
    private ResponseEntity<Result> updateCompany(@Valid @PathVariable Integer companyId,
                                                 @RequestBody CompanyDto companyDTO) {
        return ResponseEntity.status(companyService.updateCompanyById(companyId, companyDTO).getStatus()).body(companyService.updateCompanyById(companyId, companyDTO));
    }
    @DeleteMapping("/{companyId}")
    private ResponseEntity<Result> deleteCompany(@Valid @PathVariable Integer companyId) {
        return ResponseEntity.status(companyService.deleteCompanyById(companyId).getStatus()).body(companyService.deleteCompanyById(companyId));
    }
}
