package qr.restfull_spring_company.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import qr.restfull_spring_company.dto.DepartmentDto;
import qr.restfull_spring_company.entities.Department;
import qr.restfull_spring_company.payloads.Result;
import qr.restfull_spring_company.services.DepartmentService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/department")
@RequiredArgsConstructor
public class DepartmentrController {
    DepartmentService departmentService;

    @GetMapping
    private ResponseEntity<List<Department>> getDepartments() {
        return ResponseEntity.ok(departmentService.getDepartmentList());
    }

    @GetMapping("/{departmentId}")
    private ResponseEntity<Department> getDepartment(@PathVariable Integer departmentId) {
        return ResponseEntity.ok(departmentService.getDepartmentById(departmentId));
    }

    @PostMapping
    private ResponseEntity<Result> addDepartment(@Valid @RequestBody DepartmentDto departmentDTO) {
        return ResponseEntity
                .status(departmentService.addNewDepartment(departmentDTO).getStatus())
                .body(departmentService.addNewDepartment(departmentDTO));
    }

    @PutMapping("/{departmentId}")
    private ResponseEntity<Result> updateDepartment(@Valid @RequestBody DepartmentDto departmentDTO,
                                                    @PathVariable Integer departmentId) {
        return ResponseEntity
                .status(departmentService.updateDepartmentById(departmentDTO, departmentId).getStatus())
                .body(departmentService.updateDepartmentById(departmentDTO, departmentId));
    }

    @DeleteMapping("/{departmentId}")
    private ResponseEntity<Result> deleteDepartment(@PathVariable Integer departmentId) {
        return ResponseEntity
                .status(departmentService.deleteDepartmentById(departmentId).getStatus())
                .body(departmentService.deleteDepartmentById(departmentId));
    }
}
